package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.jetbrains.annotations.NotNull;

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
         * @return A reference to the dialog box (the class that implements this interface usually also extends
         *         the DialogBox class, so this method usually just returns "this").
         */
        @NotNull
        DialogBox getDialogBox();

        /**
         * @return The element that displays the help topic
         */
        @NotNull
        Frame getContents();

        /**
         * @return The OK button.
         */
        @NotNull
        PushButton getOK();

        /**
         * @return The Edit button
         */
        @NotNull
        PushButton getEdit();

        /**
         * @return The id of the topic that the dialog is displaying
         */
        int getHelpTopic();

        /**
         * Show the dialog box with the specified topic.
         *
         * @param topicId     The id of the topic that the dialog is displaying
         * @param waitDisplay The display to display the wait message
         */
        void show(final int topicId, final BaseTemplateViewInterface waitDisplay);
    }

    /**
     * @return The label that holds the build number.
     */
    @NotNull
    Label getVersion();

    /**
     * @return The help dialog box.
     */
    @NotNull
    HelpDialog getHelpDialog();

    /**
     * @return The container that holds the actions buttons (i.e. the horizontal row of buttons that are specific to a view)
     */
    @NotNull
    FlexTable getTopActionPanel();

    /**
     * @return The container that holds the view specific actions buttons. These buttons are displayed to the right.
     */
    @NotNull
    FlexTable getTopViewSpecificRightActionPanel();

    /**
     * @return The container that holds the view specific actions buttons. These buttons are displayed to the left.
     */
    @NotNull
    SimplePanel getTopViewSpecificLeftActionPanel();


    /**
     * @return The panel into which a view will add its own content
     */
    @NotNull
    SimpleLayoutPanel getPanel();

    /**
     * @return The panel that defines the page header and the remaining content
     */
    @NotNull
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
    @NotNull
    Label getPageTitle();

    /**
     * @return The name of the current view
     */
    @NotNull
    String getPageName();

    /**
     * @return The name of the application
     */
    @NotNull
    String getApplicationName();

    @NotNull
    TopShortcutView getTopShortcutView();

    /**
     * @return The panel that holds the top action buttons
     */
    @NotNull
    FlexTable getTopActionParentPanel();

    /**
     * @return The panel that holds the panel that holds the top action buttons
     */
    @NotNull
    SimplePanel getTopActionGrandParentPanel();

    /**
     * @return The list of available servers.
     */
    @NotNull
    ListBox getServers();

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

    @NotNull
    HorizontalPanel getQuickSearchParentPanel();

    @NotNull
    HorizontalPanel getQuickSearchPanel();

    /**
     * @return the button used to perform a quick search
     */
    @NotNull
    PushButton getQuickSearch();


    /**
     * @return The text box that holds the search criteria
     */
    @NotNull
    TextBox getQuickSearchQuery();

    @NotNull
    PushButton getHelpMode();

    /**
     * Replaces a widget in the top action panel with another widget.
     *
     * @param existing    The widget that is currently displayed in the top action panel
     * @param replacement The widget to replace the existing widget with
     */
    void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement);

    /**
     * Replaces a widget in a flex table with another widget.
     *
     * @param existing    The widget that is currently displayed in the top action panel
     * @param replacement The widget to replace the existing widget with
     * @param table       The table that holds the widgets.
     */
    void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement, @NotNull final FlexTable table);

    /**
     * Remove a button from the top action panel.
     *
     * @param existing The button to be removed
     */
    void removeTopActionButton(@NotNull final Widget existing);

    /**
     * Adds a widget to a new column in the table.
     *
     * @param widget The widget to add.
     * @param table  The table to add the widget to.
     */
    void addActionButton(@NotNull final Widget widget, @NotNull final FlexTable table);

    /**
     * Adds a widget to a new column in the top action panel.
     *
     * @param widget The widget to add.
     */
    void addActionButton(@NotNull final Widget widget);

    /**
     * Inserts a widget after another widget in the top action panel.
     *
     * @param newWidget The new widget to insert.
     * @param oldWidget The widget that the new widget should be inserted after
     */
    void insertActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget);

    /**
     * Adds a widget to a new column in the local (ie right hand side) top action panel.
     *
     * @param widget The widget to add.
     */
    void addLocalActionButton(@NotNull final Widget widget);

    /**
     * @return The hidden DOM element that can be used as a parent for other elements
     *         that need to be attached but hidden.
     */
    HorizontalPanel getHiddenAttachmentArea();

    /**
     * This panel can be used by views to add their own content on the left side of the footer.
     */
    public SimplePanel getFooterPanelLeftCustomContent();

    /**
     * This panel can be used by views to add their own content on the right side of the footer.
     */
    public SimplePanel getFooterPanelRightCustomContent();
}
