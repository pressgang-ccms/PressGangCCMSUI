package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.detailedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

/**
 * This presenter is used to add logic to a display of potential children and the existing children of an entity.
 *
 * @param <T> The entity type
 * @param <C> The collection item type for potential children
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public interface BaseDetailedChildrenPresenterInterface<
        T extends RESTBaseEntityV1<?, ?, ?>,
        W extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseChildrenPresenterInterface<T, C, D, E, F>, EditableView {

    /**
     * @return The provider that exposes the existing children.
     */
    ProviderUpdateData<F> getExistingProviderData();

    /**
     * @param entity Sometimes the entity being edited is one of the potential children e.g. when editing tags, we can add and
     *               remove the tag from a category that is selected as a potential child, and that selected category's tags are then
     *               presented for ordering. In that case, the entity being edited can be found through the
     *               getPossibleChildrenProviderData().getDisplayedItem() property.
     *
     *               In other cases, the list of potential children is just used to add and remove children, and the selected potential
     *               child has no significance. This happens when editing a category and assigning tags to it. The list of potential
     *               children is all the tags, while the existing children are the children of the category being edited. In this case,
     *               the entity being edited can be referenced via the filtered results getProviderData().getDisplayedItem() property.
     * @return The provider used to display the list of existing children
     */
    @NotNull
    EnhancedAsyncDataProvider<F> generateExistingProvider(@NotNull final W entity);

    /**
     * Called to refresh the list of existing children.
     *
     * @param parent The entity that contains the existing children.
     */
    void refreshExistingChildList(@NotNull final W parent);

    /**
     * This method should be called to initialize any class that extends this interface.
     *
     * @param topicId The help topic id for this view.
     * @param pageId  The history token of the page
     */
    void bindDetailedChildrenExtended(final int topicId, @NotNull final String pageId);

    /**
     * Display the data held in parent. Must call displayDetailedChildren();
     *
     * @param parent   The object holding the data to be displayed.
     * @param readOnly true if the view is read only , false otherwise
     */
    void displayDetailedChildrenExtended(@NotNull final T parent, final boolean readOnly);
}
