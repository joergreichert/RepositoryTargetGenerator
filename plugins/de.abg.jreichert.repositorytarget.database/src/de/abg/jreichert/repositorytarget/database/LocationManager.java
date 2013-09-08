package de.abg.jreichert.repositorytarget.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		try {
			result = session.get(Location.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Location) result;
	}

	public Location saveLocation(Location location) {
		Object result = null;
		Session session = SessionManager.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serializable id = session.save(location);
			result = getById((Long) id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return (Location) result;
	}

	public Location getByURL(String url) {
		Object result = null;
		Session session = SessionManager.currentSession();
		try {
			Criteria criteria = session.createCriteria(Location.class);
			criteria.add(Restrictions.eq("_url", url));
			result = criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}

	public void clearAll() {
		Session session = SessionManager.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class);
			List<Location> locations = toLocationList(criteria.list());
			for (Location location : locations) {
				session.delete(location);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Location> toLocationList(List<?> result) {
		return (List<Location>) result;
	}

	public Map<String, Long> getTimestamps() {
		return getTimestamps(new HashMap<String, Long>());
	}

	public Map<String, Long> getTimestamps(Map<String, Long> timestampsToFilter) {
		Map<String, Long> timestamps = new HashMap<String, Long>();
		List<Location> result = null;
		Session session = SessionManager.currentSession();
		try {
			Criteria criteria = session.createCriteria(Location.class);
			//criteria.add(Restrictions.in("_url", timestampsToFilter.keySet()));
			result = toLocationList(criteria.list());
			for (Location location : result) {
				timestamps.put(location.getUrl(), location.getTimestamp());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			if(idToVersions == null) {
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

	public void save(Long timestamp, String locationURL,
			SortedMap<String, SortedSet<String>> idToVersions) {
		SortedSet<String> versions = new TreeSet<String>();
		Location location = getByURL(locationURL);
		if (location == null) {
			location = new Location();
			if(timestamp == null) {
				location.setTimestamp(new Date().getTime());
			} else {
				location.setTimestamp(timestamp);
			}
			location.setUrl(locationURL);
		}
		SortedMap<String, SortedSet<String>> dbIdToVersions = new TreeMap<String, SortedSet<String>>();
		if (location != null) {
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
			Map<String, Unit> nameToUnit = new HashMap<String, Unit>();
			for (Unit unit : location.getUnits()) {
				nameToUnit.put(unit.getName(), unit);
			}
			Unit unit = null;
			Version version = null;
			for (Entry<String, SortedSet<String>> entry : idToVersions
					.entrySet()) {
				if (!nameToUnit.keySet().contains(entry.getKey())) {
					unit = new Unit();
					unit.setLocation(location);
					unit.setName(entry.getKey());
					for (String versionStr : entry.getValue()) {
						version = new Version();
						version.setName(versionStr);
						version.setUnit(unit);
						unit.getVersions().add(version);
					}
					location.getUnits().add(unit);
				}
			}
			for (Entry<String, SortedSet<String>> dbEntry : dbIdToVersions
					.entrySet()) {
				versions = idToVersions.get(dbEntry.getKey());
				if (versions == null) {
					List<Unit> unitsToDelete = new ArrayList<Unit>();
					for (Unit u : location.getUnits()) {
						if (u.getName().equalsIgnoreCase(dbEntry.getKey())) {
							unitsToDelete.add(u);
						}
					}
					location.getUnits().removeAll(unitsToDelete);
				} else {
					List<Unit> unitsToDelete = new ArrayList<Unit>();
					List<Version> versionsToDelete = new ArrayList<Version>();
					for (Unit u : location.getUnits()) {
						for (String versionStr : versions) {
							if (u.getName().equalsIgnoreCase(dbEntry.getKey())) {
								unitsToDelete.add(u);
								for (Version v : u.getVersions()) {
									if (versionStr
											.equalsIgnoreCase(v.getName())) {
										versionsToDelete.add(v);
									}
								}
							}
						}
					}
					location.getUnits().removeAll(unitsToDelete);
				}
			}
			saveLocation(location);
		}
	}
}
