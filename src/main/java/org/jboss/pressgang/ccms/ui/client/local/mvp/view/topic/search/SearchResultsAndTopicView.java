package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * The view that combines the topic search results with the individual topic views 
 * @author Matthew Casperson
 */
public class SearchResultsAndTopicView extends
        BaseSearchAndEditView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchResultsAndTopicPresenter.Display {

   
    
    /** An instance of the message log dialog box */
    private final LogMessageView messageLogDialog = new LogMessageView();
    
    /** The type of split used to display the rendered XML */
    private SplitType splitType = SplitType.NONE;

    @Override
    public LogMessageView getMessageLogDialog() {
        return messageLogDialog;
    }

    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    public SearchResultsAndTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
    }

    /**
     * The split panel needs to have the center widget added last, which we need to do after optionally added a east or south
     * widget for the rendered view.
     * 
     * @param splitType How the parent panel should be split
     * @param panel The rendered view panel itself
     */
    @Override
    public void initialize(final SplitType splitType, final Panel panel) {
        this.splitType = splitType;

        getSplitPanel().clear();

        getSplitPanel().addWest(this.getResultsViewLayoutPanel(), Constants.SPLIT_PANEL_SIZE);

        final SimplePanel renderedPanelParent = new SimplePanel();
        renderedPanelParent.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);
        renderedPanelParent.add(panel);

        if (splitType == SplitType.HORIZONTAL) {
            getSplitPanel().addSouth(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        } else if (splitType == SplitType.VERTICAL) {
            getSplitPanel().addEast(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        }

        getSplitPanel().add(this.getViewLayoutPanel());
    }
}
