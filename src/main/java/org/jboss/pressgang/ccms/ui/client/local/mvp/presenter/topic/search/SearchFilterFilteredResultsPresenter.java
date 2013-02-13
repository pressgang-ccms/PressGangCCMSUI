package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTFilterCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTFilterCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.dataevents.TopicListReceived;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * The presenter used to display the list of search filters.
 */
@Dependent
public class SearchFilterFilteredResultsPresenter extends BaseFilteredResultsComponent<RESTFilterV1, RESTFilterCollectionV1, RESTFilterCollectionItemV1> {

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseFilteredResultsViewInterface<RESTFilterV1, RESTFilterCollectionV1, RESTFilterCollectionItemV1> {
        PushButton getSearchTopics();
        PushButton getTagsSearch();
        PushButton getFields();
    }

    /**
     * The history token.
     */
    public static final String HISTORY_TOKEN = "SearchFilterFilteredResultsView";

    /** The display. */
    @Inject private Display display;

    /**
     * @return The display.
     */
    public final Display getDisplay() {
        return display;
    }

    @Override
    protected final EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> generateListProvider(@NotNull final String queryString, @NotNull final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTFilterCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTFilterCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTFilterCollectionItemV1> list) {

                final RESTCalls.RESTCallback<RESTFilterCollectionV1> callback = new BaseRestCallback<RESTFilterCollectionV1, Display>(
                        display,
                        new BaseRestCallback.SuccessAction<RESTFilterCollectionV1, Display>() {
                            @Override
                            public void doSuccessAction(final RESTFilterCollectionV1 retValue, final Display display) {
                                getProviderData().setItems(retValue.getItems());
                                getProviderData().setSize(retValue.getSize());
                                relinkSelectedItem();
                                displayAsynchronousList(getProviderData().getItems(), getProviderData().getSize(), getProviderData().getStartRow());
                            }
                    });

                getProviderData().setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = getProviderData().getStartRow() + length;

                RESTCalls.getFiltersFromQuery(callback, queryString, getProviderData().getStartRow(), end);
            }
        };
        return provider;
    }

    @Override
    public final String getQuery() {
        return null;
    }

    @Override
    public final void bindExtendedFilteredResults(final int topicId, final String pageId, final String queryString) {
        super.bindFilteredResults(topicId, pageId, queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    public final void go(final HasWidgets container) {
        bindExtendedFilteredResults(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, "");
    }

    @Override
    public final void parseToken(final String historyToken) {

    }

    @Override
    protected final void displayQueryElements(@NotNull final String queryString) {

    }
}
