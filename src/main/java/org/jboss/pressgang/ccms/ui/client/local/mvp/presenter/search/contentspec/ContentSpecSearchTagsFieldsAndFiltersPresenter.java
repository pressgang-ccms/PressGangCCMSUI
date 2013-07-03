package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.ContentSpecSearchResultsAndContentSpecViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SaveFilterDialogInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchFilterResultsAndFilterPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchLocalePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
@Dependent
public class ContentSpecSearchTagsFieldsAndFiltersPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {
    /**
     * The history token used to access this page
     */
    public static final String HISTORY_TOKEN = "ContentSpecSearchTagsFieldsAndFiltersView";

    @Inject
    private Display display;
    @Inject
    private SearchPresenter tagsComponent;
    @Inject
    private ContentSpecSearchFieldPresenter fieldsComponent;
    @Inject
    private SearchLocalePresenter localePresenter;
    /**
     * The presenter used to display the list of filters
     */
    @Inject
    private SearchFilterResultsAndFilterPresenter searchFilterResultsAndFilterPresenter;
    /**
     * The dialog used when saving or overwriting a filter
     */
    @Inject
    private SaveFilterDialogInterface saveFilterDialog;
    @Inject
    private HandlerManager eventBus;
    private HasWidgets container;

    @Override
    public void go(@NotNull final HasWidgets container) {

        this.container = container;
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        bindExtended(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN);

        tagsComponent.bindExtended(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN);
        fieldsComponent.bindExtended(ServiceConstants.SEARCH_FIELDS_HELP_TOPIC, HISTORY_TOKEN);
        localePresenter.bindExtended(ServiceConstants.SEARCH_LOCALES_HELP_TOPIC, HISTORY_TOKEN);
        searchFilterResultsAndFilterPresenter.bindSearchAndEditExtended(ServiceConstants.FILTERS_HELP_TOPIC, HISTORY_TOKEN,
                Constants.QUERY_PATH_SEGMENT_PREFIX);

        fieldsComponent.getDisplay().display(null, false);

        bindSearchButtons();
        loadSearchTags();
        loadSearchLocales();

        displayTags();
    }

    @Override
    public void close() {

    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        bind(helpTopicId, pageId, display);
    }

    private void loadSearchLocales() {
        RESTCalls.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> stringList) {
                localePresenter.getDisplay().display(stringList, false);
            }
        }, display);
    }

    /**
     * Gets the tags from the REST server
     */
    private void loadSearchTags() {

        @NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1,
                BaseTemplateViewInterface>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final BaseTemplateViewInterface waitDisplay) {

                /* Display the tags */
                tagsComponent.getDisplay().displayExtended(retValue, null, false, false);
            }
        });
        RESTCalls.getTags(callback);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
    }

    private void bindSearchButtons() {

        final ClickHandler tagsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayTags();
            }
        };

        final ClickHandler fieldsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayFields();
            }
        };

        final ClickHandler localesHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayLocales();
            }
        };

        final ClickHandler searchContentSpecsHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(
                        true) + fieldsComponent.getDisplay().getFields().getSearchQuery(
                        false) + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);

                eventBus.fireEvent(new ContentSpecSearchResultsAndContentSpecViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        final ClickHandler downloadZipHandler = new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                fieldsComponent.getDisplay().getDriver().flush();
                tagsComponent.getDisplay().getDriver().flush();
                localePresenter.getDisplay().getDriver().flush();

                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(
                        true) + fieldsComponent.getDisplay().getFields().getSearchQuery(
                        false) + localePresenter.getDisplay().getSearchUILocales().buildQueryString(false);
                Window.open(Constants.REST_SERVER + "/1/contentspecs/get/zip/" + query, "Zip Download", "");
            }
        };

        display.getSearchButton().addClickHandler(searchContentSpecsHandler);
        display.getDownloadZip().addClickHandler(downloadZipHandler);
        display.getTagsButton().addClickHandler(tagsHandler);
        display.getFields().addClickHandler(fieldsHandler);
        display.getLocales().addClickHandler(localesHandler);
    }

    private void resetTopActionButtons() {
        display.replaceTopActionButton(display.getLocalesDownLabel(), display.getLocales());
        display.replaceTopActionButton(display.getTagsButtonDownLabel(), display.getTagsButton());
        display.replaceTopActionButton(display.getFieldsDownLabel(), display.getFields());
        display.getTopViewSpecificLeftActionPanel().clear();
    }

    private void displayLocales() {
        this.setHelpTopicId(localePresenter.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(localePresenter.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getLocales(), display.getLocalesDownLabel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(true);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayTags() {
        this.setHelpTopicId(tagsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsComponent.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getTagsButton(), display.getTagsButtonDownLabel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(true);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    private void displayFields() {
        this.setHelpTopicId(fieldsComponent.getHelpTopicId());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsComponent.getDisplay().getPanel());
        resetTopActionButtons();
        display.replaceTopActionButton(display.getFields(), display.getFieldsDownLabel());

        fieldsComponent.getDisplay().setViewShown(true);
        tagsComponent.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        searchFilterResultsAndFilterPresenter.getDisplay().setViewShown(false);
    }

    public interface Display extends BaseTemplateViewInterface {

        PushButton getSearchButton();

        PushButton getDownloadZip();

        PushButton getTagsButton();

        PushButton getFields();

        PushButton getLocales();

        Label getTagsButtonDownLabel();

        Label getFieldsDownLabel();

        Label getLocalesDownLabel();
    }
}
