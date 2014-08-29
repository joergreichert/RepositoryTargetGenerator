package de.abg.jreichert.repositorytarget.dsl.generator

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.TargetDefinitionInjectorProvider
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.xtext.generator.InMemoryFileSystemAccess
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.emf.common.util.URI

import static org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(TargetDefinitionInjectorProvider)
class GeneratorTest {
	@Inject extension ParseHelper<Target> parserHelper
	@Inject extension IGenerator generator
	
	@Test
	def void testGenerate() {
		assertNotNull("parserHelper should not be null", parserHelper)
		assertNotNull("generator should not be null", generator)
		val target = testData.parse
		val rs = new XtextResourceSet
		val res = rs.createResource(URI.createFileURI("dummy.targetdef"))
		res.contents.add(target)
		val filesystemAccess = new InMemoryFileSystemAccess
		doGenerate(res, filesystemAccess)
		
		val categoryXmlFilePath = "DEFAULT_OUTPUTtestdata/category.xml"
		val featureXmlFilePath = "DEFAULT_OUTPUTtestdata/feature.xml"
		val targetFilePath = "DEFAULT_OUTPUTtestdata/testdata.target"
		
		assertTrue("should have generated category.xml", 
			filesystemAccess.allFiles.containsKey(categoryXmlFilePath)
		) 
		assertTrue("should have generated feature.xml", 
			filesystemAccess.allFiles.containsKey(featureXmlFilePath)
		) 
		assertTrue("should have generated testdata.target",
			filesystemAccess.allFiles.containsKey(targetFilePath)
		) 

		assertEquals("should have generated expected category.xml content", 
			expectedCategoryXmlContent.toString,
			filesystemAccess.allFiles.get(categoryXmlFilePath).toString
		) 
		assertEquals("should have generated expected feature.xml content", 
			expectedFeatureXmlContent.toString,
			filesystemAccess.allFiles.get(featureXmlFilePath).toString
		) 
		assertEquals("should have generated expected testdata.target content",
			expectedTargetContent.toString,
			filesystemAccess.allFiles.get(targetFilePath).toString
		) 
	}
	
	def protected testData() '''
		TargetDefinition "Example"
		
			targetFile = testdata
		
			"Composite" file://testdata/updatesite/ {
				de.abg.jreichert.repositorytarget.feature 0.1.0.201304242052
			}
	'''
	
	def protected expectedCategoryXmlContent() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<site>
			<feature url="features/de.abg.jreichert.repositorytarget.feature_0.0.0.jar" id="de.abg.jreichert.repositorytarget.feature" version="0.0.0">
				<category name="3rdparty"/>
			</feature>
		</site>
	''' 
	
	def protected expectedFeatureXmlContent() '''
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
		
		   <includes
		         id="de.abg.jreichert.repositorytarget.feature.feature.group"
		         version="0.0.0"/>
		
		</feature>
	'''
	
	def protected expectedTargetContent() '''
		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<?pde version="3.6"?>
		
		<target includeMode="feature" name="Example" sequenceNumber="67">
			<locations>
				<location includeAllPlatforms="false" includeMode="planner" includeSource="true" type="InstallableUnit">
					<unit id="de.abg.jreichert.repositorytarget.feature.feature.group" version="0.1.0.201304242052"/>
					<repository location="file:///testdata/updatesite/"/>
				</location>
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
}