package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.user.cellview.client.TextColumn;

public class SearchResultsView extends BaseFilteredResultsView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1>
        implements SearchResultsPresenter.Display {

    private final TextColumn<RESTTopicCollectionItemV1> idColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            if (object == null)
                return null + "";
            return object.getItem().getId().toString();
        }
    };

    private final TextColumn<RESTTopicCollectionItemV1> titleColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        public String getValue(final RESTTopicCollectionItemV1 object) {
            if (object == null)
                return null + "";
            return object.getItem().getTitle();
        }
    };

    public SearchResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());
        
        /* Unlike every other results view, the topic results don't have a search or create button */
        this.getSearch().removeFromParent();
        this.getCreate().removeFromParent();
    }
}
