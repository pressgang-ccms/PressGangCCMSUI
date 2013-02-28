package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

@Dependent
public class SearchResultsView extends BaseFilteredResultsView<RESTTopicCollectionItemV1>
        implements TopicFilteredResultsPresenter.Display {

    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> idColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();
        }
    };

    @NotNull
    private final TextColumn<RESTTopicCollectionItemV1> titleColumn = new TextColumn<RESTTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getTitle();
        }
    };

    public SearchResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults(), PressGangCCMSUI.INSTANCE.CreateTopic());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());
        
        /* Unlike every other results view, the topic results don't have a search button */
        this.getEntitySearch().removeFromParent();
    }
}
