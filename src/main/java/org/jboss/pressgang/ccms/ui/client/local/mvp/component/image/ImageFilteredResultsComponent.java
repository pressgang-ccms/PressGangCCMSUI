package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

@Dependent
public class ImageFilteredResultsComponent extends ComponentBase<ImageFilteredResultsPresenter.Display> implements ImageFilteredResultsPresenter.LogicComponent{
   
    @Inject
    private HandlerManager eventBus;
    
    /**
     * Holds the data required to populate and refresh the categories list
     */
    private ProviderUpdateData<RESTImageCollectionItemV1> providerData = new ProviderUpdateData<RESTImageCollectionItemV1>();
    
    @Override
    public ProviderUpdateData<RESTImageCollectionItemV1> getProviderData() {
        return providerData;
    }

    @Override
    public void setProviderData(ProviderUpdateData<RESTImageCollectionItemV1> providerData) {
        this.providerData = providerData;
    }
    
    @Override
    public void bind(final String queryString, final ImageFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display,  waitDisplay);
        display.setProvider(generateListProvider(queryString));
        bindImageSearchButtons(display);
    }
    
    private String getQuery(final ImageFilteredResultsPresenter.Display imageSearchDisplay) {
        final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
        if (!imageSearchDisplay.getImageIdFilter().getText().isEmpty()) {
            retValue.append(";imageIds=" + imageSearchDisplay.getImageIdFilter().getText());
        }
        if (!imageSearchDisplay.getImageDescriptionFilter().getText().isEmpty()) {
            retValue.append(";imageDesc=" + imageSearchDisplay.getImageDescriptionFilter().getText());
        }
        if (!imageSearchDisplay.getImageOriginalFileNameFilter().getText().isEmpty()) {
            retValue.append(";imageOrigName=" + imageSearchDisplay.getImageOriginalFileNameFilter().getText());
        }

        return retValue.toString();
    }
    
    private void bindImageSearchButtons(final ImageFilteredResultsPresenter.Display display) {
        display.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(getQuery(display)));
            }
        });
    }

    /**
     * @return A provider to be used for the image display list.
     */
    private EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider(final String queryString) {
        final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTImageCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTImageCollectionItemV1> item) {
                providerData.setStartRow(item.getVisibleRange().getStart());
                final int length = item.getVisibleRange().getLength();
                final int end = providerData.getStartRow() + length;

                final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>() {
                    @Override
                    public void begin() {
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageCollectionV1 retValue) {
                        try {
                            providerData.setItems(retValue.getItems());
                            displayAsynchronousList(providerData.getItems(), providerData.getStartRow(), retValue.getSize());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                RESTCalls.getImagesFromQuery(callback, queryString, providerData.getStartRow(), end);
            }
        };
        return provider;
    }
}
