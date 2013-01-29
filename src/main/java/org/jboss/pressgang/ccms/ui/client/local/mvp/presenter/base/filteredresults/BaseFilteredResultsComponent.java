package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

/**
 * This is the base class that is used for components adding logic to views that list the results of a query
 * @author Matthew Casperson
 *
 * @param <T> The entity type
 * @param <U> The collection type for entity T
 * @param <V> The collection item type for entity T
 */
abstract public class BaseFilteredResultsComponent<
        T extends RESTBaseEntityV1<T, U, V>, U extends RESTBaseCollectionV1<T, U, V>, V extends RESTBaseCollectionItemV1<T, U, V>>
    extends ComponentBase implements BaseFilteredResultsComponentInterface<T, U, V> {

    /** Holds the data required to populate and refresh the tags list */
    protected ProviderUpdateData<V> providerData = new ProviderUpdateData<V>();

    @Override
    public ProviderUpdateData<V> getProviderData() {
        return providerData;
    }

    @Override
    public void setTagProviderData(final ProviderUpdateData<V> providerData) {
        this.providerData = providerData;
    }
    
    @Override
    public void bind(final int topicId, final String pageId, final String queryString, final BaseFilteredResultsViewInterface display)
    {
         super.bind(topicId, pageId, display);
         displayQueryElements(queryString);        
    }
    
    /**
     * When a new entity is created, the filtered results are reloaded. This process breaks the link between the selected item
     * and the collection being displayed by the filtered results. This methods will go through and set the selected item to the
     * item in the filtered results list (if it exists).
     */
    protected void relinkSelectedItem()
    {
        if (this.providerData.getSelectedItem() != null && this.providerData.getItems() != null)
        {
            for (final V filteredResultEntity : this.providerData.getItems())
            {
                if (filteredResultEntity.getItem().getId().equals(this.providerData.getSelectedItem().getItem().getId()))
                {
                    this.providerData.setSelectedItem(filteredResultEntity);
                    break;
                }
            }
        }
    }

    /**
     * Display the current filter options
     * 
     * @param queryString The string that contains the filter options
     */
    abstract protected void displayQueryElements(final String queryString);

    /**
     * @param queryString The query string passed to the rest interface
     * @param waitDisplay The main view used to notify the user that an ongoing operation is in progress
     * @return A provider to be used for the category display list
     */
    abstract protected EnhancedAsyncDataProvider<V> generateListProvider(final String queryString, final BaseTemplateViewInterface waitDisplay);

}
