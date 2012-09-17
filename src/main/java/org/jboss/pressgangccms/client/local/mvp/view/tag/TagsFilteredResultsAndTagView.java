package org.jboss.pressgangccms.client.local.mvp.view.tag;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.images.ImageResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class TagsFilteredResultsAndTagView extends BaseTemplateView implements TagsFilteredResultsAndTagPresenter.Display {
    public TagsFilteredResultsAndTagView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
    }

    public static final String HISTORY_TOKEN = "TagFilteredResultsAndImageView";
    private final HanldedSplitLayoutPanel splitPanel = new HanldedSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel resultsActionButtonsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewActionButtonsPanel = new SimpleLayoutPanel();
    
    /** The image to display in the waiting dialog. */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());
    /** The dialog that is presented when the view is unavailable. */
    private final DialogBox waiting = new DialogBox();

    public DockLayoutPanel getViewLayoutPanel() {
        return viewLayoutPanel;
    }

    @Override
    public HanldedSplitLayoutPanel getSplitPanel() {
        return splitPanel;
    }

    @Override
    public SimpleLayoutPanel getResultsActionButtonsPanel() {
        return resultsActionButtonsPanel;
    }

    @Override
    public SimpleLayoutPanel getResultsPanel() {
        return resultsPanel;
    }

    @Override
    public SimpleLayoutPanel getViewPanel() {
        return viewPanel;
    }

    @Override
    public SimpleLayoutPanel getViewActionButtonsPanel() {
        return viewActionButtonsPanel;
    }

    public TagsFilteredResultsAndTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());

        /* Build the please wait dialog box */
        waiting.setTitle(PressGangCCMSUI.INSTANCE.PleaseWait());
        waiting.add(spinner);
        
        /* We have own own top action panels */
        this.getTopActionParentPanel().removeFromParent();

        addSpacerToShortcutPanels();

        resultsViewLayoutPanel.addStyleName(CSSConstants.RESULTSVIEWLAYOUTPANEL);
        viewLayoutPanel.addStyleName(CSSConstants.TOPICVIEWLAYOUTPANEL);

        resultsViewLayoutPanel.addNorth(resultsActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);
        viewLayoutPanel.addNorth(viewActionButtonsPanel, Constants.ACTION_BAR_HEIGHT);

        resultsViewLayoutPanel.add(resultsPanel);
        viewLayoutPanel.add(viewPanel);

        splitPanel.addWest(resultsViewLayoutPanel, Constants.SPLIT_PANEL_SIZE);

        viewActionButtonsPanel.addStyleName(CSSConstants.TOPICSEARCHTOPICVIEWBUTTONSPANEL);
        viewPanel.addStyleName(CSSConstants.TOPICSEARCHTOPICVIEWDETAILSPANEL);

        splitPanel.addStyleName(CSSConstants.TOPICSEARCHRESULTSANDVIEWPARENTPANEL);

        splitPanel.add(viewLayoutPanel);

        this.getPanel().add(splitPanel);
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
