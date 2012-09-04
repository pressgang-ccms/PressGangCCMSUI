package org.jboss.pressgangccms.client.local.mvp.view;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchResultsAndTopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class ImagesFilteredResultsAndImageView extends BaseTemplateView implements ImagesFilteredResultsAndImagePresenter.Display
{
	public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";
	private final HanldedSplitLayoutPanel splitPanel = new HanldedSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
	private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
	private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
	private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel resultsActionButtonsPanel = new SimpleLayoutPanel();
	private final SimpleLayoutPanel viewActionButtonsPanel = new SimpleLayoutPanel();

	@Override
	public DockLayoutPanel getViewLayoutPanel()
	{
		return viewLayoutPanel;
	}

	@Override
	public HanldedSplitLayoutPanel getSplitPanel()
	{
		return splitPanel;
	}

	@Override
	public SimpleLayoutPanel getResultsActionButtonsPanel()
	{
		return resultsActionButtonsPanel;
	}

	@Override
	public SimpleLayoutPanel getResultsPanel()
	{
		return resultsPanel;
	}

	@Override
	public SimpleLayoutPanel getViewPanel()
	{
		return viewPanel;
	}

	@Override
	public SimpleLayoutPanel getViewActionButtonsPanel()
	{
		return viewActionButtonsPanel;
	}

	public ImagesFilteredResultsAndImageView()
	{
		super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());

		/* We have own own top action panels */
		this.getTopActionParentPanel().removeFromParent();
		
		final HTML spacer = new HTML("<div style=\"height: " + Constants.ACTION_BAR_HEIGHT + "px;\"></div>");		
		this.getShortcutPanel().insert(spacer, 0);

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
}
