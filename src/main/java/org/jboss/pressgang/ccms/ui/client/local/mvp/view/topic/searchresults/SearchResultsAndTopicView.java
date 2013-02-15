package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view that combines the topic search results with the individual topic views 
 * @author Matthew Casperson
 */
public class SearchResultsAndTopicView extends
        BaseSearchAndEditView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchResultsAndTopicPresenter.Display {

    /** An instance of the message log dialog box */
    private final LogMessageView messageLogDialog = new LogMessageView();
    
    /** The type of split used to display the rendered XML */
    private SplitType splitType = SplitType.NULL;

    private final PushButton fields;
    private final PushButton extendedProperties;
    private final PushButton xml;
    private final PushButton xmlErrors;
    private final PushButton rendered;
    private final PushButton topicTags;
    private final PushButton save;
    private final PushButton bugs;
    private final PushButton history;
    private final PushButton csps;
    private final PushButton urls;

    private final Label fieldsDown;
    private final Label xmlDown;
    private final Label xmlErrorsDown;
    private final Label renderedDown;
    private final Label tagsDown;
    private final Label bugsDown;
    private final Label historyDown;
    private final Label extendedPropertiesDown;
    private final Label urlsDown;

    private final FlexTable renderedSplitViewMenu = new FlexTable();
    private final PushButton renderedSplit;
    private final PushButton renderedNoSplit;
    private final PushButton renderedVerticalSplit;
    private final PushButton renderedHorizontalSplit;
    private final Label renderedNoSplitDown;
    private final Label renderedVerticalSplitDown;
    private final Label renderedHorizontalSplitDown;
    private final PushButton renderedSplitClose;
    private final PushButton renderedSplitOpen;

    protected boolean readOnly = false;

    @Override
    public PushButton getUrls() {
        return urls;
    }

    @Override
    public Label getUrlsDown() {
        return urlsDown;
    }

    @Override
    public PushButton getCsps() {
        return csps;
    }

    @Override
    public Label getFieldsDown() {
        return fieldsDown;
    }

    @Override
    public Label getXmlDown() {
        return xmlDown;
    }

    @Override
    public Label getXmlErrorsDown() {
        return xmlErrorsDown;
    }

    @Override
    public Label getRenderedDown() {
        return renderedDown;
    }

    @Override
    public Label getTopicTagsDown() {
        return tagsDown;
    }

    @Override
    public Label getBugsDown() {
        return bugsDown;
    }

    @Override
    public Label getHistoryDown() {
        return historyDown;
    }

    @Override
    public PushButton getRenderedSplitOpen() {
        return renderedSplitOpen;
    }

    @Override
    public PushButton getRenderedHorizontalSplit() {
        return renderedHorizontalSplit;
    }

    @Override
    public FlexTable getRenderedSplitViewMenu() {
        return renderedSplitViewMenu;
    }

    @Override
    public PushButton getRenderedSplitClose() {
        return renderedSplitClose;
    }

    @Override
    public PushButton getRenderedVerticalSplit() {
        return renderedVerticalSplit;
    }

    @Override
    public PushButton getRenderedNoSplit() {
        return renderedNoSplit;
    }

    @Override
    public PushButton getRenderedSplit() {
        return renderedSplit;
    }

    @Override
    public PushButton getHistory() {
        return history;
    }

    @Override
    public PushButton getBugs() {
        return bugs;
    }

    @Override
    public PushButton getTopicTags() {
        return topicTags;
    }

    @Override
    public PushButton getXmlErrors() {
        return xmlErrors;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public PushButton getRendered() {
        return rendered;
    }

    @Override
    public PushButton getXml() {
        return xml;
    }

    @Override
    public PushButton getFields() {
        return fields;
    }

    @Override
    public Label getExtendedPropertiesDown()
    {
        return extendedPropertiesDown;
    }

    @Override
    public PushButton getExtendedProperties() {
        return extendedProperties;
    }

    @Override
    public LogMessageView getMessageLogDialog() {
        return messageLogDialog;
    }

    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    public SearchResultsAndTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        renderedSplitViewMenu.addStyleName(CSSConstants.RENDERED_SPLIT_VIEW_MENU_TABLE);

        /* Build the action bar icons */
        renderedSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true);
        rendered = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedView());
        xml = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.XMLEditing());
        xmlErrors = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.XMLValidationErrors());
        fields = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Properties());
        extendedProperties = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.PropertyTags());
        save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
        topicTags = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TopicTags());
        bugs = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Bugs());
        history = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Revisions());
        csps = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ContentSpecifications());
        urls = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TopicSourceUrls());

        fieldsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Properties());
        xmlDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.XMLEditing());
        xmlErrorsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.XMLValidationErrors());
        renderedDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.RenderedView());
        tagsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicTags());
        bugsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Bugs());
        historyDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Revisions());
        extendedPropertiesDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.PropertyTags());
        urlsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicSourceUrls());

        renderedSplitOpen = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true);
        renderedNoSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedNoSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedVerticalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedVerticalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedHorizontalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedHorizontalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedSplitClose = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CloseSubMenu());

        // add the filtered results panel
        getSplitPanel().clear();
        getSplitPanel().addWest(this.getResultsViewLayoutPanel(), Constants.SPLIT_PANEL_SIZE);

        this.populateTopActionBar();
    }

    /**
     * The split panel needs to have the center widget added last, which we need to do after optionally added a east or south
     * widget for the rendered view.
     * 
     * @param splitType How the parent panel should be split
     * @param panel The rendered view panel itself
     */
    @Override
    public void initialize(final boolean readOnly, final SplitType splitType, final Panel panel) {

        if (this.splitType != splitType)
        {
            this.splitType = splitType;

            getSplitPanel().clear();

            getSplitPanel().addWest(this.getResultsViewLayoutPanel(), Constants.SPLIT_PANEL_SIZE);

            final SimplePanel renderedPanelParent = new SimplePanel();
            renderedPanelParent.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);
            renderedPanelParent.add(panel);

            if (splitType == SplitType.HORIZONTAL) {
                getSplitPanel().addSouth(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
            } else if (splitType == SplitType.VERTICAL) {
                getSplitPanel().addEast(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
            }

            getSplitPanel().add(this.getViewLayoutPanel());
        }

        this.readOnly = readOnly;

        buildSplitViewButtons(splitType);
    }

    /**
     * This method enables or disables the save button based on the read only state, and also highlights the history button if
     * needed.
     */
    private void fixReadOnlyButtons() {
        this.getSave().setEnabled(!readOnly);

        if (readOnly) {
            this.getHistory().addStyleName(CSSConstants.ALERT_BUTTON);
        } else {
            this.getHistory().removeStyleName(CSSConstants.ALERT_BUTTON);
        }
    }

    private void addOrRemoveRenderedButton(final SplitType splitType)
    {
        /* Add the rendered view button if there is no split screen, and remove if it there is a split screen */
        if (splitType == SplitType.NONE || splitType == SplitType.DISABLED) {
            if (this.getRendered().getParent() == null) {
                this.getTopActionPanel().insertCell(0, 1);
                this.getTopActionPanel().setWidget(0, 1, this.getRendered());
            }
        } else {
            if (this.getRendered().getParent() != null) {
                this.getTopActionPanel().remove(this.getRendered());
                this.getTopActionPanel().removeCell(0, 1);
            }

        }
    }

    public void buildSplitViewButtons(final SplitType splitType) {

        addOrRemoveRenderedButton(splitType);

        renderedSplitViewMenu.clear();

        if (splitType != SplitType.DISABLED) {

            int column = 0;

            renderedSplitViewMenu.setWidget(0, column, renderedSplitOpen);

            ++column;
            if (splitType == SplitType.NONE) {
                renderedSplitViewMenu.setWidget(0, column, renderedNoSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, column, renderedNoSplit);
            }

            ++column;
            if (splitType == SplitType.VERTICAL) {
                renderedSplitViewMenu.setWidget(0, column, renderedVerticalSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, column, renderedVerticalSplit);
            }

            ++column;
            if (splitType == SplitType.HORIZONTAL) {
                renderedSplitViewMenu.setWidget(0, column, renderedHorizontalSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, column, renderedHorizontalSplit);
            }

            ++column;
            renderedSplitViewMenu.setWidget(0, column, renderedSplitClose);
        }
    }

    /** Show the rendered split view menu */
    public void showSplitViewButtons() {
        getTopActionParentPanel().clear();
        getTopActionParentPanel().add(renderedSplitViewMenu);
    }

    /**
     * This method is called to initialize the buttons that should appear in the top action bar.
     */
    private void populateTopActionBar()
    {
        this.getTopActionPanel().removeAllRows();
        fixReadOnlyButtons();

        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getExtendedProperties());
        addActionButton(this.getUrls());
        addActionButton(this.getTopicTags());
        addActionButton(this.getBugs());
        addActionButton(this.getHistory());
        addActionButton(this.getCsps());
        addActionButton(this.getSave());
    }
}
