package de.abg.jreichert.repositorytarget.dsl.logic

import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import java.util.Map
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.SubProgressMonitor

class ReadOutP2Repository {

	def execute(String repositoryLocation, ContentXmlHandler contentHandler, IProgressMonitor monitor) {
		monitor.beginTask("Read out P2 repository metadata", 2)
		monitor.subTask("Fetch contents")
		val parser = new ContentJarParser()
		try {
			parser.beginSession
			fetchContent(parser, repositoryLocation, contentHandler, monitor)			
			monitor.done()
		} finally {
			parser.endSession
		}
	}
	
	private def fetchContent(ContentJarParser parser, String repositoryLocation, ContentXmlHandler contentHandler, IProgressMonitor monitor) {
		val contents = parser.getContents(repositoryLocation, contentHandler)
		monitor.worked(1)
		monitor.subTask("Parse contents (this could take up to one minute)")
		val subProgressMonitor = new SubProgressMonitor(monitor, 1)
		subProgressMonitor.beginTask("Read out P2 repository metadata", contents.size())
		var int i = 0
		for (Map.Entry<String, String> entry : contents.entries) {
			subProgressMonitor.subTask("Parsing file " + i)
			try {
				contentHandler.setUrl(entry.key)
				parser.parse(entry.value, contentHandler)
				parser.save(entry.key, contentHandler)
			} catch(Exception e) {
				e.printStackTrace
			}
			subProgressMonitor.worked(i)
			i = i + 1
		}
		parser.save(repositoryLocation, contentHandler)
	}
}
