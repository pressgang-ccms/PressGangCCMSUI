package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * @param <W> The entity type for the possible children
 */
abstract public class BaseChildrenView<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseTemplateView<T, U, V> implements BaseChildrenViewInterface<T, U, V, W> {
    /** A reference to the tag that this view will be modifying */
    private T originalEntity;

    private final VerticalPanel possibleChildrenResultsPanel = new VerticalPanel();
    private final SimplePager possibleChildrenPager = UIUtilities.createSimplePager();
    private final CellTable<W> possibleChildrenResults = UIUtilities.<W> createCellTable();
    private EnhancedAsyncDataProvider<W> possibleChildrenProvider;

    @Override
    public T getOriginalEntity() {
        return originalEntity;
    }

    @Override
    public void setOriginalEntity(final T originalEntity) {
        this.originalEntity = originalEntity;
    }

    @Override
    public VerticalPanel getPossibleChildrenResultsPanel() {
        return possibleChildrenResultsPanel;
    }

    @Override
    public SimplePager getPossibleChildrenPager() {
        return possibleChildrenPager;
    }

    @Override
    public CellTable<W> getPossibleChildrenResults() {
        return possibleChildrenResults;
    }

    @Override
    public EnhancedAsyncDataProvider<W> getPossibleChildrenProvider() {
        return possibleChildrenProvider;
    }

    @Override
    public void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<W> possibleChildrenProvider) {
        this.possibleChildrenProvider = possibleChildrenProvider;
        possibleChildrenProvider.addDataDisplay(possibleChildrenResults);
    }

    @Override
    public void initialize(final T originalEntity, final boolean readOnly) {
        this.originalEntity = originalEntity;
    }

    public BaseChildrenView(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        possibleChildrenPager.setDisplay(possibleChildrenResults);

        possibleChildrenResultsPanel.add(possibleChildrenResults);
        possibleChildrenResultsPanel.add(possibleChildrenPager);

        this.getPanel().setWidget(possibleChildrenResultsPanel);
    }
}
