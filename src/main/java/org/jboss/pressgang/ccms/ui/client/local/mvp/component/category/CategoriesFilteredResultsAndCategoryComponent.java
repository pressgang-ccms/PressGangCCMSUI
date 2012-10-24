package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoriesFilteredResultsAndCategoryPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class CategoriesFilteredResultsAndCategoryComponent extends
        ComponentBase<CategoriesFilteredResultsAndCategoryPresenter.Display> implements
        CategoriesFilteredResultsAndCategoryPresenter.LogicComponent {

    private CategoryFilteredResultsPresenter.Display filteredResultsDisplay;
    private CategoryPresenter.Display resultDisplay;
    private CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent;
    
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
                            retValue.cloneInto(filteredResultsComponent.getCategoryProviderData().getSelectedItem().getItem(), true);
                            retValue.cloneInto(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem(), true);
                            filteredResultsDisplay.getProvider().updateRowData(filteredResultsComponent.getCategoryProviderData().getStartRow(),
                                    filteredResultsComponent.getCategoryProviderData().getItems());
                            resultDisplay.initialize(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem(), false);
                        }
                    }) {
            };

            RESTCategoryV1 category = new RESTCategoryV1();
            category.setId(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem().getId());
            category.explicitSetName(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem().getName());
            category.explicitSetDescription(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem().getDescription());
            RESTCalls.saveCategory(callback, category);
        }
    };
    
    @Override
    public void bind(final CategoryFilteredResultsPresenter.Display filteredResultsDisplay, final CategoryFilteredResultsPresenter.LogicCompnent filteredResultsComponent, final CategoryPresenter.Display resultDisplay, final CategoriesFilteredResultsAndCategoryPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        this.filteredResultsDisplay = filteredResultsDisplay;
        this.resultDisplay = resultDisplay;
        this.filteredResultsComponent = filteredResultsComponent;
        
        bindMainSplitResize();
        bindCategoryListRowClicks();
    }
    
    /**
     * Saves the width of the split screen
     */
    private void bindMainSplitResize() {
        display.getSplitPanel().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display.getSplitPanel()
                        .getSplitPosition(display.getResultsPanel()) + "");
            }
        });
    }
    
    /**
     * Bind the button click events for the topic editor screens
     */
    private void bindCategoryListRowClicks() {
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
                    filteredResultsComponent.getCategoryProviderData().setSelectedItem(event.getValue());
                    /*
                     * All editing is done in a clone of the selected category. Any expanded collections will be copied into
                     * this category
                     */
                    filteredResultsComponent.getCategoryProviderData().setDisplayedItem(event.getValue().clone(true));

                    resultDisplay.initialize(filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem(), false);
                }
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
        if (filteredResultsComponent.getCategoryProviderData().getDisplayedItem() != null) {
            resultDisplay.getDriver().flush();

            if (unsavedCategoryChanged()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    private boolean unsavedCategoryChanged() {
        return !(stringEqualsEquatingNullWithEmptyString(filteredResultsComponent.getCategoryProviderData().getSelectedItem().getItem().getName(),
                filteredResultsComponent.getCategoryProviderData().getDisplayedItem().getItem().getName()) && stringEqualsEquatingNullWithEmptyString(
                        filteredResultsComponent.getCategoryProviderData().getSelectedItem().getItem().getDescription(), filteredResultsComponent.getCategoryProviderData().getDisplayedItem()
                        .getItem().getDescription()));
    }
}
