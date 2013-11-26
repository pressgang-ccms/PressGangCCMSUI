package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTCSNodeCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTCSNodeCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.enums.RESTCSNodeTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBIRTBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicSourceURLsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.StringLoaded;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecIDSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class provides the common functionality shared between the topic and
 * translated topic screen.
 * <p/>
 * This class has a number of abstract protected methods that can be used to
 * provide additional functionality specific to the needs of either the topic
 * or translated topic screens.
 */
public abstract class BaseTopicFilteredResultsAndDetailsPresenter<
        T extends RESTBaseTopicV1<T, U, V>, U extends RESTBaseEntityCollectionV1<T, U, V>, V extends RESTBaseEntityCollectionItemV1<T, U,
        V>, Y extends Editor<T>> extends BaseSearchAndEditPresenter<T, U, V, Y> implements BaseTemplatePresenterInterface,
        ReadOnlyPresenter {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTopicFilteredResultsAndDetailsPresenter.class.getName());

    @Inject private FailOverRESTCall failOverRESTCall;

    /**
     * The global event bus.
     */
    @Inject
    private EventBus eventBus;

    /**
     * The query string, which is the full history token minus the HISTORY_TOKEN. Or it is null
     * if we are creating only new topics.
     */
    @Nullable
    private String queryString;

    @Inject
    private TopicXMLPresenter topicXMLPresenter;

    /**
     * The presenter used to display the topic tags.
     */
    @Inject
    private TopicTagsPresenter topicTagsPresenter;
    /**
     * The presenter used to display the topic bugs.
     */
    @Inject
    private TopicBIRTBugsPresenter topicBugsPresenter;
    /**
     * The presenter used to display the topic property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter topicPropertyTagPresenter;
    /**
     * The presenter used to display the topic's source urls.
     */
    @Inject
    private TopicSourceURLsPresenter topicSourceURLsPresenter;
    /**
     * How the rendering panel is displayed
     */
    @NotNull
    private SplitType split = SplitType.NONE;

    private boolean displayingSearchResults = true;

    protected boolean customEntitiesLoaded = false;
    protected String customEntities = "";

    /**
     * The click OK button handler for the message log dialog box depends on whether we are saving changes to the
     * topic or setting the review status. This variable allows us to remove the last assigned click handler in
     * order to swap it out for the new one.
     */
    protected HandlerRegistration messageLogOKHandler;

    public boolean isDisplayingSearchResults() {
        return displayingSearchResults;
    }

    public void setDisplayingSearchResults(final boolean displayingSearchResults) {
        this.displayingSearchResults = displayingSearchResults;
        if (displayingSearchResults) {
            getDisplay().getShowHideSearchResults().getUpFace().setText(PressGangCCMSUI.INSTANCE.HideSearchResults());
        } else {
            getDisplay().getShowHideSearchResults().getUpFace().setText(PressGangCCMSUI.INSTANCE.ShowSearchResults());

        }
    }

    /**
     * @return The view that corresponds to this parent presenter.
     */
    protected abstract Display getDisplay();

    @NotNull
    protected TopicXMLPresenter getTopicXMLPresenter() {
        return topicXMLPresenter;
    }

    @NotNull
    protected abstract BaseFilteredResultsPresenter<V> getSearchResultPresenter();

    @NotNull
    protected TopicTagsPresenter getTopicTagsPresenter() {
        return topicTagsPresenter;
    }

    @NotNull
    protected CommonExtendedPropertiesPresenter getTopicPropertyTagPresenter() {
        return topicPropertyTagPresenter;
    }

    @NotNull
    protected abstract BaseTopicRenderedPresenter getTopicSplitPanelRenderedPresenter();

    /**
     * @return The parsed query string
     */
    @Nullable
    protected String getQueryString() {
        return queryString;
    }

    /**
     * To be set in the overriding classes parseToken() method.
     *
     * @param queryString The parsed query string
     */
    protected void setQueryString(@Nullable final String queryString) {
        this.queryString = queryString;
    }


    @Override
    public void go(@NotNull final HasWidgets container) {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, getDisplay());
            bindSearchAndEditExtended(queryString);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.go()");
        }
    }

    @Override
    public void close() {
        GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.PressGangCCMS());

        /*
            Allow the child components to close.
         */
        getTopicRenderedPresenter().close();
        getTopicSplitPanelRenderedPresenter().close();
        getTopicTagsPresenter().close();
        getTopicPropertyTagPresenter().close();
        getTopicXMLPresenter().close();
        getSearchResultPresenter().close();
        topicSourceURLsPresenter.close();
    }

    @Override
    public void bindSearchAndEditExtended(@Nullable final String queryString) {
        /* Initialize the other presenters we have pulled in */
        getSearchResultPresenter().bindExtendedFilteredResults(queryString);
        topicTagsPresenter.bindExtended();
        topicPropertyTagPresenter.bindDetailedChildrenExtended();
        topicSourceURLsPresenter.bindChildrenExtended();
        topicXMLPresenter.bindExtended();
        /*topicBugsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicRenderedPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);
        topicXMLErrorsPresenter.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId); */

        bindSplitPanelResize();

        addKeyboardShortcutEventHandler();

        postBindSearchAndEditExtended(queryString);

        /*
            Display the split panes. This is done after the call to postBindSearchAndEditExtended to ensure that the
            super.bindSearchAndEdit() method has been called. This means that the loadMainSplitResize() method
            can be called.
        */
        initializeDisplay();

        buildHelpDatabase();

        bindRenderContentSpecSelection();

        bindRemarksSelection();

        bindRenderingInfoSelection();

        buildLegend();
    }

    /**
     * Builds the legend at the bottom of the screen
     */
    private void buildLegend() {
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        getDisplay().getFooterPanelCustomContent().setWidget(horizontalPanel);

        final PushButton hideLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HideLegend());
        hideLegend.addStyleName(CSSConstants.Legend.LEGEND);

        final PushButton showLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ShowLegend());
        showLegend.addStyleName(CSSConstants.Legend.LEGEND);

        final Label xmlError = new Label(PressGangCCMSUI.INSTANCE.XMLError());
        xmlError.addStyleName(CSSConstants.Legend.XML_ERROR_LEGEND);

        final Label misspelled = new Label(PressGangCCMSUI.INSTANCE.Misspelled());
        misspelled.addStyleName(CSSConstants.Legend.MISSPELLED_LEGEND);

        final Label badWord = new Label(PressGangCCMSUI.INSTANCE.BadWord());
        badWord.addStyleName(CSSConstants.Legend.BAD_WORD_LEGEND);

        final Label badPhrase = new Label(PressGangCCMSUI.INSTANCE.BadPhrase());
        badPhrase.addStyleName(CSSConstants.Legend.BAD_PHRASE_LEGEND);

        final Label styleGuide = new Label(PressGangCCMSUI.INSTANCE.StyleGuideMatch());
        styleGuide.addStyleName(CSSConstants.Legend.TAG_MATCH_LEGEND);

        if (Preferences.INSTANCE.getBoolean(Preferences.SHOW_LEGEND, true)) {
            horizontalPanel.add(hideLegend);
            horizontalPanel.add(xmlError);
            horizontalPanel.add(misspelled);
            horizontalPanel.add(badWord);
            horizontalPanel.add(badPhrase);
            horizontalPanel.add(styleGuide);
        } else {
            horizontalPanel.add(showLegend);
        }

        hideLegend.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_LEGEND, false);
                horizontalPanel.clear();
                horizontalPanel.add(showLegend);
            }
        });

        showLegend.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_LEGEND, true);
                horizontalPanel.clear();
                horizontalPanel.add(hideLegend);
                horizontalPanel.add(xmlError);
                horizontalPanel.add(misspelled);
                horizontalPanel.add(badWord);
                horizontalPanel.add(badPhrase);
                horizontalPanel.add(styleGuide);
            }
        });
    }

    private void bindRenderContentSpecSelection() {
        getTopicRenderedPresenter().getDisplay().getContentSpecs().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (getTopicRenderedPresenter().getDisplay().getContentSpecs().getSelectedIndex() != -1) {
                    final String contentSpecId = getTopicRenderedPresenter().getDisplay().getContentSpecs().getValue(
                            getTopicRenderedPresenter().getDisplay().getContentSpecs().getSelectedIndex());
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId(), contentSpecId);
                }
                getTopicRenderedPresenter().getDisplay().clear();
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                    }
                });
            }
        });

        getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().getSelectedIndex() != -1) {
                    final String contentSpecId = getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().getValue(
                            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().getSelectedIndex());
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId(), contentSpecId);
                }
                getTopicSplitPanelRenderedPresenter().getDisplay().clear();
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                    }
                });
            }
        });
    }

    private void bindRemarksSelection() {
        getTopicRenderedPresenter().getDisplay().getRemarks().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> booleanValueChangeEvent) {
                Preferences.INSTANCE.saveSetting(Preferences.REMARKS_ENABLED + getDisplayedTopic().getId(), getTopicRenderedPresenter().getDisplay().getRemarks().getValue());

                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                    }
                });

            }
        });

        getTopicSplitPanelRenderedPresenter().getDisplay().getRemarks().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> booleanValueChangeEvent) {
                Preferences.INSTANCE.saveSetting(Preferences.REMARKS_ENABLED + getDisplayedTopic().getId(), getTopicSplitPanelRenderedPresenter().getDisplay().getRemarks().getValue());

                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(final boolean readOnly) {
                        getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                    }
                });
            }
        });
    }

    private void bindRenderingInfoSelection() {
        getTopicRenderedPresenter().getDisplay().getRenderingInfo().addClickHandler(getRenderingInfoClickHandler
                (getTopicRenderedPresenter().getDisplay()));

        getTopicSplitPanelRenderedPresenter().getDisplay().getRenderingInfo().addClickHandler(getRenderingInfoClickHandler
                (getTopicSplitPanelRenderedPresenter().getDisplay()));
    }

    private ClickHandler getRenderingInfoClickHandler(@NotNull final BaseTopicRenderedPresenter.Display renderingDisplay) {
        return new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                renderingDisplay.getRenderingInfoDialog().reset();
                final ListBox contentSpecs = renderingDisplay.getContentSpecs();
                final String value = contentSpecs.getValue(contentSpecs.getSelectedIndex());
                try {
                    final JSONObject jsonValue = JSONParser.parseStrict(value).isObject();

                    if (jsonValue.containsKey("id")) {
                        renderingDisplay.getRenderingInfoDialog().getId().setValue(
                                (int) jsonValue.get("id").isNumber().doubleValue());
                    }
                    if (jsonValue.containsKey("condition")) {
                        renderingDisplay.getRenderingInfoDialog().getCondition().setText(jsonValue.get
                                ("condition").isString().stringValue());
                    }
                    if (jsonValue.containsKey("entities")) {
                        renderingDisplay.getRenderingInfoDialog().getEntities().setText(jsonValue.get
                                ("entities").isString().stringValue());
                    }
                } catch (Exception e) {

                }
                renderingDisplay.getRenderingInfoDialog().center();
            }
        };
    }

    /**
     * Get all the content specs associated with this topic and list them
     */
    private void findAndDisplayContentSpecs() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.findAndDisplayContentSpecs()");

            getTopicRenderedPresenter().getDisplay().getContentSpecs().clear();
            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().clear();

            /*
                Don't attempt to find content specs on new topics
             */
            if (getSearchResultPresenter().getProviderData().getSelectedItem() != null) {
                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getCSNodesWithContentSpecExpandedFromQuery(
                        ServiceConstants.CS_NODE_TOPIC_TYPES_QUERY + CommonFilterConstants.CONTENT_SPEC_NODE_ENTITY_ID_FILTER_VAR + "=" +
                                getDisplayedTopic().getId() + ";"), new RESTCallBack<RESTCSNodeCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTCSNodeCollectionV1 retValue) {
                        checkArgument(retValue.getItems() != null, "The returned node collection should have an expanded collection");

                        final Set<RESTCSNodeCollectionItemV1> nodes = new HashSet<RESTCSNodeCollectionItemV1>();
                        final Map<Integer, String> contentSpecTitles = new HashMap<Integer, String>();

                        // Get the csnodes that have conditions or custom entities
                        for (final RESTCSNodeCollectionItemV1 node : retValue.getItems()) {
                            checkState(node.getItem().getContentSpec() != null,
                                    "The content spec node should have an expanded content spec property");

                            // If the node has a condition then add it
                            if (!isStringNullOrEmpty(node.getItem().getInheritedCondition())) {
                                nodes.add(node);
                            }

                            // Check for entities on the nodes content spec
                            final RESTContentSpecV1 contentSpec = node.getItem().getContentSpec();
                            for (final RESTCSNodeCollectionItemV1 csNode : contentSpec.getChildren_OTM().getItems()) {
                                // Only worry about metadata nodes
                                if (csNode.getItem().getNodeType() == RESTCSNodeTypeV1.META_DATA) {
                                    if (csNode.getItem().getTitle().equals(CommonConstants.CS_TITLE_TITLE)) {
                                        contentSpecTitles.put(contentSpec.getId(), csNode.getItem().getAdditionalText());
                                    } else if (csNode.getItem().getTitle().equals(CommonConstants.CS_ENTITIES_TITLE)) {
                                        csNode.getItem().setContentSpec(contentSpec);
                                        nodes.add(csNode);
                                    }
                                }
                            }
                        }

                        final List<RESTCSNodeCollectionItemV1> items = retValue.getItems();
                        if (nodes.isEmpty()) {
                            // There is always the option to not use any conditions or entities.
                            getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
                            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(
                                    PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
                        } else {
                            final int numContentSpecs = items.size() - nodes.size();
                            final String key = PressGangCCMSUI.INSTANCE.DefaultContentSpecs().replace("#", numContentSpecs + "");
                            getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(key, "");
                            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(key, "");
                        }

                        // Create the mapping of nodes to content specs
                        final SortedMap<RESTContentSpecV1, List<RESTCSNodeV1>> contentSpecToNodes = new TreeMap
                                <RESTContentSpecV1, List<RESTCSNodeV1>>(new RESTContentSpecIDSort(true));
                        for (final RESTCSNodeCollectionItemV1 item : nodes) {
                            final RESTContentSpecV1 contentSpec = item.getItem().getContentSpec();

                            if (!contentSpecToNodes.containsKey(contentSpec)) {
                                contentSpecToNodes.put(contentSpec, new ArrayList<RESTCSNodeV1>());
                            }

                            contentSpecToNodes.get(contentSpec).add(item.getItem());
                        }

                        // Populate the Content Specs ListBox
                        for (final Map.Entry<RESTContentSpecV1, List<RESTCSNodeV1>> entry : contentSpecToNodes.entrySet()) {
                            final RESTContentSpecV1 contentSpec = entry.getKey();
                            final List<RESTCSNodeV1> contentSpecNodes = entry.getValue();
                            final String formattedTitle = getFormattedContentSpecTitle(contentSpec, contentSpecTitles);

                            // Generate a JSON object to hold the data for us.
                            final JSONObject o = new JSONObject();
                            o.put("id", new JSONNumber(contentSpec.getId()));
                            for (final RESTCSNodeV1 node : contentSpecNodes) {
                                if (!isStringNullOrEmpty(node.getInheritedCondition())) {
                                    o.put("condition", new JSONString(node.getInheritedCondition()));
                                } else if (CommonConstants.CS_ENTITIES_TITLE.equals(node.getTitle())) {
                                    o.put("entities", new JSONString(node.getAdditionalText()));
                                }
                            }
                            final String value = o.toString();

                            getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(formattedTitle, value);
                            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(formattedTitle, value);
                        }

                        // Select the saved value and trigger the rendering
                        final String key = Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId();
                        final String savedValue = Preferences.INSTANCE.getString(key, "");
                        for (int i = 0, length = getTopicRenderedPresenter().getDisplay().getContentSpecs().getItemCount
                                (); i < length; ++i) {
                            if (getTopicRenderedPresenter().getDisplay().getContentSpecs().getValue(i).equals(savedValue)) {
                                getTopicRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(i);
                                getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(i);

                                isReadOnlyMode(new ReadOnlyCallback() {
                                    @Override
                                    public void readonlyCallback(final boolean readOnly) {
                                        getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                                        getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, false);
                                    }
                                });

                                break;
                            }
                        }
                    }
                });
            } else {
                getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
                getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");

                /*
                    Trigger the initial render
                 */
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(boolean readOnly) {
                        getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                        getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, false);
                    }
                });

            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.findAndDisplayContentSpecs()");
        }
    }

    private String getFormattedContentSpecTitle(final RESTContentSpecV1 contentSpec, final Map<Integer, String> contentSpecTitles) {
        final String title = contentSpecTitles.get(contentSpec.getId());
        return title + " (CS" + contentSpec.getId() + ")";
    }

    /**
     * Called by bindSearchAndEditExtended(). Overriding classes should perform any additional initialization in this
     * method.
     *
     * @param queryString The query for this view
     */
    abstract protected void postBindSearchAndEditExtended(@Nullable final String queryString);

    /**
     * @return true if all the data that is required to be loaded before the first
     *         topic is displayed has been loaded, and false otherwise.
     */
    abstract protected boolean isInitialTopicReadyToBeLoaded();

    /**
     * (Re)Initialize the main display with the rendered view split pane (if selected).
     */
    private void initializeDisplay() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeDisplay()");

            final String savedSplit = Preferences.INSTANCE.getString(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE, "");
            if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE.equals(savedSplit)) {
                split = SplitType.NONE;
            } else if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL.equals(savedSplit)) {
                split = SplitType.VERTICAL;
            } else {
                split = SplitType.HORIZONTAL;
            }

            double renderedPanelSize = Constants.SPLIT_PANEL_SIZE;
            if (this.split == SplitType.HORIZONTAL) {
                renderedPanelSize = Preferences.INSTANCE.getDouble(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH,
                        Constants.SPLIT_PANEL_SIZE);
            } else if (this.split == SplitType.VERTICAL) {
                renderedPanelSize = Preferences.INSTANCE.getDouble(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH,
                        Constants.SPLIT_PANEL_SIZE);
            } else {
                LOGGER.log(Level.INFO, "split not set");
            }

            final double searchResultsWidth = Preferences.INSTANCE.getDouble(getMainResizePreferencesKey(), Constants.SPLIT_PANEL_SIZE);

            /* Have to do this after the parseToken method has been called */
            getDisplay().initialize(false, split, isDisplayingSearchResults(),
                    getTopicSplitPanelRenderedPresenter().getDisplay().getPanel(), searchResultsWidth, renderedPanelSize);
            enableAndDisableActionButtons(lastDisplayedView);
            loadMainSplitResize(getMainResizePreferencesKey());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeDisplay()");
        }
    }

    /**
     * @return The key that saves the width of the split between the search results and the topic details
     */
    @NotNull
    protected abstract String getMainResizePreferencesKey();

    /**
     * Display the usual menus. This is called after the split rendering pane menu has been closed.
     */
    private void showRegularMenu() {
        this.getDisplay().displayChildView(this.lastDisplayedView);
    }

    /**
     * Display the split panel menu, which will remove all common and local action buttons
     */
    private void showRenderedSplitPanelMenu() {
        this.getDisplay().getViewActionButtonsParentPanel().clear();
        this.getDisplay().getViewActionButtonsParentPanel().setWidget(0, 0, this.getDisplay().getRenderedSplitViewMenu());
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");

            getDisplay().getSplitPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(@NotNull final ResizeEvent event) {
                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize() ResizeHandler.onResize()");

                        if (topicXMLPresenter.getDisplay().getEditor() != null) {
                            topicXMLPresenter.getDisplay().getEditor().redisplay();
                        }

                        if (getTopicSplitPanelRenderedPresenter().getDisplay().getPanel().isAttached()) {
                            double splitSize = getDisplay().getSplitPanel().getSplitPosition(
                                    getTopicSplitPanelRenderedPresenter().getDisplay().getPanel().getParent());

                            /*
                             * Saves the width of the split screen
                             */
                            if (split == SplitType.HORIZONTAL) {
                                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_HORIZONTAL_SPLIT_WIDTH, splitSize + "");
                            } else if (split == SplitType.VERTICAL) {
                                Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_RENDERED_VERTICAL_SPLIT_WIDTH, splitSize + "");
                            } else {
                                LOGGER.log(Level.INFO, "split not set");
                            }
                        }
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize() ResizeHandler.onResize()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindSplitPanelResize()");
        }
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");

            preLoadAdditionalDisplayedItemData();

            enableAndDisableActionButtons(lastDisplayedView);

            /* fix the rendered view buttons */
            initializeSplitViewButtons();

            /* Display the property tags that are added to the category */
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed item need to reference a valid entity");


            /* Display the list of property tags */
            topicSourceURLsPresenter.redisplayPossibleChildList(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem());

            findAndDisplayContentSpecs();

            /* enable or disable the rendering of remarks */
            final boolean remarksEnabled = Preferences.INSTANCE.getBoolean(
                    Preferences.REMARKS_ENABLED + getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getId(), false);
            getTopicSplitPanelRenderedPresenter().getDisplay().getRemarks().setValue(remarksEnabled);
            getTopicRenderedPresenter().getDisplay().getRemarks().setValue(remarksEnabled);

            postLoadAdditionalDisplayedItemData();

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");
        }

    }

    /**
     * Called before loadAdditionalDisplayedItemData() does any processing.
     */
    protected abstract void preLoadAdditionalDisplayedItemData();

    /**
     * Called after loadAdditionalDisplayedItemData() has finished.
     */
    protected abstract void postLoadAdditionalDisplayedItemData();

    /**
     * @return The topic, or topic revision, that is being displayed.
     */
    protected abstract RESTBaseTopicV1<?, ?, ?> getDisplayedTopic();


    /**
     * This method will replace the top action buttons with their disabled labels based on the
     * currently displayed view.
     *
     * @param displayedView the view to be displayed
     */
    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.enableAndDisableActionButtons()");

            this.getDisplay().replaceTopActionButton(this.getDisplay().getXmlDown(), getDisplay().getXml());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getBugsDown(), getDisplay().getBugs());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getExtendedPropertiesDown(), getDisplay().getExtendedProperties());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getFieldsDown(), getDisplay().getFields());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getRenderedDown(), getDisplay().getRendered());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getTopicTagsDown(), getDisplay().getTopicTags());
            this.getDisplay().replaceTopActionButton(this.getDisplay().getUrlsDown(), getDisplay().getUrls());

            if (displayedView == this.topicXMLPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getXml(), getDisplay().getXmlDown());
            } else if (displayedView == this.topicBugsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getBugs(), getDisplay().getBugsDown());
            } else if (displayedView == this.topicPropertyTagPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getExtendedProperties(), getDisplay().getExtendedPropertiesDown());
            } else if (displayedView == getTopicRenderedPresenter().getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getRendered(), getDisplay().getRenderedDown());
            } else if (displayedView == this.topicTagsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getTopicTags(), getDisplay().getTopicTagsDown());
            } else if (displayedView == this.topicSourceURLsPresenter.getDisplay()) {
                getDisplay().replaceTopActionButton(getDisplay().getUrls(), getDisplay().getUrlsDown());
            }

            postEnableAndDisableActionButtons(displayedView);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.enableAndDisableActionButtons()");
        }
    }

    /**
     * Enable or disable any additional buttons that were added by the extending class.
     */
    protected abstract void postEnableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView);

    /**
     * Update the page name.
     *
     * @param displayedView the currently displayed view.
     */
    private void updatePageTitle(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.updatePageTitle()");

            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed item need to reference a valid entity");

            final StringBuilder title = new StringBuilder(
                    getSearchResultPresenter().getDisplay().getPageName() + " - " + displayedView.getPageName());
            final StringBuilder id = new StringBuilder(
                    getDisplayedTopic().getId() == null ? PressGangCCMSUI.INSTANCE.New() : getDisplayedTopic().getId().toString());

            /*
                Test to see if we are looking at a specific revision. If so, add the revision to the page title.
             */
            if (getDisplayedTopic().getRevision() != null && !getDisplayedTopic().getRevision().equals(
                    getSearchResultPresenter().getProviderData().getDisplayedItem().getItem().getRevision())) {
                id.append("-" + getDisplayedTopic().getRevision());
            }

            String displayTitle = getDisplayedTopic().getTitle() == null ? "" : getDisplayedTopic().getTitle();
            if (displayTitle.length() > Constants.MAX_PAGE_TITLE_LENGTH) {
                displayTitle = displayTitle.substring(0, Constants.MAX_PAGE_TITLE_LENGTH - 3) + "...";
            }

            if (this.getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                title.append(": " + displayTitle + " [" + id.toString() + "]");
            }
            getDisplay().getPageTitle().setText(title.toString());
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.updatePageTitle()");
        }
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.afterSwitchView()");

            enableAndDisableActionButtons(displayedView);

            updatePageTitle(displayedView);

            /* Save any changes to the xml editor */
            if (lastDisplayedView == this.topicXMLPresenter.getDisplay()) {
                this.topicXMLPresenter.getDisplay().getDriver().flush();
            }

            postAfterSwitchView(displayedView);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.afterSwitchView()");
        }
    }

    protected abstract void postAfterSwitchView(@NotNull final BaseTemplateViewInterface displayedView);

    @Override
    protected void bindActionButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons()");

            final ClickHandler topicPropertyTagsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicPropertyTagPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicSourceUrlsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicSourceURLsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicXMLClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicXMLPresenter.getDisplay());

                    }
                }
            };

            final ClickHandler topicRenderedClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(getTopicRenderedPresenter().getDisplay());
                    }
                }
            };

            final ClickHandler topicTagsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicTagsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler topicBugsClickHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null) {
                        switchView(topicBugsPresenter.getDisplay());
                    }
                }
            };

            final ClickHandler splitMenuHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    showRenderedSplitPanelMenu();
                }
            };

            final ClickHandler splitMenuCloseHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    showRegularMenu();
                }
            };

            final ClickHandler splitMenuNoSplitHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_NONE);

                    initializeDisplay();
                    initializeSplitViewButtons();
                }
            };

            final ClickHandler splitMenuVSplitHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_VERTICAL);

                    initializeDisplay();
                    initializeSplitViewButtons();

                    if (lastDisplayedView == getTopicRenderedPresenter().getDisplay()) {
                        switchView(topicXMLPresenter.getDisplay());
                        showRenderedSplitPanelMenu();
                    }
                }
            };

            final ClickHandler splitMenuHSplitHandler = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_RENDERED_VIEW_SPLIT_TYPE,
                            Preferences.TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL);

                    initializeDisplay();
                    initializeSplitViewButtons();

                    if (lastDisplayedView == getTopicRenderedPresenter().getDisplay()) {
                        switchView(topicXMLPresenter.getDisplay());
                        showRenderedSplitPanelMenu();
                    }
                }
            };

            final ClickHandler showHideSearchResults = new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    setSearchResultsVisible(!isDisplayingSearchResults());
                }
            };

            /* Hook up the click listeners */
            getDisplay().getRenderedSplit().addClickHandler(splitMenuHandler);
            getDisplay().getExtendedProperties().addClickHandler(topicPropertyTagsClickHandler);
            getDisplay().getXml().addClickHandler(topicXMLClickHandler);
            getDisplay().getRendered().addClickHandler(topicRenderedClickHandler);
            getDisplay().getTopicTags().addClickHandler(topicTagsClickHandler);
            getDisplay().getBugs().addClickHandler(topicBugsClickHandler);
            getDisplay().getUrls().addClickHandler(topicSourceUrlsClickHandler);

            getDisplay().getRenderedSplitOpen().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedSplitClose().addClickHandler(splitMenuCloseHandler);
            getDisplay().getRenderedNoSplit().addClickHandler(splitMenuNoSplitHandler);
            getDisplay().getRenderedVerticalSplit().addClickHandler(splitMenuVSplitHandler);
            getDisplay().getRenderedHorizontalSplit().addClickHandler(splitMenuHSplitHandler);

            getDisplay().getShowHideSearchResults().addClickHandler(showHideSearchResults);

            /* Hook up the xml editor buttons */
            topicXMLPresenter.getDisplay().getEditorSettings().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (getDisplay().getTopLevelPanel().isAttached() && getDisplay().isViewShown() && !isAnyDialogBoxesOpen(
                            topicXMLPresenter.getDisplay())) {
                        topicXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().center();
                        topicXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().show();
                    }
                }
            });


            postBindActionButtons();
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons()");
        }
    }

    protected void saveTopic(final ClickHandler messageLogDialogOK) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.saveTopic()");

            if (!getDisplay().getMessageLogDialog().getDialogBox().isShowing() && !AlertBox.isDisplayed()) {

                if (hasUnsavedChanges()) {
                    checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null,
                            "There should be a displayed collection item.");
                    checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                            "The displayed collection item to reference a valid entity.");

                    if (messageLogOKHandler != null) {
                        messageLogOKHandler.removeHandler();
                        messageLogOKHandler = null;
                    }

                    messageLogOKHandler = getDisplay().getMessageLogDialog().getOk().addClickHandler(messageLogDialogOK);

                    configureMessageDialog();
                } else {
                    AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.saveTopic()");
        }
    }

    protected void configureMessageDialog() {
        /*
            Default to using the major change for new topics
         */
        if (getSearchResultPresenter().getProviderData().getDisplayedItem() != null && getSearchResultPresenter().getProviderData()
                .getDisplayedItem().returnIsAddItem()) {
            getDisplay().getMessageLogDialog().getMajorChange().setValue(true);
            getDisplay().getMessageLogDialog().getMessage().setValue(PressGangCCMSUI.INSTANCE.InitialTopicCreation());
        }

        getDisplay().getMessageLogDialog().getUsername().setText(Preferences.INSTANCE.getString(Preferences.LOG_MESSAGE_USERNAME, ""));

        getDisplay().getMessageLogDialog().getDialogBox().center();
        getDisplay().getMessageLogDialog().getDialogBox().show();
    }

    protected void setSearchResultsVisible(final boolean visible) {
        if (visible != isDisplayingSearchResults()) {
            setDisplayingSearchResults(visible);
            if (visible) {
                getDisplay().showSearchResults();
            } else {
                getDisplay().hideSearchResults();
            }

            /*
                Elements like the ace editor and mergely diff viewer need to get
                an onResize event so they can be sized appropriately. For reasons that
                I have not yet worked out, this can only be done after control has been
                handed back to the browser loop (which is when scheduleDeferred runs).
             */
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    try {
                        LOGGER.log(Level.INFO,
                                "ENTER BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons() ScheduledCommand.execute()");
                        getDisplay().getSplitPanel().onResize();
                    } finally {
                        LOGGER.log(Level.INFO,
                                "EXIT BaseTopicFilteredResultsAndDetailsPresenter.bindActionButtons() ScheduledCommand.execute()");
                    }
                }
            });
        }
    }

    protected abstract void postBindActionButtons();

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeViews()");

            /**
             * Initialize the views first, as quite often the tables have columns whose
             * values depend on the parent entity set when initializing the views. This is
             * common for "Add" and "Remove" column buttons that need to know if the
             * entity in the row is in the parent in order to choose between the add and
             * remove labels.
             */

            LOGGER.log(Level.INFO, "\tInitializing topic views");

            /*
                Loop over all the standard view i.e. those that will display details from the selected topic
                or topic revision
            */
            final List<BaseCustomViewInterface> displayableViews = new ArrayList<BaseCustomViewInterface>();
            displayableViews.add(topicXMLPresenter.getDisplay());
            displayableViews.add(topicTagsPresenter.getDisplay());
            displayableViews.add(topicBugsPresenter.getDisplay());
            displayableViews.add(topicPropertyTagPresenter.getDisplay());
            displayableViews.add(topicSourceURLsPresenter.getDisplay());

            isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(final boolean readOnly) {
                    final RESTBaseTopicV1<?, ?, ?> topicToDisplay = getDisplayedTopic();
                    for (@NotNull final BaseCustomViewInterface view : displayableViews) {
                        if (viewIsInFilter(filter, view)) {
                            view.display(topicToDisplay, readOnly);
                        }
                    }
                }
            });

            /* We display the rendered view with images */
            /* This is commented out because once the list of conditions is loaded the rendered view will be updated */
            /*if (viewIsInFilter(filter, topicRenderedPresenter.getDisplay())) {
                topicRenderedPresenter.displayTopicRendered(GWTUtilities.removeAllPreamble(topicToDisplay.getXml()), isReadOnlyMode(),
                true);
            }*/

            /* We initially display the split pane rendered view without images */
            /*if (viewIsInFilter(filter, topicSplitPanelRenderedPresenter.getDisplay())) {
                topicSplitPanelRenderedPresenter.displayTopicRendered(
                        addLineNumberAttributesToXML(GWTUtilities.removeAllPreamble(topicToDisplay.getXml())), isReadOnlyMode(), false);
            }*/

            /* Redisplay the editor. topicXMLPresenter.getDisplay().getEditor() will be not null after the display method was called
            above */
            if (viewIsInFilter(filter, topicXMLPresenter.getDisplay())) {
                LOGGER.log(Level.INFO, "\tSetting topic XML edit button state and redisplaying ACE editor");
                topicXMLPresenter.loadEditorSettings();
                topicXMLPresenter.getDisplay().getEditor().redisplay();
            }

            LOGGER.log(Level.INFO, "\tInitializing topic presenters");

            isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(boolean readOnly) {
                    final RESTBaseTopicV1<?, ?, ?> topicToDisplay = getDisplayedTopic();
                    if (viewIsInFilter(filter, topicPropertyTagPresenter.getDisplay())) {
                        topicPropertyTagPresenter.displayDetailedChildrenExtended(topicToDisplay, readOnly);
                    }

                    if (viewIsInFilter(filter, topicSourceURLsPresenter.getDisplay())) {
                        topicSourceURLsPresenter.displayChildrenExtended(topicToDisplay, readOnly);
                    }
                }
            });

            postInitializeViews(filter);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeViews()");
        }

    }

    protected abstract void postInitializeViews(@Nullable final List<BaseTemplateViewInterface> filter);

    private void initializeSplitViewButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.initializeSplitViewButtons()");

            /* fix the rendered view button */
            getDisplay().buildSplitViewButtons(split);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicFilteredResultsAndDetailsPresenter.initializeSplitViewButtons()");
        }
    }

    /**
     * The presenter used to display the topic's rendered view..
     */
    protected abstract BaseTopicRenderedPresenter getTopicRenderedPresenter();

    /**
     * Add listeners for keyboard events
     */
    private void addKeyboardShortcutEventHandler() {
        @NotNull final Event.NativePreviewHandler keyboardShortcutPreviewHandler = new Event.NativePreviewHandler() {
            @Override
            public void onPreviewNativeEvent(@NotNull final Event.NativePreviewEvent event) {
                final NativeEvent ne = event.getNativeEvent();

                if (ne.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    Scheduler.get().scheduleDeferred(new Command() {
                        @Override
                        public void execute() {
                            if (getDisplay().getTopLevelPanel().isAttached() && topicXMLPresenter.getDisplay().isViewShown()) {
                                if (topicXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().isShowing()) {
                                    topicXMLPresenter.getDisplay().getEditorSettingsDialog().getDialogBox().hide();
                                }
                            }
                        }
                    });
                }
            }
        };

        Event.addNativePreviewHandler(keyboardShortcutPreviewHandler);
    }

    protected boolean isAnyDialogBoxesOpen(@NotNull final TopicXMLPresenter.Display topicXMLDisplay) {
        if (topicXMLDisplay.getXmlTagsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getCSPTopicDetailsDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getXmlTemplatesDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getPlainTextXMLDialog().getDialogBox().isShowing()) {
            return true;
        }

        if (topicXMLDisplay.getEditorSettingsDialog().getDialogBox().isShowing()) {
            return true;
        }

        return false;
    }

    protected abstract void loadAllCustomEntities(@NotNull final StringLoaded callback);

    protected String getCustomEntities(@NotNull final RESTTopicV1 retValue) {
        checkArgument(retValue.getContentSpecs_OTM() != null, "There should be a Content Spec collection.");

        final StringBuilder entities = new StringBuilder();
        for (final RESTContentSpecV1 contentSpec : retValue.getContentSpecs_OTM().returnItems()) {
            checkArgument(contentSpec.getChildren_OTM() != null, "The Content Spec should have a children collection.");

            for (final RESTCSNodeV1 csNode : contentSpec.getChildren_OTM().returnItems()) {
                if (CommonConstants.CS_ENTITIES_TITLE.equals(csNode.getTitle())) {
                    entities.append(csNode.getAdditionalText()).append("\n");
                }
            }
        }

        return entities.toString();
    }

    /**
     * The interface that defines the top level topic list and edit view
     */
    public interface Display<
            T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseEntityCollectionV1<T, U, V>,
            V extends RESTBaseEntityCollectionItemV1<T, U, V>> extends BaseSearchAndEditViewInterface<T, U, V> {


        FlexTable getRenderedSplitViewMenu();

        PushButton getRenderedSplitOpen();

        PushButton getRenderedHorizontalSplit();

        PushButton getRenderedSplitClose();

        PushButton getRenderedVerticalSplit();

        PushButton getRenderedNoSplit();

        PushButton getRenderedSplit();

        PushButton getUrls();

        Label getUrlsDown();

        /**
         * @return The button that is used to switch to the rendered view
         */
        PushButton getRendered();

        /**
         * @return The button that is used to switch to the rendered view
         */
        Label getRenderedDown();

        /**
         * @return The button that is used to switch to the XML view
         */
        PushButton getXml();

        /**
         * @return The label that is used to indicate that the XML view is selected
         */
        Label getXmlDown();

        /**
         * @return The button that is used to switch to the topic fields view
         */
        PushButton getFields();

        /**
         * @return The button that is used to switch to the topic fields view
         */
        Label getFieldsDown();

        /**
         * @return The button that is used to switch to the topic property tags view
         */
        PushButton getExtendedProperties();

        /**
         * @return The button that is used to switch to the topic property tags view
         */
        Label getExtendedPropertiesDown();

        /**
         * @return The button that is used to switch to the tags view
         */
        PushButton getTopicTags();

        /**
         * @return The button that is used to switch to the tags view
         */
        Label getTopicTagsDown();

        /**
         * @return The button that is used to switch to the bugs view
         */
        PushButton getBugs();

        /**
         * @return The button that is used to switch to the bugs view
         */
        Label getBugsDown();

        /**
         * Show the rendered split view menu
         */
        void showSplitViewButtons();

        /**
         * Rebuild the split view buttons
         *
         * @param splitType The screen split
         */
        void buildSplitViewButtons(final SplitType splitType);

        LogMessageInterface getMessageLogDialog();

        SplitType getSplitType();

        void initialize(final boolean readOnly, final SplitType splitType, final boolean dislaySearchResults, final Panel panel,
                final double searchResultsWidth, final double renderedPanelSize);

        /**
         * @return The button used to show or hide the search results panel
         */
        @NotNull
        PushButton getShowHideSearchResults();
    }

    /**
     * Assign help info to the UI elements exposed by this presenter.
     */
    private void buildHelpDatabase() {

        setDataAttribute(getDisplay().getShowHideSearchResults(), ServiceConstants.HELP_TOPICS.SHOW_HIDE_SEARCH_RESULTS_TOPIC.getId());

        /*
            Content UI elements
         */
        setDataAttribute(getSearchResultPresenter().getDisplay().getSearchResultsPanel(),
                ServiceConstants.HELP_TOPICS.TOPIC_SEARCH_RESULTS.getId());
        setDataAttribute(getTopicXMLPresenter().getDisplay().getEditorParent(), ServiceConstants.HELP_TOPICS.XML_EDITOR.getId());
        setDataAttribute(getTopicXMLPresenter().getDisplay().getXmlErrors(), ServiceConstants.HELP_TOPICS.TOPIC_XML_VALIDATION.getId());

        /*
            Common action buttons
         */
        setDataAttribute(getDisplay().getFields(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTIES.getId());
        setDataAttribute(getDisplay().getFieldsDown(), ServiceConstants.HELP_TOPICS.TOPIC_PROPERTIES.getId());
        setDataAttribute(getDisplay().getExtendedProperties(), ServiceConstants.HELP_TOPICS.TOPIC_EXTENDED_PROPERTIES.getId());
        setDataAttribute(getDisplay().getExtendedPropertiesDown(), ServiceConstants.HELP_TOPICS.TOPIC_EXTENDED_PROPERTIES.getId());
        setDataAttribute(getDisplay().getUrls(), ServiceConstants.HELP_TOPICS.TOPIC_SOURCE_URLS.getId());
        setDataAttribute(getDisplay().getUrlsDown(), ServiceConstants.HELP_TOPICS.TOPIC_SOURCE_URLS.getId());
        setDataAttribute(getDisplay().getTopicTags(), ServiceConstants.HELP_TOPICS.TOPIC_TAGS.getId());
        setDataAttribute(getDisplay().getTopicTagsDown(), ServiceConstants.HELP_TOPICS.TOPIC_TAGS.getId());
        setDataAttribute(getDisplay().getBugs(), ServiceConstants.HELP_TOPICS.TOPIC_BUGS.getId());
        setDataAttribute(getDisplay().getBugsDown(), ServiceConstants.HELP_TOPICS.TOPIC_BUGS.getId());
        setDataAttribute(getDisplay().getXml(), ServiceConstants.HELP_TOPICS.TOPIC_XML_EDITOR.getId());
        setDataAttribute(getDisplay().getXmlDown(), ServiceConstants.HELP_TOPICS.TOPIC_XML_EDITOR.getId());
//        setDataAttribute(getTopicXMLPresenter().getDisplay().getLineWrap(), ServiceConstants.HELP_TOPICS.TOPIC_LINE_WRAP.getId());
//        setDataAttribute(getTopicXMLPresenter().getDisplay().getShowInvisibles(),
//                ServiceConstants.HELP_TOPICS.TOPIC_HIDDEN_CHARACTERS.getId());
        setDataAttribute(getDisplay().getRenderedSplit(), ServiceConstants.HELP_TOPICS.TOPIC_RENDERED_PANE.getId());

        setDataAttribute(topicSourceURLsPresenter.getDisplay().getPossibleChildrenResultsPanel(),
                ServiceConstants.HELP_TOPICS.TOPIC_SOURCE_URLS_LIST.getId());
        setDataAttribute(topicSourceURLsPresenter.getDisplay().getAdd(), ServiceConstants.HELP_TOPICS.TOPIC_ADD_SOURCE_URL.getId());

        setDataAttribute(topicTagsPresenter.getDisplay().getAdd(), ServiceConstants.HELP_TOPICS.TOPIC_TAG_ADD.getId());
        setDataAttribute(topicTagsPresenter.getDisplay().getProjectsList(), ServiceConstants.HELP_TOPICS.TOPIC_TAG_PROJECTS_LIST.getId());
        setDataAttribute(topicTagsPresenter.getDisplay().getCategoriesList(),
                ServiceConstants.HELP_TOPICS.TOPIC_TAG_CATEGORIES_LIST.getId());
        setDataAttribute(topicTagsPresenter.getDisplay().getMyTags(), ServiceConstants.HELP_TOPICS.TOPIC_TAG_TAGS_LIST.getId());
        setDataAttribute(topicTagsPresenter.getDisplay().getEditor(), ServiceConstants.HELP_TOPICS.TOPIC_TAG_EXISTING.getId());

        setDataAttribute(getTopicSplitPanelRenderedPresenter().getDisplay().getLayoutPanel(),
                ServiceConstants.HELP_TOPICS.RENDERED_PREVIEW.getId());

    }
}
