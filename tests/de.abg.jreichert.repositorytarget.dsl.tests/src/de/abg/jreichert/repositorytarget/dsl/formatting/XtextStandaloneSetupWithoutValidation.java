package de.abg.jreichert.repositorytarget.dsl.formatting;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xpect.Environment;
import org.xpect.parameter.XpectParameterAdapter;
import org.xpect.setup.ThisRootTestClass;
import org.xpect.setup.XpectSetup;
import org.xpect.state.Creates;
import org.xpect.state.Precondition;
import org.xpect.util.EnvironmentUtil;
import org.xpect.xtext.lib.setup.FileSetupContext;
import org.xpect.xtext.lib.setup.InjectorSetup;
import org.xpect.xtext.lib.setup.JvmModelInferrerRegistryFix;
import org.xpect.xtext.lib.setup.ThisResource;
import org.xpect.xtext.lib.setup.XtextTestObjectSetup;
import org.xpect.xtext.lib.setup.XtextValidatingSetup;
import org.xpect.xtext.lib.setup.emf.ResourceFactory;
import org.xpect.xtext.lib.util.XtextOffsetAdapter;
import org.xpect.xtext.lib.util.XtextTargetSyntaxSupport;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import de.abg.jreichert.repositorytarget.dsl.formatting.XtextStandaloneSetupWithoutValidation.NullValidatorSetup;

@XpectParameterAdapter(XtextOffsetAdapter.class)
@XpectSetup({ XtextTargetSyntaxSupport.class, XtextTestObjectSetup.class, InjectorSetup.class, NullValidatorSetup.class })
public class XtextStandaloneSetupWithoutValidation  {

	public static class NullValidatorSetup extends XtextValidatingSetup {
		public NullValidatorSetup(@ThisResource XtextResource resource) {
			super(resource);
		}

		@Override
		public void validate() {
			// do nothing
		}
	}
	
	@Precondition
	public static void checkApplicable() {
		EnvironmentUtil.requireEnvironment(Environment.STANDALONE_TEST);
	}

	private final FileSetupContext ctx;
	private final org.xpect.xtext.lib.setup.emf.ResourceSet resourceSetConfig;

	@Inject
	private Provider<ResourceSet> resourceSetProvider;

	public XtextStandaloneSetupWithoutValidation(FileSetupContext ctx, org.xpect.xtext.lib.setup.emf.ResourceSet resourceSet, Injector injector) {
		super();
		this.resourceSetConfig = resourceSet;
		this.ctx = ctx;
		JvmModelInferrerRegistryFix.apply();
		injector.injectMembers(this);
	}

	@Creates(ThisResource.class)
	public XtextResource createThisResource() throws IOException {
		ResourceSet resourceSet = resourceSetProvider.get();
		if (resourceSet instanceof XtextResourceSet)
			((XtextResourceSet) resourceSet).setClasspathURIContext(ctx.get(Class.class, ThisRootTestClass.class));
		Resource result = null;
		for (ResourceFactory file : resourceSetConfig.getFactories()) {
			Resource res = file.create(ctx, resourceSet);
			if (file instanceof org.xpect.xtext.lib.setup.emf.ThisResource)
				result = res;
		}
		return (XtextResource) result;
	}

	protected org.xpect.xtext.lib.setup.emf.ResourceSet getResourceSetConfig() {
		return resourceSetConfig;
	}

	protected FileSetupContext getSetupContext() {
		return ctx;
	}
}
