package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentParser
import java.io.FileInputStream
import java.io.File

class LocalTargetDefinition extends AbstractTargetDefinition {
	private String targetFilePath
	
	new(String targetFilePath) {
		this.targetFilePath = targetFilePath
	}
	
	override targetDefinition((Unit) => boolean... filter) { 
		val sprayContentParser = new ContentParser
		val sprayTargetHandler = new de.abg.jreichert.repositorytarget.xml.TargetHandler
		val sprayTargetContent = sprayContentParser.getContent(new FileInputStream(new File(targetFilePath)))
		sprayContentParser.parse(sprayTargetContent, sprayTargetHandler)
		sprayTargetHandler.getTarget
	}
}