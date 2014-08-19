/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.base;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.OptGroupElement;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTCSNodeCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTCSNodeCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseAuditedEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.enums.RESTCSNodeTypeV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
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
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.contentspec.RESTContentSpecIDSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.ContentSpecUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLValidator;
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
    protected RESTCSNodeCollectionV1 topicCSNodes = null;

    /**
     * Null until validation has been done, and true if xml validation indicates that the XML is valid. The topic
     * should not be rendered unless this is false.
     */
    protected Boolean hasXMLErrors = null;

    /**
     * The click OK button handler for the message log dialog box depends on whether we are saving changes to the
     * topic or setting the review status. This variable allows us to remove the last assigned click handler in
     * order to swap it out for the new one.
     */
    protected HandlerRegistration messageLogOKHandler;

    /**
     * If this method returns false, the topics can be rendered. If it returns true it means the
     * topics has yet to be validated or has errros.
     * @return true if the xml has errors or if it has not been tested yet, and false otherwise.
     */
    protected boolean hasXMLErrors()
    {
        return hasXMLErrors == null || hasXMLErrors;
    }

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
    public abstract Display getDisplay();

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
    protected void go() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicFilteredResultsAndDetailsPresenter.go()");
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

        /*
            The horizontal panel is used to vertically align the legend items
         */
        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        horizontalPanel.addStyleName(CSSConstants.Legend.LEGEND_PARENT_PANEL);
        getDisplay().getFooterPanelCustomContent().setWidget(horizontalPanel);

        final PushButton hideLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HideLegend());
        hideLegend.addStyleName(CSSConstants.Legend.LEGEND);

        final PushButton showLegend = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ShowLegend());
        showLegend.addStyleName(CSSConstants.Legend.LEGEND);

        /*
            A flow panel is used here to allow the legend items to wrap around.
         */
        final FlowPanel legendPanel = new FlowPanel();
        legendPanel.addStyleName(CSSConstants.Legend.LEGEND_PANEL);

        final Label xmlError = new Label(PressGangCCMSUI.INSTANCE.XMLError());
        xmlError.addStyleName(CSSConstants.Legend.XML_ERROR_LEGEND);
        legendPanel.add(xmlError);

        final Label misspelled = new Label(PressGangCCMSUI.INSTANCE.Misspelled());
        misspelled.addStyleName(CSSConstants.Legend.MISSPELLED_LEGEND);
        legendPanel.add(misspelled);

        final Label badWord = new Label(PressGangCCMSUI.INSTANCE.BadWord());
        badWord.addStyleName(CSSConstants.Legend.BAD_WORD_LEGEND);
        legendPanel.add(badWord);

        final Label badPhrase = new Label(PressGangCCMSUI.INSTANCE.BadPhrase());
        badPhrase.addStyleName(CSSConstants.Legend.BAD_PHRASE_LEGEND);
        legendPanel.add(badPhrase);

        final Label styleGuide = new Label(PressGangCCMSUI.INSTANCE.StyleGuideMatch());
        styleGuide.addStyleName(CSSConstants.Legend.TAG_MATCH_LEGEND);
        legendPanel.add(styleGuide);

        final Label removedText = new Label(PressGangCCMSUI.INSTANCE.DuplicateRedText());
        removedText.addStyleName(CSSConstants.Legend.RENDERED_DIFF_REMOVED_TEXT_LEGEND);
        legendPanel.add(removedText);

        final Label addedText = new Label(PressGangCCMSUI.INSTANCE.DuplicateGreenText());
        addedText.addStyleName(CSSConstants.Legend.RENDERED_DIFF_ADDED_TEXT_LEGEND);
        legendPanel.add(addedText);

        if (Preferences.INSTANCE.getBoolean(Preferences.SHOW_LEGEND, true)) {
            horizontalPanel.add(legendPanel);
            horizontalPanel.add(hideLegend);
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
                horizontalPanel.add(legendPanel);
                horizontalPanel.add(hideLegend);
            }
        });
    }

    /**
     * When a new content spec is selected, the ace editor needs to be updated so the dimming of
     * elements that don't match the condition can be done by the conditional.js worker.
     * @param listBox The ListBox that contains the selected content spec.
     */
    private void notifyAceEditorOfCondition(@NotNull final ListBox listBox) {
        // notify the ace editor that the condition has changed
        if (listBox.getSelectedIndex() != -1) {
            try {
                final String value = listBox.getValue(listBox.getSelectedIndex());
                if (value != null && !value.trim().isEmpty()) {
                    final JSONValue jsonValue = JSONParser.parseStrict(value);

                    if (value != null) {
                        final JSONObject jsonObject = jsonValue.isObject();
                        if (jsonObject != null && jsonObject.containsKey("condition")) {
                            final JSONString condition = jsonObject.get("condition").isString();
                            if (condition != null) {
                                getTopicXMLPresenter().getDisplay().getEditor().setCondition(condition.stringValue());
                                return;
                            }
                        }
                    }
                }
            } catch (@NotNull final Exception ex) {
                // do nothing, fall through to set the condition to null
            }
        }

        // if we didn't return above, set the condition to "default"
        getTopicXMLPresenter().getDisplay().getEditor().setCondition(ServiceConstants.DEFAULT_CONDITION);
    }

    private void bindRenderContentSpecSelection() {
        getTopicRenderedPresenter().getDisplay().getContentSpecs().addChangeHandler(
                getContentSpecListChangeHandler(getTopicRenderedPresenter()));
        getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addChangeHandler(
                getContentSpecListChangeHandler(getTopicSplitPanelRenderedPresenter()));
    }

    private ChangeHandler getContentSpecListChangeHandler(@NotNull final BaseTopicRenderedPresenter renderingPresenter) {
        return new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                Preferences.INSTANCE.deleteSetting(Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId());
                if (renderingPresenter.getDisplay().getContentSpecs().getSelectedIndex() != -1) {
                    final String value = renderingPresenter.getDisplay().getContentSpecs().getValue(
                            renderingPresenter.getDisplay().getContentSpecs().getSelectedIndex());
                    try {
                        final JSONObject jsonValue = (JSONObject) JSONParser.parseStrict(value);
                        final Integer contentSpecId = (int) jsonValue.get("id").isNumber().doubleValue();
                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId(), contentSpecId);
                    } catch (Exception e) {

                    }
                }
                renderingPresenter.getDisplay().clear();
                if (!hasXMLErrors()) {
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(final boolean readOnly) {
                            renderingPresenter.displayTopicRendered(getDisplayedTopic(), readOnly, true);
                        }
                    });
                }

                notifyAceEditorOfCondition(renderingPresenter.getDisplay().getContentSpecs());
            }
        };
    }

    private void bindRemarksSelection() {
        getTopicRenderedPresenter().getDisplay().getRemarks().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> booleanValueChangeEvent) {
                Preferences.INSTANCE.saveSetting(Preferences.REMARKS_ENABLED + getDisplayedTopic().getId(), getTopicRenderedPresenter().getDisplay().getRemarks().getValue());
                if (!hasXMLErrors()) {
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(final boolean readOnly) {
                            getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                        }
                    });
                }
            }
        });

        getTopicSplitPanelRenderedPresenter().getDisplay().getRemarks().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> booleanValueChangeEvent) {
                Preferences.INSTANCE.saveSetting(Preferences.REMARKS_ENABLED + getDisplayedTopic().getId(), getTopicSplitPanelRenderedPresenter().getDisplay().getRemarks().getValue());

                if (!hasXMLErrors()) {
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(final boolean readOnly) {
                            getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                        }
                    });
                }
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

                    if (jsonValue.containsKey("customEntities")) {
                        renderingDisplay.getRenderingInfoDialog().getCustomEntities().setText(jsonValue.get
                                ("customEntities").isString().stringValue());
                    }
                } catch (Exception e) {

                }
                renderingDisplay.getRenderingInfoDialog().center();
            }
        };
    }

    protected void findContentSpecNodes(final RESTCallBack<RESTCSNodeCollectionV1> callBack, final BaseTemplateViewInterface display,
            final boolean useCachedValues) {
        // Don't attempt to find content specs on new topics
        final RESTBaseTopicV1<?, ?, ?> topic = getDisplayedTopic();
        if (getSearchResultPresenter().getProviderData().getSelectedItem() != null) {
            if (!useCachedValues || topicCSNodes == null) {
                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getCSNodesWithContentSpecExpandedFromQuery(
                        Constants.QUERY_PATH_SEGMENT_PREFIX +
                            CommonFilterConstants.CONTENT_SPEC_NODE_ENTITY_ID_FILTER_VAR + "=" + topic.getId() + ";" +
                            CommonFilterConstants.CONTENT_SPEC_NODE_INFO_TOPIC_ID_FILTER_VAR + "=" + topic.getId() + ";" +
                            CommonFilterConstants.LOGIC_FILTER_VAR + "=" + CommonFilterConstants.OR_LOGIC),
                        new RESTCallBack<RESTCSNodeCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTCSNodeCollectionV1 retValue) {
                        topicCSNodes = retValue;
                        callBack.success(retValue);
                    }
                }, display);
            } else {
                callBack.success(topicCSNodes);
            }
        } else {
            callBack.success(null);
        }
    }

    /**
     * Get all the content specs associated with this topic and list them
     */
    private void displayContentSpecs(final RESTCSNodeCollectionV1 retValue) {
        clearRenderContentSpecListBox(getTopicRenderedPresenter().getDisplay().getContentSpecs());
        clearRenderContentSpecListBox(getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs());

        if (retValue != null) {
            checkArgument(retValue.getItems() != null, "The returned node collection should have an expanded collection");

            final Map<String, Map<RESTContentSpecV1, List<RESTCSNodeV1>>> productVersionToContentSpecMap = new TreeMap<String, Map<RESTContentSpecV1, List<RESTCSNodeV1>>>();
            final Map<Integer, String> contentSpecTitles = new HashMap<Integer, String>();

            // Get the csnodes that have conditions or custom entities
            for (final RESTCSNodeCollectionItemV1 node : retValue.getItems()) {
                checkState(node.getItem().getContentSpec() != null,
                        "The content spec node should have an expanded content spec property");

                // Check its a topic node or an info topic node
                if (!(Constants.TOPIC_CS_NODE_TYPES.contains(node.getItem().getNodeType()) || node.getItem().getInfoTopicNode() != null)) {
                    continue;
                }

                final List<RESTCSNodeV1> nodes = new ArrayList<RESTCSNodeV1>();

                // If the node has a condition then add it
                if (!isStringNullOrEmpty(node.getItem().getInheritedCondition())) {
                    nodes.add(node.getItem());
                }

                // Check for entities on the nodes content spec
                final RESTContentSpecV1 contentSpec = node.getItem().getContentSpec();
                String product = null;
                String version = null;
                for (final RESTCSNodeCollectionItemV1 csNode : contentSpec.getChildren_OTM().getItems()) {
                    // Only worry about metadata nodes
                    if (csNode.getItem().getNodeType() == RESTCSNodeTypeV1.META_DATA) {
                        if (csNode.getItem().getTitle().equals(CommonConstants.CS_TITLE_TITLE)) {
                            contentSpecTitles.put(contentSpec.getId(), csNode.getItem().getAdditionalText());
                        } else if (csNode.getItem().getTitle().equals(CommonConstants.CS_ENTITIES_TITLE)) {
                            nodes.add(csNode.getItem());
                        } else if (csNode.getItem().getTitle().equals(CommonConstants.CS_PRODUCT_TITLE)) {
                            product = csNode.getItem().getAdditionalText();
                        } else if (csNode.getItem().getTitle().equals(CommonConstants.CS_VERSION_TITLE)) {
                            version = csNode.getItem().getAdditionalText();
                        }
                    }
                }

                // Add the content spec to the map
                final String productVersion = product + (version == null ? "" : (" " + version));
                if (!productVersionToContentSpecMap.containsKey(productVersion)) {
                    productVersionToContentSpecMap.put(productVersion,
                            new TreeMap<RESTContentSpecV1, List<RESTCSNodeV1>>(new RESTContentSpecIDSort(true)));
                }

                productVersionToContentSpecMap.get(productVersion).put(contentSpec, nodes);
            }

            // Clear the <select> and add the default [NONE] option
            clearRenderContentSpecListBox(getTopicRenderedPresenter().getDisplay().getContentSpecs());
            clearRenderContentSpecListBox(getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs());
            getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");

            final SelectElement s1 = getTopicRenderedPresenter().getDisplay().getContentSpecs().getElement().cast();
            final SelectElement s2 = getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().getElement().cast();

            // Populate the Content Specs ListBox
            for (final Map.Entry<String, Map<RESTContentSpecV1, List<RESTCSNodeV1>>> entry :
                    productVersionToContentSpecMap.entrySet()) {
                final String productVersion = entry.getKey();
                final Map<RESTContentSpecV1, List<RESTCSNodeV1>> contentSpecToNodes = entry.getValue();

                // Create the <optgroup>
                final OptGroupElement groupElement = Document.get().createOptGroupElement();
                groupElement.setLabel(productVersion);

                for (final Map.Entry<RESTContentSpecV1, List<RESTCSNodeV1>> entry2 : contentSpecToNodes.entrySet()) {
                    final RESTContentSpecV1 contentSpec = entry2.getKey();
                    final List<RESTCSNodeV1> contentSpecNodes = entry2.getValue();
                    final String formattedTitle = getFormattedContentSpecTitle(contentSpec, contentSpecTitles);
                    final String entities = ContentSpecUtilities.buildEntities(contentSpec, false);

                    // Generate a JSON object to hold the data for us.
                    final JSONObject o = new JSONObject();
                    o.put("id", new JSONNumber(contentSpec.getId()));

                    if (!isStringNullOrEmpty(entities)) {
                        o.put("entities", new JSONString(entities.trim()));
                    }

                    for (final RESTCSNodeV1 node : contentSpecNodes) {
                        if (!isStringNullOrEmpty(node.getInheritedCondition())) {
                            o.put("condition", new JSONString(node.getInheritedCondition()));
                        } else if (CommonConstants.CS_ENTITIES_TITLE.equalsIgnoreCase(node.getTitle())) {
                            o.put("customEntities", new JSONString(node.getAdditionalText().trim()));
                        }
                    }
                    final String value = o.toString();

                    final OptionElement optElement = Document.get().createOptionElement();
                    optElement.setText(formattedTitle);
                    optElement.setValue(value);
                    groupElement.appendChild(optElement);
                }

                s1.appendChild(groupElement);
                s2.appendChild(groupElement.cloneNode(true));
            }

            // Select the saved value and trigger the rendering
            final String key = Preferences.TOPIC_CONTENT_SPEC + getDisplayedTopic().getId();
            final String savedValue = Preferences.INSTANCE.getString(key, null);
            if (isStringNullOrEmpty(savedValue)) {
                getTopicRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(0);
                getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(0);
                notifyAceEditorOfCondition(getTopicRenderedPresenter().getDisplay().getContentSpecs());

                if (!hasXMLErrors()) {
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(final boolean readOnly) {
                            getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                            getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, false);
                        }
                    });
                }
            } else {
                for (int i = 0, length = getTopicRenderedPresenter().getDisplay().getContentSpecs().getItemCount(); i < length; ++i) {
                    if (getTopicRenderedPresenter().getDisplay().getContentSpecs().getValue(i).contains("\"id\":" + savedValue)) {
                        getTopicRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(i);
                        getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().setSelectedIndex(i);
                        notifyAceEditorOfCondition(getTopicRenderedPresenter().getDisplay().getContentSpecs());

                        if (!hasXMLErrors()) {
                            isReadOnlyMode(new ReadOnlyCallback() {
                            @Override
                            public void readonlyCallback(final boolean readOnly) {
                                    getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                                    getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, false);
                                }
                            });
                        }
                        break;
                    }
                }
            }
        } else {
            getTopicRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
            getTopicSplitPanelRenderedPresenter().getDisplay().getContentSpecs().addItem(PressGangCCMSUI.INSTANCE.NoContentSpec(), "");
            notifyAceEditorOfCondition(getTopicRenderedPresenter().getDisplay().getContentSpecs());

            // Trigger the initial render
            if (!hasXMLErrors()) {
                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(boolean readOnly) {
                        getTopicRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, true);
                        getTopicSplitPanelRenderedPresenter().displayTopicRendered(getDisplayedTopic(), readOnly, false);
                    }
                });
            }
        }
    }

    private void clearRenderContentSpecListBox(final ListBox listBox) {
        listBox.clear();
        final SelectElement s = listBox.getElement().cast();
        s.setInnerHTML("");
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
            } else if (Preferences.TOPIC_RENDERED_VIEW_SPLIT_HOIRZONTAL.equals(savedSplit)) {
                split = SplitType.HORIZONTAL;
            } else {
                split = SplitType.VERTICAL;
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
            getDisplay().initialize(
                    false,
                    split,
                    isDisplayingSearchResults(),
                    getTopicSplitPanelRenderedPresenter().getDisplay().getThirdLevelLayoutPanel(),
                    searchResultsWidth,
                    renderedPanelSize);
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
                                    getTopicSplitPanelRenderedPresenter().getDisplay().getThirdLevelLayoutPanel().getParent());

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

            topicCSNodes = null;

            enableAndDisableActionButtons(lastDisplayedView);

            /* fix the rendered view buttons */
            initializeSplitViewButtons();

            /* Display the property tags that are added to the category */
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem() != null, "There has to be a displayed item");
            checkState(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed item need to reference a valid entity");


            /* Display the list of property tags */
            topicSourceURLsPresenter.redisplayPossibleChildList(getSearchResultPresenter().getProviderData().getDisplayedItem().getItem());

            findContentSpecNodes(new RESTCallBack<RESTCSNodeCollectionV1>() {
                @Override
                public void success(final RESTCSNodeCollectionV1 retValue) {
                    displayContentSpecs(retValue);
                }
            }, null, true);

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

    @Override
    protected void disableTopShortcutButtonsInReadOnlyMode() {
        super.isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                getDisplay().getTopShortcutView().getCreateContentSpec().setEnabled(!readOnly);
                getDisplay().getTopShortcutView().getCreateTopic().setEnabled(!readOnly);
            }
        });
    }

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

            // Make sure the mode is up to date
            if (displayedView == topicXMLPresenter.getDisplay()) {
                final RESTXMLFormat format = getDisplayedTopic().getXmlFormat();
                final AceEditorMode mode;
                if (format == RESTXMLFormat.DOCBOOK_50) {
                    mode = AceEditorMode.DOCBOOK_50;
                } else {
                    mode = AceEditorMode.DOCBOOK_45;
                }

                if (topicXMLPresenter.getDisplay().getEditor().getMode() != mode) {
                    topicXMLPresenter.getDisplay().getEditor().setMode(mode);
                }
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

                    if (viewIsInFilter(filter, topicPropertyTagPresenter.getDisplay())) {
                        topicPropertyTagPresenter.displayDetailedChildrenExtended(topicToDisplay, readOnly);
                    }

                    if (viewIsInFilter(filter, topicSourceURLsPresenter.getDisplay())) {
                        topicSourceURLsPresenter.displayChildrenExtended(topicToDisplay, readOnly);
                    }

                    postInitializeViews(filter);
                }
            });

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



    protected void initAndStartXMLValidation() {
        if (!getXmlValidator().isCheckingXML()) {
            loadAllCustomEntities(new StringLoaded() {
                @Override
                public void stringLoaded(final String entities) {
                    customEntitiesLoaded = true;
                    getXmlValidator().setCustomEntities(entities);
                    getXmlValidator().startCheckingXML();
                }
            });
        }
    }



    protected String getCustomEntities(@NotNull final RESTTopicV1 retValue) {
        checkArgument(retValue.getContentSpecs_OTM() != null, "There should be a Content Spec collection.");

        // Start off with the dummy entities as all content specs have access to them
        final StringBuilder entities = new StringBuilder(Constants.DUMMY_CS_ENTITIES);

        // Loop over the content specs for the topic and add any additional entities
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
     * The presenter used to display the topic's rendered view..
     */
    protected abstract BaseTopicRenderedPresenter getTopicRenderedPresenter();

    protected abstract XMLValidator getXmlValidator();

    protected abstract void loadAllCustomEntities(@NotNull final StringLoaded callback);

    /**
     * The interface that defines the top level topic list and edit view
     */
    public interface Display<
            T extends RESTBaseAuditedEntityV1<T, U, V>, U extends RESTBaseEntityCollectionV1<T, U, V>,
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
