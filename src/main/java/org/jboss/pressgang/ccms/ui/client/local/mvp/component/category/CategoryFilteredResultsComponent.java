package org.jboss.pressgang.ccms.ui.client.local.mvp.component.category;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTCategoryCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTCategoryCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.category.CategoryFilteredResultsPresenter.LogicCompnent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

public class CategoryFilteredResultsComponent extends ComponentBase<CategoryFilteredResultsPresenter.Display> implements LogicCompnent {
    
    /**
     * Holds the data required to populate and refresh the categories list
     */
    private ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData = new ProviderUpdateData<RESTCategoryCollectionItemV1>();
    
    @Override
    public ProviderUpdateData<RESTCategoryCollectionItemV1> getCategoryProviderData() {
        return categoryProviderData;
    }

    @Override
    public void setCategoryProviderData(ProviderUpdateData<RESTCategoryCollectionItemV1> categoryProviderData) {
        this.categoryProviderData = categoryProviderData;
    }

    @Override
    public void bind(final String queryString, final CategoryFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        
        display.setProvider(generateListProvider(queryString));
    }
    
    /**
     * @return A provider to be used for the category display list
     */
    private EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> generateListProvider(final String queryString) {
        final EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTCategoryCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTCategoryCollectionItemV1> list) {

                final RESTCallback<RESTCategoryCollectionV1> callback = new RESTCallback<RESTCategoryCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTCategoryCollectionV1 retValue) {
                        try {
                            categoryProviderData.setItems(retValue.getItems());
                            displayAsynchronousList(categoryProviderData.getItems(), retValue.getSize(),
                                    categoryProviderData.getStartRow());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed(final Message message, final Throwable throwable) {
                        display.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                categoryProviderData.setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = categoryProviderData.getStartRow() + length;

                RESTCalls.getCategoriesFromQuery(callback, queryString, categoryProviderData.getStartRow(), end);
             }
        };
        return provider;
    }
}
