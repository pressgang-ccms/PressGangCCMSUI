package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityWithPropertiesV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren.BaseDetailedChildrenPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1.REMOVE_STATE;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

/**
 * The presenter used to populate the tables of possible and existing property tags.
 */
public class CommonExtendedPropertiesPresenter
        extends BaseDetailedChildrenPresenter<
        RESTBaseEntityWithPropertiesV1<?, ?, ?>,
        RESTBaseEntityWithPropertiesV1<?, ?, ?>,
        RESTPropertyTagCollectionItemV1,
        RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1> {

    public interface Display extends BaseExtendedChildrenViewInterface<
            RESTBaseEntityWithPropertiesV1<?, ?, ?>,
            RESTPropertyTagCollectionItemV1,
            RESTAssignedPropertyTagV1, RESTAssignedPropertyTagCollectionV1, RESTAssignedPropertyTagCollectionItemV1>,
            BaseTemplateViewInterface {
        Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagRemoveColumn();

        Column<RESTAssignedPropertyTagCollectionItemV1, String> getPropertyTagValueColumn();
    }

    public static final String HISTORY_TOKEN = "CommonPropertyTagsView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonExtendedPropertiesPresenter.class.getName());
    @Nullable
    private Integer topicId;
    @Inject
    private Display display;

    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            topicId = null;
        }
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindDetailedChildrenExtended(ServiceConstants.TOPIC_EXTENDED_PROPERTIES_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    @Override
    public void bindDetailedChildrenExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bindDetailedChildren(helpTopicId, pageId, Preferences.TOPIC_PROPERTYTAG_VIEW_MAIN_SPLIT_WIDTH, display);
    }

    @Override
    public void displayDetailedChildrenExtended(@NotNull final RESTBaseEntityWithPropertiesV1<?, ?, ?> parent, final boolean readOnly) {
        super.displayDetailedChildren(parent, readOnly);
        bindPropertyTagButtons(parent);
    }

    /**
     * Add behaviour to the property tag add and remove buttons, and the value text edit field.
     */
    private void bindPropertyTagButtons(@NotNull final RESTBaseEntityWithPropertiesV1<?, ?, ?> parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER CommonExtendedPropertiesPresenter.bindPropertyTagButtons()");

            display.getPossibleChildrenButtonColumn().setFieldUpdater(
                    new FieldUpdater<RESTPropertyTagCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTPropertyTagCollectionItemV1 object, final String value) {
                            try {
                                LOGGER.log(Level.INFO, "ENTER CommonExtendedPropertiesPresenter.bindPropertyTagButtons() FieldUpdater.update()");

                                checkState(object.getItem() != null, "The object collection item being clicked on need to reference a valid entity");

                                /* Create a new property tag child */
                                final RESTAssignedPropertyTagV1 restAssignedPropertyTagV1 = new RESTAssignedPropertyTagV1();
                                restAssignedPropertyTagV1.setId(object.getItem().getId());
                                restAssignedPropertyTagV1.setName(object.getItem().getName());
                                restAssignedPropertyTagV1.setDescription(object.getItem().getDescription());

                                parent.getProperties().addNewItem(restAssignedPropertyTagV1);

                                /* Update the list of existing children */
                                Collections.sort(parent.getProperties().getItems(), new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
                                refreshExistingChildList(parent);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT CommonExtendedPropertiesPresenter.bindPropertyTagButtons() FieldUpdater.update()");
                            }
                        }
                    }
            );

            display.getPropertyTagRemoveColumn().setFieldUpdater(
                    new FieldUpdater<RESTAssignedPropertyTagCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

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
                public void update(final int index, @NotNull final RESTAssignedPropertyTagCollectionItemV1 object, final String value) {

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
            LOGGER.log(Level.INFO, "EXIT CommonExtendedPropertiesPresenter.bindPropertyTagButtons()");
        }
    }

    @NotNull
    @Override
    public EnhancedAsyncDataProvider<RESTAssignedPropertyTagCollectionItemV1> generateExistingProvider(@NotNull final RESTBaseEntityWithPropertiesV1<?, ?, ?> entity) {
        return new EnhancedAsyncDataProvider<RESTAssignedPropertyTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTAssignedPropertyTagCollectionItemV1> display) {
                try {
                    LOGGER.log(Level.INFO, "ENTER CommonExtendedPropertiesPresenter.generateExistingProvider() EnhancedAsyncDataProvider.onRangeChanged()");

                    if (entity.getProperties() != null) {
                        getExistingProviderData().setStartRow(display.getVisibleRange().getStart());
                        getExistingProviderData().setItems(new ArrayList<RESTAssignedPropertyTagCollectionItemV1>());

                        /* Don't display removed tags */
                        for (final RESTAssignedPropertyTagCollectionItemV1 propertyTagInTopic : entity.getProperties().returnExistingAddedAndUpdatedCollectionItems()) {
                            getExistingProviderData().getItems().add(propertyTagInTopic);
                        }

                        //Collections.sort(getExistingProviderData().getItems(), new RESTTagCategoryCollectionItemV1SortComparator());

                        displayNewFixedList(getExistingProviderData().getItems());
                    } else {
                        this.resetProvider();
                    }
                } finally {
                    LOGGER.log(Level.INFO, "EXIT CommonExtendedPropertiesPresenter.generateExistingProvider() EnhancedAsyncDataProvider.onRangeChanged()");
                }
            }
        };
    }

    @NotNull
    @Override
    public EnhancedAsyncDataProvider<RESTPropertyTagCollectionItemV1> generatePossibleChildrenProvider(@NotNull final RESTBaseEntityWithPropertiesV1<?, ?, ?> parent) {
        return new EnhancedAsyncDataProvider<RESTPropertyTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTPropertyTagCollectionItemV1> data) {

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
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final RESTBaseEntityWithPropertiesV1<?, ?, ?> parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER CommonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");

            checkState(getDisplay().getPossibleChildrenProvider() != null, "There should be a valid possible children provider");

            final RESTCallBack<RESTPropertyTagCollectionV1> callback = new RESTCallBack<RESTPropertyTagCollectionV1>() {
                @Override
                public void success(@NotNull final RESTPropertyTagCollectionV1 retValue) {
                    LOGGER.log(Level.INFO, "ENTER CommonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList() callback.success()");
                    LOGGER.log(Level.INFO, "RESTCallback.success(). retValue.getSize(): " + retValue.getSize() + " retValue.getItems().size(): " + retValue.getItems().size());
                            /* Zero results can be a null list */
                    getPossibleChildrenProviderData().setStartRow(0);
                    getPossibleChildrenProviderData().setItems(retValue.getItems());
                    getPossibleChildrenProviderData().setSize(retValue.getItems().size());

                            /* Refresh the list */
                    getDisplay().getPossibleChildrenProvider().displayNewFixedList(getPossibleChildrenProviderData().getItems());
                }
            };
            getDisplay().getPossibleChildrenProvider().resetProvider();
            FailOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getPropertyTags(), callback, display);
        } finally {
            LOGGER.log(Level.INFO, "EXIT CommonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList()");
        }
    }
}
