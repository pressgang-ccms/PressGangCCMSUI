package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

@Dependent
public class ImageFilteredResultsComponent
        extends
        BaseFilteredResultsComponent<ImageFilteredResultsPresenter.Display, RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1>
        implements ImageFilteredResultsPresenter.LogicComponent {

    @Inject
    private HandlerManager eventBus;

    @Override
    public void bind(final String queryString, final ImageFilteredResultsPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        display.setProvider(generateListProvider(queryString, display, waitDisplay));
    }

    @Override
    public String getQuery() {
        final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
        if (!display.getImageIdFilter().getText().isEmpty()) {
            retValue.append(";imageIds=" + display.getImageIdFilter().getText());
        }
        if (!display.getImageDescriptionFilter().getText().isEmpty()) {
            retValue.append(";imageDesc=" + display.getImageDescriptionFilter().getText());
        }
        if (!display.getImageOriginalFileNameFilter().getText().isEmpty()) {
            retValue.append(";imageOrigName=" + display.getImageOriginalFileNameFilter().getText());
        }

        return retValue.toString();
    }

    /**
     * @return A provider to be used for the image display list.
     */
    @Override
    protected EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider(final String queryString,
            final Display display, final BaseTemplateViewInterface waitDisplay) {
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
                            providerData.setSize(retValue.getSize());
                            displayAsynchronousList(providerData.getItems(), providerData.getSize(), providerData.getStartRow());
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

                RESTCalls.getImagesFromQuery(callback, queryString, providerData.getStartRow(), end);
            }
        };
        return provider;
    }

    @Override
    protected void displayQueryElements(final String queryString) {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("imageIds")) {
                    this.display.getImageIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageDesc")) {
                    this.display.getImageDescriptionFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageOrigName")) {
                    this.display.getImageOriginalFileNameFilter().setText(queryElements[1]);
                }
            }
        }

    }
}
