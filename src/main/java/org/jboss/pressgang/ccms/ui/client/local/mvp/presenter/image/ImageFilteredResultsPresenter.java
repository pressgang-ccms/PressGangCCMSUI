package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.filteredresults.BaseFilteredResultsComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImageFilteredResultsPresenter
        extends
        BaseFilteredResultsComponent<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1>
        implements TemplatePresenter {

    public interface Display extends
            BaseFilteredResultsViewInterface<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1> {

        TextBox getImageIdFilter();

        TextBox getImageDescriptionFilter();

        TextBox getImageOriginalFileNameFilter();
    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

    @Inject
    private Display display;

    private String queryString;

    @Inject
    private HandlerManager eventBus;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public final void parseToken(final String searchToken) {
        this.queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public final void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, queryString);
    }

    public final void process(final int topicId, final String pageId, final String queryString) {
        super.bind(topicId, pageId, queryString, display);
        display.setProvider(generateListProvider(queryString, display));
    }

    @Override
    public String getQuery() {
        final StringBuilder retValue = new StringBuilder();
        if (!display.getImageIdFilter().getText().isEmpty()) {
            retValue.append(";imageIds=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getImageIdFilter().getText()) : display.getImageIdFilter().getText()));
        }
        if (!display.getImageDescriptionFilter().getText().isEmpty()) {
            retValue.append(";imageDesc=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getImageDescriptionFilter().getText()) : display.getImageDescriptionFilter().getText()));
        }
        if (!display.getImageOriginalFileNameFilter().getText().isEmpty()) {
            retValue.append(";imageOrigName=").append((Constants.ENCODE_QUERY_OPTIONS ? URL.encodeQueryString(display.getImageOriginalFileNameFilter().getText()) : display.getImageOriginalFileNameFilter().getText()));
        }

        return retValue.toString().isEmpty() ? Constants.QUERY_PATH_SEGMENT_PREFIX
                : Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON + retValue.toString();
    }

    /**
     * @param waitDisplay The view used to notify the user that an ongoin operation is in progress
     * @return A provider to be used for the image display list.
     */
    @Override
    protected EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider(final String queryString, final BaseTemplateViewInterface waitDisplay) {
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
                            relinkSelectedItem();
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
                    this.display.getImageIdFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals("imageDesc")) {
                    this.display.getImageDescriptionFilter().setText(URL.decodeQueryString(queryElements[1]));
                } else if (queryElements[0].equals("imageOrigName")) {
                    this.display.getImageOriginalFileNameFilter().setText(URL.decodeQueryString(queryElements[1]));
                }
            }
        }

    }
}
