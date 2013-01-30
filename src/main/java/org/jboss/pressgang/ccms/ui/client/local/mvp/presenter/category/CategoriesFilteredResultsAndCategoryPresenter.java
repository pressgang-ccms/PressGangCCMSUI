package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTTagInCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.orderedchildren.SetNewChildSortCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.CategoriesFilteredResultsAndCategoryViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.categoryview.RESTCategoryV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.*;

/**
 * The presenter that adds logic to the category search and edit view.
 *
 * @author Matthew Casperson
 */
@Dependent
public class CategoriesFilteredResultsAndCategoryPresenter
        extends
        BaseSearchAndEditComponent<CategoryFilteredResultsPresenter.Display, RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1, CategoryViewInterface, CategoryPresenter.Display, RESTCategoryV1BasicDetailsEditor>
        implements TemplatePresenter {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "CategoriesFilteredResultsAndCategoryView";
    /**
     * A logger.
     */
    private static final Logger logger = Logger.getLogger(CategoriesFilteredResultsAndCategoryPresenter.class.getName());
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
     * The Errai event bus
     */
    @Inject
    private HandlerManager eventBus;
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
    public final void go(final HasWidgets container) {

        /* A call back used to get a fresh copy of the entity that was selected */
        final GetNewEntityCallback<RESTCategoryV1> getNewEntityCallback = new GetNewEntityCallback<RESTCategoryV1>() {

            @Override
            public void getNewEntity(final Integer id, final DisplayNewEntityCallback<RESTCategoryV1> displayCallback) {
                final RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, BaseTemplateViewInterface>(
                        display, new BaseRestCallback.SuccessAction<RESTCategoryV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTCategoryV1 retValue, final BaseTemplateViewInterface display) {
                        displayCallback.displayNewEntity(retValue);
                    }
                });
                RESTCalls.getCategory(callback, id);
            }
        };

        clearContainerAndAddTopLevelPanel(container, display);

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        categoryPresenter.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        categoryTagPresenter.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        filteredResultsPresenter.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, queryString);

        super.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, categoryPresenter.getDisplay(), categoryPresenter.getDisplay(),
                filteredResultsPresenter.getDisplay(), filteredResultsPresenter, display, display, getNewEntityCallback);

        bindExistingChildrenAddAndRemoveButtons();
        bindExistingChildrenRowClick();
    }

    @Override
    public final void parseToken(final String historyToken) {
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

                            @Override
                            public RESTTagInCategoryCollectionV1 getExistingCollection() {
                                return filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags();
                            }

                        }, new AddPossibleChildCallback<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1>() {

                            @Override
                            public void createAndAddChild(final RESTTagCollectionItemV1 copy) {

                                categoryTagPresenter.setSortOrderOfChildren(sortCallback);

                                final RESTTagInCategoryV1 newChild = new RESTTagInCategoryV1();
                                newChild.setId(copy.getItem().getId());
                                newChild.setName(copy.getItem().getName());
                                newChild.explicitSetRelationshipSort(Constants.NEW_CHILD_SORT_ORDER);
                                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags()
                                        .addNewItem(newChild);
                            }

                        }, new UpdateAfterChildModfiedCallback() {

                            @Override
                            public void updateAfterChidModfied() {
                                /*
                                 * refresh the list of tags in the category
                                 */
                                categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData()
                                        .getDisplayedItem().getItem());

                                /*
                                 * refresh the list of possible tags
                                 */
                                categoryTagPresenter.refreshPossibleChildList();
                            }

                        }
                );
    }



    @Override
    protected final void loadAdditionalDisplayedItemData() {
        try {
            logger.log(Level.INFO, "ENTER CategoriesFilteredResultsAndCategoryPresenter.loadAdditionalDisplayedItemData()");

            /* Display the tags that are added to the category */
            categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());

            /* Get a new collection of tags */
            categoryTagPresenter.refreshPossibleChildrenDataAndList();
        }
        finally {
            logger.log(Level.INFO, "EXIT CategoriesFilteredResultsAndCategoryPresenter.loadAdditionalDisplayedItemData()");
        }
    }

    @Override
    protected final void initializeViews(final List<CategoryViewInterface> filter) {
        try {
            logger.log(Level.INFO, "ENTER CategoriesFilteredResultsAndCategoryPresenter.initializeViews()");

            /* We need to initialize the view so the celltable buttons can display the correct labels */
            if (viewIsInFilter(filter, categoryTagPresenter.getDisplay()))  {
                categoryTagPresenter.getDisplay().initialize(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
            }

            /* Initialize the properties view */
            if (viewIsInFilter(filter, categoryPresenter.getDisplay())) {
                categoryPresenter.getDisplay()
                        .initialize(this.filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(), false);
            }
        } finally {
            logger.log(Level.INFO, "EXIT CategoriesFilteredResultsAndCategoryPresenter.initializeViews()");
        }
    }

    private void bindExistingChildrenRowClick() {
        categoryTagPresenter.getDisplay().getExistingChildUpButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    @Override
                    public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                        categoryTagPresenter.moveTagsUpAndDown(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                object, false, sortCallback);
                    }

                });

        categoryTagPresenter.getDisplay().getExistingChildDownButtonColumn().setFieldUpdater(
                new FieldUpdater<RESTTagInCategoryCollectionItemV1, String>() {

                    /**
                     * Swap the sort value for the tag that was selected with the tag below it.
                     */
                    @Override
                    public void update(final int index, final RESTTagInCategoryCollectionItemV1 object, final String value) {
                        categoryTagPresenter.moveTagsUpAndDown(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                object, true, sortCallback);
                    }
                });
    }

    @Override
    protected final void bindActionButtons() {
        /**
         * A click handler used to display the category fields view
         */
        final ClickHandler categoryDetailsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                switchView(categoryPresenter.getDisplay());
            }

        };

        /**
         * A click handler used to display the category tags view
         */
        final ClickHandler categoryTagsClickHandler = new

                ClickHandler() {
                    @Override
                    public void onClick(final ClickEvent event) {
                        switchView(categoryTagPresenter.getDisplay());
                    }

                };

        /**
         * A click handler used to save any changes to the category
         */
        final ClickHandler saveClickHandler = new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {

                    /* Was the tag we just saved a new tag? */
                    final boolean wasNewEntity = filteredResultsPresenter.getProviderData().getDisplayedItem().returnIsAddItem();

                     /* Sync the UI to the underlying object */
                    categoryPresenter.getDisplay().getDriver().flush();

                    final RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, Display>(display,
                            new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
                                @Override
                                public void doSuccessAction(final RESTCategoryV1 retValue, final Display display) {
                                    retValue.cloneInto(filteredResultsPresenter.getProviderData().getSelectedItem().getItem(), true);
                                    retValue.cloneInto(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem(),
                                            true);

                                    /* This category is no longer a new category */
                                    filteredResultsPresenter.getProviderData().getDisplayedItem()
                                            .setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                                    categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData()
                                            .getDisplayedItem().getItem());
                                    categoryTagPresenter.refreshPossibleChildrenDataAndList();

                                    updateDisplayAfterSave(wasNewEntity);

                                    Window.alert(PressGangCCMSUI.INSTANCE.SaveSuccess());
                                }
                            });

                    if (filteredResultsPresenter.getProviderData().getDisplayedItem() != null) {

                        /*
                         * If this is a new category, it needs to be saved in order to get the tag id to complete the category
                         * updates. Upon success, the categories will be updated.
                         */
                        final boolean unsavedTagChanges = unsavedCategoryChanges() || unsavedTagChanges();

                        if (unsavedTagChanges) {

                            final RESTCategoryV1 category = new RESTCategoryV1();
                            category.setId(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getId());
                            category.explicitSetName(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                                    .getName());
                            category.explicitSetDescription(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                                    .getDescription());
                            category.explicitSetTags(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem()
                                    .getTags());

                            if (wasNewEntity) {
                                RESTCalls.createCategory(callback, category);
                            } else {
                                RESTCalls.saveCategory(callback, category);
                            }
                        } else {
                            Window.alert(PressGangCCMSUI.INSTANCE.NoUnsavedChanges());
                        }
                    }
                }
            };

        for (final CategoryViewInterface view : new CategoryViewInterface[]{categoryPresenter.getDisplay(), categoryTagPresenter.getDisplay()}) {
            view.getDetails().addClickHandler(categoryDetailsClickHandler);
            view.getChildren().addClickHandler(categoryTagsClickHandler);
            view.getSave().addClickHandler(saveClickHandler);
        }

    }

    /**
     * Binds behaviour to the tag search and list view
     */
    @Override
    protected final void bindFilteredResultsButtons() {
        try {
            logger.log(Level.INFO, "ENTER CategoriesFilteredResultsAndCategoryPresenter.bindFilteredResultsButtons()");

            filteredResultsPresenter.getDisplay().getEntitySearch().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    if (isOKToProceed())
                        eventBus.fireEvent(new CategoriesFilteredResultsAndCategoryViewEvent(filteredResultsPresenter.getQuery(),
                                GWTUtilities.isEventToOpenNewWindow(event)));
                }
            });

            filteredResultsPresenter.getDisplay().getCreate().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {

                /* The 'selected' tag will be blank. This gives us something to compare to when checking for unsaved changes */
                    final RESTCategoryV1 selectedEntity = new RESTCategoryV1();
                    selectedEntity.setId(Constants.NULL_ID);
                    final RESTCategoryCollectionItemV1 selectedTagWrapper = new RESTCategoryCollectionItemV1(selectedEntity);

                /* The displayed tag will also be blank. This is the object that our data will be saved into */
                    final RESTCategoryV1 displayedEntity = new RESTCategoryV1();
                    displayedEntity.setId(Constants.NULL_ID);
                    displayedEntity.setTags(new RESTTagInCategoryCollectionV1());
                    final RESTCategoryCollectionItemV1 displayedTagWrapper = new RESTCategoryCollectionItemV1(displayedEntity,
                            RESTBaseCollectionItemV1.ADD_STATE);

                    filteredResultsPresenter.getProviderData().setSelectedItem(selectedTagWrapper);
                    filteredResultsPresenter.getProviderData().setDisplayedItem(displayedTagWrapper);

                    categoryTagPresenter.refreshExistingChildList(filteredResultsPresenter.getProviderData().getDisplayedItem().getItem());
                    categoryTagPresenter.refreshPossibleChildrenDataAndList();

                    initializeViews();

                    switchView(lastDisplayedView == null ? categoryPresenter.getDisplay() : lastDisplayedView);
                }
            });
        } finally {
            logger.log(Level.INFO, "EXIT CategoriesFilteredResultsAndCategoryPresenter.bindFilteredResultsButtons()");
        }
    }

    @Override
    public final boolean hasUnsavedChanges() {
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
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsPresenter.getProviderData().getSelectedItem().getItem()
                .getName(), filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                filteredResultsPresenter.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getDescription()));
    }

    /*
    * @return true if there are modified tags, false otherwise
    */
    private boolean unsavedTagChanges() {
        return !filteredResultsPresenter.getProviderData().getDisplayedItem().getItem().getTags()
                .returnDeletedAddedAndUpdatedCollectionItems().isEmpty();
    }

    @Override
    protected final void switchView(final CategoryViewInterface displayedView) {

        super.switchView(displayedView);

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
        }

        lastDisplayedView = displayedView;
    }

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends
            BaseSearchAndEditViewInterface<RESTCategoryV1, RESTCategoryCollectionV1, RESTCategoryCollectionItemV1> {

    }
}
