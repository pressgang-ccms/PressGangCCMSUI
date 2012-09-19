package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topicsearch;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topicsearch.SearchResultsPresenter;
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
    private final CellTable<RESTTopicV1> results = new CellTable<RESTTopicV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private EnhancedAsyncDataProvider<RESTTopicV1> provider;
    private final TextColumn<RESTTopicV1> idColumn = new TextColumn<RESTTopicV1>() {
        @Override
        public String getValue(final RESTTopicV1 object) {
            return object.getId().toString();

        }
    };

    private final TextColumn<RESTTopicV1> titleColumn = new TextColumn<RESTTopicV1>() {
        @Override
        public String getValue(RESTTopicV1 object) {
            return object.getTitle();
        }
    };

    @Override
    public EnhancedAsyncDataProvider<RESTTopicV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(EnhancedAsyncDataProvider<RESTTopicV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    @Override
    public CellTable<RESTTopicV1> getResults() {
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

        searchResultsPanel.addStyleName(CSSConstants.SEARCHRESULTSPANEL);

        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    @Override
    protected void showWaiting() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void hideWaiting() {
        // TODO Auto-generated method stub
        
    }

}