/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children;

import static com.google.common.base.Preconditions.checkState;

import com.google.gwt.cell.client.FieldUpdater;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseAuditedEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @see BaseChildrenPresenterInterface
 */
public abstract class BaseChildrenPresenter<
        T extends RESTBaseAuditedEntityV1<?, ?, ?>,
        C extends RESTBaseEntityCollectionItemV1<?, ?, ?>,
        D extends RESTBaseAuditedEntityV1<D, E, F>,
        E extends RESTBaseEntityCollectionV1<D, E, F>,
        F extends RESTBaseEntityCollectionItemV1<D, E, F>>
        extends BaseTemplatePresenter implements BaseChildrenPresenterInterface<T, C, D, E, F> {

    private boolean readOnly;

    private final ProviderUpdateData<C> providerData = new ProviderUpdateData<C>();
    /**
     * The display that shows the children of a given entity.
     */
    @Nullable
    private BaseChildrenViewInterface display;

    @Nullable
    private T parent;

    /**
     * @return An instance of the possible children provider data.
     */
    @Override
    @NotNull
    public ProviderUpdateData<C> getPossibleChildrenProviderData() {
        return this.providerData;
    }

    /**
     * An empty implementation of the extended bind method. Classes extending BaseChildrenPresenter should implement
     * bindChildrenExtended().
     */
    public void bindExtended() {
        throw new UnsupportedOperationException("bindExtended() is not supported. Use bindChildren() instead.");
    }

    /**
     * Display the data held by parent.
     *
     * @param parent   The object that holds the data we want to display
     * @param readOnly true if the view is readonly, false otherwise
     */
    protected void displayChildren(@NotNull final T parent, final boolean readOnly) {
        this.readOnly = readOnly;
        redisplayPossibleChildList(parent);
    }

    /**
     * Initialize this presenter.
     *
     * @param display The view to display the wait dialog.
     */
    protected void bindChildren(@NotNull final BaseChildrenViewInterface display) {
        this.display = display;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void bindPossibleChildrenListButtonClicks(@NotNull final GetExistingCollectionCallback<D, E, F> getExistingCollectionCallback,
                                                     @NotNull final AddPossibleChildCallback<C> addChildCallback,
                                                     @NotNull final UpdateAfterChildModifiedCallback updateAfterChildModified) {
        checkState(display != null, "The display variable should have been set.");

        this.display.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<C, String>() {
            @Override
            public void update(final int index, @NotNull final C object, @NotNull final String value) {
                checkState(object.getItem() != null, "The object collection item needs to have a valid item.");
                checkState(object.getItem().getId() != null, "The object collection item needs to have a valid item with a valid id.");

                /* find the tag if it exists in the category */
                boolean found = false;
                final E existingCollection = getExistingCollectionCallback.getExistingCollection();

                checkState(getExistingCollectionCallback.getExistingCollection().getItems() != null, "The existing collection must have populated items.");

                for (@NotNull final F child : existingCollection.getItems()) {

                    checkState(child.getItem() != null, "The child collection item needs to have a valid item.");
                    checkState(child.getItem().getId() != null, "The child collection item needs to have a valid item with a valid id.");

                    /* we've found a matching tag */
                    if (child.getItem().getId().equals(object.getItem().getId())) {
                        if (child.returnIsAddItem()) {
                            /* Tag was added and then removed */
                            existingCollection.getItems().remove(child);
                        } else if (child.returnIsRemoveItem()) {
                            /* Tag existed, was removed and then was added again */
                            child.setState(RESTBaseEntityCollectionItemV1.UNCHANGED_STATE);
                        } else {
                            /* Tag existed and was removed */
                            child.setState(RESTBaseEntityCollectionItemV1.REMOVE_STATE);
                        }
                        found = true;
                        break;
                    }
                }
                /* The tag did not exist, so add it to the collection */
                if (!found) {
                    addChildCallback.createAndAddChild(object);
                }
                updateAfterChildModified.updateAfterChildModified();
            }
        });
    }

    /**
     * @inheritDoc
     */
    public void redisplayPossibleChildList(@NotNull final T parent) {
        checkState(display != null, "The display variable should have been set.");

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

    /**
     * The entity being displayed.
     */
    @Nullable
    public T getParent() {
        return parent;
    }

    public void setParent(@NotNull T parent) {
        this.parent = parent;
    }
}
