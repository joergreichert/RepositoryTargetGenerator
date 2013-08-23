/*
 * generated by Xtext
 */
package de.abg.jreichert.repositorytarget.dsl.generator

import de.abg.jreichert.repositorytarget.definition.Location
import de.abg.jreichert.repositorytarget.definition.Target
import de.abg.jreichert.repositorytarget.definition.Unit
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import de.abg.jreichert.repositorytarget.definition.Category

class TargetDefinitionGenerator implements IGenerator {
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		val dslTarget = resource.allContents.filter(typeof(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target)).head
		if(dslTarget != null) {
			val generatorTarget = dslTarget.transformTarget
			val dslTargetFileName = dslTarget.fileName
			val dslTargetFolderName = dslTargetFileName.replace('.target', '')
			fsa.generateFile(dslTargetFolderName + "/category.xml", generatorTarget.generateCategoryXml)
			fsa.generateFile(dslTargetFolderName + "/" + dslTargetFileName, generatorTarget.generateTarget)
			fsa.generateFile(dslTargetFolderName + "/feature.xml", generatorTarget.generateFeatureXml)
		}
	}
	
	def private fileName(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target it) {
		if(targetFileName.nullOrEmpty) {
			name.replaceAll(" ", "").toFirstUpper + ".target"
		} else {
			if(targetFileName.endsWith(".target")) {
				targetFileName
			} else {
				targetFileName + ".target"
			}
		}
	}
	
	def Target create generatorTarget : new Target transformTarget(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target dslTarget) {
		generatorTarget.name = dslTarget.name
		dslTarget.categories.forEach[generatorTarget.categories.add(transformCategory)]
		dslTarget.locations.forEach[generatorTarget.locations.add(transformLocation)]
	}

	def Category create new Category transformCategory(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Category dslCategory) {
		name = dslCategory.name
		longName = dslCategory.longName
		description = dslCategory.description
		defaultCategory = dslCategory.^default
	}
	
	def Location create generatorLocation : new Location transformLocation(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Location dslLocation) {
		generatorLocation.repositoryLocation = dslLocation.repositoryLocation 
		generatorLocation.assignedLocationCategories = dslLocation.categories.map[name]
		generatorLocation.strictVersion = dslLocation.strictVersion
		dslLocation.unit.forEach[generatorLocation.units.add(transformUnit)]
	}
	
	def Unit create generatorUnit : new Unit transformUnit(de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit dslUnit) {
		if(dslUnit.noFeature) {
			generatorUnit.targetId = dslUnit.categoryId 
		} else {
			generatorUnit.categoryId = dslUnit.categoryId 
		}
		generatorUnit.version = dslUnit.version
		generatorUnit.feature = !dslUnit.noFeature
		generatorUnit.assignedUnitCategories = dslUnit.categories.map[name]
		generatorUnit.strictVersion = dslUnit.strictVersion
	}	
}
