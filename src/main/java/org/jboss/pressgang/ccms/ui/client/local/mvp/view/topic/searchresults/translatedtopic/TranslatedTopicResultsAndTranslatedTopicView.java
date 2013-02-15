package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.translatedtopic;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.translatedtopics.TranslatedTopicResultsAndTranslatedTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

/**
    Displays the translated topics results and topic details. This class does not add anything over the base class, and
    exists as a seperate class to satisfy injection.
 */
public class TranslatedTopicResultsAndTranslatedTopicView extends BaseSearchResultsAndTopicView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1>
        implements TranslatedTopicResultsAndTranslatedTopicPresenter.Display {

    public TranslatedTopicResultsAndTranslatedTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
    }

    @Override
    protected void postPopulateTopActionBar() {
        /*
            Do nothing.
         */
    }
}
