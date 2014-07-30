/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

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

    private final TextColumn<RESTTranslatedTopicCollectionItemV1> revisionColumn = new TextColumn<RESTTranslatedTopicCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTTranslatedTopicCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getTopicRevision().toString();
        }
    };

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

        /* Unlike every other results view, the translated topic results don't have a search or create button */
        this.getEntitySearch().removeFromParent();
        this.getCreate().removeFromParent();

        /*
            We don't filter search results from here
        */
        getFilterTable().removeFromParent();
    }
}
