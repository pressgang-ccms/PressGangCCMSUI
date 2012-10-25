package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface BaseOrderedChildrenViewInterface<T extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseTemplateViewInterface {
    CellTable<V> getExistingChildrenResults();

    SimplePager getExistingChildrenPager();

    VerticalPanel getExistingChildrenResultsPanel();

    EnhancedAsyncDataProvider<U> getPossibleChildrenProvider();

    void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<U> possibleChildrenProvider);

    CellTable<U> getPossibleChildrenResults();

    SimplePager getPossibleChildrenPager();

    VerticalPanel getPossibleChildrenResultsPanel();

    HandlerSplitLayoutPanel getSplit();

    void setSplit(final HandlerSplitLayoutPanel split);

    T getOriginalEntity();

    void setOriginalEntity(final T originalEntity);

    EnhancedAsyncDataProvider<V> getExistingChildrenProvider();

    void setExistingChildrenProvider(final EnhancedAsyncDataProvider<V> existingChildrenProvider);

    void initialize(final T originalEntity, final boolean readOnly);
}
