package de.abg.jreichert.repositorytarget.dsl.validation

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.dsl.TargetDefinitionInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(XtextRunner)
@InjectWith(TargetDefinitionInjectorProvider)
class TargetDefinitionValidationUnitTest {
	@Inject TargetDefinitionValidator validator

	@Test
	def void testTokenize() {
		Assert.assertEquals("2, \\., 10", validator.tokenize("2.10").join(", "))
	}

	@Test
	def void testSort() {
		Assert.assertEquals("2.2, 2.10", validator.sortVersions(#["2.10", "2.2"]).join(", "))
		Assert.assertEquals("2.2-SNAPSHOT, 2.10.qualifier",
			validator.sortVersions(#["2.2-SNAPSHOT", "2.10.qualifier"]).join(", "))
		Assert.assertEquals("2.2, 2.a", validator.sortVersions(#["2.a", "2.2"]).join(", "))
		Assert.assertEquals("2.a-5, 2.a-10", validator.sortVersions(#["2.a-10", "2.a-5"]).join(", "))
		Assert.assertEquals("0.9, 1", validator.sortVersions(#["1", "0.9"]).join(", "))
		Assert.assertEquals("0.9, 1", validator.sortVersions(#["0.9", "1"]).join(", "))
		versionIsGreater("0.0.0.2", "0.0.0.1");
		versionIsGreater("1.0", "0.9");
		versionIsGreater("2.0.1", "2.0.0");
		versionIsGreater("2.0.1", "2.0");
		versionIsGreater("2.0.1", "2");
		Assert.assertEquals("2, 2.0.1", validator.sortVersions(#["2.0.1", "2"]).join(", "))
		versionIsGreater("0.9.1", "0.9.0");
		versionIsGreater("0.9.2", "0.9.1");
		versionIsGreater("0.9.11", "0.9.2");
		versionIsGreater("0.9.12", "0.9.11");
		versionIsGreater("0.10", "0.9");
		versionIsGreater("2.0", "2.0b35");
		versionIsGreater("1.10.3", "1.10.3b3");
		versionIsGreater("1.10.3", "1.10.3B3");
		versionIsGreater("88", "88a12");
		versionIsGreater("0.0.33", "0.0.33rc23");
		Assert.assertEquals("reversed", "0.0.33rc23, 0.0.33", validator.sortVersions(#["0.0.33", "0.0.33rc23"]).join(", "))
		Assert.assertEquals("0.0.33rc23, 0.0.33", validator.sortVersions(#["0.0.33rc23", "0.0.33"]).join(", "))
		versionIsGreater("0.0.33", "0.0.33RC23");
		versionIsGreater("2.0b1", "2.0a2");
	}
	
	def private versionIsGreater(String... strings) {
		Assert.assertEquals("in given order", strings.reverse.join(", "), validator.sortVersions(strings).join(", "))
	}
}
