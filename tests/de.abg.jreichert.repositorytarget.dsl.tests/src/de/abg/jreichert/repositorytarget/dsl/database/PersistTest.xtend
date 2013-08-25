package de.abg.jreichert.repositorytarget.dsl.database

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.TargetDefinitionInjectorProvider
import de.abg.jreichert.repositorytarget.dsl.targetDefinition.Target
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(TargetDefinitionInjectorProvider)
class PersistTest {
	@Inject extension ParseHelper<Target> 
	
	@Test
	def void testPersist() {
	}
}
