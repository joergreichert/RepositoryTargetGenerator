package de.abg.jreichert.repositorytarget.dsl.validation

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Category
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Location
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.TargetDefinitionPackage
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import java.util.List
import java.util.SortedMap
import java.util.SortedSet
import org.apache.log4j.Logger
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.validation.Check

import static org.eclipse.xtext.validation.CheckType.*

class TargetDefinitionValidator extends AbstractTargetDefinitionValidator {

	public static val NOT_UPTODATE = "NOT_UPTODATE"
	public static val DEPRECATED_FEATURE_ORDER = "DEPRECATED_FEATURE_ORDER"

	private val static Logger LOGGER = Logger::getLogger(typeof(TargetDefinitionValidator))

	@Inject
	private ReadOutP2Repository readOutP2Repository
	
	@Check(value=EXPENSIVE)
	@LogExecutionTime
	def void checkUpToDate(Target target) {
		try {
			val monitor = new NullProgressMonitor
			for (location : target.locations) {
				try {
					val contentHandler = new ContentXmlHandler(location.repositoryLocation)
					readOutP2Repository.execute(location.repositoryLocation, contentHandler, monitor)
					
					
					val urlToIdVersionPairs = contentHandler.urlToIdToVersion
					checkLocation(urlToIdVersionPairs, location)
				} catch (IllegalArgumentException iae) {
					error(iae.message, location, TargetDefinitionPackage.Literals.LOCATION__REPOSITORY_LOCATION)
				}
			}
		} catch (OutOfMemoryError ooe) {
			LOGGER.error(
				"Out of memory: Please start your Eclipse with something like -Xmx1024m -Xms1024m -XX:MaxPermSize=512m",
				ooe)
		}
	}

	def void checkLocation(SortedMap<String, SortedMap<String, SortedSet<String>>> urlToIdVersionPairs, Location location) {
		val foundUnits = <Unit>newArrayList
		val notFoundUnits = <Unit>newArrayList
		val foundUnitVersions = <Unit>newArrayList
		val notFoundUnitVersionsMap = <Unit, SortedSet<String>>newHashMap
		val foundRecentUnitVersions = <Unit>newArrayList
		val notRecentUnitVersionsMap = <Unit, List<String>>newHashMap
		for(idVersionPairs : urlToIdVersionPairs.entrySet) {
			if (idVersionPairs.value !== null) {
				for (unit : location.unit) {
					val localFoundUnitVersions = idVersionPairs.value.get(
						if(unit.noFeature) unit.categoryId else unit.categoryId + ".feature.group"
					)
					if (localFoundUnitVersions === null || localFoundUnitVersions.size == 0) {
						notFoundUnits.add(unit)
					} else {
						foundUnits.add(unit)
						if (!localFoundUnitVersions.contains(unit.version)) {
							notFoundUnitVersionsMap.put(unit, localFoundUnitVersions)
						} else {
							foundUnitVersions.add(unit)
							val newerVersions = localFoundUnitVersions.tailSet(unit.version).tail.sort
							if (newerVersions.size > 0) {
								notRecentUnitVersionsMap.put(unit, newerVersions)
							} else {
								foundRecentUnitVersions.add(unit)
							}
						}
					}
				}
			}
		}
		notFoundUnits.removeAll(foundUnits)
		for(unit : notFoundUnits) {
			error('unit does not exist in location anymore', unit,
				TargetDefinitionPackage.Literals.UNIT__CATEGORY_ID)
		}
		foundUnitVersions.forEach[notFoundUnitVersionsMap.remove(it)]
		for(notFoundUnitVersion : notFoundUnitVersionsMap.entrySet) {
			error('version does not exist for unit anymore', notFoundUnitVersion.key,
				TargetDefinitionPackage.Literals.UNIT__VERSION, NOT_UPTODATE, notFoundUnitVersion.value)
		}
		foundRecentUnitVersions.forEach[notRecentUnitVersionsMap.remove(it)]
		for(notRecentUnitVersion : notRecentUnitVersionsMap.entrySet) {
			warning('unit does not use the latest version ' + notRecentUnitVersion.value, notRecentUnitVersion.key,
				TargetDefinitionPackage.Literals.UNIT__VERSION, NOT_UPTODATE, notRecentUnitVersion.value.toList)
		}
	}

	@Check
	def checkNoFeaturePosition(Unit unit) {
		val unitNode = NodeModelUtils.findActualNodeFor(unit)
		val children = unitNode.children.iterator.toList
		val categoryIdNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__CATEGORY_ID).
			head
		val noFeatureNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__NO_FEATURE).
			head
		val noFeatureNodeIndex = children.indexOf(noFeatureNode)
		if (noFeatureNodeIndex != -1) {
			val versionNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__VERSION).
				head
			val versionNodeIndex = children.indexOf(versionNode)
			if (versionNodeIndex != -1 && noFeatureNodeIndex > versionNodeIndex) {
				error('noFeature have to placed between categoryId and version', unit.eContainer,
					unit.eContainingFeature,
					(unit.eContainer.eGet(unit.eContainingFeature) as List<Unit>).indexOf(unit),
					DEPRECATED_FEATURE_ORDER, categoryIdNode.text + " noFeature " + versionNode.text)
			}
		}
	}

	@Check
	def checkNoMultipleCategoryNames(Target target) {
		val nameToCategories = <String, List<Category>>newHashMap
		target.categories.forEach [
			val entry = nameToCategories.get(name)
			val categories = if(entry === null) newArrayList else entry
			categories.add(it)
			nameToCategories.put(name, categories)
		]
		nameToCategories.entrySet.filter[value.size > 1].map[value].forEach [
			forEach[
				error('Category name have to unique', eContainer, eContainingFeature,
					(eContainer.eGet(eContainingFeature) as List<?>).indexOf(it))
			]
		]
	}

	@Check
	def checkNoMultipleDefaults(Target target) {
		val defaultCategories = target.categories.filter[^default]
		if (defaultCategories.size > 1) {
			defaultCategories.forEach [
				error('Only one category can be marked as default', eContainer, eContainingFeature,
					(eContainer.eGet(eContainingFeature) as List<?>).indexOf(it))
			]
		}
	}
}
