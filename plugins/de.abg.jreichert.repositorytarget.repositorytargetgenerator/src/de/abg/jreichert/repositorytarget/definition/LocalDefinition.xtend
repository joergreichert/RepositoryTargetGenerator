package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentParser
import de.abg.jreichert.repositorytarget.xml.TargetHandler
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import org.xml.sax.SAXException

class LocalTargetDefinition extends AbstractTargetDefinition {
	private String targetFilePath

	new(String targetFilePath) {
		this.targetFilePath = targetFilePath
	}

	override targetDefinition() throws IOException , SAXException {
		val sprayContentParser = new ContentParser
		val sprayTargetHandler = new TargetHandler
		val sprayTargetContent = sprayContentParser.getContent(new FileInputStream(new File(targetFilePath)))
		sprayContentParser.parse(sprayTargetContent, sprayTargetHandler)
		sprayTargetHandler.getTarget
	}
}
