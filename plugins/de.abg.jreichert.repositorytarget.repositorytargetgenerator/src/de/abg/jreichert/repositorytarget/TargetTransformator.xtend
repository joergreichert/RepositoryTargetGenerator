package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.Unit
import de.abg.jreichert.repositorytarget.definition.Target
import org.eclipse.xtext.xbase.lib.Functions

class TargetTransformator {
	
	def targetTransformators() {
		val list = <Functions$Function1<Target, Target>>newArrayList()
		list.addAll(swtTargetTransformator())
		list
	}
	
	def swtTargetTransformator() {
		val unitTransformatorList = swtTargetIdUnitTransformator()
		unitTransformatorList.addAll(swtCategoryUnitTransformator())

		val Functions$Function1<Target, Target> transformator = [
			locations.forEach([units.forEach(u|unitTransformatorList.forEach([apply(u)]))])
			it
		]
		
		val list = <Functions$Function1<Target, Target>>newArrayList(transformator)
		list
	}	
	
	def swtTargetIdUnitTransformator() {
		val Functions$Function1<Unit, Unit> unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId)) { 
				targetId = "org.eclipse.swt"
				feature=false
			}
			it
		]
		val list = <Functions$Function1<Unit, Unit>>newArrayList(unitTransformator)
		list
	}	
	
	def swtCategoryUnitTransformator() {
		val Functions$Function1<Unit, Unit> unitTransformator = [
			if("org.eclipse.swt.feature.group".equals(targetId) || "org.eclipse.swt".equals(targetId)) { 
				includeInCategoryXml=false
			}
			it
		]
		val list = <Functions$Function1<Unit, Unit>>newArrayList(unitTransformator)
		list
	}	
}