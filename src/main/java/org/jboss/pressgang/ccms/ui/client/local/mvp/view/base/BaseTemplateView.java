package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.DocBookUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to build the standard page template. All views extend this class.
 * <p/>
 * The top of the page is a simple header image.
 * <p/>
 * Next is the page title and quick search box.
 * <p/>
 * Next is the top action bar. For a single view, this will be housed in the topActionGrandParentPanel container. For
 * compound views the topActionGrandParentPanel will be removed, and replaced with another container that will pull
 * the topActionParentPanel and topViewSpecificActionPanel containers out. The topActionParentPanel container holds the
 * "common" action buttons. These are buttons that are usually navigation related, and will remain in place as the user
 * moves through the various views in the UI. The "local" action buttons are specific to a view, like line wrapping
 * buttons in a text editing view. These are displayed to the right, and disappear as the user moves to another view.
 * <p/>
 * Next, on the left, is the main navigation column.
 * <p/>
 * To the right of that is the main view area.
 * <p/>
 * Finally, at the bottom, there is the footer.
 * <p/>
 * When a child view is included in a parent view, the parent will usually pull out the action buttons and content panel,
 * displaying them in containers defined by the parent.
 * <p/>
 * This class defines only the layout, not the logic. The BaseTemplatePresenter class is where logic is applied to
 * the ui elements.
 *
 * @author Matthew Casperson
 */
public abstract class BaseTemplateView implements BaseTemplateViewInterface {
    /**
     * true when the view is visible, false otherwise
     */
    private boolean isViewShown;
    /**
     * Maintains a count of how many waiting operations are in progress
     */
    private int waitingCount;

    /**
     * The name of the application.
     */
    @NotNull
    private final String applicationName;
    /**
     * The name of the current page.
     */
    @NotNull
    private final String pageName;

    /**
     * Defines the top level layout that holds the header and the other content.
     */
    private final DockLayoutPanel topLevelLayoutPanel = new DockLayoutPanel(Unit.PX);

    /**
     * Defines the panel that holds the shortcut bar, content and footer.
     */
    private final DockLayoutPanel secondLevelLayoutPanel = new DockLayoutPanel(Unit.PX);

    /**
     * Defines the panel that holds the shortcut bar and content.
     */
    private final DockLayoutPanel thirdLevelLayoutPanel = new DockLayoutPanel(Unit.PX);
    /**
     * The panel that holds the PressGang logo.
     */
    private final FlexTable headingBanner = new FlexTable();
    /**
     * The panel that holds the page title label.
     */
    private final HorizontalPanel pageTitleParentLayoutPanel = new HorizontalPanel();
    /**
     * The application title label.
     */
    private final Anchor applicationTitle = new Anchor(PressGangCCMSUI.INSTANCE.PressGangCCMS());
    /**
     * The page title label.
     */
    private final Label pageTitle = new Label();
    /**
     * The panel that holds the shortbut buttons
     */
    private final TopShortcutView topShortcutView = new TopShortcutView();
    /**
     * This is where the page specific content is held.
     */
    @NotNull
    private SimpleLayoutPanel panel = new SimpleLayoutPanel();

    /**
     * This panel holds the topActionParentPanel panel.
     */
    private final SimplePanel topActionGrandParentPanel = new SimplePanel();
    /**
     * This panel holds the common action buttons in the top action bar.
     */
    private final FlexTable topActionParentPanel = new FlexTable();
    /**
     * This is the collection the local action panel buttons. These buttons are placed to the right.
     */
    private final FlexTable topViewSpecificRightActionPanel = new FlexTable();
    /**
     * This is the collection the local action panel buttons. These buttons are placed to the left.
     */
    private final SimplePanel topViewSpecificLeftActionPanel = new SimplePanel();
    /**
     * This is the collection of view specific action buttons.
     */
    private final FlexTable topActionPanel = new FlexTable();
    private final FlexTable footerPanel = new FlexTable();
    private final SimplePanel footerPanelCustomContent = new SimplePanel();

    private final Label version = new Label(PressGangCCMSUI.INSTANCE.PressGangCCMS() + " " + Constants.VERSION + " " +
            PressGangCCMSUI.INSTANCE.Build() + " " + Constants.BUILD);

    private final HorizontalPanel quickSearchParentPanel = new HorizontalPanel();

    private final HorizontalPanel quickSearchPanel = new HorizontalPanel();

    /**
     * The text box where a quick search is entered.
     */
    private final TextBox quickSearchQuery = new TextBox();
    /**
     * The button used to execute the quick search.
     */
    private final PushButton quickSearch = UIUtilities.createPushButton("");

    /**
     * A hidden element that displays can attach elements to that need to be in the DOM to work (like iFrames)
     * but that should not be seen.
     */
    private final HorizontalPanel hiddenAttachmentArea = new HorizontalPanel();

    /**
     * The list of available servers
     */
    private final ListBox servers = new ListBox();

    private final PushButton helpMode = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Help());

    @Override
    @NotNull
    public HorizontalPanel getHiddenAttachmentArea() {
        return hiddenAttachmentArea;
    }

    @Override
    @NotNull
    public ListBox getServers() {
        return servers;
    }

    /**
     * Enable the help overlay.
     */
    @Override
    @NotNull
    public PushButton getHelpMode() {
        return helpMode;
    }

    /**
     * The container holding the quick search ui elements
     */
    @Override
    @NotNull
    public HorizontalPanel getQuickSearchParentPanel() {
        return quickSearchParentPanel;
    }

    @Override
    @NotNull
    public HorizontalPanel getQuickSearchPanel() {
        return quickSearchPanel;
    }

    @Override
    @NotNull
    public Label getVersion() {
        return version;
    }

    /**
     * The panel that holds the shortcut buttons at the top of the page
     */
    @Override
    @NotNull
    public TopShortcutView getTopShortcutView() {
        return topShortcutView;
    }

    /**
     * The panel that holds the footer items.
     */
    public FlexTable getFooterPanel() {
        return footerPanel;
    }

    /**
     * This panel can be used by views to add their own content.
     */
    public SimplePanel getFooterPanelCustomContent() {
        return footerPanelCustomContent;
    }

    @Override
    @NotNull
    public DockLayoutPanel getSecondLevelLayoutPanel() {
        return secondLevelLayoutPanel;
    }

    @Override
    @NotNull
    public DockLayoutPanel getThirdLevelLayoutPanel() {
        return thirdLevelLayoutPanel;
    }

    @Override
    @NotNull
    public Anchor getHome() {
        return applicationTitle;
    }

    /**
     * Defines the help dialog box, which is displayed when the help link is clicked.
     *
     * @author Matthew Casperson
     */
    public final static class HelpDialogImpl extends DialogBox implements HelpDialog {

        @Inject private FailOverRESTCall failOverRESTCall;
        private final VerticalPanel layout = new VerticalPanel();
        private final Frame contents = new Frame();
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton edit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Edit());
        private int helpTopic;

        @NotNull
        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @NotNull
        @Override
        public PushButton getEdit() {
            return this.edit;
        }

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @NotNull
        @Override
        public Frame getContents() {
            return this.contents;
        }

        @Override
        public int getHelpTopic() {
            return helpTopic;
        }

        public HelpDialogImpl() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.Help());

            contents.addStyleName(CSSConstants.HelpDialog.HELP_CONTENTS);

            layout.add(contents);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);

            buttonPanel.add(edit);
            buttonPanel.add(ok);

            layout.add(buttonPanel);

            this.add(layout);
        }

        @Override
        public void show(final int topicId, @NotNull final BaseTemplateViewInterface waitDisplay) {
            this.helpTopic = topicId;

            final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                @Override
                public void success(@NotNull final RESTTopicV1 retValue) {
                    final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + DocBookDTD.getDtdDoctype() + "\n" +
                            DocBookUtilities.escapeAllCustomEntities(XMLUtilities.removeAllPreamble(retValue.getXml()));

                    ServerDetails.getSavedServer(new ServerDetailsCallback() {
                        @Override
                        public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.holdXML(xml), new RESTCallBack<IntegerWrapper>() {
                                public void success(@NotNull final IntegerWrapper value) {
                                    contents.setUrl(
                                            serverDetails.getRestEndpoint() + Constants.ECHO_ENDPOINT + "?id=" + value.value +
                                                    "&" + Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities
                                                    .getLocalUrlEncoded());
                                    center();
                                }
                            }, waitDisplay, true);
                        }
                    });
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback, waitDisplay);
        }
    }

    /**
     * An instance of the help dialog box.
     */
    private HelpDialog helpDialog = new HelpDialogImpl();


    @NotNull
    @Override
    public HelpDialog getHelpDialog() {
        return helpDialog;
    }

    @NotNull
    @Override
    public PushButton getQuickSearch() {
        return quickSearch;
    }

    @NotNull
    @Override
    public TextBox getQuickSearchQuery() {
        return quickSearchQuery;
    }

    /**
     * @return true when the view is visible, false otherwise
     */
    @Override
    public boolean isViewShown() {
        return isViewShown;
    }

    /**
     * @param isViewShown true if the view is visible, false otherwise
     */
    @Override
    public void setViewShown(final boolean isViewShown) {
        this.isViewShown = isViewShown;
        updateDisplay();
    }

    @NotNull
    @Override
    public Label getPageTitle() {
        return pageTitle;
    }

    @NotNull
    @Override
    public String getPageName() {
        return pageName;
    }

    @NotNull
    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @NotNull
    @Override
    public FlexTable getTopActionParentPanel() {
        return topActionParentPanel;
    }

    @NotNull
    @Override
    public DockLayoutPanel getTopLevelPanel() {
        return topLevelLayoutPanel;
    }

    @NotNull
    @Override
    public SimpleLayoutPanel getPanel() {
        return panel;
    }

    @NotNull
    @Override
    public SimplePanel getTopActionGrandParentPanel() {
        return topActionGrandParentPanel;
    }

    @NotNull
    @Override
    public FlexTable getTopActionPanel() {
        return topActionPanel;
    }

    @NotNull
    @Override
    public FlexTable getTopViewSpecificRightActionPanel() {
        return topViewSpecificRightActionPanel;
    }

    @NotNull
    @Override
    public SimplePanel getTopViewSpecificLeftActionPanel() {
        return topViewSpecificLeftActionPanel;
    }

    /**
     * The constructor is used to piece the template together.
     *
     * @param applicationName The name of the application. This is not actually used any more, and should be removed.
     * @param pageName        The name of the page.
     */
    public BaseTemplateView(@NotNull final String applicationName, @NotNull final String pageName) {
        this.applicationName = applicationName;
        this.pageName = pageName;

        /* Set the heading */
        headingBanner.addStyleName(CSSConstants.Template.APPLICATION_HEADING_PANEL);

        applicationTitle.addStyleName(CSSConstants.Template.APPLICATION_TITLE);
        headingBanner.setWidget(0, 0, applicationTitle);

        /* Set the page title */
        pageTitle.setText(pageName);
        pageTitle.addStyleName(CSSConstants.Template.PAGE_TITLE);
        headingBanner.setWidget(1, 0, pageTitle);

        /* Add the quick search box */
        quickSearchParentPanel.addStyleName(CSSConstants.Template.QUICK_SEARCH_PARENT_PANEL);
        quickSearchParentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        quickSearchQuery.addStyleName(CSSConstants.Template.QUICK_SEARCH_TEXT_BOX);
        quickSearchQuery.getElement().setPropertyString("placeholder", PressGangCCMSUI.INSTANCE.QuickSearch());

        quickSearchPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        quickSearchPanel.add(quickSearchQuery);
        quickSearchPanel.add(getQuickSearch());
        getQuickSearch().addStyleName(CSSConstants.FontAwesome.FONT_AWESOME);
        getQuickSearch().addStyleName(CSSConstants.FontAwesome.FONT_AWESOME_SEARCH);

        quickSearchParentPanel.add(quickSearchPanel);

        headingBanner.setWidget(0, 2, quickSearchParentPanel);
        headingBanner.getFlexCellFormatter().setRowSpan(0, 2, 2);

        topLevelLayoutPanel.addStyleName(CSSConstants.Template.TOP_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.addNorth(headingBanner, Constants.HEADING_BANNER_HEIGHT);

        /*
            Add the hidden DOM attachment area
         */
        hiddenAttachmentArea.setVisible(false);
        topLevelLayoutPanel.addSouth(hiddenAttachmentArea, 0);


        /* Set the remaining content */
        secondLevelLayoutPanel.addStyleName(CSSConstants.Template.THIRD_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.add(secondLevelLayoutPanel);



        /* Set the action bar panel */
        topActionGrandParentPanel.addStyleName(CSSConstants.Template.TOP_ACTION_GRANDPARENT_PANEL);
        topActionParentPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PARENT_PANEL);
        topActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);
        topViewSpecificRightActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);
        topViewSpecificLeftActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);

        topActionParentPanel.setWidget(0, 0, topActionPanel);
        topActionParentPanel.setWidget(0, 1, topViewSpecificLeftActionPanel);
        /* Make 100% wide to push the next cell to the right */
        topActionParentPanel.getFlexCellFormatter().setWidth(0, 1, "100%");
        topActionParentPanel.setWidget(0, 2, topViewSpecificRightActionPanel);

        topActionGrandParentPanel.setWidget(topActionParentPanel);

        /* Set the footer panel */
        footerPanel.addStyleName(CSSConstants.Template.FOOTER_PANEL);

        secondLevelLayoutPanel.addSouth(footerPanel, Constants.FOOTER_HEIGHT);

        thirdLevelLayoutPanel.addNorth(topActionGrandParentPanel, Constants.ACTION_BAR_PARENT_HEIGHT);
        thirdLevelLayoutPanel.add(panel);

        secondLevelLayoutPanel.add(thirdLevelLayoutPanel);

        /* Add the version */
        version.addStyleName(CSSConstants.Template.VERSION_LABEL);
        footerPanel.setWidget(0, 0, version);

        footerPanel.setWidget(0, footerPanel.getCellCount(0), servers);
        footerPanel.setWidget(0, footerPanel.getCellCount(0), footerPanelCustomContent);
        footerPanel.getCellFormatter().addStyleName(0, footerPanel.getCellCount(0) - 1, CSSConstants.Template.CUSTOM_FOOTER_PANEL);

        /* Add the help button */
        footerPanel.setWidget(0, footerPanel.getCellCount(0), helpMode);

        /* Add the content panel */
        panel.addStyleName(CSSConstants.Template.CONTENT_LAYOUT_PANEL);

        /* Build the shortcut panel */
        initialiseShortcuts();
    }

    protected void initialiseShortcuts() {
        //shortcuts.initialise();
        headingBanner.setWidget(0, 1, topShortcutView);
        headingBanner.getFlexCellFormatter().setRowSpan(0, 1, 2);
        headingBanner.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.Template.TOP_SHORTCUT_PANEL_CELL);
    }

    @Override
    public void showRegularMenu() {
        topActionParentPanel.clear();
        topActionParentPanel.add(topActionPanel);
    }

    public void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement) {
        replaceTopActionButton(existing, replacement, this.topActionPanel);
    }

    public void removeTopActionButton(@NotNull final Widget existing) {
        /* Early out if the existing widget isn't actually attached */
        if (!existing.isAttached() || existing.getParent() != topActionPanel) {
            return;
        }

        for (int row = 0; row < topActionPanel.getRowCount(); ++row) {
            for (int column = 0; column < topActionPanel.getCellCount(row); ++column) {
                if (topActionPanel.getWidget(row, column) == existing) {
                    topActionPanel.removeCell(row, column);
                    return;
                }
            }
        }
    }

    public void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement, @NotNull final FlexTable table) {

        /* Early out if the existing widget isn't actually attached */
        if (!existing.isAttached() || existing.getParent() != table) {
            return;
        }

        for (int row = 0; row < table.getRowCount(); ++row) {
            for (int column = 0; column < table.getCellCount(row); ++column) {
                if (table.getWidget(row, column) == existing) {
                    table.setWidget(row, column, replacement);
                    return;
                }
            }
        }
    }

    private void addRightAlignedActionButtonPaddingPanel(@NotNull final FlexTable table) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, new SimplePanel());
        table.getCellFormatter().addStyleName(0, columns, CSSConstants.Template.RIGHT_ALIGNED_ACTION_BUTTONS);
    }

    public void addActionButton(@NotNull final Widget widget, @NotNull final FlexTable table) {
        addActionButton(widget, table, false, false);
    }

    public void addActionButton(@NotNull final Widget widget, @NotNull final FlexTable table, boolean endOfTabs, boolean startOfTabs) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, widget);

        if (endOfTabs) {
            table.getFlexCellFormatter().addStyleName(0, columns, CSSConstants.Template.END_TAB_BUTTONS);
        }

        if (startOfTabs) {
            table.getFlexCellFormatter().addStyleName(0, columns, CSSConstants.Template.START_TAB_BUTTONS);
        }
    }

    public void insertActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget, @NotNull final FlexTable table, boolean endOfTabs) {
        /* Early out if the existing widget isn't actually attached */
        if (!oldWidget.isAttached() || oldWidget.getParent() != table) {
            return;
        }

        final int oldWidgetColumn = getWidgetColumn(oldWidget, table);
        // Insert the new cell
        if (table.getCellCount(0) > oldWidgetColumn + 1) {
            table.insertCell(0, oldWidgetColumn + 1);
        }
        table.setWidget(0, oldWidgetColumn + 1, newWidget);

        if (endOfTabs) {
            table.getFlexCellFormatter().addStyleName(0, oldWidgetColumn + 1, CSSConstants.Template.END_TAB_BUTTONS);
        }
    }

    private int getWidgetColumn(final Widget widget, final FlexTable table) {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getCellCount(row); col++) {
                final Widget w = table.getWidget(row, col);
                if (w == widget) {
                    return col;
                }
            }
        }

        return -1;
    }

    public void addLocalActionButton(@NotNull final Widget widget) {
        addActionButton(widget, this.getTopViewSpecificRightActionPanel());
    }

    public void insertLocalActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget) {
        insertLocalActionButton(newWidget, oldWidget, false);
    }

    public void insertLocalActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget, boolean endOfTabs) {
        insertActionButton(newWidget, oldWidget, this.getTopViewSpecificRightActionPanel(), endOfTabs);
    }

    /**
     * @param widget The widget to add as an action button
     * @param endOfTabs True if the cell holding the widget should be styled as if it is ending a group of widgets
     */
    public void addActionButton(@NotNull final Widget widget, boolean endOfTabs) {
        addActionButton(widget, this.getTopActionPanel(), endOfTabs, false);
    }

    /**
     * @param widget The widget to add as an action button
     * @param endOfTabs True if the cell holding the widget should be styled as if it is ending a group of widgets
     * @param startOfTabs True if the cell holding the widget should be styled as if it is starting a group of widgets
     */
    public void addActionButton(@NotNull final Widget widget, boolean endOfTabs, boolean startOfTabs) {
        addActionButton(widget, this.getTopActionPanel(), endOfTabs, startOfTabs);
    }

    public void addActionButton(@NotNull final Widget widget) {
        addActionButton(widget, this.getTopActionPanel());
    }

    public void insertActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget) {
        insertActionButton(newWidget, oldWidget, false);
    }

    public void insertActionButton(@NotNull final Widget newWidget, @NotNull final Widget oldWidget, boolean endOfTabs) {
        insertActionButton(newWidget, oldWidget, this.getTopActionPanel(), endOfTabs);
    }

    /**
     * Called when some background operation that would cause this view to be locked is started.
     */
    @Override
    public void addWaitOperation() {
        ++waitingCount;
        updateDisplay();
    }

    /**
     * Called when some background operation that would cause this view to be locked is ended.
     */
    @Override
    public void removeWaitOperation() {
        if (waitingCount < 1) {
            throw new IllegalStateException("waitingCount should never be less than one when removeWaitOperation() is called.");
        }

        --waitingCount;
        updateDisplay();
    }

    /**
     * Hide or display the waiting state depending on the views visibility and any pending background operations.
     */
    private void updateDisplay() {
        if (!isViewShown || waitingCount == 0) {
            hideWaiting();
        } else if (isViewShown && waitingCount != 0) {
            showWaiting();
        }
    }

    /**
     * The view can show a waiting widget when this method is called. Override this method to provide implementation.
     */
    protected void showWaiting() {
        // Do nothing by default
    }

    /**
     * The view can hide a waiting widget when this method is called. Override this method to provide implementation.
     */
    protected void hideWaiting() {
        // Do nothing by default
    }
}