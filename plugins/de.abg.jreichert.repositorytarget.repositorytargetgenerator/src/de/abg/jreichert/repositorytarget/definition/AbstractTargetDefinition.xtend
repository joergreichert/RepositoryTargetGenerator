package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import org.eclipse.xtext.xbase.lib.Functions
import java.util.List

abstract class AbstractTargetDefinition {

	def buildTarget(List<Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>>> unitFilters, List<Functions$Function1<Target, Target>> transformators) {
		val target = targetDefinition()
		target.fillVersions(unitFilters)
		var tmpTarget = target
		for(transformator : transformators) {
			val finalTarget = tmpTarget
			tmpTarget = transformator.apply(finalTarget)
		}
		tmpTarget
	}
	
	def Target fillVersions(Target target, List<Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>>> unitFilters) {
		for(l : target.locations) {
			val parser = new ContentJarParser(l.repositoryLocation)
			l.units.forEach([fillVersion(parser, filter(unitFilters))])			
		}
		target
	}
	
	def filter(Unit unit, List<Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>>> unitFilters) {
		val list = <Functions$Function1<String, Boolean>>newArrayList
		for(f : unitFilters) {
			list.addAll(f.apply(unit))
		}
		list
	}
	
	def fillVersion(Unit unit, ContentJarParser parser, List<Functions$Function1<String, Boolean>> filters) {
		if(unit.version.nullOrEmpty) {
			unit.version = parser.parseVersionForId(unit.categoryId, filters)
		}	
	}

	def Target targetDefinition() 
}