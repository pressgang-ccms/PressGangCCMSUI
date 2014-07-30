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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @param <T> The entity type
 * @param <U> The entity collection type of T
 * @param <V> The entity collection item type of T
 * @param <Y> The type of the Editor that is displayed by this component
 * @author Matthew Casperson
 * @see BaseSearchAndEditPresenterInterface
 */
abstract public class BaseSearchAndEditPresenter<
        T extends RESTBaseEntityV1<T, U, V>,
        U extends RESTBaseEntityCollectionV1<T, U, V>,
        V extends RESTBaseEntityCollectionItemV1<T, U, V>,
        Y extends Editor<T>>
        extends BaseTemplatePresenter implements BaseSearchAndEditPresenterInterface {

    /**
     * A logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseSearchAndEditPresenter.class.getName());
    /**
     * The last displayed view
     */
    protected BaseTemplateViewInterface lastDisplayedView;
    /**
     * The default view to display when an entity is selected for the first time
     */
    private BaseTemplateViewInterface firstDisplayedView;
    /**
     * The view that displays the entity properties (namely the id)
     */
    private BasePopulatedEditorViewInterface<T, T, Y> entityPropertiesView;
    /**
     * The view that displays the list of results
     */
    private BaseFilteredResultsViewInterface<V> filteredResultsDisplay;
    /**
     * The component that adds logic to the filtered results view
     */
    private BaseFilteredResultsPresenterInterface<V> filteredResultsComponent;
    /**
     * The top level display
     */
    private BaseSearchAndEditViewInterface display;


    /**
     * Unsupported method. Call bindSearchAndEdit() instead.
     */
    public void bindExtended() {
        throw new UnsupportedOperationException("bindExtended() is not supported. Use bindSearchAndEdit() instead.");
    }

    /**
     * @param mainSplitSizePreferenceKey The storage key that holds the position of the split panel
     * @param firstDisplayedView         The view to display first when an entity is first selected
     * @param entityPropertiesView       The view that displays the entity fields
     * @param filteredResultsDisplay     The view that displays the search results
     * @param filteredResultsComponent   The component that backs the filtered results view
     * @param display                    The view that this component adds logic to
     * @param waitDisplay                The view that displays the wait dialog
     */
    protected void bindSearchAndEdit(
            @NotNull final String mainSplitSizePreferenceKey,
            @NotNull final BaseTemplateViewInterface firstDisplayedView,
            @NotNull final BasePopulatedEditorViewInterface<T, T, Y> entityPropertiesView,
            @NotNull final BaseFilteredResultsViewInterface<V> filteredResultsDisplay,
            @NotNull final BaseFilteredResultsPresenterInterface<V> filteredResultsComponent,
            @NotNull final BaseSearchAndEditViewInterface display,
            @NotNull final BaseTemplateViewInterface waitDisplay,
            @NotNull final GetNewEntityCallback<T> getNewEntityCallback) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.bindSearchAndEdit()");

            this.entityPropertiesView = entityPropertiesView;
            this.filteredResultsDisplay = filteredResultsDisplay;
            this.filteredResultsComponent = filteredResultsComponent;
            this.firstDisplayedView = firstDisplayedView;
            this.display = display;

            filteredResultsDisplay.setViewShown(true);
            display.setViewShown(true);

            display.displaySearchResultsView(filteredResultsDisplay);

            bindResultsListRowClicks(getNewEntityCallback);
            bindActionButtons();
            bindFilteredResultsButtons();

            loadMainSplitResize(mainSplitSizePreferenceKey);
            bindMainSplitResize(mainSplitSizePreferenceKey);
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.bindSearchAndEdit()");
        }
    }

    /**
     * Called once an entity has been saved to refresh the various lists that may have been modified by the edited or created
     * entity.
     * <p/>
     * This is also called when a new entity is loaded. This has the effect of updating the filtered results
     * lists with any new information.
     *
     * @param wasNewEntity true if the entity that was saved was a new entity, and false otherwise
     * @param refreshList  true if some changes have been made to the filtered list that should be redisplayed, false otherwise
     */
    private final void updateDisplayWithNewEntityData(final boolean wasNewEntity, final boolean refreshList) {
        try {

            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.updateDisplayWithNewEntityData()");

            if (refreshList) {
                refreshFilteredResults(wasNewEntity);
            }

            /* refresh the display */
            initializeViews();

            /* Load the data that is loaded when a new entity is selected */
            loadAdditionalDisplayedItemData();

            /* Refresh the view, or display the properties view if none is shown */
            switchView(lastDisplayedView == null ? firstDisplayedView : lastDisplayedView);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.updateDisplayWithNewEntityData()");
        }
    }

    /**
     * When a new entity is selected or saved, this method will update the views and the filtered results list.
     */
    public void updateDisplayWithNewEntityData(final boolean wasNewEntity) {
        updateDisplayWithNewEntityData(wasNewEntity, true);
    }

    /**
     * When a new entity is created, this method will update the views without updating the filtered results list.
     */
    protected void updateViewsAfterNewEntityLoaded() {
        /*  wasNewEntity has no meaning because refreshList is false */
        updateDisplayWithNewEntityData(false, false);
    }

    /**
     * Update the list of results
     *
     * @param wasNewEntity true if the update happened after a entity was created, false otherwise
     */
    private void refreshFilteredResults(final boolean wasNewEntity) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.refreshFilteredResults()");

            if (!wasNewEntity) {
                /* refresh the list of tags from the existing list that was modified */
                checkState(filteredResultsComponent != null, "filteredResultsComponent should not return null");
                checkState(filteredResultsComponent.getProviderData() != null, "filteredResultsComponent.getProviderData() should not return null");
                checkState(filteredResultsComponent.getProviderData().isValid(), "filteredResultsComponent.getProviderData() should be valid");

                filteredResultsDisplay.getProvider().displayAsynchronousList(filteredResultsComponent.getProviderData().getItems(),
                        filteredResultsComponent.getProviderData().getSize(),
                        filteredResultsComponent.getProviderData().getStartRow());
            } else {
                /* If we just created a new entity, refresh the list of entities from the database */

                filteredResultsComponent.bindExtendedFilteredResults(filteredResultsComponent.getQuery());

                /*
                 * reInitialiseView will flush the ui, which will flush the null ID back to the displayed object. To prevent that we
                 * need to call edit on the newly saved entity.
                 *
                 * lastDisplayedView might be null if we have never viewed any entity (like when creating a filter). If so,
                 * don't edit the new entity because there is no view open to display it.
                 */
                if (lastDisplayedView != null) {
                    checkState(filteredResultsComponent != null, "filteredResultsComponent should not return null");
                    checkState(filteredResultsComponent.getProviderData() != null, "filteredResultsComponent.getProviderData() should not return null");
                    checkState(filteredResultsComponent.getProviderData().getDisplayedItem() != null, "There needs to be a displayed item");
                    checkState(filteredResultsComponent.getProviderData().getDisplayedItem().getItem() != null, "The displayed item needs to reference a valid entity");

                    entityPropertiesView.getDriver().edit(filteredResultsComponent.getProviderData().getDisplayedItem().getItem());
                }
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.refreshFilteredResults()");
        }
    }

    /**
     * Given an entity, load a fresh copy from the database and display it on the screen. Note that this method
     * will update the selected item as well as the displayed item when a fresh copy is pulled down.
     *
     * @param getNewEntityCallback The callback used to load a fresh entity
     * @param selectedItem         The item that was selected from a collection of entities
     */
    protected void loadNewEntity(@NotNull final GetNewEntityCallback<T> getNewEntityCallback, @NotNull final V selectedItem) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.loadNewEntity()");

            /*
             * When an entity is selected we will get afresh copy from the database. This is to deal with a
             * situation where the search screen has been left for a while and the entity has been edited by someone
             * else in the mean time.
             */
            getNewEntityCallback.getNewEntity(selectedItem.getItem(), new DisplayNewEntityCallback<T>() {

                @Override
                public void displayNewEntity(@NotNull final T entity) {

                    try {
                        LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.loadNewEntity() DisplayNewEntityCallback.displayNewEntity(final T entity)");

                        // update the filtered list with the new entity
                        selectedItem.setItem(entity);

                        /*
                         * The selected item will be the category from the list. This is the unedited, unexpanded
                         * copy of the category
                         */
                        filteredResultsComponent.setSelectedItem(selectedItem);

                        /*
                         * All editing is done in a clone of the selected category. Any expanded collections will be
                         * copied into this category
                         */
                        filteredResultsComponent.getProviderData().setDisplayedItem(selectedItem.clone(true));

                        updateDisplayWithNewEntityData(false);


                    } finally {
                        LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.loadNewEntity() DisplayNewEntityCallback.displayNewEntity(final T entity)");
                    }

                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.loadNewEntity()");
        }
    }


    /**
     * Binds logic to the search results list row click event
     */
    protected void bindResultsListRowClicks(@NotNull final GetNewEntityCallback<T> getNewEntityCallback) {
        filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<V>() {
            @Override
            public void onCellPreview(@NotNull final CellPreviewEvent<V> event) {

                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.bindResultsListRowClicks() Anonymous.onCellPreview(final CellPreviewEvent<V> event)");

                        final V selectedItem = event.getValue();
                        if (!selectedItem.equals(filteredResultsComponent.getProviderData().getSelectedItem())) {
                            if (!isOKToProceed()) {
                                return;
                            }

                            loadNewEntity(getNewEntityCallback, selectedItem);
                        }
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.bindResultsListRowClicks() Anonymous.onCellPreview(final CellPreviewEvent<V> event)");
                    }
                }
            }
        });

    }

    /**
     * Called once a new entity has been selected from the filtered results view. This method
     * is expected to be used to make additional calls to the REST service to load data that
     * was not included when the selected entity was fetched, and then call initializeViews()
     * to display that data.
     */
    abstract protected void loadAdditionalDisplayedItemData();

    /**
     * Called when a new entity is selected, or when there is new data to display. This method is used to display any
     * data that is contained in the selected object when it is initially loaded, and when any data is loaded
     * asynchronously or after the initial entity was loaded.
     * <p/>
     * Typically, this method is initially called when the entity is loaded with no filter (i.e. filter = null), meaning
     * that the displayBlah() methods on the views are called.
     * <p/>
     * Additional data (usually child collections) is expected to be loaded and displayed in the
     * loadAdditionalDisplayedItemData() method. The loadAdditionalDisplayedItemData() will then
     * call initializeViews() with a filter set to the view that needs to be updated with the new data.
     *
     * @param filter null if all views are to be initialized, or includes a list of views to be initialized
     */
    abstract protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter);

    /**
     * Binds logic to the action buttons. This is called by the bind() method, and so should not
     * be called from an extending class.
     */
    abstract protected void bindActionButtons();

    /**
     * Bind logic to the filtered results buttons. This is called by the bind() method, and so should not
     * be called from an extending class.
     */
    abstract protected void bindFilteredResultsButtons();

    /**
     * Saves the position of the main split
     */
    private void bindMainSplitResize(@NotNull final String saveKey) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.bindMainSplitResize()");

            display.getSplitPanel().addResizeHandler(new ResizeHandler() {

                @Override
                public void onResize(@NotNull final ResizeEvent event) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.bindMainSplitResize() ResizeHandler.onResize()");
                        /**
                         * The results panel may not be displayed, in which case we don't want to save
                         * the size.
                         */
                        if (display.getResultsViewLayoutPanel().isAttached()) {
                            Preferences.INSTANCE.saveSetting(saveKey, display.getSplitPanel().getSplitPosition(display.getResultsViewLayoutPanel()) + "");
                        }
                    }  finally {
                        LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.bindMainSplitResize() ResizeHandler.onResize()");
                    }
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.bindMainSplitResize()");
        }
    }

    /**
     * Restores the size of the main split screen
     */
    protected void loadMainSplitResize(@NotNull final String mainSplitSizePreferenceKey) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.loadMainSplitResize()");

            checkState(display != null, "The display variable should have been initialized. You need to call bindSearchAndEdit() first.");

            if (display.getResultsViewLayoutPanel().isAttached()) {
                final double newPanelSize = Preferences.INSTANCE.getDouble(mainSplitSizePreferenceKey, Constants.SPLIT_PANEL_SIZE);
                final Widget widget = display.getResultsViewLayoutPanel();
                display.getSplitPanel().setSplitPosition(widget, newPanelSize < Constants.MINIMUM_SPLIT_SIZE ? Constants.MINIMUM_SPLIT_SIZE : newPanelSize, false);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "Caught exception");
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.loadMainSplitResize()");
        }
    }

    /**
     * Called when a new entity is selected
     */
    protected void initializeViews() {
        initializeViews(null);
    }


    /**
     * Used by the initializeViews method
     *
     * @param filter The filter containing the list of views, or null if all views are to be initialized
     * @param view   The view to test against the filter
     * @return true if the filter is null or if it contains the view, and false otherwise
     */
    protected boolean viewIsInFilter(@Nullable final List<BaseTemplateViewInterface> filter, @NotNull final BaseTemplateViewInterface view) {
        if (filter == null) {
            return true;
        }

        return filter.contains(view);
    }

    /**
     * Called when displaying changes to a entity or when changing views
     */
    protected void switchView(@NotNull final BaseTemplateViewInterface displayedView) {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchAndEditPresenter.switchView(final W displayedView)");

            if (beforeSwitchView(displayedView)) {

                /* Show/Hide any localised loading dialogs */
                if (lastDisplayedView != null) {
                    LOGGER.log(Level.INFO, "\tSetting old view to not shown");
                    lastDisplayedView.setViewShown(false);
                }

                /* update the new view */
                LOGGER.log(Level.INFO, "\tSetting new view to shown");
                displayedView.setViewShown(true);


                /* update the display widgets if we have changed displays */
                if (lastDisplayedView != displayedView) {
                    LOGGER.log(Level.INFO, "\tAdding new display to canvas");
                    display.displayChildView(displayedView);
                }

                /* copy any changes from the property view into the underlying object */
                if (lastDisplayedView == this.entityPropertiesView) {
                    LOGGER.log(Level.INFO, "\tFlushing data from properties view");
                    checkState(entityPropertiesView.getDriver() != null, "entityPropertiesView.getDriver() should not return null");
                    entityPropertiesView.getDriver().flush();
                }

                afterSwitchView(displayedView);

                lastDisplayedView = displayedView;
            }


        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchAndEditPresenter.switchView(final W displayedView)");
        }
    }

    /**
     * Called once switchView has completed. Override this method to perform some
     * additional logic after a new screen has been displayed.
     *
     * @param displayedView The newly displayed screen.
     */
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
    }

    /**
     * Called when switchView has been called. Override this method to perform some
     * additional logic before a new screen has been displayed.
     *
     * @param displayedView The newly displayed screen.
     * @return true if the switch should go ahead, and false otherwise
     */
    protected boolean beforeSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {
        return true;
    }


}
