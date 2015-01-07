/*
 * generated by Xtext
 */
package de.abg.jreichert.repositorytarget.dsl.ui.contentassist

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Location
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.TargetDefinitionPackage
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Unit
import de.abg.jreichert.repositorytarget.dsl.ui.internal.TargetDefinitionActivator
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import java.lang.reflect.InvocationTargetException
import java.util.SortedMap
import java.util.SortedSet
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.dialogs.ProgressMonitorDialog
import org.eclipse.jface.operation.IRunnableWithProgress
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.statushandlers.StatusManager
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
class TargetDefinitionProposalProvider extends AbstractTargetDefinitionProposalProvider {
   public static int VERSION_PRIORITY = 600

   @Inject
   private TerminalsProposalProvider terminalsProposalProvider

   @Inject
   private ReadOutP2Repository readOutP2Repository

   private static String FEATURE_GROUP = ".feature.group"

   private val urlToCategoryIdsToVersions = <String, SortedMap<String, SortedSet<String>>>newHashMap

   override complete_URL(EObject model, RuleCall ruleCall, ContentAssistContext context,
      ICompletionProposalAcceptor acceptor) {
      val exampleUrl = "http://www.example.org/p2"
      val displayString = new StyledString(exampleUrl)
      val proposal = doCreateProposal(exampleUrl, displayString, null, 0, context)
      acceptor.accept(proposal)
   }

   override completeUnit_CategoryId(EObject model, Assignment assignment, ContentAssistContext context,
      ICompletionProposalAcceptor acceptor) {
      val location = getLocation(model)
      if (location !== null) {
         val repositoryLocation = location.getRepositoryLocation()
         if (repositoryLocation !== null) {
            val existingCategoryId = NodeModelUtils.findNodesForFeature(location,
               TargetDefinitionPackage.Literals.LOCATION__UNIT).filter[it.offset + text.trim.length == context.offset].
               last?.text?.trim
            try {
               val locationToLocation = fill(repositoryLocation)
               propose(locationToLocation, repositoryLocation) [
                  String givenLocation | proposeCategories(givenLocation, existingCategoryId, context, acceptor)
               ]
            } catch (Exception exception) {
               val status = new Status(IStatus::ERROR,
                  TargetDefinitionActivator.instance.bundle.symbolicName,
                  "Exception while parsing the content.xml", exception)
               StatusManager::getManager().handle(status, StatusManager::LOG.bitwiseOr(StatusManager::SHOW))
            }
         }
      }
   }
   
   def private void propose(Multimap<String, String> locationToLocation, String repositoryLocation, (String) => void proposeFunction) {
      val normalizedLocation = repositoryLocation.normalize
      proposeFunction.apply(normalizedLocation)
      val subLocations = locationToLocation.get(normalizedLocation)
      for(subLocation : subLocations) {
         propose(locationToLocation, subLocation, proposeFunction)
      }
   }

   def private proposeCategories(String repositoryLocation, String existingCategoryId,
      ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
      val categoryIdsToVersions = urlToCategoryIdsToVersions.get(repositoryLocation.normalize)
      if (categoryIdsToVersions !== null) {
         val ids = categoryIdsToVersions.keySet()
         val filteredIds = if (existingCategoryId === null)
               ids.filter[length == replaceAll("\\s", "").length]
            else {
               ids.filterWhitespaceContainingStrings.filterStringThatStartsWith(existingCategoryId)
            }
         for (String id : filteredIds) {
            val displayString = new StyledString(id)
            val index = id.indexOf(".feature.group")
            val proposalString = if (index > 0) {
                  id.replace(".feature.group", "")
               } else {
                  id + " noFeature "
               }
            val proposal = doCreateProposal(proposalString, displayString, null, 600, context)
            acceptor.accept(proposal)
         }
      }
   }
   
   def normalize(String repositoryLocation) {
      repositoryLocation + (if(repositoryLocation.endsWith("/")) "" else "/")
   }

   def private Location getLocation(EObject model) {
      return getType(model, typeof(Location))
   }

   def private <T> T getType(EObject model, Class<T> clazz) {
      var T type = null
      if (clazz.isAssignableFrom(model.getClass())) {
         type = model as T
      }
      return type
   }

   def private Multimap<String, String> fill(String repositoryLocation) throws InvocationTargetException, InterruptedException {
      if (repositoryLocation !== null) {
         if (urlToCategoryIdsToVersions.get(repositoryLocation.normalize) === null) {
            val contentHandler = new ContentXmlHandler(repositoryLocation)
            val runnable = new ReadOutP2RepositoryRunnable(repositoryLocation, contentHandler, readOutP2Repository)
            val shell = Display::getDefault().getActiveShell()
            val dialog = new ProgressMonitorDialog(shell)
            dialog.run(true, true, runnable)
            urlToCategoryIdsToVersions.putAll(contentHandler.getUrlToIdToVersion())
            return contentHandler.locationToLocation
         }
      }
      return ArrayListMultimap.create
   }

   override completeUnit_Version(EObject model, Assignment assignment, ContentAssistContext context,
      ICompletionProposalAcceptor acceptor) {
      val Unit unit = getUnit(model)
      if (unit !== null) {
         val repositoryLocation = getRepositoryLocation(unit)
         if (repositoryLocation !== null) {
            var existingCategoryId = unit.getCategoryId()
            if (existingCategoryId !== null) {
               val categoryId = if (unit.isNoFeature() || existingCategoryId.endsWith(FEATURE_GROUP))
                  existingCategoryId
               else
                  existingCategoryId + ".feature.group"
               val existingVersion = NodeModelUtils.findNodesForFeature(unit,
                  TargetDefinitionPackage.Literals.UNIT__VERSION).last?.text
               try {
                  fill(repositoryLocation)
                  val locationToLocation = fill(repositoryLocation)
                  propose(locationToLocation, repositoryLocation) [
                     String givenLocation | proposeVersions(givenLocation, categoryId, existingVersion, context, acceptor)
                  ]
               } catch (Exception exception) {
                  val status = new Status(IStatus::ERROR,
                     TargetDefinitionActivator.instance.bundle.symbolicName,
                     "Exception while parsing the content.xml", exception)
                  StatusManager::getManager().handle(status,
                     StatusManager::LOG.bitwiseOr(StatusManager::SHOW))
               }
            }
         }
      }
   }
   
   def private void proposeVersions(String repositoryLocation, String categoryId, String existingVersion, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
      val versions = urlToCategoryIdsToVersions.get(repositoryLocation)?.get(categoryId)
      if (versions !== null) {
         val filteredVersions = if (existingVersion === null)
               versions.filterWhitespaceContainingStrings
            else {
               versions.filterWhitespaceContainingStrings.
                  filterStringThatStartsWith(existingVersion)
            }
         for (String version : filteredVersions) {
            if (version !== null) {
               val displayString = new StyledString(version)
               val proposal = doCreateProposal(version, displayString, null, VERSION_PRIORITY, context)
               acceptor.accept(proposal)
            }
         }
      }
   }

   def private filterWhitespaceContainingStrings(Iterable<String> strings) {
      strings.filter[length == replaceAll("\\s", "").length]
   }

   def private filterStringThatStartsWith(Iterable<String> strings, String start) {
      strings.filter[startsWith(start)]
   }

   def private Unit getUnit(EObject model) {
      return getType(model, typeof(Unit))
   }

   def private String getRepositoryLocation(Unit unit) {
      var String repositoryLocation = null
      val location = unit.eContainer() as Location
      if (location !== null) {
         repositoryLocation = location.getRepositoryLocation()
      }
      return repositoryLocation
   }

   override complete_STRING(EObject model, RuleCall ruleCall, ContentAssistContext context,
      ICompletionProposalAcceptor acceptor) {
      terminalsProposalProvider.complete_STRING(model, ruleCall, context, acceptor)
   }

   override complete_ID(EObject model, RuleCall ruleCall, ContentAssistContext context,
      ICompletionProposalAcceptor acceptor) {
      if (EcoreUtil2::getContainerOfType(model, typeof(Location)) === null) {
         terminalsProposalProvider.complete_ID(model, ruleCall, context, acceptor)
      } else {
         super.complete_ID(model, ruleCall, context, acceptor)
      }
   }
}

@Accessors
class ReadOutP2RepositoryRunnable implements IRunnableWithProgress {
   private val String repositoryLocation
   private val ContentXmlHandler contentHandler
   private val ReadOutP2Repository readOutP2Repository

   override run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
      try {
         readOutP2Repository.execute(repositoryLocation, contentHandler, monitor)
      } catch (IllegalArgumentException iae) {
         val status = new Status(IStatus::ERROR, TargetDefinitionActivator.instance.bundle.symbolicName,
            iae.message, iae)
         StatusManager::getManager().handle(status, StatusManager::LOG.bitwiseOr(StatusManager::SHOW))
      } catch (OutOfMemoryError ooe) {
         val status = new Status(IStatus::ERROR, TargetDefinitionActivator.instance.bundle.symbolicName,
            "Out of memory: Please start your Eclipse with something like -Xmx1024m -Xms1024m -XX:MaxPermSize=512m",
            ooe)
         StatusManager::getManager().handle(status, StatusManager::LOG.bitwiseOr(StatusManager::SHOW))
      }
   }
}
