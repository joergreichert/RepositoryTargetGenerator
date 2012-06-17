package de.abg.jreichert.repositorytarget.xml

import org.xml.sax.helpers.DefaultHandler
import de.abg.jreichert.repositorytarget.definition.Location
import de.abg.jreichert.repositorytarget.definition.Unit
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import de.abg.jreichert.repositorytarget.definition.Target

class TargetHandler extends DefaultHandler {

	private Location location = null;
	private Target target = null;
	private Unit unit = null;
	
	new() {
	}

	override characters(char[] ch, int start, int length)
			throws SAXException {
	}

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("target")) {
			target = new Target();
			target.setName(atts.getValue("name"));
		} else if (localName.equals("location")) {
			location = new Location();
			target.getLocations().add(location);
		} else if (localName.equals("unit")) {
			unit = new Unit();
			unit.setTargetId(atts.getValue("id"));
			location.getUnits().add(unit);
			//unit.setVersion(atts.getValue("version"));
		} else if (localName.equals("repository")) {
			location.setRepositoryLocation(atts.getValue("location"));
		}
	}
	
	def getTarget() {
		target;
	}
}