package de.abg.jreichert.repositorytarget.xml

import java.util.List
import java.util.Set
import java.util.SortedMap
import java.util.SortedSet
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class ContentXmlHandler extends DefaultHandler {

	private String id;
	private SortedMap<String, SortedMap<String, SortedSet<String>>> localUrlToIdToVersions = newTreeMap([s1, s2 | compareStrings(s1, s2)])
	private String version;
	private String url;
	private String expectedId;
	private List<(String) => boolean> filters;

	new(String url, String expectedId, List<(String) => boolean> filters) {
		this(url, filters)
		this.expectedId = expectedId
	}
	
	def setUrl(String url) {
		this.url = url
	}
	
	def getUrl() {
		url
	}
	
	def private compareStrings(String s1, String s2) {
		if(s1 != null) {
			if(s2 != null) {
				s1.compareTo(s2)
			} else {
				-1
			}
		} else if(s2 != null) {
			-1
		} else {
			0
		}
	}
	
	new(String url, String expectedId) {
		this(url)
		this.expectedId = expectedId
	}
	
	new(String url) {
		setUrl(url)
		filters = <(String) => boolean>newArrayList
	}
	
	new(String url, List<(String) => boolean> filters) {
		this(url)
		this.filters = filters
	}

	override characters(char[] ch, int start, int length)
			throws SAXException {
	}

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if(getUrl != null) {
			if (localName.equals("unit")) {
				id = atts.getValue("id")
				if(expectedId != null) {
					if(id != null && id.startsWith(expectedId)) {
						register(getUrl, atts)
					}
				} else {
					register(getUrl, atts)
				}
			}
		}
	}
	
	def getIdToVersions(String uri) {
		localUrlToIdToVersions.get(uri)
	}

	def addIdToVersions(String uri, SortedMap<String, SortedSet<String>> idToVersions) {
		localUrlToIdToVersions.put(uri, idToVersions)
	}
	
	def private register(String uri, Attributes atts) {
		version = atts.getValue("version")
		var idToVersions = getIdToVersions(uri) ?: <String, SortedSet<String>>newTreeMap[s1, s2 | compareStrings(s1, s2)]
		if(version != null && version.filter) {
			if(!idToVersions.containsKey(id)) {
				idToVersions.put(id, <String>newTreeSet([s1, s2 | compareStrings(s1, s2)]))
			}
			if(!version.nullOrEmpty) {
				idToVersions.get(id).add(version)
			}
			addIdToVersions(uri, idToVersions)
		}
	}
	
	def filter(String version) {
		var boolean passed = true
		for(filter : filters) {
			passed = passed && filter.apply(version)
		}
		passed
	}

	def SortedMap<String, SortedMap<String, SortedSet<String>>> getUrlToIdToVersion() {
		localUrlToIdToVersions		
	}
	
	def Set<String> getIds(String url) {
		getIdToVersions(url)?.keySet		
	}
	
	def SortedSet<String> getVersions(String url) {
		if(url != null && expectedId != null) url.getVersions(id) else createEmptySortedSet
	}
	
	def SortedSet<String> getVersions(String url, String id) {
		if(url != null && id != null) getIdToVersions(url)?.getNullSafe(id) else createEmptySortedSet
	}	
	
	def private getNullSafe(SortedMap<String, SortedSet<String>> map, String key) {
		if (map.get(id) == null) {
			map.put(id, createEmptySortedSet)
		}
		map.get(id)
	}
	
	def private createEmptySortedSet() {
		<String>newTreeSet([s1, s2 | compareStrings(s1, s2)])
	}
	
	def String getVersion(String url, String id) {
		url.getVersions(id).version
	}
	
	def String getVersion(String url) {
		url.versions.version
	}
	
	def private String getVersion(SortedSet<String> versions) {
		if (versions.size() > 0)  versions.first() else ""
	}
}