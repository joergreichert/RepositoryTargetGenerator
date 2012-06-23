package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import java.util.List

abstract class AbstractTargetDefinition {

	def buildTarget(List<(Unit) => List<(String) => boolean>> unitFilters, List<(Target) => Target> transformators) {
		val target = targetDefinition()
		target.fillVersions(unitFilters)
		var tmpTarget = target
		for(transformator : transformators) {
			val finalTarget = tmpTarget
			tmpTarget = transformator.apply(finalTarget)
		}
		tmpTarget
	}
	
	def Target fillVersions(Target target, List<(Unit) => List<(String) => boolean>> unitFilters) {
		for(l : target.locations) {
			val parser = new ContentJarParser(l.repositoryLocation)
			l.units.forEach[fillVersion(parser, filter(unitFilters))]			
		}
		target
	}
	
	def filter(Unit unit, List<(Unit) => List<(String) => boolean>> unitFilters) {
		val list = <(String) => boolean>newArrayList
		for(f : unitFilters) {
			list.addAll(f.apply(unit))
		}
		list
	}
	
	def fillVersion(Unit unit, ContentJarParser parser, List<(String) => boolean> filters) {
		if(unit.version.nullOrEmpty) {
			unit.version = parser.parseVersionForId(unit.categoryId, filters)
		}	
	}

	def Target targetDefinition() 
}