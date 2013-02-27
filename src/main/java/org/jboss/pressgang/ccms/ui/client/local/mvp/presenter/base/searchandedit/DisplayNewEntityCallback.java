package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jetbrains.annotations.NotNull;

/**
 * A callback used by BaseSearchAndEditPresenter to display a newly returned entity.
 */
public interface DisplayNewEntityCallback<T extends RESTBaseEntityV1<?, ?, ?>> {
    void displayNewEntity(@NotNull final T entity);
}
