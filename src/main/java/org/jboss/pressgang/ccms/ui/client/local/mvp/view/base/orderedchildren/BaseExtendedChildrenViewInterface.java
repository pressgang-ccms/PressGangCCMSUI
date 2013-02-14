package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 *
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 *
 * @param <W> The parent of the children
 *
 * @param <C> The collection item type for entity A
 *
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 */
public interface BaseExtendedChildrenViewInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenViewInterface<T, C, D, E, F> {

    /**
     *
     * @return The table that displays the existing children.
     */
    CellTable<F> getExistingChildrenResults();

    /**
     *
     * @return The pager this is used to page over the table of existing children
     */
    SimplePager getExistingChildrenPager();

    /**
     *
     * @return The panel this is used to holds the table and pager for the existing children
     */
    VerticalPanel getExistingChildrenResultsPanel();

    /**
     * @return The table that lists the existing children
     */
    EnhancedAsyncDataProvider<F> getExistingChildrenProvider();

    /**
     *
     * @param possibleExistingProvider The provider that exposes the existing children
     */
    void setExistingChildrenProvider(final EnhancedAsyncDataProvider<F> possibleExistingProvider);

    /**
     *
     * @return The split panel between the possible children and the existing children
     */
    HandlerSplitLayoutPanel getSplit();

    /**
     *
     * @return The parent entity that holds the existing children
     */
    T getOriginalEntity();

    /**
     *
     * @param originalEntity The entity that will have the possible children added to it, and that holds the existing
     *                       children that will be modified.
     */
    void setOriginalEntity(final T originalEntity);
}
