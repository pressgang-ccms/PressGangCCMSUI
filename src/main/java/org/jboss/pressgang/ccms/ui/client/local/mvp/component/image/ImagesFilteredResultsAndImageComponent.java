package org.jboss.pressgang.ccms.ui.client.local.mvp.component.image;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;

public class ImagesFilteredResultsAndImageComponent extends ComponentBase<ImagesFilteredResultsAndImagePresenter.Display>
        implements ImagesFilteredResultsAndImagePresenter.LogicComponent {

    @Override
    public void bind(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);

        bindListRowClicks(imageFilteredResultsDisplay, imageFilteredResultsComponent, imageDisplay, imageComponent, display,
                waitDisplay);
    }

    /**
     * Bind the button click events for the topic editor screens.
     */
    private void bindListRowClicks(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        imageFilteredResultsDisplay.getResults().addCellPreviewHandler(new Handler<RESTImageCollectionItemV1>() {
            @Override
            public void onCellPreview(final CellPreviewEvent<RESTImageCollectionItemV1> event) {
                /* Check to see if this was a click event */
                final boolean isClick = Constants.JAVASCRIPT_CLICK_EVENT.equals(event.getNativeEvent().getType());

                if (isClick) {
                    /*
                     * selectedSearchImage will be null until an image is selected for the first time
                     */
                    final boolean needToAddImageView = imageFilteredResultsComponent.getProviderData().getSelectedItem() == null;

                    imageFilteredResultsComponent.getProviderData().setDisplayedItem(event.getValue());
                    imageFilteredResultsComponent.getProviderData().setSelectedItem(event.getValue().clone(true));

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
                                /*
                                 * Do a shallow copy here, because Chrome has issues with System.arraycopy - see
                                 * http://code.google.com/p/chromium/issues/detail?id=56588
                                 */
                                retValue.cloneInto(
                                        imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), false);

                                reInitialiseImageView(imageFilteredResultsDisplay, imageFilteredResultsComponent, imageDisplay,
                                        imageComponent, display, waitDisplay);

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

                    RESTCalls.getImage(callback, imageFilteredResultsComponent.getProviderData().getSelectedItem().getItem()
                            .getId());
                }
            }
        });
    }

    private void reInitialiseImageView(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        imageDisplay.initialize(imageFilteredResultsComponent.getProviderData().getDisplayedItem().getItem(), imageComponent
                .getUnassignedLocales().toArray(new String[0]));

        imageComponent.bindImageUploadButtons(imageDisplay, waitDisplay);
    }

    private boolean checkForUnsavedChanges(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
            final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
            final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
            final ImagesFilteredResultsAndImagePresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        if (imageComponent.getImageData().getSelectedItem() != null) {
            if (!imageComponent.getImageData().getSelectedItem().getItem().getDescription()
                    .equals(imageComponent.getImageData().getDisplayedItem().getItem().getDescription())) {
                return true;
            }
        }
        return false;
    }
}
