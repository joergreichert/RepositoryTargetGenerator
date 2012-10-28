package de.abg.jreichert.repositorytarget.xml

import java.util.List
import java.util.SortedMap
import java.util.SortedSet
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.util.Set

class ContentXmlHandler extends DefaultHandler {

	private String id;
	private SortedMap<String, SortedSet<String>> idToVersions = newTreeMap([s1, s2 | compareStrings(s1, s2)])
	private String version;
	private String expectedId;
	private List<(String) => boolean> filters;
	
	new(String expectedId, List<(String) => boolean> filters) {
		this(filters)
		this.expectedId = expectedId
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
	
	new(String expectedId) {
		this()
		this.expectedId = expectedId
	}
	
	new() {
		filters = <(String) => boolean>newArrayList
	}
	
	new(List<(String) => boolean> filters) {
		this.filters = filters
	}

	override characters(char[] ch, int start, int length)
			throws SAXException {
	}

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("unit")) {
			id = atts.getValue("id")
			if(expectedId != null) {
				if(id?.startsWith(expectedId)) {
					register(atts)
				}
			} else {
				register(atts)
			}
		}
	}
	
	def private register(Attributes atts) {
		version = atts.getValue("version")
		if(version?.filter) {
			if(!idToVersions.containsKey(id)) {
				idToVersions.put(id, <String>newTreeSet([s1, s2 | compareStrings(s1, s2)]))
			}
			if(!version.nullOrEmpty) {
				idToVersions.get(id).add(version)
			}
		}
	}
	
	def filter(String version) {
		var boolean passed = true
		for(filter : filters) {
			passed = passed && filter.apply(version)
		}
		passed
	}

	def SortedMap<String, SortedSet<String>> getIdToVersion() {
		idToVersions		
	}
	
	def Set<String> getIds() {
		idToVersions.keySet		
	}
	
	def SortedSet<String> getVersions() {
		if(expectedId != null) expectedId.versions else createEmptySortedSet
	}
	
	def SortedSet<String> getVersions(String id) {
		if(id != null) idToVersions.getNullSafe(id) else createEmptySortedSet
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
	
	def String getVersion(String id) {
		id.versions.version
	}
	
	def String getVersion() {
		versions.version
	}
	
	def private String getVersion(SortedSet<String> versions) {
		if (versions.size() > 0)  versions.first() else ""
	}
}