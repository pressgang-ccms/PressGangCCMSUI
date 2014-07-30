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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * The presenter used to add logic to the category filtered list view.
 */
@Dependent
public class CategoryFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTCategoryCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    /**
     * This history token.
     */
    public static final String HISTORY_TOKEN = "CategoryFilteredResultsView";
    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CategoryFilteredResultsPresenter.class.getName());

    /**
     * The display used to show the list of categories.
     */
    @Inject
    private Display display;
    /**
     * The query string used to return the list of categories.
     */
    private String queryString;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public CategoryFilteredResultsPresenter() {

    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    public void bindExtendedFilteredResults(@NotNull final String queryString) {
        try {
            LOGGER.log(Level.INFO, "ENTER CategoryFilteredResultsPresenter.bind()");
            super.bindFilteredResults(queryString, display);
            display.setProvider(generateListProvider(queryString, display));
        } finally {
            LOGGER.log(Level.INFO, "EXIT CategoryFilteredResultsPresenter.bind()");
        }
    }

    @Override
    @NotNull
    protected EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateListProvider(@NotNull final String queryString,
            @NotNull final BaseTemplateViewInterface waitDisplay) {
        return new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTCategoryCollectionItemV1> list) {

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                final RESTCallBack<RESTCategoryCollectionV1> callback = new RESTCallBack<RESTCategoryCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTCategoryCollectionV1 retValue) {
                        checkState(retValue.getItems() != null, "There returned collection should have a valid items collection.");
                        checkState(retValue.getSize() != null, "There returned collection should have a valid size collection.");

                        getProviderData().setItems(retValue.getItems());
                        getProviderData().setSize(retValue.getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getCategoriesFromQuery(queryString, getProviderData().getStartRow(), end),
                        callback, display);
            }
        };
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.CATEGORY_IDS_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getIdFilter().getText()));
        }
        if (!display.getNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.CATEGORY_NAME_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getNameFilter().getText()));
        }
        if (!display.getDescriptionFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.CATEGORY_DESCRIPTION_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getDescriptionFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON +
                retValue.toString();
    }

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (@NotNull final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals(CommonFilterConstants.CATEGORY_IDS_FILTER_VAR)) {
                    this.display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.CATEGORY_NAME_FILTER_VAR)) {
                    this.display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.CATEGORY_DESCRIPTION_FILTER_VAR)) {
                    this.display.getDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }
    }

    /**
     * The interface used to define the category filtered list view.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTCategoryCollectionItemV1> {

        /**
         * @return The fields used to specify the category ids filter
         */
        TextBox getIdFilter();

        /**
         * @return The fields used to specify the category description filter
         */
        TextBox getDescriptionFilter();

        /**
         * @return The fields used to specify the category name filter
         */
        TextBox getNameFilter();
    }
}
