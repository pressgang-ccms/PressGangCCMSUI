package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

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
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTCategoryV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

/**
 * This presenter takes the CategoryFilteredResults view to provide a list of categories, and the CategoryView to provide
 * a way to edit the properties of a category.
 *
 * @author Matthew Casperson
 */
@Dependent
public class CategoriesFilteredResultsAndCategoryPresenter extends TemplatePresenter implements EditableView {

    /**
     * The history token used to identify this view
     */
    public static final String HISTORY_TOKEN = "CategoriesFilteredResultsAndCategoryView";

    /**
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the two views
     * CategoryFilteredResults view to provide a list of categories and the CategoryView.
     *
     * @author Matthew Casperson
     */
    public interface Display extends BaseTemplateViewInterface {
        /**
         * @return The panel used to hold the list of categories
         */
        SimpleLayoutPanel getResultsPanel();

        /**
         * @return The panel used to hold the views that display the category details
         */
        SimpleLayoutPanel getViewPanel();

        /**
         * @return The panel that holds the action buttons for the category details view
         */
        SimpleLayoutPanel getViewActionButtonsPanel();

        /**
         * @return The panel that holds the action buttons for the list of categories
         */
        SimpleLayoutPanel getResultsActionButtonsPanel();

        /**
         * @return The split panel that separates the category list from the category details views
         */
        HandlerSplitLayoutPanel getSplitPanel();
    }

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
                            retValue.cloneInto(categoryProviderData.getSelectedItem().getItem(), true);
                            retValue.cloneInto(categoryProviderData.getDisplayedItem().getItem(), true);
                            filteredResultsDisplay.getProvider().updateRowData(categoryProviderData.getStartRow(),
                                    categoryProviderData.getItems());
                            resultDisplay.initialize(categoryProviderData.getDisplayedItem().getItem(), false);
                        }
                    }) {
            };

            RESTCategoryV1 category = new RESTCategoryV1();
            category.setId(categoryProviderData.getDisplayedItem().getItem().getId());
            category.explicitSetName(categoryProviderData.getDisplayedItem().getItem().getName());
            category.explicitSetDescription(categoryProviderData.getDisplayedItem().getItem().getDescription());
            RESTCalls.saveCategory(callback, category);
        }
    };

    /**
     * An Errai injected instance of a class that implements Display. This is the view that holds all other views
     */
    @Inject
    private Display display;

    /**
     * An Errai injected instance of a class that implements CategoryFilteredResultsPresenter.Display. This is the view that displays
     * the list of categories.
     */
    @Inject
    private CategoryFilteredResultsPresenter.Display filteredResultsDisplay;

    /**
     * An Errai injected instance of a class that implements CategoryPresenter.Display. This is the view that displays the fields of
     * the category (name, description etc)
     */
    @Inject
    private CategoryPresenter.Display resultDisplay;

    /**
     * The category query string extracted from the history token
     */
    private String queryString;
    /**
     * Holds the data required to populate and refresh the categories list
     */
    private ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData = new ProviderUpdateData<RESTCategoryCollectionItemV1>();

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.getResultsActionButtonsPanel().setWidget(filteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(filteredResultsDisplay.getPanel());
        display.getViewActionButtonsPanel().setWidget(resultDisplay.getTopActionPanel());
        display.getViewPanel().setWidget(resultDisplay.getPanel());

        filteredResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        display.getSplitPanel().setSplitPosition(display.getResultsPanel(), Preferences.INSTANCE.getInt(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, Constants.SPLIT_PANEL_SIZE), false);

        bind();
    }

    /**
     * Add behaviour to the UI elements exposed by the views
     */
    private void bind() {
        super.bind(display, this);
        filteredResultsDisplay.setProvider(generateListProvider());
        bindCategoryListRowClicks();
        bindMainSplitResize();
    }

    /**
     * Saves the width of the split screen
     */
    private void bindMainSplitResize() {
        display.getSplitPanel().addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                Preferences.INSTANCE.saveSetting(Preferences.CATEGORY_VIEW_MAIN_SPLIT_WIDTH, display.getSplitPanel().getSplitPosition(display.getResultsPanel()) + "");
            }
        });
    }

    /**
     * @return A provider to be used for the category display list
     */
    private EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateListProvider() {
        final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> display) {

                final RESTCallback<RESTCategoryCollectionV1> callback = new RESTCallback<RESTCategoryCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        filteredResultsDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        filteredResultsDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTCategoryCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            categoryProviderData.setItems(retValue.getItems());
                            displayAsynchronousList(categoryProviderData.getItems(), retValue.getSize(),
                                    categoryProviderData.getStartRow());
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

                categoryProviderData.setStartRow(display.getVisibleRange().getStart());
                final int length = display.getVisibleRange().getLength();
                final int end = categoryProviderData.getStartRow() + length;

                RESTCalls.getCategoriesFromQuery(callback, queryString, categoryProviderData.getStartRow(), end);
            }
        };
        return provider;
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

                    /* The selected item will be the category from the list. This is the unedited, unexpanded copy of the category */
                    categoryProviderData.setSelectedItem(event.getValue());
                    /* All editing is done in a clone of the selected category. Any expanded collections will be copied into this category */
                    categoryProviderData.setDisplayedItem(event.getValue().clone(true));

                    resultDisplay.initialize(categoryProviderData.getDisplayedItem().getItem(), false);
                }
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
                if (queryElements[0].equals("categoryIds")) {
                    this.filteredResultsDisplay.getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("categoryName")) {
                    this.filteredResultsDisplay.getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("categoryDesc")) {
                    this.filteredResultsDisplay.getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

    /**
     * Compare the displayed category (the one that is edited) with the selected category (the one that exists in the collection used to
     * build the category list). If there are unsaved changes, prompt the user.
     *
     * @return true if the user wants to ignore the unsaved changes, false otherwise
     */
    @Override
    public boolean checkForUnsavedChanges() {
        /* sync the UI with the underlying tag */
        if (categoryProviderData.getDisplayedItem() != null) {
            resultDisplay.getDriver().flush();

            if (unsavedCategoryChanged()) {
                return Window.confirm(PressGangCCMSUI.INSTANCE.UnsavedChangesPrompt());
            }
        }
        return true;
    }

    private boolean unsavedCategoryChanged() {
        return !(stringEqualsEquatingNullWithEmptyString(categoryProviderData.getSelectedItem().getItem().getName(), categoryProviderData.getDisplayedItem().getItem().getName())
                && stringEqualsEquatingNullWithEmptyString(categoryProviderData.getSelectedItem().getItem().getDescription(), categoryProviderData.getDisplayedItem().getItem().getDescription()));
    }
}
