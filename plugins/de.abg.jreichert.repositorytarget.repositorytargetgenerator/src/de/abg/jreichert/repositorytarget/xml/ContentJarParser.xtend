package de.abg.jreichert.repositorytarget.xml

import de.abg.jreichert.repositorytarget.database.LocationManager
import de.abg.jreichert.repositorytarget.database.SessionManager
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.JarURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.List
import java.util.Map

class ContentJarParser extends ContentParser {
	private var Map<String, Long> locationToTimestamp = null;

	def private Map<String, Long> getInternalTimestamps(String urlStr, Map<String, String> localContents, ContentXmlHandler contentHandler) {
		val Map<String, Long> localMap = newHashMap
		var contentFileName = "content"
		val url = urlStr.toUrl
		var lastModified = lastModifiedXmlUrl(url, contentFileName)
		if (lastModified.longValue > -1) {
			localMap.put(urlStr, lastModified)
		} else {
			lastModified = lastModifiedJarUrl(url, contentFileName)
			if (lastModified.longValue > -1) {
				localMap.put(urlStr, lastModified)
			} else {
				contentFileName = "compositeContent"
				lastModified = lastModifiedXmlUrl(url, contentFileName)
				if (lastModified.longValue > -1) {

					// ignore time stamp of composite update site
					// localMap.put(urlStr, lastModified)
					val compositeContent = getXmlContent(url, contentFileName)

					//localContents.put(urlStr, compositeContent)
					localMap.putAll(parseTimestamps(compositeContent, url, localContents, contentHandler))
				} else {

					// ignore time stamp of composite update site
					// lastModified = lastModifiedJarUrl(url, contentFileName)
					localMap.put(urlStr, lastModified)
					val compositeContent = getJarContent(url, contentFileName)

					//localContents.put(urlStr, compositeContent)
					localMap.putAll(parseTimestamps(compositeContent, url, localContents, contentHandler))
				}
			}
		}
		localMap
	}

	def private void fillInternalContents(String urlStr, Map<String, String> localContents, ContentXmlHandler contentHandler) {
		var contentFileName = "content"
		val url = urlStr.toUrl
		if (existsXmlUrl(url, contentFileName)) {
			if (!localContents.containsKey(urlStr)) {
				localContents.put(urlStr, getXmlContent(url, contentFileName))
			}
		} else if (existsJarUrl(url, contentFileName)) {
			if (!localContents.containsKey(urlStr)) {
				localContents.put(urlStr, getJarContent(url, contentFileName))
			}
		} else {
			contentFileName = "compositeContent"
			if (existsXmlUrl(url, contentFileName)) {
				val compositeContent = getXmlContent(url, contentFileName)
				parseContents(compositeContent, url, localContents, contentHandler)
			} else if (existsJarUrl(url, contentFileName)) {
				val compositeContent = getJarContent(url, contentFileName)
				parseContents(compositeContent, url, localContents, contentHandler)
			} else {
				throw new IllegalArgumentException("For " + url + " no content.xml could be found.")
			}
		}
	}

	def private void parseContents(String compositeContent, String parentLocation, Map<String, String> localContents, ContentXmlHandler contentHandler) {
		val locations = parseLocations(compositeContent, parentLocation, contentHandler)
		for (location : locations.toSet) {
			if (!localContents.containsKey(location)) {
				fillInternalContents(location, localContents, contentHandler)
			}
		}
	}

	def private Map<String, Long> parseTimestamps(String compositeContent, String parentLocation,
		Map<String, String> localContents, ContentXmlHandler contentHandler) {
		val Map<String, Long> map = newHashMap
		val locations = parseLocations(compositeContent, parentLocation, contentHandler)
		for (location : locations) {
			map.putAll(getTimestamps(location, localContents, contentHandler))
		}
		map
	}

	def private String getJarContent(String url, String contentFileName) {
		try {
			val jarUrl = new URL("jar:" + url.toUrl + contentFileName + ".jar!/")
			val connection = jarUrl.openConnection() as JarURLConnection
			val jarFile = connection.getJarFile
			var entry = jarFile.getJarEntry(contentFileName + ".xml")
			val input = jarFile.getInputStream(entry)
			val content = getContent(input)
			jarFile.close
			content
		} catch (IOException exc) {
			throw new RuntimeException(exc)
		}
	}

	def private String getXmlContent(String url, String contentFileName) {
		try {
			val xmlUrl = new URL(url.toUrl + contentFileName + ".xml")
			if (xmlUrl.protocol == "file") {
				val file = new File(xmlUrl.toURI.path).absoluteFile
				if (file.exists && file.file)
					getContent(new FileInputStream(file))
				else {
					val newFile = new File(System.getProperty("user.dir") + "/" + xmlUrl.toString.replace("file://", ""))
					if (newFile.exists && newFile.file) getContent(new FileInputStream(newFile))
				}
			} else {
				if (url.contains("file://")) {
					val file = new File(xmlUrl.toURI.path.replace("jar:", ""))
					val newXmlUrl = new URL("jar:" + file.absolutePath)
					val connection = newXmlUrl.openConnection
					val input = connection.inputStream
					getContent(input)
				} else {
					val connection = xmlUrl.openConnection
					val input = connection.inputStream
					getContent(input)
				}
			}
		} catch (Exception exc) {
			throw new RuntimeException(exc)
		}
	}

	def private lastModifiedXmlUrl(String url, String contentFileName) {
		lastModified(url, contentFileName, "xml")
	}

	def private lastModifiedJarUrl(String url, String contentFileName) {
		lastModified(url, contentFileName, "jar")
	}

	def private existsXmlUrl(String url, String contentFileName) {
		existsUrl(url, contentFileName, "xml")
	}

	def private existsJarUrl(String url, String contentFileName) {
		existsUrl(url, contentFileName, "jar")
	}

	def private boolean existsUrl(String url, String contentFileName, String fileExt) {
		url.lastModified(contentFileName, fileExt).longValue > -1
	}

	def private Long lastModified(String url, String contentFileName, String fileExt) {
		try {
			val contentUrl = new URL(url.toUrl + contentFileName + "." + fileExt);
			if (contentUrl.protocol == "file") {
				val file = new File(contentUrl.toURI.path).absoluteFile
				if (file.exists && file.file)
					Long.valueOf(file.lastModified)
				else {
					val newFile = new File(System.getProperty("user.dir") + "/" + contentUrl.toString.replace("file://", ""))
					if (newFile.exists && newFile.file) Long.valueOf(newFile.lastModified) else Long.valueOf(-1L)
				}
			} else {
				if (url.contains("file://")) {
					val file = new File(contentUrl.toURI.path.replace("jar:", "")).absoluteFile
					if (file.exists && file.file) Long.valueOf(file.lastModified) else Long.valueOf(-1L)
				} else {
					val conn = contentUrl.openConnection
					if (conn.existsUrl) Long.valueOf(conn.lastModified) else Long.valueOf(-1L)
				}
			}
		} catch (Exception exc) {
			throw new RuntimeException(exc)
		}
	}

	def private dispatch existsUrl(HttpURLConnection contentCon) {
		try {
			contentCon.requestMethod = "HEAD"
			val code = contentCon.responseCode
			code == HttpURLConnection::HTTP_OK
		} catch (Exception exc) {
			throw new RuntimeException(exc)
		}
	}

	def private dispatch existsUrl(URLConnection contentCon) {
		true
	}

	def private toUrl(String url) {
		if (url.endsWith("/")) url else url + "/"
	}

	def parseVersionForId(String url, String id, List<(String)=>boolean> filters) {
		getParsedContent(url, id, filters).getVersion(url)
	}

	def parseVersionsForId(String url, String id, List<(String)=>boolean> filters) {
		getParsedContent(url, id, filters).getVersions(url)
	}

	def private ContentXmlHandler getParsedContent(String url, String id, List<(String)=>boolean> filters) {
		val contentHandler = new ContentXmlHandler(url, id, filters);
		for (Map.Entry<String, String> entry : getContents(url, contentHandler).entrySet) {
			parse(entry.value, contentHandler)
		}
		contentHandler
	}

	def private List<String> parseLocations(String content, String location, ContentXmlHandler contentXmlHandler) {
		val contentHandler = new CompositeContentXmlHandler(location);
		parse(content, contentHandler)
		contentHandler.locations.forEach[contentXmlHandler.locationToLocation.put(location, it)]
      contentHandler.locations
	}

	def Map<String, String> getContents(String url, ContentXmlHandler contentHandler) {
		val Map<String, String> localContents = newHashMap
		val urlToTimestamps = getTimestamps(url, localContents, contentHandler)
		val locationManager = new LocationManager();
		val urlToDbTimestamps = locationManager.getTimestamps(urlToTimestamps)
		val urlToDbIdToVersions = locationManager.getUrlToIdToVersions(urlToTimestamps)
		contentHandler.urlToIdToVersion.putAll(urlToDbIdToVersions)
		fillInnerContents(urlToTimestamps, urlToDbTimestamps, localContents, contentHandler)
		localContents
	}

	def void fillInnerContents(Map<String, Long> urlToTimestamps, Map<String, Long> urlToDbTimestamps,
		Map<String, String> localContents, ContentXmlHandler contentXmlHandler) {
		val innerUrls = getInnerUrls(urlToTimestamps, urlToDbTimestamps)
		fillUpdatedContents(innerUrls, localContents, contentXmlHandler)
	}

	def private void fillUpdatedContents(Iterable<String> innerUrls, Map<String, String> localContents, ContentXmlHandler contentXmlHandler) {
		for (innerUrl : innerUrls.toSet) {
			fillInternalContents(innerUrl, localContents, contentXmlHandler)
		}
	}

	def private Iterable<String> getInnerUrls(Map<String, Long> urlToTimestamps, Map<String, Long> urlToDbTimestamps) {
		urlToTimestamps.entrySet.filter(
			entry|checkDbTimestampOlderThanUrlTimestamp(entry.key, urlToDbTimestamps.get(entry.key), entry.value)
		).map[key]
	}

	def private boolean checkDbTimestampOlderThanUrlTimestamp(String url, Long dbTimestamp, Long remoteTimestamp) {
		if (dbTimestamp === null) {
			return true;
		}
		if (remoteTimestamp === null) {
			return false;
		}
		return dbTimestamp < remoteTimestamp
	}

	def private Map<String, Long> getTimestamps(String url, Map<String, String> localContents, ContentXmlHandler contentHandler) {
		if (locationToTimestamp === null || !locationToTimestamp.keySet.contains(url)) {
			locationToTimestamp = getInternalTimestamps(url, localContents, contentHandler)
		}
		locationToTimestamp
	}

	def void save(String url, ContentXmlHandler contentHandler) {
		val urlToTimestamps = getTimestamps(url, newHashMap, contentHandler)
		val locationManager = new LocationManager();
		for (entry : contentHandler.urlToIdToVersion.entrySet) {
			locationManager.saveLocation(urlToTimestamps.get(entry.key), entry.key, entry.value)
		}
		if (urlToTimestamps.get(url) !== null) {
			locationManager.saveCompositeLocation(url, urlToTimestamps.get(url), urlToTimestamps.keySet)
		}
	}

	def beginSession() {
		SessionManager.sessionFactory.openStatelessSession
	}

	def endSession() {
		SessionManager.closeSession;
	}
}
