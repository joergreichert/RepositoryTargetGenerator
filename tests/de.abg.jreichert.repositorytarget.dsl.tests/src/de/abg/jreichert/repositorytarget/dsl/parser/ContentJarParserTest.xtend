package de.abg.jreichert.repositorytarget.dsl.parser

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.database.LocationManager
import de.abg.jreichert.repositorytarget.dsl.TargetDefinitionInjectorProvider
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(TargetDefinitionInjectorProvider)
class ContentJarParserTest {
	@Inject LocationManager locationManager

	@Test
	def void testParsingLocal() {
		val contentJarParser = new ContentJarParser() 
      	val contentHandler = new ContentXmlHandler;
		val result = contentJarParser.getContents("file://testdata/updatesite/", contentHandler)
	}
	
	@Test
	@Ignore
	def void testParsingRemoteComposite() {
		val contentJarParser = new ContentJarParser() 
      	val contentHandler = new ContentXmlHandler;
		val result = contentJarParser.getContents("http://download.eclipse.org/releases/kepler", contentHandler)
	}
	
	@Test
	def void testParsingContent() {
		val contentHandler = new ContentXmlHandler()
		val monitor = new NullProgressMonitor
		val readOutP2Repository = new ReadOutP2Repository 
		val url = "file://testdata/updatesite/"
		readOutP2Repository.execute(url, contentHandler, monitor)
		val idVersionPairs = contentHandler.idToVersion
	}
}