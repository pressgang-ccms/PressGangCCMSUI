package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author matthew
 *
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <W> The potential children type
 */
public interface BaseChildrenViewInterface<
    T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, 
    A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>,
    D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseTemplateViewInterface {
    
    T getOriginalEntity();

    void setOriginalEntity(final T originalEntity);

    VerticalPanel getPossibleChildrenResultsPanel();

    SimplePager getPossibleChildrenPager();

    CellTable<C> getPossibleChildrenResults();

    EnhancedAsyncDataProvider<C> getPossibleChildrenProvider();

    void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<C> possibleChildrenProvider);

    void initialize(final T originalEntity, final boolean readOnly);

    Column<C, String> getPossibleChildrenButtonColumn();
}
