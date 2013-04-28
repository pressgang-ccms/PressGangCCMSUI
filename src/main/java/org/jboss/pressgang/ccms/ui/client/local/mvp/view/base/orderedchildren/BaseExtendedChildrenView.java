package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * The base class for views that display a collection of existing children and a second collection of possible children.
 *
 * @author Matthew Casperson
 */
abstract public class BaseExtendedChildrenView<
        T extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenView<T, C, D, E, F> implements BaseExtendedChildrenViewInterface<T, C, D, E, F> {

    /**
     * The panel that defines the split between the current children and the possible children
     */
    private final HandlerSplitLayoutPanel split = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel existingChildrenResultsPanel = new VerticalPanel();
    private final SimplePager existingChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<F> existingChildrenResults = UIUtilities.<F>createCellTable();
    private EnhancedAsyncDataProvider<F> existingChildrenProvider;

    @Override
    public final CellTable<F> getExistingChildrenResults() {
        return this.existingChildrenResults;
    }

    @Override
    public final SimplePager getExistingChildrenPager() {
        return this.existingChildrenPager;
    }

    @NotNull
    @Override
    public final VerticalPanel getExistingChildrenResultsPanel() {
        return this.existingChildrenResultsPanel;
    }

    @NotNull
    @Override
    public final HandlerSplitLayoutPanel getSplit() {
        return this.split;
    }

    @Override
    public final EnhancedAsyncDataProvider<F> getExistingChildrenProvider() {
        return this.existingChildrenProvider;
    }

    @Override
    public final void setExistingChildrenProvider(@NotNull final EnhancedAsyncDataProvider<F> existingChildrenProvider) {
        if (this.existingChildrenProvider != null) {
            this.existingChildrenProvider.removeDataDisplay(this.existingChildrenResults);
        }

        this.existingChildrenProvider = existingChildrenProvider;
        existingChildrenProvider.addDataDisplay(this.existingChildrenResults);
    }

    /**
     * Initializes the UI elements required to display the existing and possible children.
     *
     * @param applicationName The name of the application, which will be added to the page's title field
     * @param pageName        The name of the page that is being displayed, which will be added to the page's title field
     */
    public BaseExtendedChildrenView(@NotNull final String applicationName, @NotNull final String pageName) {
        super(applicationName, pageName);

        this.split.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_SPLIT_PANEL);

        getPossibleChildrenResultsPanel().addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_LIST_PANEL);
        getPossibleChildrenResultsPanel().add(getPossibleChildrenResults());
        getPossibleChildrenResultsPanel().add(getPossibleChildrenPager());
        getPossibleChildrenPager().setDisplay(getPossibleChildrenResults());

        this.split.addWest(getPossibleChildrenResultsPanel(), Constants.SPLIT_PANEL_SIZE);

        this.existingChildrenResultsPanel.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_EXISTING_LIST_PANEL);
        this.existingChildrenResultsPanel.add(this.existingChildrenResults);
        this.existingChildrenResultsPanel.add(this.existingChildrenPager);
        this.existingChildrenPager.setDisplay(this.existingChildrenResults);

        this.getPanel().setWidget(this.split);
    }

    /**
     * This method will add the existing results panel to the view. This might be called when the view is constructed, or left
     * if the existing children panel should not be shown straight away.
     */
    protected final void addExistingChildrenPanel() {
        this.split.add(this.existingChildrenResultsPanel);
    }
}
