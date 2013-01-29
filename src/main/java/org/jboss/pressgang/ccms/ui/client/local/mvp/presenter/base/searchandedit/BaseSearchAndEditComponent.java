package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

/**
 * The base class for all components adding logic to search and edit views. This view has a split screen with a filtered results
 * list on the left, and the entity details on the right, with at least one of those views being a "properties view", which
 * shows the fields associated with an entity (especially the ID).
 * 
 * @author Matthew Casperson
 *
 * @param <R> The results view type
 * 
 * @param <T> The entity type
 * @param <U> The entity collection type of T
 * @param <V> The entity collection item type of T
 * 
 * @param <W> The common type to all views
 * @param <X> The type of the entity properties view
 * 
 * @param <Y> The type of the Editor that is displayed by this component
 */
abstract public class BaseSearchAndEditComponent<R extends BaseFilteredResultsViewInterface<T, U, V>, T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends BaseTemplateViewInterface, X extends BaseEditorViewInterface<T, Y> & BaseTemplateViewInterface, Y extends Editor<T>>
        extends ComponentBase {

    /** A logger */
    private static final Logger logger = Logger.getLogger(BaseSearchAndEditComponent.class.getName());

    /** The last displayed view */
    protected W lastDisplayedView;
    /** The default view to display when an entity is selected for the first time */
    private W firstDisplayedView;
    /** The view that displays the entity properties (namely the id) */
    private X entityPropertiesView;
    /** The view that displays the list of results */
    private BaseFilteredResultsViewInterface filteredResultsDisplay;
    /** The component that adds logic to the filtered results view */
    private BaseFilteredResultsComponentInterface<T, U, V> filteredResultsComponent;
    /** The top level display */
    private BaseSearchAndEditViewInterface display;

    /**
     * @param topicId The topic ID used for this views help link
     * @param pageId The id of the page, used when submitting feedback
     * @param mainSplitSizePreferenceKey The storage key that holds the position of the split panel
     * @param firstDisplayedView The view to display first when an entity is first selected
     * @param entityPropertiesView The view that displays the entity fields
     * @param filteredResultsDisplay The view that displays the search results
     * @param filteredResultsComponent The component that backs the filtered results view
     * @param display The view that this component adds logic to
     * @param waitDisplay The view that displays the wait dialog
     */
    public void bind(final int topicId, final String pageId, final String mainSplitSizePreferenceKey,
            final W firstDisplayedView, final X entityPropertiesView, final R filteredResultsDisplay,
            final BaseFilteredResultsComponentInterface<T, U, V> filteredResultsComponent, final BaseSearchAndEditViewInterface display,
            final BaseTemplateViewInterface waitDisplay, final GetNewEntityCallback<T> getNewEntityCallback) {

        super.bind(topicId, pageId, display);

        this.entityPropertiesView = entityPropertiesView;
        this.filteredResultsDisplay = filteredResultsDisplay;
        this.filteredResultsComponent = filteredResultsComponent;
        this.firstDisplayedView = firstDisplayedView;
        this.display = display;

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        display.displaySearchResultsView(filteredResultsDisplay);

        loadMainSplitResize(mainSplitSizePreferenceKey);
        bindMainSplitResize(mainSplitSizePreferenceKey);
        bindResultsListRowClicks(getNewEntityCallback);
        bindActionButtons();
        bindFilteredResultsButtons();
    }

    /**
     * Called once an entity has been saved to refresh the various lists that may have been modified by the edited or created
     * entity.
     * 
     * 
     * @param wasNewEntity true if the entity that was saved was a new entity, and false otherwise
     */
    protected void updateDisplayAfterSave(final boolean wasNewEntity) {
        /* refresh the list of tags from the existing list that was modified */
        if (!wasNewEntity) {
            filteredResultsDisplay.getProvider().displayAsynchronousList(filteredResultsComponent.getProviderData().getItems(),
                    filteredResultsComponent.getProviderData().getSize(),
                    filteredResultsComponent.getProviderData().getStartRow());
        }
        /* If we just created a new entity, refresh the list of entities from the database */
        else {
            filteredResultsComponent.process(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, "", filteredResultsComponent.getQuery());

            /*
             * reInitialiseView will flush the ui, which will flush the null ID back to the displayed object. To prevent that we
             * need to call edit on the newly saved entity
             */
            entityPropertiesView.getDriver().edit(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());

        }

        /* refresh the display */
        initializeViews();

        /* Load the data that is loaded when a new entity is selected */
        loadAdditionalDisplayedItemData();
    }

    /**
     * Given an entity, load a fresh copy from the database and display it on the screen
     * @param getNewEntityCallback The callback used to load a fresh entity
     * @param selectedItem The item that was selected from a collection of entities
     */
    protected void loadNewEntity(final GetNewEntityCallback<T> getNewEntityCallback, final V selectedItem)
    {
        /*
         * When an entity is selected we will get afresh copy from the database. This is to deal with a
         * situation where the search screen has been left for a while and the entity has been edited by someone
         * else in the mean time.
         */
        getNewEntityCallback.getNewEntity(selectedItem.getItem().getId(), new DisplayNewEntityCallback<T>() {

            @Override
            public void displayNewEntity(final T entity) {

            try {
                logger.log(
                        Level.INFO,
                        "EXIT BaseSearchAndEditComponent.loadNewEntity() DisplayNewEntityCallback.displayNewEntity(final T entity)");

                entity.cloneInto(selectedItem.getItem(), true);

                                /*
                                 * The selected item will be the category from the list. This is the unedited, unexpanded
                                 * copy of the category
                                 */
                filteredResultsComponent.getProviderData().setSelectedItem(selectedItem);

                                /*
                                 * All editing is done in a clone of the selected category. Any expanded collections will be
                                 * copied into this category
                                 */

                filteredResultsComponent.getProviderData().setDisplayedItem(selectedItem.clone(true));

                                /* Refresh the list */
                updateDisplayAfterSave(false);

                                /* Refresh the view, or display the properties view if none is shown */
                switchView(lastDisplayedView == null ? firstDisplayedView : lastDisplayedView);

                                /* Initialize the views */
                initializeViews();

                                /* Allow overriding classes to display any additional details */
                loadAdditionalDisplayedItemData();
            } finally {
                logger.log(
                        Level.INFO,
                        "EXIT BaseSearchAndEditComponent.loadNewEntity() DisplayNewEntityCallback.displayNewEntity(final T entity)");
            }

            }
        });
    }


    /** Binds logic to the search results list row click event */
    protected void bindResultsListRowClicks(final GetNewEntityCallback<T> getNewEntityCallback) {
        filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<V>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<V> event) {

                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    try {
                        logger.log(Level.INFO,
                                "ENTER BaseSearchAndEditComponent.bindResultsListRowClicks() Anonymous.onCellPreview(final CellPreviewEvent<V> event)");

                        if (!isOKToProceed()) {
                            return;
                        }

                        final V selectedItem = event.getValue();

                        loadNewEntity(getNewEntityCallback, selectedItem);

                    } finally {
                        logger.log(Level.INFO,
                                "EXIT BaseSearchAndEditComponent.bindResultsListRowClicks() Anonymous.onCellPreview(final CellPreviewEvent<V> event)");
                    }
                }
            }
        });

    }

    /**
     * Called once a new entity has been selected from the filtered results view
     */
    abstract protected void loadAdditionalDisplayedItemData();

    /** Binds logic to the action buttons */
    abstract protected void bindActionButtons();

    /** Bind logic to the filtered results buttons */
    abstract protected void bindFilteredResultsButtons();

    /** Saves the position of the main split */
    private void bindMainSplitResize(final String saveKey) {
        try {
            logger.log(Level.INFO, "ENTER BaseSearchAndEditComponent.bindMainSplitResize()");

            display.getSplitPanel().addResizeHandler(new ResizeHandler() {

                @Override
                public void onResize(final ResizeEvent event) {
                    Preferences.INSTANCE.saveSetting(saveKey,
                            display.getSplitPanel().getSplitPosition(display.getResultsViewLayoutPanel()) + "");
                }
            });
        } finally {
            logger.log(Level.INFO, "EXIT BaseSearchAndEditComponent.bindMainSplitResize()");
        }
    }

    /**
     * Restores the size of the main split screen
     * 
     * @param preferencesKey The key against which the previous size was saved
     */
    private void loadMainSplitResize(final String preferencesKey) {
        try {
            logger.log(Level.INFO, "ENTER BaseSearchAndEditComponent.loadMainSplitResize()");

            display.getSplitPanel().setSplitPosition(display.getResultsViewLayoutPanel(),
                    Preferences.INSTANCE.getInt(preferencesKey, Constants.SPLIT_PANEL_SIZE), false);
        } finally {
            logger.log(Level.INFO, "EXIT BaseSearchAndEditComponent.loadMainSplitResize()");
        }
    }

    /**
     * Called when a new entity is selected
     */
    protected void initializeViews() {
        initializeViews(null);
    }

    /**
     * Called when a new entity is selected
     * 
     * @param filter null if all views are to be initialized, or includes a list of views to be initialized
     */
    abstract protected void initializeViews(final List<W> filter);

    /**
     * Used by the initializeViews method
     * 
     * @param filter The filter containing the list of views, or null if all views are to be initialized
     * @param view The view to test against the filter
     * @return true if the filter is null or if it contains the view, and false otherwise
     */
    protected boolean viewIsInFilter(final List<W> filter, final W view) {
        if (filter == null)
            return true;

        return filter.contains(view);
    }

    /** Called when displaying changes to a entity or when changing views */
    protected void switchView(final W displayedView) {

        try {
            logger.log(Level.INFO, "ENTER BaseSearchAndEditComponent.switchView(final W displayedView)");

            /* Show/Hide any localised loading dialogs */
            if (lastDisplayedView != null) {
                logger.log(Level.INFO, "\tSetting old view to not shown");
                lastDisplayedView.setViewShown(false);
            }

            /* update the new view */
            if (displayedView != null) {
                logger.log(Level.INFO, "\tSetting new view to shown");
                displayedView.setViewShown(true);
            }

            /* update the display widgets if we have changed displays */
            if (lastDisplayedView != displayedView) {
                logger.log(Level.INFO, "\tAdding new display to canvas");
                display.displayChildView(displayedView);
            }

            /* copy any changes from the property view into the underlying object */
            if (lastDisplayedView == this.entityPropertiesView) {
                logger.log(Level.INFO, "\tFlushing data from properties view");
                this.entityPropertiesView.getDriver().flush();
            }
        } finally {
            logger.log(Level.INFO, "EXIT BaseSearchAndEditComponent.switchView(final W displayedView)");
        }
    }
}