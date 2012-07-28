package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.LocalTargetDefinition
import java.io.FileWriter
import java.io.File
import de.abg.jreichert.repositorytarget.definition.AbstractTargetDefinition
import de.abg.jreichert.repositorytarget.definition.TodoTargetDefinition

class GeneratorMain {
	private val TargetFilter targetFilter;
	private val TargetTransformator targetTransformator;
	
	def static void main(String[] args) {
		val generator = new GeneratorMain 
		if(args.size == 1) {
			switch(args.get(0)) {
				case "todo": generator.generateTodo
				case "spray": generator.generateSpray
				case "bookmarks": generator.generateBookmarks
			}
		} else if(args.size > 1) {
			val inputTargetFile = args.get(0)
			val outputPath = args.get(1)
			generator.generateNewFiles(inputTargetFile, outputPath)
		} else {
			System::err.println("Two parameters required: path to input target file and output path.")
		}
	}
	
	new() {
		targetFilter = new TargetFilter
		targetTransformator = new TargetTransformator
	}
	
	def generateNewFiles(String inputTargetFile, String outputPath) {
		val targetFileName = new File(inputTargetFile).name
		val targetDefinition = new LocalTargetDefinition(inputTargetFile)
		generateNewFiles(targetDefinition, outputPath, targetFileName)
	}
	
	def generateNewFiles(AbstractTargetDefinition targetDefinition, String outputPath, String targetFileName) {
		val target = targetDefinition.buildTarget(targetFilter.unitFilters(), targetTransformator.targetTransformators)
		generate(outputPath + "/category.xml", target.generateCategoryXml)
		generate(outputPath + "/" + targetFileName, target.generateTarget)
	}
	
	def generate(String fileName, CharSequence fileContent) {
		new File(fileName).parentFile?.mkdirs
		val writer = new FileWriter(fileName)
		writer.write(fileContent.toString)
		writer.close
	}	
	
	def generateSpray() {
		generateNewFiles("input/spray/spray-juno.target", "output/spray")
	}

	def generateBookmarks() {
		generateNewFiles("input/bookmarks/bookmarks.target", "output/bookmarks")
	}

	def generateTodo() {
		val todoTargetDefinition = new TodoTargetDefinition
		generateNewFiles(todoTargetDefinition, "output/todo", "todo.target")
	}
}