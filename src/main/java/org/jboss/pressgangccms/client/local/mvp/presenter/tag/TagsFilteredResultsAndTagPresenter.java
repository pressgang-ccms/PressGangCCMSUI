package org.jboss.pressgangccms.client.local.mvp.presenter.tag;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.events.TagsFilteredResultsAndTagViewEvent;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgangccms.client.local.mvp.view.tag.TagsFilteredResultsAndTagView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.restcalls.RESTCalls;
import org.jboss.pressgangccms.client.local.ui.ProviderUpdateData;
import org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgangccms.client.local.utilities.GWTUtilities;
import org.jboss.pressgangccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.components.ComponentRESTBaseEntityV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
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
public class TagsFilteredResultsAndTagPresenter extends TagPresenterBase {

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
        HanldedSplitLayoutPanel getSplitPanel();
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
            /* Save any changes made to the tag entity itself */
            final RESTCalls.RESTCallback<RESTTagV1> callback = new RESTCalls.RESTCallback<RESTTagV1>() {
                @Override
                public void begin() {
                    display.addWaitOperation();
                }

                @Override
                public void generalException(final Exception ex) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    display.removeWaitOperation();
                }

                @Override
                public void success(final RESTTagV1 retValue) {
                    try {
                        /* we are noew viewing the object returned by the save */
                        tagProviderData.setDisplayedItem(retValue);
                        
                        /* Update the list of tags with any saved changes */
                        retValue.cloneInto(tagProviderData.getSelectedItem(), true);
                        
                        /* refresh the list of tags */
                        filteredResultsDisplay.getProvider().displayNewFixedList(tagProviderData.getItems());

                        /* reload the list of categoires and projects */
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
                }
            };

            /* Sync the UI to the underlying object */
            resultDisplay.getDriver().flush();

            /* Sync changes from the tag view */
            final RESTTagV1 updateTag = new RESTTagV1();
            updateTag.setId(tagProviderData.getDisplayedItem().getId());
            updateTag.explicitSetDescription(tagProviderData.getDisplayedItem().getDescription());
            updateTag.explicitSetName(tagProviderData.getDisplayedItem().getName());

            /*
             * Sync changes from the categories. categoryProviderData.getItems() contains a collection of all the categories,
             * and their tags collections contain any added or removed tag relationships. Here we copy those modified
             * relationships into the updateTag, so the changes are all done in one transaction.
             */
            if (categoryProviderData.getItems() != null) {
                updateTag.explicitSetCategories(new RESTCategoryCollectionV1());
                for (final RESTCategoryV1 category : categoryProviderData.getItems()) {
                    for (final RESTTagV1 tag : category.getTags().getItems()) {
                        /*
                         * It should only be possible to add the currently displayed tag to the categories
                         */
                        if (tag.getId().equals(updateTag.getId())) {

                            final RESTCategoryV1 addedCategory = new RESTCategoryV1();
                            addedCategory.setId(category.getId());

                            if (tag.getAddItem()) {
                                addedCategory.setAddItem(true);
                            } else if (tag.getRemoveItem()) {
                                addedCategory.setRemoveItem(true);
                            }

                            updateTag.getCategories().addItem(addedCategory);
                        }
                    }
                }
            }

            /*
             * Sync changes from the projects.
             */
            if (projectProviderData.getItems() != null) {
                updateTag.explicitSetProjects(new RESTProjectCollectionV1());
                for (final RESTProjectV1 project : projectProviderData.getItems()) {
                    for (final RESTTagV1 tag : project.getTags().getItems()) {
                        if (tag.getId().equals(updateTag.getId())) {
                            final RESTProjectV1 addedProject = new RESTProjectV1();
                            addedProject.setId(project.getId());

                            if (tag.getAddItem()) {
                                addedProject.setAddItem(true);
                            } else if (tag.getRemoveItem()) {
                                addedProject.setRemoveItem(true);
                            }

                            updateTag.getProjects().addItem(addedProject);
                        }
                    }
                }
            }

            RESTCalls.saveTag(callback, updateTag);
        }
    };

    /**
     * An Errai injected instance of a class that implements Display. This is the view
     * that holds all other views
     */
    @Inject
    private Display display;

    /**
     * An Errai injected instance of a class that implements TagFilteredResultsPresenter.Display. This
     * is the view that displays the list of tags.
     */
    @Inject
    private TagFilteredResultsPresenter.Display filteredResultsDisplay;

    /**
     * An Errai injected instance of a class that implements TagPresenter.Display. This is the view
     * that displays the fields of the tag (name, description etc)
     */
    @Inject
    private TagPresenter.Display resultDisplay;

    /**
     * An Errai injected instance of a class that implements TagProjectsPresenter.Display. This is the view
     * that lists all the projects that the tag can be added to or removed from.
     */
    @Inject
    private TagProjectsPresenter.Display projectsDisplay;

    /**
     * An Errai injected instance of a class that implements TagCategoriesPresenter.Display. This is the view
     * that lists all the categories that the tag can be added to or removed from.
     */
    @Inject
    private TagCategoriesPresenter.Display categoriesDisplay;

    /** The tag query string extracted from the history token */
    private String queryString;
    /** Holds the data required to populate and refresh the tags list */
    private ProviderUpdateData<RESTTagV1> tagProviderData = new ProviderUpdateData<RESTTagV1>();
    /** Holds the data required to populate and refresh the projects list */
    private ProviderUpdateData<RESTProjectV1> projectProviderData = new ProviderUpdateData<RESTProjectV1>();
    /** Holds the data required to populate and refresh the categories list */
    private ProviderUpdateData<RESTCategoryV1> categoryProviderData = new ProviderUpdateData<RESTCategoryV1>();
    /** Holds the data required to populate and refresh the category tags list */
    private ProviderUpdateData<RESTTagV1> categoryTagsProviderData = new ProviderUpdateData<RESTTagV1>();

    /** The currently displayed view */
    private TagViewInterface displayedView;

    /** The last displayed view */
    private TagViewInterface lastDisplayedView;

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        bind();
    }

    /**
     * Add behaviour to the UI elements exposed by the views
     */
    private void bind() {
        super.bind(display, this);

        filteredResultsDisplay.setProvider(generateListProvider());
        projectsDisplay.setProvider(generateProjectListProvider());
        categoriesDisplay.setProvider(generateCategoriesListProvider());

        bindTagListRowClicks();

        bindCategoryListRowClicks();

        bindSearchButtons();

        bindTagViewButtons();

        bindProjectColumnButtons();

        bindCategoryColumnButtons();
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
            public void generalException(final Exception ex) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                projectsDisplay.removeWaitOperation();
            }

            @Override
            public void success(final RESTProjectCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    projectProviderData.setStartRow(0);
                    projectProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTProjectV1>() : retValue
                            .getItems());

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
            public void generalException(final Exception ex) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                categoriesDisplay.removeWaitOperation();
            }

            @Override
            public void success(final RESTCategoryCollectionV1 retValue) {
                try {
                    categoryProviderData.setStartRow(0);
                    /* Zero results can be a null list */
                    categoryProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTCategoryV1>() : retValue
                            .getItems());

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

    /**
     * Binds behaviour to the category list buttons
     */
    private void bindCategoryColumnButtons() {
        categoriesDisplay.getButtonColumn().setFieldUpdater(new FieldUpdater<RESTCategoryV1, String>() {
            @Override
            public void update(final int index, final RESTCategoryV1 object, final String value) {
                boolean found = false;
                for (final RESTTagV1 tag : object.getTags().getItems()) {
                    if (tag.getId().equals(tagProviderData.getDisplayedItem().getId())) {
                        /* Tag was added and then removed */
                        if (tag.getAddItem()) {
                            object.getTags().getItems().remove(tag);
                        }

                        /* Tag existed, was removed and then was added again */
                        if (tag.getRemoveItem()) {
                            tag.setRemoveItem(false);
                        }
                        /* Tag existed and was removed */
                        else {
                            tag.setRemoveItem(true);
                        }

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    final RESTTagV1 newTag = tagProviderData.getDisplayedItem().clone(true);
                    newTag.setAddItem(true);
                    object.getTags().addItem(newTag);
                }

                /*
                 * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set the
                 * configured parameters to reflect the fact that the category contains tags that will modify the database. So
                 * here we check to see if any tags have been added or removed. If there are none (i.e. a tag was added and then
                 * removed again without persisting the change in the database, or there were just no changes made) we remove
                 * the tags collection from the configured parameters.
                 */
                boolean collectionContainsDirtyTags = false;
                for (final RESTTagV1 tag : object.getTags().getItems()) {
                    if (tag.getAddItem() || tag.getRemoveItem()) {
                        /*
                         * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set the
                         * appropriate configured parameter value
                         */
                        object.explicitSetTags(object.getTags());
                        collectionContainsDirtyTags = true;
                        break;
                    }
                }

                if (!collectionContainsDirtyTags) {
                    object.getConfiguredParameters().remove(RESTCategoryV1.TAGS_NAME);
                }

                /* refresh the category list */
                categoriesDisplay.getProvider().displayNewFixedList(categoryProviderData.getItems());

                /*
                 * reset the provider, which will refresh the list of tags
                 */
                categoriesDisplay.setTagsProvider(generateCategoriesTagListProvider());
            }
        });
    }

    /**
     * Binds behaviour to the project list buttons
     */
    private void bindProjectColumnButtons() {
        projectsDisplay.getButtonColumn().setFieldUpdater(new FieldUpdater<RESTProjectV1, String>() {
            @Override
            public void update(final int index, final RESTProjectV1 object, final String value) {
                boolean found = false;
                for (final RESTTagV1 tag : object.getTags().getItems()) {
                    if (tag.getId().equals(tagProviderData.getDisplayedItem().getId())) {
                        /* Project was added and then removed */
                        if (tag.getAddItem()) {
                            object.getTags().getItems().remove(tag);
                        }

                        /* Project existed, was removed and then was added again */
                        if (tag.getRemoveItem()) {
                            tag.setRemoveItem(false);
                        }
                        /* Project existed and was removed */
                        else {
                            tag.setRemoveItem(true);
                        }

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    final RESTTagV1 newTag = tagProviderData.getDisplayedItem().clone(true);
                    newTag.setAddItem(true);
                    object.getTags().addItem(newTag);
                    /*
                     * Need to mark the tags collection as dirty. The explicitSetTags provides a convenient way to set the
                     * appropriate configured paramater value
                     */
                    object.explicitSetTags(object.getTags());
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

    /**
     * @return A provider to be used for the tag display list
     */
    private EnhancedAsyncDataProvider<RESTTagV1> generateCategoriesTagListProvider() {
        final EnhancedAsyncDataProvider<RESTTagV1> provider = new EnhancedAsyncDataProvider<RESTTagV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagV1> display) {
                categoryTagsProviderData.setStartRow(display.getVisibleRange().getStart());
                categoryTagsProviderData.setItems(new ArrayList<RESTTagV1>());                                          

                /* Zero results can be a null list. Also selecting a new tag will reset categoryProviderData. */
                if (categoryProviderData.getDisplayedItem() != null
                        && categoryProviderData.getDisplayedItem().getTags() != null
                        && categoryProviderData.getDisplayedItem().getTags().getItems() != null) {
                    /* Don't display removed tags */
                    for (final RESTTagV1 tagInCategory : categoryProviderData.getDisplayedItem().getTags().getItems()) {
                        if (!tagInCategory.getRemoveItem()) {
                            categoryTagsProviderData.getItems().add(tagInCategory);
                        }
                    }
                }
                
                displayNewFixedList(categoryTagsProviderData.getItems());
            }
        };
        return provider;
    }

    /**
     * This provider pages over a collection of categories that was returned when the page was built. This is because changes to
     * the tag category relationships are done to the categories, not to the tag. This means we need to keep a list of the
     * categories instead of losing them when the table is paged through.
     * 
     * @return A provider to be used for the category display list.
     */
    private EnhancedAsyncDataProvider<RESTCategoryV1> generateCategoriesListProvider() {
        final EnhancedAsyncDataProvider<RESTCategoryV1> provider = new EnhancedAsyncDataProvider<RESTCategoryV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryV1> display) {

                categoryProviderData.setStartRow(display.getVisibleRange().getStart());
                
                if (categoryProviderData.getItems() != null)
                    displayNewFixedList(categoryProviderData.getItems());
                else
                    resetProvider();

            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the project display list
     */
    private EnhancedAsyncDataProvider<RESTProjectV1> generateProjectListProvider() {
        
        final EnhancedAsyncDataProvider<RESTProjectV1> provider = new EnhancedAsyncDataProvider<RESTProjectV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTProjectV1> display) {

                projectProviderData.setStartRow(display.getVisibleRange().getStart());
                
                if (projectProviderData.getItems() != null)
                    displayNewFixedList(projectProviderData.getItems());
                else
                    resetProvider();

            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the tag display list
     */
    private EnhancedAsyncDataProvider<RESTTagV1> generateListProvider() {
        final EnhancedAsyncDataProvider<RESTTagV1> provider = new EnhancedAsyncDataProvider<RESTTagV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagV1> display) {

                final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
                    @Override
                    public void begin() {
                        filteredResultsDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        filteredResultsDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTTagCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            tagProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTTagV1>() : retValue
                                    .getItems());
                            displayAsynchronousList(tagProviderData.getItems(), retValue.getSize(), tagProviderData.getStartRow());
                        } finally {
                            filteredResultsDisplay.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        filteredResultsDisplay.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };
                
                tagProviderData.setStartRow(display.getVisibleRange().getStart());
                final int length = display.getVisibleRange().getLength();
                final int end = tagProviderData.getStartRow() + length;

                RESTCalls.getTagsFromQuery(callback, queryString, tagProviderData.getStartRow(), end);
            }
        };
        return provider;
    }

    /**
     * Bind behaviour to the buttons found in the celltable listing the categories
     */
    private void bindCategoryListRowClicks() {
        this.categoriesDisplay.getResults().addCellPreviewHandler(new Handler<RESTCategoryV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTCategoryV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * categoryProviderData.getSelectedItem() will be null until a category is selected for the first time
                     */
                    final boolean needToAddImageView = categoryProviderData.getSelectedItem() == null;

                    /*
                     * Normally a list is populated with an un-expanded collection of entities. However, in this case we have
                     * expanded the categories to include all the tags.
                     * 
                     * Because the categories that are displayed in the category list have all the expanded topics, we don't
                     * need to get an expanded category in response to a category being selected. This means the displayed and
                     * selected items are the same.
                     */
                    categoryProviderData.setSelectedItem(event.getValue());
                    categoryProviderData.setDisplayedItem(event.getValue());

                    /*
                     * If this is the first category selected, display the tags list
                     */
                    if (needToAddImageView) {
                        categoriesDisplay.getSplit().add(categoriesDisplay.getTagsResultsPanel());
                    }

                    /*
                     * reset the provider, which will refresh the list of tags
                     */
                    categoriesDisplay.setTagsProvider(generateCategoriesTagListProvider());
                }
            }
        });
    }

    /**
     * Bind the button click events for the topic editor screens
     */
    private void bindTagListRowClicks() {
        filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTTagV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTTagV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    if (!checkForUnsavedChanges()) {
                        return;
                    }

                    /* The selected item will be the tag from the list. This is the unedited, unexpanded copy of the tag */
                    tagProviderData.setSelectedItem(event.getValue());
                    /* All editing is done in a clone of the selected tag. Any expanded collections will be copied into this tag */
                    tagProviderData.setDisplayedItem(event.getValue().clone(true));

                    /*
                     * If this is the first image selected, display the image view
                     */
                    if (displayedView == null) {
                        displayedView = resultDisplay;
                    }

                    resetCategoryAndProjectsLists(true);

                    reInitialiseView();
                }
            }
        });
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = historyToken.replace(TagsFilteredResultsAndTagView.HISTORY_TOKEN + ";", "");
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
            displayedView.initialize(tagProviderData.getDisplayedItem(), false);
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
            title.append(": " + this.tagProviderData.getDisplayedItem().getName());
        display.getPageTitle().setText(title.toString());

        lastDisplayedView = displayedView;
    }

    /**
     * Compare the displayed tag (the one that is edited) with the selected tag (the one that exists in the collection used to
     * build the tag list). If there are unsaved changes, prompt the user.
     * 
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (tagProviderData.getDisplayedItem() != null) {
            resultDisplay.getDriver().flush();

            /*
             * See if any items have been added or removed from the project and category lists
             */
            final boolean unsavedCategoryChanges = categoryProviderData.getItems() != null
                    && ComponentRESTBaseEntityV1.returnDirtyState(categoryProviderData.getItems());
            final boolean unsavedProjectChanges = projectProviderData.getItems() != null
                    && ComponentRESTBaseEntityV1.returnDirtyState(projectProviderData.getItems());

            /* See if any of the fields were changed */
            final boolean unsavedDescriptionChanges = !GWTUtilities.compareStrings(tagProviderData.getSelectedItem()
                    .getDescription(), tagProviderData.getDisplayedItem().getDescription());
            final boolean unsavedNameChanges = !GWTUtilities.compareStrings(tagProviderData.getSelectedItem().getName(),
                    tagProviderData.getDisplayedItem().getName());

            if (unsavedCategoryChanges || unsavedProjectChanges || unsavedDescriptionChanges || unsavedNameChanges) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }

        return true;
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
