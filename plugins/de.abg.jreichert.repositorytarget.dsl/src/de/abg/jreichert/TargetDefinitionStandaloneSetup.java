
package de.abg.jreichert;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class TargetDefinitionStandaloneSetup extends TargetDefinitionStandaloneSetupGenerated{

	public static void doSetup() {
		new TargetDefinitionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

