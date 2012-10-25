package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
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
 * @param <T> The type of the entity that is being modified
 * @param <U> The type of the children that can be added to the collection
 * @param <V> The type of the children that are added to the collection
 */
public class BaseOrderedChildrenView<T extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseTemplateView {
    

    /** A reference to the entity that this view will be modifying. */
    private T originalEntity;

    /** The panel that defines the split between the current children and the possible children */
    private HandlerSplitLayoutPanel split = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);

    private final VerticalPanel possibleChildrenResultsPanel = new VerticalPanel();
    private final SimplePager possibleChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<U> possibleChildrenResults = UIUtilities.<U> createCellTable();
    private EnhancedAsyncDataProvider<U> possibleChildrenProvider;

    private final VerticalPanel existingChildrenResultsPanel = new VerticalPanel();
    private final SimplePager existingChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<V> existingChildrenResults = UIUtilities.<V> createCellTable();
    private EnhancedAsyncDataProvider<V> existingChildrenProvider;

    public CellTable<V> getExistingChildrenResults() {
        return existingChildrenResults;
    }

    public SimplePager getExistingChildrenPager() {
        return existingChildrenPager;
    }

    public VerticalPanel getExistingChildrenResultsPanel() {
        return existingChildrenResultsPanel;
    }

    public EnhancedAsyncDataProvider<U> getPossibleChildrenProvider() {
        return possibleChildrenProvider;
    }

    public void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<U> possibleChildrenProvider) {
        this.possibleChildrenProvider = possibleChildrenProvider;
        possibleChildrenProvider.addDataDisplay(possibleChildrenResults);
    }

    public CellTable<U> getPossibleChildrenResults() {
        return possibleChildrenResults;
    }

    public SimplePager getPossibleChildrenPager() {
        return possibleChildrenPager;
    }

    public VerticalPanel getPossibleChildrenResultsPanel() {
        return possibleChildrenResultsPanel;
    }

    public HandlerSplitLayoutPanel getSplit() {
        return split;
    }

    public void setSplit(final HandlerSplitLayoutPanel split) {
        this.split = split;
    }

    public T getOriginalEntity() {
        return originalEntity;
    }

    public void setOriginalEntity(final T originalEntity) {
        this.originalEntity = originalEntity;
    }

    public EnhancedAsyncDataProvider<V> getExistingChildrenProvider() {
        return existingChildrenProvider;
    }

    public void setExistingChildrenProvider(final EnhancedAsyncDataProvider<V> existingChildrenProvider) {
        this.existingChildrenProvider = existingChildrenProvider;
        existingChildrenProvider.addDataDisplay(existingChildrenResults);
    }

    public void initialize(final T originalEntity, final boolean readOnly) {
        this.originalEntity = originalEntity;
    }
    
    public BaseOrderedChildrenView(final String applicationName, final String pageName) {
        super(applicationName, pageName);
       
        split.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_SPLIT_PANEL);
        
        possibleChildrenResultsPanel.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_LIST_PANEL);
        possibleChildrenResultsPanel.add(possibleChildrenResults);
        possibleChildrenResultsPanel.add(possibleChildrenPager);
        possibleChildrenPager.setDisplay(possibleChildrenResults);

        split.addWest(possibleChildrenResultsPanel, Constants.SPLIT_PANEL_SIZE);

        existingChildrenResultsPanel.addStyleName(CSSConstants.OrderedChildrenResultsView.ORDERED_CHILDREN_EXISTING_LIST_PANEL);
        existingChildrenResultsPanel.add(existingChildrenResults);
        existingChildrenResultsPanel.add(existingChildrenPager);
        existingChildrenPager.setDisplay(existingChildrenResults);

        /* Add this later once a category has been selected */
        // split.add(existingChildrenResultsPanel);

        this.getPanel().setWidget(split);
    }
}
