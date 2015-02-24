package de.abg.jreichert.repositorytarget.dsl.ui.contentassist

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.validation.VersionSorter
import org.eclipse.jface.text.contentassist.ICompletionProposal
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalComparator

class VersionAwareCompletionProposalComparator extends ICompletionProposalComparator.NaturalOrder {
   @Inject VersionSorter versionSorter
   
   override compare(ICompletionProposal o1, ICompletionProposal o2) {
      o1.internalCompare(o2)
   }
   
   def dispatch internalCompare(ConfigurableCompletionProposal o1, ConfigurableCompletionProposal o2) {
      val result = o1.priority.compareTo(o2.priority)
      if(result == 0) {
         if(o1.priority == TargetDefinitionProposalProvider.VERSION_PRIORITY) {
            -versionSorter.compareVersions(#[o1.displayString], #[o2.displayString])
         } else {
           super.compare(o1, o2) 
         }
      } else {
         if(o1.priority == TargetDefinitionProposalProvider.VERSION_PRIORITY || 
            o2.priority == TargetDefinitionProposalProvider.VERSION_PRIORITY) {
            -result
         } else {
            result
         } 
      }
   }
   
   def dispatch internalCompare(ICompletionProposal o1, ICompletionProposal o2) {
      super.compare(o1, o2) 
   }
   
   // work around for https://bugs.eclipse.org/bugs/show_bug.cgi?id=460691
   override equals(Object obj) {
      super.equals(obj)
   }
}