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

The ACE editor currently works with the ACE distribution from the 17th December 2012. See https://github.com/ajaxorg/ace-builds/tree/97bae1d132effbeca5eef8aaef2a2a1fa836b181