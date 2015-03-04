package de.abg.jreichert.repositorytarget.xml

import java.util.List
import org.xml.sax.Attributes
import org.xml.sax.SAXException

class CompositeContentXmlHandler extends P2TimestampContentXmlHandler {

	private String location;
	private val children = <String>newArrayList()
	private String child;

	new(String location) {
		if (!location.endsWith("/")) {
			this.location = location + "/";
		} else {
			this.location = location;
		}
	}

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		super.startElement(uri, localName, qName, atts)	   
		if (localName.equals("child")) {
			child = atts.getValue("location");
			if (child !== null) {
				if (!child.contains("http://")) {
					if(child.contains("file://")) {
						child = child.replace("file://", "");
					}
					if(child.startsWith("jar:")) {
						child = "jar:" + location.replace("file://", "file:") + child.replace("jar:", "");
					} else {
						child = location + child;
					}
				}
				children.add(child);
			}
		}
	}
	
	def List<String> getLocations() {
		return children;
	}
}
