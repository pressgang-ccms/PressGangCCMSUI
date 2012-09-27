package org.jboss.pressgang.ccms.ui.client.local.mvp.events;

import com.google.gwt.event.shared.GwtEvent;

public class CategoriesFilteredResultsAndCategoryViewEvent extends GwtEvent<CategoriesFilteredResultsAndCategoryViewEventHandler> {
    public static final Type<CategoriesFilteredResultsAndCategoryViewEventHandler> TYPE = new Type<CategoriesFilteredResultsAndCategoryViewEventHandler>();
    private final String query;

    public String getQuery() {
        return query;
    }

    public CategoriesFilteredResultsAndCategoryViewEvent(final String query) {
        this.query = query;
    }

    @Override
    public Type<CategoriesFilteredResultsAndCategoryViewEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final CategoriesFilteredResultsAndCategoryViewEventHandler handler) {
        handler.onCategoriesFilteredResultsViewAndCategoryOpen(this);
    }
}
