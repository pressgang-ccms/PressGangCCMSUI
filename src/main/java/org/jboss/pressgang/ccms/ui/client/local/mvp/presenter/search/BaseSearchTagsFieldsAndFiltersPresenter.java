package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ReadOnlyCallback;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to display the search screen, including the child tags, fields, locales and filters
 * presenters.
 */
public abstract class BaseSearchTagsFieldsAndFiltersPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {
    @Inject
    private SearchTagPresenter tagsPresenter;
    @Inject
    private SearchLocalePresenter localePresenter;
    /**
     * true if we are showing bulk tag buttons, and false otherwise.
     */
    private boolean showBulkTags;
    private boolean tagsLoaded = false;

    public abstract Display getDisplay();

    protected abstract BaseSearchFieldPresenter getFieldsPresenter();

    protected abstract BaseSearchFilterResultsAndFilterPresenter getSearchFilterResultsAndFilterPresenter();

    protected SearchTagPresenter getTagsPresenter() {
        return tagsPresenter;
    }

    protected boolean isTagsLoaded() {
        return tagsLoaded;
    }

    protected void setTagsLoaded(final boolean tagsLoaded) {
        this.tagsLoaded = tagsLoaded;
    }

    protected SearchLocalePresenter getLocalePresenter() {
        return localePresenter;
    }

    @Override
    public void bindExtended() {
        buildHelpDatabase();
    }

    protected void loadSearchLocales() {
        isReadOnlyMode(new ReadOnlyCallback() {
            @Override
            public void readonlyCallback(final boolean readOnly) {
                getServerSettings(new ServerSettingsCallback() {
                    @Override
                    public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings) {
                        final List<String> locales = serverSettings.getLocales();
                        Collections.sort(locales);
                        localePresenter.getDisplay().display(locales, readOnly);
                    }
                });
            }
        });
    }

    /**
     * Gets the tags from the REST server
     */
    protected void loadSearchTags() {
        final RESTFilterCollectionItemV1 selectedFilter = getSearchFilterResultsAndFilterPresenter()
                .getSearchFilterFilteredResultsPresenter().getProviderData().getSelectedItem();

        // If no filter is defined, or the filter has no tags then lazy load the tags, otherwise we need to eager load them
        if (selectedFilter == null
                || selectedFilter.getItem() == null
                || selectedFilter.getItem().getFilterTags_OTM() == null
                || selectedFilter.getItem().getFilterTags_OTM().getItems().size() <= 0) {
            final RESTFilterV1 displayedFilter = selectedFilter == null ? null : selectedFilter.getItem();

            final RESTCallBack<RESTProjectCollectionV1> callback = new RESTCallBack<RESTProjectCollectionV1>() {
                @Override
                public void success(@NotNull final RESTProjectCollectionV1 retValue) {
                    /* Display the tags */
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(boolean readOnly) {
                            tagsPresenter.getDisplay().displayExtended(retValue, displayedFilter, readOnly, isShowBulkTags());
                        }
                    });
                }
            };

            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProjects(false), callback, getDisplay());
        } else {
            final RESTCallBack<RESTTagCollectionV1> callback = new RESTCallBack<RESTTagCollectionV1>() {
                @Override
                public void success(@NotNull final RESTTagCollectionV1 retValue) {
                    /* Display the tags */
                    isReadOnlyMode(new ReadOnlyCallback() {
                        @Override
                        public void readonlyCallback(boolean readOnly) {
                            tagsPresenter.getDisplay().displayExtended(retValue, selectedFilter.getItem(), readOnly, isShowBulkTags());
                        }
                    });
                }
            };

            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTags(), callback, getDisplay());
        }
    }

    /**
     * Adds logic to the filter action buttons
     */
    protected abstract void bindFilterActionButtons();

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

        /* Bind the load, load and search and overwrite buttons */
        bindFilterActionButtons();
    }

    protected void resetTopActionButtons() {
        getDisplay().replaceTopActionButton(getDisplay().getLocalesDownLabel(), getDisplay().getLocales());
        getDisplay().replaceTopActionButton(getDisplay().getTagsButtonDownLabel(), getDisplay().getTagsButton());
        getDisplay().replaceTopActionButton(getDisplay().getFieldsDownLabel(), getDisplay().getFields());
        getDisplay().replaceTopActionButton(getDisplay().getFiltersDownLabel(), getDisplay().getFilters());
        getDisplay().getTopViewSpecificLeftActionPanel().clear();
    }

    protected void displayLocales() {
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
        if (!tagsLoaded) {
            tagsLoaded = true;
            loadSearchTags();
        }

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

    /**
     * Assign help info to the UI elements exposed by this presenter.
     */
    protected void buildHelpDatabase() {
        setDataAttribute(getDisplay().getDownloadZip(), ServiceConstants.HELP_TOPICS.SEARCH_DOWNLOAD_ZIP.getId());
        setDataAttribute(tagsPresenter.getDisplay().getEditor().getProjectButtonPanel(),
                ServiceConstants.HELP_TOPICS.SEARCH_PROJECTS_COLUMN.getId());
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
