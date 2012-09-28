package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchResultsView extends BaseTemplateView implements SearchResultsPresenter.Display {
    

    private final VerticalPanel searchResultsPanel = new VerticalPanel();

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTTopicCollectionItemV1> results = new CellTable<RESTTopicCollectionItemV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider;
    private final TextColumn<RESTTopicCollectionItemV1> idColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTTopicCollectionItemV1> titleColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            return object.getItem().getTitle();
        }
    };

    @Override
    public EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTTopicCollectionItemV1> getResults() {
        return results;
    }

    @Override
    public SimplePager getPager() {
        return pager;
    }

    public SearchResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        results.addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());

        searchResultsPanel.addStyleName(CSSConstants.SEARCH_RESULTS_PANEL);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }
}
