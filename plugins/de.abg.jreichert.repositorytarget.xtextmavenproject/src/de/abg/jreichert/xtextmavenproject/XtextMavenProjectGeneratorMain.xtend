package de.abg.jreichert.xtextmavenproject

import java.io.File
import java.io.FileWriter
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.generator.IFileSystemAccess
import java.io.IOException

@Accessors class MyFileSystemAccess implements IFileSystemAccess {
	private val String base;

	override deleteFile(String fileName) {
	}
	
	override generateFile(String fileName, CharSequence contents) {
		val file = new File(base + "/" + fileName)
		if(file.parentFile.mkdirs) {
			try {
				val fw = new FileWriter(file)
				fw.write(contents.toString)
				fw.close
			} catch (IOException exc) {
				throw new RuntimeException(exc)
			}
		}
	}
	
	override generateFile(String fileName, String outputConfigurationName, CharSequence contents) {
	}
}

@Accessors class XtextMavenProjectGeneratorMain {
	private val String name
	private val String version
	private val String targetName
	private val IFileSystemAccess fileAccess
	
	def static void main(String[] args) {
		val base = "C:/base/repositorytarget"
		val name = "de.abg.jreichert.repositorytarget"
		val version = "0.1.0-SNAPSHOT"
		val targetName = "repositorytarget"
		val fileAccess = new MyFileSystemAccess(base)
		val generator = new XtextMavenProjectGeneratorMain(name, version, targetName, fileAccess)
		generator.generate
	}
	
	def generate() {
		generateTargetPlatformProject()
		generateRepositoryParentProject()
		generateRepositoryProject()
		generateParentProject()
		val pluginProjects = newArrayList(
			"dsl",
			"dsl.tests",
			"dsl.ui",
			"repositorytargetgenerator"
		)
		pluginProjects.forEach(pp|pp.generatePluginProject())
		generateDistributionProject(pluginProjects)
	}

	def void generateTargetPlatformProject() {
		val projectName = name + ".targetplatform"
		val content = targetPlatformPom(projectName)
		fileAccess.generateFile("/releng/" + projectName + "/pom.xml", content)
	}
	
	def targetPlatformPom(String projectName) '''
		�pomHeader(projectName)�
			<packaging>pom</packaging>

			<build>
				<plugins>
					<plugin>
						<groupId>org.codehaus.mojo</groupId>
						<artifactId>build-helper-maven-plugin</artifactId>
						<version>1.3</version>
						<executions>
							<execution>
								<id>attach-artifacts</id>
								<phase>package</phase>
								<goals>
									<goal>attach-artifact</goal>
								</goals>
								<configuration>
									<artifacts>
										<artifact>
											<file>�targetName�.target</file>
											<type>target</type>
											<classifier>�targetName�</classifier>
										</artifact>
									</artifacts>
								</configuration>
							</execution>
						</executions>
					</plugin>
				</plugins>
			</build>
		</project>
	'''
	
	def pomHeader(String projectName) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
			<modelVersion>4.0.0</modelVersion>
			<groupId>�name�</groupId>
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
	'''
	
	def void generateRepositoryParentProject() {
		val projectName = name + ".repository.parent"
		val content = repositoryParentPom(projectName)
		fileAccess.generateFile("/releng/" + projectName + "/pom.xml", content)
	}

	def repositoryParentPom(String projectName) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
			<modelVersion>4.0.0</modelVersion>
			<prerequisites>
				<maven>3.0</maven>
			</prerequisites>
		
			<groupId>�name�</groupId>
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
			<packaging>pom</packaging>
		
			<properties>
				<tycho-version>0.16.0</tycho-version>
				<junit-version>4.8.1</junit-version>
				<platform-version-name>juno</platform-version-name>
				<eclipse-site>http://download.eclipse.org/releases/${platform-version-name}</eclipse-site>
				<orbit-site>http://download.eclipse.org/tools/orbit/downloads/drops/R20110523182458/repository</orbit-site>
				<platform-version>[4.2,4.3)</platform-version>
				<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
				<target-platform-version>0.1.0-SNAPSHOT</target-platform-version>
				<maven-resources-plugin-version>2.5</maven-resources-plugin-version>
				<maven-antrun-plugin-version>1.7</maven-antrun-plugin-version>
				<maven-surefire-plugin-version>2.9</maven-surefire-plugin-version>
				<maven-clean-plugin-version>2.4.1</maven-clean-plugin-version>
				<maven-assembly-plugin-version>2.3</maven-assembly-plugin-version>
				<build-helper-maven-plugin-version>1.7</build-helper-maven-plugin-version>
				<fornax-oaw-m2-plugin-version>3.3.1</fornax-oaw-m2-plugin-version>
				<xtend-maven-plugin-version>2.3.1</xtend-maven-plugin-version>
			</properties>
		
			<build>
				<plugins>
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>tycho-maven-plugin</artifactId>
						<version>${tycho-version}</version>
						<extensions>true</extensions>
					</plugin>
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>target-platform-configuration</artifactId>
						<version>${tycho-version}</version>
						<configuration>
							<resolver>p2</resolver>
							<pomDependencies>consider</pomDependencies>
							<target>
								<artifact>
									<groupId>�name�</groupId>
									<artifactId>�name�.targetplatform</artifactId>
									<version>${target-platform-version}</version>
									<classifier>�targetName�</classifier>
								</artifact>
							</target>
							<environments>
								<environment>
									<os>win32</os>
									<ws>win32</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>win32</os>
									<ws>win32</ws>
									<arch>x86_64</arch>
								</environment>
								<environment>
									<os>macosx</os>
									<ws>cocoa</ws>
									<arch>x86_64</arch>
								</environment>
								<environment>
									<os>macosx</os>
									<ws>cocoa</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>linux</os>
									<ws>gtk</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>linux</os>
									<ws>gtk</ws>
									<arch>x86_64</arch>
								</environment>
							</environments>
						</configuration>
					</plugin>
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>tycho-source-plugin</artifactId>
						<version>${tycho-version}</version>
						<executions>
							<execution>
								<id>attach-source</id>
								<goals>
									<goal>plugin-source</goal>
								</goals>
							</execution>
						</executions>
					</plugin>
				</plugins>
				<pluginManagement>
					<plugins>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>tycho-compiler-plugin</artifactId>
							<version>${tycho-version}</version>
							<configuration>
								<encoding>UTF-8</encoding>
								<source>6.0</source>
								<target>6.0</target>
								<verbose>true</verbose>
							</configuration>
						</plugin>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>tycho-surefire-plugin</artifactId>
							<version>${tycho-version}</version>
						</plugin>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>maven-osgi-test-plugin</artifactId>
							<version>${tycho-version}</version>
						</plugin>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>tycho-p2-repository-plugin</artifactId>
							<version>${tycho-version}</version>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-resources-plugin</artifactId>
							<version>${maven-resources-plugin-version}</version>
							<configuration>
								<encoding>UTF-8</encoding>
							</configuration>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-antrun-plugin</artifactId>
							<version>${maven-antrun-plugin-version}</version>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-surefire-plugin</artifactId>
							<version>${maven-surefire-plugin-version}</version>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-clean-plugin</artifactId>
							<version>${maven-clean-plugin-version}</version>
						</plugin>
						<plugin>
							<groupId>org.apache.maven.plugins</groupId>
							<artifactId>maven-assembly-plugin</artifactId>
							<version>${maven-assembly-plugin-version}</version>
						</plugin>
						<plugin>
							<groupId>org.codehaus.mojo</groupId>
							<artifactId>build-helper-maven-plugin</artifactId>
							<version>${build-helper-maven-plugin-version}</version>
							<executions>
								<execution>
									<id>add-source</id>
									<phase>generate-sources</phase>
									<goals>
										<goal>add-source</goal>
									</goals>
									<configuration>
										<sources>
											<source>src-gen</source>
										</sources>
										<sources>
											<source>${project.build.directory}/xtend-gen</source>
										</sources>
									</configuration>
								</execution>
							</executions>
						</plugin>
						<plugin>
							<groupId>org.fornax.toolsupport</groupId>
							<artifactId>fornax-oaw-m2-plugin</artifactId>
							<version>${fornax-oaw-m2-plugin-version}</version>
						</plugin>
						<plugin>
							<groupId>org.eclipse.xtend2</groupId>
							<artifactId>xtend-maven-plugin</artifactId>
							<version>${xtend-maven-plugin-version}</version>
							<configuration>
								<outputDirectory>${basedir}/xtend-gen</outputDirectory>
							</configuration>
						</plugin>
					</plugins>
				</pluginManagement>
			</build>
			<pluginRepositories>
				<pluginRepository>
					<id>sonatype</id>
					<url>http://repository.sonatype.org/content/groups/sonatype-public-grid/</url>
					<releases>
						<enabled>true</enabled>
					</releases>
					<snapshots>
						<enabled>true</enabled>
					</snapshots>
				</pluginRepository>
				<pluginRepository>
					<id>fornax</id>
					<url>http://www.fornax-platform.org/m2/repository</url>
					<releases>
						<enabled>true</enabled>
					</releases>
					<snapshots>
						<enabled>false</enabled>
					</snapshots>
				</pluginRepository>
				<pluginRepository>
					<id>xtend</id>
					<url>http://build.eclipse.org/common/xtend/maven/</url>
				</pluginRepository>
			</pluginRepositories>
		
			<profiles>
				<profile>
					<id>platform-juno</id>
					<activation>
						<property>
							<name>platform-version-name</name>
							<value>juno</value>
						</property>
					</activation>
					<properties>
						<eclipse-site>http://download.eclipse.org/releases/juno</eclipse-site>
						<platform-version>${platform-version}</platform-version>
					</properties>
				</profile>
				<profile>
					<id>macosx</id>
					<activation>
						<os>
							<name>mac os x</name>
							<family>mac</family>
						</os>
					</activation>
					<properties>
						<ui.test.vmargs>-Xmx512m -XX:MaxPermSize=256m -XstartOnFirstThread</ui.test.vmargs>
					</properties>
				</profile>
				<profile>
					<id>other-os</id>
					<activation>
						<os>
							<name>not-mac</name>
							<family>!mac</family>
						</os>
					</activation>
					<properties>
						<ui.test.vmargs>-Xmx512m -XX:MaxPermSize=256m</ui.test.vmargs>
					</properties>
				</profile>
			</profiles>
		
			<licenses>
				<license>
					<name>Eclipse Public License v1.0</name>
					<comments>
		       All rights reserved.
		
		       This program and the accompanying materials are made
		       available under the terms of the Eclipse Public License v1.0
		       which accompanies this distribution, and is available at
		       http://www.eclipse.org/legal/epl-v10.htm
		      </comments>
				</license>
			</licenses>
		
			<inceptionYear>2011</inceptionYear>
			<issueManagement>
				<system>GitHub Issue Tracker</system>
				<url>https://github.com/joergreichert/XtextTodos/issues</url>
			</issueManagement>
			<organization>
				<name>GitHub</name>
				<url>https://github.com/joergreichert/XtextTodos</url>
			</organization>
			<scm>
				<developerConnection>https://github.com/joergreichert/XtextTodos</developerConnection>
				<url>https://github.com/joergreichert/XtextTodos</url>
				<connection>https://github.com/joergreichert/XtextTodos</connection>
			</scm>
			<ciManagement>
				<system>Cloudbees Jenkins</system>
				<url>https://joergreichert.ci.cloudbees.com/</url>
			</ciManagement>
			
			<distributionManagement>
				<repository>
					<id>cloudbees-release</id>
					<name>Cloudbees Private Repository</name>
					<url>dav:https://repository-joergreichert.forge.cloudbees.com/release</url>
				</repository>
				<snapshotRepository>
					<id>cloudbees-snapshot</id>
					<name>Cloudbees Private Repository</name>
					<url>dav:https://repository-joergreichert.forge.cloudbees.com/snapshot</url>
				</snapshotRepository>
			</distributionManagement>	
		</project>
	'''
	
	def void generateRepositoryProject() {
		val projectName = name + ".repository"
		val projectParentName = projectName + ".parent"
		val content = repositoryPom(projectParentName, projectName)
		fileAccess.generateFile("/releng/" + projectName + "/pom.xml", content)
	}

	def repositoryPom(String projectParentName, String projectName) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
			xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<modelVersion>4.0.0</modelVersion>
			<parent>
				<groupId>�name�</groupId>
				<artifactId>�projectParentName�</artifactId>
				<version>�version�</version>
				<relativePath>../../releng/�projectParentName�/pom.xml</relativePath>
			</parent>
		
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
			<packaging>eclipse-repository</packaging>
			
			<build>
				<pluginManagement>
					<plugins>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>tycho-p2-repository-plugin</artifactId>
							<configuration>
								<includeAllDependencies>true</includeAllDependencies>
							</configuration>
						</plugin>
					</plugins>
				</pluginManagement>
			</build>
		</project>
	'''
	
	def void generateParentProject() {
		val projectName = name + ".parent"
		val projectParentName = projectName + ".repository.parent"
		val content = repositoryPom(projectParentName, projectName)
		fileAccess.generateFile("/releng/" + projectName + "/pom.xml", content)
	}
	
	def parentPom(String projectParentName, String projectName) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
			<modelVersion>4.0.0</modelVersion>
			<parent>
				<groupId>�name�</groupId>
				<artifactId>�projectParentName�</artifactId>
				<version>�version�</version>
				<relativePath>../../releng/�projectParentName�/pom.xml</relativePath>
			</parent>	
		
			<groupId>�name�</groupId>
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
			<packaging>pom</packaging>
		
			<build>
				<plugins>
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>target-platform-configuration</artifactId>
						<version>${tycho-version}</version>
						<configuration>
							<resolver>p2</resolver>
							<pomDependencies>consider</pomDependencies>
							<environments>
								<environment>
									<os>win32</os>
									<ws>win32</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>win32</os>
									<ws>win32</ws>
									<arch>x86_64</arch>
								</environment>
								<environment>
									<os>macosx</os>
									<ws>cocoa</ws>
									<arch>x86_64</arch>
								</environment>
								<environment>
									<os>macosx</os>
									<ws>cocoa</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>linux</os>
									<ws>gtk</ws>
									<arch>x86</arch>
								</environment>
								<environment>
									<os>linux</os>
									<ws>gtk</ws>
									<arch>x86_64</arch>
								</environment>
							</environments>
						</configuration>
					</plugin>
				</plugins>
			</build>
		
			<profiles>
		        <profile>
		            <id>local-build</id>
		            <activation>
		                <property>
		                    <name>local-build</name>
		                </property>
		            </activation>
		            <repositories>
		                <repository>
		                    <id>�targetName�-dependencies</id>
		                    <layout>p2</layout>
		                    <url>file:../�name�.repository/target/repository</url>
		                </repository>
		            </repositories>
		        </profile>
		        <profile>
		            <id>remote-build</id>
		            <activation>
		                <property>
		                    <name>remote-build</name>
		                </property>
		            </activation>
		            <repositories>
		                <repository>
		                    <id>bookmarks-dependencies</id>
		                    <layout>p2</layout>
		                    <url>https://joergreichert.ci.cloudbees.com/job/XtextTodos/lastSuccessfulBuild/artifact/�name�.repository/target/repository/</url>
		                </repository>
		            </repositories>
		        </profile>
				<profile>
					<id>skipUITests</id>
					<activation>
						<property>
							<name>skipUITests</name>
						</property>
					</activation>
					<properties>
						<tycho.surefire.skipUiTest>true</tycho.surefire.skipUiTest>
					</properties>
				</profile>
			</profiles>
		
			<dependencyManagement>
				<dependencies>
					<dependency>
						<groupId>junit</groupId>
						<artifactId>junit</artifactId>
						<version>${junit-version}</version>
						<scope>test</scope>
					</dependency>
				</dependencies>
			</dependencyManagement>
			
			<distributionManagement>
				<repository>
					<id>cloudbees-release</id>
					<name>Cloudbees Private Repository</name>
					<url>dav:https://repository-joergreichert.forge.cloudbees.com/release</url>
				</repository>
				<snapshotRepository>
					<id>cloudbees-snapshot</id>
					<name>Cloudbees Private Repository</name>
					<url>dav:https://repository-joergreichert.forge.cloudbees.com/snapshot</url>
				</snapshotRepository>
			</distributionManagement>	
		</project>
	'''
	
	def void generatePluginProject(String pluginProject) {
		val projectName = name + "." + pluginProject
		val content = pluginPom(pluginProject)
		fileAccess.generateFile("/plugins/" + projectName + "/pom.xml", content)
	}
	
	def pluginPom(String projectName) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<modelVersion>4.0.0</modelVersion>
			<parent>
				<groupId>�name�</groupId>
				<artifactId>�name�.parent</artifactId>
				<version>�version�</version>
				<relativePath>../../releng/�name�.parent/pom.xml</relativePath>
			</parent>
		
			<groupId>�name�</groupId>
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
			<packaging>eclipse-plugin</packaging>
			<build>
				<plugins>
					<plugin>
						<groupId>org.eclipse.xtend2</groupId>
						<artifactId>xtend-maven-plugin</artifactId>
						<executions>
							<execution>
								<goals>
									<goal>compile</goal>
								</goals>
							</execution>
						</executions>
						<dependencies>
							<!-- these dependencies are contributed in Eclipse by the "Xtend Library" classpath container -->
							<dependency>
								<groupId>org.eclipse.xtend</groupId>
								<artifactId>xtend-library</artifactId>
								<version>2.2.1</version>
								<type>pom</type>
							</dependency>
						</dependencies>
					</plugin>
				</plugins>
			</build>
		</project>
	'''
	
	def void generateDistributionProject(List<String> pluginProjects) {
		val projectName = name + ".distribution"
		val content = distroPom(projectName, pluginProjects)
		fileAccess.generateFile("/releng/" + projectName + "/pom.xml", content)
	}
	
	def distroPom(String projectName, List<String> pluginProjects) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
			<modelVersion>4.0.0</modelVersion>
			<prerequisites>
				<maven>3.0</maven>
			</prerequisites>
		
			<groupId>�name�</groupId>
			<artifactId>�projectName�</artifactId>
			<version>�version�</version>
			<packaging>pom</packaging>
			<name>�targetName�</name>
			<modules>
				<module>../../releng/�name�.targetplatform</module>
				<module>../../releng/�name�.repository.parent</module>
				<module>../../releng/�name�.parent</module>
			</modules>
			<profiles>
				<profile>
					<id>modules-default</id>
					<activation>
						<activeByDefault>true</activeByDefault>
					</activation>
					<modules>
						�FOR pluginProject : pluginProjects�
						<module>../../plugins/�name�.�pluginProject�</module>
						�ENDFOR�
					</modules>
				</profile>
				<profile>
					<id>modules-targetplatform</id>
					<activation>
						<property>
							<name>modules-targetplatform</name>
						</property>
					</activation>
					<modules>
						<module>../../releng/�name�.repository</module>
					</modules>
				</profile>
				<!--profile>
					<id>modules-assembly</id>
					<activation>
						<property>
							<name>modules-assembly</name>
						</property>
					</activation>
					<modules>
						<module>../../features/�name�.feature</module>
						<module>../../features/�name�.updatesite</module>
					</modules>
				</profile-->
				<profile>
					<id>sonarLocal</id>
					<properties>
						<sonar.jdbc.url>jdbc:mysql://localhost:3306/sonar?useUnicode=true&amp;characterEncoding=utf8</sonar.jdbc.url>
						<sonar.jdbc.driverClassName>com.mysql.jdbc.Driver</sonar.jdbc.driverClassName>
						<sonar.jdbc.username>sonar</sonar.jdbc.username>
						<sonar.jdbc.password>sonar</sonar.jdbc.password>
						<sonar.host.url>http://localhost:9000/</sonar.host.url>
						<sonar.java.source>1.6</sonar.java.source>
						<sonar.java.target>1.6</sonar.java.target>				
						<sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
					</properties> 
				</profile>
				<profile>
					<id>sonarRemote</id>
					<properties>
						<sonar.jdbc.url>jdbc:mysql://ec2-174-129-80-78.compute-1.amazonaws.com:3306/sonar?useUnicode=true&amp;characterEncoding=utf8</sonar.jdbc.url>
						<sonar.jdbc.driverClassName>com.mysql.jdbc.Driver</sonar.jdbc.driverClassName>
						<sonar.jdbc.username>sonar</sonar.jdbc.username>
						<sonar.jdbc.password>sonar</sonar.jdbc.password>
						<sonar.host.url>http://ec2-174-129-80-78.compute-1.amazonaws.com:9000/sonar</sonar.host.url>
						<sonar.java.source>1.6</sonar.java.source>
						<sonar.java.target>1.6</sonar.java.target>				
						<sonar.dynamicAnalysis>reuseReports</sonar.dynamicAnalysis>
					</properties> 
				</profile>
			</profiles>
		</project>
	'''
}