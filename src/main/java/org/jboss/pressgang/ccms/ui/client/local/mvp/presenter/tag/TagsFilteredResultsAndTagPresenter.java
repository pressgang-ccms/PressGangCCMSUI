package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTCategoryInTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentRESTBaseEntityV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.sort.RESTTagCategoryCollectionItemV1SortComparator;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.Holder;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

/**
 * This presenter takes the TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and
 * TagCategoriesView to provide a way to edit the properties and relationships of a tag.
 * 
 * @author Matthew Casperson
 */
@Dependent
public class TagsFilteredResultsAndTagPresenter extends TemplatePresenter {



    /** The history token used to identify this view */
    public static final String HISTORY_TOKEN = "TagsFilteredResultsAndTagView";

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the four views
     * TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and TagCategoriesView.
     * 
     * @author Matthew Casperson
     * 
     */
    public interface Display extends BaseTemplateViewInterface {
        /**
         * @return The panel used to hold the list of tags
         */
        SimpleLayoutPanel getResultsPanel();

        /**
         * @return The panel used to hold the views that display the tag details
         */
        SimpleLayoutPanel getViewPanel();

        /**
         * @return The panel that holds the action buttons for the tag detail views
         */
        SimpleLayoutPanel getViewActionButtonsPanel();

        /**
         * @return The panel that holds the action buttons for the list of tags
         */
        SimpleLayoutPanel getResultsActionButtonsPanel();

        /**
         * @return The split panel that separates the tag list from the tag details views
         */
        HandlerSplitLayoutPanel getSplitPanel();
    }
    
    public interface LogicComponent extends Component<Display> {

    }

    /**
     * A click handler used to display the tag fields view
     */
    private final ClickHandler tagDetailsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            displayedView = resultDisplay;
            reInitialiseView();
        }

    };

    /**
     * A click handler used to display the tag projects view
     */
    private final ClickHandler tagProjectsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            displayedView = projectsDisplay;
            reInitialiseView();
        }
    };

    /**
     * A click handler used to display the tag categories view
     */
    private final ClickHandler tagCategoriesClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            displayedView = categoriesDisplay;
            reInitialiseView();
        }
    };

    /**
     * A click handler used to save any changes to the tag
     */
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            /* Sync the UI to the underlying object */
            resultDisplay.getDriver().flush();

            final boolean unsavedTagChanges = unsavedTagChanged();
            final boolean unsavedCategoryChanges = unsavedCategoryChanges();

            /*
             * We refresh the list of categories when both save operations are complete. To do this we need to know when the
             * final REST call has succeeded, which is noted by these two variables.
             */
            final Holder<Boolean> tagSaveComplete = new Holder<Boolean>(!unsavedTagChanges);
            final Holder<Boolean> categorySaveComplete = new Holder<Boolean>(!unsavedCategoryChanges);

            /* Only attempt to save changes if there are changes to save */
            if (unsavedTagChanged()) {

                /* Save any changes made to the tag entity itself */
                final RESTCalls.RESTCallback<RESTTagV1> callback = new RESTCalls.RESTCallback<RESTTagV1>() {
                    @Override
                    public void begin() {
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                        tagSaveComplete.setValue(true);
                    }

                    @Override
                    public void success(final RESTTagV1 retValue) {
                        try {
                            tagSaveComplete.setValue(true);

                            /* we are now viewing the object returned by the save */
                            retValue.cloneInto(tagProviderData.getDisplayedItem().getItem(), true);

                            /* Update the list of tags with any saved changes */
                            retValue.cloneInto(tagProviderData.getSelectedItem().getItem(), true);

                            /* refresh the list of tags */
                            filteredResultsDisplay.getProvider().displayNewFixedList(tagProviderData.getItems());

                            /*
                             * Reload the list of categoires and projects if this is the last REST call to succeed
                             */
                            if (categorySaveComplete.getValue())
                                resetCategoryAndProjectsLists(false);

                            /* refresh the display */
                            reInitialiseView();
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                        tagSaveComplete.setValue(true);
                    }
                };

                /* Sync changes from the tag view */
                final RESTTagV1 updateTag = new RESTTagV1();
                updateTag.setId(tagProviderData.getDisplayedItem().getItem().getId());
                updateTag.explicitSetDescription(tagProviderData.getDisplayedItem().getItem().getDescription());
                updateTag.explicitSetName(tagProviderData.getDisplayedItem().getItem().getName());

                /*
                 * Sync changes from the categories. categoryProviderData.getItems() contains a collection of all the
                 * categories, and their tags collections contain any added or removed tag relationships. Here we copy those
                 * modified relationships into the updateTag, so the changes are all done in one transaction.
                 */
                if (categoryProviderData.getItems() != null) {
                    updateTag.explicitSetCategories(new RESTCategoryInTagCollectionV1());
                    for (final RESTCategoryCollectionItemV1 category : categoryProviderData.getItems()) {
                        for (final RESTTagInCategoryCollectionItemV1 tag : category.getItem().getTags()
                                .returnDeletedAndAddedCollectionItems()) {
                            /*
                             * It should only be possible to add the currently displayed tag to the categories
                             */
                            if (tag.getItem().getId().equals(updateTag.getId())) {

                                final RESTCategoryInTagV1 addedCategory = new RESTCategoryInTagV1();
                                addedCategory.setId(category.getItem().getId());

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
                if (projectProviderData.getItems() != null) {
                    updateTag.explicitSetProjects(new RESTProjectCollectionV1());
                    for (final RESTProjectCollectionItemV1 project : projectProviderData.getItems()) {
                        for (final RESTTagCollectionItemV1 tag : project.getItem().getTags()
                                .returnDeletedAndAddedCollectionItems()) {
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

                RESTCalls.saveTag(callback, updateTag);
            }

            /* Save the changes to the categories in a separate REST call */
            if (unsavedCategoryChanges()) {

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
                        categorySaveComplete.setValue(true);
                    }

                    @Override
                    public void success(final RESTCategoryCollectionV1 retValue) {
                        try {
                            /*
                             * Reload the list of categoires and projects if this is the last REST call to succeed
                             */
                            if (tagSaveComplete.getValue())
                                resetCategoryAndProjectsLists(false);

                            /* refresh the display */
                            reInitialiseView();
                        } finally {
                            display.removeWaitOperation();
                            categorySaveComplete.setValue(true);
                        }

                    }

                    @Override
                    public void failed() {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                        categorySaveComplete.setValue(true);
                    }
                };

                final RESTCategoryCollectionV1 updatedCategories = new RESTCategoryCollectionV1();

                for (final RESTCategoryCollectionItemV1 category : categoryProviderData.getItems()) {
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
                            /* create a new tag to represent the one that we are updaing */
                            final RESTTagInCategoryV1 updatedTag = new RESTTagInCategoryV1();
                            updatedTag.explicitSetRelationshipId(tag.getItem().getRelationshipId());
                            updatedTag.explicitSetRelationshipSort(tag.getItem().getRelationshipSort());
                            updatedTag.setId(tag.getItem().getId());

                            /* add it to the collection */
                            updatedCategory.getTags().addUpdateItem(updatedTag);
                        }
                    }
                }

                RESTCalls.saveCategories(callback, updatedCategories);
            }
        }
    };

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;
    
    @Inject private LogicComponent component;

    /**
     * An Errai injected instance of a class that implements TagFilteredResultsPresenter.Display. This is the view that displays
     * the list of tags.
     */
    @Inject
    private TagFilteredResultsPresenter.Display filteredResultsDisplay;
    
    @Inject TagFilteredResultsPresenter.LogicComponent filteredResultsComponent;

    /**
     * An Errai injected instance of a class that implements TagPresenter.Display. This is the view that displays the fields of
     * the tag (name, description etc)
     */
    @Inject
    private TagPresenter.Display resultDisplay;
    
    @Inject private TagPresenter.LogicComponent resultComponent;

    /**
     * An Errai injected instance of a class that implements TagProjectsPresenter.Display. This is the view that lists all the
     * projects that the tag can be added to or removed from.
     */
    @Inject
    private TagProjectsPresenter.Display projectsDisplay;
    
    @Inject TagProjectsPresenter.LogicComponent projectsComponent;

    /**
     * An Errai injected instance of a class that implements TagCategoriesPresenter.Display. This is the view that lists all the
     * categories that the tag can be added to or removed from.
     */
    @Inject
    private TagCategoriesPresenter.Display categoriesDisplay;
    
    @Inject TagCategoriesPresenter.LogicComponent categoriesComponent; 

    /** The tag query string extracted from the history token */
    private String queryString;

    
    

    /** The currently displayed view */
    private TagViewInterface displayedView;

    /** The last displayed view */
    private TagViewInterface lastDisplayedView;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        display.getSplitPanel().setSplitPosition(display.getResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        categoriesDisplay.getSplit().setSplitPosition(categoriesDisplay.getSearchResultsPanel(),
                Preferences.INSTANCE.getInt(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);
        
        component.bind(display,  display);
        filteredResultsComponent.bind(filteredResultsDisplay, display);
        projectsComponent.bind(projectsDisplay, display);
        categoriesComponent.bind(categoriesDisplay,  display);

        bind();
    }

    /**
     * Add behaviour to the UI elements exposed by the views
     */
    private void bind() {
 
        bindTagListRowClicks();

        bindCategoryListRowClicks();

        bindSearchButtons();

        bindTagViewButtons();

        bindProjectColumnButtons();

        bindCategoryColumnButtons();

        bindCategoryTagsColumnButtons();

        bindMainSplitResize();

        bindCategorySplitResize();
    }

    /**
     * Saves the width of the split screen in the category view
     */
    private void bindCategorySplitResize() {
        categoriesDisplay.getSplit().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, categoriesDisplay.getSplit()
                        .getSplitPosition(categoriesDisplay.getSearchResultsPanel()) + "");
            }
        });
    }

    /**
     * Saves the width of the split screen
     */
    private void bindMainSplitResize() {
        display.getSplitPanel().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.TAG_CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display.getSplitPanel()
                        .getSplitPosition(display.getResultsPanel()) + "");
            }
        });
    }

    /**
     * Get the collection of projects, to which we will add or remove the currently selected tag. Note that the changes made to
     * this collection will be synced in reverse to the tag when the save button is clicked i.e. where the displayed tag is
     * added to a project, that will actually be persisted through the REST interface as a project added to the displayed tag.
     */
    private void getProjects() {
        final RESTCalls.RESTCallback<RESTProjectCollectionV1> callback = new RESTCalls.RESTCallback<RESTProjectCollectionV1>() {
            @Override
            public void begin() {
                projectsDisplay.addWaitOperation();
            }

            @Override
            public void generalException(final Exception e) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                projectsDisplay.removeWaitOperation();
            }

            @Override
            public void success(final RESTProjectCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    projectProviderData.setStartRow(0);
                    projectProviderData.setItems(retValue.getItems());

                    /* Refresh the list */
                    projectsDisplay.getProvider().displayNewFixedList(projectProviderData.getItems());

                } finally {
                    projectsDisplay.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                projectsDisplay.removeWaitOperation();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
        projectProviderData.reset();
        projectsDisplay.getProvider().resetProvider();

        RESTCalls.getProjects(callback);
    }

    /**
     * Get the collection of categories, to which we will add or remove the currently selected tag. Note that the changes made
     * to this collection will be synced in reverse to the tag when the save button is clicked i.e. where the displayed tag is
     * added to a project, that will actually be persisted through the REST interface as a category added to the displayed tag.
     */
    private void getCategories() {
        final RESTCalls.RESTCallback<RESTCategoryCollectionV1> callback = new RESTCalls.RESTCallback<RESTCategoryCollectionV1>() {
            @Override
            public void begin() {
                categoriesDisplay.addWaitOperation();
            }

            @Override
            public void generalException(final Exception e) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                categoriesDisplay.removeWaitOperation();
            }

            @Override
            public void success(final RESTCategoryCollectionV1 retValue) {
                try {
                    categoryProviderData.setStartRow(0);
                    /* Zero results can be a null list */
                    categoryProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTCategoryCollectionItemV1>()
                            : retValue.getItems());

                    categoriesDisplay.getProvider().displayNewFixedList(categoryProviderData.getItems());

                } finally {
                    categoriesDisplay.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                categoriesDisplay.removeWaitOperation();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

        /* Redisplay the loading widget. updateRowCount(0, false) is used to display the cell table loading widget. */
        categoryProviderData.reset();
        categoriesDisplay.getProvider().resetProvider();

        RESTCalls.getCategories(callback);
    }

    private void moveTagsUpAndDown(final RESTTagInCategoryCollectionItemV1 object, final boolean down) {

        final int size = categoryTagsProviderData.getItems().size();

        boolean modifiedSort = false;

        /* if moving down, start at the begining, and end on the second last item. If moving up, start the second item */
        for (int i = (down ? 0 : 1); i < (down ? size - 1 : size); ++i) {

            /* Get the item from the collection for convenience */
            final RESTTagInCategoryCollectionItemV1 tagInCatgeory = categoryTagsProviderData.getItems().get(i);

            if (tagInCatgeory.getItem().getId().equals(object.getItem().getId())) {

                /*
                 * The sort values are exposed directly in the old UI. This means that it is possible for two tags to have the
                 * same or no sort value assigned to them, or have sort values that increment in odd values.
                 * 
                 * If we are changing the sort order in the new UI, we need a consistent progression of the sort field. So, now
                 * that we have found a tag that needs changing, we start by reassigning sort values based on the location of
                 * the tag in the collection.
                 */

                for (int j = 0; j < size; ++j) {
                    /* get the item from the collection */
                    final RESTTagInCategoryCollectionItemV1 existingTagInCatgeory = categoryTagsProviderData.getItems().get(j);

                    /* set the sort value (the list was previously sorted on this value) */
                    existingTagInCatgeory.getItem().setRelationshipSort(j);

                    /* we need to mark the joiner entity as updated */
                    if (!existingTagInCatgeory.returnIsAddItem())
                        existingTagInCatgeory.setState(RESTTagInCategoryCollectionItemV1.UPDATE_STATE);
                }

                /* The next item is either the item before (if moving up) of the item after (if moving down) */
                final int nextItemIndex = down ? i + 1 : i - 1;

                /* get the next item in the list */
                final RESTTagInCategoryCollectionItemV1 nextTagInCatgeory = categoryTagsProviderData.getItems().get(
                        nextItemIndex);

                /* swap the sort field */
                tagInCatgeory.getItem().setRelationshipSort(nextItemIndex);
                nextTagInCatgeory.getItem().setRelationshipSort(i);

                modifiedSort = true;
                break;
            }
        }

        if (modifiedSort) {
            /* redisplay the fixed list */
            categoriesDisplay.setTagsProvider(generateCategoriesTagListProvider());
        }
    }

    private void bindCategoryTagsColumnButtons() {
        categoriesDisplay.getTagUpButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

            @Override
            public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                moveTagsUpAndDown(object, false);
            }

        });

        categoriesDisplay.getTagDownButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    /**
                     * Swap the sort value for the tag that was selected with the tag below it.
                     */
                    @Override
                    public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                        moveTagsUpAndDown(object, true);
                    }
                });
    }

    /**
     * Binds behaviour to the category list buttons
     */
    private void bindCategoryColumnButtons() {
        categoriesDisplay.getButtonColumn().setFieldUpdater(new FieldUpdater<RESTCategoryCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTCategoryCollectionItemV1 object, final String value) {
                boolean found = false;
                for (final RESTTagInCategoryCollectionItemV1 tag : object.getItem().getTags().getItems()) {
                    if (tag.getItem().getId().equals(tagProviderData.getDisplayedItem().getItem().getId())) {
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
                    final RESTTagInCategoryV1 newTag = new RESTTagInCategoryV1();
                    newTag.setId(tagProviderData.getDisplayedItem().getItem().getId());
                    newTag.setName(tagProviderData.getDisplayedItem().getItem().getName());
                    newTag.setRelationshipSort(0);

                    object.getItem().getTags().addNewItem(newTag);
                }

                /*
                 * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set the
                 * configured parameters to reflect the fact that the category contains tags that will modify the database. So
                 * here we check to see if any tags have been added or removed. If there are none (i.e. a tag was added and then
                 * removed again without persisting the change in the database, or there were just no changes made) we remove
                 * the tags collection from the configured parameters.
                 */
                if (object.getItem().getTags().returnDeletedAndAddedCollectionItems().size() != 0) {

                    /*
                     * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set the
                     * appropriate configured parameter value
                     */
                    object.getItem().explicitSetTags(object.getItem().getTags());
                } else {
                    object.getItem().getConfiguredParameters().remove(RESTBaseCategoryV1.TAGS_NAME);
                }

                /* refresh the category list */
                categoriesDisplay.getProvider().displayNewFixedList(categoryProviderData.getItems());

                /*
                 * refresh the list of tags in the category
                 */
                categoriesDisplay.setTagsProvider(generateCategoriesTagListProvider());
            }
        });
    }

    /**
     * Binds behaviour to the project list buttons
     */
    private void bindProjectColumnButtons() {
        projectsDisplay.getButtonColumn().setFieldUpdater(new FieldUpdater<RESTProjectCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTProjectCollectionItemV1 object, final String value) {
                boolean found = false;

                for (final RESTTagCollectionItemV1 tag : object.getItem().getTags().getItems()) {
                    if (tag.getItem().getId().equals(tagProviderData.getDisplayedItem().getItem().getId())) {
                        /* Project was added and then removed */
                        if (tag.getState() == RESTBaseCollectionItemV1.ADD_STATE) {
                            object.getItem().getTags().getItems().remove(tag);
                        }

                        /* Project existed, was removed and then was added again */
                        if (tag.getState() == RESTBaseCollectionItemV1.REMOVE_STATE) {
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
                    final RESTTagV1 newTag = tagProviderData.getDisplayedItem().getItem().clone(true);
                    object.getItem().getTags().addNewItem(newTag);
                }

                /*
                 * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set the
                 * configured parameters to reflect the fact that the category contains tags that will modify the database. So
                 * here we check to see if any tags have been added or removed. If there are none (i.e. a tag was added and then
                 * removed again without persisting the change in the database, or there were just no changes made) we remove
                 * the tags collection from the configured parameters.
                 */
                if (object.getItem().getTags().returnDeletedAndAddedCollectionItems().size() != 0) {

                    /*
                     * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set the
                     * appropriate configured parameter value
                     */
                    object.getItem().explicitSetTags(object.getItem().getTags());
                } else {
                    object.getItem().getConfiguredParameters().remove(RESTBaseCategoryV1.TAGS_NAME);
                }

                /* refresh the project list */
                projectsDisplay.getProvider().displayNewFixedList(projectProviderData.getItems());
            }
        });
    }

    /**
     * Bind behaviour to the buttons found in the tag views
     */
    private void bindTagViewButtons() {
        for (final TagViewInterface tagDisplay : new TagViewInterface[] { resultDisplay, projectsDisplay, categoriesDisplay }) {
            tagDisplay.getTagDetails().addClickHandler(tagDetailsClickHandler);
            tagDisplay.getTagProjects().addClickHandler(tagProjectsClickHandler);
            tagDisplay.getSave().addClickHandler(saveClickHandler);
            tagDisplay.getTagCategories().addClickHandler(tagCategoriesClickHandler);
        }
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    private void bindSearchButtons() {
        filteredResultsDisplay.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (checkForUnsavedChanges())
                    eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(getQuery(filteredResultsDisplay)));
            }
        });
    }

    

    

    

    

    

    

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("tagIds")) {
                    this.filteredResultsDisplay.getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("tagName")) {
                    this.filteredResultsDisplay.getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("tagDesc")) {
                    this.filteredResultsDisplay.getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

    /**
     * Called when the selected tag is changed, or the selected view is changed.
     */
    @Override
    protected void reInitialiseView() {
        /* save any changes as we move between views */
        if (lastDisplayedView == resultDisplay) {
            resultDisplay.getDriver().flush();
        }

        /* Show/Hide any localised loading dialogs */
        if (lastDisplayedView != null)
            lastDisplayedView.setViewShown(false);

        /* update the new view */
        if (displayedView != null) {
            displayedView.initialize(tagProviderData.getDisplayedItem().getItem(), false);
            displayedView.setViewShown(true);
        }

        /* refresh the project list */
        if (displayedView == projectsDisplay) {
            /* If we switch to this view before the projects have been downloaded, there is nothing to update */
            if (projectsDisplay.getProvider() != null && projectProviderData.getItems() != null) {
                projectsDisplay.getProvider().displayNewFixedList(projectProviderData.getItems());
            }
        }
        /* refresh the category list */
        else if (displayedView == categoriesDisplay) {
            /* If we switch to this view before the categories have been downloaded, there is nothing to update */
            if (categoriesDisplay.getProvider() != null && categoryProviderData.getItems() != null) {
                categoriesDisplay.getProvider().displayNewFixedList(categoryProviderData.getItems());
            }
        }

        /* update the display widgets if we have changed displays */
        if (lastDisplayedView != displayedView) {
            display.getViewPanel().setWidget(displayedView.getPanel());
            display.getViewActionButtonsPanel().setWidget(displayedView.getTopActionPanel());
        }

        /* Update the page name */
        final StringBuilder title = new StringBuilder(displayedView.getPageName());
        if (this.tagProviderData.getDisplayedItem() != null)
            title.append(": " + this.tagProviderData.getDisplayedItem().getItem().getName());
        display.getPageTitle().setText(title.toString());

        lastDisplayedView = displayedView;
    }

    

    /**
     * Called when a new tag is selected or the tag is saved. This refreshes the list of categories and projects.
     * 
     * @param removeCatgeoryTagListFromScreen true if the list of tags within a category is to be removed from the screen
     */
    private void resetCategoryAndProjectsLists(final boolean removeCatgeoryTagListFromScreen) {
        /*
         * Reset the category and projects data. This is to clear out any added tags. Maybe cache this info if reloading is too
         * slow.
         */
        categoryProviderData.reset();
        projectProviderData.reset();

        getProjects();
        getCategories();

        /* remove the category tags list */
        if (removeCatgeoryTagListFromScreen) {
            categoryProviderData.setSelectedItem(null);
            categoryProviderData.setDisplayedItem(null);
            categoriesDisplay.getSplit().remove(categoriesDisplay.getTagsResultsPanel());
        }
    }
}
