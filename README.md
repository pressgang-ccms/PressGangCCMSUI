The user interface to the PressGang CCMS REST service.

This code is broken down into 3 major packages.

Views, found in the org.jboss.pressgang.ccms.ui.client.local.mvp.view package, contain classes and interfaces that define the UI layout. No logic is implemented here, 
just the code to build and display the user interface.

Components, found in the org.jboss.pressgang.ccms.ui.client.local.mvp.component package, contains the logic that is applied to the views.

Presenters, found in the org.jboss.pressgang.ccms.ui.client.local.mvp.presenter package, are used to define the views and components, and combine them to provide the
interface that the users sees and interacts with. 