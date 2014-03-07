package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec;

import static com.google.common.base.Preconditions.checkState;

import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProcessInformationCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.process.ProcessFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

public class ContentSpecProcessPresenter extends ProcessFilteredResultsPresenter {

    @NotNull
    protected EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1> generateListProvider(
            @NotNull final RESTTextContentSpecV1 contentSpec, @NotNull final BaseTemplateViewInterface waitDisplay) {
        final ProviderUpdateData<RESTProcessInformationCollectionItemV1> providerData = getProviderData();
        providerData.reset();

        return new EnhancedAsyncDataProvider<RESTProcessInformationCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTProcessInformationCollectionItemV1> list) {

                providerData.setStartRow(list.getVisibleRange().getStart());
                final int length = list.getVisibleRange().getLength();
                final int end = providerData.getStartRow() + length;

                final RESTCallBack<RESTContentSpecV1> callback = new RESTCallBack<RESTContentSpecV1>() {
                    @Override
                    public void success(@NotNull final RESTContentSpecV1 retValue) {
                        checkState(retValue.getProcesses() != null, "There returned content spec should have the processes expanded.");
                        checkState(retValue.getProcesses().getItems() != null,
                                "There returned collection should have a valid items collection.");
                        checkState(retValue.getProcesses().getSize() != null,
                                "There returned collection should have a valid size collection.");

                        providerData.setItems(retValue.getProcesses().getItems());
                        providerData.setSize(retValue.getProcesses().getSize());
                        relinkSelectedItem();
                        displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
                    }
                };

                getFailOverRESTCall().performRESTCall(
                        FailOverRESTCallDatabase.getContentSpecProcesses(contentSpec.getId(), providerData.getStartRow(), end),
                        callback, waitDisplay);
            }
        };
    }
}
