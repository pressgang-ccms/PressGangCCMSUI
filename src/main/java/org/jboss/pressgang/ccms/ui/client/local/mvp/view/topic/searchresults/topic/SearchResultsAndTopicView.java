package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import java.util.logging.Logger;

/**
 * The view that combines the topic search results with the individual topic views
 *
 * @author Matthew Casperson
 */
public class SearchResultsAndTopicView extends
        BaseSearchResultsAndTopicView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchResultsAndTopicPresenter.Display {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SearchResultsAndTopicView.class.getName());
    /**
     * The save button.
     */
    private final PushButton save;
    /**
     * The revisions button.
     */
    private final PushButton history;
    /**
     * The label used to represent the history button in a down state.
     */
    private final Label historyDown;

    @Override
    public Label getHistoryDown() {
        return historyDown;
    }

    @Override
    public PushButton getHistory() {
        return history;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    public SearchResultsAndTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

        /* Build the action bar icons */
        save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
        history = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Revisions());

        historyDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Revisions());

        this.addActionButton(history);
        this.addActionButton(save);
    }

}
