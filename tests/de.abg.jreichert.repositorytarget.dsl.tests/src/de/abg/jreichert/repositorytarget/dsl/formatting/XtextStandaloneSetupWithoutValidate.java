package de.abg.jreichert.repositorytarget.dsl.formatting;

import org.eclipse.emf.ecore.resource.Resource;
import org.xpect.xtext.lib.setup.FileCtx;
import org.xpect.xtext.lib.setup.XtextStandaloneSetup;

public class XtextStandaloneSetupWithoutValidate extends XtextStandaloneSetup {

	@Override
	protected void validate(FileCtx ctx, Resource resource) {
		// don't validate here
	}
}
