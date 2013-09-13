package de.abg.jreichert.repositorytarget.xml

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.ListMultimap
import de.abg.jreichert.repositorytarget.database.LocationManager
import de.abg.jreichert.repositorytarget.database.SessionManager
import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.JarURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.List
import java.util.Map

class ContentJarParser extends ContentParser {
	private var Map<String, Long> locationToTimestamp = null;
	
	def private Map<String, Long> getInternalTimestamps(String urlStr) {
		val Map<String, Long> localMap = newHashMap
		var contentFileName = "content"
		val url = urlStr.toUrl
		var lastModified = lastModifiedXmlUrl(url, contentFileName)
		if(lastModified.longValue > -1) {
			localMap.put(urlStr, lastModified)
		} else {
			lastModified = lastModifiedJarUrl(url, contentFileName)
			if(lastModified.longValue > -1) {
				localMap.put(urlStr, lastModified)
			} else {
				contentFileName = "compositeContent"
				lastModified = lastModifiedXmlUrl(url, contentFileName)
				if(lastModified.longValue > -1) {
					localMap.put(urlStr, lastModified)
	 				val compositeContent = getXmlContent(url, contentFileName)
	 				localMap.putAll(parseTimestamps(compositeContent, url))
				} else {
					lastModified = lastModifiedJarUrl(url, contentFileName)
					localMap.put(urlStr, lastModified)
	 				val compositeContent = getJarContent(url, contentFileName)
	 				localMap.putAll(parseTimestamps(compositeContent, url))
				}
			}
		}
 		localMap
	}
	
	def private ListMultimap<String, String> getInternalContents(String urlStr) {
		val ListMultimap<String, String> localContents = ArrayListMultimap.create
		var contentFileName = "content"
		val url = urlStr.toUrl
		if (existsXmlUrl(url, contentFileName)) {
 			localContents.put(urlStr, getXmlContent(url, contentFileName))
		} else if (existsJarUrl(url, contentFileName)) {
 			localContents.put(urlStr, getJarContent(url, contentFileName))
		} else {
			contentFileName = "compositeContent"
			if (existsXmlUrl(url, contentFileName)) {
 				val compositeContent = getXmlContent(url, contentFileName)
 				localContents.putAll(parseContents(compositeContent, url))
			} else if (existsJarUrl(url, contentFileName)) {
 				val compositeContent = getJarContent(url, contentFileName)
 				localContents.putAll(parseContents(compositeContent, url))
			} else {
				throw new IllegalArgumentException("For " + url + " no content.xml could be found.")				
			}
		}
 		localContents
	}
	
	def private parseContents(String compositeContent, String parentLocation) {
		val ListMultimap<String, String> localContents = ArrayListMultimap.create
		val locations = parseLocations(compositeContent, parentLocation)
 		for(location : locations) {
 			localContents.putAll(getInternalContents(location))
 		}
 		localContents
	}
	
	def private parseTimestamps(String compositeContent, String parentLocation) {
		val Map<String, Long> map = newHashMap
		val locations = parseLocations(compositeContent, parentLocation)
 		for(location : locations) {
 			map.putAll(getTimestamps(location))
 		}
 		map
	}	

	def private getJarContent(String url, String contentFileName) {
		val jarUrl = new URL("jar:" + url.toUrl + contentFileName + ".jar!/")
    	val connection = jarUrl.openConnection() as JarURLConnection
	    val jarFile = connection.getJarFile		
 		var entry = jarFile.getJarEntry(contentFileName + ".xml")
		val input = jarFile.getInputStream(entry)
		val content = getContent(input)
		jarFile.close
		content
	}

	def private getXmlContent(String url, String contentFileName) {
		val xmlUrl = new URL(url.toUrl + contentFileName + ".xml")
		if(xmlUrl.protocol == "file") {
			val file = new File(xmlUrl.toURI.path).absoluteFile
			if(file.exists && file.file) getContent(new FileInputStream(file)) 
			else {
				val newFile = new File(System.getProperty("user.dir") + "/" + xmlUrl.toString.replace("file://", ""))
				if(newFile.exists && newFile.file) getContent(new FileInputStream(newFile)) 
			} 
		} else {
			if(url.contains("file://")) {
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
		val contentUrl = new URL(url.toUrl + contentFileName + "." + fileExt);
		if(contentUrl.protocol == "file") {
			val file = new File(contentUrl.toURI.path).absoluteFile
			if(file.exists && file.file) Long.valueOf(file.lastModified) 
			else {
				val newFile = new File(System.getProperty("user.dir") + "/" + contentUrl.toString.replace("file://", ""))
				if(newFile.exists && newFile.file) Long.valueOf(newFile.lastModified) else Long.valueOf(-1L)
			} 
		} else {
			if(url.contains("file://")) {
				val file = new File(contentUrl.toURI.path.replace("jar:", "")).absoluteFile
				if(file.exists && file.file) Long.valueOf(file.lastModified) else Long.valueOf(-1L) 
			} else {
				val conn = contentUrl.openConnection
				if(conn.existsUrl) Long.valueOf(conn.lastModified) else Long.valueOf(-1L)
			}
		}
	}
	
	def private dispatch existsUrl(HttpURLConnection contentCon) {
 		contentCon.requestMethod = "HEAD";
 		val code = contentCon.responseCode 
		code == HttpURLConnection::HTTP_OK
	}
	
	def private dispatch existsUrl(URLConnection contentCon) {
 		true
	}
	
	def private toUrl(String url) {
		if(url.endsWith("/")) url else url + "/"
	}
	
	def parseVersionForId(String url, String id, List<(String) => boolean> filters) {
		getParsedContent(url, id, filters).getVersion(url)
	}
	
	def parseVersionsForId(String url, String id, List<(String) => boolean> filters) {
		getParsedContent(url, id, filters).getVersions(url)
	}
	
	def private getParsedContent(String url, String id, List<(String) => boolean> filters) {
      	val contentHandler = new ContentXmlHandler(url, id, filters);
      	for(Map.Entry<String, String> entry : getContents(url, contentHandler).entries) {
	      	parse(entry.value, contentHandler)
      	}
		contentHandler
	}
	
	def private parseLocations(String content, String location) {
      	val contentHandler = new CompositeContentXmlHandler(location);
      	parse(content, contentHandler)
		contentHandler.locations
	}
	
	def ListMultimap<String, String> getContents(String url, ContentXmlHandler contentHandler) {
		val ListMultimap<String, String> contents = ArrayListMultimap.create
      	val urlToTimestamps = getTimestamps(url)
      	val locationManager = new LocationManager();
      	val urlToDbTimestamps = locationManager.getTimestamps(urlToTimestamps)
      	val urlToDbIdToVersions = locationManager.getUrlToIdToVersions(urlToTimestamps)
      	contentHandler.urlToIdToVersion.putAll(urlToDbIdToVersions)
      	for(innerUrl : urlToTimestamps.entrySet.filter(entry|
      		checkDbTimestampOlderThanUrlTimestamp(urlToDbTimestamps.get(entry.key), entry.value)
      	).map[key]) {
			contents.putAll(getInternalContents(innerUrl))
		}
		contents
	}
	
	def private boolean checkDbTimestampOlderThanUrlTimestamp(Long dbTimestamp, Long remoteTimestamp) {
		if(dbTimestamp == null) {
			return true;
		}
		if(remoteTimestamp == null) {
			return false;
		}
		return dbTimestamp < remoteTimestamp
	}
	
	def private Map<String, Long> getTimestamps(String url) {
		if(locationToTimestamp == null) {
			locationToTimestamp = getInternalTimestamps(url)
		}
		locationToTimestamp
	}	
	
	def save(String url, ContentXmlHandler contentHandler) {
      	val urlToTimestamps = getTimestamps(url)
      	val locationManager = new LocationManager();
      	for(entry : contentHandler.urlToIdToVersion.entrySet) {
			locationManager.save(urlToTimestamps.get(entry.key), entry.key, entry.value) 
      	}
      	if(urlToTimestamps.get(url) != null) {
			locationManager.save(url, urlToTimestamps.get(url), urlToTimestamps.keySet) 
      	}
	}
	
	def beginSession() {
		SessionManager.sessionFactory.openStatelessSession
	}
	
	def endSession() {
		SessionManager.closeSession;
	}
}