package org.jboss.pressgang.ccms.ui.client.local.mvp.component.tag;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.HasData;

public class TagFilteredResultsComponent extends ComponentBase<TagFilteredResultsPresenter.Display> implements
        TagFilteredResultsPresenter.LogicComponent {

    /** Holds the data required to populate and refresh the tags list */
    private ProviderUpdateData<RESTTagCollectionItemV1> tagProviderData = new ProviderUpdateData<RESTTagCollectionItemV1>();
    
    @Override
    public ProviderUpdateData<RESTTagCollectionItemV1> getTagProviderData() {
        return tagProviderData;
    }

    @Override
    public void setTagProviderData(final ProviderUpdateData<RESTTagCollectionItemV1> tagProviderData) {
        this.tagProviderData = tagProviderData;
    }

    @Override
    public void bind(final String queryString, final TagFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        display.setProvider(generateListProvider(queryString, display, waitDisplay));
        displayQueryElements(queryString);
    }
    
    private void displayQueryElements(final String queryString)
    {
        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("tagIds")) {
                    this.display.getIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("tagName")) {
                    this.display.getNameFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("tagDesc")) {
                    this.display.getDescriptionFilter().setText(queryElements[1]);
                }
            }
        }
    }

    /**
     * @return A provider to be used for the tag display list
     */
    private EnhancedAsyncDataProvider<RESTTagCollectionItemV1> generateListProvider(final String queryString,
            final TagFilteredResultsPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        final EnhancedAsyncDataProvider<RESTTagCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTTagCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTagCollectionItemV1> range) {

                final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new RESTCalls.RESTCallback<RESTTagCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTTagCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            tagProviderData.setItems(retValue.getItems());
                            displayAsynchronousList(tagProviderData.getItems(), retValue.getSize(),
                                    tagProviderData.getStartRow());
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

                tagProviderData.setStartRow(range.getVisibleRange().getStart());
                final int length = range.getVisibleRange().getLength();
                final int end = tagProviderData.getStartRow() + length;

                RESTCalls.getTagsFromQuery(callback, queryString, tagProviderData.getStartRow(), end);
            }
        };
        
        return provider;
    }
    
    public String getQuery(final TagFilteredResultsPresenter.Display searchDisplay) {
        final StringBuilder retValue = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX_WO_SEMICOLON);
        if (!searchDisplay.getIdFilter().getText().isEmpty()) {
            retValue.append(";tagIds=" + searchDisplay.getIdFilter().getText());
        }
        if (!searchDisplay.getNameFilter().getText().isEmpty()) {
            retValue.append(";tagName=" + searchDisplay.getNameFilter().getText());
        }
        if (!searchDisplay.getDescriptionFilter().getText().isEmpty()) {
            retValue.append(";tagDesc=" + searchDisplay.getDescriptionFilter().getText());
        }

        return retValue.toString();
    }
}
