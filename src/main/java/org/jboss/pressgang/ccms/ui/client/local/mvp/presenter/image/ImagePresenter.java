package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImagePresenter extends ComponentBase implements TemplatePresenter {

    public interface Display extends BaseTemplateViewInterface, BaseEditorViewInterface<RESTImageV1, RESTImageV1Editor> {

        public interface AddLocaleInterface {
            PushButton getCancel();

            PushButton getOk();

            ListBox getLocales();

            DialogBox getDialogBox();
        }

        void initialize(final RESTImageV1 image, final String[] locales);

        PushButton getRemoveLocale();

        PushButton getAddLocale();

        PushButton getViewImage();

        PushButton getFindTopics();

        PushButton getSave();

        AddLocaleInterface getAddLocaleDialog();

        RESTImageV1Editor getEditor();

    }

    // Empty interface declaration, similar to UiBinder
    public interface ImagePresenterDriver extends SimpleBeanEditorDriver<RESTImageV1, RESTImageV1Editor> {
    }

    public static final String HISTORY_TOKEN = "ImageView";

    @Inject
    private Display display;

    /**
     * The id of the image to display, extracted from the history token.
     */
    private Integer imageId;

    public Display getDisplay()
    {
        return display;
    }

    /**
     * Default constructor. Does nothing.
     */
    public ImagePresenter() {

    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);

    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            imageId = Integer.parseInt(removeHistoryToken(historyToken, HISTORY_TOKEN));
        } catch (final Exception ex) {
            // bad history token. silently fail
            imageId = null;
        }
    }

    public void process(final int topicId, final String pageId) {
        super.bind(topicId, pageId, display);
        getEntity(imageId);

    }

    public void getEntity(final Integer imageId) {
        final RESTCalls.RESTCallback<RESTImageV1> callback = new BaseRestCallback<RESTImageV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTImageV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTImageV1 retValue, final Display display) {
                        display.initialize(retValue, new String[] {});
                    }
                }) {
        };

        if (imageId != null) {
            RESTCalls.getImage(callback, imageId);
        }
    }
}
