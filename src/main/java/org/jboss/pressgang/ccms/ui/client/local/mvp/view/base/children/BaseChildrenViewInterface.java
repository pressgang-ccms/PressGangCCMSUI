package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBasePrimaryEntityV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface BaseChildrenViewInterface<T extends RESTBasePrimaryEntityV1, U extends RESTBaseCollectionItemV1> extends
        BaseTemplateViewInterface {
    T getOriginalEntity();

    void setOriginalEntity(final T originalEntity);

    VerticalPanel getPossibleChildrenResultsPanel();

    SimplePager getPossibleChildrenPager();

    CellTable<U> getPossibleChildrenResults();

    EnhancedAsyncDataProvider<U> getPossibleChildrenProvider();

    void setPossibleChildrenProvider(final EnhancedAsyncDataProvider<U> possibleChildrenProvider);

    void initialize(final T originalEntity, final boolean readOnly);
    
    Column<U, String> getPossibleChildrenButtonColumn();
}
