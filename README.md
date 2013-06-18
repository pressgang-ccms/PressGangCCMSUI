The user interface to the PressGang CCMS REST service.

This code is broken down using a fairly standard MVP configuration.

Views, found in the org.jboss.pressgang.ccms.ui.client.local.mvp.view package, contain classes and interfaces that define the UI layout. No logic is implemented here, 
just the code to build and display the user interface.

Presenters, found in the org.jboss.pressgang.ccms.ui.client.local.mvp.presenter package, are used to add logic to the views.

The models are the REST entities imported from the pressgang-ccms-restv1 maven artifact.

Compile with

mvn -P jboss7,oss-public,jboss-public clean package

Run super dev mode with

mvn -P oss-public,jboss-public gwt:run-codeserver

Some views simply create iFrames to BIRT reports. See https://github.com/mcasperson/birt-jsonparser and https://github.com/mcasperson/BIRT-JSON-Report-Template for examples.

To get rid of the border in the standard BIRT web viewer, set the border-width property in the .birtviewer_document_fragment class in webcontent/birt/styles.style.css file to 0.

To define the jboss-public and oss-public repos, include the following in ~/.m2/settings.xml

	<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
	  <profiles>
			<profile>
				<id>oss-public</id>
				<activation>
					<property>
						<name>!oss.public.off</name>
					</property>
				</activation>
				<repositories>
					<repository>
						<id>oss-public</id>
						<name>oss-public</name>
						<url>https://oss.sonatype.org/content/groups/public/</url>
						<releases>
							<enabled>true</enabled>
						</releases>
						<snapshots>
							<enabled>true</enabled>
						</snapshots>
					</repository>
				</repositories>
				<pluginRepositories>
					<pluginRepository>
						<id>oss-public</id>
						<name>oss-public</name>
						<url>https://oss.sonatype.org/content/groups/public/</url>
						<releases>
							<enabled>true</enabled>
						</releases>
						<snapshots>
							<enabled>true</enabled>
						</snapshots>
					</pluginRepository>
				</pluginRepositories>
			</profile>
			<profile>
				<id>jboss-public</id>
				<activation>
					<property>
						<name>!jboss.public.off</name>
					</property>
				</activation>
				<repositories>
					<repository>
						<id>jboss-public-repository-group</id>
						<name>JBoss Public Maven Repository Group</name>
						<url>https://repository.jboss.org/nexus/content/groups/public/</url>
						<layout>default</layout>
						<releases>
							<updatePolicy>never</updatePolicy>
						</releases>
						<snapshots>
							<updatePolicy>never</updatePolicy>
						</snapshots>
					</repository>
				</repositories>
				<pluginRepositories>
					<pluginRepository>
						<id>jboss-public-repository-group</id>
						<name>JBoss Public Maven Repository Group</name>
						<url>https://repository.jboss.org/nexus/content/groups/public/</url>
						<layout>default</layout>
						<releases>
							<updatePolicy>never</updatePolicy>
						</releases>
						<snapshots>
							<updatePolicy>never</updatePolicy>
						</snapshots>
					</pluginRepository>
				</pluginRepositories>
			</profile>
		</profiles>
	</settings>
