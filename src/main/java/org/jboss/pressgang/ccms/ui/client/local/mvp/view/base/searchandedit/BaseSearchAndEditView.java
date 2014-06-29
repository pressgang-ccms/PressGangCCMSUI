package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract public class BaseSearchAndEditView<
        T extends RESTBaseEntityV1<T, U, V>,
        U extends RESTBaseEntityCollectionV1<T, U, V>,
        V extends RESTBaseEntityCollectionItemV1<T, U, V>>
        extends BaseTemplateView implements BaseSearchAndEditViewInterface<T, U, V> {

    private final HandlerSplitLayoutPanel splitPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final DockLayoutPanel resultsViewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final DockLayoutPanel viewLayoutPanel = new DockLayoutPanel(Unit.PX);
    private final SimpleLayoutPanel resultsPanel = new SimpleLayoutPanel();
    private final SimpleLayoutPanel viewPanel = new SimpleLayoutPanel();
    /**
     * The panel that will hold the command and local action buttons from the main view and the filtered results view it is displaying
     */
    private final FlexTable resultsActionButtonsParentPanel = new FlexTable();
    /**
     * The panel that will hold the common action buttons from the main view and the filtered results view
     */
    private final HorizontalPanel resultsActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the local action buttons from the main view and the filtered results view
     */
    private final HorizontalPanel resultsViewSpecificActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the action buttons from the main view and the child view it is displaying
     */
    private final FlexTable viewActionButtonsParentPanel = new FlexTable();
    /**
     * The panel that will hold the action buttons
     */
    private final HorizontalPanel viewActionButtonsPanel = new HorizontalPanel();
    /**
     * The panel that will hold the view specific action buttons
     */
    private final HorizontalPanel viewViewSpecificActionButtonsPanel = new HorizontalPanel();
    /**
     * The scroll panel used to hold the view action buttons.
     */
    private final ScrollPanel viewActionButtonsParentPanelScroll = new ScrollPanel();
    /**
     * The scroll panel used to hold the results action buttons.
     */
    private final ScrollPanel resultsActionButtonsParentPanelScroll = new ScrollPanel();


    /**
     * If true, this view will create top action panels above the filtered results, and above
     * the entity view area. It will also pull out the entity view top action panel and
     * place it in this new view. If false, this view won't touch the top action panel, and
     * will eave it to another presenter to manage.
     * <p/>
     * The topic search view is an example where a BaseSearchAndEdit presenter is a child of
     * another presenter, and as such the child BaseSearchAndEdit does not manage the top action panel.
     * In this case, addCustomActionButtonFields is false.
     */
    final boolean addCustomActionButtonFields;

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    @NotNull
    @Override
    public DockLayoutPanel getResultsViewLayoutPanel() {
        return resultsViewLayoutPanel;
    }

    @NotNull
    public DockLayoutPanel getViewLayoutPanel() {
        return viewLayoutPanel;
    }

    @NotNull
    @Override
    public HandlerSplitLayoutPanel getSplitPanel() {
        return splitPanel;
    }

    @NotNull
    @Override
    public FlexTable getResultsActionButtonsParentPanel() {
        return resultsActionButtonsParentPanel;
    }

    @NotNull
    @Override
    public SimpleLayoutPanel getResultsPanel() {
        return resultsPanel;
    }

    @NotNull
    @Override
    public SimpleLayoutPanel getViewPanel() {
        return viewPanel;
    }

    @NotNull
    @Override
    public FlexTable getViewActionButtonsParentPanel() {
        return viewActionButtonsParentPanel;
    }

    @NotNull
    protected ScrollPanel getResultsActionButtonsParentPanelScroll() {
        return resultsActionButtonsParentPanelScroll;
    }

    public BaseSearchAndEditView(@NotNull final String applicationName, @NotNull final String pageName) {
        this(applicationName, pageName, true);
    }

    public BaseSearchAndEditView(@NotNull final String applicationName, @NotNull final String pageName, final boolean addCustomActionButtonFields) {
        super(applicationName, pageName);

        this.addCustomActionButtonFields = addCustomActionButtonFields;

        resultsViewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.RESULTS_VIEW_LAYOUT_PANEL);
        viewLayoutPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_VIEW_LAYOUT_PANEL);

        if (addCustomActionButtonFields) {
            /* We have own own top action panels */
            this.getTopActionGrandParentPanel().removeFromParent();

            viewActionButtonsParentPanelScroll.addStyleName(CSSConstants.BaseSearchAndEditView.VIEW_ACTION_BUTTONS_PARENT_PANEL_SCROLL);
            resultsActionButtonsParentPanelScroll.addStyleName(CSSConstants.BaseSearchAndEditView.RESULTS_ACTION_BUTTONS_PARENT_PANEL_SCROLL);

            viewActionButtonsParentPanelScroll.setWidget(viewActionButtonsParentPanel);
            resultsActionButtonsParentPanelScroll.setWidget(resultsActionButtonsParentPanel);

            resultsViewLayoutPanel.addNorth(resultsActionButtonsParentPanelScroll, Constants.ACTION_BAR_HEIGHT);
            viewLayoutPanel.addNorth(viewActionButtonsParentPanelScroll, Constants.ACTION_BAR_HEIGHT);
        }

        resultsViewLayoutPanel.add(resultsPanel);
        viewLayoutPanel.add(viewPanel);

        viewActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        viewViewSpecificActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PANEL);
        viewActionButtonsParentPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAG_VIEW_BUTTONS_PARENT_PANEL);

        resultsActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
        resultsViewSpecificActionButtonsPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PANEL);
        resultsActionButtonsParentPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TAGS_RESULT_BUTTONS_PARENT_PANEL);

        viewPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_TOPIC_VIEW_DETAILS_PANEL);

        splitPanel.addStyleName(CSSConstants.BaseSearchAndEditView.ENTITY_SEARCH_RESULTS_AND_VIEW_PARENT_PANEL);

        this.getPanel().add(splitPanel);
    }

    protected void initialize(final boolean displaySearchResults) {
        initialize(displaySearchResults, Constants.SPLIT_PANEL_SIZE, null);
    }

    protected void initialize(final boolean displaySearchResults, final double searchResultsWidth, @Nullable final DisplaySplitViewCallback callback) {
        splitPanel.clear();

        final double fixedSearchResultsWidth = searchResultsWidth < Constants.MINIMUM_SPLIT_SIZE ? Constants.MINIMUM_SPLIT_SIZE : searchResultsWidth;

        splitPanel.addWest(resultsViewLayoutPanel, Constants.MINIMUM_SPLIT_SIZE);
        splitPanel.setWidgetMinSize(resultsViewLayoutPanel, Constants.MINIMUM_SPLIT_SIZE);

        /*
            The size has to be set after setWidgetMinSize is called, otherwise some panels
             are smaller than expected.
         */
        splitPanel.setSplitPosition(resultsViewLayoutPanel, fixedSearchResultsWidth, false);

        if (!displaySearchResults) {
            hideSearchResults();
        }

        if (callback != null) {
            callback.addToCompassPoints();
        }
        splitPanel.add(viewLayoutPanel);
    }

    @Override
    public void hideSearchResults() {
        splitPanel.setWidgetHidden(resultsViewLayoutPanel, true);
    }

    @Override
    public void showSearchResults() {
        splitPanel.setWidgetHidden(resultsViewLayoutPanel, false);
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

    /**
     * Displays the contents of a child view. This method will also merge the action buttons
     * defined in the top level view and the individual view that it is displaying as a child.
     *
     * @param displayedView The view to be displayed, or null if no view is to be displayed
     */
    @Override
    public void displayChildView(@Nullable final BaseTemplateViewInterface displayedView) {
        this.getViewPanel().clear();
        this.viewActionButtonsParentPanel.clear();
        this.viewActionButtonsPanel.clear();
        this.viewViewSpecificActionButtonsPanel.clear();

        viewActionButtonsParentPanel.setWidget(0, 0, viewActionButtonsPanel);
        /* Make 100% wide to push the next cell to the right */
        viewActionButtonsParentPanel.getFlexCellFormatter().setWidth(0, 0, "100%");
        viewActionButtonsParentPanel.setWidget(0, 2, viewViewSpecificActionButtonsPanel);


        if (addCustomActionButtonFields) {
            this.viewActionButtonsPanel.add(this.getTopActionPanel());
            this.viewViewSpecificActionButtonsPanel.add(this.getTopViewSpecificRightActionPanel());
        }

        if (displayedView != null) {
            this.getViewPanel().setWidget(displayedView.getPanel());
            this.viewActionButtonsPanel.add(displayedView.getTopActionPanel());

            this.viewViewSpecificActionButtonsPanel.add(displayedView.getTopViewSpecificRightActionPanel());
        }
    }

    /**
     * Displays the contents of a child filtered results view. This method will also merge the action buttons
     * defined in the top level view and the individual view that it is displaying as a child.
     *
     * @param filteredResultsView The filtered view to be displayed, or null if no view is to be displayed
     */
    @Override
    public void displaySearchResultsView(@Nullable final BaseFilteredResultsViewInterface<V> filteredResultsView) {
        this.getResultsPanel().clear();
        this.resultsActionButtonsParentPanel.clear();
        this.resultsActionButtonsPanel.clear();
        this.resultsViewSpecificActionButtonsPanel.clear();

        resultsActionButtonsParentPanel.setWidget(0, 0, resultsActionButtonsPanel);
        /* Make 100% wide to push the next cell to the right */
        resultsActionButtonsParentPanel.getFlexCellFormatter().setWidth(0, 0, "100%");
        resultsActionButtonsParentPanel.setWidget(0, 1, resultsViewSpecificActionButtonsPanel);

        if (filteredResultsView != null) {
            this.getResultsPanel().setWidget(filteredResultsView.getPanel());
            this.resultsActionButtonsPanel.add(filteredResultsView.getTopActionPanel());
            this.resultsViewSpecificActionButtonsPanel.add(filteredResultsView.getTopViewSpecificRightActionPanel());
        }
    }


}
