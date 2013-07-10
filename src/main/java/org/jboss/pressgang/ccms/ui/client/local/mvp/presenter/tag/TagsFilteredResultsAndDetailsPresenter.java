package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentRESTBaseEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.SetNewChildSortCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.common.CommonExtendedPropertiesPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TagsFilteredResultsAndDetailsPresenter
        extends
        BaseSearchAndEditPresenter<
                RESTTagV1,
                RESTTagCollectionV1,
                RESTTagCollectionItemV1,
                RESTTagV1BasicDetailsEditor>
        implements BaseTemplatePresenterInterface {


    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the four views
     * TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and TagCategoriesView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {

        PushButton getTagProjects();

        PushButton getTagDetails();

        PushButton getExtendedProperties();

        PushButton getSave();

        PushButton getTagCategories();

        Label getTagCategoriesDown();

        Label getTagProjectsDown();

        Label getTagDetailsDown();

        Label getExtendedPropertiesDown();
    }

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "TagsFilteredResultsAndTagView";

    @Inject private FailOverRESTCall failOverRESTCall;

    @Inject
    private HandlerManager eventBus;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TagsFilteredResultsAndDetailsPresenter.class.getName());

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    @Inject
    private TagFilteredResultsPresenter filteredResultsComponent;


    @Inject
    private TagPresenter resultComponent;


    @Inject
    private TagProjectsPresenter projectsComponent;

    @Inject
    private TagCategoriesPresenter categoriesComponent;

    /**
     * The presenter used to display the property tags.
     */
    @Inject
    private CommonExtendedPropertiesPresenter commonExtendedPropertiesPresenter;

    /**
     * The tag query string extracted from the history token
     */
    private String queryString;

    /**
     * Used when moving children up and down
     */
    private final SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> sortCallback = new SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>() {

        @Override
        public boolean setSort(@NotNull final RESTTagInCategoryCollectionItemV1 child, final int index) {

            checkArgument(child.getItem() != null, "The collection item should reference a valid entity.");

            if (child.getItem().getRelationshipSort() == null || child.getItem().getRelationshipSort() != index) {
                child.getItem().explicitSetRelationshipSort(index);
                /* Set any unchanged items to updated */
                if (RESTBaseCollectionItemV1.UNCHANGED_STATE.equals(child.getState())) {
                    child.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                }

                return true;
            }

            return false;
        }
    };

    /**
     * A click handler used to display the tag fields view
     */
    private final ClickHandler tagDetailsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            switchView(resultComponent.getDisplay());
        }

    };

    /**
     * A click handler used to display the tag fields view
     */
    private final ClickHandler tagExtendedPropertiesClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            switchView(commonExtendedPropertiesPresenter.getDisplay());
        }

    };

    /**
     * A click handler used to display the tag projects view
     */
    private final ClickHandler tagProjectsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            switchView(projectsComponent.getDisplay());
        }
    };

    /**
     * A click handler used to display the tag categories view
     */
    private final ClickHandler tagCategoriesClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            switchView(categoriesComponent.getDisplay());
        }
    };

    /**
     * A click handler used to save any changes to the tag
     */
    @NotNull
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(@NotNull final ClickEvent event) {
            try {
                LOGGER.log(Level.INFO, "ENTER saveClickHandler.onClick()");

                if (hasUnsavedChanges()) {

                    final boolean unsavedTagChanges = unsavedTagChanged();
                    final boolean unsavedCategoryChanges = categoriesComponent.hasUnsavedChanges();

                    LOGGER.log(Level.INFO, "unsavedTagChanges: " + unsavedTagChanges);
                    LOGGER.log(Level.INFO, "unsavedCategoryChanges: " + unsavedCategoryChanges);

                    /* Create the tag first */
                    saveTagChanges(unsavedTagChanges, unsavedCategoryChanges);
                } else {
                    Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                }
            } finally {
                LOGGER.log(Level.INFO, "EXIT saveClickHandler.onClick()");
            }

        }

        private void saveTagChanges(final boolean unsavedTagChanges, final boolean unsavedCategoryChanges) {

            try {
                LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndDetailsPresenter.saveTagChanges()");

                checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                checkState(filteredResultsComponent.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
                checkState(filteredResultsComponent.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");
                checkState(categoriesComponent.getPossibleChildrenProviderData().getItems() == null || categoriesComponent.getPossibleChildrenProviderData().isValid(), "The categories should not have been loaded, or they should have a valid data provider.");
                checkState(projectsComponent.getPossibleChildrenProviderData().getItems() == null || projectsComponent.getPossibleChildrenProviderData().isValid(), "The projects should not have been loaded, or they should have a valid data provider.");


                /* Was the tag we just saved a new tag? */
                final boolean wasNewTag = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                final RESTCallBack<RESTTagV1> callback = new RESTCallBack<RESTTagV1>() {
                    public void success(@NotNull final RESTTagV1 retValue) {
                        /* we are now viewing the object returned by the save */
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                        filteredResultsComponent.getProviderData().getDisplayedItem().setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        /* Update the list of tags with any saved changes */
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);

                        /* refresh the projects list */
                        projectsComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                        if (unsavedCategoryChanges) {
                            saveCategoryChanges(wasNewTag, filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                        } else {
                            updateDisplayWithNewEntityData(wasNewTag);
                            Window.alert(PressGangCCMSUI.INSTANCE.TagSaveSuccess() + " " + retValue.getId());
                        }
                    }
                };



                /* Sync changes from the tag view */
                final RESTTagV1 sourceTag = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();
                final RESTTagV1 updateTag = new RESTTagV1();
                updateTag.setId(sourceTag.getId());
                updateTag.explicitSetDescription(sourceTag.getDescription());
                updateTag.explicitSetName(sourceTag.getName());

                updateTag.explicitSetProperties(new RESTAssignedPropertyTagCollectionV1());

                /* Update the extended properties */
                if (sourceTag.getProperties() != null) {
                    updateTag.getProperties().setItems(sourceTag.getProperties().returnDeletedAddedAndUpdatedCollectionItems());
                }

                /*
                 * Sync changes from the projects. categoriesComponent.getProviderData().getItems() contains a collection of all the
                 * projects, and their tags collections contain any added or removed tag relationships. Here we copy those modified
                 * relationships into the updateTag, so the changes are all done in one transaction.
                 */
                if (categoriesComponent.getPossibleChildrenProviderData().getItems() != null) {

                    updateTag.explicitSetCategories(new RESTCategoryInTagCollectionV1());

                    for (@NotNull final RESTCategoryCollectionItemV1 category : categoriesComponent.getPossibleChildrenProviderData().getItems()) {

                        checkState(category.getItem() != null, "The category collection item should reference a valid entity.");
                        checkState(category.getItem().getTags() != null, "The category collection item should reference a valid entity and have a valid tags collection.");

                        for (@NotNull final RESTTagInCategoryCollectionItemV1 tag : category.getItem().getTags().returnDeletedAndAddedCollectionItems()) {
                            /*
                             * It should only be possible to add the currently displayed tag to the categories
                             */
                            if (tag.getItem().getId().equals(updateTag.getId())) {

                                final RESTCategoryInTagV1 addedCategory = new RESTCategoryInTagV1();
                                addedCategory.setId(category.getItem().getId());
                                addedCategory.explicitSetRelationshipSort(tag.getItem().getRelationshipSort());

                                final RESTCategoryInTagCollectionItemV1 collectionItem = new RESTCategoryInTagCollectionItemV1();
                                collectionItem.setState(tag.getState());
                                collectionItem.setItem(addedCategory);

                                updateTag.getCategories().getItems().add(collectionItem);
                            }
                        }
                    }
                }

                /*
                 * Sync changes from the projects.
                 */
                if (projectsComponent.getPossibleChildrenProviderData().getItems() != null) {
                    updateTag.explicitSetProjects(new RESTProjectCollectionV1());
                    for (@NotNull final RESTProjectCollectionItemV1 project : projectsComponent.getPossibleChildrenProviderData().getItems()) {

                        checkState(project.getItem() != null, "The project collection item should reference a valid entity.");
                        checkState(project.getItem().getTags() != null, "The project collection item should reference a valid entity and have a valid tags collection.");

                        for (@NotNull final RESTTagCollectionItemV1 tag : project.getItem().getTags().returnDeletedAndAddedCollectionItems()) {
                            if (tag.getItem().getId().equals(updateTag.getId())) {

                                final RESTProjectV1 addedProject = new RESTProjectV1();
                                addedProject.setId(project.getItem().getId());

                                final RESTProjectCollectionItemV1 collectionItem = new RESTProjectCollectionItemV1();
                                collectionItem.setState(tag.getState());
                                collectionItem.setItem(addedProject);

                                updateTag.getProjects().getItems().add(collectionItem);
                            }
                        }
                    }
                }

                /*
                 * If this is a new tag, it needs to be saved in order to get the tag id to complete the category updates. Upon
                 * success, the categories will be updated.
                 */
                if (unsavedTagChanges) {
                    if (wasNewTag) {
                        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.createTag(updateTag), callback, display);
                    } else {
                        failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.saveTag(updateTag), callback, display);
                    }
                }
                /*
                 * If there are no tag changes but there are pending category changes, apply them. There should never be a situation
                 * where a there are no tag changes but the tag is new.
                 */
                else if (unsavedCategoryChanges && !wasNewTag) {
                    saveCategoryChanges(false, filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                }
            } finally {
                LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndDetailsPresenter.saveTagChanges()");
            }
        }

        /**
         * Saves the changes to the tags within the categories
         *
         * @param wasNewTag true if we just created a new tag
         * @param newTagId the id of the new tag, to replace any tags with the NULL_ID placeholder id if wasNewTag == true
         */
        private void saveCategoryChanges(final boolean wasNewTag, @NotNull final Integer newTagId) {
            try {
                LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndDetailsPresenter.saveCategoryChanges()");

                final RESTCallBack<RESTCategoryCollectionV1> callback = new RESTCallBack<RESTCategoryCollectionV1>() {
                    public void success(@NotNull final RESTCategoryCollectionV1 retValue) {
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                                /*
                                    Reload the list of categories and projects if this is the last REST call to succeed
                                */
                        if (categoriesComponent.getPossibleChildrenProviderData().getDisplayedItem() != null) {
                            categoriesComponent.refreshExistingChildList(categoriesComponent.getPossibleChildrenProviderData().getDisplayedItem().getItem());
                        }
                        categoriesComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

                        updateDisplayWithNewEntityData(wasNewTag);
                        Window.alert(PressGangCCMSUI.INSTANCE.TagSaveSuccess() + " " + newTagId);
                    }
                };

                @NotNull final RESTCategoryCollectionV1 updatedCategories = new RESTCategoryCollectionV1();

                checkState(categoriesComponent.getPossibleChildrenProviderData().isValid(), "The category provider data should be valid.");

                for (@NotNull final RESTCategoryCollectionItemV1 category : categoriesComponent.getPossibleChildrenProviderData().getItems()) {

                    checkState(category.getItem() != null, "The category collection item should reference a valid entity.");
                    checkState(category.getItem().getTags() != null, "The category collection item should reference a valid entity and have a valid tags collection.");

                    final List<RESTTagInCategoryCollectionItemV1> updatedItems = category.getItem().getTags().returnUpdatedCollectionItems();

                    /* this should always be greater than 0 */
                    if (updatedItems.size() != 0) {
                        /* Create the category that we are updating */
                        @NotNull final RESTCategoryV1 updatedCategory = new RESTCategoryV1();
                        updatedCategory.setId(category.getItem().getId());
                        updatedCategory.explicitSetTags(new RESTTagInCategoryCollectionV1());

                        /* Add it to the collection */
                        updatedCategories.addItem(updatedCategory);

                        for (@NotNull final RESTTagInCategoryCollectionItemV1 tag : updatedItems) {
                            /* create a new tag to represent the one that we are updating */
                            @NotNull final RESTTagInCategoryV1 updatedTag = new RESTTagInCategoryV1();
                            updatedTag.explicitSetRelationshipId(tag.getItem().getRelationshipId());
                            updatedTag.explicitSetRelationshipSort(tag.getItem().getRelationshipSort());

                            /*
                             * If we were editing a new tag, it is possible that a tag with a NULL_ID is in the category tags
                             * collection. If so, replace it with the id that was assigned to the created tag.
                             */
                            updatedTag.setId(tag.getItem().getId() == Constants.NULL_ID && wasNewTag ? newTagId : tag.getItem().getId());

                            /* add it to the collection */
                            updatedCategory.getTags().addUpdateItem(updatedTag);
                        }
                    }
                }

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.updateCategories(updatedCategories), callback, display);
            } finally {
                LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndDetailsPresenter.saveCategoryChanges()");
            }
        }

    };

    @Override
    public void go(@NotNull final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndDetailsPresenter.go()");

            clearContainerAndAddTopLevelPanel(container, display);
            bindSearchAndEditExtended(ServiceConstants.TAG_HELP_TOPIC, HISTORY_TOKEN, queryString);
        } finally {
            LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndDetailsPresenter.go()");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void bindSearchAndEditExtended(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
                    /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTTagV1> getNewEntityCallback = new GetNewEntityCallback<RESTTagV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTTagV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTTagV1> displayCallback) {
                final RESTCallBack<RESTTagV1> callback = new RESTCallBack<RESTTagV1>() {
                    @Override
                    public void success(@NotNull final RESTTagV1 retValue) {
                        checkArgument(retValue.getProjects() != null, "The initially retrieved entity should have an expanded projects collection");
                        checkArgument(retValue.getCategories() != null, "The initially retrieved entity should have an expanded categories collection");
                        displayCallback.displayNewEntity(retValue);
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTag(selectedEntity.getId()), callback, display);
            }
        };

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);

        filteredResultsComponent.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, pageId, queryString);
        projectsComponent.bindChildrenExtended(ServiceConstants.TAG_PROJECTS_HELP_TOPIC, pageId);
        categoriesComponent.bindDetailedChildrenExtended(ServiceConstants.TAG_CATEGORIES_HELP_TOPIC, pageId);
        resultComponent.bindExtended(ServiceConstants.TAG_DETAIL_HELP_TOPIC, pageId);
        commonExtendedPropertiesPresenter.bindDetailedChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, pageId);

        super.bindSearchAndEdit(topicId, pageId, Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

        bindCategoryColumnButtons();
        bindProjectColumnButtons();
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    /**
     * Binds behaviour to the project list buttons
     */
    private void bindProjectColumnButtons() {
        projectsComponent.getDisplay().getPossibleChildrenButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTProjectCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, @NotNull final RESTProjectCollectionItemV1 object, final String value) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndDetailsPresenter.bindProjectColumnButtons() FieldUpdater.update()");

                            checkState(object.getItem() != null, "The selected collection item to reference a valid entity.");
                            checkState(object.getItem().getTags() != null, "The selected collection item to reference a valid entity and have a valid tags collection.");
                            checkState(object.getItem().getTags().getItems() != null, "The selected collection item to reference a valid entity, have a valid tags collection, and has a valid items collection.");

                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() != null, "The displayed collection item to reference a valid entity with a valid id.");

                            boolean found = false;

                            for (@NotNull final RESTTagCollectionItemV1 tag : object.getItem().getTags().getItems()) {

                                checkState(tag.getItem().getId() != null, "The tag should have a valid id.");

                                if (tag.getItem().getId().equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId())) {
                                    /* Project was added and then removed */
                                    if (RESTBaseCollectionItemV1.ADD_STATE.equals(tag.getState())) {
                                        object.getItem().getTags().getItems().remove(tag);
                                    }

                                    /* Project existed, was removed and then was added again */
                                    if (RESTBaseCollectionItemV1.REMOVE_STATE.equals(tag.getState())) {
                                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                    }
                                    /* Project existed and was removed */
                                    else {
                                        tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                                    }

                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                final RESTTagV1 newTag = filteredResultsComponent.getProviderData().getDisplayedItem().getItem().clone(true);
                                object.getItem().getTags().addNewItem(newTag);
                            }

                            /*
                             * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set
                             * the configured parameters to reflect the fact that the category contains tags that will modify the
                             * database. So here we check to see if any tags have been added or removed. If there are none (i.e. a
                             * tag was added and then removed again without persisting the change in the database, or there were
                             * just no changes made) we remove the tags collection from the configured parameters.
                             */
                            if (object.getItem().getTags().returnDeletedAndAddedCollectionItems().size() != 0) {

                                /*
                                 * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set
                                 * the appropriate configured parameter value
                                 */
                                object.getItem().explicitSetTags(object.getItem().getTags());
                            } else {
                                object.getItem().getConfiguredParameters().remove(RESTBaseCategoryV1.TAGS_NAME);
                            }

                            /* refresh the project list */
                            projectsComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(projectsComponent.getPossibleChildrenProviderData().getItems());
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndDetailsPresenter.bindProjectColumnButtons() FieldUpdater.update()");
                        }
                    }
                });
    }

    /**
     * Binds behaviour to the category list buttons.
     */
    private void bindCategoryColumnButtons() {
        categoriesComponent.getDisplay().getPossibleChildrenButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTCategoryCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, @NotNull final RESTCategoryCollectionItemV1 object, final String value) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndDetailsPresenter.bindCategoryColumnButtons() FieldUpdater.update()");

                            checkState(object.getItem() != null, "The selected collection item to reference a valid entity.");
                            checkState(object.getItem().getTags() != null, "The selected collection item to reference a valid entity and have a valid tags collection.");
                            checkState(object.getItem().getTags().getItems() != null, "The selected collection item to reference a valid entity, have a valid tags collection, and has a valid items collection.");

                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                            checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId() != null, "The displayed collection item to reference a valid entity with a valid id.");

                            boolean found = false;
                            for (@NotNull final RESTTagInCategoryCollectionItemV1 tag : object.getItem().getTags().getItems()) {

                                checkState(tag.getItem().getId() != null, "The tag should have a valid id.");

                                if (tag.getItem().getId().equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId())) {
                                    /* Tag was added and then removed */
                                    if (tag.returnIsAddItem()) {
                                        object.getItem().getTags().getItems().remove(tag);
                                    }
                                    /* Tag existed, was removed and then was added again */
                                    else if (tag.returnIsRemoveItem()) {
                                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                                    }
                                    /* Tag existed and was removed */
                                    else {
                                        tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                                        tag.getItem().setRelationshipSort(0);
                                    }

                                    found = true;
                                    break;
                                }
                            }

                            if (!found) {
                                categoriesComponent.setSortOrderOfChildren(sortCallback);

                                @NotNull final RESTTagInCategoryV1 newTag = new RESTTagInCategoryV1();
                                newTag.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                                newTag.setName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());
                                newTag.setRelationshipSort(Constants.NEW_CHILD_SORT_ORDER);

                                object.getItem().getTags().addNewItem(newTag);
                            }

                            /*
                             * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set
                             * the configured parameters to reflect the fact that the category contains tags that will modify the
                             * database. So here we check to see if any tags have been added or removed. If there are none (i.e. a
                             * tag was added and then removed again without persisting the change in the database, or there were
                             * just no changes made) we remove the tags collection from the configured parameters.
                             */
                            if (object.getItem().getTags().returnDeletedAndAddedCollectionItems().size() != 0) {

                                /*
                                 * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set
                                 * the appropriate configured parameter value
                                 */
                                object.getItem().explicitSetTags(object.getItem().getTags());
                            } else {
                                object.getItem().getConfiguredParameters().remove(RESTBaseCategoryV1.TAGS_NAME);
                            }

                            /* refresh the category list */
                            categoriesComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(categoriesComponent.getPossibleChildrenProviderData().getItems());

                            /*
                             * refresh the list of tags in the category
                             */
                            categoriesComponent.getDisplay().setExistingChildrenProvider(categoriesComponent
                                    .generateExistingProvider(categoriesComponent.getPossibleChildrenProviderData()
                                            .getDisplayedItem().getItem()));
                        } finally {
                            LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndDetailsPresenter.bindCategoryColumnButtons() FieldUpdater.update()");
                        }
                    }
                });
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {

            resultComponent.getDisplay().getDriver().flush();

            return (unsavedTagChanged() || categoriesComponent.hasUnsavedChanges() || projectsComponent.hasUnsavedChanges());
        }

        return false;
    }

    /**
     * @return true if the tag has any unsaved changes
     */
    public boolean unsavedTagChanged() {
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
        checkState(filteredResultsComponent.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
        checkState(filteredResultsComponent.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

        final RESTTagV1 selected = filteredResultsComponent.getProviderData().getSelectedItem().getItem();
        final RESTTagV1 displayed = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

        /*
         * See if any items have been added or removed from the project and category lists
         */
        final boolean unsavedCategoryChanges = categoriesComponent.getPossibleChildrenProviderData().getItems() != null
                && ComponentRESTBaseEntityV1.returnDirtyStateForCollectionItems(categoriesComponent
                .getPossibleChildrenProviderData().getItems());

        final boolean unsavedProjectChanges = projectsComponent.getPossibleChildrenProviderData().getItems() != null
                && ComponentRESTBaseEntityV1.returnDirtyStateForCollectionItems(projectsComponent
                .getPossibleChildrenProviderData().getItems());

        /* See if any of the fields were changed */
        final boolean unsavedDescriptionChanges = !GWTUtilities.stringEqualsEquatingNullWithEmptyString(selected.getDescription(), displayed.getDescription());

        final boolean unsavedNameChanges = !GWTUtilities.stringEqualsEquatingNullWithEmptyString(selected.getName(), displayed.getName());

        /* If there are any modified property tags in newTopic, we have unsaved changes */
        final boolean unsavedExtendedProperties = displayed.getProperties() != null
                && !displayed.getProperties().returnDeletedAddedAndUpdatedCollectionItems().isEmpty();

        return unsavedCategoryChanges || unsavedProjectChanges || unsavedDescriptionChanges || unsavedNameChanges || unsavedExtendedProperties;
    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(filteredResultsComponent.getQuery(), newWindow));
        }
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                doSearch(GWTUtilities.isEventToOpenNewWindow(event));
            }
        });

        final KeyPressHandler searchKeyPressHandler = new KeyPressHandler() {
            @Override
            public void onKeyPress(@NotNull final KeyPressEvent event) {

                if (GWTUtilities.enterKeyWasPressed(event)) {
                    doSearch(false);
                }

            }
        };

        filteredResultsComponent.getDisplay().getDescriptionFilter().addKeyPressHandler(searchKeyPressHandler);
        filteredResultsComponent.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
        filteredResultsComponent.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

        filteredResultsComponent.getDisplay().getCreate().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                final RESTTagV1 selectedTag = new RESTTagV1();
                selectedTag.setId(Constants.NULL_ID);
                final RESTTagCollectionItemV1 selectedTagWrapper = new RESTTagCollectionItemV1(selectedTag);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                final RESTTagV1 displayedTag = new RESTTagV1();
                displayedTag.setId(Constants.NULL_ID);
                /* We assume all tags already have a properties collection present */
                displayedTag.setProperties(new RESTAssignedPropertyTagCollectionV1());
                final RESTTagCollectionItemV1 displayedTagWrapper = new RESTTagCollectionItemV1(displayedTag, RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                updateViewsAfterNewEntityLoaded();
            }
        });
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getTagCategoriesDown(), this.display.getTagCategories());
        this.display.replaceTopActionButton(this.display.getTagDetailsDown(), this.display.getTagDetails());
        this.display.replaceTopActionButton(this.display.getTagProjectsDown(), this.display.getTagProjects());
        this.display.replaceTopActionButton(this.display.getExtendedPropertiesDown(), this.display.getExtendedProperties());

        if (displayedView == this.categoriesComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagCategories(), this.display.getTagCategoriesDown());
        } else if (displayedView == this.projectsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagProjects(), this.display.getTagProjectsDown());
        } else if (displayedView == this.resultComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagDetails(), this.display.getTagDetailsDown());
        } else if (displayedView == this.commonExtendedPropertiesPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getExtendedProperties(), this.display.getExtendedPropertiesDown());
        }
    }

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        this.enableAndDisableActionButtons(displayedView);
        setHelpTopicForView(displayedView);

        /* save any changes to the tag details */
        if (lastDisplayedView == this.resultComponent.getDisplay()) {
            /*
             * If this tag was added to a category, the it was cloned with the old tag name. Here we reflect the current tag
             * name in the category tag lists.
             */
            if (this.categoriesComponent.getPossibleChildrenProviderData().getDisplayedItem() != null) {
                final RESTTagInCategoryV1 tag = ComponentCategoryV1.returnTag(this.categoriesComponent
                        .getPossibleChildrenProviderData().getDisplayedItem().getItem(), filteredResultsComponent
                        .getProviderData().getDisplayedItem().getItem().getId());
                if (tag != null) {

                    /* update the tag in the category list */
                    tag.setName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());

                    /* refresh the list */
                    this.categoriesComponent.getDisplay().getExistingChildrenProvider().displayNewFixedList(
                            this.categoriesComponent.getExistingProviderData().getItems());
                }
            }
        }

        /* refresh the project list */
        if (displayedView == projectsComponent.getDisplay()) {
            /* If we switch to this view before the projects have been downloaded, there is nothing to update */
            if (projectsComponent.getDisplay().getPossibleChildrenProvider() != null && projectsComponent.getPossibleChildrenProviderData().getItems() != null) {
                projectsComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                        projectsComponent.getPossibleChildrenProviderData().getItems());
            }
        }
        /* refresh the category list */
        else if (displayedView == categoriesComponent.getDisplay()) {
            /* If we switch to this view before the categories have been downloaded, there is nothing to update */
            if (categoriesComponent.getDisplay().getPossibleChildrenProvider() != null && categoriesComponent.getPossibleChildrenProviderData().getItems() != null) {
                categoriesComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                        categoriesComponent.getPossibleChildrenProviderData().getItems());
            }
        }

        /* Update the page name */
        @NotNull final StringBuilder title = new StringBuilder(displayedView.getPageName());
        if (this.filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            final String tagTitle = this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName();
            title.append(": " + (tagTitle == null ? PressGangCCMSUI.INSTANCE.NoTitle() : tagTitle));
        }
        display.getPageTitle().setText(title.toString());
    }

    /**
     * Called when a new tag is selected or the tag is saved. This refreshes the list of categories and projects.
     *
     * @param removeCategoryTagListFromScreen
     *         true if the list of tags within a category is to be removed from the screen
     */
    private void resetCategoryAndProjectsLists(final boolean removeCategoryTagListFromScreen) {

        resetCategoryLists();
        resetProjectList();

        /* remove the category tags list */
        if (removeCategoryTagListFromScreen) {
            categoriesComponent.getPossibleChildrenProviderData().setSelectedItem(null);
            categoriesComponent.getPossibleChildrenProviderData().setDisplayedItem(null);
            categoriesComponent.getDisplay().getSplit().remove(categoriesComponent.getDisplay().getExistingChildrenResultsPanel());
        }
    }

    /**
     * Reset the category list
     */
    private void resetCategoryLists() {
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        categoriesComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
    }

    /**
     * Reset the project list
     */
    private void resetProjectList() {
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        projectsComponent.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
    }

    @Override
    protected void bindActionButtons() {
        display.getTagDetails().addClickHandler(tagDetailsClickHandler);
        display.getTagProjects().addClickHandler(tagProjectsClickHandler);
        display.getSave().addClickHandler(saveClickHandler);
        display.getTagCategories().addClickHandler(tagCategoriesClickHandler);
        display.getExtendedProperties().addClickHandler(tagExtendedPropertiesClickHandler);
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        resetCategoryAndProjectsLists(true);

        /*
            Display the list of assigned property tags.
         */
        Collections.sort(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getProperties().getItems(),
                new RESTAssignedPropertyTagCollectionItemV1NameAndRelationshipIDSort());
        commonExtendedPropertiesPresenter.refreshExistingChildList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

        /* Get a new collection of property tags. */
        commonExtendedPropertiesPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {

        checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

        final RESTTagV1 displayedTag = filteredResultsComponent.getProviderData().getDisplayedItem().getItem();

        final List<BaseCustomViewInterface> displayableViews = new ArrayList<BaseCustomViewInterface>();
        displayableViews.add(resultComponent.getDisplay());
        displayableViews.add(projectsComponent.getDisplay());
        displayableViews.add(categoriesComponent.getDisplay());
        displayableViews.add(commonExtendedPropertiesPresenter.getDisplay());

        for (@NotNull final BaseCustomViewInterface view : displayableViews) {
            if (viewIsInFilter(filter, view)) {
                view.display(displayedTag, false);
            }
        }

        if (viewIsInFilter(filter, projectsComponent.getDisplay())) {
            projectsComponent.displayChildrenExtended(displayedTag, false);
        }

        if (viewIsInFilter(filter, categoriesComponent.getDisplay())) {
            categoriesComponent.displayDetailedChildrenExtended(displayedTag, false);
        }

        if (viewIsInFilter(filter, commonExtendedPropertiesPresenter.getDisplay())) {
            commonExtendedPropertiesPresenter.displayDetailedChildrenExtended(displayedTag, false);
        }
    }

    private void setHelpTopicForView(@NotNull final BaseTemplateViewInterface view) {
        if (view == projectsComponent.getDisplay()) {
            setHelpTopicId(projectsComponent.getHelpTopicId());
        } else if (view == categoriesComponent.getDisplay()) {
            setHelpTopicId(categoriesComponent.getHelpTopicId());
        } else if (view == resultComponent.getDisplay()) {
            setHelpTopicId(resultComponent.getHelpTopicId());
        }
    }

}
