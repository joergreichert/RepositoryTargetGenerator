package de.abg.jreichert.repositorytarget.xml

import org.xml.sax.helpers.DefaultHandler
import java.util.SortedSet
import java.util.TreeSet
import org.xml.sax.SAXException
import org.xml.sax.Attributes
import org.eclipse.xtext.xbase.lib.Functions$Function1
import java.util.List
import org.eclipse.xtext.xbase.lib.Functions

class ContentXmlHandler extends DefaultHandler {

	private String id;
	private SortedSet<String> versions = new TreeSet<String>();
	private String version;
	private String expectedId;
	private List<Functions$Function1<String, Boolean>> filters;
	
	new(String expectedId, List<Functions$Function1<String, Boolean>> filters) {
		this.expectedId = expectedId
		this.filters = filters
	}

	override characters(char[] ch, int start, int length)
			throws SAXException {
	}

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("unit")) {
			id = atts.getValue("id")
			if(id != null && id.startsWith(expectedId)) {
				version = atts.getValue("version")
				if(version != null && filter(version)) {
					versions.add(atts.getValue("version"))
				}
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
	
	def String getVersion() {
		if (versions.size() > 0)  versions.first() else ""
	}
}