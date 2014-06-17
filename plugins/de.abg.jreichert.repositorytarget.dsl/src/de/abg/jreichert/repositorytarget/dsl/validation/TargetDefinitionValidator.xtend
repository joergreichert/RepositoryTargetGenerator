package de.abg.jreichert.repositorytarget.dsl.validation

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Category
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.TargetDefinitionPackage
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import java.util.List
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
						if(foundUnitVersions === null || foundUnitVersions.size == 0) {
							error('unit does not exist in location anymore', 
									unit, TargetDefinitionPackage.Literals.UNIT__CATEGORY_ID)
						} else {
							if(!foundUnitVersions.contains(unit.version)) {
								error('version does not exist for unit anymore', 
										unit, TargetDefinitionPackage.Literals.UNIT__VERSION,
										NOT_UPTODATE, foundUnitVersions)
							} else {
								val newerVersions =	foundUnitVersions.tailSet(unit.version).tail.sortVersions
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
	
	def sortVersions(Iterable<String> versions) {
		val tokenized = versions.map[it -> it.tokenize.toList].toList
		tokenized.sort(left, right| compareVersions(left.value, right.value)).map[key]
	}

	def tokenize(String version) {
		version.tokenize('\\.').map[it.tokenize('-')].flatten
	}

	def tokenize(String version, String delimiter) {
		version.split(delimiter).toList.fold(newArrayList, [list, b | if(!list.empty) list.add(delimiter); list.add(b); list])
	}
	
	def compareVersions(List<String> left, List<String> right) {
		val max = if (left.size < right.size) left.size else right.size
		val typeList = <Class<?>>newArrayList
		for(i : 0 ..< max) {
			if(parse(left.get(i)) instanceof Integer && parse(right.get(i)) instanceof Integer) {
				typeList.add(Integer)
			} else {
				typeList.add(String)
			}
		}
		if(typeList.size > 0) {
			var oldType = null
			val newLeftList = newArrayList
			val newRightList = newArrayList
			var newLeftString = ""
			var newRightString = ""
			for(i : 0 ..< typeList.size) {
				if(typeList.get(i) == oldType == String) {
					newLeftString += left.get(i) 
					newRightString += right.get(i) 
				} else {
					newLeftList.add(newLeftString)
					newLeftString = ""
					newRightList.add(newRightString)
					newRightString = ""
					newLeftList.add(left.get(i))
					newRightList.add(right.get(i))
				}
			}
			newLeftString = ""
			newRightString = ""
			for(i : typeList.size ..< left.size) {
				newLeftString += left.get(i) 
			}
			newLeftList.add(newLeftString)
			for(i : typeList.size ..< right.size) {
				newRightString += right.get(i) 
			}
			newRightList.add(newRightString)
			val compareResult = (0..<newLeftList.size).map[i|newLeftList.get(i).parse.compareValue(newRightList.get(i).parse)]
			val findFirst = compareResult.findFirst[it != 0]
			return if(findFirst === null) 0 else findFirst
		}
		return 0
	}
	
	def Object parse(String s) {
		try {
			return Integer.parseInt(s);
		} catch(NumberFormatException e) {
			return s
		}
	}
	
	def dispatch int compareValue(String s, String s2) {
		s.compareTo(s2)
	}

	def dispatch int compareValue(String s, Integer i2) {
		-compareValue(i2, s)
	}

	def dispatch int compareValue(Integer i, String s2) {
		if(s2.length == 0) {
			-1
		} else {
			val iAsString = Integer.toString(i)
			if(iAsString.length == s2.length) {
				return iAsString.compareTo(s2)
			} else {
				if(iAsString.length > s2.length) {
					val result = iAsString.substring(0, iAsString.length-s2.length).compareTo(s2)
					if(result == 0) {
						return compareValue(iAsString.substring(iAsString.length-s2.length), "")
					} else {
						return -result
					}
				} else {
					val result = iAsString.compareTo(s2.substring(0, s2.length-iAsString.length))
					if(result == 0) {
						return compareValue(s2.substring(s2.length-iAsString.length), "")
					} else {
						return -result
					}
				}
			}
		}
	}
	
	def dispatch int compareValue(Integer i, Integer i2) {
		i.compareTo(i2)
	}

	def dispatch int compareValue(Object o, Object o2) {
		o.toString.compareTo(o2.toString)
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
	
	@Check
	def checkNoMultipleCategoryNames(Target target) {
		val nameToCategories = <String, List<Category>>newHashMap
		target.categories.forEach[
			val entry = nameToCategories.get(name)  
			val categories = if(entry === null) newArrayList else entry 
			categories.add(it)
			nameToCategories.put(name, categories)
			
		]
		nameToCategories.entrySet.filter[value.size > 1].map[value].forEach[
			forEach[
				error('Category name have to unique',
					eContainer, eContainingFeature, 
					(eContainer.eGet(eContainingFeature) as List<?>).indexOf(it))
			]
		]
	}
	
	@Check
	def checkNoMultipleDefaults(Target target) {
		val defaultCategories = target.categories.filter[^default]
		if(defaultCategories.size > 1) {
			defaultCategories.forEach[
				error('Only one category can be marked as default',
					eContainer, eContainingFeature, 
					(eContainer.eGet(eContainingFeature) as List<?>).indexOf(it))
			]
		}
	}	
}
