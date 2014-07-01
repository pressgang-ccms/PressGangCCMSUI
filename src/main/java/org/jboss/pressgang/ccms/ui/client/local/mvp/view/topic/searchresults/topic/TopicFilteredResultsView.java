package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.TopicFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

@Dependent
public class TopicFilteredResultsView extends BaseFilteredResultsView<RESTTopicCollectionItemV1>
        implements TopicFilteredResultsPresenter.Display {

    private final PushButton bulkImport = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.BulkTopicImport());
    private final PushButton bulkOverwrite = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.BulkTopicOverwrite());
    private final PushButton atomFeed = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ATOMFeed(), false, true, true, null);

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

    public TopicFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults(), PressGangCCMSUI.INSTANCE.CreateTopic());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());
        
        /* Unlike every other results view, the topic results don't have a search button */
        this.getEntitySearch().removeFromParent();

        addActionButton(bulkImport);
        addActionButton(bulkOverwrite);
        addActionButton(atomFeed);

        /*
            We don't filter search results from here
         */
        getFilterTable().removeFromParent();
    }

    @NotNull
    @Override
    public PushButton getBulkImport() {
        return bulkImport;
    }

    @NotNull
    @Override
    public PushButton getBulkOverwrite() {
        return bulkOverwrite;
    }

    @NotNull
    @Override
    public PushButton getAtomFeed() {
        return atomFeed;
    }
}
