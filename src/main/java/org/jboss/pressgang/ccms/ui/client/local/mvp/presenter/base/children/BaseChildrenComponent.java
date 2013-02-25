package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import com.google.gwt.cell.client.FieldUpdater;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jetbrains.annotations.NotNull;

/**
 * @param <T> The entity type
 * @param <C> The collection item type for the possible child
 * @param <D> The existing child type
 * @param <E> The collection type for entity D
 * @param <F> The collection item type for entity D
 * @inheritDoc
 */
public abstract class BaseChildrenComponent<
        T extends RESTBaseEntityV1<?, ?, ?>,
        C extends RESTBaseCollectionItemV1<?, ?, ?>,
        D extends RESTBaseEntityV1<D, E, F>,
        E extends RESTBaseCollectionV1<D, E, F>,
        F extends RESTBaseCollectionItemV1<D, E, F>>
        extends BaseTemplatePresenter implements BaseChildrenComponentInterface<T, C, D, E, F> {

    private boolean readOnly;

    private final ProviderUpdateData<C> providerData = new ProviderUpdateData<C>();
    /**
     * The display that shows the children of a given entity.
     */
    private BaseChildrenViewInterface display;

    /**
     * @return An instance of the possible children provider data.
     */
    @Override
    @NotNull
    public final ProviderUpdateData<C> getPossibleChildrenProviderData() {
        return this.providerData;
    }

    /**
     * An empty implementation of the extended bind method. Classes extending BaseChildrenComponent should implement
     * bindChildrenExtended().
     *
     * @param topicId the help topic for the page
     * @param pageId  The history token of the page
     */
    public final void bindExtended(final int topicId, @NotNull final String pageId) {
        throw new UnsupportedOperationException("bindExtended() is not supported. Use bindChildren() instead.");
    }

    /**
     * Display the data held by parent.
     *
     * @param parent   The object that holds the data we want to display
     * @param readOnly true if the view is readonly, false otherwise
     */
    protected final void displayChildren(@NotNull final T parent, final boolean readOnly) {
        this.readOnly = readOnly;
        redisplayPossibleChildList(parent);
    }

    /**
     * @inheritDoc
     */
    protected final void bindChildren(final int topicId, @NotNull final String pageId, @NotNull final BaseChildrenViewInterface display) {
        this.display = display;
        super.bind(topicId, pageId, display);
    }

    /**
     * @inheritDoc
     */
    @Override
    public final void bindPossibleChildrenListButtonClicks(@NotNull final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback,
                                                           @NotNull final AddPossibleChildCallback<C> addChildCallback,
                                                           @NotNull final UpdateAfterChildModfiedCallback updateAfterChildModfied) {
        this.display.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<C, String>() {
            @Override
            public void update(final int index, @NotNull final C object, @NotNull final String value) {
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
     * @inheritDoc
     */
    public final void redisplayPossibleChildList(@NotNull final T parent) {
        this.display.setPossibleChildrenProvider(generatePossibleChildrenProvider(parent));
    }

    /**
     * Default empty implementation.
     */
    public void refreshPossibleChildrenDataFromRESTAndRedisplayList(@NotNull final T parent) {

    }


    protected boolean isReadOnly() {
        return readOnly;
    }
}
