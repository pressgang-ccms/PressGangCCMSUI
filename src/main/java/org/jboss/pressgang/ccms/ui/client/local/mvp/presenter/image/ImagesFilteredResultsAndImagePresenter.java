package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.ImagesFilteredResultsAndImageViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.image.ImagesFilteredResultsAndImageView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HanldedSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

@Dependent
public class ImagesFilteredResultsAndImagePresenter extends ImagePresenterBase implements EditableView {
   
    public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";
    
    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getResultsPanel();

        SimpleLayoutPanel getViewPanel();

        SimpleLayoutPanel getViewActionButtonsPanel();

        SimpleLayoutPanel getResultsActionButtonsPanel();

        HanldedSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getViewLayoutPanel();
    }

    @Inject
    private Display display;

    @Inject
    private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;

    @Inject
    private ImagePresenter.Display imageDisplay;

    private String queryString;

    /** Keeps a reference to the start row. */
    private Integer tableStartRow;

    /** Keeps a reference to the list of topics being displayed. */
    private List<RESTImageV1> currentList;

    /** The currently selected image in the search results.*/
    private RESTImageV1 selectedSearchImage;

    @Override
    public void go(final HasWidgets container) {
        display.setViewShown(true);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
        
        container.clear();
        container.add(display.getTopLevelPanel());

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

        final EnhancedAsyncDataProvider<RESTImageV1> provider = generateListProvider();
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
    private EnhancedAsyncDataProvider<RESTImageV1> generateListProvider() {
        final EnhancedAsyncDataProvider<RESTImageV1> provider = new EnhancedAsyncDataProvider<RESTImageV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTImageV1> display) {
                tableStartRow = display.getVisibleRange().getStart();
                final int length = display.getVisibleRange().getLength();
                final int end = tableStartRow + length;

                final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>() {
                    @Override
                    public void begin() {
                        resetProvider();
                        imageFilteredResultsDisplay.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception ex) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        imageFilteredResultsDisplay.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageCollectionV1 retValue) {
                        try {
                            /* Zero results can be a null list */
                            currentList = retValue.getItems() == null ? new ArrayList<RESTImageV1>() : retValue.getItems();
                            displayAsynchronousList(currentList, retValue.getSize(), tableStartRow);
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

                RESTCalls.getImagesFromQuery(callback, queryString, tableStartRow, end);
            }
        };
        return provider;
    }

    /**
     * Bind the button click events for the topic editor screens.
     */
    private void bindListRowClicks() {
        imageFilteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTImageV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTImageV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * selectedSearchImage will be null until an image is selected for the first time
                     */
                    final boolean needToAddImageView = selectedSearchImage == null;

                    selectedSearchImage = event.getValue();
                    displayedImage = null;

                    final RESTCalls.RESTCallback<RESTImageV1> callback = new RESTCalls.RESTCallback<RESTImageV1>() {
                        @Override
                        public void begin() {
                            display.addWaitOperation();
                        }

                        @Override
                        public void generalException(final Exception ex) {
                            display.removeWaitOperation();
                            Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                        }

                        @Override
                        public void success(final RESTImageV1 retValue) {
                            try {
                                displayedImage = retValue;
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

                    RESTCalls.getImage(callback, selectedSearchImage.getId());
                }
            }
        });
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = historyToken.replace(HISTORY_TOKEN + ";", "");
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
        imageDisplay.initialize(displayedImage, getUnassignedLocales().toArray(new String[0]));

        bindImageUploadButtons(imageDisplay);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
