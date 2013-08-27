package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

/**
 * The interface for a view that displays custom ui elements (i.e. not a table or editor)
 */
public interface BaseCustomViewInterface<T> extends BaseTemplateViewInterface {
    /**
     * Populate the UI with the details from the supplied entity. If additional data is required by the view,
     * it is usually defined in a method called initialize.
     *
     * Since this is a method on a view, no logic is done here other than to copy out the information in
     * the entity and display it in the view.
     *
     * @param entity   The entity that contains the data to be displayed
     * @param readonly true if the UI should be read only, and false otherwise
     */
    void display(final T entity, final boolean readonly);
}
