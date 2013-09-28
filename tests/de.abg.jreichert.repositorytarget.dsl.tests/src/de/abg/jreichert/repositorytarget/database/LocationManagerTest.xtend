package de.abg.jreichert.repositorytarget.database

import java.util.SortedSet
import org.junit.After
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class LocationManagerTest {
	private extension LocationManager locationManager
	
	@Before def void setUp() {
		locationManager = new LocationManager
		locationManager.clearAll
		SessionManager.closeSession;	
	}
	
	@Test def void testGetById() {
		val locationTestdata = new LocationTestdata(expectedSimpleLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
		val location = getById(1L)
		assertNotNull(location)
		assertEquals(expectedSimpleLocation.toString, location.toString)
	}
	
	@Test def void testGetById__WhenEmptyDatabase() {
		val result = getById(1L)
		assertNull(result)
	}	

	@Test(expected=IllegalArgumentException) 
	def void testGetById__WhenIdNull() {
		getById(null)
	}	
	
	@Test def void testGetByURL() {
		val locationTestdata = new LocationTestdata(expectedSimpleLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
		val location = getByURL(locationTestdata.locationURL)
		assertNotNull(location)
		assertEquals(expectedSimpleLocation.toString, location.toString)
	}
	
	@Test def void testDeleteLocation() {
		val locationTestdata = new LocationTestdata(expectedSimpleLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
		val location = getById(1L)
		assertNotNull(location)
		deleteLocation(location)
		val location2 = getById(1L)
		assertNull(location2)
	}
	
	@Test def void testClearAll() {
		val expectedLocation = expectedComplexLocation
		expectedLocation.aggregatedLocations.forEach[executeSaveLocation]
		executeSaveLocation(expectedSimpleLocation)
		clearAll
		val timestamps = getTimestamps
		assertEquals(0, timestamps.size)
	}
	
	@Test def void testGetTimestamps() {
		val locationTestdata = new LocationTestdata(expectedSimpleLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
		val location = getById(1L)
		assertNotNull(location)
		val timestamps = getTimestamps
		assertEquals(1, timestamps.size)
		assertEquals(locationTestdata.locationURL, timestamps.keySet.head)
		assertEquals(locationTestdata.timestamp, timestamps.values.head)
	}
	
	@Test def void testGetTimestampsWithTimestampsToFilter() {
		val expectedLocation = expectedComplexLocation
		expectedLocation.aggregatedLocations.forEach[executeSaveLocation]
		executeSaveLocation(expectedSimpleLocation)
		val timestampsToFilter = newHashMap(
			"file://testdata/updatesite/" -> 1366902476295L,
			"file://testdata/updatesite/complex/cdefgh" -> 1367932476293L,
			"file://testdata/updatesite/doesnotexist" -> 1367932476293L,
			"file://testdata/updatesite/complex/ijklmn" -> 1367932476293L
		)
		val filteredTimestamps = getTimestamps(timestampsToFilter)
		assertEquals(3, filteredTimestamps.size)
		assertTrue(filteredTimestamps.keySet.contains("file://testdata/updatesite/complex/ijklmn"))
		assertTrue(filteredTimestamps.keySet.contains("file://testdata/updatesite/complex/cdefgh"))
		assertTrue(filteredTimestamps.keySet.contains("file://testdata/updatesite/"))
	}
	
	@Test def void testGetUrlToIdToVersions() {
	}
	
	@Test def void testSaveLocation__WhenSimpleLocation() {
		executeSaveLocationTest(expectedSimpleLocation)
	}

	@Test def void testSave__WhenSimpleLocationWithUpdate() {
		executeSaveLocationTest(expectedSimpleLocation)
		executeSaveLocationTest(expectedSimpleLocationUpdateUnitVersion)
		executeSaveLocationTest(expectedSimpleLocationUpdateUnit)
		executeSaveLocationTest(expectedSimpleLocationUpdateLocation)
	}
	
	def private expectedSimpleLocation() {
		new Location() => [
			id = 1L
			timestamp = "1366902476295"
			url =  "file://testdata/updatesite/"
			parentLocation = null
			aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]
			units = newTreeSet(
				[a,b|a.name.compareTo(b.name)],
				new Unit(it) => [
					id = 1L
					name = "de.abg.jreichert.repositorytarget.feature"
					versions = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Version(it) => [
							id = 1L
							name = "0.1.0.201304242052"
						]	
					)
				]
			)
		]
	}
	
	def private expectedSimpleLocationUpdateUnitVersion() {
		new Location() => [
			id = 1L
			timestamp = "1366902476296"
			url =  "file://testdata/updatesite/"
			parentLocation = null
			aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]
			units = newTreeSet(
				[a,b|a.name.compareTo(b.name)],
				new Unit(it) => [
					id = 1L
					name = "de.abg.jreichert.repositorytarget.feature"
					versions = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Version(it) => [
							id = 2L
							name = "0.1.1.201304252052"
						]	
					)
				]
			)
		]
	}	
	
	def private expectedSimpleLocationUpdateUnit() {
		new Location() => [
			id = 1L
			timestamp = "1366902476297"
			url =  "file://testdata/updatesite/"
			parentLocation = null
			aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]
			units = newTreeSet(
				[a,b|a.name.compareTo(b.name)],
				new Unit(it) => [
					id = 2L
					name = "de.abg.jreichert.repositorytarget2.feature"
					versions = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Version(it) => [
							id = 3L
							name = "0.1.1.201304252052"
						]	
					)
				]
			)
		]
	}	
	
	def private expectedSimpleLocationUpdateLocation() {
		new Location() => [
			id = 2L
			timestamp = "1366902476297"
			url =  "file://testdata/updatesite2/"
			parentLocation = null
			aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]
			units = newTreeSet(
				[a,b|a.name.compareTo(b.name)],
				new Unit(it) => [
					id = 3L
					name = "de.abg.jreichert.repositorytarget2.feature"
					versions = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Version(it) => [
							id = 4L
							name = "0.1.1.201304252052"
						]	
					)
				]
			)
		]
	}	
	
	private def void executeSaveLocationTest(Location expectedLocation) {
		val locationTestdata = new LocationTestdata(expectedLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
		val location = getByURL(locationTestdata.locationURL)
		assertNotNull(location)
		assertEquals(expectedLocation.toString, location.toString)
	}
	
	private def void executeSaveLocation(Location expectedLocation) {
		val locationTestdata = new LocationTestdata(expectedLocation)
		saveLocation(locationTestdata.timestamp, locationTestdata.locationURL, locationTestdata.idToVersions)
	}	
	
	@Test def void testSaveCompositeLocation() {
		val expectedLocation = expectedComplexLocation
		expectedLocation.aggregatedLocations.forEach[it.parentLocation=null; executeSaveLocationTest]
		val locationTestdata = new LocationTestdata(expectedLocation)
		saveCompositeLocation(locationTestdata.locationURL, locationTestdata.timestamp, locationTestdata.aggregatedLocations)
		val location = getByURL(locationTestdata.locationURL)
		assertNotNull(location)
		val expectedLocationWithParentSet = expectedComplexLocation
		assertEquals(expectedLocationWithParentSet.toString, location.toString)
	}	
	
	def private expectedComplexLocation() {
		new Location() => [
			id = 3L
			timestamp = "1367912476285"
			url =  "file://testdata/updatesite/complex"
			parentLocation = null
			aggregatedLocations = newTreeSet(
				[a,b|a.url.compareTo(b.url)],
				new Location(it) => [
					id = 1L
					timestamp = "1367932476293"
					url =  "file://testdata/updatesite/complex/cdefgh"
					units = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Unit(it) => [
							id = 1L
							name = "cde.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									id = 1L
									name = "0.0.1.201304242052"
								],
								new Version(it) => [
									id = 2L
									name = "0.0.2.201305242052"
								]	
							)
						],
						new Unit(it) => [
							id = 2L
							name = "fgh.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									id = 3L
									name = "0.0.3.201306242052"
								],
								new Version(it) => [
									id = 4L
									name = "0.0.4.201307242052"
								]	
							)
						]
					)
				],
				new Location(it) => [
					id = 2L
					timestamp = "1367922476291"
					url =  "file://testdata/updatesite/complex/ijklmn"
					units = newTreeSet(
						[a,b|a.name.compareTo(b.name)],
						new Unit(it) => [
							id = 3L
							name = "ijk.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									id = 5L
									name = "0.0.5.201304242052"
								],
								new Version(it) => [
									id = 6L
									name = "0.0.6.201305242052"
								]	
							)
						],
						new Unit(it) => [
							id = 4L
							name = "lmn.feature"
							versions = newTreeSet(
								[a,b|a.name.compareTo(b.name)],
								new Version(it) => [
									id = 7L
									name = "0.0.7.201306242052"
								],
								new Version(it) => [
									id = 8L
									name = "0.0.8.201307242052"
								]	
							)
						]
					)
				]
			)
		]
	}	
	
	@After
	def after() {
		SessionManager.closeSession;
	}
	
	def afterClass() {
		SessionManager.shutdown;
	}
}

@Data
class LocationTestdata {
	@Property 
	private Location location
	
	def getTimestamp() {
		Long.valueOf(location.timestamp)
	}
	
	def getLocationURL() {
		location.url
	}
	
	def getIdToVersions() {
		newTreeMap([a,b|a.compareTo(b)], location.units.map[
			name -> <String>newTreeSet(
				[c,d|c.compareTo(d)], versions.map[name]
			) as SortedSet<String>
		])
	}
	
	def getAggregatedLocations() {
		<String>newTreeSet([a,b|a.compareTo(b)], location.aggregatedLocations.map[url])
	}
}
