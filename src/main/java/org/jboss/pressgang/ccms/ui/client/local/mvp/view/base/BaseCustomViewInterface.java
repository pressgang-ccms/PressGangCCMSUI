package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

/**
 * The interface for a view that displays custom ui elements (i.e. not a table or editor)
 */
public interface BaseCustomViewInterface<T> extends BaseTemplateViewInterface {
    void display(final T entity, final boolean readonly);
}
