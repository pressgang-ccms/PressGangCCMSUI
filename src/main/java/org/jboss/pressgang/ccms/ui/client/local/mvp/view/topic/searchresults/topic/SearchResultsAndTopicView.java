package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.topic;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.searchresults.topics.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.searchresults.base.BaseSearchResultsAndTopicView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view that combines the topic search results with the individual topic views 
 * @author Matthew Casperson
 */
public class SearchResultsAndTopicView extends
        BaseSearchResultsAndTopicView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchResultsAndTopicPresenter.Display {

    private final PushButton save;
    private final PushButton history;

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
    }


    /**
     * This method enables or disables the save button based on the read only state, and also highlights the history button if
     * needed.
     */
    @Override
    protected void postPopulateTopActionBar() {
        this.getSave().setEnabled(!readOnly);

        if (readOnly) {
            this.getHistory().addStyleName(CSSConstants.ALERT_BUTTON);
        } else {
            this.getHistory().removeStyleName(CSSConstants.ALERT_BUTTON);
        }
    }
}
