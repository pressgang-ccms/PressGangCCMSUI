package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

public class CategoriesFilteredResultsAndCategoryViewEvent extends ViewOpenWithQueryEvent {
    public static final Type<ViewOpenWithQueryEventHandler> TYPE = new Type<ViewOpenWithQueryEventHandler>();

    public CategoriesFilteredResultsAndCategoryViewEvent(String query) {
        super(query);
    }

    @Override
    public Type<ViewOpenWithQueryEventHandler> getAssociatedType() {
        return TYPE;
    }
}
