package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.searchandedit;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;


/** A callback to be implemented by a Search and Edit component that gets a copy of an entity from the server */
public interface GetNewEntityCallback<T extends RESTBaseEntityV1<?, ?, ?>> {
    public void getNewEntity(final Integer id, final DisplayNewEntityCallback<T> displayCallback);
}
