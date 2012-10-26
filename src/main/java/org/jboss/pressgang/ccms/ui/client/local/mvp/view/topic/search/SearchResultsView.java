package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.cellview.client.TextColumn;

public class SearchResultsView extends BaseFilteredResultsView<RESTTopicCollectionItemV1> implements SearchResultsPresenter.Display {
    
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

    public SearchResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());       
    }
}
