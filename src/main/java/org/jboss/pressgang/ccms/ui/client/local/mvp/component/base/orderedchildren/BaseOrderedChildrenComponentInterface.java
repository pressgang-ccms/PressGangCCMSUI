package org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

public interface BaseOrderedChildrenComponentInterface<S extends BaseOrderedChildrenViewInterface<T, U, V>, T extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1, V extends RESTBaseCollectionItemV1>
        extends BaseChildrenComponentInterface<S, T, U>, EditableView {
    ProviderUpdateData<V> getExistingProviderData();

    void setExistingProviderData(final ProviderUpdateData<V> existingProviderData);
    
    EnhancedAsyncDataProvider<V> generateExistingProvider();
}
