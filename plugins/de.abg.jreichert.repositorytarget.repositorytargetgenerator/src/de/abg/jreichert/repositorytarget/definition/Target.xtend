package de.abg.jreichert.repositorytarget.definition

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

class Target {
   @Accessors private String name
   @Accessors private List<Category> categories = newArrayList
   @Accessors private List<Location> locations = newArrayList
  
   def createLocation((Location) => void initializer) {
      val location = new Location
      initializer.apply(location)
      location
   }
   
	def generateTarget() '''
		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<?pde version="3.6"?>

		<target includeMode="feature" name="«getName»" sequenceNumber="67">
			<locations>
				«getLocations.map[generateTarget].join»
			</locations>
			<environment>
				<nl>en</nl>
			</environment>
			<targetJRE path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.6"/>
			<launcherArgs>
				<vmArgs>-Xmx512m -XX:MaxPermSize=128m</vmArgs>
			</launcherArgs>
		</target>
	'''
	
	def generateCategoryXml() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<site>
			«getLocations.map[generateCategoryXml(getCategories)].join»
			«getCategories.map[generateCategoryXml].join»
		</site>
	'''    
	
	def generateFeatureXml() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<feature
		      id="%featureId"
		      label="%featureName"
		      version="%featureVersion"
		      provider-name="%providerName">
		
		   <description url="http://www.example.com/description">
		      [Enter Feature Description here.]
		   </description>
		
		   <copyright url="http://www.example.com/copyright">
		      [Enter Copyright Description here.]
		   </copyright>
		
		   <license url="http://www.example.com/license">
		      [Enter License Description here.]
		   </license>
		
		   «getLocations.map[generateFeatureXml].join»
		
		</feature>
	'''

	def generateDistoBuilderXML() '''
	'''
}


class Category {
	@Accessors private String name 
	@Accessors private String longName 
	@Accessors private String description 
	@Accessors private boolean defaultCategory

	def generateCategoryXml() '''
		<category-def name="«getName»" «IF !getLongName.nullOrEmpty»label="«getLongName»"«ENDIF»>
			«IF !getDescription.nullOrEmpty»
				<description>«getDescription»</description>
			«ENDIF»
		</category-def>
	'''	
}


class Location {
   @Accessors private List<Unit> units = newArrayList
   @Accessors private String repositoryLocation
   @Accessors private List<String> assignedLocationCategories = newArrayList
   @Accessors private boolean strictVersion = false
	
   def createUnit((Unit) => void initializer) {
      val unit = new Unit()
      initializer.apply(unit)
      unit
   }
   
	def generateTarget() '''
		<location includeAllPlatforms="false" includeMode="planner" includeSource="true" type="InstallableUnit">
			«getUnits.map[generateTarget].join»
			<repository location="«getRepositoryLocation.convertURI»"/>
		</location>
	'''
	
	def private convertURI(String uri) {
		if(uri.startsWith("file://")) uri.replaceFirst("file://", "file:///") else uri
	}
	
	def generateCategoryXml(List<Category> allCategories) '''
		«getUnits.map[it.generateCategoryXml(allCategories, getAssignedLocationCategories, isStrictVersion)].join»
	''' 
	
	def generateFeatureXml() '''
		«getUnits.map[generateFeatureXml].join»
	'''
}

class Unit {
	@Accessors private String categoryId = ""
	@Accessors private String targetId = ""
	@Accessors private String version = ""
	@Accessors private List<String> assignedUnitCategories = newArrayList
	@Accessors private Boolean feature = true
	@Accessors private Boolean includeInCategoryXml = true
	@Accessors private Boolean includeInTarget = true
	@Accessors private boolean strictVersion = false
	private String defaultCategory = "3rdparty"
	
	def String getTargetId() {
		if(targetId.nullOrEmpty && !categoryId.nullOrEmpty && !categoryId.endsWith("feature.group") && feature)
           targetId = categoryId + ".feature.group"
		else targetId	
	}

	def String getCategoryId() {
		if(!targetId.nullOrEmpty && categoryId.nullOrEmpty)
			categoryId = targetId.replace(".feature.group", "") 
		else categoryId	
	}
	
	def String getUrl() {
		if(!getCategoryId.nullOrEmpty && !getVersion.nullOrEmpty)
			"features/" + getCategoryId + "_" + calculateVersion(getVersion, isStrictVersion) + ".jar"
		else ""	
	}
	
	def generateTarget() '''
		«IF includeInTarget»
			<unit id="«getTargetId»" version="«getVersion»"/>
		«ENDIF»
	'''
	
	def generateCategoryXml(List<Category> allCategories, List<String> assignedLocationCategories, boolean strictVersion) '''
		«setStrictVersion = strictVersion»
		«IF getIncludeInCategoryXml»
			«FOR category : calculateCategory(allCategories, assignedLocationCategories, getAssignedUnitCategories)»
				<feature url="«getUrl»" id="«getCategoryId»" version="«calculateVersion(getVersion, isStrictVersion)»">
					<category name="«category»"/>
				</feature>
			«ENDFOR»
		«ENDIF»
    '''
	
	def private List<String> calculateCategory(List<Category> allCategories, List<String> assignedLocationCategories, List<String> assignedUnitCategories) {
		if (assignedUnitCategories.empty) {
			if(assignedLocationCategories.empty) {
				val defaultCategory = allCategories.findFirst[it.defaultCategory]
				if(defaultCategory !== null) #[defaultCategory.name] else #[this.defaultCategory]  
			} else {
				assignedLocationCategories
			}
		} else {
			assignedUnitCategories
		}
	}
    
	def calculateVersion(String version, boolean strictVersion) {
		if(!strictVersion) "0.0.0" else version
	}
	
    def generateFeatureXml() '''
	   <includes
	         id="«targetId»"
	         version="0.0.0"/>
    '''
}