package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is used to build the standard page template.
 * 
 * @author Matthew Casperson
 * 
 */
public abstract class BaseTemplateView
        implements BaseTemplateViewInterface {
    /** true when the view is visible, false otherwise */
    private boolean isViewShown;
    /** Maintains a count of how many waiting operations are in progress */
    private int waitingCount;

    /** The name of the application. */
    private final String applicationName;
    /** The name of the current page. */
    private final String pageName;

    /** Defines the top level layout that holds the header and the other content. */
    private final DockLayoutPanel topLevelLayoutPanel = new DockLayoutPanel(Unit.PX);

    /** Defines the panel that holds the page title and the other content. */
    private final DockLayoutPanel secondLevelLayoutPanel = new DockLayoutPanel(Unit.EM);

    /** Defines the panel that holds the shortcut bar, content and footer. */
    private final DockLayoutPanel thirdLevelLayoutPanel = new DockLayoutPanel(Unit.PX);

    private final SimplePanel headingBanner = new SimplePanel();
    private final HorizontalPanel pageTitleParentLayoutPanel = new HorizontalPanel();
    private final Label pageTitle = new Label();

    private final SimplePanel shortCutPanelParent = new SimplePanel();
    private final FlexTable shortcutPanel = new FlexTable();
    private final FlexTable advancedShortcutPanel = new FlexTable();

    private SimpleLayoutPanel panel = new SimpleLayoutPanel();

    /** This panel holds the buttons currently displayed in the top action bar. */
    private final SimplePanel topActionParentPanel = new SimplePanel();
    /** This is the default collection of top action bar items. */
    private final FlexTable topActionPanel = new FlexTable();
    private final FlexTable footerPanel = new FlexTable();

    /** The feedback link */
    private final Anchor feedback = new Anchor(PressGangCCMSUI.INSTANCE.Feedback());
    private final Anchor help = new Anchor(PressGangCCMSUI.INSTANCE.Help());
    /** The version label */
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

    public class HelpDialogImpl extends DialogBox implements HelpDialog {

        private final VerticalPanel layout = new VerticalPanel();
        private final HTML contents = new HTML("div");
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());

        @Override
        public final PushButton getOK() {
            return this.ok;
        }

        @Override
        public final DialogBox getDialogBox() {
            return this;
        }

        @Override
        public final HTML getContents() {
            return this.contents;
        }
        
        public HelpDialogImpl()
        {
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
                    }) {

            };
            RESTCalls.getTopic(callback, topicId);

        }

    }

    private final HelpDialog helpDialog = new HelpDialogImpl();

    @Override
    public PushButton getHome() {
        return home;
    }

    @Override
    public Anchor getHelp() {
        return help;
    }

    @Override
    public HelpDialog getHelpDialog() {
        return helpDialog;
    }

    @Override
    public PushButton getQuickSearch() {
        return quickSearch;
    }

    @Override
    public TextBox getQuickSearchQuery() {
        return quickSearchQuery;
    }

    @Override
    public PushButton getCreateTopic() {
        return createTopic;
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

    @Override
    public PushButton getClose() {
        return close;
    }

    @Override
    public PushButton getAdvancedOpen() {
        return advancedOpen;
    }

    @Override
    public SimplePanel getShortCutPanelParent() {
        return shortCutPanelParent;
    }

    @Override
    public FlexTable getAdvancedShortcutPanel() {
        return advancedShortcutPanel;
    }

    @Override
    public PushButton getAdvanced() {
        return advanced;
    }

    @Override
    public PushButton getPropertyTagCategories() {
        return propertyTagCategories;
    }

    @Override
    public PushButton getPropertyTags() {
        return propertyTags;
    }

    @Override
    public PushButton getRoles() {
        return roles;
    }

    @Override
    public PushButton getUsers() {
        return users;
    }

    @Override
    public PushButton getIntegerConstants() {
        return integerConstants;
    }

    @Override
    public PushButton getBlobConstants() {
        return blobConstants;
    }

    @Override
    public PushButton getStringConstants() {
        return stringConstants;
    }

    @Override
    public PushButton getProjects() {
        return projects;
    }

    @Override
    public PushButton getCategories() {
        return categories;
    }

    @Override
    public PushButton getTags() {
        return tags;
    }

    @Override
    public PushButton getImages() {
        return images;
    }

    @Override
    public Label getPageTitle() {
        return pageTitle;
    }

    @Override
    public String getPageName() {
        return pageName;
    }

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public SimplePanel getTopActionParentPanel() {
        return topActionParentPanel;
    }

    @Override
    public FlexTable getShortcutPanel() {
        return shortcutPanel;
    }

    @Override
    public DockLayoutPanel getTopLevelPanel() {
        return topLevelLayoutPanel;
    }

    @Override
    public SimpleLayoutPanel getPanel() {
        return panel;
    }

    public PushButton getReports() {
        return reports;
    }

    public PushButton getSearchTranslations() {
        return searchTranslations;
    }

    @Override
    public FlexTable getTopActionPanel() {
        return topActionPanel;
    }

    @Override
    public PushButton getBug() {
        return bug;
    }

    @Override
    public PushButton getSearch() {
        return search;
    }

    public BaseTemplateView(final String applicationName, final String pageName) {
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
        topActionParentPanel.addStyleName(CSSConstants.TOP_ACTION_PARENT_PANEL);
        topActionPanel.addStyleName(CSSConstants.TOP_ACTION_PANEL);

        topActionParentPanel.add(topActionPanel);

        thirdLevelLayoutPanel.addNorth(topActionParentPanel, Constants.ACTION_BAR_HEIGHT);

        /* Set the footer panel */
        footerPanel.addStyleName(CSSConstants.FOOTER_PANEL);

        thirdLevelLayoutPanel.addSouth(footerPanel, Constants.FOOTER_HEIGHT);

        /* Set the shortcut bar */
        shortCutPanelParent.setWidget(shortcutPanel);
        shortCutPanelParent.addStyleName(CSSConstants.SHORTCUT_PANEL_PARENT);
        shortcutPanel.addStyleName(CSSConstants.SHORTCUT_PANEL);

        thirdLevelLayoutPanel.addWest(shortCutPanelParent, Constants.SHORTCUT_BAR_WIDTH);

        /* Add the feedback link */
        footerPanel.setWidget(0, 0, version);
        addRightAlignedActionButtonPaddingPanel(footerPanel);
        footerPanel.setWidget(0, 2, help);
        footerPanel.setWidget(0, 3, new Label("|"));
        footerPanel.setWidget(0, 4, feedback);

        /* Add the content panel */
        panel.addStyleName(CSSConstants.CONTENT_LAYOUT_PANEL);

        thirdLevelLayoutPanel.add(panel);

        /* Build the shortcut panel */

        home = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Home());
        addShortcutButton(home);

        createTopic = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateTopic());
        addShortcutButton(createTopic);

        search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
        addShortcutButton(search);

        searchTranslations = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.SearchTranslations());
        searchTranslations.setEnabled(false);
        addShortcutButton(searchTranslations);

        images = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Images());
        addShortcutButton(images);

        tags = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Tags());
        addShortcutButton(tags);

        categories = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Categories());
        addShortcutButton(categories);

        projects = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Projects());
        addShortcutButton(projects);

        reports = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Reports());
        reports.setEnabled(false);
        addShortcutButton(reports);

        bug = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CreateBug());
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
    public void showRegularMenu() {
        topActionParentPanel.clear();
        topActionParentPanel.add(topActionPanel);
    }

    protected void addRightAlignedActionButtonPaddingPanel() {
        addRightAlignedActionButtonPaddingPanel(this.getTopActionPanel());
    }

    protected void addRightAlignedActionButtonPaddingPanel(final FlexTable table) {
        final int rows = table.getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = table.getCellCount(0);
        }

        table.setWidget(0, columns, new SimplePanel());
        table.getCellFormatter().addStyleName(0, columns, CSSConstants.RIGHT_ALIGNED_ACTION_BUTTONS);
    }

    protected void addActionButton(final Widget widget) {
        final int rows = this.getTopActionPanel().getRowCount();
        int columns = 0;
        if (rows != 0) {
            columns = this.getTopActionPanel().getCellCount(0);
        }

        this.getTopActionPanel().setWidget(0, columns, widget);
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
    protected void addSpacerToShortcutPanels() {
        final String spacerDiv = "<div style=\"height: " + Constants.ACTION_BAR_HEIGHT + "px;\"></div>";
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
    public void addWaitOperation() {
        ++waitingCount;
        updateDisplay();
    }

    /**
     * Called when some background operation that would cause this view to be locked is ended.
     */
    @Override
    public void removeWaitOperation() {
        if (waitingCount < 1)
            throw new IllegalStateException("waitingCount should never be less than one when removeWaitOperation() is called.");

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
    public void setFeedbackLink(final String link) {
        feedback.setHref(link);
    }
 
}