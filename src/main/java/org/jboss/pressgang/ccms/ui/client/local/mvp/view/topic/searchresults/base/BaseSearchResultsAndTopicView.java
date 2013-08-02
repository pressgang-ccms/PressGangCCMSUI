package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base.BaseTopicFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.DisplaySplitViewCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The view that combines the topic search results with the individual topic views
 *
 * @author Matthew Casperson
 */
public abstract class BaseSearchResultsAndTopicView<
        T extends RESTBaseEntityV1<T, U, V>,
        U extends RESTBaseCollectionV1<T, U, V>,
        V extends RESTBaseCollectionItemV1<T, U, V>> extends
        BaseSearchAndEditView<T, U, V> implements BaseTopicFilteredResultsAndDetailsPresenter.Display<T, U, V> {

    /**
     * An instance of the message log dialog box
     */
    private final LogMessageView messageLogDialog = new LogMessageView();

    /**
     * The type of split used to display the rendered XML
     */
    private SplitType splitType = SplitType.NULL;

    private final PushButton fields;
    private final PushButton extendedProperties;
    private final PushButton xml;
    private final PushButton rendered;
    private final PushButton topicTags;
    private final PushButton bugs;
    private final PushButton urls;

    private final Label fieldsDown;
    private final Label xmlDown;
    private final Label renderedDown;
    private final Label tagsDown;
    private final Label bugsDown;
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

    private final PushButton showHideSearchResults = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HideSearchResults());

    protected boolean readOnly = false;

    @NotNull
    @Override
    public PushButton getShowHideSearchResults() {
        return showHideSearchResults;
    }

    @NotNull
    @Override
    public PushButton getUrls() {
        return urls;
    }

    @NotNull
    @Override
    public Label getUrlsDown() {
        return urlsDown;
    }

    @NotNull
    @Override
    public Label getFieldsDown() {
        return fieldsDown;
    }

    @NotNull
    @Override
    public Label getXmlDown() {
        return xmlDown;
    }

    @NotNull
    @Override
    public Label getRenderedDown() {
        return renderedDown;
    }

    @NotNull
    @Override
    public Label getTopicTagsDown() {
        return tagsDown;
    }

    @NotNull
    @Override
    public Label getBugsDown() {
        return bugsDown;
    }

    @NotNull
    @Override
    public PushButton getRenderedSplitOpen() {
        return renderedSplitOpen;
    }

    @NotNull
    @Override
    public PushButton getRenderedHorizontalSplit() {
        return renderedHorizontalSplit;
    }

    @NotNull
    @Override
    public FlexTable getRenderedSplitViewMenu() {
        return renderedSplitViewMenu;
    }

    @NotNull
    @Override
    public PushButton getRenderedSplitClose() {
        return renderedSplitClose;
    }

    @NotNull
    @Override
    public PushButton getRenderedVerticalSplit() {
        return renderedVerticalSplit;
    }

    @NotNull
    @Override
    public PushButton getRenderedNoSplit() {
        return renderedNoSplit;
    }

    @NotNull
    @Override
    public PushButton getRenderedSplit() {
        return renderedSplit;
    }

    @NotNull
    @Override
    public PushButton getBugs() {
        return bugs;
    }

    @NotNull
    @Override
    public PushButton getTopicTags() {
        return topicTags;
    }


    @NotNull
    @Override
    public PushButton getRendered() {
        return rendered;
    }

    @NotNull
    @Override
    public PushButton getXml() {
        return xml;
    }

    @NotNull
    @Override
    public PushButton getFields() {
        return fields;
    }

    @NotNull
    @Override
    public Label getExtendedPropertiesDown() {
        return extendedPropertiesDown;
    }

    @NotNull
    @Override
    public PushButton getExtendedProperties() {
        return extendedProperties;
    }

    @NotNull
    @Override
    public LogMessageView getMessageLogDialog() {
        return messageLogDialog;
    }

    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    public BaseSearchResultsAndTopicView(@NotNull final String applicationName, @NotNull final String pageName) {
        super(applicationName, pageName);

        renderedSplitViewMenu.addStyleName(CSSConstants.TopicView.RENDERED_SPLIT_VIEW_MENU_TABLE);

        /* Build the action bar icons */
        renderedSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true, Constants.ElementIDs.RENDERED_PANE_TOPIC_EDIT_BUTTON_ID.getId());
        rendered = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.RenderedView(), Constants.ElementIDs.RENDERED_VIEW_TOPIC_EDIT_BUTTON_ID.getId());
        xml = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.XMLEditing(), Constants.ElementIDs.XML_EDITING_TOPIC_EDIT_BUTTON_ID.getId());
        fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Properties(), Constants.ElementIDs.PROPERTIES_TOPIC_EDIT_BUTTON_ID.getId());
        extendedProperties = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.PropertyTags(), Constants.ElementIDs.EXTENDED_PROPERTIES_TOPIC_EDIT_BUTTON_ID.getId());
        topicTags = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TopicTags(), Constants.ElementIDs.TAGS_TOPIC_EDIT_BUTTON_ID.getId());
        bugs = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Bugs(), Constants.ElementIDs.BUGS_TOPIC_EDIT_BUTTON_ID.getId());
        urls = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.TopicSourceUrls(), Constants.ElementIDs.SOURCE_URLS_TOPIC_EDIT_BUTTON_ID.getId());

        fieldsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Properties());
        xmlDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.XMLEditing());
        renderedDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.RenderedView());
        tagsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TopicTags());
        bugsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Bugs());
        extendedPropertiesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.PropertyTags());
        urlsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.TopicSourceUrls());

        renderedSplitOpen = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true);
        renderedNoSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedNoSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedVerticalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedVerticalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedHorizontalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedHorizontalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedSplitClose = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CloseSubMenu());

        getShortcuts().setSpacerButton(showHideSearchResults);

        // add the filtered results panel
        getSplitPanel().clear();
        getSplitPanel().addWest(this.getResultsViewLayoutPanel(), Constants.SPLIT_PANEL_SIZE);
        getSplitPanel().setWidgetMinSize(this.getResultsViewLayoutPanel(), Constants.MINIMUM_SPLIT_SIZE);

        this.populateTopActionBar();
    }

    /**
     * The split panel needs to have the center widget added last, which we need to do after optionally added a east or south
     * widget for the rendered view.
     *
     * @param readOnly            true if the view should be read only, and false otherwise
     * @param splitType           How the parent panel should be split
     * @param panel               The rendered view panel itself
     * @param displaySearchResults true if the search results are being displayed, false otherwise
     * @param searchResultsWidth  the width of the search results panel
     * @param renderedPanelSize   the width of the rendered panel
     */
    @Override
    public void initialize(final boolean readOnly, final SplitType splitType, final boolean displaySearchResults, @NotNull final Panel panel, final double searchResultsWidth, final double renderedPanelSize) {

        this.splitType = splitType;
        this.readOnly = readOnly;

        final double fixedRenderedPanelSize = renderedPanelSize < Constants.MINIMUM_SPLIT_SIZE ? Constants.MINIMUM_SPLIT_SIZE : renderedPanelSize;

        super.initialize(displaySearchResults, searchResultsWidth, new DisplaySplitViewCallback() {
            @Override
            public void addToCompassPoints() {
                final SimplePanel renderedPanelParent = new SimplePanel();
                renderedPanelParent.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_LAYOUT_PANEL);
                renderedPanelParent.add(panel);

                if (splitType == SplitType.HORIZONTAL) {
                    getSplitPanel().addSouth(renderedPanelParent, Constants.MINIMUM_SPLIT_SIZE);
                    getSplitPanel().setWidgetMinSize(renderedPanelParent, Constants.MINIMUM_SPLIT_SIZE);
                } else if (splitType == SplitType.VERTICAL) {
                    getSplitPanel().addEast(renderedPanelParent, Constants.MINIMUM_SPLIT_SIZE);
                    getSplitPanel().setWidgetMinSize(renderedPanelParent, Constants.MINIMUM_SPLIT_SIZE);
                }

                /*
                    The size has to be set after setWidgetMinSize is called, otherwise some panels
                    are smaller than expected.
                */
                getSplitPanel().setSplitPosition(renderedPanelParent, fixedRenderedPanelSize, false);
            }
        });

        buildSplitViewButtons(splitType);
    }


    private void addOrRemoveRenderedButton(final SplitType splitType) {
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

    /**
     * Show the rendered split view menu
     */
    public void showSplitViewButtons() {
        getTopActionParentPanel().clear();
        getTopActionParentPanel().add(renderedSplitViewMenu);
    }

    /**
     * This method is called to initialize the buttons that should appear in the top action bar.
     */
    private void populateTopActionBar() {
        this.getTopActionPanel().removeAllRows();

        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getFields());
        addActionButton(this.getExtendedProperties());
        addActionButton(this.getUrls());
        addActionButton(this.getTopicTags());
        addActionButton(this.getBugs());
    }
}
