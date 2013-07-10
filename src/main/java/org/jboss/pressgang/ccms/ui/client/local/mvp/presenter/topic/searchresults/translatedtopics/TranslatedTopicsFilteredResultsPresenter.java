package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTranslatedTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.EntityListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.StringListLoaded;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

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

    private final Map<String, String> localeQueries = new TreeMap<String, String>();
    private final Map<PushButton, Label> tabButtonsAndLabels = new HashMap<PushButton, Label>();
    private final StringBuilder commonQuery = new StringBuilder();
    private PushButton lastLocaleClicked;

    @Inject
    private HandlerManager eventBus;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtendedFilteredResults(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    @Override
    public void close() {

    }

    public void bindExtendedFilteredResults(final int topicId, @NotNull final String pageId, @NotNull final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        this.queryString = queryString;

        FailOverRESTCallDatabase.populateLocales(new StringListLoaded() {
            @Override
            public void stringListLoaded(@NotNull final List<String> locales) {

                /* Get the common query and locales that will make up the grouped results */
                breakDownQuery(locales);

                /*
                    Add "tabs" for the locales
                */
                boolean first = true;
                for (final String locale : localeQueries.keySet()) {
                    final PushButton localeTab = UIUtilities.createTopTabPushButton(locale);
                    final Label localeTabDown = UIUtilities.createTopTabDownLabel(locale);
                    tabButtonsAndLabels.put(localeTab, localeTabDown);

                    display.addActionButton(localeTab, display.getTabPanel());

                    @NotNull final ClickHandler clickHandler = new ClickHandler() {
                        @Override
                        public void onClick(final ClickEvent event) {
                            displayLocaleResults(localeQueries.get(locale));

                            for (final PushButton tab : tabButtonsAndLabels.keySet()) {
                                display.replaceTopActionButton(tabButtonsAndLabels.get(tab), tab, display.getTabPanel());
                            }

                            display.replaceTopActionButton(localeTab, localeTabDown, display.getTabPanel());
                        }
                    };

                    localeTab.addClickHandler(clickHandler);

                    /*
                        Load the first locale by default
                     */
                    if (first) {
                        clickHandler.onClick(null);
                    }

                    first = false;
                }

            }
        }, display);
    }

    private void displayLocaleResults(@Nullable final String localeSearch) {
        /* Initially we display the results from the first locale */
        @NotNull final StringBuilder initialQuery = new StringBuilder(commonQuery.toString());
        if (localeSearch != null) {
            initialQuery.append(";").append(localeSearch);
        }

        if (display.getProvider() != null) {
            display.getProvider().resetProvider();
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

        @NotNull final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
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
        @NotNull final EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTranslatedTopicCollectionItemV1>() {
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

                FailOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTranslatedTopicsFromQuery(queryString, getProviderData().getStartRow(), end), callback, display);
            }
        };
        return provider;
    }

    /**
     * Break down the query into individual locales
     */
    private void breakDownQuery(@NotNull final List<String> locales) {

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
            for (final String locale : locales) {

                boolean isExcluded = false;
                for (@NotNull final String query : excludedLocales) {
                    final String excludedLocale = query.split("=")[1].substring(0, query.split("=")[1].length() - 1);
                    if (excludedLocale.equals(locale)) {
                        isExcluded = true;
                        break;
                    }
                }

                if (!isExcluded) {
                    localeQueries.put(locale, CommonFilterConstants.MATCH_LOCALE + "1=" + locale + "1");
                }
            }
        }
    }

}
