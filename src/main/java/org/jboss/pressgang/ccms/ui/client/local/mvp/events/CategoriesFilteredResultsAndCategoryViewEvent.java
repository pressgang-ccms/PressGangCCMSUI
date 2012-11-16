package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class CategoriesFilteredResultsAndCategoryViewEvent extends ViewOpenWithQueryEvent<ViewOpenWithQueryEventHandler> {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public CategoriesFilteredResultsAndCategoryViewEvent(final String query, final boolean newWindow) {
        super(query, newWindow);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
