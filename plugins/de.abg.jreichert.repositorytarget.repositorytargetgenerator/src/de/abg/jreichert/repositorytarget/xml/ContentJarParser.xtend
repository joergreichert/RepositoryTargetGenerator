package de.abg.jreichert.repositorytarget.xml

import java.io.File
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.JarURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.List
import java.util.Map

class ContentJarParser extends ContentParser {
	private val Map<String, Long> locationToTimestamp;
	private val List<String> contents;
	
	new(String url) {
		val Map<String, Long> localMap = newHashMap
		locationToTimestamp = getTimestamps(url, localMap)
		val List<String> localContents = newArrayList
		contents = getContents(url, localContents)
	}

	def Map<String, Long> getTimestamps(String url, Map<String, Long> localMap) {
		var contentFileName = "content"
		var lastModified = lastModifiedXmlUrl(url.toUrl, contentFileName)
		if(lastModified > -1) {
			localMap.put(url, lastModified)
		} else {
			lastModified = lastModifiedJarUrl(url.toUrl, contentFileName)
			if(lastModified > -1) {
				localMap.put(url, lastModified)
			} else {
				contentFileName = "compositeContent"
				lastModified = lastModifiedXmlUrl(url.toUrl, contentFileName)
				if(lastModified > -1) {
					localMap.put(url, lastModified)
				} else {
					lastModified = lastModifiedJarUrl(url.toUrl, contentFileName)
					localMap.put(url, lastModified)
				}
			}
		}
 		localMap
	}
	
	def Map<String, Long> getTimestamps(String url, Map<String, Long> localMap, String contentFileName) {
		
	}

	def List<String> getContents(String url, List<String> localContents) {
		var contentFileName = "content"
		if (existsXmlUrl(url.toUrl, contentFileName)) {
 			localContents.add(getXmlContent(url, contentFileName))
		} else if (existsJarUrl(url.toUrl, contentFileName)) {
 			localContents.add(getJarContent(url, contentFileName))
		} else {
			contentFileName = "compositeContent"
			if (existsXmlUrl(url.toUrl, contentFileName)) {
 				val compositeContent = getXmlContent(url, contentFileName)
 				parse(compositeContent, url, localContents)
			} else if (existsJarUrl(url.toUrl, contentFileName)) {
 				val compositeContent = getJarContent(url, contentFileName)
 				parse(compositeContent, url, localContents)
			} else {
				throw new IllegalArgumentException("For " + url + " no content.xml could be found.")				
			}
		}
 		localContents
	}
	
	def parse(String compositeContent, String parentLocation, List<String> localContents) {
		val locations = parseLocations(compositeContent, parentLocation)
 		for(location : locations) {
 			getContents(location, localContents)
 		}
	}

	def getJarContent(String url, String contentFileName) {
		val jarUrl = new URL("jar:" + url.toUrl + contentFileName + ".jar!/")
    	val connection = jarUrl.openConnection() as JarURLConnection
	    val jarFile = connection.getJarFile		
 		var entry = jarFile.getJarEntry(contentFileName + ".xml")
		val input = jarFile.getInputStream(entry)
		val content = getContent(input)
		jarFile.close
		content
	}

	def getXmlContent(String url, String contentFileName) {
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
	
	def lastModifiedXmlUrl(String url, String contentFileName) {
		lastModified(url, contentFileName, "xml")
	}

	def lastModifiedJarUrl(String url, String contentFileName) {
		lastModified(url, contentFileName, "jar")
	}
	
	def existsXmlUrl(String url, String contentFileName) {
		existsUrl(url, contentFileName, "xml")
	}

	def existsJarUrl(String url, String contentFileName) {
		existsUrl(url, contentFileName, "jar")
	}	

	def boolean existsUrl(String url, String contentFileName, String fileExt) {
		url.lastModified(contentFileName, fileExt) > -1
	}

	def long lastModified(String url, String contentFileName, String fileExt) {
		val contentUrl = new URL(url.toUrl + contentFileName + "." + fileExt);
		if(contentUrl.protocol == "file") {
			val file = new File(contentUrl.toURI.path).absoluteFile
			if(file.exists && file.file) file.lastModified 
			else {
				val newFile = new File(System.getProperty("user.dir") + "/" + contentUrl.toString.replace("file://", ""))
				if(newFile.exists && newFile.file) newFile.lastModified else -1
			} 
		} else {
			if(url.contains("file://")) {
				val file = new File(contentUrl.toURI.path.replace("jar:", "")).absoluteFile
				if(file.exists && file.file) file.lastModified else -1 
			} else {
				val conn = contentUrl.openConnection
				if(conn.existsUrl) conn.lastModified else -1
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
	
	def toUrl(String url) {
		if(url.endsWith("/")) url else url + "/"
	}
	
	def parseVersionForId(String id, List<(String) => boolean> filters) {
		getParsedContent(id, filters).version
	}
	
	def parseVersionsForId(String id, List<(String) => boolean> filters) {
		getParsedContent(id, filters).versions
	}
	
	def private getParsedContent(String id, List<(String) => boolean> filters) {
      	val contentHandler = new ContentXmlHandler(id, filters);
      	for(content : contents) {
	      	parse(content, contentHandler)
      	}
		contentHandler
	}
	
	def parseLocations(String content, String location) {
      	val contentHandler = new CompositeContentXmlHandler(location);
      	parse(content, contentHandler)
		contentHandler.locations
	}
	
	def getContents() {
		contents
	}
}