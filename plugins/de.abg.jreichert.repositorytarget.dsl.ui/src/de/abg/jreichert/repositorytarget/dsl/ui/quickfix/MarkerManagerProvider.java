/**
 *
 */
package de.abg.jreichert.repositorytarget.dsl.ui.quickfix;

import org.eclipse.core.internal.resources.MarkerManager;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * @author Reichert
 *
 */
@SuppressWarnings("restriction")
public class MarkerManagerProvider {

	@SuppressWarnings("javadoc")
	public static MarkerManager getMarkerManager() {
		return ((Workspace) ResourcesPlugin.getWorkspace()).getMarkerManager();
	}
}
