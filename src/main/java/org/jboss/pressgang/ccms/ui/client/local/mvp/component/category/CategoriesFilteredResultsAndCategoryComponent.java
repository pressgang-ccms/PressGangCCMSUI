package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTCategoryInTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTTagInCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTCategoryInTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTTagInCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.searchandedit.BaseSearchAndEditComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.category.CategoryViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class CategoriesFilteredResultsAndCategoryComponent
        extends
        BaseSearchAndEditComponent<CategoriesFilteredResultsAndCategoryPresenter.Display, CategoryFilteredResultsPresenter.Display, RESTCategoryCollectionItemV1, CategoryViewInterface>
        implements CategoriesFilteredResultsAndCategoryPresenter.LogicComponent {

    private CategoryFilteredResultsPresenter.Display filteredResultsDisplay;
    private CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent;
    private CategoryPresenter.Display resultDisplay;
    private CategoryTagPresenter.Display tagDisplay;
    private CategoryTagPresenter.LogicComponent tagComponent;
    private CategoryViewInterface[] views;

    /**
     * A click handler used to display the category fields view
     */
    private final ClickHandler categoryDetailsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            reInitialiseView(resultDisplay);
        }

    };

    /**
     * A click handler used to display the category tags view
     */
    private final ClickHandler categoryTagsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            reInitialiseView(tagDisplay);
        }

    };

    /**
     * A click handler used to save any changes to the category
     */
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            /* Sync the UI to the underlying object */
            resultDisplay.getDriver().flush();

            RESTCallback<RESTCategoryV1> callback = new BaseRestCallback<RESTCategoryV1, Display>(display,
                    new BaseRestCallback.SuccessAction<RESTCategoryV1, Display>() {
                        @Override
                        public void doSuccessAction(RESTCategoryV1 retValue, Display display) {
                            retValue.cloneInto(filteredResultsComponent.getProviderData().getSelectedItem().getItem(), true);
                            retValue.cloneInto(filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), true);
                            filteredResultsDisplay.getProvider().updateRowData(
                                    filteredResultsComponent.getProviderData().getStartRow(),
                                    filteredResultsComponent.getProviderData().getItems());

                            reInitialiseView(lastDisplayedView);
                        }
                    }) {
            };

            if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
                final RESTCategoryV1 category = new RESTCategoryV1();
                category.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                category.explicitSetName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());
                category.explicitSetDescription(filteredResultsComponent.getProviderData().getDisplayedItem().getItem()
                        .getDescription());
                RESTCalls.saveCategory(callback, category);
            }
        }
    };

    @Override
    public void bind(final CategoryFilteredResultsPresenter.Display filteredResultsDisplay,
            final CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent,
            final CategoryPresenter.Display resultDisplay, final CategoryTagPresenter.Display tagDisplay,
            final CategoryTagPresenter.LogicComponent tagComponent,
            final CategoriesFilteredResultsAndCategoryPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);
        this.filteredResultsDisplay = filteredResultsDisplay;
        this.resultDisplay = resultDisplay;
        this.filteredResultsComponent = filteredResultsComponent;
        this.tagDisplay = tagDisplay;
        this.tagComponent = tagComponent;

        views = new CategoryViewInterface[] { resultDisplay, tagDisplay };

        bindMainSplitResize(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH);
        bindResultsListRowClicks();
        bindActionButtons();
        bindTagListButtonClicks();
    }

    /**
     * Binds behaviour to the tag list buttons
     */
    private void bindTagListButtonClicks() {
        tagDisplay.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<RESTTagCollectionItemV1, String>() {
            @Override
            public void update(final int index, final RESTTagCollectionItemV1 object, final String value) {
                boolean found = false;
                for (final RESTCategoryInTagCollectionItemV1 category : object.getItem().getCategories().getItems()) {
                    if (category.getItem().getId()
                            .equals(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId())) {
                        /* Tag was added and then removed */
                        if (category.returnIsAddItem()) {
                            object.getItem().getCategories().getItems().remove(category);
                        }
                        /* Tag existed, was removed and then was added again */
                        else if (category.returnIsRemoveItem()) {
                            category.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                        }
                        /* Tag existed and was removed */
                        else {
                            category.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                            category.getItem().setRelationshipSort(0);
                        }

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    final RESTCategoryInTagV1 newCategory = new RESTCategoryInTagV1();
                    newCategory.setId(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getId());
                    newCategory.setName(filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName());
                    newCategory.setRelationshipSort(0);

                    object.getItem().getCategories().addNewItem(newCategory);
                }

                /*
                 * In order for the warning to appear if selecting a new tag when unsaved changes exist, we need to set the
                 * configured parameters to reflect the fact that the category contains tags that will modify the database. So
                 * here we check to see if any tags have been added or removed. If there are none (i.e. a tag was added and then
                 * removed again without persisting the change in the database, or there were just no changes made) we remove
                 * the tags collection from the configured parameters.
                 */
                if (object.getItem().getCategories().returnDeletedAndAddedCollectionItems().size() != 0) {

                    /*
                     * Need to mark the categories collection as dirty. The explicitSetTags provides a convenient way to set the
                     * appropriate configured parameter value
                     */
                    object.getItem().explicitSetCategories(object.getItem().getCategories());
                } else {
                    object.getItem().getConfiguredParameters().remove(RESTBaseTagV1.CATEGORIES_NAME);
                }

                /* refresh the category list */
                tagDisplay.getPossibleChildrenProvider().displayNewFixedList(
                        tagComponent.getPossibleChildrenProviderData().getItems());

                /*
                 * refresh the list of tags in the category
                 */
                tagDisplay.setExistingChildrenProvider(tagComponent.generateExistingProvider(filteredResultsComponent
                        .getProviderData().getDisplayedItem().getItem()));
            }
        });

    }

    /**
     * Compare the displayed category (the one that is edited) with the selected category (the one that exists in the collection
     * used to build the category list). If there are unsaved changes, prompt the user.
     * 
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (filteredResultsComponent.getProviderData().getDisplayedItem() != null) {
            resultDisplay.getDriver().flush();

            if (unsavedCategoryChanged()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    private boolean unsavedCategoryChanged() {
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getProviderData().getSelectedItem().getItem()
                .getName(), filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                filteredResultsComponent.getProviderData().getSelectedItem().getItem().getDescription(),
                filteredResultsComponent.getProviderData().getDisplayedItem().getItem().getDescription()));
    }

    @Override
    protected void updateDisplayAfterSave(boolean wasNewEntity) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void bindResultsListRowClicks() {
        filteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTCategoryCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTCategoryCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    if (!checkForUnsavedChanges()) {
                        return;
                    }

                    /*
                     * The selected item will be the category from the list. This is the unedited, unexpanded copy of the
                     * category
                     */
                    filteredResultsComponent.getProviderData().setSelectedItem(event.getValue());
                    /*
                     * All editing is done in a clone of the selected category. Any expanded collections will be copied into
                     * this category
                     */
                    filteredResultsComponent.getProviderData().setDisplayedItem(event.getValue().clone(true));
                    /* Refresh the view, or display the properties view if none is shown */
                    reInitialiseView(lastDisplayedView == null ? resultDisplay : lastDisplayedView);
                }
            }
        });

    }

    @Override
    protected void bindActionButtons() {
        for (final CategoryViewInterface view : views) {
            view.getDetails().addClickHandler(this.categoryDetailsClickHandler);
            view.getChildren().addClickHandler(this.categoryTagsClickHandler);
            view.getSave().addClickHandler(this.saveClickHandler);
        }

    }

    @Override
    protected void reInitialiseView(final CategoryViewInterface displayedView) {

        /* We have changed views */
        if (lastDisplayedView != displayedView) {
            /* Save any changes to the underlying object */
            if (lastDisplayedView == this.resultDisplay) {
                resultDisplay.getDriver().flush();
            }

            /* Hide any wait dialogs from the last view */
            if (lastDisplayedView != null) {
                lastDisplayedView.setViewShown(false);
            }

            /* Update the displayed view */
            display.displayChildView(displayedView);
        }

        /* Show any wait dialogs from the new view, and update the view with the currently displayed entity */
        if (displayedView != null) {
            displayedView.setViewShown(true);
            displayedView.initialize(this.filteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);
        }

        lastDisplayedView = displayedView;
    }

    @Override
    protected String getQuery() {
        // TODO Auto-generated method stub
        return "";
    }
}
