package de.abg.jreichert.repositorytarget.dsl.validation

import org.junit.runner.RunWith
import org.xpect.runner.XpectRunner
import org.xpect.runner.XpectTestFiles
import org.xpect.setup.XpectSetup
import org.xpect.runner.XpectTestFiles.FileRoot
import org.xpect.xtext.lib.setup.XtextStandaloneSetup
import org.xpect.xtext.lib.tests.ValidationTest

@RunWith(XpectRunner)
@XpectTestFiles(relativeTo = FileRoot.CURRENT, baseDir = "model/testcases/validation", fileExtensions = "targetdef")
@XpectSetup(XtextStandaloneSetup)
class TargetDefinitionValidationTest extends ValidationTest {
	
}