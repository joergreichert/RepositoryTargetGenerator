package de.abg.jreichert.repositorytarget.dsl.logic

import org.eclipse.core.runtime.IProgressMonitor
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import org.eclipse.core.runtime.SubProgressMonitor

class ReadOutP2Repository {

	def execute(String repositoryLocation, ContentXmlHandler contentHandler, IProgressMonitor monitor) {
		monitor.beginTask("Read out P2 repository metadata", 2)
		monitor.subTask("Fetch contents")
		val parser = new ContentJarParser()
		try {
			parser.beginSession
			val contents = parser.getContents(repositoryLocation, contentHandler)
			monitor.worked(1)
			monitor.subTask("Parse contents (this could take up to one minute)")
			val subProgressMonitor = new SubProgressMonitor(monitor, 1)
			subProgressMonitor.beginTask("Read out P2 repository metadata", contents.size())
			var int i = 0
			for (String content : contents) {
				subProgressMonitor.subTask("Parsing file " + i)
				parser.parse(content, contentHandler)
				subProgressMonitor.worked(i)
				i = i + 1
			}
			parser.save(repositoryLocation, contentHandler)
			monitor.done()
		} finally {
			parser.endSession
		}
	}
}
