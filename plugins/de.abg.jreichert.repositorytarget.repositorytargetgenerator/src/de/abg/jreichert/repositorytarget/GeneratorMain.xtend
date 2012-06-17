package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.LocalTargetDefinition
import de.abg.jreichert.repositorytarget.definition.OperaTargetDefinition
import java.io.FileWriter

class GeneratorMain {
	private val TargetFilter targetFilter;
	private val TargetTransformator targetTransformator;
	
	def static void main(String[] args) {
		val generator = new GeneratorMain 
		generator.generateSpray
	}
	
	new() {
		targetFilter = new TargetFilter
		targetTransformator = new TargetTransformator
	}
	
	def generateSpray() {
		val sprayGenerator = new GeneratorMain
		val sprayTargetDefinition = new LocalTargetDefinition("input/spray/spray-juno.target")
		val sprayTarget = sprayTargetDefinition.buildTarget(targetFilter.unitFilters(), targetTransformator.targetTransformators)
		sprayGenerator.generate("output/spray/category.xml", sprayTarget.generateCategoryXml)
		sprayGenerator.generate("output/spray/spray-juno.target", sprayTarget.generateTarget)
	}

	def generateOpera() {
		val operaGenerator = new GeneratorMain
		val operaTargetDefinition = new OperaTargetDefinition
		val operaTarget = operaTargetDefinition.buildTarget(targetFilter.unitFilters, targetTransformator.targetTransformators)
		operaGenerator.generate("output/opera/category.xml", operaTarget.generateCategoryXml)
		operaGenerator.generate("output/opera/todo.target", operaTarget.generateTarget)
	}
	
	def generate(String fileName, CharSequence fileContent) {
		val writer = new FileWriter(fileName)
		writer.write(fileContent.toString)
		writer.close
	}	
}