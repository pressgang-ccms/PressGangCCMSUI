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
 * This class is used to build the standard page template.
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
    private final String applicationName;
    /**
     * The name of the current page.
     */
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

    private final SimplePanel headingBanner = new SimplePanel();
    private final HorizontalPanel pageTitleParentLayoutPanel = new HorizontalPanel();
    private final Label pageTitle = new Label();

    private final SimplePanel shortCutPanelParent = new SimplePanel();
    private final FlexTable shortcutPanel = new FlexTable();
    private final FlexTable advancedShortcutPanel = new FlexTable();

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
     * This is the collection the local action panel buttons.
     */
    private final FlexTable topViewSpecificActionPanel = new FlexTable();
    /**
     * This is the collection of view specific action buttons.
     */
    private final FlexTable topActionPanel = new FlexTable();
    private final FlexTable footerPanel = new FlexTable();

    /**
     * The feedback link
     */
    private final Anchor feedback = new Anchor(PressGangCCMSUI.INSTANCE.Feedback());
    private final Anchor help = new Anchor(PressGangCCMSUI.INSTANCE.Help());
    /**
     * The version label
     */
    private final Label version = new Label(PressGangCCMSUI.INSTANCE.Build() + " " + Constants.VERSION);

    private final PushButton home;
    private final PushButton createTopic;
    private final PushButton search;
    private final PushButton searchTranslations;
    private final PushButton images;
    private final PushButton tags;
    private final PushButton categories;
    private final PushButton projects;
    private final PushButton stringConstants;
    private final PushButton blobConstants;
    private final PushButton integerConstants;
    private final PushButton users;
    private final PushButton roles;
    private final PushButton propertyTags;
    private final PushButton propertyTagCategories;
    private final PushButton bug;
    private final PushButton reports;
    private final PushButton advanced;
    private final PushButton advancedOpen;
    private final PushButton close;

    private final TextBox quickSearchQuery = new TextBox();
    private final PushButton quickSearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.QuickSearch());

    /**
     * Defines the help dialog box.
     *
     * @author Matthew Casperson
     */
    public final static class HelpDialogImpl extends DialogBox implements HelpDialog {

        private final VerticalPanel layout = new VerticalPanel();
        private final HTML contents = new HTML("div");
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());

        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public HTML getContents() {
            return this.contents;
        }

        public HelpDialogImpl() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.Help());

            contents.addStyleName(CSSConstants.HelpDialog.HELP_CONTENTS);

            final ScrollPanel scroll = new ScrollPanel(contents);
            scroll.setWidth(Constants.HELP_DIALOG_WIDTH);
            scroll.setHeight(Constants.HELP_DIALOG_HEIGHT);

            layout.add(scroll);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(ok);

            layout.add(buttonPanel);

            this.add(layout);
        }

        @Override
        public void show(final int topicId, final BaseTemplateViewInterface waitDisplay) {
            final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, BaseTemplateViewInterface>(
                    waitDisplay, new BaseRestCallback.SuccessAction<RESTTopicV1, BaseTemplateViewInterface>() {
                @Override
                public void doSuccessAction(final RESTTopicV1 retValue, final BaseTemplateViewInterface display) {

                    try {
                        final XsltProcessor processor = new XsltProcessor();
                        processor.importStyleSheet(DocbookToHTML.XSL);
                        processor.importSource(retValue.getXml());
                        processor.setParameter("externalImages", true + "");
                        final String resultString = processor.transform();
                        contents.setHTML(resultString);


                    } catch (final XsltProcessingException ex) {
                        // this should not happen
                    }

                    HelpDialogImpl.this.center();
                    HelpDialogImpl.this.show();
                }
            });
            RESTCalls.getTopic(callback, topicId);
        }
    }

    private final HelpDialog helpDialog = new HelpDialogImpl();

    @Override
    public final PushButton getHome() {
        return home;
    }

    @Override
    public final Anchor getHelp() {
        return help;
    }

    @Override
    public final HelpDialog getHelpDialog() {
        return helpDialog;
    }

    @Override
    public final PushButton getQuickSearch() {
        return quickSearch;
    }

    @Override
    public final TextBox getQuickSearchQuery() {
        return quickSearchQuery;
    }

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

    @Override
    public final PushButton getClose() {
        return close;
    }

    @Override
    public final PushButton getAdvancedOpen() {
        return advancedOpen;
    }

    @Override
    public final SimplePanel getShortCutPanelParent() {
        return shortCutPanelParent;
    }

    @Override
    public final FlexTable getAdvancedShortcutPanel() {
        return advancedShortcutPanel;
    }

    @Override
    public final PushButton getAdvanced() {
        return advanced;
    }

    @Override
    public final PushButton getPropertyTagCategories() {
        return propertyTagCategories;
    }

    @Override
    public final PushButton getPropertyTags() {
        return propertyTags;
    }

    @Override
    public final PushButton getRoles() {
        return roles;
    }

    @Override
    public final PushButton getUsers() {
        return users;
    }

    @Override
    public final PushButton getIntegerConstants() {
        return integerConstants;
    }

    @Override
    public final PushButton getBlobConstants() {
        return blobConstants;
    }

    @Override
    public final PushButton getStringConstants() {
        return stringConstants;
    }

    @Override
    public final PushButton getProjects() {
        return projects;
    }

    @Override
    public final PushButton getCategories() {
        return categories;
    }

    @Override
    public final PushButton getTags() {
        return tags;
    }

    @Override
    public final PushButton getImages() {
        return images;
    }

    @Override
    public final Label getPageTitle() {
        return pageTitle;
    }

    @Override
    public final String getPageName() {
        return pageName;
    }

    @Override
    public final String getApplicationName() {
        return applicationName;
    }

    @Override
    public final FlexTable getTopActionParentPanel() {
        return topActionParentPanel;
    }

    @Override
    public final FlexTable getShortcutPanel() {
        return shortcutPanel;
    }

    @Override
    public final DockLayoutPanel getTopLevelPanel() {
        return topLevelLayoutPanel;
    }

    @Override
    public final SimpleLayoutPanel getPanel() {
        return panel;
    }

    @Override
    public final PushButton getReports() {
        return reports;
    }

    @Override
    public final PushButton getSearchTranslations() {
        return searchTranslations;
    }

    @Override
    public final SimplePanel getTopActionGrandParentPanel() {
        return topActionGrandParentPanel;
    }

    @Override
    public final FlexTable getTopActionPanel() {
        return topActionPanel;
    }

    @Override
    public final FlexTable getTopViewSpecificActionPanel() {
        return topViewSpecificActionPanel;
    }

    @Override
    public final PushButton getBug() {
        return bug;
    }

    @Override
    public final PushButton getSearch() {
        return search;
    }

    public BaseTemplateView(@NotNull final String applicationName, @NotNull final String pageName) {
        this.applicationName = applicationName;
        this.pageName = pageName;

        /* Set the heading */
        headingBanner.addStyleName(CSSResources.INSTANCE.appCss().ApplicationHeadingPanel());
        headingBanner.add(new Image(ImageResources.INSTANCE.headingBanner()));

        topLevelLayoutPanel.addStyleName(CSSConstants.TOP_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.addNorth(headingBanner, Constants.HEADING_BANNER_HEIGHT);

        /* Set the second level layout */
        secondLevelLayoutPanel.addStyleName(CSSConstants.SECOND_LEVEL_LAYOUT_PANEL);
        topLevelLayoutPanel.add(secondLevelLayoutPanel);

        /* Set the page title */
        pageTitle.setText(pageName);
        pageTitle.addStyleName(CSSConstants.PAGE_TITLE);
        pageTitleParentLayoutPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        pageTitleParentLayoutPanel.add(pageTitle);

        /* Add the quick search box */
        final HorizontalPanel quickSearchParentPanel = new HorizontalPanel();
        quickSearchParentPanel.addStyleName(CSSConstants.QUICK_SEARCH_PARENT_PANEL);
        quickSearchParentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        final HorizontalPanel quickSearchPanel = new HorizontalPanel();
        quickSearchPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        quickSearchPanel.add(quickSearchQuery);
        quickSearchPanel.add(getQuickSearch());

        quickSearchParentPanel.add(quickSearchPanel);
        pageTitleParentLayoutPanel.add(quickSearchParentPanel);

        pageTitleParentLayoutPanel.addStyleName(CSSConstants.PAGE_TITLE_PARENT_LAYOUT_PANEL);
        secondLevelLayoutPanel.addNorth(pageTitleParentLayoutPanel, Constants.PAGE_TITLE_BAR_HEIGHT);

        /* Set the remaining content */
        thirdLevelLayoutPanel.addStyleName(CSSConstants.THIRD_LEVEL_LAYOUT_PANEL);
        secondLevelLayoutPanel.add(thirdLevelLayoutPanel);

        /* Set the action bar panel */
        topActionGrandParentPanel.addStyleName(CSSConstants.TOP_ACTION_GRANDPARENT_PANEL);
        topActionParentPanel.addStyleName(CSSConstants.TOP_ACTION_PARENT_PANEL);
        topActionPanel.addStyleName(CSSConstants.TOP_ACTION_PANEL);
        topViewSpecificActionPanel.addStyleName(CSSConstants.TOP_ACTION_PANEL);

        topActionParentPanel.setWidget(0, 0, topActionPanel);
        /* A spacer cell, to push the next cell to the right */
        topActionParentPanel.setWidget(0, 1, new SimplePanel());
        topActionParentPanel.setWidget(0, 2, topViewSpecificActionPanel);
        topActionParentPanel.getFlexCellFormatter().setWidth(0, 1, "100%");


        thirdLevelLayoutPanel.addNorth(topActionGrandParentPanel, Constants.ACTION_BAR_HEIGHT);

        /* Set the footer panel */
        footerPanel.addStyleName(CSSConstants.FOOTER_PANEL);

        thirdLevelLayoutPanel.addSouth(footerPanel, Constants.FOOTER_HEIGHT);

        /* Set the shortcut bar */
        shortCutPanelParent.setWidget(shortcutPanel);
        shortCutPanelParent.addStyleName(CSSConstants.SHORTCUT_PANEL_PARENT);
        shortcutPanel.addStyleName(CSSConstants.SHORTCUT_PANEL);

        thirdLevelLayoutPanel.addWest(shortCutPanelParent, Constants.SHORTCUT_BAR_WIDTH);


        /* Add the version */
        footerPanel.setWidget(0, 0, version);

        /* Add the REST server */
        final Label restServer = new Label();
        if (Constants.BASE_URL == Constants.DEV_REST_SERVER) {
            restServer.setText(PressGangCCMSUI.INSTANCE.DevelopmentServer());
        } else if (Constants.BASE_URL == Constants.PROD_REST_SERVER) {
            restServer.setText(PressGangCCMSUI.INSTANCE.ProductionServer());
        } else if (Constants.BASE_URL == Constants.LOCAL_REST_SERVER) {
            restServer.setText(PressGangCCMSUI.INSTANCE.LocalServer());
        } else {
            restServer.setText(PressGangCCMSUI.INSTANCE.OtherServer());
        }

        footerPanel.setWidget(0, footerPanel.getCellCount(0), new Label("|"));
        footerPanel.setWidget(0, footerPanel.getCellCount(0), restServer);

        /* Add the feedback link */
        addRightAlignedActionButtonPaddingPanel(footerPanel);
        footerPanel.setWidget(0, footerPanel.getCellCount(0), help);
        footerPanel.setWidget(0, footerPanel.getCellCount(0), new Label("|"));
        footerPanel.setWidget(0, footerPanel.getCellCount(0), feedback);

        /* Add the content panel */
        panel.addStyleName(CSSConstants.CONTENT_LAYOUT_PANEL);

        thirdLevelLayoutPanel.add(panel);

        /* Build the shortcut panel */

        home = UIUtilities.createLeftSideTabPushButton(PressGangCCMSUI.INSTANCE.Home());
        addShortcutButton(home);

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

        reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports(), false, true);
        addShortcutButton(reports);

        bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug(), false, true);
        addShortcutButton(bug);

        advanced = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Advanced(), true);
        addShortcutButton(advanced);

        advancedOpen = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Advanced(), true);
        addShortcutButton(advancedOpen, advancedShortcutPanel);

        users = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Users());
        users.setEnabled(false);
        addShortcutButton(users, advancedShortcutPanel);

        roles = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Roles());
        roles.setEnabled(false);
        addShortcutButton(roles, advancedShortcutPanel);

        stringConstants = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.StringConstants());
        stringConstants.setEnabled(false);
        addShortcutButton(stringConstants, advancedShortcutPanel);

        blobConstants = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.BlobConstants());
        blobConstants.setEnabled(false);
        addShortcutButton(blobConstants, advancedShortcutPanel);

        integerConstants = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.IntegerConstants());
        integerConstants.setEnabled(false);
        addShortcutButton(integerConstants, advancedShortcutPanel);

        propertyTags = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.PropertyTags());
        propertyTags.setEnabled(false);
        addShortcutButton(propertyTags, advancedShortcutPanel);

        propertyTagCategories = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.PropertyTagCategories());
        propertyTagCategories.setEnabled(false);
        addShortcutButton(propertyTagCategories, advancedShortcutPanel);

        close = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CloseSubMenu());
        addShortcutButton(close, advancedShortcutPanel);
    }

    @Override
    public final void showRegularMenu() {
        topActionParentPanel.clear();
        topActionParentPanel.add(topActionPanel);
    }

    public final void replaceTopActionButton(final Widget existing, final Widget replacement) {
        replaceTopActionButton(existing, replacement, this.topActionPanel);
    }

    public final void replaceTopActionButton(final Widget existing, final Widget replacement, final FlexTable table) {

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

    private void addRightAlignedActionButtonPaddingPanel(final FlexTable table) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, new SimplePanel());
        table.getCellFormatter().addStyleName(0, columns, CSSConstants.RIGHT_ALIGNED_ACTION_BUTTONS);
    }

    public final void addActionButton(final Widget widget, final FlexTable table) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, widget);
    }

    protected final void addLocalActionButton(final Widget widget) {
        addActionButton(widget, this.getTopViewSpecificActionPanel());
    }

    protected final void addActionButton(final Widget widget) {
        addActionButton(widget, this.getTopActionPanel());
    }

    private void addShortcutButton(final Widget widget) {
        addShortcutButton(widget, this.shortcutPanel);
    }

    private void addShortcutButton(final Widget widget, final FlexTable table) {
        final int rows = table.getRowCount();
        table.setWidget(rows, 0, widget);
    }

    /**
     * When combining views into a single merged view, the shortcuit panels need to have a spacer placed above them to replace
     * the template action bar, which is removed.
     */
    protected final void addSpacerToShortcutPanels() {
        final String spacerDiv = "<div style=\"height: " + Constants.SHORTCUT_BAR_SPACER_HEIGHT + "px;\"></div>";
        final HTML spacer = new HTML(spacerDiv);
        final HTML spacer2 = new HTML(spacerDiv);
        this.getShortcutPanel().insertRow(0);
        this.getShortcutPanel().setWidget(0, 0, spacer);
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
    public final void setFeedbackLink(final String link) {
        feedback.setHref(link);
    }

}