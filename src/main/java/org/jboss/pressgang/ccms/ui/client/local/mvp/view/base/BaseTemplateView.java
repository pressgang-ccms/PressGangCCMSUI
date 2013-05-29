package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import hu.szaboaz.gwt.xslt.client.XsltProcessingException;
import hu.szaboaz.gwt.xslt.client.XsltProcessor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.CSSResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.resources.xsl.DocbookToHTML;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to build the standard page template. All views extend this class.
 *
 * The top of the page is a simple header image.
 *
 * Next is the page title and quick search box.
 *
 * Next is the top action bar. For a single view, this will be housed in the topActionGrandParentPanel container. For
 * compound views the topActionGrandParentPanel will be removed, and replaced with another container that will pull
 * the topActionParentPanel and topViewSpecificActionPanel containers out. The topActionParentPanel container holds the
 * "common" action buttons. These are buttons that are usually navigation related, and will remain in place as the user
 * moves through the various views in the UI. The "local" action buttons are specific to a view, like line wrapping
 * buttons in a text editing view. These are displayed to the right, and disappear as the user moves to another view.
 *
 * Next, on the left, is the main navigation column.
 *
 * To the right of that is the main view area.
 *
 * Finally, at the bottom, there is the footer.
 *
 * When a child view is included in a parent view, the parent will usually pull out the action buttons and content panel,
 * displaying them in containers defined by the parent.
 *
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
     * Defines the panel that holds the page title and the other content.
     */
    private final DockLayoutPanel secondLevelLayoutPanel = new DockLayoutPanel(Unit.EM);

    /**
     * Defines the panel that holds the shortcut bar, content and footer.
     */
    private final DockLayoutPanel thirdLevelLayoutPanel = new DockLayoutPanel(Unit.PX);
    /**
     * The panel that holds the PressGang logo.
     */
    private final SimplePanel headingBanner = new SimplePanel();
    /**
     * The panel that holds the page title label.
     */
    private final HorizontalPanel pageTitleParentLayoutPanel = new HorizontalPanel();
    /**
     * The page title label.
     */
    private final Label pageTitle = new Label();
    /**
     * The panel that holds the vertical shortcut buttons on the left hand side of the screen.
     */
    private final SimplePanel shortCutPanelParent = new SimplePanel();
    /**
     * The panel that holds the shortcut buttons that are visible by default.
     */
    private final FlexTable shortcutPanel = new FlexTable();
    /**
     * The panel that holds the shortcut buttons visible when the advanced submenu is open.
     */
    private final FlexTable advancedShortcutPanel = new FlexTable();
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
    /**
     * The panel that holds the footer items.
     */
    private final FlexTable footerPanel = new FlexTable();

    /**
     * The feedback link.
     */
    private final Anchor feedback = new Anchor(PressGangCCMSUI.INSTANCE.Feedback());
    /**
     * The help link.
     */
    private final Anchor help = new Anchor(PressGangCCMSUI.INSTANCE.Help());
    /**
     * The version label.
     */
    private final Label version = new Label(PressGangCCMSUI.INSTANCE.Build() + " " + Constants.VERSION);

    private final PushButton home;
    private final PushButton docbuilder;
    private final PushButton createTopic;
    private final PushButton search;
    private final PushButton searchTranslations;
    private final PushButton images;
    private final PushButton tags;
    private final PushButton categories;
    private final PushButton projects;
    private final PushButton bulkTagging;
    private final PushButton stringConstants;
    private final PushButton blobConstants;
    private final PushButton integerConstants;
    private final PushButton propertyTags;
    private final PushButton propertyTagCategories;
    private final PushButton monitoring;
    private final PushButton bug;
    private final PushButton reports;
    private final PushButton advanced;
    private final PushButton advancedOpen;
    private final PushButton close;
    /**
     * The text box where a quick search is entered.
     */
    private final TextBox quickSearchQuery = new TextBox();
    /**
     * The button used to execute the quick search.
     */
    private final PushButton quickSearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.QuickSearch());

    @Override
    public PushButton getMonitoring() {
        return monitoring;
    }

    /**
     * Defines the help dialog box, which is displayed when the help link is clicked.
     *
     * @author Matthew Casperson
     */
    public final static class HelpDialogImpl extends DialogBox implements HelpDialog {

        private final VerticalPanel layout = new VerticalPanel();
        private final HTML contents = new HTML();
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
        public HTML getContents() {
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

            @NotNull final ScrollPanel scroll = new ScrollPanel(contents);
            scroll.setWidth(Constants.HELP_DIALOG_WIDTH);
            scroll.setHeight(Constants.HELP_DIALOG_HEIGHT);

            layout.add(scroll);

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);

            buttonPanel.add(edit);
            buttonPanel.add(ok);

            layout.add(buttonPanel);

            this.add(layout);
        }

        @Override
        public void show(final int topicId, @NotNull final BaseTemplateViewInterface waitDisplay) {
            this.helpTopic = topicId;

            @NotNull final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                    waitDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(@NotNull final RESTTopicV1 retValue, final BaseTemplateViewInterface display) {

                    try {
                        @NotNull final XsltProcessor processor = new XsltProcessor();
                        processor.importStyleSheet(DocbookToHTML.XSL);
                        processor.importSource(retValue.getXml());
                        processor.setParameter("externalImages", true + "");
                        final String resultString = processor.transform();
                        contents.setHTML(resultString);


                    } catch (@NotNull final XsltProcessingException ex) {
                        // this should not happen
                    }

                    HelpDialogImpl.this.center();
                    HelpDialogImpl.this.show();
                    setTitle(PressGangCCMSUI.INSTANCE.Help());
                }
            });
            RESTCalls.getTopic(callback, topicId);
        }
    }

    /**
     * An instance of the help dialog box.
     */
    private final HelpDialog helpDialog = new HelpDialogImpl();

    @NotNull
    @Override
    public final PushButton getHome() {
        return home;
    }

    @NotNull
    @Override
    public final PushButton getDocBuilder() {
        return docbuilder;
    }

    @NotNull
    @Override
    public final Anchor getHelp() {
        return help;
    }

    @NotNull
    @Override
    public final HelpDialog getHelpDialog() {
        return helpDialog;
    }

    @NotNull
    @Override
    public final PushButton getQuickSearch() {
        return quickSearch;
    }

    @NotNull
    @Override
    public final TextBox getQuickSearchQuery() {
        return quickSearchQuery;
    }

    @NotNull
    @Override
    public final PushButton getCreateTopic() {
        return createTopic;
    }

    /**
     * @return true when the view is visible, false otherwise
     */
    @Override
    public final boolean isViewShown() {
        return isViewShown;
    }

    /**
     * @param isViewShown true if the view is visible, false otherwise
     */
    @Override
    public final void setViewShown(final boolean isViewShown) {
        this.isViewShown = isViewShown;
        updateDisplay();
    }

    @NotNull
    @Override
    public final PushButton getClose() {
        return close;
    }

    @NotNull
    @Override
    public final PushButton getAdvancedOpen() {
        return advancedOpen;
    }

    @NotNull
    @Override
    public final SimplePanel getShortCutPanelParent() {
        return shortCutPanelParent;
    }

    @NotNull
    @Override
    public final FlexTable getAdvancedShortcutPanel() {
        return advancedShortcutPanel;
    }

    @NotNull
    @Override
    public final PushButton getAdvanced() {
        return advanced;
    }

    @NotNull
    @Override
    public final PushButton getPropertyTagCategories() {
        return propertyTagCategories;
    }

    @NotNull
    @Override
    public final PushButton getPropertyTags() {
        return propertyTags;
    }

    @NotNull
    @Override
    public final PushButton getIntegerConstants() {
        return integerConstants;
    }

    @NotNull
    @Override
    public final PushButton getBlobConstants() {
        return blobConstants;
    }

    @NotNull
    @Override
    public final PushButton getBulkTagging() {
        return bulkTagging;
    }

    @NotNull
    @Override
    public final PushButton getStringConstants() {
        return stringConstants;
    }

    @NotNull
    @Override
    public final PushButton getProjects() {
        return projects;
    }

    @NotNull
    @Override
    public final PushButton getCategories() {
        return categories;
    }

    @NotNull
    @Override
    public final PushButton getTags() {
        return tags;
    }

    @NotNull
    @Override
    public final PushButton getImages() {
        return images;
    }

    @NotNull
    @Override
    public final Label getPageTitle() {
        return pageTitle;
    }

    @NotNull
    @Override
    public final String getPageName() {
        return pageName;
    }

    @NotNull
    @Override
    public final String getApplicationName() {
        return applicationName;
    }

    @NotNull
    @Override
    public final FlexTable getTopActionParentPanel() {
        return topActionParentPanel;
    }

    @NotNull
    @Override
    public final FlexTable getShortcutPanel() {
        return shortcutPanel;
    }

    @NotNull
    @Override
    public final DockLayoutPanel getTopLevelPanel() {
        return topLevelLayoutPanel;
    }

    @NotNull
    @Override
    public final SimpleLayoutPanel getPanel() {
        return panel;
    }

    @NotNull
    @Override
    public final PushButton getReports() {
        return reports;
    }

    @NotNull
    @Override
    public final PushButton getSearchTranslations() {
        return searchTranslations;
    }

    @NotNull
    @Override
    public final SimplePanel getTopActionGrandParentPanel() {
        return topActionGrandParentPanel;
    }

    @NotNull
    @Override
    public final FlexTable getTopActionPanel() {
        return topActionPanel;
    }

    @NotNull
    @Override
    public final FlexTable getTopViewSpecificRightActionPanel() {
        return topViewSpecificRightActionPanel;
    }

    @NotNull
    @Override
    public final SimplePanel getTopViewSpecificLeftActionPanel() {
        return topViewSpecificLeftActionPanel;
    }

    @NotNull
    @Override
    public final PushButton getBug() {
        return bug;
    }

    @NotNull
    @Override
    public final PushButton getSearch() {
        return search;
    }

    /**
     * The constructor is used to piece the template together.
     * @param applicationName The name of the application. This is not actually used any more, and should be removed.
     * @param pageName The name of the page.
     */
    public BaseTemplateView(@NotNull final String applicationName, @NotNull final String pageName) {
        this.applicationName = applicationName;
        this.pageName = pageName;

        /* Set the heading */
        headingBanner.addStyleName(CSSResources.INSTANCE.appCss().ApplicationHeadingPanel());
        headingBanner.add(new Image(ImageResources.INSTANCE.headingBanner()));

        topLevelLayoutPanel.addStyleName(CSSConstants.Template.TOP_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.addNorth(headingBanner, Constants.HEADING_BANNER_HEIGHT);

        /* Set the second level layout */
        secondLevelLayoutPanel.addStyleName(CSSConstants.Template.SECOND_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.add(secondLevelLayoutPanel);

        /* Set the page title */
        pageTitle.setText(pageName);
        pageTitle.addStyleName(CSSConstants.Template.PAGE_TITLE);
        pageTitleParentLayoutPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        pageTitleParentLayoutPanel.add(pageTitle);

        /* Add the quick search box */
        @NotNull final HorizontalPanel quickSearchParentPanel = new HorizontalPanel();
        quickSearchParentPanel.addStyleName(CSSConstants.Template.QUICK_SEARCH_PARENT_PANEL);
        quickSearchParentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        @NotNull final HorizontalPanel quickSearchPanel = new HorizontalPanel();
        quickSearchPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        quickSearchPanel.add(quickSearchQuery);
        quickSearchPanel.add(getQuickSearch());

        quickSearchParentPanel.add(quickSearchPanel);
        pageTitleParentLayoutPanel.add(quickSearchParentPanel);

        pageTitleParentLayoutPanel.addStyleName(CSSConstants.Template.PAGE_TITLE_PARENT_LAYOUT_PANEL);
        secondLevelLayoutPanel.addNorth(pageTitleParentLayoutPanel, Constants.PAGE_TITLE_BAR_HEIGHT);

        /* Set the remaining content */
        thirdLevelLayoutPanel.addStyleName(CSSConstants.Template.THIRD_LEVEL_LAYOUT_PANEL);
        secondLevelLayoutPanel.add(thirdLevelLayoutPanel);

        /* Set the action bar panel */
        topActionGrandParentPanel.addStyleName(CSSConstants.Template.TOP_ACTION_GRANDPARENT_PANEL);
        topActionParentPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PARENT_PANEL);
        topActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);
        topViewSpecificRightActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);
        topViewSpecificLeftActionPanel.addStyleName(CSSConstants.Template.TOP_ACTION_PANEL);

        topActionParentPanel.setWidget(0, 0, topActionPanel);
        topActionParentPanel.setWidget(0, 1, topViewSpecificLeftActionPanel);
         /* A spacer cell, to push the next cell to the right */
        topActionParentPanel.setWidget(0, 2, new SimplePanel());
        topActionParentPanel.getFlexCellFormatter().setWidth(0, 2, "100%");
        topActionParentPanel.setWidget(0, 3, topViewSpecificRightActionPanel);

        topActionGrandParentPanel.setWidget(topActionParentPanel);

        thirdLevelLayoutPanel.addNorth(topActionGrandParentPanel, Constants.ACTION_BAR_HEIGHT);

        /* Set the footer panel */
        footerPanel.addStyleName(CSSConstants.Template.FOOTER_PANEL);

        thirdLevelLayoutPanel.addSouth(footerPanel, Constants.FOOTER_HEIGHT);

        /* Set the shortcut bar */
        shortCutPanelParent.setWidget(shortcutPanel);
        shortCutPanelParent.addStyleName(CSSConstants.Template.SHORTCUT_PANEL_PARENT);
        shortcutPanel.addStyleName(CSSConstants.Template.SHORTCUT_PANEL);

        thirdLevelLayoutPanel.addWest(shortCutPanelParent, Constants.SHORTCUT_BAR_WIDTH);


        /* Add the version */
        footerPanel.setWidget(0, 0, version);

        /* Add the REST server */
        @NotNull final Label restServer = new Label();

        if (Constants.BASE_URL.equals(Constants.DEV_REST_SERVER)) {
            restServer.addStyleName(CSSConstants.Template.SERVER_TYPE_DEVELOPMENT);
            restServer.setText(PressGangCCMSUI.INSTANCE.DevelopmentServer());
        } else if (Constants.BASE_URL.equals(Constants.PROD_REST_SERVER)) {
            restServer.addStyleName(CSSConstants.Template.SERVER_TYPE_PRODUCTION);
            restServer.setText(PressGangCCMSUI.INSTANCE.ProductionServer());
        } else if (Constants.BASE_URL.equals(Constants.LOCAL_REST_SERVER)) {
            restServer.addStyleName(CSSConstants.Template.SERVER_TYPE_DEVELOPMENT);
            restServer.setText(PressGangCCMSUI.INSTANCE.LocalServer());
        } else {
            restServer.setText(PressGangCCMSUI.INSTANCE.OtherServer());
            restServer.addStyleName(CSSConstants.Template.SERVER_TYPE_DEVELOPMENT);
        }

        footerPanel.setWidget(0, footerPanel.getCellCount(0), new Label("|"));
        footerPanel.setWidget(0, footerPanel.getCellCount(0), restServer);

        /* Add the feedback link */
        addRightAlignedActionButtonPaddingPanel(footerPanel);
        footerPanel.setWidget(0, footerPanel.getCellCount(0), help);
        footerPanel.setWidget(0, footerPanel.getCellCount(0), new Label("|"));
        footerPanel.setWidget(0, footerPanel.getCellCount(0), feedback);

        /* Add the content panel */
        panel.addStyleName(CSSConstants.Template.CONTENT_LAYOUT_PANEL);

        thirdLevelLayoutPanel.add(panel);

        /* Build the shortcut panel */

        home = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Home());
        addShortcutButton(home);

        docbuilder = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.DocBuilder());
        addShortcutButton(docbuilder);

        createTopic = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.CreateTopic());
        addShortcutButton(createTopic);

        search = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Search());
        addShortcutButton(search);

        searchTranslations = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.SearchTranslations());
        addShortcutButton(searchTranslations);

        images = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Images());
        addShortcutButton(images);

        tags = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
        addShortcutButton(tags);

        categories = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Categories());
        addShortcutButton(categories);

        projects = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Projects());
        addShortcutButton(projects);

        /* Only add the reports button if the URL is not null */
        reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true);
        if (Constants.BIRT_URL != null) {
            addShortcutButton(reports);
        }

        bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true);
        addShortcutButton(bug);

        advanced = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Advanced(), true);
        addShortcutButton(advanced);

        advancedOpen = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Advanced(), true);
        addShortcutButton(advancedOpen, advancedShortcutPanel);

        bulkTagging = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.BulkTagging());
        addShortcutButton(bulkTagging, advancedShortcutPanel);

        stringConstants = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.StringConstants());
        addShortcutButton(stringConstants, advancedShortcutPanel);

        blobConstants = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.BlobConstants());
        addShortcutButton(blobConstants, advancedShortcutPanel);

        integerConstants = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.IntegerConstants());
        addShortcutButton(integerConstants, advancedShortcutPanel);

        propertyTags = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.PropertyTags());
        addShortcutButton(propertyTags, advancedShortcutPanel);

        propertyTagCategories = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.PropertyTagCategories());
        addShortcutButton(propertyTagCategories, advancedShortcutPanel);

        monitoring = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Monitoring(), false, true);
        addShortcutButton(monitoring, advancedShortcutPanel);

        close = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CloseSubMenu());
        addShortcutButton(close, advancedShortcutPanel);
    }

    @Override
    public final void showRegularMenu() {
        topActionParentPanel.clear();
        topActionParentPanel.add(topActionPanel);
    }

    public final void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement) {
        replaceTopActionButton(existing, replacement, this.topActionPanel);
    }

    public final void removeTopActionButton(@NotNull final Widget existing) {
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

    public final void replaceTopActionButton(@NotNull final Widget existing, @NotNull final Widget replacement, @NotNull final FlexTable table) {

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

    public final void addActionButton(@NotNull final Widget widget, @NotNull final FlexTable table) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, widget);
    }

    public final void addLocalActionButton(@NotNull final Widget widget) {
        addActionButton(widget, this.getTopViewSpecificRightActionPanel());
    }

    public final void addActionButton(@NotNull final Widget widget) {
        addActionButton(widget, this.getTopActionPanel());
    }

    private void addShortcutButton(@NotNull final Widget widget) {
        addShortcutButton(widget, this.shortcutPanel);
    }

    private void addShortcutButton(@NotNull final Widget widget, @NotNull final FlexTable table) {
        final int rows = table.getRowCount();
        table.setWidget(rows, 0, widget);
    }

    /**
     * When combining views into a single merged view, the shortcut panels need to have a spacer placed above them to replace
     * the template action bar, which is removed.
     */
    protected final void addSpacerToShortcutPanels() {
        @NotNull final String spacerDiv = "<div style=\"height: " + Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px;\"></div>";
        @NotNull final HTML spacer = new HTML(spacerDiv);
        @NotNull final HTML spacer2 = new HTML(spacerDiv);
        this.getShortcutPanel().insertRow(0);
        this.getShortcutPanel().setWidget(0, 0, spacer);
        this.getAdvancedShortcutPanel().insertRow(0);
        this.getAdvancedShortcutPanel().setWidget(0, 0, spacer2);
    }

    /**
     * When combining views into a single merged view, the shortcut panels need to have a spacer placed above them to replace
     * the template action bar, which is removed.
     *
     * The button supplied to this method is placed in the spacer cell.
     */
    protected final void addButtonToShortcutPanels(@NotNull final PushButton button) {
        @NotNull final String spacerDiv = "<div style=\"height: " + Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px;\"></div>";

        this.getShortcutPanel().insertRow(0);
        final SimplePanel panel = new SimplePanel();
        panel.setWidget(button);
        this.getShortcutPanel().setWidget(0, 0, panel);
        this.getShortcutPanel().getCellFormatter().setHeight(0, 0, Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px");
        this.getShortcutPanel().getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

        @NotNull final HTML spacer2 = new HTML(spacerDiv);
        this.getAdvancedShortcutPanel().insertRow(0);
        this.getAdvancedShortcutPanel().setWidget(0, 0, spacer2);
    }

    /**
     * Called when some background operation that would cause this view to be locked is started.
     */
    @Override
    public final void addWaitOperation() {
        ++waitingCount;
        updateDisplay();
    }

    /**
     * Called when some background operation that would cause this view to be locked is ended.
     */
    @Override
    public final void removeWaitOperation() {
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

    @Override
    public final void setFeedbackLink(@NotNull final String link) {
        feedback.setHref(link);
    }

}