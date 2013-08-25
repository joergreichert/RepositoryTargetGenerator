package de.abg.jreichert.repositorytarget.database;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {
	private static Activator singleton;
	
	public static Activator getDefault() {
		return singleton;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		singleton = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		singleton = null;
		super.stop(context);
	}
}
