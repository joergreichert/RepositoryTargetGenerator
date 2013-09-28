package de.abg.jreichert.repositorytarget.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LocationManager {

	public Location getById(Long id) {
		Object result = null;
		Session session = SessionManager.currentSession();
		result = session.get(Location.class, id);
		return (Location) result;
	}

	public Location saveLocation(Location location) {
		Object result = null;
		Session session = SessionManager.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serializable id = session.save(location);
			tx.commit();
			result = getById((Long) id);
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return (Location) result;
	}

	public Location getByURL(String url) {
		Object result = null;
		Session session = SessionManager.currentSession();
		Criteria criteria = session.createCriteria(Location.class).add(
				Restrictions.eq("_url", url));
		result = criteria.uniqueResult();
		return (Location) result;
	}

	public void deleteLocation(Location location) {
		Session session = SessionManager.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(location);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}

	public void clearAll() {
		Session session = SessionManager.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class);
			Set<Location> locations = toLocationList(criteria.list());
			for (Location location : locations) {
				session.delete(location);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}

	private Set<Location> toLocationList(List<?> result) {
		Set<Location> set = new HashSet<Location>();
		for (Object obj : result) {
			if (obj instanceof Location) {
				set.add((Location) obj);
			}
		}
		return set;
	}

	public Map<String, Long> getTimestamps() {
		return getTimestamps(new HashMap<String, Long>());
	}

	public Map<String, Long> getTimestamps(Map<String, Long> timestampsToFilter) {
		Map<String, Long> timestamps = new HashMap<String, Long>();
		Session session = SessionManager.currentSession();
		Criteria criteria = session.createCriteria(Location.class);
		if (timestampsToFilter.size() > 0) {
			criteria = criteria.add(Restrictions.in("_url",
					timestampsToFilter.keySet()));
		}
		Set<Location> result = toLocationList(criteria.list());
		for (Location location : result) {
			timestamps.put(location.getUrl(),
					Long.valueOf(location.getTimestamp()));
		}
		return timestamps;
	}

	public SortedMap<String, SortedMap<String, SortedSet<String>>> getUrlToIdToVersions(
			Map<String, Long> timestampsToFilter) {
		SortedMap<String, SortedMap<String, SortedSet<String>>> urlToIdToVersions = new TreeMap<String, SortedMap<String, SortedSet<String>>>();
		Map<String, Long> locationToTimestamp = getTimestamps(timestampsToFilter);
		Location location = null;
		SortedSet<String> versions = null;
		SortedMap<String, SortedSet<String>> idToVersions;
		for (Entry<String, Long> entry : locationToTimestamp.entrySet()) {
			location = getByURL(entry.getKey());
			idToVersions = urlToIdToVersions.get(entry.getKey());
			if (idToVersions == null) {
				idToVersions = new TreeMap<String, SortedSet<String>>();
			}
			for (Unit unit : location.getUnits()) {
				versions = idToVersions.get(unit.getName());
				if (versions == null) {
					versions = new TreeSet<String>();
				}
				for (Version version : unit.getVersions()) {
					versions.add(version.getName());
				}
				idToVersions.put(unit.getName(), versions);
			}
			urlToIdToVersions.put(entry.getKey(), idToVersions);
		}
		return urlToIdToVersions;
	}

	public void saveLocation(Long timestamp, String locationURL,
			SortedMap<String, SortedSet<String>> idToVersions) {
		SortedSet<String> versions = new TreeSet<String>();
		Location location = getOrInitLocation(timestamp, locationURL);
		if (location != null) {
			fillUnits(idToVersions, location);
			SortedMap<String, SortedSet<String>> dbIdToVersions = fillDbIdToVersions(location);
			for (Entry<String, SortedSet<String>> dbEntry : dbIdToVersions
					.entrySet()) {
				versions = idToVersions.get(dbEntry.getKey());
				if (versions == null) {
					removeUnits(location, dbEntry);
				} else {
					removeVersions(location, dbEntry, versions);
				}
			}
			saveLocation(location);
		}
	}

	private void removeVersions(Location location,
			Entry<String, SortedSet<String>> dbEntry, SortedSet<String> newVersions) {
		List<Unit> unitsToDelete = new ArrayList<Unit>();
		for (Unit u : location.getUnits()) {
			if (u.getName().equalsIgnoreCase(dbEntry.getKey())) {
				List<Version> versionsToDelete = new ArrayList<Version>();
				boolean exists = false;
				for (Version v : u.getVersions()) {
					for (String newVersion : newVersions) {
						if (v.getName().equalsIgnoreCase(newVersion)) {
							exists = true;
							break;
						}
					}
					if(!exists) {
						versionsToDelete.add(v);
					}
				}
				u.getVersions().removeAll(versionsToDelete);
				if (u.getVersions().size() == 0) {
					unitsToDelete.add(u);
				}
			}
		}
		location.getUnits().removeAll(unitsToDelete);
	}

	private void removeUnits(Location location,
			Entry<String, SortedSet<String>> dbEntry) {
		List<Unit> unitsToDelete = new ArrayList<Unit>();
		for (Unit u : location.getUnits()) {
			if (u.getName().equalsIgnoreCase(dbEntry.getKey())) {
				unitsToDelete.add(u);
			}
		}
		location.getUnits().removeAll(unitsToDelete);
	}

	private void fillUnits(SortedMap<String, SortedSet<String>> idToVersions,
			Location location) {
		Map<String, Unit> nameToUnit = fillNameToUnit(location);
		Unit unit = null;
		for (Entry<String, SortedSet<String>> entry : idToVersions.entrySet()) {
			if ((unit = nameToUnit.get(entry.getKey())) == null) {
				unit = new Unit();
				unit.setLocation(location);
				unit.setName(entry.getKey());
				fillVersions(unit, entry);
				location.getUnits().add(unit);
			} else {
				unit.setLocation(location);
				unit.setName(entry.getKey());
				fillVersions(unit, entry);
			}
		}
	}

	private void fillVersions(Unit unit, Entry<String, SortedSet<String>> entry) {
		Version version;
		for (String versionStr : entry.getValue()) {
			if (findVersion(unit.getVersions(), versionStr) == null) {
				version = new Version();
				version.setName(versionStr);
				version.setUnit(unit);
				unit.getVersions().add(version);
			}
		}
	}

	private Version findVersion(Set<Version> versions, String versionStr) {
		for (Version version : versions) {
			if (versionStr.equalsIgnoreCase(version.getName())) {
				return version;
			}
		}
		return null;
	}

	private Map<String, Unit> fillNameToUnit(Location location) {
		Map<String, Unit> nameToUnit = new HashMap<String, Unit>();
		for (Unit unit : location.getUnits()) {
			nameToUnit.put(unit.getName(), unit);
		}
		return nameToUnit;
	}

	private SortedMap<String, SortedSet<String>> fillDbIdToVersions(
			Location location) {
		SortedSet<String> versions;
		SortedMap<String, SortedSet<String>> dbIdToVersions = new TreeMap<String, SortedSet<String>>();
		for (Unit unit : location.getUnits()) {
			versions = dbIdToVersions.get(unit.getName());
			if (versions == null) {
				versions = new TreeSet<String>();
			}
			for (Version version : unit.getVersions()) {
				versions.add(version.getName());
			}
			dbIdToVersions.put(unit.getName(), versions);
		}
		return dbIdToVersions;
	}

	private Location getOrInitLocation(Long timestamp, String locationURL) {
		Location location = getByURL(locationURL);
		if (location == null) {
			location = new Location();
			setTimestamp(timestamp, location);
			location.setUrl(locationURL);
		} else {
			setTimestamp(timestamp, location);
		}
		return location;
	}

	private void setTimestamp(Long timestamp, Location location) {
		if (timestamp == null) {
			location.setTimestamp(String.valueOf(new Date().getTime()));
		} else {
			location.setTimestamp(String.valueOf(timestamp));
		}
	}

	public void saveCompositeLocation(String parentLocationStr,
			Long parentLocationTimestamp, Set<String> aggregatedLocationStrs) {
		Location parentLocation = getOrInitLocation(parentLocationTimestamp,
				parentLocationStr);
		if (parentLocation != null) {
			Location aggregatedLocation = null;
			for (String aggregatedLocationStr : aggregatedLocationStrs) {
				aggregatedLocation = getByURL(aggregatedLocationStr);
				if (aggregatedLocation != null) {
					aggregatedLocation.setParentLocation(parentLocation);
					parentLocation.getAggregatedLocations().add(
							aggregatedLocation);
				}
			}
			saveLocation(parentLocation);
		}
	}
}
