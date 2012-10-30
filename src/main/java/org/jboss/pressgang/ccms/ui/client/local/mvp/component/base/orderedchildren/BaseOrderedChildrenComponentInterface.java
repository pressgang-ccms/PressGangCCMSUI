package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

public interface BaseOrderedChildrenComponentInterface<S extends BaseOrderedChildrenViewInterface<T, U, V>, T extends RESTBasePrimaryEntityV1<?, ?, ?>, W extends RESTBasePrimaryEntityV1<?, ?, ?>, U extends RESTBaseCollectionItemV1<?, ?, ?>, V extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends BaseChildrenComponentInterface<S, T, U>, EditableView {
    ProviderUpdateData<V> getExistingProviderData();

    void setExistingProviderData(final ProviderUpdateData<V> existingProviderData);

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
    EnhancedAsyncDataProvider<V> generateExistingProvider(final W entity);
    
    boolean moveTagsUpAndDown(final V object, final boolean down);
}
