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
import org.jboss.pressgangccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgangccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgangccms.rest.v1.components.ComponentRESTBaseEntityV1;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.AsyncDataProvider;
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
    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getResultsPanel();

        SimpleLayoutPanel getViewPanel();

        SimpleLayoutPanel getViewActionButtonsPanel();

        SimpleLayoutPanel getResultsActionButtonsPanel();

        HanldedSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getViewLayoutPanel();
    }

    private final ClickHandler tagDetailsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            displayedView = resultDisplay;
            reInitialiseView();
        }

    };

    private final ClickHandler tagProjectsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            displayedView = projectsDisplay;
            reInitialiseView();
        }
    };

    private final ClickHandler tagCategoriesClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            displayedView = categoriesDisplay;
            reInitialiseView();
        }
    };

    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            /* Save any changes made to the tag entity itself */
            final RESTCalls.RESTCallback<RESTTagV1> callback = new RESTCalls.RESTCallback<RESTTagV1>() {
                @Override
                public void begin() {
                    startProcessing();
                }

                @Override
                public void generalException(final Exception ex) {
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    stopProcessing();
                }

                @Override
                public void success(final RESTTagV1 retValue) {
                    try {
                        tagProviderData.setDisplayedItem(retValue);

                        /* clear the categories and tags */
                        categoryProviderData = new ProviderUpdateData<RESTCategoryV1>();
                        projectProviderData = new ProviderUpdateData<RESTProjectV1>();

                        /* refresh the display */
                        reInitialiseView();
                    } finally {
                        stopProcessing();
                    }

                }

                @Override
                public void failed() {
                    Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    stopProcessing();
                }
            };

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

    @Inject
    private Display display;

    @Inject
    private TagFilteredResultsPresenter.Display filteredResultsDisplay;

    @Inject
    private TagPresenter.Display resultDisplay;

    @Inject
    private TagProjectsPresenter.Display projectsDisplay;

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

    /** used to keep a track of how many rest calls are active */
    int count = 0;

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());

        bind();
    }

    /**
     * Add behaviour to the UI elements exposed by the views
     */
    private void bind() {
        super.bind(display);

        filteredResultsDisplay.setProvider(generateListProvider());

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
                startProcessing();
            }

            @Override
            public void generalException(final Exception ex) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                stopProcessing();
            }

            @Override
            public void success(final RESTProjectCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    projectProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTProjectV1>() : retValue
                            .getItems());
                    projectsDisplay.setProvider(generateProjectListProvider());
                } finally {
                    stopProcessing();
                }
            }

            @Override
            public void failed() {
                stopProcessing();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

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
                startProcessing();
            }

            @Override
            public void generalException(final Exception ex) {
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                stopProcessing();
            }

            @Override
            public void success(final RESTCategoryCollectionV1 retValue) {
                try {
                    /* Zero results can be a null list */
                    categoryProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTCategoryV1>() : retValue
                            .getItems());
                    categoriesDisplay.setProvider(generateCategoriesListProvider());
                } finally {
                    stopProcessing();
                }
            }

            @Override
            public void failed() {
                stopProcessing();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };

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
                categoriesDisplay.getProvider().updateRowData(categoryProviderData.getStartRow(),
                        categoryProviderData.getItems());

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
                projectsDisplay.getProvider().updateRowData(projectProviderData.getStartRow(), projectProviderData.getItems());
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
                eventBus.fireEvent(new TagsFilteredResultsAndTagViewEvent(getQuery(filteredResultsDisplay)));
            }
        });
    }

    /**
     * @return A provider to be used for the tag display list
     */
    private AsyncDataProvider<RESTTagV1> generateCategoriesTagListProvider() {
        final AsyncDataProvider<RESTTagV1> provider = new AsyncDataProvider<RESTTagV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagV1> display) {
                categoryTagsProviderData.setStartRow(0);

                categoryTagsProviderData.setItems(new ArrayList<RESTTagV1>());

                /* Zero results can be a null list. Also selecting a new tag will reset categoryProviderData.  */
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

                updateRowData(categoryTagsProviderData.getStartRow(), categoryTagsProviderData.getItems());
                updateRowCount(categoryTagsProviderData.getItems().size(), true);
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
    private AsyncDataProvider<RESTCategoryV1> generateCategoriesListProvider() {
        final AsyncDataProvider<RESTCategoryV1> provider = new AsyncDataProvider<RESTCategoryV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryV1> display) {
                categoryProviderData.setStartRow(0);
                updateRowData(categoryProviderData.getStartRow(), categoryProviderData.getItems());
                updateRowCount(categoryProviderData.getItems().size(), true);
            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the project display list
     */
    private AsyncDataProvider<RESTProjectV1> generateProjectListProvider() {
        final AsyncDataProvider<RESTProjectV1> provider = new AsyncDataProvider<RESTProjectV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTProjectV1> display) {
                projectProviderData.setStartRow(0);
                updateRowData(projectProviderData.getStartRow(), projectProviderData.getItems());
                updateRowCount(projectProviderData.getItems().size(), true);
            }
        };
        return provider;
    }

    /**
     * @return A provider to be used for the tag display list
     */
    private AsyncDataProvider<RESTTagV1> generateListProvider() {
        final AsyncDataProvider<RESTTagV1> provider = new AsyncDataProvider<RESTTagV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagV1> display) {
                tagProviderData.setStartRow(display.getVisibleRange().getStart());
                final int length = display.getVisibleRange().getLength();
                final int end = tagProviderData.getStartRow() + length;

                final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
                    @Override
                    public void begin() {
                        startProcessing();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        stopProcessing();
                    }

                    @Override
                    public void success(final RESTTagCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            tagProviderData.setItems(retValue.getItems() == null ? new ArrayList<RESTTagV1>() : retValue
                                    .getItems());
                            updateRowData(tagProviderData.getStartRow(), tagProviderData.getItems());
                            updateRowCount(retValue.getSize(), true);
                        } finally {
                            stopProcessing();
                        }
                    }

                    @Override
                    public void failed() {
                        stopProcessing();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                RESTCalls.getTagsFromQuery(callback, queryString, tagProviderData.getStartRow(), end);
            }
        };
        return provider;
    }

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

                    /*
                     * selectedSearchImage will be null until an image is selected for the first time
                     */
                    final boolean needToAddImageView = tagProviderData.getSelectedItem() == null;

                    tagProviderData.setSelectedItem(event.getValue());
                    tagProviderData.setDisplayedItem(event.getValue());

                    /*
                     * If this is the first image selected, display the image view
                     */
                    if (needToAddImageView) {
                        displayedView = resultDisplay;
                    }

                    /*
                     * Reset the category and projects data. This is to clear out any added tags. Maybe cache this info if
                     * reloading is too slow.
                     */
                    categoryProviderData = new ProviderUpdateData<RESTCategoryV1>();
                    projectProviderData = new ProviderUpdateData<RESTProjectV1>();

                    /* remove the category tags list */
                    categoriesDisplay.getSplit().remove(categoriesDisplay.getTagsResultsPanel());

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

    protected void stopProcessing() {
        --count;
        if (count == 0) {
            display.setSpinnerVisible(false);
        }
    }

    protected void startProcessing() {
        ++count;
        display.setSpinnerVisible(true);
    }

    @Override
    protected void reInitialiseView() {
        /* save any changes as we move between views */
        if (lastDisplayedView == resultDisplay) {
            resultDisplay.getDriver().flush();
        }

        /* update the new view */
        if (displayedView != null) {
            displayedView.initialize(tagProviderData.getDisplayedItem(), false);
        }

        /*
         * Update the projects and categories list to show the buttons as they apply to a new tag
         */
        if (displayedView == projectsDisplay) {
            if (projectProviderData.getItems() == null) {
                getProjects();
            }
        } else if (displayedView == categoriesDisplay) {
            if (categoryProviderData.getItems() == null) {
                getCategories();
            }
        }

        /* update the display widgets if we have changed displays */
        if (lastDisplayedView != displayedView) {
            display.getViewPanel().setWidget(displayedView.getPanel());
            display.getViewActionButtonsPanel().setWidget(displayedView.getTopActionPanel());
        }
        
        /* Update the page name */
        final StringBuilder title = new StringBuilder(displayedView.getPageName());
        if (this.tagProviderData.getSelectedItem() != null)
            title.append(": " + this.tagProviderData.getSelectedItem().getName());
        display.getPageTitle().setText(title.toString());

        lastDisplayedView = displayedView;
    }

    private boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (tagProviderData.getSelectedItem() != null) {
            resultDisplay.getDriver().flush();

            /*
             * See if any items have been added or removed from the project and category lists
             */
            final boolean unsavedCategoryChanges = categoryProviderData.getItems() != null
                    && ComponentRESTBaseEntityV1.returnDirtyState(categoryProviderData.getItems());
            final boolean unsavedProjectChanges = projectProviderData.getItems() != null
                    && ComponentRESTBaseEntityV1.returnDirtyState(projectProviderData.getItems());

            final boolean unsavedTagChanges = !tagProviderData.getSelectedItem().getDescription()
                    .equals(tagProviderData.getDisplayedItem().getDescription())
                    || !tagProviderData.getSelectedItem().getName().equals(tagProviderData.getDisplayedItem().getName());

            if (unsavedCategoryChanges || unsavedProjectChanges || unsavedTagChanges) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }

        return true;
    }
}
