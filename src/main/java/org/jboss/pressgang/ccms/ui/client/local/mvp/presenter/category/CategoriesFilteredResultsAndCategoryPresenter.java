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
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.stringEqualsEquatingNullWithEmptyString;

/**
 * This presenter takes the TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and
 * TagCategoriesView to provide a way to edit the properties and relationships of a tag.
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
     * This interface describes the required UI elements for the parent view (i.e. the view that holds the four views
     * TagFilteredResults view to provide a list of tags, and the TagView, TagProjectsView and TagCategoriesView.
     *
     * @author Matthew Casperson
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

    /**
     * A click handler used to save any changes to the tag
     */
    private final ClickHandler saveClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {

            /* Sync the UI to the underlying object */
            resultDisplay.getDriver().flush();

            RESTCallback<RESTCategoryV1> callback = new RESTCallback<RESTCategoryV1>() {
                @Override
                public void begin() {
                    display.addWaitOperation();
                }

                @Override
                public void generalException(Exception ex) {
                    display.removeWaitOperation();
                }

                @Override
                public void success(RESTCategoryV1 retValue) {
                    retValue.cloneInto(categoryProviderData.getSelectedItem().getItem(), true);
                    retValue.cloneInto(categoryProviderData.getDisplayedItem().getItem(), true);
                    filteredResultsDisplay.getProvider().updateRowData(categoryProviderData.getStartRow(), categoryProviderData.getItems());
                    resultDisplay.initialize(categoryProviderData.getDisplayedItem().getItem(), false);
                    display.removeWaitOperation();
                }

                @Override
                public void failed() {
                    display.removeWaitOperation();
                }
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
     * An Errai injected instance of a class that implements TagFilteredResultsPresenter.Display. This is the view that displays
     * the list of tags.
     */
    @Inject
    private CategoryFilteredResultsPresenter.Display filteredResultsDisplay;

    /**
     * An Errai injected instance of a class that implements TagPresenter.Display. This is the view that displays the fields of
     * the tag (name, description etc)
     */
    @Inject
    private CategoryPresenter.Display resultDisplay;

    /**
     * The tag query string extracted from the history token
     */
    private String queryString;
    /**
     * Holds the data required to populate and refresh the tags list
     */
    private ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData = new ProviderUpdateData<RESTCategoryCollectionItemV1>();

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

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
     * @return A provider to be used for the tag display list
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

                    /* The selected item will be the tag from the list. This is the unedited, unexpanded copy of the tag */
                    categoryProviderData.setSelectedItem(event.getValue());
                    /* All editing is done in a clone of the selected tag. Any expanded collections will be copied into this tag */
                    categoryProviderData.setDisplayedItem(event.getValue().clone(true));

                    resultDisplay.initialize(categoryProviderData.getDisplayedItem().getItem(), false);
                }
            }
        });
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = historyToken.replace(HISTORY_TOKEN + ";", "");
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
     * Compare the displayed tag (the one that is edited) with the selected tag (the one that exists in the collection used to
     * build the tag list). If there are unsaved changes, prompt the user.
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
