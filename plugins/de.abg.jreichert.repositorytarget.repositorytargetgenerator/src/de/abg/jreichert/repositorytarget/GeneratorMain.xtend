package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.OperaTargetDefinition
import de.abg.jreichert.repositorytarget.definition.LocalTargetDefinition
import java.io.FileWriter

import static de.abg.jreichert.repositorytarget.GeneratorMain.*
import java.util.List
import org.eclipse.xtext.xbase.lib.Functions
import de.abg.jreichert.repositorytarget.definition.Unit
import java.util.regex.Pattern

class GeneratorMain {
	
	def static void main(String[] args) {
		generateSpray
	}
	
	def static unitFilters() {
		val list = <Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>>>newArrayList()
		list.addAll(sdkFilter())
		list.addAll(pdeFilter())
		list
	}

	def static sdkFilter() {
		unitFilters("org.eclipse.sdk.feature.group", "\\d+\\.\\d+\\.\\d+\\.v\\d{8}-\\d{4}-.*")
	}
	
	def static pdeFilter() {
		unitFilters("org.eclipse.pde.feature.group", "3.8.\\d+\\.v\\d{8}-\\d{4}-.*")
	}
	
	def static unitFilters(String expectedTargetId, String versionRegex) {
		val versionPattern = Pattern::compile(versionRegex)
		val Functions$Function1<String, Boolean> filterVersion = [ String e | versionPattern.matcher(e).matches ]
		val List<Functions$Function1<String, Boolean>> versionFilters = newArrayList(filterVersion)
		val List<Functions$Function1<String, Boolean>> emptyVersionFilters = newArrayList()
		val (Unit) => boolean targetIdFilter = [ expectedTargetId.equals(targetId) ]
		val Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>> unitFilter
			= [if(targetIdFilter.apply(it)) versionFilters else emptyVersionFilters]
		newArrayList(unitFilter)
	}		

	def static generateSpray() {
		val sprayGenerator = new GeneratorMain
		val sprayTargetDefinition = new LocalTargetDefinition("input/spray/spray-juno.target")
		val sprayTarget = sprayTargetDefinition.buildTarget(unitFilters())
		sprayGenerator.generate("output/spray/category.xml", sprayTarget.generateCategoryXml)
		sprayGenerator.generate("output/spray/spray-juno.target", sprayTarget.generateTarget)
	}

	def static generateOpera() {
		val operaGenerator = new GeneratorMain
		val operaTargetDefinition = new OperaTargetDefinition
		val operaTarget = operaTargetDefinition.buildTarget(unitFilters())
		operaGenerator.generate("output/opera/category.xml", operaTarget.generateCategoryXml)
		operaGenerator.generate("output/opera/todo.target", operaTarget.generateTarget)
	}
	
	def generate(String fileName, CharSequence fileContent) {
		val writer = new FileWriter(fileName)
		writer.write(fileContent.toString)
		writer.close
	}	
}