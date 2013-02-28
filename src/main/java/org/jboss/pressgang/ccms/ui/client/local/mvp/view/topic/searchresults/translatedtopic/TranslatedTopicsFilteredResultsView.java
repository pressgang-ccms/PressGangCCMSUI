package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.translatedtopic;

import com.google.gwt.user.cellview.client.TextColumn;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTranslatedTopicCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics.TranslatedTopicsFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

/**
 * This class defines the view for the translated topics filtered results.
 */
@Dependent
public class TranslatedTopicsFilteredResultsView extends BaseFilteredResultsView<RESTTranslatedTopicCollectionItemV1>
        implements TranslatedTopicsFilteredResultsPresenter.Display {

    @NotNull
    private final TextColumn<RESTTranslatedTopicCollectionItemV1> idColumn = new TextColumn<RESTTranslatedTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTranslatedTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getTopicId().toString();
        }
    };

    @NotNull
    private final TextColumn<RESTTranslatedTopicCollectionItemV1> revisionColumn = new TextColumn<RESTTranslatedTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTranslatedTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getRevision().toString();
        }
    };

    @NotNull
    private final TextColumn<RESTTranslatedTopicCollectionItemV1> titleColumn = new TextColumn<RESTTranslatedTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTranslatedTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getTitle();
        }
    };



    public TranslatedTopicsFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults(), PressGangCCMSUI.INSTANCE.CreateTopic());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TopicID());
        getResults().addColumn(revisionColumn, PressGangCCMSUI.INSTANCE.TopicRevision());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.TopicTitle());

        
        /* remove the search and create buttons */
        this.getTopActionPanel().removeAllRows();
    }
}
