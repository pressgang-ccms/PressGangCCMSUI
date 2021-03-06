/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
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

@Dependent
public class ProjectFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTProjectCollectionItemV1> implements
        BaseTemplatePresenterInterface {

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "ProjectFilteredResultsView";

    @Inject
    private Display display;
    private String queryString;

    @Override
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    protected void go() {
        bindExtendedFilteredResults(queryString);
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtendedFilteredResults(@NotNull final String queryString) {
        super.bindFilteredResults(queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    /**
     * @param waitDisplay The view used to notify the user that an on going operation is in progress
     * @return A provider to be used for the category display list
     */
    @Override
    @NotNull
    protected EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> generateListProvider(@NotNull final String queryString,
            @NotNull final BaseTemplateViewInterface waitDisplay) {
        @NotNull final EnhancedAsyncDataProvider<RESTProjectCollectionItemV1> provider = new
                EnhancedAsyncDataProvider<RESTProjectCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTProjectCollectionItemV1> list) {

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                final RESTCallBack<RESTProjectCollectionV1> callback = new RESTCallBack<RESTProjectCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTProjectCollectionV1 retValue) {
                        checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                        checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                        getProviderData().setItems(retValue.getItems());
                        getProviderData().setSize(retValue.getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getProjectsFromQuery(queryString, getProviderData().getStartRow(), end),
                        callback, display);
            }
        };
        return provider;
    }

    @Override
    @NotNull
    public String getQuery() {
        @NotNull final StringBuilder retValue = new StringBuilder();
        if (!display.getIdFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROJECT_IDS_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getIdFilter().getText()));
        }
        if (!display.getNameFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROJECT_NAME_FILTER_VAR).append("=").append(
                    encodeQueryParameter(display.getNameFilter().getText()));
        }
        if (!display.getDescriptionFilter().getText().isEmpty()) {
            retValue.append(";").append(CommonFilterConstants.PROJECT_DESCRIPTION_FILTER_VAR).append("=").append(
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
                if (queryElements[0].equals(CommonFilterConstants.PROJECT_IDS_FILTER_VAR)) {
                    display.getIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.PROJECT_NAME_FILTER_VAR)) {
                    display.getNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals(CommonFilterConstants.PROJECT_DESCRIPTION_FILTER_VAR)) {
                    display.getDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }

    }

    public interface Display extends BaseFilteredResultsViewInterface<RESTProjectCollectionItemV1> {

        TextBox getIdFilter();

        TextBox getDescriptionFilter();

        TextBox getNameFilter();
    }
}