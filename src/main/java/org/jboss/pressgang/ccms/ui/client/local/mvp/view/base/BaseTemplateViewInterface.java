package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.*;

/**
 * This interface defines the UI elements that are exposed from the base template that is
 * used to build up all views.
 *
 * @author Matthew Casperson
 */
public interface BaseTemplateViewInterface {

    /**
     * The interface to the help dialog popup box.
     *
     * @author Matthew Casperson
     */
    interface HelpDialog {
        /**
         *
         * @return A reference to the dialog box (the class that implements this interface usually also extends
         * the DialogBox class, so this method usually just returns "this").
         */
        DialogBox getDialogBox();

        /**
         *
         * @return The element that displays the help topic
         */
        HTML getContents();

        /**
         *
         * @return The OK button.
         */
        PushButton getOK();

        /**
         *
         * @return The Edit button
         */
        PushButton getEdit();

        /**
         *
         * @return The id of the topic that the dialog is displaying
         */
        int getHelpTopic();

        /**
         * Show the dialog box with the specified topic.
         * @param topicId The id of the topic that the dialog is displaying
         * @param waitDisplay The display to display the wait message
         */
        void show(final int topicId, final BaseTemplateViewInterface waitDisplay);
    }

    /**
     *
     * @return The help dialog box.
     */
    HelpDialog getHelpDialog();

    /**
     * @return The container that holds the actions buttons (i.e. the horizontal row of buttons that are specific to a view)
     */
    FlexTable getTopActionPanel();

    /**
     * @return The container that holds the view specific actions buttons
     */
    FlexTable getTopViewSpecificActionPanel();

    /**
     * @return The panel into which a view will add its own content
     */
    SimpleLayoutPanel getPanel();

    /**
     * @return The panel that defines the page header and the remaining content
     */
    DockLayoutPanel getTopLevelPanel();

    /**
     * In some combined views, the top action panel is removed and replaced
     * with customized action panels (usually two action panels split by
     * a vertical divider). This method restores the default top action panel.
     */
    void showRegularMenu();

    /**
     * @return The label that displays the current page title
     */
    Label getPageTitle();

    /**
     * @return The name of the current view
     */
    String getPageName();

    /**
     * @return The name of the application
     */
    String getApplicationName();

    /**
     * @return The panel that holds the advanced links
     */
    FlexTable getAdvancedShortcutPanel();

    /**
     * @return The panel that holds the standard links
     */
    FlexTable getShortcutPanel();

    /**
     * @return The panel that holds the standard and advanced shortcut panels
     */
    SimplePanel getShortCutPanelParent();

    /**
     * @return The panel that holds the top action buttons
     */
    FlexTable getTopActionParentPanel();

    /**
     * @return The panel that holds the panel that holds the top action buttons
     */
    SimplePanel getTopActionGrandParentPanel();

    /**
     * @return The button that opens the home view
     */
    PushButton getHome();

    /**
     * @return The button that opens the bugzilla window
     */
    PushButton getBug();

    /**
     * @return The button that switches to the topic search view
     */
    PushButton getSearch();

    /**
     * @return The button that switches to the topic create view
     */
    PushButton getCreateTopic();

    /**
     * @return The button that switches to the images view
     */
    PushButton getImages();

    /**
     * @return The button that opens the advanced menu
     */
    PushButton getAdvanced();

    /**
     * @return The button that opens the property tag categories view
     */
    PushButton getPropertyTagCategories();

    /**
     * @return The button that opens the property tags view
     */
    PushButton getPropertyTags();

    /**
     * @return The button that opens the integer constants view
     */
    PushButton getIntegerConstants();

    /**
     * @return The button that opens the blob constants view
     */
    PushButton getBlobConstants();

    /**
     * @return The button that opens the string constants view
     */
    PushButton getStringConstants();

    /**
     * @return The button that opens the projects view
     */
    PushButton getProjects();

    /**
     * @return The button that opens the categories view
     */
    PushButton getCategories();

    /**
     * @return The button that opens the tags view
     */
    PushButton getTags();

    /**
     * @return The button that opens the external reports view
     */
    PushButton getReports();

    /**
     * @return The button that opens the translated topics search results view
     */
    PushButton getSearchTranslations();

    /**
     *
     * @return The link that opens the help page
     */
    Anchor getHelp();

    /**
     * @return The button that closes the advanced menu
     */
    PushButton getAdvancedOpen();

    /**
     * @return The button that closes the advanced menu
     */
    PushButton getClose();

    /**
     * All calls to addWaitOperation() should be matched by a call to removeWaitOperation() when
     * the interaction with the server has finished.
     */
    void removeWaitOperation();

    /**
     * When interacting with the server (i.e. making a REST call), the view will be notified
     * by a call to addWaitOperation(). The view can do nothing in response to this call,
     * or display some kind of loading message or widget.
     */
    void addWaitOperation();

    /**
     * @return true if the view is being shown to the user, false otherwise
     */
    boolean isViewShown();

    /**
     * @param isViewShown true when the view is being shown to the user, false otherwise
     */
    void setViewShown(boolean isViewShown);

    /**
     * Sets the link on the feedback anchor.
     *
     * @param link A link to a survey
     */
    void setFeedbackLink(String link);

    /**
     * @return the button used to perform a quick search
     */
    PushButton getQuickSearch();


    /**
     * @return The text box that holds the search criteria
     */
    TextBox getQuickSearchQuery();

    /**
     * Replaces a widget in the top action panel with another widget.
     * @param existing The widget that is currently displayed in the top action panel
     * @param replacement The widget to replace the existing widget with
     */
    void replaceTopActionButton(final Widget existing, final Widget replacement);
    /**
     * Replaces a widget in a flex table with another widget.
     * @param existing The widget that is currently displayed in the top action panel
     * @param replacement The widget to replace the existing widget with
     * @param table The table that holds the widgets.
     */
    void replaceTopActionButton(final Widget existing, final Widget replacement, final FlexTable table);

    /**
     * Adds a widget to a new column in the table.
     * @param widget The widget to add.
     * @param table The table to add the widget to.
     */
    void addActionButton(final Widget widget, final FlexTable table);
}
