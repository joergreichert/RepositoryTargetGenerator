package de.abg.jreichert.repositorytarget.database;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @startuml
 * class AbstractBundleRevision
 * AbstractBundleRevision <|-- Branch
 * AbstractBundleRevision --> AbstractBundleRevision : directlyUsed
 * class BranchRevision {
 * 	String tagName
 * }
 * Branch *--> BranchRevision : revision
 * Branch --> Bundle : parentBudle
 * AbstractBundleRevision <|-- BranchRevision
 * 
 * Bundle --> AbstractBundleRevision : abstractRevision 
 * note on link : this is a shorcut that gets revision from all branches
 * Bundle *--> Branch : branch
 * Bundle *--> RepositoryResource : repository
 * 
 * @enduml
 * @param rev
 * @param overrideMap
 * @return
 * @throws AmbiguousConfigurationException
 */
@SuppressWarnings("javadoc")
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
