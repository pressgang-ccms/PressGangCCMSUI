package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
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
 * @param <W> The type of the children that can be added to the parent
 * @param <X> The type of the children that have been added to the parent
 */
public interface BaseOrderedChildrenViewInterface<T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>, X extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseChildrenViewInterface<T, U, V, W> {

    SimplePager getExistingChildrenPager();

    VerticalPanel getExistingChildrenResultsPanel();
    
    EnhancedAsyncDataProvider<X> getExistingChildrenProvider();

    void setExistingChildrenProvider(final EnhancedAsyncDataProvider<X> possibleExistingProvider);

    HandlerSplitLayoutPanel getSplit();

    void setSplit(final HandlerSplitLayoutPanel split);

    @Override
    T getOriginalEntity();

    @Override
    void setOriginalEntity(final T originalEntity);

    @Override
    void initialize(final T originalEntity, final boolean readOnly);
}
