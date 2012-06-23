package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.Target
import de.abg.jreichert.repositorytarget.definition.Unit

class TargetTransformator {
	
	def targetTransformators() {
		val list = <(Target) => void>newArrayList()
		list.addAll(swtTargetTransformator())
		list
	}
	
	def swtTargetTransformator() {
		val unitTransformatorList = swtTargetIdUnitTransformator()
		unitTransformatorList.addAll(swtCategoryUnitTransformator())

		val (Target) => void transformator = [
			locations.forEach[units.forEach(u|unitTransformatorList.forEach[apply(u)])]
		]
		newArrayList(transformator)
	}	
	
	def swtTargetIdUnitTransformator() {
		val (Unit) => void unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId)) { 
				targetId = "org.eclipse.swt"
				feature=false
			}
		]
		newArrayList(unitTransformator)
	}	
	
	def swtCategoryUnitTransformator() {
		val (Unit) => void unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId) || "org.eclipse.swt".equals(targetId)) { 
				includeInCategoryXml=false
			}
		]
		newArrayList(unitTransformator)
	}	
}