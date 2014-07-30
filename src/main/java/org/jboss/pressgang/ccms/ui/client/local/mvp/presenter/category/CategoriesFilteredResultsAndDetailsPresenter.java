/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModifiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.SetNewChildSortCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.common.AlertBox;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter that adds logic to the category search and edit view.
 *
 * @author Matthew Casperson
 */
@Dependent
public class CategoriesFilteredResultsAndDetailsPresenter
        extends
        BaseSearchAndEditPresenter<
                RESTCategoryV1,
                RESTCategoryCollectionV1,
                RESTCategoryCollectionItemV1,
                RESTCategoryV1BasicDetailsEditor>
        implements BaseTemplatePresenterInterface {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "CategoriesFilteredResultsAndCategoryView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoriesFilteredResultsAndDetailsPresenter.class.getName());
    /**
     * Used when moving children up and down
     */
    private final SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1> sortCallback = new SetNewChildSortCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>() {

        @Override
        public boolean setSort(@NotNull final RESTTagInCategoryCollectionItemV1 child, final int index) {
            if (child.getItem().getRelationshipSort() == null || child.getItem().getRelationshipSort() != index) {
                child.getItem().explicitSetRelationshipSort(index);
                /* Set any unchanged items to updated */
                if (RESTBaseEntityCollectionItemV1.UNCHANGED_STATE.equals(child.getState())) {
                    child.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                }

                return true;
            }

            return false;
        }
    };
    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /**
     * The presenter used to display the list of categories
     */
    @Inject
    private CategoryFilteredResultsPresenter filteredResultsPresenter;

    /**
     * An Errai injected instance of a class that implements CategoryPresenter.LogicComponent
     */
    @Inject
    private CategoryPresenter categoryPresenter;
    /**
     * The presenter used to display the category's tags.
     */
    @Inject
    private CategoryTagPresenter categoryTagPresenter;
    /**
     * The category query string extracted from the history token
     */
    private String queryString;

    @Override
    protected void go() {
        bindSearchAndEditExtended(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
       /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTCategoryV1> getNewEntityCallback = new GetNewEntityCallback<RESTCategoryV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTCategoryV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTCategoryV1> displayCallback) {
                final RESTCallBack<RESTCategoryV1> callback = new RESTCallBack<RESTCategoryV1>() {
                    @Override
                    public void success(@NotNull final RESTCategoryV1 retValue) {
                            checkArgument(retValue.getTags() != null, "The initially retrieved entity should have a tags collection");

                            displayCallback.displayNewEntity(retValue);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getCategory(selectedEntity.getId()), callback, display);
            }
        };

        categoryPresenter.bindExtended();
        categoryTagPresenter.bindDetailedChildrenExtended();
        filteredResultsPresenter.bindExtendedFilteredResults(queryString);

        super.bindSearchAndEdit(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, categoryPresenter.getDisplay(), categoryPresenter.getDisplay(),
                filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display, getNewEntityCallback);

        bindExistingChildrenAddAndRemoveButtons();
        bindExistingChildrenRowClick();

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                display.getSave().setEnabled(!readOnly);
                filteredResultsPresenter.getDisplay().getCreate().setEnabled(!readOnly);
            }
        });
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }

    /**
     * Bind the logic to add and remove possible children.
     */
    private void bindExistingChildrenAddAndRemoveButtons() {
        categoryTagPresenter
                .bindPossibleChildrenListButtonClicks(
                        new GetExistingCollectionCallback<RESTTagInCategoryV1, RESTTagInCategoryCollectionV1, RESTTagInCategoryCollectionItemV1>() {

                            @NotNull
                            @Override
                            public RESTTagInCategoryCollectionV1 getExistingCollection() {
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags() != null, "The displayed collection item to reference a valid entity with a valid tags collection.");

                                return filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags();
                            }

                        }, new AddPossibleChildCallback<RESTTagCollectionItemV1>() {

                            @Override
                            public void createAndAddChild(@NotNull final RESTTagCollectionItemV1 copy) {

                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags() != null, "The displayed collection item to reference a valid entity with a valid tags collection.");

                                categoryTagPresenter.setSortOrderOfChildren(sortCallback);

                                @NotNull final RESTTagInCategoryV1 newChild = new RESTTagInCategoryV1();
                                newChild.setId(copy.getItem().getId());
                                newChild.setName(copy.getItem().getName());
                                newChild.explicitSetRelationshipSort(Constants.NEW_CHILD_SORT_ORDER);
                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags().addNewItem(newChild);
                            }

                        }, new UpdateAfterChildModifiedCallback() {

                            @Override
                            public void updateAfterChildModified() {
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");


                                /*
                                 * refresh the list of tags in the category
                                 */
                                categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());

                                /*
                                 * refresh the list of possible tags
                                 */
                                categoryTagPresenter.redisplayPossibleChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
                            }

                        }
                );
    }


    @Override
    protected void loadAdditionalDisplayedItemData() {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoriesFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");

            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

            /* Display the tags that are added to the category */
            categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of tags */
            categoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoriesFilteredResultsAndDetailsPresenter.loadAdditionalDisplayedItemData()");
        }
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoriesFilteredResultsAndDetailsPresenter.initializeViews()");

            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
            checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

            isReadOnlyMode(new ReadOnlyCallback() {
                @Override
                public void readonlyCallback(boolean readOnly) {
                    /* We need to initialize the view so the cell table buttons can display the correct labels */
                    if (viewIsInFilter(filter, categoryTagPresenter.getDisplay())) {
                        categoryTagPresenter.getDisplay().display(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                                , readOnly);
                        categoryTagPresenter.displayDetailedChildrenExtended(filteredResultsPresenter.getProviderData().getDisplayedItem
                                ().getItem(), readOnly);
                    }

                    /* Initialize the properties view */
                    if (viewIsInFilter(filter, categoryPresenter.getDisplay())) {
                        categoryPresenter.getDisplay().display(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                readOnly);
                    }
                }
            });

        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoriesFilteredResultsAndDetailsPresenter.initializeViews()");
        }
    }

    private void bindExistingChildrenRowClick() {
        categoryTagPresenter.getDisplay().getExistingChildUpButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    @Override
                    public void update(final int index, @NotNull final RESTTagInCategoryCollectionItemV1 object, final String value) {

                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                        categoryTagPresenter.moveTagsUpAndDown(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                object, false, sortCallback);
                    }

                });

        categoryTagPresenter.getDisplay().getExistingChildDownButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    /**
                     * Swap the sort value for the tag that was selected with the tag below it.
                     */
                    @Override
                    public void update(final int index, @NotNull final RESTTagInCategoryCollectionItemV1 object, final String value) {

                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                        categoryTagPresenter.moveTagsUpAndDown(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                object, true, sortCallback);
                    }
                });
    }

    @Override
    protected void bindActionButtons() {
        /**
         * A click handler used to display the category fields view
         */
        @NotNull final ClickHandler categoryDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                switchView(categoryPresenter.getDisplay());
            }

        };

        /**
         * A click handler used to display the category tags view
         */
        @NotNull final ClickHandler categoryTagsClickHandler = new

                ClickHandler() {
                    @Override
                    public void onClick(@NotNull final ClickEvent event) {
                        switchView(categoryTagPresenter.getDisplay());

                    }

                };

        /**
         * A click handler used to save any changes to the category
         */
        @NotNull final ClickHandler saveClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
                checkState(filteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
                checkState(filteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

                /* Was the tag we just saved a new tag? */
                final boolean wasNewEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                     /* Sync the UI to the underlying object */
                categoryPresenter.getDisplay().getDriver().flush();

                final RESTCallBack<RESTCategoryV1> callback = new RESTCallBack<RESTCategoryV1>() {
                    public void success(@NotNull final RESTCategoryV1 retValue) {
                        retValue.cloneInto(filteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                        retValue.cloneInto(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), true);

                                /* This category is no longer a new category */
                        filteredResultsPresenter.getProviderData().getDisplayedItem().setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);

                        categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
                        categoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());

                        updateDisplayWithNewEntityData(wasNewEntity);

                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.SaveSuccess());
                    }
                };

                if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                        /*
                         * If this is a new category, it needs to be saved in order to get the tag id to complete the category
                         * updates. Upon success, the categories will be updated.
                         */
                    final boolean unsavedTagChanges = unsavedCategoryChanges() || unsavedTagChanges();

                    if (unsavedTagChanges) {

                        final RESTCategoryV1 category = new RESTCategoryV1();
                        category.setId(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                        category.explicitSetName(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName());
                        category.explicitSetDescription(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getDescription());
                        category.explicitSetTags(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags());

                        if (wasNewEntity) {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createCategory(category), callback, display);
                        } else {
                            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.saveCategory(category), callback, display);
                        }
                    } else {
                        AlertBox.setMessageAndDisplay(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                    }
                }
            }
        };


        display.getDetails().addClickHandler(categoryDetailsClickHandler);
        display.getChildren().addClickHandler(categoryTagsClickHandler);
        display.getSave().addClickHandler(saveClickHandler);


    }

    private void doSearch(final boolean newWindow) {
        if (isOKToProceed()) {
            getEventBus().fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(filteredResultsPresenter.getQuery(),
                    newWindow));
        }
    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected void bindFilteredResultsButtons() {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoriesFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");

            filteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
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

            filteredResultsPresenter.getDisplay().getDescriptionFilter().addKeyPressHandler(searchKeyPressHandler);
            filteredResultsPresenter.getDisplay().getIdFilter().addKeyPressHandler(searchKeyPressHandler);
            filteredResultsPresenter.getDisplay().getNameFilter().addKeyPressHandler(searchKeyPressHandler);

            filteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {

                    /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                    final RESTCategoryV1 selectedEntity = new RESTCategoryV1();
                    selectedEntity.setId(Constants.NULL_ID);
                    final RESTCategoryCollectionItemV1 selectedTagWrapper = new RESTCategoryCollectionItemV1(selectedEntity);

                    /* The displayed tag will also be blank. This is the object that our data will be saved into */
                    final RESTCategoryV1 displayedEntity = new RESTCategoryV1();
                    displayedEntity.setId(Constants.NULL_ID);
                    displayedEntity.setTags(new RESTTagInCategoryCollectionV1());
                    final RESTCategoryCollectionItemV1 displayedTagWrapper = new RESTCategoryCollectionItemV1(displayedEntity, RESTBaseEntityCollectionItemV1.ADD_STATE);

                    filteredResultsPresenter.setSelectedItem(selectedTagWrapper);
                    filteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                    categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
                    categoryTagPresenter.refreshPossibleChildrenDataFromRESTAndRedisplayList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());

                    updateViewsAfterNewEntityLoaded();
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoriesFilteredResultsAndDetailsPresenter.bindFilteredResultsButtons()");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {
            categoryPresenter.getDisplay().getDriver().flush();

            return unsavedCategoryChanges() || unsavedTagChanges();
        }
        return false;
    }

    /**
     * Compare the selected and displayed category, and see if any of the fields have changed
     *
     * @return true if there are unsaved changes, false otherwise
     */

    private boolean unsavedCategoryChanges() {

        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
        checkState(filteredResultsPresenter.getProviderData().getSelectedItem() != null, "There should be a selected collection item.");
        checkState(filteredResultsPresenter.getProviderData().getSelectedItem().getItem() != null, "The selected collection item to reference a valid entity.");

        if (!stringEqualsEquatingNullWithEmptyString(filteredResultsPresenter.getProviderData().getSelectedItem().getItem().getName(),
                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName())) {
            return true;
        }

        if (!stringEqualsEquatingNullWithEmptyString(filteredResultsPresenter.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getDescription())) {
            return true;
        }

        return false;
    }

    /*
    * @return true if there are modified tags, false otherwise
    */
    private boolean unsavedTagChanges() {
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");
        checkState(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags() != null, "The displayed collection item to reference a valid entity and have a tags collection");

        return !filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags()
                .returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    @Override
    protected void afterSwitchView(@NotNull final BaseTemplateViewInterface displayedView) {

        enableAndDisableActionButtons(displayedView);
        displayedView.setViewShown(true);
    }

    private void enableAndDisableActionButtons(@NotNull final BaseTemplateViewInterface displayedView) {
        this.display.replaceTopActionButton(this.display.getChildrenDown(), this.display.getChildren());
        this.display.replaceTopActionButton(this.display.getDetailsDown(), this.display.getDetails());

        if (displayedView == this.categoryPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getDetails(), this.display.getDetailsDown());
        } else if (displayedView == this.categoryTagPresenter.getDisplay()) {
            this.display.replaceTopActionButton(this.display.getChildren(), this.display.getChildrenDown());
        }
    }


    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> {
        PushButton getChildren();

        PushButton getDetails();

        PushButton getSave();

        Label getChildrenDown();

        Label getDetailsDown();
    }
}
