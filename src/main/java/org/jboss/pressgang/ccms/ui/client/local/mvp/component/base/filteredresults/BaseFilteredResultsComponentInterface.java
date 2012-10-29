package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;

public interface BaseFilteredResultsComponentInterface<S extends BaseTemplateViewInterface, T extends RESTBaseCollectionItemV1<?, ?, ?>>
        extends Component<S> {

    /**
     * @return The query string that represents the current state of the filters
     */
    String getQuery();

    /**
     * @return The provider data used to populate the celltable
     */
    public ProviderUpdateData<T> getProviderData();

    /**
     * @param providerData The provider data used to populate the celltable
     */
    void setTagProviderData(final ProviderUpdateData<T> providerData);
}
