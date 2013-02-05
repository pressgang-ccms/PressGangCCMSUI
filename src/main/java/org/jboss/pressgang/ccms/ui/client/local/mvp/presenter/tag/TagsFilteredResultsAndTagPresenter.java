package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.errai.bus.client.api.Message;
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
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TagsFilteredResultsAndTagPresenter
        extends
        BaseSearchAndEditComponent<TagFilteredResultsPresenter.Display, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, TagViewInterface, TagPresenter.Display, RESTTagV1BasicDetailsEditor>
        implements BaseTemplatePresenterInterface {

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the four views
     * TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and TagCategoriesView.
     *
     * @author Matthew Casperson
     *
     */
    public interface Display extends BaseSearchAndEditViewInterface<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {

        PushButton getTagProjects();

        PushButton getTagDetails();

        PushButton getSave();

        PushButton getTagCategories();

        Label getTagCategoriesDown();

        Label getTagProjectsDown();

        Label getTagDetailsDown();
    }

    /** The history token used to identify this view */
    public static final String HISTORY_TOKEN = "TagsFilteredResultsAndTagView";

    @Inject
    private HandlerManager eventBus;

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(TagsFilteredResultsAndTagPresenter.class.getName());

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

    /** The tag query string extracted from the history token */
    private String queryString;

    /**
     * Used when moving children up and down
     */
    private final SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> sortCallback = new SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>() {

        @Override
        public boolean setSort(final RESTTagInCategoryCollectionItemV1 child, final int index) {
            if (child.getItem().getRelationshipSort() != index)  {
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
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
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
                LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndTagPresenter.saveTagChanges()");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewTag = filteredResultsComponent.getProviderData().getDisplayedItem().returnIsAddItem();

                /* Save any changes made to the tag entity itself */
                final RESTCallback<RESTTagV1> callback = new BaseRestCallback<RESTTagV1, TagsFilteredResultsAndTagPresenter.Display>(
                        display, new BaseRestCallback.SuccessAction<RESTTagV1, TagsFilteredResultsAndTagPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTTagV1 retValue,
                                                final TagsFilteredResultsAndTagPresenter.Display display) {

                        /* we are now viewing the object returned by the save */
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                        filteredResultsComponent.getProviderData().getDisplayedItem()
                                .setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        /* Update the list of tags with any saved changes */
                        retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);

                        /* refresh the projects list */
                        projectsComponent.refreshPossibleChildrenDataAndList();

                        if (unsavedCategoryChanges) {
                            saveCategoryChanges(wasNewTag, filteredResultsComponent.getProviderData().getDisplayedItem()
                                    .getItem().getId());
                        } else {
                            updateDisplayAfterSave(wasNewTag);
                            Window.alert(PressGangCCMSUI.INSTANCE.TagSaveSuccess() + " " + retValue.getId());
                        }

                    }
                });

                /* Sync changes from the tag view */
                final RESTTagV1 updateTag = new RESTTagV1();
                updateTag.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                updateTag.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                        .getDescription());
                updateTag.explicitSetName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());

                /*
                 * Sync changes from the projects. categoriesComponent.getProviderData().getItems() contains a collection of all the
                 * projects, and their tags collections contain any added or removed tag relationships. Here we copy those modified
                 * relationships into the updateTag, so the changes are all done in one transaction.
                 */
                if (categoriesComponent.getPossibleChildrenProviderData().getItems() != null) {

                    updateTag.explicitSetCategories(new RESTCategoryInTagCollectionV1());

                    for (final RESTCategoryCollectionItemV1 category : categoriesComponent.getPossibleChildrenProviderData()
                            .getItems()) {
                        for (final RESTTagInCategoryCollectionItemV1 tag : category.getItem().getTags()
                                .returnDeletedAndAddedCollectionItems()) {
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
                    for (final RESTProjectCollectionItemV1 project : projectsComponent.getPossibleChildrenProviderData().getItems()) {
                        for (final RESTTagCollectionItemV1 tag : project.getItem().getTags().returnDeletedAndAddedCollectionItems()) {
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
                        RESTCalls.createTag(callback, updateTag);
                    } else {
                        RESTCalls.saveTag(callback, updateTag);
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
                LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndTagPresenter.saveTagChanges()");
            }
        }

        /**
         * Saves the changes to the tags within the categories
         *
         * @param wasNewTag true if we just created a new tag
         * @param newTagId the id of the new tag, to replace any tags with the NULL_ID placeholder id if wasNewTag == true
         */
        private void saveCategoryChanges(final boolean wasNewTag, final Integer newTagId) {
            try {
                LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndTagPresenter.saveCategoryChanges()");

                /* Save any changes made to the tag entity itself */
                final RESTCallback<RESTCategoryCollectionV1> callback = new RESTCalls.RESTCallback<RESTCategoryCollectionV1>() {
                    @Override
                    public void begin() {
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTCategoryCollectionV1 retValue) {
                        try {
                            /*
                             * Reload the list of categories and projects if this is the last REST call to succeed
                             */
                            if (categoriesComponent.getPossibleChildrenProviderData().getDisplayedItem() != null) {
                                categoriesComponent.refreshExistingChildList(categoriesComponent.getPossibleChildrenProviderData()
                                        .getDisplayedItem().getItem());
                            }
                            categoriesComponent.refreshPossibleChildrenDataAndList();

                            updateDisplayAfterSave(wasNewTag);
                            Window.alert(PressGangCCMSUI.INSTANCE.TagSaveSuccess() + " " + newTagId);
                        } finally {
                            display.removeWaitOperation();
                        }

                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }
                };

                final RESTCategoryCollectionV1 updatedCategories = new RESTCategoryCollectionV1();

                for (final RESTCategoryCollectionItemV1 category : categoriesComponent.getPossibleChildrenProviderData().getItems()) {
                    final List<RESTTagInCategoryCollectionItemV1> updatedItems = category.getItem().getTags()
                            .returnUpdatedCollectionItems();

                    /* this should always be greater than 0 */
                    if (updatedItems.size() != 0) {
                        /* Create the category that we are updating */
                        final RESTCategoryV1 updatedCategory = new RESTCategoryV1();
                        updatedCategory.setId(category.getItem().getId());
                        updatedCategory.explicitSetTags(new RESTTagInCategoryCollectionV1());

                        /* Add it to the collection */
                        updatedCategories.addItem(updatedCategory);

                        for (final RESTTagInCategoryCollectionItemV1 tag : updatedItems) {
                            /* create a new tag to represent the one that we are updating */
                            final RESTTagInCategoryV1 updatedTag = new RESTTagInCategoryV1();
                            updatedTag.explicitSetRelationshipId(tag.getItem().getRelationshipId());
                            updatedTag.explicitSetRelationshipSort(tag.getItem().getRelationshipSort());

                            /*
                             * If we were editing a new tag, it is possible that a tag with a NULL_ID is in the category tags
                             * collection. If so, replace it with the id that was assigned to the created tag.
                             */
                            updatedTag.setId(tag.getItem().getId() == Constants.NULL_ID && wasNewTag ? newTagId : tag.getItem()
                                    .getId());

                            /* add it to the collection */
                            updatedCategory.getTags().addUpdateItem(updatedTag);
                        }
                    }
                }

                RESTCalls.updateCategories(callback, updatedCategories);
            } finally {
                LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndTagPresenter.saveCategoryChanges()");
            }
        }

    };

    @Override
    public void go(@NotNull final HasWidgets container) {
        try {
            LOGGER.log(Level.INFO, "ENTER TagsFilteredResultsAndTagPresenter.go()");

            /* A call back used to get a fresh copy of the entity that was selected */
            final GetNewEntityCallback<RESTTagV1> getNewEntityCallback = new GetNewEntityCallback<RESTTagV1>() {

                @Override
                public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTTagV1> displayCallback) {
                    final RESTCallback<RESTTagV1> callback = new BaseRestCallback<RESTTagV1, BaseTemplateViewInterface>(
                            display, new BaseRestCallback.SuccessAction<RESTTagV1, BaseTemplateViewInterface>() {
                        @Override
                        public void doSuccessAction(final RESTTagV1 retValue, final BaseTemplateViewInterface display) {
                            displayCallback.displayNewEntity(retValue);
                        }
                    });
                    RESTCalls.getTag(callback, id);
                }
            };

            clearContainerAndAddTopLevelPanel(container, display);

            display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

            display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                    Preferences.INSTANCE.getInt(Preferences.TAG_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);

            filteredResultsComponent.bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
            projectsComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
            categoriesComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
            resultComponent.bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);

            super.bindSearchAndEdit(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, resultComponent.getDisplay(), resultComponent.getDisplay(),
                    filteredResultsComponent.getDisplay(), filteredResultsComponent, display, display, getNewEntityCallback);

            bindCategoryColumnButtons();
            bindProjectColumnButtons();
        }
        finally {
            LOGGER.log(Level.INFO, "EXIT TagsFilteredResultsAndTagPresenter.go()");
        }
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
                    public void update(final int index, final RESTProjectCollectionItemV1 object, final String value) {
                        boolean found = false;

                        for (final RESTTagCollectionItemV1 tag : object.getItem().getTags().getItems()) {
                            if (tag.getItem().getId()
                                    .equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId())) {
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
                            final RESTTagV1 newTag = filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                                    .clone(true);
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
                        projectsComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                                projectsComponent.getPossibleChildrenProviderData().getItems());
                    }
                });
    }

    /**
     * Binds behaviour to the category list buttons
     */
    private void bindCategoryColumnButtons() {
        categoriesComponent.getDisplay().getPossibleChildrenButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTCategoryCollectionItemV1, String>() {
                    @Override
                    public void update(final int index, final RESTCategoryCollectionItemV1 object, final String value) {
                        boolean found = false;
                        for (final RESTTagInCategoryCollectionItemV1 tag : object.getItem().getTags().getItems()) {
                            if (tag.getItem().getId()
                                    .equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId())) {
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

                            final RESTTagInCategoryV1 newTag = new RESTTagInCategoryV1();
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
                        categoriesComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                                categoriesComponent.getPossibleChildrenProviderData().getItems());

                        /*
                         * refresh the list of tags in the category
                         */
                        categoriesComponent.getDisplay().setExistingChildrenProvider(categoriesComponent
                                .generateExistingProvider(categoriesComponent.getPossibleChildrenProviderData()
                                        .getDisplayedItem().getItem()));
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
     *
     * @return true if the tag has any unsaved changes
     */
    public boolean unsavedTagChanged() {
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
        final boolean unsavedDescriptionChanges = !GWTUtilities.stringEqualsEquatingNullWithEmptyString(
                filteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription());

        final boolean unsavedNameChanges = !GWTUtilities.stringEqualsEquatingNullWithEmptyString(filteredResultsComponent
                .getProviderData().getSelectedItem().getItem().getName(), filteredResultsComponent.getProviderData()
                .getDisplayedItem().getItem().getName());

        return unsavedCategoryChanges || unsavedProjectChanges || unsavedDescriptionChanges || unsavedNameChanges;
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        filteredResultsComponent.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (isOKToProceed()) {
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(filteredResultsComponent.getQuery(), GWTUtilities
                            .isEventToOpenNewWindow(event)));
                }
            }
        });

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
                final RESTTagCollectionItemV1 displayedTagWrapper = new RESTTagCollectionItemV1(displayedTag,
                        RESTBaseCollectionItemV1.ADD_STATE);

                filteredResultsComponent.getProviderData().setSelectedItem(selectedTagWrapper);
                filteredResultsComponent.getProviderData().setDisplayedItem(displayedTagWrapper);

                initializeViews();

                resetCategoryAndProjectsLists(true);

                switchView(lastDisplayedView == null ? resultComponent.getDisplay() : lastDisplayedView);
            }
        });
    }

    private void enableAndDisableActionButtons(final TagViewInterface displayedView)
    {
        this.display.replaceTopActionButton(this.display.getTagCategoriesDown(), this.display.getTagCategories());
        this.display.replaceTopActionButton(this.display.getTagDetailsDown(), this.display.getTagDetails());
        this.display.replaceTopActionButton(this.display.getTagProjectsDown(), this.display.getTagProjects());

        if (displayedView == this.categoriesComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagCategories(), this.display.getTagCategoriesDown());
        } else if (displayedView == this.projectsComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagProjects(), this.display.getTagProjectsDown());
        } else if (displayedView == this.resultComponent.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getTagDetails(), this.display.getTagDetailsDown());
        }
    }

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void afterSwitchView(@NotNull final TagViewInterface displayedView) {

        this.enableAndDisableActionButtons(displayedView);

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
            if (projectsComponent.getDisplay().getPossibleChildrenProvider() != null
                    && projectsComponent.getPossibleChildrenProviderData().getItems() != null) {
                projectsComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                        projectsComponent.getPossibleChildrenProviderData().getItems());
            }
        }
        /* refresh the category list */
        else if (displayedView == categoriesComponent.getDisplay()) {
            /* If we switch to this view before the categories have been downloaded, there is nothing to update */
            if (categoriesComponent.getDisplay().getPossibleChildrenProvider() != null
                    && categoriesComponent.getPossibleChildrenProviderData().getItems() != null) {
                categoriesComponent.getDisplay().getPossibleChildrenProvider().displayNewFixedList(
                        categoriesComponent.getPossibleChildrenProviderData().getItems());
            }
        }

        /* Update the page name */
        final StringBuilder title = new StringBuilder(displayedView.getPageName());
        if (this.filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            final String tagTitle = this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName();
            title.append(": " + (tagTitle == null ? PressGangCCMSUI.INSTANCE.NoTitle() : tagTitle));
        }
        display.getPageTitle().setText(title.toString());
    }

    /**
     * Called when a new tag is selected or the tag is saved. This refreshes the list of categories and projects.
     *
     * @param removeCatgeoryTagListFromScreen true if the list of tags within a category is to be removed from the screen
     */
    private void resetCategoryAndProjectsLists(final boolean removeCatgeoryTagListFromScreen) {

        resetCategoryLists();
        resetProjectList();

        /* remove the category tags list */
        if (removeCatgeoryTagListFromScreen) {
            categoriesComponent.getPossibleChildrenProviderData().setSelectedItem(null);
            categoriesComponent.getPossibleChildrenProviderData().setDisplayedItem(null);
            categoriesComponent.getDisplay().getSplit().remove(categoriesComponent.getDisplay().getExistingChildrenResultsPanel());
        }
    }

    /**
     * Reset the category list
     */
    private void resetCategoryLists() {
        categoriesComponent.getPossibleChildrenProviderData().reset();
        categoriesComponent.refreshPossibleChildrenDataAndList();
    }

    /**
     * Reset the project list
     */
    private void resetProjectList() {
        projectsComponent.getPossibleChildrenProviderData().reset();
        projectsComponent.refreshPossibleChildrenDataAndList();
    }

    @Override
    protected void bindActionButtons() {
        display.getTagDetails().addClickHandler(tagDetailsClickHandler);
        display.getTagProjects().addClickHandler(tagProjectsClickHandler);
        display.getSave().addClickHandler(saveClickHandler);
        display.getTagCategories().addClickHandler(tagCategoriesClickHandler);
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        resetCategoryAndProjectsLists(true);
    }

    @Override
    protected void initializeViews(@Nullable final List<TagViewInterface> filter) {
        for (final TagViewInterface view : new TagViewInterface[] { resultComponent.getDisplay(), projectsComponent.getDisplay(), categoriesComponent.getDisplay() }) {
            if (viewIsInFilter(filter, view)) {
                view.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
            }
        }
    }

}
