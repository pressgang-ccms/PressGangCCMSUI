package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren.BaseDetailedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1.REMOVE_STATE;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to populate the tables of possible and existing topic property tags.
 */
@Dependent
public class TopicPropertyTagsPresenter extends BaseDetailedChildrenPresenter<
        RESTBaseTopicV1<?, ?, ?>,
        RESTBaseTopicV1<?, ?, ?>,
        RESTPropertyTagCollectionItemV1,
        RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1> {

    public interface Display extends BaseExtendedChildrenViewInterface<
            RESTBaseTopicV1<?, ?, ?>,
            RESTTopicV1,
            RESTPropertyTagCollectionItemV1,
            RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1>,
            BaseTemplateViewInterface {
        Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagRemoveColumn();

        Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagValueColumn();
    }

    public static final String HISTORY_TOKEN = "TopicPropertyTagsView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TopicPropertyTagsPresenter.class.getName());
    private Integer topicId;
    @Inject
    private Display display;

    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (final NumberFormatException ex) {
            topicId = null;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindDetailedChildrenExtended(ServiceConstants.TOPIC_EXTENDED_PROPERTIES_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void bindDetailedChildrenExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bindDetailedChildren(helpTopicId, pageId, Preferences.TOPIC_PROPERTYTAG_VIEW_MAIN_SPLIT_WIDTH, display);
    }

    @Override
    public void displayDetailedChildrenExtended(final RESTBaseTopicV1<?, ?, ?> parent, final boolean readOnly) {
        super.displayDetailedChildren(parent, readOnly);
        bindPropertyTagButtons(parent);
    }

    /**
     * Add behaviour to the property tag add and remove buttons, and the value text edit field.
     */
    private void bindPropertyTagButtons(@NotNull final RESTBaseTopicV1<?, ?, ?> parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicFilteredResultsAndDetailsPresenter.bindPropertyTagButtons()");

            display.getPossibleChildrenButtonColumn().setFieldUpdater(
                    new FieldUpdater<RESTPropertyTagCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, final RESTPropertyTagCollectionItemV1 object, final String value) {

                            /* Create a new property tag child */
                            final RESTAssignedPropertyTagV1 restAssignedPropertyTagV1 = new RESTAssignedPropertyTagV1();
                            restAssignedPropertyTagV1.setId(object.getItem().getId());
                            restAssignedPropertyTagV1.setName(object.getItem().getName());
                            restAssignedPropertyTagV1.setDescription(object.getItem().getDescription());

                            parent.getProperties().addNewItem(restAssignedPropertyTagV1);

                            /* Update the list of existing children */
                            Collections.sort(parent.getProperties().getItems(), new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                            refreshExistingChildList(parent);
                        }
                    }
            );

            display.getPropertyTagRemoveColumn().setFieldUpdater(
                    new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

                            /*
                                Note that the relationship between topic and property tag is many to many.
                             */

                            if (object.returnIsAddItem()) {
                                /* Previously added items are just removed from the collection */
                                parent.getProperties().getItems().remove(index);
                            } else {
                                /* Existing children are marked for removal */
                                object.setState(REMOVE_STATE);
                            }

                            /* Update the list of existing children */
                            refreshExistingChildList(parent);
                        }
                    }
            );

            display.getPropertyTagValueColumn().setFieldUpdater(new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
                @Override
                public void update(final int index, final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

                    /*
                        Updating just the value (and no other topic fields or children) will not create a new Envers revision
                        for the topic. This makes it incredibly difficult to get the state of the topic, including the state
                        or the property tags, as the topic existed at a particular point in time because the state of the
                        assigned property tags may have been changed.

                        To force a new topic revision to be created, any time a value is updated the existing mapping is removed
                         and a new one created. This has the effect of creating a new topic revision to match the fact that
                         the value of a property tag has been changed.
                     */

                    if (object.returnIsAddItem()) {
                        object.getItem().setValue(value);
                    } else {
                        object.setState(REMOVE_STATE);

                        /* Create a new property tag child */
                        final RESTAssignedPropertyTagV1 restAssignedPropertyTagV1 = new RESTAssignedPropertyTagV1();
                        restAssignedPropertyTagV1.setId(object.getItem().getId());
                        restAssignedPropertyTagV1.setName(object.getItem().getName());
                        restAssignedPropertyTagV1.setDescription(object.getItem().getDescription());
                        restAssignedPropertyTagV1.setValue(value);

                        parent.getProperties().addNewItem(restAssignedPropertyTagV1);

                        /* Update the list of existing children */
                        Collections.sort(parent.getProperties().getItems(), new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                        refreshExistingChildList(parent);
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicFilteredResultsAndDetailsPresenter.bindPropertyTagButtons()");
        }
    }

    @Override
    public EnhancedAsyncDataProvider<RESTAssignedPropertyTagCollectionItemV1> generateExistingProvider(final RESTBaseTopicV1<?, ?, ?> entity) {
        return new EnhancedAsyncDataProvider<RESTAssignedPropertyTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTAssignedPropertyTagCollectionItemV1> display) {
                try {
                    LOGGER.log(Level.INFO, "ENTER TopicPropertyTagsPresenter.generateExistingProvider() EnhancedAsyncDataProvider.onRangeChanged()");

                    getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                    getExistingProviderData().setItems(new ArrayList<RESTAssignedPropertyTagCollectionItemV1>());

                    /* Zero results can be a null list. Also selecting a new tag will reset getProviderData(). */
                    if (entity != null && entity.getProperties() != null) {
                        LOGGER.log(Level.INFO, "Found " + entity.getProperties().getItems().size() + " Property Tags.");
                        /* Don't display removed tags */
                        for (final RESTAssignedPropertyTagCollectionItemV1 propertyTagInTopic : entity.getProperties().returnExistingAddedAndUpdatedCollectionItems()) {
                            getExistingProviderData().getItems().add(propertyTagInTopic);
                        }
                    } else {
                        LOGGER.log(Level.WARNING, "entity == null: " + (entity == null) + " entity.getProperties() == null: " + (entity.getProperties() == null));
                    }

                    //Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                    displayNewFixedList(getExistingProviderData().getItems());
                } finally {
                    LOGGER.log(Level.INFO, "EXIT TopicPropertyTagsPresenter.generateExistingProvider() EnhancedAsyncDataProvider.onRangeChanged()");
                }
            }
        };
    }

    @Override
    public EnhancedAsyncDataProvider<RESTPropertyTagCollectionItemV1> generatePossibleChildrenProvider(final RESTBaseTopicV1<?, ?, ?> parent) {
        return new EnhancedAsyncDataProvider<RESTPropertyTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTPropertyTagCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
                    displayNewFixedList(getPossibleChildrenProviderData().getItems());
                } else {
                    resetProvider();
                }

            }
        };
    }

    @Override
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTBaseTopicV1<?, ?, ?> parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicPropertyTagsPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");

            final RESTCalls.RESTCallback<RESTPropertyTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTPropertyTagCollectionV1>() {
                @Override
                public void begin() {
                    getDisplay().addWaitOperation();
                }

                @Override
                public void generalException(final Exception ex) {
                    LOGGER.log(Level.SEVERE, "RESTCallback.generalException()\n\tException: " + ex.toString());
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    getDisplay().removeWaitOperation();
                }

                @Override
                public void success(final RESTPropertyTagCollectionV1 retValue) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicPropertyTagsPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() callback.success()");
                        LOGGER.log(Level.INFO, "RESTCallback.success(). retValue.getSize(): " + retValue.getSize() + " retValue.getItems().size(): " + retValue.getItems().size());
                        /* Zero results can be a null list */
                        getPossibleChildrenProviderData().setStartRow(0);
                        getPossibleChildrenProviderData().setItems(retValue.getItems());
                        getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                        /* Refresh the list */
                        getDisplay().getPossibleChildrenProvider().displayNewFixedList(getPossibleChildrenProviderData().getItems());

                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicPropertyTagsPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() callback.success()");
                        getDisplay().removeWaitOperation();
                    }
                }

                @Override
                public void failed(final Message message, final Throwable throwable) {
                    getDisplay().removeWaitOperation();
                    LOGGER.log(Level.SEVERE, "RESTCallback.failed()\n\tMessage: " + message.toString() + "\n\t Throwable: " + throwable.toString());
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                }
            };

            /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
            getPossibleChildrenProviderData().reset();
            RESTCalls.getPropertyTags(callback);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicPropertyTagsPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");
        }
    }
}
