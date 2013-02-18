package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * A callback used by BaseSearchAndEditComponent to display a newly returned entity
 */
public interface DisplayNewEntityCallback<T extends RESTBaseEntityV1<?, ?, ?>> {
    void displayNewEntity(final T entity);
}
