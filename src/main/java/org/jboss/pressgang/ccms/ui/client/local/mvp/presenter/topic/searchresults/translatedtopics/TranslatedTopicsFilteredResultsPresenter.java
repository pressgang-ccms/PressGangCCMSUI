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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTLocaleCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerSettingsV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTLocaleV1;
import org.jboss.pressgang.ccms.ui.client.local.callbacks.ServerSettingsCallback;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.sort.RESTLocaleV1Sort;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class TranslatedTopicsFilteredResultsPresenter extends BaseFilteredResultsPresenter<RESTTranslatedTopicCollectionItemV1> implements BaseTemplatePresenterInterface {

    public interface Display extends BaseFilteredResultsViewInterface<RESTTranslatedTopicCollectionItemV1> {

    }

    public static final String HISTORY_TOKEN = "TranslatedTopicsFilteredResultsView";

    /**
     * A regex that identifies a local option
     */
    private static final String LOCALE_REGEX_STRING = CommonFilterConstants.MATCH_LOCALE + "\\d+=.*?[01]";
    /**
     * The compiled LOCALE_REGEX_STRING regex.
     */
    private static final RegExp LOCALE_REGEX = RegExp.compile(LOCALE_REGEX_STRING);

    @Inject
    private Display display;

    private String queryString;


    private final StringBuilder commonQuery = new StringBuilder();

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

    public void bindExtendedFilteredResults(@NotNull final String queryString) {
        super.bindFilteredResults(queryString, display);
        this.queryString = queryString;

        getServerSettings(new ServerSettingsCallback() {
            @Override
            public void serverSettingsLoaded(@NotNull final RESTServerSettingsV1 serverSettings, RESTLocaleCollectionV1 locales) {
                final Map<PushButton, Label> tabButtonsAndLabels = new HashMap<PushButton, Label>();
                // Get the common query and locales that will make up the grouped results
                final List<RESTLocaleV1> localesList = locales.returnItems();
                Collections.sort(localesList, new RESTLocaleV1Sort());
                final Map<String, String> localeQueries = breakDownQuery(localesList);

                // Add "tabs" for the locales
                boolean first = true;
                for (final String locale : localeQueries.keySet()) {
                    final PushButton localeTab = UIUtilities.createTopTabPushButton(locale);
                    final Label localeTabDown = UIUtilities.createTopTabDownLabel(locale);
                    tabButtonsAndLabels.put(localeTab, localeTabDown);

                    display.addActionButton(localeTab);

                    final ClickHandler clickHandler = new ClickHandler() {
                        @Override
                        public void onClick(final ClickEvent event) {
                            displayLocaleResults(localeQueries.get(locale));

                            for (final PushButton tab : tabButtonsAndLabels.keySet()) {
                                display.replaceTopActionButton(tabButtonsAndLabels.get(tab), tab);
                            }

                            display.replaceTopActionButton(localeTab, localeTabDown);
                        }
                    };

                    localeTab.addClickHandler(clickHandler);

                    // Load the first locale by default
                    if (first) {
                        clickHandler.onClick(null);
                    }

                    first = false;
                }
            }
        });
    }

    private void displayLocaleResults(@Nullable final String localeSearch) {
        /* Initially we display the results from the first locale */
        final StringBuilder initialQuery = new StringBuilder(commonQuery.toString());
        if (localeSearch != null) {
            initialQuery.append(";").append(localeSearch);
        }

        if (display.getProvider() != null) {
            display.getProvider().resetProvider(false);
        }

        display.setProvider(generateListProvider(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + initialQuery.toString(), display));
    }

    @NotNull
    @Override
    public String getQuery() {
        return queryString;
    }

    @Override
    protected void displayQueryElements(@NotNull final String queryString) {

    }

    @NotNull
    protected EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> generateListProvider() {
        getProviderData().resetToEmpty();

        final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTranslatedTopicCollectionItemV1> list) {

                checkState(getProviderData().getItems() != null, "The provider data should have items.");

                displayNewFixedList(getProviderData().getItems());
            }
        };
        return provider;
    }

    @NotNull
    @Override
    protected EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTranslatedTopicCollectionItemV1> list) {

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                final RESTCallBack<RESTTranslatedTopicCollectionV1> callback = new RESTCallBack<RESTTranslatedTopicCollectionV1>() {
                    @Override
                    public void success(@NotNull final RESTTranslatedTopicCollectionV1 retValue) {
                        try {
                            checkState(retValue.getItems() != null, "The returned collection data should have items.");
                            checkState(retValue.getSize() != null, "The returned collection data should have a size.");
                            checkState(getProviderData().getStartRow() != null, "The data provider should have a starting row.");

                            getProviderData().setItems(retValue.getItems());
                            getProviderData().setSize(retValue.getSize());
                            relinkSelectedItem();
                            displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                        } finally {
                            getHandlerManager().fireEvent(new EntityListReceived<RESTTranslatedTopicCollectionV1>(retValue));
                        }
                    }

                    @Override
                    public void failed() {
                        getProviderData().setItems(new ArrayList<RESTTranslatedTopicCollectionItemV1>());
                        getProviderData().setSize(0);
                        getProviderData().setStartRow(0);
                        displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTranslatedTopicsFromQuery(queryString, getProviderData().getStartRow(),
                        end), callback, display);
            }
        };
        return provider;
    }

    /**
     * Break down the query into individual locales
     */
    private Map<String, String> breakDownQuery(@NotNull final List<RESTLocaleV1> locales) {

        final Map<String, String> localeQueries = new TreeMap<String, String>();
        final String queryWithoutPrefix = queryString.replaceFirst(Constants.QUERY_PATH_SEGMENT_PREFIX, "");
        final String[] queryOptions = queryWithoutPrefix.split(";");

        @NotNull final List<String> includedLocales = new ArrayList<String>();
        @NotNull final List<String> excludedLocales = new ArrayList<String>();

        for (@NotNull final String queryOption : queryOptions) {
            if (LOCALE_REGEX.test(queryOption)) {
                if (queryOption.endsWith("0")) {
                    /* If the local query ends with a 0, it is an excluded locale */
                    excludedLocales.add(queryOption);
                } else {
                    /* If the local query ends with a 1, it is an included locale */
                    includedLocales.add(queryOption);
                }
            } else {
                commonQuery.append(";" + queryOption);
            }
        }

        if (!includedLocales.isEmpty()) {
            for (@NotNull final String query : includedLocales) {
                /* extract the locale from the query variable */
                localeQueries.put(query.split("=")[1].substring(0, query.split("=")[1].length() - 1), query);
            }
        } else {
            /*
                Add a query for every locale, except those that were excluded
             */
            for (final RESTLocaleV1 locale : locales) {

                boolean isExcluded = false;
                for (@NotNull final String query : excludedLocales) {
                    final String excludedLocale = query.split("=")[1].substring(0, query.split("=")[1].length() - 1);
                    if (excludedLocale.equals(locale)) {
                        isExcluded = true;
                        break;
                    }
                }

                if (!isExcluded) {
                    localeQueries.put(locale.getValue(), CommonFilterConstants.MATCH_LOCALE + "1=" + locale.getValue() + "1");
                }
            }
        }

        return localeQueries;
    }

}
