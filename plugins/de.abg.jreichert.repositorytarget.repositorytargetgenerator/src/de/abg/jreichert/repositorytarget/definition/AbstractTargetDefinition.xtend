package de.abg.jreichert.repositorytarget.definition

import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import java.io.IOException
import java.net.URISyntaxException
import java.util.List
import org.xml.sax.SAXException

abstract class AbstractTargetDefinition {

	def buildTarget(List<(Unit)=>List<(String)=>boolean>> unitFilters, List<(Target)=>void> transformators) throws IOException, SAXException , URISyntaxException {
		val target = targetDefinition()
		target.fillVersions(unitFilters)
		for (transformator : transformators) {
			transformator.apply(target)
		}
		target
	}

	def Target fillVersions(Target target, List<(Unit)=>List<(String)=>boolean>> unitFilters) throws SAXException, IOException, URISyntaxException {
		for (l : target.locations) {
			val parser = new ContentJarParser(l.repositoryLocation)
			l.units.forEach[try {
				fillVersion(parser, filter(unitFilters))
			} catch (SAXException exc) {
				throw new RuntimeException("auto-generated try/catch", exc)
			} catch (IOException exc) {
				throw new RuntimeException("auto-generated try/catch", exc)
			}]
		}
		target
	}

	def filter(Unit unit, List<(Unit)=>List<(String)=>boolean>> unitFilters) {
		val list = <(String)=>boolean>newArrayList
		for (f : unitFilters) {
			list.addAll(f.apply(unit))
		}
		list
	}

	def fillVersion(Unit unit, ContentJarParser parser, List<(String)=>boolean> filters) throws SAXException, IOException {
		if (unit.version.nullOrEmpty) {
			unit.version = parser.parseVersionForId(unit.categoryId, filters)
		}
	}

	def Target targetDefinition() throws IOException, SAXException
}
