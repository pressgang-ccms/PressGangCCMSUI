package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import com.google.gwt.cell.client.FieldUpdater;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

/**
 * This is the base class that is used for components adding logic to views that list the children of an entity.
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 * @param <A> The possible child type
 * @param <B> The collection type for entity A
 * @param <C> The collection item type for entity A
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @author Matthew Casperson
 */
public abstract class BaseChildrenComponent<
        T extends RESTBaseEntityV1<T, U, V>,
        U extends RESTBaseCollectionV1<T, U, V>,
        V extends RESTBaseCollectionItemV1<T, U, V>,
        A extends RESTBaseEntityV1<A, B, C>,
        B extends RESTBaseCollectionV1<A, B, C>,
        C extends RESTBaseCollectionItemV1<A, B, C>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends ComponentBase implements BaseChildrenComponentInterface<T, U, V, A, B, C, D, E, F> {

    /**
     * An instance of the provider data.
     */
    protected ProviderUpdateData<C> providerData = new ProviderUpdateData<C>();
    /**
     * The display that shows the children of a given entity.
     */
    private BaseChildrenViewInterface display;

    @Override
    public ProviderUpdateData<C> getPossibleChildrenProviderData() {
        return this.providerData;
    }

    @Override
    public void setPossibleChildrenProviderData(final ProviderUpdateData<C> providerData) {
        this.providerData = providerData;
    }

    public void bind(final int topicId, final String pageId, final BaseChildrenViewInterface display) {
        this.display = display;

        super.bind(topicId, pageId, display);

        refreshPossibleChildList();
    }

    /**
     * Binds behaviour to the tag list buttons.
     */
    @Override
    public void bindPossibleChildrenListButtonClicks(final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback,
                                                     final AddPossibleChildCallback<A, B, C> addChildCallback,
                                                     final UpdateAfterChildModfiedCallback updateAfterChildModfied) {
        this.display.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<C, String>() {
            @Override
            public void update(final int index, final C object, final String value) {
                /* find the tag if it exists in the category */
                boolean found = false;
                final E existingCollection = getExistingCollectionCallback.getExistingCollection();
                for (final F child : existingCollection.getItems()) {
                    /* we've found a matching tag */
                    if (child.getItem().getId().equals(object.getItem().getId())) {
                        if (child.returnIsAddItem()) {
                            /* Tag was added and then removed */
                            existingCollection.getItems().remove(child);
                        } else if (child.returnIsRemoveItem()) {
                            /* Tag existed, was removed and then was added again */
                            child.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);
                        } else {
                            /* Tag existed and was removed */
                            child.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
                        }
                        found = true;
                        break;
                    }
                }
                /* The tag did not exist, so add it to the collection */
                if (!found) {
                    addChildCallback.createAndAddChild(object);
                }
                updateAfterChildModfied.updateAfterChidModfied();
            }
        });
    }

    /**
     * Used to bind logic to mouse clicks on the list of possible children. This is not mandatory, as sometimes selecting an
     * item in this list has no effect in the UI.
     */
    protected void bindPossibleChildrenRowClick() {

    }

    @Override
    public final void refreshPossibleChildList() {
        this.display.setPossibleChildrenProvider(generatePossibleChildrenProvider());
    }
}