package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;

/**
 * The interface for a view that displays custom ui elements (i.e. not a table or editor)
 */
public interface BaseCustomViewInterface <T extends RESTBaseEntityV1<?, ?, ?>> extends BaseTemplateViewInterface {
    void displayCustomElements(final T entity, final boolean readonly);
}
