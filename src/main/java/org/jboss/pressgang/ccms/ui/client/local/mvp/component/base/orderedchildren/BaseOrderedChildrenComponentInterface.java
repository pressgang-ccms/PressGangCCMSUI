package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * 
 * @author Matthew Casperson
 *
 * @param <S> The type of the parent view
 * @param <T> The type of the entity being edited by the view
 * @param <U> The collection type of T
 * @param <V> The collection Item type of T
 * @param <W> The type of the possible children
 * @param <X> The type of the existing children
 * @param <Y> The type of the parent of W
 */
public interface BaseOrderedChildrenComponentInterface<S extends BaseOrderedChildrenViewInterface<T, U, V, W, X>, T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>, W extends RESTBaseCollectionItemV1<?, ?, ?>, X extends RESTBaseCollectionItemV1<?, ?, ?>, Y extends RESTBaseEntityV1<?, ?, ?>>
        extends BaseChildrenComponentInterface<S, T, U, V, W>, EditableView {
    ProviderUpdateData<X> getExistingProviderData();

    void setExistingProviderData(final ProviderUpdateData<X> existingProviderData);

    /**
     * 
     * @param entity Sometimes the entity being edited is one of the potential children e.g. when editing tags, we can add and
     *        remove the tag from a category that is selected as a potential child, and that selected category's tags are then
     *        presented for ordering. In that case, the entity being edited can be found through the
     *        getPossibleChildrenProviderData().getDisplayedItem() property.
     * 
     *        In other cases, the list of potential children is just used to add and remove children, and the selected potential
     *        child has no significance. This happens when editing a category and assigning tags to it. The list of potential
     *        children is all the tags, while the existing children are the children of the category being edited. In this case,
     *        the entity being edited can be referenced via the filtered results getProviderData().getDisplayedItem() property.
     * @return The provder used to display the list of existing children
     */
    EnhancedAsyncDataProvider<X> generateExistingProvider(final Y entity);
    
    boolean moveTagsUpAndDown(final X object, final boolean down);
}
