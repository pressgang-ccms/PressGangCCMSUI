package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The base class for views that display a collection of ordered children and a second collection of possible children
 * 
 * @author Matthew Casperson
 * 
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * @param <W> The type of the children that can be added to the collection
 * @param <X> The type of the children that are added to the collection
 */
abstract public class BaseOrderedChildrenView<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>, X extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseChildrenView<T, U, V, W> implements BaseOrderedChildrenViewInterface<T, U, V, W, X> {

    /** A reference to the entity that this view will be modifying. */
    private T originalEntity;

    /** The panel that defines the split between the current children and the possible children */
    private HandlerSplitLayoutPanel split = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel existingChildrenResultsPanel = new VerticalPanel();
    private final SimplePager existingChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<X> existingChildrenResults = UIUtilities.<X> createCellTable();
    private EnhancedAsyncDataProvider<X> existingChildrenProvider;

    public CellTable<X> getExistingChildrenResults() {
        return existingChildrenResults;
    }

    @Override
    public SimplePager getExistingChildrenPager() {
        return existingChildrenPager;
    }

    @Override
    public VerticalPanel getExistingChildrenResultsPanel() {
        return existingChildrenResultsPanel;
    }

    @Override
    public HandlerSplitLayoutPanel getSplit() {
        return split;
    }

    @Override
    public void setSplit(final HandlerSplitLayoutPanel split) {
        this.split = split;
    }

    @Override
    public T getOriginalEntity() {
        return originalEntity;
    }

    @Override
    public void setOriginalEntity(final T originalEntity) {
        this.originalEntity = originalEntity;
    }

    @Override
    public EnhancedAsyncDataProvider<X> getExistingChildrenProvider() {
        return existingChildrenProvider;
    }

    @Override
    public void setExistingChildrenProvider(final EnhancedAsyncDataProvider<X> existingChildrenProvider) {
        this.existingChildrenProvider = existingChildrenProvider;
        existingChildrenProvider.addDataDisplay(existingChildrenResults);
    }

    @Override
    public void initialize(final T originalEntity, final boolean readOnly) {
        this.originalEntity = originalEntity;
    }

    public BaseOrderedChildrenView(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        split.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_SPLIT_PANEL);

        getPossibleChildrenResultsPanel().addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_LIST_PANEL);
        getPossibleChildrenResultsPanel().add(getPossibleChildrenResults());
        getPossibleChildrenResultsPanel().add(getPossibleChildrenPager());
        getPossibleChildrenPager().setDisplay(getPossibleChildrenResults());

        split.addWest(getPossibleChildrenResultsPanel(), Constants.SPLIT_PANEL_SIZE);

        existingChildrenResultsPanel.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_EXISTING_LIST_PANEL);
        existingChildrenResultsPanel.add(existingChildrenResults);
        existingChildrenResultsPanel.add(existingChildrenPager);
        existingChildrenPager.setDisplay(existingChildrenResults);

        this.getPanel().setWidget(split);
    }

    /**
     * This method will add the existing results panel to the view. This might be called when the view is constructed, or left
     * if the existing children panel should not be shown straight away.
     */
    protected void addExistingChildrenPanel() {
        split.add(existingChildrenResultsPanel);
    }
}
