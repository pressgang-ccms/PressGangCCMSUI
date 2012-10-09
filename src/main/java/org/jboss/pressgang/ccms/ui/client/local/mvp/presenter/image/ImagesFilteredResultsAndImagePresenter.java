package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImagesFilteredResultsAndImagePresenter implements EditableView, TemplatePresenter {

    public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";

    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getResultsPanel();

        SimpleLayoutPanel getViewPanel();

        SimpleLayoutPanel getViewActionButtonsPanel();

        SimpleLayoutPanel getResultsActionButtonsPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getViewLayoutPanel();
    }

    @Inject
    private Display display;

    @Inject
    private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;

    @Inject
    private ImagePresenter.Display imageDisplay;

    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        display.setViewShown(true);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        clearContainerAndAddTopLevelPanel(container, display);

        display.getResultsActionButtonsPanel().setWidget(imageFilteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(imageFilteredResultsDisplay.getPanel());

        getLocales(display);       

        bind();
    }

    /**
     * Add behaviour to the UI elements exposed by the views.
     */
    private void bind() {
        super.bind(display, this);

        final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = generateListProvider();
        imageFilteredResultsDisplay.setProvider(provider);

        bindListRowClicks();

        bindImageViewButtons(imageDisplay, display);

        bindImageSearchButtons();
    }

    protected void bindImageSearchButtons() {
        imageFilteredResultsDisplay.getSearch().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                eventBus.fireEvent(new ImagesFilteredResultsAndImageViewEvent(getQuery(imageFilteredResultsDisplay)));
            }
        });
    }

    /**
     * @return A provider to be used for the topic display list
     */
    private EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider() {
        final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTImageCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTImageCollectionItemV1> display) {
                imageData.setStartRow(display.getVisibleRange().getStart());
                final int length = display.getVisibleRange().getLength();
                final int end = imageData.getStartRow() + length;

                final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        imageFilteredResultsDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        imageFilteredResultsDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            imageData.setItems(retValue.getItems() == null ? new ArrayList<RESTImageCollectionItemV1>()
                                    : retValue.getItems());
                            displayAsynchronousList(imageData.getItems(), retValue.getSize(), imageData.getStartRow());
                        } finally {
                            imageFilteredResultsDisplay.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        imageFilteredResultsDisplay.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                RESTCalls.getImagesFromQuery(callback, queryString, imageData.getStartRow(), end);
            }
        };
        return provider;
    }

    /**
     * Bind the button click events for the topic editor screens.
     */
    private void bindListRowClicks() {
        imageFilteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTImageCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTImageCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * selectedSearchImage will be null until an image is selected for the first time
                     */
                    final boolean needToAddImageView = imageData.getSelectedItem() == null;

                    imageData.setSelectedItem(event.getValue());
                    imageData.setSelectedItem(event.getValue().clone(true));

                    final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                        @Override
                        public void begin() {
                            display.addWaitOperation();
                        }

                        @Override
                        public void generalException(final Exception e) {
                            display.removeWaitOperation();
                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        }

                        @Override
                        public void success(final RESTImageV1 retValue) {
                            try {
                                retValue.cloneInto(imageData.getDisplayedItem().getItem(), true);
                                reInitialiseImageView();

                                /*
                                 * If this is the first image selected, display the image view
                                 */
                                if (needToAddImageView) {
                                    display.getViewPanel().setWidget(imageDisplay.getPanel());
                                    display.getViewActionButtonsPanel().setWidget(imageDisplay.getTopActionPanel());
                                }
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

                    RESTCalls.getImage(callback, imageData.getSelectedItem().getItem().getId());
                }
            }
        });
    }

    @Override
    public void parseToken(final String historyToken) {

        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("imageIds")) {
                    this.imageFilteredResultsDisplay.getImageIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageDesc")) {
                    this.imageFilteredResultsDisplay.getImageDescriptionFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageOrigName")) {
                    this.imageFilteredResultsDisplay.getImageOriginalFileNameFilter().setText(queryElements[1]);
                }
            }
        }
    }

    @Override
    protected void reInitialiseImageView() {
        imageDisplay.initialize(imageData.getDisplayedItem().getItem(), getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons(imageDisplay, display);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        if (imageData.getSelectedItem() != null) {
            if (!imageData.getSelectedItem().getItem().getDescription()
                    .equals(imageData.getDisplayedItem().getItem().getDescription())) {
                return true;
            }
        }
        return false;
    }
}
