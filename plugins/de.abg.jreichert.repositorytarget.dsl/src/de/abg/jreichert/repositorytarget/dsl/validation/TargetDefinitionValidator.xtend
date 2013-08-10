package de.abg.jreichert.repositorytarget.dsl.validation

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.TargetDefinitionPackage
import org.apache.log4j.Logger
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.xtext.validation.Check

import static org.eclipse.xtext.validation.CheckType.*
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import java.util.List

class TargetDefinitionValidator extends AbstractTargetDefinitionValidator {
	
	public static val NOT_UPTODATE = "NOT_UPTODATE"
	public static val DEPRECATED_FEATURE_ORDER = "DEPRECATED_FEATURE_ORDER"
	
	private val static Logger LOGGER = Logger::getLogger(typeof(TargetDefinitionValidator)) 

	@Inject
	private ReadOutP2Repository readOutP2Repository

	@Check(value=EXPENSIVE)
	def checkUpToDate(Target target) {
		try {
			val contentHandler = new ContentXmlHandler()
			val monitor = new NullProgressMonitor
			for(location : target.locations) {
				try {
					readOutP2Repository.execute(location.repositoryLocation, contentHandler, monitor)
					val idVersionPairs = contentHandler.idToVersion
					for(unit : location.unit) {
						val foundUnitVersions = idVersionPairs.get(
							if(unit.noFeature) unit.categoryId else unit.categoryId + ".feature.group" 
						)
						if(foundUnitVersions == null || foundUnitVersions.size == 0) {
							error('unit does not exist in location anymore', 
									unit, TargetDefinitionPackage.Literals.UNIT__CATEGORY_ID)
						} else {
							if(!foundUnitVersions.contains(unit.version)) {
								error('version does not exist for unit anymore', 
										unit, TargetDefinitionPackage.Literals.UNIT__VERSION,
										NOT_UPTODATE, foundUnitVersions)
							} else {
								val newerVersions =	foundUnitVersions.tailSet(unit.version).tail.sort
								if(newerVersions.size > 0) {
									warning('unit does not use the latest version ' + newerVersions, 
											unit, TargetDefinitionPackage.Literals.UNIT__VERSION,
											NOT_UPTODATE, newerVersions.toList)
								} 
							}
						}
					}
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
	
	@Check
	def checkNoFeaturePosition(Unit unit) {
		val unitNode = NodeModelUtils.findActualNodeFor(unit)
		val children = unitNode.children.iterator.toList
		val categoryIdNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__CATEGORY_ID).head
		val noFeatureNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__NO_FEATURE).head
		val noFeatureNodeIndex = children.indexOf(noFeatureNode)
		if(noFeatureNodeIndex != -1) {
			val versionNode = NodeModelUtils.findNodesForFeature(unit, TargetDefinitionPackage.Literals.UNIT__VERSION).head
			val versionNodeIndex = children.indexOf(versionNode)
			if(versionNodeIndex != -1 && noFeatureNodeIndex > versionNodeIndex) {
				error('noFeature have to placed between categoryId and version', 
											unit.eContainer, unit.eContainingFeature, 
											(unit.eContainer.eGet(unit.eContainingFeature) as List<Unit>).indexOf(unit),
											DEPRECATED_FEATURE_ORDER, categoryIdNode.text + " noFeature " + versionNode.text)
			}
		}
	}
}
