package de.abg.jreichert.repositorytarget.dsl.formatting;

import org.xpect.setup.ISetupInitializer;
import org.xpect.xtext.lib.setup.XtextStandaloneSetup;

public class XtextStandaloneSetupWithoutValidate extends XtextStandaloneSetup {

	@Override
	public Config beforeFile(IFileSetupContext frameworkCtx, ClassCtx userCtx, ISetupInitializer<Config> initializer) throws Exception {
		Config ctx = new Config();
		new Defaults(initializer).initialize(ctx);
		loadThisResource(frameworkCtx.getInjector(), frameworkCtx, ctx);
		return ctx;
	}
}
