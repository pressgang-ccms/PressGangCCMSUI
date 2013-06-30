package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;


/**
 * A callback to be implemented by a Search and Edit component that gets a copy of an entity from the server.
 */
public interface GetNewEntityCallback<T extends RESTBaseEntityV1<?, ?, ?>> {
    /**
     * Load a new entity based on the one that was selected, and then call the displayCallback.
     *
     * @param selectedEntity  The entity that was selected from the list of filtered results.
     * @param displayCallback The callback to call once the new entity is loaded.
     */
    void getNewEntity(@NotNull final T selectedEntity, @NotNull final DisplayNewEntityCallback<T> displayCallback);
}
