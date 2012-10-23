package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.create;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create.CreateTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class CreateTopicView extends BaseTemplateView implements CreateTopicPresenter.Display {

    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();
    
    /** The current split type */
    private SplitType splitType = SplitType.NONE;
    
    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel topicViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel topicViewPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel topicViewActionButtonsPanel = new SimpleLayoutPanel();
    
    public SimpleLayoutPanel getTopicViewActionButtonsPanel() {
        return topicViewActionButtonsPanel;
    }

    public DockLayoutPanel getTopicViewLayoutPanel() {
        return topicViewLayoutPanel;
    }

    public HandlerSplitLayoutPanel getSplitPanel() {
        return splitPanel;
    }
    

    public SimpleLayoutPanel getTopicViewPanel() {
        return topicViewPanel;
    }

    public CreateTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.CreateTopic());
        
        /* We have own own top action panels */
        this.getTopActionParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        topicViewLayoutPanel.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);

        topicViewLayoutPanel.addNorth(topicViewActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);
        topicViewLayoutPanel.add(topicViewPanel);

        topicViewActionButtonsPanel.addStyleName(CSSConstants.TOPIC_SEARCH_TOPIC_VIEW_BUTTONS_PANEL);
        topicViewPanel.addStyleName(CSSConstants.TOPIC_SEARCH_TOPIC_VIEW_DETAILS_PANEL);

        splitPanel.addStyleName(CSSConstants.TOPIC_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL);

        this.getPanel().add(splitPanel);
    }
    
    public void initialize(final SplitType splitType, final Panel panel)
    {
        this.splitType = splitType;
        
        splitPanel.clear();

        final SimplePanel renderedPanelParent = new SimplePanel();
        renderedPanelParent.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);
        renderedPanelParent.add(panel);

        if (splitType == SplitType.HORIZONTAL) {
            splitPanel.addSouth(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        } else if (splitType == SplitType.VERTICAL) {
            splitPanel.addEast(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        }

        splitPanel.add(topicViewLayoutPanel);
    }
    
    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();        
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();        
    }

}

