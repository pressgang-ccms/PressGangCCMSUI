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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.BaseSearchAndEditPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.DisplayNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit.GetNewEntityCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.filter.RESTFilterV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The presenter used to display the list of filter's and their details.
 */
public abstract class BaseSearchFilterResultsAndFilterPresenter extends BaseSearchAndEditPresenter<
        RESTFilterV1,
        RESTFilterCollectionV1,
        RESTFilterCollectionItemV1,
        RESTFilterV1BasicDetailsEditor> {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseSearchFilterResultsAndFilterPresenter.class.getName());

    @Inject
    private SearchFilterPresenter searchFilterPresenter;
    /**
     * The display.
     */
    @Inject
    private Display display;

    /**
     * @return The display
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * @return The display
     */
    @NotNull
    public BaseSearchFilterFilteredResultsPresenter.Display getFilteredResultsDisplay() {
        return getSearchFilterFilteredResultsPresenter().getDisplay();
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        LOGGER.log(Level.INFO, "ENTER BaseSearchFilterResultsAndFilterPresenter.parseToken()");
    }

    @Override
    public void close() {

    }

    @Override
    public void bindSearchAndEditExtended(@NotNull final String queryString) {
        /* A call back used to get a fresh copy of the entity that was selected */
        @NotNull final GetNewEntityCallback<RESTFilterV1> getNewEntityCallback = new GetNewEntityCallback<RESTFilterV1>() {

            @Override
            public void getNewEntity(@NotNull final RESTFilterV1 selectedEntity, @NotNull final DisplayNewEntityCallback<RESTFilterV1> displayCallback) {

                try {
                    LOGGER.log(Level.INFO, "ENTER BaseSearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");

                    final RESTCallBack<RESTFilterV1> callback = new RESTCallBack<RESTFilterV1>() {
                        @Override
                        public void success(@NotNull final RESTFilterV1 retValue) {
                            try {
                                LOGGER.log(Level.INFO, "ENTERSearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");

                                checkArgument(retValue.getFilterCategories_OTM() != null, "The initially retrieved entity should have an expanded filter categories collection");
                                checkArgument(retValue.getFilterFields_OTM() != null, "The initially retrieved entity should have an expanded filter fields collection");
                                checkArgument(retValue.getFilterTags_OTM() != null, "The initially retrieved entity should have an expanded filter tags collection");
                                checkArgument(retValue.getFilterLocales_OTM() != null, "The initially retrieved entity should have an expanded filter locales collection");

                                checkArgument(retValue.getFilterTags_OTM().getItems().size() == 0 || retValue.getFilterTags_OTM().getItems().get(0).getItem().getTag() != null,
                                        "The initially retrieved entity should have an expanded filter tags collection, and the filter tags should have an expanded tag reference");

                                checkArgument(retValue.getFilterCategories_OTM().getItems().size() == 0 || retValue.getFilterCategories_OTM().getItems().get(0).getItem().getCategory() != null,
                                        "The initially retrieved entity should have an expanded filter categories collection, and the filter tags should have an expanded category reference");

                                displayCallback.displayNewEntity(retValue);
                            } finally {
                                LOGGER.log(Level.INFO, "EXIT BaseSearchFilterResultsAndFilterPresenter.go() RESTCallback.doSuccessAction()");
                            }
                        }
                    };

                    getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getFilter(selectedEntity.getId()), callback, display);
                } finally {
                    LOGGER.log(Level.INFO, "EXIT BaseSearchFilterResultsAndFilterPresenter.go() GetNewEntityCallback.getNewEntity()");
                }
            }
        };

        searchFilterPresenter.bindExtended();
        getSearchFilterFilteredResultsPresenter().bindExtendedFilteredResults(queryString);
        super.bindSearchAndEdit(
                Preferences.FILTER_VIEW_MAIN_SPLIT_WIDTH,
                searchFilterPresenter.getDisplay(),
                searchFilterPresenter.getDisplay(),
                getSearchFilterFilteredResultsPresenter().getDisplay(),
                getSearchFilterFilteredResultsPresenter(),
                display,
                display,
                getNewEntityCallback);
    }

    @Override
    protected void bindActionButtons() {

    }

    @Override
    protected void bindFilteredResultsButtons() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void loadAdditionalDisplayedItemData() {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchFilterResultsAndFilterPresenter.loadAdditionalDisplayedItemData()");

            /*
                When a filter is selected, the load and overwrite buttons are displayed.
            */
            display.getLoad().setEnabled(true);
            display.getLoadAndSearch().setEnabled(true);
            display.getOverwrite().setEnabled(true);

        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchFilterResultsAndFilterPresenter.loadAdditionalDisplayedItemData()");
        }
    }

    @Override
    protected void initializeViews(@Nullable final List<BaseTemplateViewInterface> filter) {
        try {
            LOGGER.log(Level.INFO, "ENTER BaseSearchFilterResultsAndFilterPresenter.initializeViews()");

            checkState(getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem() != null,
                    "There should be a displayed collection item.");
            checkState(getSearchFilterFilteredResultsPresenter().getProviderData().getDisplayedItem().getItem() != null,
                    "The displayed collection item to reference a valid entity.");

            if (viewIsInFilter(filter, this.searchFilterPresenter.getDisplay())) {
                searchFilterPresenter.getDisplay().display(getSearchFilterFilteredResultsPresenter().getProviderData()
                        .getDisplayedItem().getItem(), true);
            }
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseSearchFilterResultsAndFilterPresenter.initializeViews()");
        }
    }

    /**
     * The presenter used to display the filter's details.
     */
    public SearchFilterPresenter getSearchFilterPresenter() {
        return searchFilterPresenter;
    }

    public void setSearchFilterPresenter(@NotNull final SearchFilterPresenter searchFilterPresenter) {
        this.searchFilterPresenter = searchFilterPresenter;
    }

    /**
     * The presenter used to display the list of filters.
     */
    public abstract BaseSearchFilterFilteredResultsPresenter getSearchFilterFilteredResultsPresenter();

    /**
     * The interface that defines the view that this presenter displays.
     */
    public interface Display extends BaseSearchAndEditViewInterface<
            RESTFilterV1,
            RESTFilterCollectionV1,
            RESTFilterCollectionItemV1> {
        PushButton getCreate();

        PushButton getOverwrite();

        PushButton getLoad();

        PushButton getLoadAndSearch();
    }
}
