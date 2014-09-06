package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentParser
import java.io.FileInputStream
import java.io.File
import java.io.FileNotFoundException

class LocalTargetDefinition extends AbstractTargetDefinition {
	private String targetFilePath
	
	new(String targetFilePath) {
		this.targetFilePath = targetFilePath
	}
	
	override targetDefinition() { 
		val sprayContentParser = new ContentParser
		val sprayTargetHandler = new de.abg.jreichert.repositorytarget.xml.TargetHandler
		try {
			val sprayTargetContent = sprayContentParser.getContent(new FileInputStream(new File(targetFilePath)))
			sprayContentParser.parse(sprayTargetContent, sprayTargetHandler)
			sprayTargetHandler.getTarget
		} catch (FileNotFoundException exc) {
			throw new RuntimeException(exc)
		}
	}
}