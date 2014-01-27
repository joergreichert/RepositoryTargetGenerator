package de.abg.jreichert.repositorytarget.transform.popup.actions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Location;
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target;
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.TargetDefinitionFactory;
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit;

public class TargetHandler extends DefaultHandler {

	private Target target = null;
	private Location currentLocation = null;
	private Unit currentUnit = null;
	private final String targetName;
	private static final String FEATURE_GROUP_SUFFIX = ".feature.group";
	
	public TargetHandler(String targetName) {
		this.targetName = targetName;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (target == null) {
			if (localName.equals("target")) {
				currentLocation = null;
				target = TargetDefinitionFactory.eINSTANCE.createTarget();
				target.setName(atts.getValue("name"));
				target.setTargetFileName(targetName);
			}
		} else {
			if (localName.equals("location")) {
				currentUnit = null;
				currentLocation = TargetDefinitionFactory.eINSTANCE.createLocation();
				target.getLocations().add(currentLocation);
			} else if (localName.equals("unit")) {
				currentUnit = TargetDefinitionFactory.eINSTANCE.createUnit();
				String id = atts.getValue("id");
				
				int index = id.lastIndexOf(FEATURE_GROUP_SUFFIX);
				if(index > 0) {
					id = id.replace(FEATURE_GROUP_SUFFIX, "");
				} else {
					currentUnit.setNoFeature(true);
				}
				currentUnit.setCategoryId(id);
				currentUnit.setVersion(atts.getValue("version"));
				currentLocation.getUnit().add(currentUnit);
			} else if (localName.equals("repository")) {
				currentLocation.setRepositoryLocation(atts.getValue("location"));
			}
		}
	}
	
	public Target getTarget() {
		return target;
	}
}
