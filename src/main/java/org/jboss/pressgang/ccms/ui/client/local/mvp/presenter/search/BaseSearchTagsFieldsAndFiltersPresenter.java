package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.List;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
public abstract class BaseSearchTagsFieldsAndFiltersPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {
    @Inject
    private SearchTagPresenter tagsPresenter;
    @Inject
    private SearchLocalePresenter localePresenter;
    @Inject
    private HandlerManager eventBus;
    @Inject private FailOverRESTCall failOverRESTCall;
    /**
     * true if we are showing bulk tag buttons, and false otherwise.
     */
    private boolean showBulkTags;

    protected abstract Display getDisplay();

    protected abstract BaseSearchFieldPresenter getFieldsPresenter();

    protected abstract BaseSearchFilterResultsAndFilterPresenter getSearchFilterResultsAndFilterPresenter();

    protected SearchTagPresenter getTagsPresenter() {
        return tagsPresenter;
    }

    protected SearchLocalePresenter getLocalePresenter() {
        return localePresenter;
    }

    protected HandlerManager getEventBus() {
        return eventBus;
    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        bind(helpTopicId, pageId, getDisplay());
    }

    protected void loadSearchLocales() {
        FailOverRESTCallDatabase.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> stringList) {
                localePresenter.getDisplay().display(stringList, false);
            }
        }, getDisplay(), failOverRESTCall);
    }

    /**
     * Gets the tags from the REST server
     */
    protected void loadSearchTags() {

        @NotNull final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1,
                BaseTemplateViewInterface>(
                getDisplay(), new BaseRestCallback.SuccessAction<RESTTagCollectionV1, BaseTemplateViewInterface>() {
            @Override
            public void doSuccessAction(@NotNull final RESTTagCollectionV1 retValue, @NotNull final BaseTemplateViewInterface waitDisplay) {

                /* Bind the load, load and search and overwrite buttons */
                bindFilterActionButtons(retValue);

                /* Display the tags */
                tagsPresenter.getDisplay().displayExtended(retValue, null, false, isShowBulkTags());
            }
        });
        RESTCalls.getTags(callback);
    }

    /**
     * Adds logic to the filter action buttons
     */
    protected abstract void bindFilterActionButtons(@NotNull final RESTTagCollectionV1 tags);

    @Override
    public void parseToken(@NotNull final String historyToken) {
    }

    protected void bindSearchButtons() {

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

        final ClickHandler filtersHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayFilters();
            }
        };

        getDisplay().getTagsButton().addClickHandler(tagsHandler);
        getDisplay().getFields().addClickHandler(fieldsHandler);
        getDisplay().getLocales().addClickHandler(localesHandler);
        getDisplay().getFilters().addClickHandler(filtersHandler);
    }

    protected void resetTopActionButtons() {
        getDisplay().replaceTopActionButton(getDisplay().getLocalesDownLabel(), getDisplay().getLocales());
        getDisplay().replaceTopActionButton(getDisplay().getTagsButtonDownLabel(), getDisplay().getTagsButton());
        getDisplay().replaceTopActionButton(getDisplay().getFieldsDownLabel(), getDisplay().getFields());
        getDisplay().replaceTopActionButton(getDisplay().getFiltersDownLabel(), getDisplay().getFilters());
        getDisplay().getTopViewSpecificLeftActionPanel().clear();
    }

    protected void displayLocales() {
        this.setHelpTopicId(localePresenter.getHelpTopicId());

        getDisplay().getPanel().clear();
        getDisplay().getPanel().setWidget(localePresenter.getDisplay().getPanel());
        resetTopActionButtons();
        getDisplay().replaceTopActionButton(getDisplay().getLocales(), getDisplay().getLocalesDownLabel());

        getFieldsPresenter().getDisplay().setViewShown(false);
        tagsPresenter.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(true);
        getSearchFilterResultsAndFilterPresenter().getDisplay().setViewShown(false);
    }

    protected void displayTags() {
        setHelpTopicId(tagsPresenter.getHelpTopicId());

        getDisplay().getPanel().clear();
        getDisplay().getPanel().setWidget(tagsPresenter.getDisplay().getPanel());
        resetTopActionButtons();
        getDisplay().replaceTopActionButton(getDisplay().getTagsButton(), getDisplay().getTagsButtonDownLabel());

        getFieldsPresenter().getDisplay().setViewShown(false);
        tagsPresenter.getDisplay().setViewShown(true);
        localePresenter.getDisplay().setViewShown(false);
        getSearchFilterResultsAndFilterPresenter().getDisplay().setViewShown(false);
    }

    protected void displayFilters() {
        setHelpTopicId(getSearchFilterResultsAndFilterPresenter().getHelpTopicId());

        getDisplay().getPanel().clear();
        getDisplay().getPanel().setWidget(getSearchFilterResultsAndFilterPresenter().getDisplay().getPanel());
        resetTopActionButtons();
        getDisplay().replaceTopActionButton(getDisplay().getFilters(), getDisplay().getFiltersDownLabel());

        getSearchFilterResultsAndFilterPresenter().getDisplay().getTopActionPanel().removeFromParent();
        getDisplay().getTopViewSpecificLeftActionPanel().setWidget(getSearchFilterResultsAndFilterPresenter().getDisplay().getTopActionPanel());

        getFieldsPresenter().getDisplay().setViewShown(false);
        tagsPresenter.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        getSearchFilterResultsAndFilterPresenter().getDisplay().setViewShown(true);
    }

    protected void displayFields() {
        this.setHelpTopicId(getFieldsPresenter().getHelpTopicId());

        getDisplay().getPanel().clear();
        getDisplay().getPanel().setWidget(getFieldsPresenter().getDisplay().getPanel());
        resetTopActionButtons();
        getDisplay().replaceTopActionButton(getDisplay().getFields(), getDisplay().getFieldsDownLabel());

        getFieldsPresenter().getDisplay().setViewShown(true);
        tagsPresenter.getDisplay().setViewShown(false);
        localePresenter.getDisplay().setViewShown(false);
        getSearchFilterResultsAndFilterPresenter().getDisplay().setViewShown(false);
    }

    public boolean isShowBulkTags() {
        return showBulkTags;
    }

    public void setShowBulkTags(boolean showBulkTags) {
        this.showBulkTags = showBulkTags;
    }

    public interface Display extends BaseTemplateViewInterface {

        PushButton getSearchButton();

        PushButton getDownloadZip();

        PushButton getTagsButton();

        PushButton getFields();

        PushButton getLocales();

        PushButton getFilters();

        Label getTagsButtonDownLabel();

        Label getFieldsDownLabel();

        Label getLocalesDownLabel();

        Label getFiltersDownLabel();
    }
}
