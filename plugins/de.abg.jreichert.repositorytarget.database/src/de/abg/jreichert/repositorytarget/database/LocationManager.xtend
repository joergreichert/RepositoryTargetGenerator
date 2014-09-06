package de.abg.jreichert.repositorytarget.database

import de.abg.jreichert.repositorytarget.activeannotations.CustomJpaHibFindByConditionAccessImpl
import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
import java.util.ArrayList
import java.util.Date
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Map.Entry
import java.util.Set
import java.util.SortedMap
import java.util.SortedSet
import java.util.TreeMap
import java.util.TreeSet
import org.hibernate.Transaction
import org.hibernate.criterion.Restrictions
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder

class LocationManager {

	def getById(Long id) {
		val session = SessionManager::currentSession
		session.get(Location, id) as Location
	}

	def saveLocation(Location location) {
		val session = SessionManager::currentSession
		var Transaction tx = null
		var Object result = null
		try {
			tx = session.beginTransaction
			val id = session.save(location)
			tx.commit
			result = getById((id as Long))
		} catch (Exception e) {
			if(tx !== null) tx.rollback
			throw new RuntimeException(e);
		} 
		(result as Location)
	}

	def getByURL(String url) {
		val session = SessionManager::currentSession
		val criteria = session.createCriteria(Location).add(Restrictions::eq('url', url))
		criteria.uniqueResult as Location
	}

	def deleteLocation(Location location) {
		val session = SessionManager::currentSession
		var Transaction tx = null
		try {
			tx = session.beginTransaction
			session.delete(location)
			tx.commit
		} catch (Exception e) {
			if(tx !== null) tx.rollback
			throw new RuntimeException(e);
		}
	}

	def clearAll() {
		val session = SessionManager::currentSession
		var Transaction tx = null
		try {
			tx = session.beginTransaction
			val criteria = session.createCriteria(Location)
			val locations = toLocationList(criteria.list)
			locations.forEach[session.delete(it)]
			tx.commit
		} catch (Exception e) {
			if(tx !== null) tx.rollback
			throw new RuntimeException(e);
		}
	}

	private def toLocationList(List<?> result) {
		result.filter(Location).toSet
	}

	def getTimestamps() {
		getTimestamps(new HashMap<String, Long>())
	}

	@LogExecutionTime def Map<String, Long> getTimestamps(Map<String, Long> timestampsToFilter) {
		val timestamps = new HashMap<String, Long>()
		val session = SessionManager::currentSession
		val findByCondition = new CustomJpaHibFindByConditionAccessImpl(Location, session)
		var conditionalCriteriaRoot = ConditionalCriteriaBuilder.criteriaFor(Location)
		if (timestampsToFilter.size > 0) {
			conditionalCriteriaRoot = conditionalCriteriaRoot.withProperty(LocationLiterals.url()).in(timestampsToFilter.keySet)
			findByCondition.addCondition(conditionalCriteriaRoot.buildSingle())
		}
		findByCondition.performExecute
		val result = toLocationList(findByCondition.getResult())
		result.forEach[timestamps.put(url, Long::valueOf(timestamp))]
		timestamps
	}

	// TODO: find a time and memory saving alternative!
	@LogExecutionTime def Map<String, SortedMap<String, SortedSet<String>>> getUrlToIdToVersions(Map<String, Long> timestampsToFilter) {
		val urlToIdToVersions = new TreeMap<String, SortedMap<String, SortedSet<String>>>()
		val locationToTimestamp = getTimestamps(timestampsToFilter)
		for (entry : locationToTimestamp.entrySet) {
			val location = getByURL(entry.key)
			val idToVersions = urlToIdToVersions.get(entry.key) ?: new TreeMap<String, SortedSet<String>>()
			for (unit : location.units) {
				val versions = idToVersions.get(unit.name) ?: new TreeSet<String>()
				versions += unit.versions.map[name]
				idToVersions.put(unit.name, versions)
			}
			urlToIdToVersions.put(entry.key, idToVersions)
		}
		urlToIdToVersions
	}

	def saveLocation(Long timestamp, String locationURL, SortedMap<String, SortedSet<String>> idToVersions) {
		val location = getOrInitLocation(timestamp, locationURL)
		if (location !== null) {
			fillUnits(idToVersions, location)
			val dbIdToVersions = fillDbIdToVersions(location)
			for (dbEntry : dbIdToVersions.entrySet) {
				val versions = idToVersions.get(dbEntry.key)
				if (versions === null) {
					removeUnits(location, dbEntry)
				} else {
					removeVersions(location, dbEntry, versions)
				}
			}
			saveLocation(location)
		}
	}

	private def removeVersions(Location location, Entry<String, SortedSet<String>> dbEntry,
		SortedSet<String> newVersions) {
		val unitsToDelete = new ArrayList<Unit>()
		for (u : location.units) {
			if (u.name.equalsIgnoreCase(dbEntry.key)) {
				val versionsToDelete = new ArrayList<Version>()
				var exists = false
				val versionsIt = u.versions.iterator
				while (versionsIt.hasNext && !exists) {
					val v = versionsIt.next
					exists = newVersions.exists[v.name.equalsIgnoreCase(it)]
					if (!exists) {
						versionsToDelete.add(v)
					}
				}
				u.versions.removeAll(versionsToDelete)
				if (u.versions.size == 0) {
					unitsToDelete.add(u)
				}
			}
		}
		location.units.removeAll(unitsToDelete)
	}

	private def removeUnits(Location location, Entry<String, SortedSet<String>> dbEntry) {
		val unitsToDelete = new ArrayList<Unit>()
		unitsToDelete += location.units.filter[name.equalsIgnoreCase(dbEntry.key)]
		location.units.removeAll(unitsToDelete)
	}

	private def fillUnits(SortedMap<String, SortedSet<String>> idToVersions, Location location) {
		val nameToUnit = fillNameToUnit(location)
		for (entry : idToVersions.entrySet) {
			val unit = nameToUnit.get(entry.key) ?: new Unit() => [
				it.location = location
				name = entry.key
				location.units += it
			]
			unit.fillVersions(entry)
		}
	}

	private def fillVersions(Unit unit, Entry<String, SortedSet<String>> entry) {
		for (versionStr : entry.value) {
			if (findVersion(unit.versions, versionStr) === null) {
				unit.versions.add(new Version() => [
					name = versionStr
					it.unit = unit
				])
			}
		}
	}

	private def findVersion(Set<Version> versions, String versionStr) {
		versions.findFirst[versionStr.equalsIgnoreCase(name)]
	}

	private def fillNameToUnit(Location location) {
		location.units.toMap[name]
	}

	private def fillDbIdToVersions(Location location) {
		val dbIdToVersions = new TreeMap<String, SortedSet<String>>()
		for (unit : location.units) {
			val versions = dbIdToVersions.get(unit.name) ?: new TreeSet<String>()
			unit.versions.forEach[versions.add(name)]
			dbIdToVersions.put(unit.name, versions)
		}
		dbIdToVersions
	}

	private def getOrInitLocation(Long timestamp, String locationURL) {
		val location = getByURL(locationURL) ?: new Location() => [
			url = locationURL
		]
		setTimestamp(timestamp, location)
		location
	}

	private def setTimestamp(Long timestamp, Location location) {
		if (timestamp === null) {
			location.timestamp = String::valueOf(new Date().time)
		} else {
			location.timestamp = String::valueOf(timestamp)
		}
	}

	def saveCompositeLocation(String parentLocationStr, Long parentLocationTimestamp,
		Set<String> aggregatedLocationStrs) {
		val parentLocation = getOrInitLocation(parentLocationTimestamp, parentLocationStr)
		if (parentLocation !== null) {
			for (aggregatedLocationStr : aggregatedLocationStrs) {
				val aggregatedLocation = getByURL(aggregatedLocationStr)
				if (aggregatedLocation !== null) {
					aggregatedLocation.parentLocation = parentLocation
					parentLocation.aggregatedLocations.add(aggregatedLocation)
				}
			}
			saveLocation(parentLocation)
		}
	}
	
	def saveVersion(String uri, String id, String version) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}
