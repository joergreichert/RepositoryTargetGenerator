/*
 * generated by Xtext
 */
package de.abg.jreichert.repositorytarget.dsl.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalComparator;

import de.abg.jreichert.repositorytarget.dsl.ui.contentassist.VersionAwareCompletionProposalComparator;

/**
 * Use this class to register components to be used within the IDE.
 */
public class TargetDefinitionUiModule extends de.abg.jreichert.repositorytarget.dsl.ui.AbstractTargetDefinitionUiModule {

	@SuppressWarnings("javadoc")
	public TargetDefinitionUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	@SuppressWarnings("javadoc")
	public Class<? extends ICompletionProposalComparator> bindICompletionProposalComparator() {
		return VersionAwareCompletionProposalComparator.class;
	}
}
