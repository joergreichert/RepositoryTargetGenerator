/*
* generated by Xtext
*/
package de.abg.jreichert.repositorytarget.dsl.ui.quickfix

import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit
import de.abg.jreichert.repositorytarget.dsl.ui.internal.TargetDefinitionActivator
import de.abg.jreichert.repositorytarget.dsl.validation.TargetDefinitionValidator
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue

/**
 * Custom quickfixes.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#quickfixes
 */
class TargetDefinitionQuickfixProvider extends DefaultQuickfixProvider {

	@Fix(TargetDefinitionValidator::NOT_UPTODATE)
	def fixVersion(Issue issue, IssueResolutionAcceptor acceptor) {
		val newVersion = issue.data.last
		acceptor.accept(issue, 'Update version to ' + newVersion, 'Updates current version to ' + newVersion, null) [
			element, context |
			if(element instanceof Unit) {
				element.version = newVersion
				removeMarker(element, issue)
			}
		]
	}
	
	def private removeMarker(EObject eObject, Issue issue) {
		val file = ResourcesPlugin.getWorkspace().getRoot().getFile(
			new Path(URI.decode(eObject.eResource.URI.toPlatformString(true)))
		);
		if(file.exists) {
			val markerManager = MarkerManagerProvider.getMarkerManager();
			val markerList = <IMarker>newArrayList
			markerManager.doFindMarkers(file, markerList, 
				TargetDefinitionActivator.instance.bundle.symbolicName + ".targetdefinition.check.expensive", 
				true, IResource.DEPTH_INFINITE
			)
			val markers = markerList.filter[try {
				it.getAttribute(IMarker.CHAR_START) == issue.offset
			} catch (CoreException exc) {
				throw new RuntimeException(exc)
			}] 
			markers.forEach[try {
				delete
			} catch (CoreException exc) {
				throw new RuntimeException("auto-generated try/catch", exc)
			}]
		} else {
			System::err.println(file + " does not exist.")
		}
	}
	
	@Fix(TargetDefinitionValidator::DEPRECATED_FEATURE_ORDER)
	def fixNoFeatureOrder(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Update position', 'Plaves noFeature between categoryId and version', null) [
			context |
				val xtextDocument = context.getXtextDocument();
				xtextDocument.replace(issue.offset, issue.length, issue.data.last);
		]
	}
}
