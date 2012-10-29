package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The type of the parent entity
 * @param <U> The type of the children that can be added to the parent
 * @param <V> The type of the children that have been added to the parent
 */
public interface BaseOrderedChildrenViewInterface<T extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseChildrenViewInterface<T, U> {

    SimplePager getExistingChildrenPager();

    VerticalPanel getExistingChildrenResultsPanel();
    
    EnhancedAsyncDataProvider<V> getExistingChildrenProvider();

    void setExistingChildrenProvider(final EnhancedAsyncDataProvider<V> possibleExistingProvider);

    HandlerSplitLayoutPanel getSplit();

    void setSplit(final HandlerSplitLayoutPanel split);

    @Override
    T getOriginalEntity();

    @Override
    void setOriginalEntity(final T originalEntity);

    @Override
    void initialize(final T originalEntity, final boolean readOnly);
}
