package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.Target
import de.abg.jreichert.repositorytarget.definition.Unit

class TargetTransformator {
	
	def targetTransformators() {
		val list = <(Target)=>Target>newArrayList()
		list.addAll(swtTargetTransformator())
		list
	}
	
	def swtTargetTransformator() {
		val unitTransformatorList = swtTargetIdUnitTransformator()
		unitTransformatorList.addAll(swtCategoryUnitTransformator())

		val (Target) => Target transformator = [
			locations.forEach[units.forEach(u|unitTransformatorList.forEach[apply(u)])]
			it
		]
		newArrayList(transformator)
	}	
	
	def swtTargetIdUnitTransformator() {
		val (Unit) => Unit unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId)) { 
				targetId = "org.eclipse.swt"
				feature=false
			}
			it
		]
		newArrayList(unitTransformator)
	}	
	
	def swtCategoryUnitTransformator() {
		val (Unit) => Unit unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId) || "org.eclipse.swt".equals(targetId)) { 
				includeInCategoryXml=false
			}
			it
		]
		newArrayList(unitTransformator)
	}	
}