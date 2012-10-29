package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This is the base class that is used for components adding logic to views that list the results of a query
 * @author Matthew Casperson
 *
 * @param <S> The type of the view that displays the query results
 * @param <T> The type of the entity that is listed in the query results
 */
abstract public class BaseFilteredResultsComponent<S extends BaseFilteredResultsViewInterface<T>, T extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends ComponentBase<S> implements BaseFilteredResultsComponentInterface<S, T> {

    /** Holds the data required to populate and refresh the tags list */
    protected ProviderUpdateData<T> providerData = new ProviderUpdateData<T>();

    @Override
    public ProviderUpdateData<T> getProviderData() {
        return providerData;
    }

    @Override
    public void setTagProviderData(final ProviderUpdateData<T> providerData) {
        this.providerData = providerData;
    }

    /**
     * DIsplay the current filter options
     * 
     * @param queryString The string that contains the filter options
     */
    abstract protected void displayQueryElements(final String queryString);
    
    abstract protected EnhancedAsyncDataProvider<T> generateListProvider(final String queryString,
            final S display, final BaseTemplateViewInterface waitDisplay);

}
