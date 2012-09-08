package de.abg.jreichert.repositorytarget.definition

class TodoTargetDefinition extends AbstractTargetDefinition {
	
	override targetDefinition() { 
		val target = new Target => [
 			name = "Xtext TODO Platform - Eclipse 4.2"
 			locations += createLocation [
  				repositoryLocation = "http://download.itemis.com/updates/releases/2.0.0/"
  				units += createUnit [
					categoryId = "de.itemis.xtext.antlr.sdk"
  				]
  				units += createUnit [
					categoryId = "de.itemis.xtext.antlr.feature"
  				]
  			]
  			locations += createLocation [
  				repositoryLocation = "http://download.eclipse.org/technology/swtbot/helios/dev-build/update-site"
  				units += createUnit [
					categoryId = "org.eclipse.swtbot.eclipse"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.swtbot.eclipse.gef"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.swtbot.eclipse.test.junit4"
  				]
  			]
  			locations += createLocation [
  				repositoryLocation = "http://download.eclipse.org/releases/juno"
  				units += createUnit [
					categoryId = "org.eclipse.sdk"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.pde"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.emf.sdk"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.swt"
					feature = false
  				]
  			]
  			locations += createLocation [
  				repositoryLocation = "http://download.itemis.de/updates/"
  				units += createUnit [
					categoryId = "org.eclipse.emf.mwe2.language.sdk"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.emf.mwe2.runtime.sdk"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.xtext.sdk"
  				]
  				units += createUnit [
					categoryId = "org.eclipse.xtend.sdk"
  				]
  			]
  			locations += createLocation [
  				repositoryLocation = "http://xtext-utils.eclipselabs.org.codespot.com/git.distribution/releases/unittesting-0.9.x"
  				units += createUnit [
					categoryId = "org.eclipselabs.xtext.utils.unittesting.feature"
  				]
  			]
		]
		target
	}
}