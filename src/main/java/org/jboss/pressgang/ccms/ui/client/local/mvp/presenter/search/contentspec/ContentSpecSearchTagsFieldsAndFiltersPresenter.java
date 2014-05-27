package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterFieldCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterFieldCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterCategoryV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterFieldV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTFilterTypeV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerDetailsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchResultsAndContentSpecViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SaveFilterDialogInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
@Dependent
public class ContentSpecSearchTagsFieldsAndFiltersPresenter extends BaseSearchTagsFieldsAndFiltersPresenter {
    /**
     * The history token used to access this page
     */
    public static final String HISTORY_TOKEN = "ContentSpecSearchTagsFieldsAndFiltersView";
    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ContentSpecSearchTagsFieldsAndFiltersPresenter.class.getName());

    @Inject
    private Display display;
    @Inject
    private ContentSpecSearchFieldPresenter fieldsComponent;
    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private ContentSpecSearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;
    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject
    private SaveFilterDialogInterface saveFilterDialog;

    @Override
    public void go(@NotNull final HasWidgets container) {
        final RESTFilterCollectionItemV1 filterItem = new RESTFilterCollectionItemV1();
        filterItem.setItem(new RESTFilterV1());

        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        bindExtended();

        getTagsPresenter().bindExtended();
        getFieldsPresenter().bindExtended();
        getLocalePresenter().bindExtended();
        searchFilterResultsAndFilterPresenter.bindSearchAndEditExtended(
                Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_TYPE_FILTER_VAR + "=" + CommonConstants
                        .FILTER_CONTENT_SPEC);

        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(boolean readOnly) {
                fieldsComponent.getDisplay().display(filterItem.getItem(), readOnly);
            }
        });

        bindSearchButtons();
        loadSearchTags();
        loadSearchLocales();

        displayTags();

        // Set a blank filter as the current displayed item
        getSearchFilterResultsAndFilterPresenter().getSearchFilterFilteredResultsPresenter()
                .getProviderData().setDisplayedItem(filterItem);
    }

    @Override
    public void close() {

    }

    @Override
    protected Display getDisplay() {
        return display;
    }

    @Override
    protected BaseSearchFieldPresenter getFieldsPresenter() {
        return fieldsComponent;
    }

    @Override
    protected BaseSearchFilterResultsAndFilterPresenter getSearchFilterResultsAndFilterPresenter() {
        return searchFilterResultsAndFilterPresenter;
    }

    /**
     * Adds logic to the filter action buttons
     */
    @Override
    protected void bindFilterActionButtons(@NotNull final RESTTagCollectionV1 tags) {
        final ClickHandler loadClickHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();

                isReadOnlyMode(new ReadOnlyCallback() {
                    @Override
                    public void readonlyCallback(boolean readOnly) {
                        getTagsPresenter().getDisplay().displayExtended(tags, displayedFilter, readOnly, false);
                        fieldsComponent.getDisplay().display(displayedFilter, readOnly);
                    }
                });
            }
        };

        final ClickHandler loadAndSearchClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();
                final String query = Constants.QUERY_PATH_SEGMENT_PREFIX + CommonFilterConstants.FILTER_ID + "=" + displayedFilter.getId();
                getEventBus().fireEvent(
                        new ContentSpecSearchResultsAndContentSpecViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        /*
            A filter instance to be shared between the click handlers for create and overwrite, and the save
            dialog ok click handler.
        */
        final RESTFilterV1 filter = new RESTFilterV1();

        final ClickHandler saveFilter = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem() != null, "There should be a displayed collection item.");
                checkState(searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null, "The displayed collection item to reference a valid entity.");

                /* Save any changes back to the underlying object */
                fieldsComponent.getDisplay().getDriver().flush();
                getTagsPresenter().getDisplay().getDriver().flush();

                final RESTFilterV1 displayedFilter = searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem();

                /* Setting the id to the value of the displayed filter is the indication that this is an updated filter */
                filter.setId(displayedFilter.getId());

                // If the displayed filter has no id, it's new so set the type
                if (displayedFilter.getId() == null) {
                    filter.explicitSetType(RESTFilterTypeV1.CONTENT_SPEC);
                }

                /* An overwritten filter will delete any existing children before adding new ones */
                filter.explicitSetFilterTags_OTM(new RESTFilterTagCollectionV1());
                filter.explicitSetFilterFields_OTM(new RESTFilterFieldCollectionV1());
                filter.explicitSetFilterCategories_OTM(new RESTFilterCategoryCollectionV1());

                if (displayedFilter.getFilterTags_OTM() != null) {
                    for (@NotNull final RESTFilterTagCollectionItemV1 tag : displayedFilter.getFilterTags_OTM().getItems()) {
                        @NotNull final RESTFilterTagV1 removeTag = new RESTFilterTagV1();
                        removeTag.setId(tag.getItem().getId());
                        filter.getFilterTags_OTM().addRemoveItem(removeTag);
                    }
                }

                if (displayedFilter.getFilterFields_OTM() != null) {
                    for (@NotNull final RESTFilterFieldCollectionItemV1 field : displayedFilter.getFilterFields_OTM().getItems()) {
                        @NotNull final RESTFilterFieldV1 remove = new RESTFilterFieldV1();
                        remove.setId(field.getItem().getId());
                        filter.getFilterFields_OTM().addRemoveItem(remove);
                    }
                }

                if (displayedFilter.getFilterCategories_OTM() != null) {
                    for (@NotNull final RESTFilterCategoryCollectionItemV1 category : displayedFilter.getFilterCategories_OTM().getItems()) {
                        @NotNull final RESTFilterCategoryV1 remove = new RESTFilterCategoryV1();
                        remove.setId(category.getItem().getId());
                        filter.getFilterCategories_OTM().addRemoveItem(remove);
                    }
                }

                saveFilterDialog.getDialogBox().show();

                saveFilterDialog.getName().setValue(displayedFilter.getName());
                saveFilterDialog.getDescription().setValue(displayedFilter.getDescription());
            }
        };

        final ClickHandler saveOKHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {

                saveFilterDialog.getDialogBox().hide();

                getTagsPresenter().getDisplay().getSearchUIProjects().populateFilter(filter);
                fieldsComponent.getDisplay().getFields().populateFilter(filter);

                filter.explicitSetName(saveFilterDialog.getName().getValue());
                filter.explicitSetDescription(saveFilterDialog.getDescription().getValue());

                final RESTCallBack<RESTFilterV1> callback = new RESTCallBack<RESTFilterV1>() {
                    @Override
                    public void success(@NotNull final RESTFilterV1 retValue) {
                        try {
                            LOGGER.log(Level.INFO, "ENTER ContentSpecSearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");

                            // Create the topic wrapper
                            @NotNull final RESTFilterCollectionItemV1 collectionItem = new RESTFilterCollectionItemV1();
                            collectionItem.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);

                            // create the topic, and add to the wrapper
                            collectionItem.setItem(retValue);

                            /* Update the displayed topic */
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().getProviderData().setDisplayedItem(collectionItem.clone(true));
                            searchFilterResultsAndFilterPresenter.getSearchFilterFilteredResultsPresenter().setSelectedItem(collectionItem);

                            /* if filter.getId() == null, we created a new filter */
                            searchFilterResultsAndFilterPresenter.updateDisplayWithNewEntityData(filter.getId() == null);


                        } finally {
                            LOGGER.log(Level.INFO, "EXIT ContentSpecSearchTagsFieldsAndFiltersPresenter.bindFilterActionButtons() createFilter.saveOKHandler() SuccessAction.doSuccessAction()");
                        }
                    }

                    @Override
                    public void failed() {
                        saveFilterDialog.getDialogBox().hide();
                    }
                };

                if (filter.getId() == null) {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.createFilter(filter), callback, display);
                } else {
                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.updateFilter(filter), callback, display);
                }
            }
        };

        @NotNull final ClickHandler saveCancelHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                saveFilterDialog.getDialogBox().hide();
            }
        };

        searchFilterResultsAndFilterPresenter.getDisplay().getLoad().addClickHandler(loadClickHandler);
        searchFilterResultsAndFilterPresenter.getDisplay().getLoadAndSearch().addClickHandler(loadAndSearchClickHandler);
        searchFilterResultsAndFilterPresenter.getDisplay().getOverwrite().addClickHandler(saveFilter);
        searchFilterResultsAndFilterPresenter.getDisplay().getCreate().addClickHandler(saveFilter);
        saveFilterDialog.getOk().addClickHandler(saveOKHandler);
        saveFilterDialog.getCancel().addClickHandler(saveCancelHandler);
    }

    @Override
    protected void bindSearchButtons() {
        super.bindSearchButtons();

        final ClickHandler searchContentSpecsHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                getTagsPresenter().getDisplay().getDriver().flush();
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(
                        true) + getFieldsPresenter().getDisplay().getFields().getSearchQuery(
                        false) + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                getEventBus().fireEvent(
                        new ContentSpecSearchResultsAndContentSpecViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        final ClickHandler downloadZipHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                getFieldsPresenter().getDisplay().getDriver().flush();
                getTagsPresenter().getDisplay().getDriver().flush();
                getLocalePresenter().getDisplay().getDriver().flush();

                final String query = getTagsPresenter().getDisplay().getSearchUIProjects().getSearchQuery(
                        true) + getFieldsPresenter().getDisplay().getFields().getSearchQuery(
                        false) + getLocalePresenter().getDisplay().getSearchUILocales().buildQueryString(false);

                ServerDetails.getSavedServer(new ServerDetailsCallback() {
                    @Override
                    public void serverDetailsFound(@NotNull final ServerDetails serverDetails) {
                        Window.open(serverDetails.getRestEndpoint() + "/1/contentspecs/get/zip/" + query, "Zip Download", "");
                    }
                });
            }
        };

        display.getSearchButton().addClickHandler(searchContentSpecsHandler);
        display.getDownloadZip().addClickHandler(downloadZipHandler);
    }

    public interface Display extends BaseSearchTagsFieldsAndFiltersPresenter.Display {
    }
}
