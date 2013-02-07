package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * 
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 * 
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 */
public interface BaseChildrenViewInterface<
    T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, 
    A extends RESTBaseEntityV1<A, B, C>, B extends RESTBaseCollectionV1<A, B, C>, C extends RESTBaseCollectionItemV1<A, B, C>,
    D extends RESTBaseEntityV1<D, E, F>, E extends RESTBaseCollectionV1<D, E, F>, F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseCustomViewInterface<T> {

    /**
     *
     * @return The entity that will have the possible children added to it.
     */
    T getOriginalEntity();

    /**
     *
     * @param originalEntity The entity that will have the possible children added to it.
     */
    void setOriginalEntity(final T originalEntity);

    /**
     *
     * @return The panel used to hold the table and pager
     */
    VerticalPanel getPossibleChildrenResultsPanel();

    /**
     *
     * @return The table pager.
     */
    SimplePager getPossibleChildrenPager();

    /**
     *
     * @return The table used to display the possible children
     */
    CellTable<C> getPossibleChildrenResults();

    /**
     *
     * @return The provider used as the source for the possible children.
     */
    EnhancedAsyncDataProvider<C> getPossibleChildrenProvider();

    /**
     *
     * @param possibleChildrenProvider The provider used as the source for the possible children.
     */
    void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<C> possibleChildrenProvider);

    /**
     *
     * @return The column that holds the buttons used to add or remove the possible children.
     */
    Column<C, String> getPossibleChildrenButtonColumn();
}
