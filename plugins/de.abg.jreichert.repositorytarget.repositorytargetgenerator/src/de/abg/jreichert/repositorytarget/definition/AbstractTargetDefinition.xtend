package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import java.util.List

abstract class AbstractTargetDefinition {

	def buildTarget(List<(Unit) => List<(String) => boolean>> unitFilters, List<(Target) => void> transformators) {
		val target = targetDefinition()
		target.fillVersions(unitFilters)
		for(transformator : transformators) {
			transformator.apply(target)
		}
		target
	}
	
	def Target fillVersions(Target target, List<(Unit) => List<(String) => boolean>> unitFilters) {
		for(l : target.locations) {
			val parser = new ContentJarParser()
			l.units.forEach[fillVersion(parser, l.repositoryLocation, filter(unitFilters))]			
		}
		target
	}
	
	def List<(String) => boolean> filter(Unit unit, List<(Unit) => List<(String) => boolean>> unitFilters) {
		val list = <(String) => boolean>newArrayList
		for(f : unitFilters) {
			list.addAll(f.apply(unit))
		}
		list
	}
	
	def fillVersion(Unit unit, ContentJarParser parser, String repositoryLocation, List<(String) => boolean> filters) {
		if(unit.version.nullOrEmpty) {
			unit.version = parser.parseVersionForId(repositoryLocation, unit.categoryId, filters)
		}	
	}

	def Target targetDefinition() 
}