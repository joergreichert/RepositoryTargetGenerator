package de.abg.jreichert.repositorytarget.database;

import java.util.ArrayList;
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
	
	public Object saveLocation(Location location) {
		Object result = null;
		Session session = SessionManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			result = session.save(location);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
		}
		return result;
	}
	
	public Location getById(Long id) {
		Object result = null;
		Session session = SessionManager.getSessionFactory().openSession();
		try {
			result = session.get(Location.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
		}
		return (Location) result;
	}	
	
	public Location getByURL(String url) {
		Object result = null;
		Session session = SessionManager.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Location.class).createCriteria("url", url);
			result = criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
		}
		return (Location) result;
	}	
	
	public void deleteLocation(Location location) {
		Session session = SessionManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(location);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
		}
	}
	
	public void clearAll() {
		Session session = SessionManager.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Location.class);
			List<Location> locations = toLocationList(criteria.list());
			Transaction tx = session.beginTransaction();
			for(Location location : locations) {
				session.delete(location);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
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
		Session session = SessionManager.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Location.class);
			criteria.add(Restrictions.in("_url", timestampsToFilter.keySet()));
			result = toLocationList(criteria.list());
			for(Location location : result) {
				timestamps.put(location.getUrl(), location.getTimestamp());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
//			SessionManager.shutdown();
		}
		return timestamps;
	}
	
	public SortedMap<String, SortedSet<String>> getIdToVersions(Map<String, Long> timestampsToFilter) {
		SortedMap<String, SortedSet<String>> idToVersions = new TreeMap<String, SortedSet<String>>();
		Map<String, Long> locationToTimestamp = getTimestamps(timestampsToFilter);
		Location location = null;
		SortedSet<String> versions = null;
		for(Entry<String, Long> entry : locationToTimestamp.entrySet()) {
			location = getByURL(entry.getKey());
			for(Unit unit : location.getUnits()) {
				versions = idToVersions.get(unit.getName());
				if(versions == null) {
					versions = new TreeSet<String>();
				}
				versions.add(unit.getVersion());
				idToVersions.put(unit.getName(), versions);
			}
		}
		return idToVersions;
	}

	public void save(Long timestamp, String locationURL, SortedMap<String,SortedSet<String>> idToVersions) {
		SortedSet<String> versions = null;
		Location location = getByURL(locationURL);
		if(location == null) {
			location = new Location();
			location.setTimestamp(timestamp);
			location.setUrl(locationURL);
			saveLocation(location);
			location = getByURL(locationURL);
		}
		SortedMap<String,SortedSet<String>> dbIdToVersions = new TreeMap<String,SortedSet<String>>();
		for(Unit unit : location.getUnits()) {
			versions = dbIdToVersions.get(unit.getName());
			if(versions == null) {
				versions = new TreeSet<String>();
			}
			versions.add(unit.getVersion());
			dbIdToVersions.put(unit.getName(), versions);
		}
		Map<String, Unit> nameToUnit = new HashMap<String, Unit>();
		for(Unit unit : location.getUnits()) {
			nameToUnit.put(unit.getName(), unit);
		}		
		Unit unit = null;
		for(Entry<String, SortedSet<String>> entry : idToVersions.entrySet()) {
			if(!nameToUnit.keySet().contains(entry.getKey())) {
				for(String version : entry.getValue()) {
					unit = new Unit();
					unit.setLocation(location);
					unit.setName(entry.getKey());
					unit.setVersion(version);
					location.getUnits().add(unit);
				}
			}
		}
		for(Entry<String, SortedSet<String>> dbEntry : dbIdToVersions.entrySet()) {
			versions = idToVersions.get(dbEntry.getKey());
			if(versions == null) {
				List<Unit> unitsToDelete = new ArrayList<Unit>();
				for(Unit u : location.getUnits()) {
					if(u.getName().equalsIgnoreCase(dbEntry.getKey())) {
						unitsToDelete.add(u);
					}
				}
				location.getUnits().removeAll(unitsToDelete);
			} else {
				List<Unit> unitsToDelete = new ArrayList<Unit>();
				for(Unit u : location.getUnits()) {
					for(String version : versions) {
						if(u.getName().equalsIgnoreCase(dbEntry.getKey()) && u.getVersion() != null && u.getVersion().equals(version)) {
							unitsToDelete.add(u);
						}
					}
				}
				location.getUnits().removeAll(unitsToDelete);
			}
		}
		saveLocation(location);
	}
}
