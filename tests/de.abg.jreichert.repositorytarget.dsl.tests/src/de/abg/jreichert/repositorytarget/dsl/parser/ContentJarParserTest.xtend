package de.abg.jreichert.repositorytarget.dsl.parser

import com.google.inject.Inject
import de.abg.jreichert.repositorytarget.database.LocationManager
import de.abg.jreichert.repositorytarget.dsl.TargetDefinitionInjectorProvider
import de.abg.jreichert.repositorytarget.dsl.logic.ReadOutP2Repository
import de.abg.jreichert.repositorytarget.xml.ContentJarParser
import de.abg.jreichert.repositorytarget.xml.ContentXmlHandler
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import de.abg.jreichert.repositorytarget.database.SessionManager

@RunWith(XtextRunner)
@InjectWith(TargetDefinitionInjectorProvider)
class ContentJarParserTest {
	@Inject LocationManager locationManager
	
	@Before
	def setUp() {
		locationManager.clearAll
		SessionManager.closeSession;
	}

	@Test
	def void testParsingLocal() {
		val url = "file://testdata/updatesite/"
		val contentJarParser = new ContentJarParser()
		val contentHandler = new ContentXmlHandler(url);
		val urlResult = contentJarParser.getContents(url, contentHandler)
		assertEquals("expected url count", 2, urlResult.size)
		assertEquals("expected content count", 2, urlResult.size)
		val keys = urlResult.keySet.toList
		assertEquals("expected URL at pos 1", "jar:file:testdata/updatesite/de.abg.jreichert.repositorytarget.updatesite-0.1.0.201211111542.zip!/", keys.get(0))
		assertEquals("expected URL at pos 2", "file://testdata/updatesite/de.abg.jreichert.repositorytarget.updatesite-0.1.0.201204242252/", keys.get(1))
		assertEquals("expected content count at pos 1", content_1.toString.replace("\r\n", "\n"), urlResult.get(keys.get(0)).toString)
		assertEquals("expected content count at pos 2", content_2.toString.replace("\r\n", "\n"), urlResult.get(keys.get(1)).toString)
	}
	
	def content_1() '''
		<?xml version='1.0' encoding='UTF-8'?>
		<?metadataRepository version='1.1.0'?>
		<repository name='de.abg.jreichert.repositorytarget.updatesite' type='org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository' version='1'>
		  <properties size='2'>
		    <property name='p2.timestamp' value='1352648845772'/>
		    <property name='p2.compressed' value='true'/>
		  </properties>
		  <units size='6'>
		    <unit id='201211111547.repositorytarget' version='1.0.0.01-cBzFg67735755555CAG'>
		      <properties size='2'>
		        <property name='org.eclipse.equinox.p2.name' value='Repository Target Generator'/>
		        <property name='org.eclipse.equinox.p2.type.category' value='true'/>
		      </properties>
		      <provides size='1'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='201211111547.repositorytarget' version='1.0.0.01-cBzFg67735755555CAG'/>
		      </provides>
		      <requires size='1'>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.group' range='[0.1.0.201211111547,0.1.0.201211111547]'/>
		      </requires>
		      <touchpoint id='null' version='0.0.0'/>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201211111542'>
		      <update id='de.abg.jreichert.repositorytarget.dsl' range='[0.0.0,0.1.0.201211111542)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.dsl'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.dsl'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='15'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201211111542'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201211111542'/>
		        <provided namespace='java.package' name='de.abg.jreichert' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.services' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition.impl' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition.util' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.serializer' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.parser.antlr' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.parser.antlr.internal' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.validation' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.scoping' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.generator' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.formatting' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='15'>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.xbase' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.generator' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.apache.commons.logging' range='1.0.4' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.codegen.ecore' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.mwe.utils' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.mwe2.launch' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.util' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.ecore' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.common' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.antlr.runtime' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.common.types' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='0.1.0'/>
		        <required namespace='java.package' name='org.apache.log4j' range='0.0.0'/>
		        <required namespace='java.package' name='org.eclipse.xtext.xbase.lib' range='0.0.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201211111542'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.dsl; singleton:=true&#xA;Bundle-Version: 0.1.0.201211111542
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.feature.feature.jar' version='0.1.0.201211111547'>
		      <properties size='11'>
		        <property name='org.eclipse.equinox.p2.name' value='%featureName'/>
		        <property name='org.eclipse.equinox.p2.description' value='%description'/>
		        <property name='org.eclipse.equinox.p2.provider' value='%providerName'/>
		        <property name='df_LT.featureName' value='Repository Target Generator'/>
		        <property name='df_LT.providerName' value='Joerg Reichert'/>
		        <property name='df_LT.description' value=''/>
		        <property name='df_LT.copyright' value='Copyright (c) 2012.&#xA;All rights reserved. This program and the accompanying materials&#xA;are made available under the terms of the Eclipse Public License v1.0&#xA;which accompanies this distribution, and is available at&#xA;http://www.eclipse.org/legal/epl-v10.html&#xA;&#xA;Contributors:&#xA;Joerg Reichert - initial API and implementation'/>
		        <property name='df_LT.license' value='Eclipse Foundation Software User Agreement&#xA;February 1, 2011&#xA;&#xA;Usage Of Content&#xA;&#xA;THE ECLIPSE FOUNDATION MAKES AVAILABLE SOFTWARE, DOCUMENTATION, INFORMATION AND/OR&#xA;OTHER MATERIALS FOR OPEN SOURCE PROJECTS (COLLECTIVELY &quot;CONTENT&quot;).&#xA;USE OF THE CONTENT IS GOVERNED BY THE TERMS AND CONDITIONS OF THIS&#xA;AGREEMENT AND/OR THE TERMS AND CONDITIONS OF LICENSE AGREEMENTS OR&#xA;NOTICES INDICATED OR REFERENCED BELOW.  BY USING THE CONTENT, YOU&#xA;AGREE THAT YOUR USE OF THE CONTENT IS GOVERNED BY THIS AGREEMENT&#xA;AND/OR THE TERMS AND CONDITIONS OF ANY APPLICABLE LICENSE AGREEMENTS&#xA;OR NOTICES INDICATED OR REFERENCED BELOW.  IF YOU DO NOT AGREE TO THE&#xA;TERMS AND CONDITIONS OF THIS AGREEMENT AND THE TERMS AND CONDITIONS&#xA;OF ANY APPLICABLE LICENSE AGREEMENTS OR NOTICES INDICATED OR REFERENCED&#xA;BELOW, THEN YOU MAY NOT USE THE CONTENT.&#xA;&#xA;Applicable Licenses&#xA;&#xA;Unless otherwise indicated, all Content made available by the&#xA;Eclipse Foundation is provided to you under the terms and conditions of&#xA;the Eclipse Public License Version 1.0 (&quot;EPL&quot;). A copy of the EPL is&#xA;provided with this Content and is also available at http://www.eclipse.org/legal/epl-v10.html.&#xA;For purposes of the EPL, &quot;Program&quot; will mean the Content.&#xA;&#xA;Content includes, but is not limited to, source code, object code,&#xA;documentation and other files maintained in the Eclipse Foundation source code&#xA;repository (&quot;Repository&quot;) in software modules (&quot;Modules&quot;) and made available&#xA;as downloadable archives (&quot;Downloads&quot;).&#xA;&#xA;- Content may be structured and packaged into modules to facilitate delivering,&#xA;extending, and upgrading the Content. Typical modules may include plug-ins (&quot;Plug-ins&quot;),&#xA;plug-in fragments (&quot;Fragments&quot;), and features (&quot;Features&quot;).&#xA;- Each Plug-in or Fragment may be packaged as a sub-directory or JAR (Java(TM) ARchive)&#xA;in a directory named &quot;plugins&quot;.&#xA;- A Feature is a bundle of one or more Plug-ins and/or Fragments and associated material.&#xA;Each Feature may be packaged as a sub-directory in a directory named &quot;features&quot;.&#xA;Within a Feature, files named &quot;feature.xml&quot; may contain a list of the names and version&#xA;numbers of the Plug-ins and/or Fragments associated with that Feature.&#xA;- Features may also include other Features (&quot;Included Features&quot;). Within a Feature, files&#xA;named &quot;feature.xml&quot; may contain a list of the names and version numbers of Included Features.&#xA;&#xA;The terms and conditions governing Plug-ins and Fragments should be&#xA;contained in files named &quot;about.html&quot; (&quot;Abouts&quot;). The terms and&#xA;conditions governing Features and Included Features should be contained&#xA;in files named &quot;license.html&quot; (&quot;Feature Licenses&quot;). Abouts and Feature&#xA;Licenses may be located in any directory of a Download or Module&#xA;including, but not limited to the following locations:&#xA;&#xA;- The top-level (root) directory&#xA;- Plug-in and Fragment directories&#xA;- Inside Plug-ins and Fragments packaged as JARs&#xA;- Sub-directories of the directory named &quot;src&quot; of certain Plug-ins&#xA;- Feature directories&#xA;&#xA;Note: if a Feature made available by the Eclipse Foundation is installed using the&#xA;Provisioning Technology (as defined below), you must agree to a license (&quot;Feature &#xA;Update License&quot;) during the installation process. If the Feature contains&#xA;Included Features, the Feature Update License should either provide you&#xA;with the terms and conditions governing the Included Features or inform&#xA;you where you can locate them. Feature Update Licenses may be found in&#xA;the &quot;license&quot; property of files named &quot;feature.properties&quot; found within a Feature.&#xA;Such Abouts, Feature Licenses, and Feature Update Licenses contain the&#xA;terms and conditions (or references to such terms and conditions) that&#xA;govern your use of the associated Content in that directory.&#xA;&#xA;THE ABOUTS, FEATURE LICENSES, AND FEATURE UPDATE LICENSES MAY REFER&#xA;TO THE EPL OR OTHER LICENSE AGREEMENTS, NOTICES OR TERMS AND CONDITIONS.&#xA;SOME OF THESE OTHER LICENSE AGREEMENTS MAY INCLUDE (BUT ARE NOT LIMITED TO):&#xA;&#xA;- Eclipse Distribution License Version 1.0 (available at http://www.eclipse.org/licenses/edl-v1.0.html)&#xA;- Common Public License Version 1.0 (available at http://www.eclipse.org/legal/cpl-v10.html)&#xA;- Apache Software License 1.1 (available at http://www.apache.org/licenses/LICENSE)&#xA;- Apache Software License 2.0 (available at http://www.apache.org/licenses/LICENSE-2.0)&#xA;- Metro Link Public License 1.00 (available at http://www.opengroup.org/openmotif/supporters/metrolink/license.html)&#xA;- Mozilla Public License Version 1.1 (available at http://www.mozilla.org/MPL/MPL-1.1.html)&#xA;&#xA;IT IS YOUR OBLIGATION TO READ AND ACCEPT ALL SUCH TERMS AND CONDITIONS PRIOR&#xA;TO USE OF THE CONTENT. If no About, Feature License, or Feature Update License&#xA;is provided, please contact the Eclipse Foundation to determine what terms and conditions&#xA;govern that particular Content.&#xA;&#xA;&#xA;Use of Provisioning Technology&#xA;&#xA;The Eclipse Foundation makes available provisioning software, examples of which include,&#xA;but are not limited to, p2 and the Eclipse Update Manager (&quot;Provisioning Technology&quot;) for&#xA;the purpose of allowing users to install software, documentation, information and/or&#xA;other materials (collectively &quot;Installable Software&quot;). This capability is provided with&#xA;the intent of allowing such users to install, extend and update Eclipse-based products.&#xA;Information about packaging Installable Software is available at&#xA;http://eclipse.org/equinox/p2/repository_packaging.html (&quot;Specification&quot;).&#xA;&#xA;You may use Provisioning Technology to allow other parties to install Installable Software.&#xA;You shall be responsible for enabling the applicable license agreements relating to the&#xA;Installable Software to be presented to, and accepted by, the users of the Provisioning Technology&#xA;in accordance with the Specification. By using Provisioning Technology in such a manner and&#xA;making it available in accordance with the Specification, you further acknowledge your&#xA;agreement to, and the acquisition of all necessary rights to permit the following:&#xA;&#xA;1. A series of actions may occur (&quot;Provisioning Process&quot;) in which a user may execute&#xA;the Provisioning Technology on a machine (&quot;Target Machine&quot;) with the intent of installing,&#xA;extending or updating the functionality of an Eclipse-based product.&#xA;2. During the Provisioning Process, the Provisioning Technology may cause third party&#xA;Installable Software or a portion thereof to be accessed and copied to the Target Machine.&#xA;3. Pursuant to the Specification, you will provide to the user the terms and conditions that&#xA;govern the use of the Installable Software (&quot;Installable Software Agreement&quot;) and such&#xA;Installable Software Agreement shall be accessed from the Target Machine in accordance&#xA;with the Specification. Such Installable Software Agreement must inform the user of the&#xA;terms and conditions that govern the Installable Software and must solicit acceptance by&#xA;the end user in the manner prescribed in such Installable Software Agreement. Upon such&#xA;indication of agreement by the user, the provisioning Technology will complete installation&#xA;of the Installable Software.&#xA;&#xA;Cryptography&#xA;&#xA;Content may contain encryption software. The country in which you are&#xA;currently may have restrictions on the import, possession, and use,&#xA;and/or re-export to another country, of encryption software. BEFORE&#xA;using any encryption software, please check the country&apos;s laws,&#xA;regulations and policies concerning the import, possession, or use, and&#xA;re-export of encryption software, to see if this is permitted.&#xA;&#xA;Java and all Java-based trademarks are trademarks of Oracle Corporation in the United States, other countries, or both.'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.feature'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='3'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.jar' version='0.1.0.201211111547'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='feature' version='1.0.0'/>
		        <provided namespace='org.eclipse.update.feature' name='de.abg.jreichert.repositorytarget.feature' version='0.1.0.201211111547'/>
		      </provides>
		      <filter>
		        (org.eclipse.update.install.features=true)
		      </filter>
		      <artifacts size='1'>
		        <artifact classifier='org.eclipse.update.feature' id='de.abg.jreichert.repositorytarget.feature' version='0.1.0.201211111547'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='zipped'>
		            true
		          </instruction>
		        </instructions>
		      </touchpointData>
		      <licenses size='1'>
		        <license uri='%25licenseURL' url='%25licenseURL'>
		          %license
		        </license>
		      </licenses>
		      <copyright>
		        %copyright
		      </copyright>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201211111542' singleton='false'>
		      <update id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='[0.0.0,0.1.0.201211111542)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.repositorytargetgenerator'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.repositorytargetgenerator'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='6'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201211111542'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201211111542'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget.definition' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget.xml' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='4'>
		        <required namespace='osgi.bundle' name='org.eclipse.xtend.lib' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.generator' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ecore' range='2.3.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201211111542'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.repositorytargetgenerator&#xA;Bundle-Version: 0.1.0.201211111542
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201211111542'>
		      <update id='de.abg.jreichert.repositorytarget.dsl.ui' range='[0.0.0,0.1.0.201211111542)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.dsl.ui'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.dsl.ui'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='6'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201211111542'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201211111542'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.contentassist' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.contentassist.antlr' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.internal' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='14'>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl' range='0.1.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui.editors' range='3.5.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui.ide' range='3.5.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui.shared' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.builder' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.antlr.runtime' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.common.types.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui.codetemplates.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.compare' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='0.1.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.xbase.lib' range='2.3.1'/>
		        <required namespace='java.package' name='org.apache.log4j' range='0.0.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201211111542'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.dsl.ui; singleton:=true&#xA;Bundle-Version: 0.1.0.201211111542
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.feature.feature.group' version='0.1.0.201211111547' singleton='false'>
		      <update id='de.abg.jreichert.repositorytarget.feature.feature.group' range='[0.0.0,0.1.0.201211111547)' severity='0'/>
		      <properties size='12'>
		        <property name='org.eclipse.equinox.p2.name' value='%featureName'/>
		        <property name='org.eclipse.equinox.p2.description' value='%description'/>
		        <property name='org.eclipse.equinox.p2.provider' value='%providerName'/>
		        <property name='org.eclipse.equinox.p2.type.group' value='true'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.feature'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		        <property name='df_LT.featureName' value='Repository Target Generator'/>
		        <property name='df_LT.providerName' value='Joerg Reichert'/>
		        <property name='df_LT.description' value=''/>
		        <property name='df_LT.copyright' value='Copyright (c) 2012.&#xA;All rights reserved. This program and the accompanying materials&#xA;are made available under the terms of the Eclipse Public License v1.0&#xA;which accompanies this distribution, and is available at&#xA;http://www.eclipse.org/legal/epl-v10.html&#xA;&#xA;Contributors:&#xA;Joerg Reichert - initial API and implementation'/>
		        <property name='df_LT.license' value='Eclipse Foundation Software User Agreement&#xA;February 1, 2011&#xA;&#xA;Usage Of Content&#xA;&#xA;THE ECLIPSE FOUNDATION MAKES AVAILABLE SOFTWARE, DOCUMENTATION, INFORMATION AND/OR&#xA;OTHER MATERIALS FOR OPEN SOURCE PROJECTS (COLLECTIVELY &quot;CONTENT&quot;).&#xA;USE OF THE CONTENT IS GOVERNED BY THE TERMS AND CONDITIONS OF THIS&#xA;AGREEMENT AND/OR THE TERMS AND CONDITIONS OF LICENSE AGREEMENTS OR&#xA;NOTICES INDICATED OR REFERENCED BELOW.  BY USING THE CONTENT, YOU&#xA;AGREE THAT YOUR USE OF THE CONTENT IS GOVERNED BY THIS AGREEMENT&#xA;AND/OR THE TERMS AND CONDITIONS OF ANY APPLICABLE LICENSE AGREEMENTS&#xA;OR NOTICES INDICATED OR REFERENCED BELOW.  IF YOU DO NOT AGREE TO THE&#xA;TERMS AND CONDITIONS OF THIS AGREEMENT AND THE TERMS AND CONDITIONS&#xA;OF ANY APPLICABLE LICENSE AGREEMENTS OR NOTICES INDICATED OR REFERENCED&#xA;BELOW, THEN YOU MAY NOT USE THE CONTENT.&#xA;&#xA;Applicable Licenses&#xA;&#xA;Unless otherwise indicated, all Content made available by the&#xA;Eclipse Foundation is provided to you under the terms and conditions of&#xA;the Eclipse Public License Version 1.0 (&quot;EPL&quot;). A copy of the EPL is&#xA;provided with this Content and is also available at http://www.eclipse.org/legal/epl-v10.html.&#xA;For purposes of the EPL, &quot;Program&quot; will mean the Content.&#xA;&#xA;Content includes, but is not limited to, source code, object code,&#xA;documentation and other files maintained in the Eclipse Foundation source code&#xA;repository (&quot;Repository&quot;) in software modules (&quot;Modules&quot;) and made available&#xA;as downloadable archives (&quot;Downloads&quot;).&#xA;&#xA;- Content may be structured and packaged into modules to facilitate delivering,&#xA;extending, and upgrading the Content. Typical modules may include plug-ins (&quot;Plug-ins&quot;),&#xA;plug-in fragments (&quot;Fragments&quot;), and features (&quot;Features&quot;).&#xA;- Each Plug-in or Fragment may be packaged as a sub-directory or JAR (Java(TM) ARchive)&#xA;in a directory named &quot;plugins&quot;.&#xA;- A Feature is a bundle of one or more Plug-ins and/or Fragments and associated material.&#xA;Each Feature may be packaged as a sub-directory in a directory named &quot;features&quot;.&#xA;Within a Feature, files named &quot;feature.xml&quot; may contain a list of the names and version&#xA;numbers of the Plug-ins and/or Fragments associated with that Feature.&#xA;- Features may also include other Features (&quot;Included Features&quot;). Within a Feature, files&#xA;named &quot;feature.xml&quot; may contain a list of the names and version numbers of Included Features.&#xA;&#xA;The terms and conditions governing Plug-ins and Fragments should be&#xA;contained in files named &quot;about.html&quot; (&quot;Abouts&quot;). The terms and&#xA;conditions governing Features and Included Features should be contained&#xA;in files named &quot;license.html&quot; (&quot;Feature Licenses&quot;). Abouts and Feature&#xA;Licenses may be located in any directory of a Download or Module&#xA;including, but not limited to the following locations:&#xA;&#xA;- The top-level (root) directory&#xA;- Plug-in and Fragment directories&#xA;- Inside Plug-ins and Fragments packaged as JARs&#xA;- Sub-directories of the directory named &quot;src&quot; of certain Plug-ins&#xA;- Feature directories&#xA;&#xA;Note: if a Feature made available by the Eclipse Foundation is installed using the&#xA;Provisioning Technology (as defined below), you must agree to a license (&quot;Feature &#xA;Update License&quot;) during the installation process. If the Feature contains&#xA;Included Features, the Feature Update License should either provide you&#xA;with the terms and conditions governing the Included Features or inform&#xA;you where you can locate them. Feature Update Licenses may be found in&#xA;the &quot;license&quot; property of files named &quot;feature.properties&quot; found within a Feature.&#xA;Such Abouts, Feature Licenses, and Feature Update Licenses contain the&#xA;terms and conditions (or references to such terms and conditions) that&#xA;govern your use of the associated Content in that directory.&#xA;&#xA;THE ABOUTS, FEATURE LICENSES, AND FEATURE UPDATE LICENSES MAY REFER&#xA;TO THE EPL OR OTHER LICENSE AGREEMENTS, NOTICES OR TERMS AND CONDITIONS.&#xA;SOME OF THESE OTHER LICENSE AGREEMENTS MAY INCLUDE (BUT ARE NOT LIMITED TO):&#xA;&#xA;- Eclipse Distribution License Version 1.0 (available at http://www.eclipse.org/licenses/edl-v1.0.html)&#xA;- Common Public License Version 1.0 (available at http://www.eclipse.org/legal/cpl-v10.html)&#xA;- Apache Software License 1.1 (available at http://www.apache.org/licenses/LICENSE)&#xA;- Apache Software License 2.0 (available at http://www.apache.org/licenses/LICENSE-2.0)&#xA;- Metro Link Public License 1.00 (available at http://www.opengroup.org/openmotif/supporters/metrolink/license.html)&#xA;- Mozilla Public License Version 1.1 (available at http://www.mozilla.org/MPL/MPL-1.1.html)&#xA;&#xA;IT IS YOUR OBLIGATION TO READ AND ACCEPT ALL SUCH TERMS AND CONDITIONS PRIOR&#xA;TO USE OF THE CONTENT. If no About, Feature License, or Feature Update License&#xA;is provided, please contact the Eclipse Foundation to determine what terms and conditions&#xA;govern that particular Content.&#xA;&#xA;&#xA;Use of Provisioning Technology&#xA;&#xA;The Eclipse Foundation makes available provisioning software, examples of which include,&#xA;but are not limited to, p2 and the Eclipse Update Manager (&quot;Provisioning Technology&quot;) for&#xA;the purpose of allowing users to install software, documentation, information and/or&#xA;other materials (collectively &quot;Installable Software&quot;). This capability is provided with&#xA;the intent of allowing such users to install, extend and update Eclipse-based products.&#xA;Information about packaging Installable Software is available at&#xA;http://eclipse.org/equinox/p2/repository_packaging.html (&quot;Specification&quot;).&#xA;&#xA;You may use Provisioning Technology to allow other parties to install Installable Software.&#xA;You shall be responsible for enabling the applicable license agreements relating to the&#xA;Installable Software to be presented to, and accepted by, the users of the Provisioning Technology&#xA;in accordance with the Specification. By using Provisioning Technology in such a manner and&#xA;making it available in accordance with the Specification, you further acknowledge your&#xA;agreement to, and the acquisition of all necessary rights to permit the following:&#xA;&#xA;1. A series of actions may occur (&quot;Provisioning Process&quot;) in which a user may execute&#xA;the Provisioning Technology on a machine (&quot;Target Machine&quot;) with the intent of installing,&#xA;extending or updating the functionality of an Eclipse-based product.&#xA;2. During the Provisioning Process, the Provisioning Technology may cause third party&#xA;Installable Software or a portion thereof to be accessed and copied to the Target Machine.&#xA;3. Pursuant to the Specification, you will provide to the user the terms and conditions that&#xA;govern the use of the Installable Software (&quot;Installable Software Agreement&quot;) and such&#xA;Installable Software Agreement shall be accessed from the Target Machine in accordance&#xA;with the Specification. Such Installable Software Agreement must inform the user of the&#xA;terms and conditions that govern the Installable Software and must solicit acceptance by&#xA;the end user in the manner prescribed in such Installable Software Agreement. Upon such&#xA;indication of agreement by the user, the provisioning Technology will complete installation&#xA;of the Installable Software.&#xA;&#xA;Cryptography&#xA;&#xA;Content may contain encryption software. The country in which you are&#xA;currently may have restrictions on the import, possession, and use,&#xA;and/or re-export to another country, of encryption software. BEFORE&#xA;using any encryption software, please check the country&apos;s laws,&#xA;regulations and policies concerning the import, possession, or use, and&#xA;re-export of encryption software, to see if this is permitted.&#xA;&#xA;Java and all Java-based trademarks are trademarks of Oracle Corporation in the United States, other countries, or both.'/>
		      </properties>
		      <provides size='2'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.group' version='0.1.0.201211111547'/>
		        <provided namespace='org.eclipse.equinox.p2.localization' name='df_LT' version='1.0.0'/>
		      </provides>
		      <requires size='4'>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl' range='[0.1.0.201211111542,0.1.0.201211111542]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl.ui' range='[0.1.0.201211111542,0.1.0.201211111542]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='[0.1.0.201211111542,0.1.0.201211111542]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.jar' range='[0.1.0.201211111547,0.1.0.201211111547]'>
		          <filter>
		            (org.eclipse.update.install.features=true)
		          </filter>
		        </required>
		      </requires>
		      <touchpoint id='null' version='0.0.0'/>
		      <licenses size='1'>
		        <license uri='%25licenseURL' url='%25licenseURL'>
		          %license
		        </license>
		      </licenses>
		      <copyright>
		        %copyright
		      </copyright>
		    </unit>
		  </units>
		</repository>'''

	def content_2() '''
		<?xml version='1.0' encoding='UTF-8'?>
		<?metadataRepository version='1.1.0'?>
		<repository name='de.abg.jreichert.repositorytarget.updatesite' type='org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository' version='1'>
		  <properties size='2'>
		    <property name='p2.timestamp' value='1366836741506'/>
		    <property name='p2.compressed' value='true'/>
		  </properties>
		  <units size='6'>
		    <unit id='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201304242049'>
		      <update id='de.abg.jreichert.repositorytarget.dsl.ui' range='[0.0.0,0.1.0.201304242049)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.dsl.ui'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.dsl.ui'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='6'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201304242049'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201304242049'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.contentassist' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.contentassist.antlr' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.ui.internal' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='14'>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl' range='0.1.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui.editors' range='3.5.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui.ide' range='3.5.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui.shared' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.builder' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.antlr.runtime' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.common.types.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ui.codetemplates.ui' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.compare' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='0.1.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.xbase.lib' range='2.3.1'/>
		        <required namespace='java.package' name='org.apache.log4j' range='0.0.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.dsl.ui' version='0.1.0.201304242049'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.dsl.ui; singleton:=true&#xA;Bundle-Version: 0.1.0.201304242049
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201304242049' singleton='false'>
		      <update id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='[0.0.0,0.1.0.201304242049)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.repositorytargetgenerator'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.repositorytargetgenerator'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='6'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201304242049'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201304242049'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget.definition' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.repositorytarget.xml' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='4'>
		        <required namespace='osgi.bundle' name='org.eclipse.xtend.lib' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.generator' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext' range='2.3.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.ecore' range='2.3.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.repositorytargetgenerator' version='0.1.0.201304242049'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.repositorytargetgenerator&#xA;Bundle-Version: 0.1.0.201304242049
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.feature.feature.jar' version='0.1.0.201304242052'>
		      <properties size='11'>
		        <property name='org.eclipse.equinox.p2.name' value='%featureName'/>
		        <property name='org.eclipse.equinox.p2.description' value='%description'/>
		        <property name='org.eclipse.equinox.p2.provider' value='%providerName'/>
		        <property name='df_LT.featureName' value='Repository Target Generator'/>
		        <property name='df_LT.providerName' value='Joerg Reichert'/>
		        <property name='df_LT.description' value=''/>
		        <property name='df_LT.copyright' value='Copyright (c) 2012.&#xA;All rights reserved. This program and the accompanying materials&#xA;are made available under the terms of the Eclipse Public License v1.0&#xA;which accompanies this distribution, and is available at&#xA;http://www.eclipse.org/legal/epl-v10.html&#xA;&#xA;Contributors:&#xA;Joerg Reichert - initial API and implementation'/>
		        <property name='df_LT.license' value='Eclipse Foundation Software User Agreement&#xA;February 1, 2011&#xA;&#xA;Usage Of Content&#xA;&#xA;THE ECLIPSE FOUNDATION MAKES AVAILABLE SOFTWARE, DOCUMENTATION, INFORMATION AND/OR&#xA;OTHER MATERIALS FOR OPEN SOURCE PROJECTS (COLLECTIVELY &quot;CONTENT&quot;).&#xA;USE OF THE CONTENT IS GOVERNED BY THE TERMS AND CONDITIONS OF THIS&#xA;AGREEMENT AND/OR THE TERMS AND CONDITIONS OF LICENSE AGREEMENTS OR&#xA;NOTICES INDICATED OR REFERENCED BELOW.  BY USING THE CONTENT, YOU&#xA;AGREE THAT YOUR USE OF THE CONTENT IS GOVERNED BY THIS AGREEMENT&#xA;AND/OR THE TERMS AND CONDITIONS OF ANY APPLICABLE LICENSE AGREEMENTS&#xA;OR NOTICES INDICATED OR REFERENCED BELOW.  IF YOU DO NOT AGREE TO THE&#xA;TERMS AND CONDITIONS OF THIS AGREEMENT AND THE TERMS AND CONDITIONS&#xA;OF ANY APPLICABLE LICENSE AGREEMENTS OR NOTICES INDICATED OR REFERENCED&#xA;BELOW, THEN YOU MAY NOT USE THE CONTENT.&#xA;&#xA;Applicable Licenses&#xA;&#xA;Unless otherwise indicated, all Content made available by the&#xA;Eclipse Foundation is provided to you under the terms and conditions of&#xA;the Eclipse Public License Version 1.0 (&quot;EPL&quot;). A copy of the EPL is&#xA;provided with this Content and is also available at http://www.eclipse.org/legal/epl-v10.html.&#xA;For purposes of the EPL, &quot;Program&quot; will mean the Content.&#xA;&#xA;Content includes, but is not limited to, source code, object code,&#xA;documentation and other files maintained in the Eclipse Foundation source code&#xA;repository (&quot;Repository&quot;) in software modules (&quot;Modules&quot;) and made available&#xA;as downloadable archives (&quot;Downloads&quot;).&#xA;&#xA;- Content may be structured and packaged into modules to facilitate delivering,&#xA;extending, and upgrading the Content. Typical modules may include plug-ins (&quot;Plug-ins&quot;),&#xA;plug-in fragments (&quot;Fragments&quot;), and features (&quot;Features&quot;).&#xA;- Each Plug-in or Fragment may be packaged as a sub-directory or JAR (Java(TM) ARchive)&#xA;in a directory named &quot;plugins&quot;.&#xA;- A Feature is a bundle of one or more Plug-ins and/or Fragments and associated material.&#xA;Each Feature may be packaged as a sub-directory in a directory named &quot;features&quot;.&#xA;Within a Feature, files named &quot;feature.xml&quot; may contain a list of the names and version&#xA;numbers of the Plug-ins and/or Fragments associated with that Feature.&#xA;- Features may also include other Features (&quot;Included Features&quot;). Within a Feature, files&#xA;named &quot;feature.xml&quot; may contain a list of the names and version numbers of Included Features.&#xA;&#xA;The terms and conditions governing Plug-ins and Fragments should be&#xA;contained in files named &quot;about.html&quot; (&quot;Abouts&quot;). The terms and&#xA;conditions governing Features and Included Features should be contained&#xA;in files named &quot;license.html&quot; (&quot;Feature Licenses&quot;). Abouts and Feature&#xA;Licenses may be located in any directory of a Download or Module&#xA;including, but not limited to the following locations:&#xA;&#xA;- The top-level (root) directory&#xA;- Plug-in and Fragment directories&#xA;- Inside Plug-ins and Fragments packaged as JARs&#xA;- Sub-directories of the directory named &quot;src&quot; of certain Plug-ins&#xA;- Feature directories&#xA;&#xA;Note: if a Feature made available by the Eclipse Foundation is installed using the&#xA;Provisioning Technology (as defined below), you must agree to a license (&quot;Feature &#xA;Update License&quot;) during the installation process. If the Feature contains&#xA;Included Features, the Feature Update License should either provide you&#xA;with the terms and conditions governing the Included Features or inform&#xA;you where you can locate them. Feature Update Licenses may be found in&#xA;the &quot;license&quot; property of files named &quot;feature.properties&quot; found within a Feature.&#xA;Such Abouts, Feature Licenses, and Feature Update Licenses contain the&#xA;terms and conditions (or references to such terms and conditions) that&#xA;govern your use of the associated Content in that directory.&#xA;&#xA;THE ABOUTS, FEATURE LICENSES, AND FEATURE UPDATE LICENSES MAY REFER&#xA;TO THE EPL OR OTHER LICENSE AGREEMENTS, NOTICES OR TERMS AND CONDITIONS.&#xA;SOME OF THESE OTHER LICENSE AGREEMENTS MAY INCLUDE (BUT ARE NOT LIMITED TO):&#xA;&#xA;- Eclipse Distribution License Version 1.0 (available at http://www.eclipse.org/licenses/edl-v1.0.html)&#xA;- Common Public License Version 1.0 (available at http://www.eclipse.org/legal/cpl-v10.html)&#xA;- Apache Software License 1.1 (available at http://www.apache.org/licenses/LICENSE)&#xA;- Apache Software License 2.0 (available at http://www.apache.org/licenses/LICENSE-2.0)&#xA;- Metro Link Public License 1.00 (available at http://www.opengroup.org/openmotif/supporters/metrolink/license.html)&#xA;- Mozilla Public License Version 1.1 (available at http://www.mozilla.org/MPL/MPL-1.1.html)&#xA;&#xA;IT IS YOUR OBLIGATION TO READ AND ACCEPT ALL SUCH TERMS AND CONDITIONS PRIOR&#xA;TO USE OF THE CONTENT. If no About, Feature License, or Feature Update License&#xA;is provided, please contact the Eclipse Foundation to determine what terms and conditions&#xA;govern that particular Content.&#xA;&#xA;&#xA;Use of Provisioning Technology&#xA;&#xA;The Eclipse Foundation makes available provisioning software, examples of which include,&#xA;but are not limited to, p2 and the Eclipse Update Manager (&quot;Provisioning Technology&quot;) for&#xA;the purpose of allowing users to install software, documentation, information and/or&#xA;other materials (collectively &quot;Installable Software&quot;). This capability is provided with&#xA;the intent of allowing such users to install, extend and update Eclipse-based products.&#xA;Information about packaging Installable Software is available at&#xA;http://eclipse.org/equinox/p2/repository_packaging.html (&quot;Specification&quot;).&#xA;&#xA;You may use Provisioning Technology to allow other parties to install Installable Software.&#xA;You shall be responsible for enabling the applicable license agreements relating to the&#xA;Installable Software to be presented to, and accepted by, the users of the Provisioning Technology&#xA;in accordance with the Specification. By using Provisioning Technology in such a manner and&#xA;making it available in accordance with the Specification, you further acknowledge your&#xA;agreement to, and the acquisition of all necessary rights to permit the following:&#xA;&#xA;1. A series of actions may occur (&quot;Provisioning Process&quot;) in which a user may execute&#xA;the Provisioning Technology on a machine (&quot;Target Machine&quot;) with the intent of installing,&#xA;extending or updating the functionality of an Eclipse-based product.&#xA;2. During the Provisioning Process, the Provisioning Technology may cause third party&#xA;Installable Software or a portion thereof to be accessed and copied to the Target Machine.&#xA;3. Pursuant to the Specification, you will provide to the user the terms and conditions that&#xA;govern the use of the Installable Software (&quot;Installable Software Agreement&quot;) and such&#xA;Installable Software Agreement shall be accessed from the Target Machine in accordance&#xA;with the Specification. Such Installable Software Agreement must inform the user of the&#xA;terms and conditions that govern the Installable Software and must solicit acceptance by&#xA;the end user in the manner prescribed in such Installable Software Agreement. Upon such&#xA;indication of agreement by the user, the provisioning Technology will complete installation&#xA;of the Installable Software.&#xA;&#xA;Cryptography&#xA;&#xA;Content may contain encryption software. The country in which you are&#xA;currently may have restrictions on the import, possession, and use,&#xA;and/or re-export to another country, of encryption software. BEFORE&#xA;using any encryption software, please check the country&apos;s laws,&#xA;regulations and policies concerning the import, possession, or use, and&#xA;re-export of encryption software, to see if this is permitted.&#xA;&#xA;Java and all Java-based trademarks are trademarks of Oracle Corporation in the United States, other countries, or both.'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.feature'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='3'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.jar' version='0.1.0.201304242052'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='feature' version='1.0.0'/>
		        <provided namespace='org.eclipse.update.feature' name='de.abg.jreichert.repositorytarget.feature' version='0.1.0.201304242052'/>
		      </provides>
		      <filter>
		        (org.eclipse.update.install.features=true)
		      </filter>
		      <artifacts size='1'>
		        <artifact classifier='org.eclipse.update.feature' id='de.abg.jreichert.repositorytarget.feature' version='0.1.0.201304242052'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='zipped'>
		            true
		          </instruction>
		        </instructions>
		      </touchpointData>
		      <licenses size='1'>
		        <license uri='%25licenseURL' url='%25licenseURL'>
		          %license
		        </license>
		      </licenses>
		      <copyright>
		        %copyright
		      </copyright>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.feature.feature.group' version='0.1.0.201304242052' singleton='false'>
		      <update id='de.abg.jreichert.repositorytarget.feature.feature.group' range='[0.0.0,0.1.0.201304242052)' severity='0'/>
		      <properties size='12'>
		        <property name='org.eclipse.equinox.p2.name' value='%featureName'/>
		        <property name='org.eclipse.equinox.p2.description' value='%description'/>
		        <property name='org.eclipse.equinox.p2.provider' value='%providerName'/>
		        <property name='org.eclipse.equinox.p2.type.group' value='true'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.feature'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		        <property name='df_LT.featureName' value='Repository Target Generator'/>
		        <property name='df_LT.providerName' value='Joerg Reichert'/>
		        <property name='df_LT.description' value=''/>
		        <property name='df_LT.copyright' value='Copyright (c) 2012.&#xA;All rights reserved. This program and the accompanying materials&#xA;are made available under the terms of the Eclipse Public License v1.0&#xA;which accompanies this distribution, and is available at&#xA;http://www.eclipse.org/legal/epl-v10.html&#xA;&#xA;Contributors:&#xA;Joerg Reichert - initial API and implementation'/>
		        <property name='df_LT.license' value='Eclipse Foundation Software User Agreement&#xA;February 1, 2011&#xA;&#xA;Usage Of Content&#xA;&#xA;THE ECLIPSE FOUNDATION MAKES AVAILABLE SOFTWARE, DOCUMENTATION, INFORMATION AND/OR&#xA;OTHER MATERIALS FOR OPEN SOURCE PROJECTS (COLLECTIVELY &quot;CONTENT&quot;).&#xA;USE OF THE CONTENT IS GOVERNED BY THE TERMS AND CONDITIONS OF THIS&#xA;AGREEMENT AND/OR THE TERMS AND CONDITIONS OF LICENSE AGREEMENTS OR&#xA;NOTICES INDICATED OR REFERENCED BELOW.  BY USING THE CONTENT, YOU&#xA;AGREE THAT YOUR USE OF THE CONTENT IS GOVERNED BY THIS AGREEMENT&#xA;AND/OR THE TERMS AND CONDITIONS OF ANY APPLICABLE LICENSE AGREEMENTS&#xA;OR NOTICES INDICATED OR REFERENCED BELOW.  IF YOU DO NOT AGREE TO THE&#xA;TERMS AND CONDITIONS OF THIS AGREEMENT AND THE TERMS AND CONDITIONS&#xA;OF ANY APPLICABLE LICENSE AGREEMENTS OR NOTICES INDICATED OR REFERENCED&#xA;BELOW, THEN YOU MAY NOT USE THE CONTENT.&#xA;&#xA;Applicable Licenses&#xA;&#xA;Unless otherwise indicated, all Content made available by the&#xA;Eclipse Foundation is provided to you under the terms and conditions of&#xA;the Eclipse Public License Version 1.0 (&quot;EPL&quot;). A copy of the EPL is&#xA;provided with this Content and is also available at http://www.eclipse.org/legal/epl-v10.html.&#xA;For purposes of the EPL, &quot;Program&quot; will mean the Content.&#xA;&#xA;Content includes, but is not limited to, source code, object code,&#xA;documentation and other files maintained in the Eclipse Foundation source code&#xA;repository (&quot;Repository&quot;) in software modules (&quot;Modules&quot;) and made available&#xA;as downloadable archives (&quot;Downloads&quot;).&#xA;&#xA;- Content may be structured and packaged into modules to facilitate delivering,&#xA;extending, and upgrading the Content. Typical modules may include plug-ins (&quot;Plug-ins&quot;),&#xA;plug-in fragments (&quot;Fragments&quot;), and features (&quot;Features&quot;).&#xA;- Each Plug-in or Fragment may be packaged as a sub-directory or JAR (Java(TM) ARchive)&#xA;in a directory named &quot;plugins&quot;.&#xA;- A Feature is a bundle of one or more Plug-ins and/or Fragments and associated material.&#xA;Each Feature may be packaged as a sub-directory in a directory named &quot;features&quot;.&#xA;Within a Feature, files named &quot;feature.xml&quot; may contain a list of the names and version&#xA;numbers of the Plug-ins and/or Fragments associated with that Feature.&#xA;- Features may also include other Features (&quot;Included Features&quot;). Within a Feature, files&#xA;named &quot;feature.xml&quot; may contain a list of the names and version numbers of Included Features.&#xA;&#xA;The terms and conditions governing Plug-ins and Fragments should be&#xA;contained in files named &quot;about.html&quot; (&quot;Abouts&quot;). The terms and&#xA;conditions governing Features and Included Features should be contained&#xA;in files named &quot;license.html&quot; (&quot;Feature Licenses&quot;). Abouts and Feature&#xA;Licenses may be located in any directory of a Download or Module&#xA;including, but not limited to the following locations:&#xA;&#xA;- The top-level (root) directory&#xA;- Plug-in and Fragment directories&#xA;- Inside Plug-ins and Fragments packaged as JARs&#xA;- Sub-directories of the directory named &quot;src&quot; of certain Plug-ins&#xA;- Feature directories&#xA;&#xA;Note: if a Feature made available by the Eclipse Foundation is installed using the&#xA;Provisioning Technology (as defined below), you must agree to a license (&quot;Feature &#xA;Update License&quot;) during the installation process. If the Feature contains&#xA;Included Features, the Feature Update License should either provide you&#xA;with the terms and conditions governing the Included Features or inform&#xA;you where you can locate them. Feature Update Licenses may be found in&#xA;the &quot;license&quot; property of files named &quot;feature.properties&quot; found within a Feature.&#xA;Such Abouts, Feature Licenses, and Feature Update Licenses contain the&#xA;terms and conditions (or references to such terms and conditions) that&#xA;govern your use of the associated Content in that directory.&#xA;&#xA;THE ABOUTS, FEATURE LICENSES, AND FEATURE UPDATE LICENSES MAY REFER&#xA;TO THE EPL OR OTHER LICENSE AGREEMENTS, NOTICES OR TERMS AND CONDITIONS.&#xA;SOME OF THESE OTHER LICENSE AGREEMENTS MAY INCLUDE (BUT ARE NOT LIMITED TO):&#xA;&#xA;- Eclipse Distribution License Version 1.0 (available at http://www.eclipse.org/licenses/edl-v1.0.html)&#xA;- Common Public License Version 1.0 (available at http://www.eclipse.org/legal/cpl-v10.html)&#xA;- Apache Software License 1.1 (available at http://www.apache.org/licenses/LICENSE)&#xA;- Apache Software License 2.0 (available at http://www.apache.org/licenses/LICENSE-2.0)&#xA;- Metro Link Public License 1.00 (available at http://www.opengroup.org/openmotif/supporters/metrolink/license.html)&#xA;- Mozilla Public License Version 1.1 (available at http://www.mozilla.org/MPL/MPL-1.1.html)&#xA;&#xA;IT IS YOUR OBLIGATION TO READ AND ACCEPT ALL SUCH TERMS AND CONDITIONS PRIOR&#xA;TO USE OF THE CONTENT. If no About, Feature License, or Feature Update License&#xA;is provided, please contact the Eclipse Foundation to determine what terms and conditions&#xA;govern that particular Content.&#xA;&#xA;&#xA;Use of Provisioning Technology&#xA;&#xA;The Eclipse Foundation makes available provisioning software, examples of which include,&#xA;but are not limited to, p2 and the Eclipse Update Manager (&quot;Provisioning Technology&quot;) for&#xA;the purpose of allowing users to install software, documentation, information and/or&#xA;other materials (collectively &quot;Installable Software&quot;). This capability is provided with&#xA;the intent of allowing such users to install, extend and update Eclipse-based products.&#xA;Information about packaging Installable Software is available at&#xA;http://eclipse.org/equinox/p2/repository_packaging.html (&quot;Specification&quot;).&#xA;&#xA;You may use Provisioning Technology to allow other parties to install Installable Software.&#xA;You shall be responsible for enabling the applicable license agreements relating to the&#xA;Installable Software to be presented to, and accepted by, the users of the Provisioning Technology&#xA;in accordance with the Specification. By using Provisioning Technology in such a manner and&#xA;making it available in accordance with the Specification, you further acknowledge your&#xA;agreement to, and the acquisition of all necessary rights to permit the following:&#xA;&#xA;1. A series of actions may occur (&quot;Provisioning Process&quot;) in which a user may execute&#xA;the Provisioning Technology on a machine (&quot;Target Machine&quot;) with the intent of installing,&#xA;extending or updating the functionality of an Eclipse-based product.&#xA;2. During the Provisioning Process, the Provisioning Technology may cause third party&#xA;Installable Software or a portion thereof to be accessed and copied to the Target Machine.&#xA;3. Pursuant to the Specification, you will provide to the user the terms and conditions that&#xA;govern the use of the Installable Software (&quot;Installable Software Agreement&quot;) and such&#xA;Installable Software Agreement shall be accessed from the Target Machine in accordance&#xA;with the Specification. Such Installable Software Agreement must inform the user of the&#xA;terms and conditions that govern the Installable Software and must solicit acceptance by&#xA;the end user in the manner prescribed in such Installable Software Agreement. Upon such&#xA;indication of agreement by the user, the provisioning Technology will complete installation&#xA;of the Installable Software.&#xA;&#xA;Cryptography&#xA;&#xA;Content may contain encryption software. The country in which you are&#xA;currently may have restrictions on the import, possession, and use,&#xA;and/or re-export to another country, of encryption software. BEFORE&#xA;using any encryption software, please check the country&apos;s laws,&#xA;regulations and policies concerning the import, possession, or use, and&#xA;re-export of encryption software, to see if this is permitted.&#xA;&#xA;Java and all Java-based trademarks are trademarks of Oracle Corporation in the United States, other countries, or both.'/>
		      </properties>
		      <provides size='2'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.group' version='0.1.0.201304242052'/>
		        <provided namespace='org.eclipse.equinox.p2.localization' name='df_LT' version='1.0.0'/>
		      </provides>
		      <requires size='4'>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl' range='[0.1.0.201304242049,0.1.0.201304242049]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl.ui' range='[0.1.0.201304242049,0.1.0.201304242049]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='[0.1.0.201304242049,0.1.0.201304242049]'/>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.jar' range='[0.1.0.201304242052,0.1.0.201304242052]'>
		          <filter>
		            (org.eclipse.update.install.features=true)
		          </filter>
		        </required>
		      </requires>
		      <touchpoint id='null' version='0.0.0'/>
		      <licenses size='1'>
		        <license uri='%25licenseURL' url='%25licenseURL'>
		          %license
		        </license>
		      </licenses>
		      <copyright>
		        %copyright
		      </copyright>
		    </unit>
		    <unit id='201304242052.repositorytarget' version='1.0.0.01-cBzFg6773593A7A73C7'>
		      <properties size='2'>
		        <property name='org.eclipse.equinox.p2.name' value='Repository Target Generator'/>
		        <property name='org.eclipse.equinox.p2.type.category' value='true'/>
		      </properties>
		      <provides size='1'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='201304242052.repositorytarget' version='1.0.0.01-cBzFg6773593A7A73C7'/>
		      </provides>
		      <requires size='1'>
		        <required namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.feature.feature.group' range='[0.1.0.201304242052,0.1.0.201304242052]'/>
		      </requires>
		      <touchpoint id='null' version='0.0.0'/>
		    </unit>
		    <unit id='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201304242049'>
		      <update id='de.abg.jreichert.repositorytarget.dsl' range='[0.0.0,0.1.0.201304242049)' severity='0'/>
		      <properties size='5'>
		        <property name='org.eclipse.equinox.p2.name' value='de.abg.jreichert.repositorytarget.dsl'/>
		        <property name='org.eclipse.equinox.p2.provider' value='Joerg Reichert'/>
		        <property name='maven-groupId' value='de.abg.jreichert.repositorytarget'/>
		        <property name='maven-artifactId' value='de.abg.jreichert.repositorytarget.dsl'/>
		        <property name='maven-version' value='0.1.0-SNAPSHOT'/>
		      </properties>
		      <provides size='15'>
		        <provided namespace='org.eclipse.equinox.p2.iu' name='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201304242049'/>
		        <provided namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201304242049'/>
		        <provided namespace='java.package' name='de.abg.jreichert' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.services' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition.impl' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.targetDefinition.util' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.serializer' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.parser.antlr' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.parser.antlr.internal' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.validation' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.scoping' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.generator' version='0.0.0'/>
		        <provided namespace='java.package' name='de.abg.jreichert.formatting' version='0.0.0'/>
		        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='bundle' version='1.0.0'/>
		      </provides>
		      <requires size='15'>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.xbase' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.generator' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.apache.commons.logging' range='1.0.4' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.codegen.ecore' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.mwe.utils' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.mwe2.launch' range='0.0.0' optional='true' greedy='false'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.util' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.ecore' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.emf.common' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.antlr.runtime' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='org.eclipse.xtext.common.types' range='0.0.0'/>
		        <required namespace='osgi.bundle' name='de.abg.jreichert.repositorytarget.repositorytargetgenerator' range='0.1.0'/>
		        <required namespace='java.package' name='org.apache.log4j' range='0.0.0'/>
		        <required namespace='java.package' name='org.eclipse.xtext.xbase.lib' range='0.0.0'/>
		      </requires>
		      <artifacts size='1'>
		        <artifact classifier='osgi.bundle' id='de.abg.jreichert.repositorytarget.dsl' version='0.1.0.201304242049'/>
		      </artifacts>
		      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
		      <touchpointData size='1'>
		        <instructions size='1'>
		          <instruction key='manifest'>
		            Bundle-SymbolicName: de.abg.jreichert.repositorytarget.dsl; singleton:=true&#xA;Bundle-Version: 0.1.0.201304242049
		          </instruction>
		        </instructions>
		      </touchpointData>
		    </unit>
		  </units>
		</repository>'''
	
	@Test
	@Ignore
	def void testParsingRemoteCompositeContent() {
		val url = "http://download.eclipse.org/releases/kepler"
		val contentJarParser = new ContentJarParser()
		val contentHandler = new ContentXmlHandler(url);
		val result = contentJarParser.getContents(url, contentHandler)
		assertEquals("expected content count", 3, result.size)
	}

	@Test
	@Ignore("runs to long in remote build")
	def void testParsingRemoteComposite() {
		val url = "http://download.eclipse.org/releases/kepler"
		val contentHandler = new ContentXmlHandler(url)
		val monitor = new NullProgressMonitor
		val readOutP2Repository = new ReadOutP2Repository
		readOutP2Repository.execute(url, contentHandler, monitor)
	}

	@Test
	def void testParsingContent() {
		val url = "file://testdata/updatesite/"
		val contentHandler = new ContentXmlHandler(url)
		val monitor = new NullProgressMonitor
		val readOutP2Repository = new ReadOutP2Repository
		readOutP2Repository.execute(url, contentHandler, monitor)
		val urlToIdVersionPairs = contentHandler.urlToIdToVersion
		val expectedURL1 = "file://testdata/updatesite/de.abg.jreichert.repositorytarget.updatesite-0.1.0.201204242252/"
		val expectedURL2 = "jar:file:testdata/updatesite/de.abg.jreichert.repositorytarget.updatesite-0.1.0.201211111542.zip!/"
		assertEquals(expectedURL1, contentHandler.urlToIdToVersion.keySet.head)
		assertEquals(expectedURL2, contentHandler.urlToIdToVersion.keySet.last)
		val idVersionPairs1 = urlToIdVersionPairs.get(expectedURL1)
		assertNotNull("idVersionPairs1", idVersionPairs1)
		assertEquals("expected idVersionPairs 1 count", 6, idVersionPairs1.size)
		for(entry : idVersionPairs1.entrySet) {
			println("key: " + entry.key + ", value: " + entry.value)
		}
		
		val idVersionPairs2 = urlToIdVersionPairs.get(expectedURL2)
		assertNotNull("idVersionPairs2", idVersionPairs2)
		assertNotNull("idVersionPairs2", idVersionPairs2)
		assertEquals("expected idVersionPairs 2 count", 6, idVersionPairs2.size)
		for(entry : idVersionPairs2.entrySet) {
			println("key: " + entry.key + ", value: " + entry.value)
		}
//		assertEquals("expected idVersionPair keys", "[201211111547.repositorytarget, 201304242052.repositorytarget, de.abg.jreichert.repositorytarget.dsl, de.abg.jreichert.repositorytarget.dsl.ui, de.abg.jreichert.repositorytarget.feature.feature.group, de.abg.jreichert.repositorytarget.feature.feature.jar, de.abg.jreichert.repositorytarget.repositorytargetgenerator]", idVersionPairs.keySet.toString)
	}

	@Test
	def void testCaching() {
		val url = "file://testdata/updatesite/"
		val contentHandler1 = new ContentXmlHandler(url)
		val monitor = new NullProgressMonitor
		val readOutP2Repository = new ReadOutP2Repository
		readOutP2Repository.execute(url, contentHandler1, monitor)
		val contentHandler2 = new ContentXmlHandler(url)
		readOutP2Repository.execute(url, contentHandler2, monitor)
	}
	
	@After
	def after() {
//		locationManager.clearAll
		SessionManager.closeSession;
	}
	
	def afterClass() {
		SessionManager.shutdown;
	}
}
