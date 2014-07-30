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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTTextContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTTextContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class ContentSpecFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTTextContentSpecCollectionItemV1>
        implements BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTTextContentSpecCollectionItemV1> {
        //@NotNull PushButton getBulkImport();
        //@NotNull PushButton getBulkOverwrite();
    }

    public static final String HISTORY_TOKEN = "ContentSpecFilteredResultsView";

    @Inject
    private Display display;

    @Nullable
    private String queryString;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    public ContentSpecFilteredResultsPresenter() {

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

    public void bindExtendedFilteredResults(@Nullable final String queryString) {
        super.bindFilteredResults(queryString, display);
        this.queryString = queryString;

        if (queryString == null) {
            display.setProvider(generateListProvider());
        } else {
            display.setProvider(generateListProvider(queryString, display));
        }
    }

    @NotNull
    @Override
    public String getQuery() {
        return queryString;
    }

    @Override
    protected void displayQueryElements(@Nullable final String queryString) {
        // TODO Auto-generated method stub
    }

    @NotNull
    protected EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> generateListProvider() {
        getProviderData().resetToEmpty();

        @NotNull final EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTextContentSpecCollectionItemV1> list) {
                displayNewFixedList(getProviderData().getItems());
            }
        };
        return provider;
    }

    @Nullable
    @Override
    protected EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> generateListProvider(@Nullable final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {

        if (queryString == null) {
            return null;
        }

        final EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTextContentSpecCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTextContentSpecCollectionItemV1> list) {

                final RESTCallBack<RESTTextContentSpecCollectionV1> callback = new RESTCallBack<RESTTextContentSpecCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTTextContentSpecCollectionV1 retValue) {
                        try {
                            checkArgument(retValue.getItems() != null, "Returned collection should have a valid items collection.");
                            checkArgument(retValue.getSize() != null, "Returned collection should have a valid size.");

                            for (final RESTTextContentSpecCollectionItemV1 item : retValue.getItems()) {
                                ComponentContentSpecV1.fixDisplayedText(item.getItem());
                            }

                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        } finally {
                            getHandlerManager().fireEvent(new EntityListReceived<RESTTextContentSpecCollectionV1>(retValue));
                        }
                    }
                };

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getContentSpecsFromQuery(queryString, getProviderData().getStartRow(), end),
                        callback, display);
            }
        };
        return provider;
    }

}
