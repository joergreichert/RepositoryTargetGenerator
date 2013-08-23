package de.abg.jreichert.repositorytarget.definition

import java.util.List

class Target {
   @Property private String name
   @Property private List<Category> categories = newArrayList
   @Property private List<Location> locations = newArrayList
  
   def createLocation((Location) => void initializer) {
      val location = new Location
      initializer.apply(location)
      location
   }
   
	def generateTarget() '''
		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<?pde version="3.6"?>

		<target includeMode="feature" name="«name»" sequenceNumber="67">
			<locations>
				«locations.map[generateTarget].join»
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
			«locations.map[generateCategoryXml(categories)].join»
			«categories.map[generateCategoryXml].join»
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
		
		   «locations.map[generateFeatureXml].join»
		
		</feature>
	'''

	def generateDistoBuilderXML() '''
	'''
}


class Category {
	@Property String name 
	@Property String longName 
	@Property String description 
	@Property boolean defaultCategory

	def generateCategoryXml() '''
		<category-def name="«name»" «IF !longName.nullOrEmpty»label="«longName»"«ENDIF»>
			«IF !description.nullOrEmpty»
				<description>«description»</description>
			«ENDIF»
		</category-def>
	'''	
}


class Location {
   @Property private List<Unit> units = newArrayList
   @Property private String repositoryLocation
   @Property private List<String> assignedLocationCategories = newArrayList
   @Property boolean strictVersion = false
	
   def createUnit((Unit) => void initializer) {
      val unit = new Unit()
      initializer.apply(unit)
      unit
   }
   
	def generateTarget() '''
		<location includeAllPlatforms="false" includeMode="planner" includeSource="true" type="InstallableUnit">
			«units.map[generateTarget].join»
			<repository location="«repositoryLocation»"/>
		</location>
	'''
	
	def generateCategoryXml(List<Category> allCategories) '''
		«units.map[it.generateCategoryXml(allCategories, assignedLocationCategories, this.strictVersion)].join»
	''' 
	
	def generateFeatureXml() '''
		«units.map[generateFeatureXml].join»
	'''
}

class Unit {
	@Property private String categoryId = ""
	@Property private String targetId = ""
	@Property private String version = ""
	@Property private String url = ""
	@Property private List<String> assignedUnitCategories = newArrayList
	@Property private Boolean feature = true
	@Property private Boolean includeInCategoryXml = true
	@Property private Boolean includeInTarget = true
	@Property boolean strictVersion = false
	private String defaultCategory = "3rdparty"
	
	def String getTargetId() {
		if(_targetId.nullOrEmpty && !_categoryId.nullOrEmpty && !_categoryId.endsWith("feature.group") && feature)
           _targetId = _categoryId + ".feature.group"
		else _targetId	
	}

	def String getCategoryId() {
		if(!_targetId.nullOrEmpty && _categoryId.nullOrEmpty)
			_categoryId = _targetId.replace(".feature.group", "") 
		else _categoryId	
	}
	
	def String getUrl() {
		if(!categoryId.nullOrEmpty && !version.nullOrEmpty)
			"features/" + categoryId + "_" + calculateVersion(version, strictVersion) + ".jar"
		else ""	
	}
	
	def generateTarget() '''
		«IF includeInTarget»
			<unit id="«targetId»" version="«version»"/>
		«ENDIF»
	'''
	
	def generateCategoryXml(List<Category> allCategories, List<String> assignedLocationCategories, boolean strictVersion) '''
		«this.strictVersion = strictVersion»
		«IF includeInCategoryXml»
			«FOR category : calculateCategory(allCategories, assignedLocationCategories, assignedUnitCategories)»
				<feature url="«url»" id="«categoryId»" version="«calculateVersion(version, this.strictVersion)»">
					<category name="«category»"/>
				</feature>
			«ENDFOR»
		«ENDIF»
    '''
	
	def private List<String> calculateCategory(List<Category> allCategories, List<String> assignedLocationCategories, List<String> assignedUnitCategories) {
		if (assignedUnitCategories.empty) {
			if(assignedLocationCategories.empty) {
				val defaultCategory = allCategories.findFirst[it.defaultCategory]
				if(defaultCategory != null) #[defaultCategory.name] else #[this.defaultCategory]  
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