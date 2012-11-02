package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagePresenter.Display.ImagePresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.ProviderUpdateData;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.image.RESTImageV1Editor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;

@Dependent
public class ImagePresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "ImageView";

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    /**
     * The id of the image to display, extracted from the history token.
     */
    private Integer imageId;

    public interface Display extends BaseTemplateViewInterface {
        // Empty interface declaration, similar to UiBinder
        public interface ImagePresenterDriver extends SimpleBeanEditorDriver<RESTImageV1, RESTImageV1Editor> {
        }

        public interface AddLocaleInterface {
            PushButton getCancel();

            PushButton getOk();

            ListBox getLocales();

            DialogBox getDialogBox();
        }

        void initialize(final RESTImageV1 image, final String[] locales);

        PushButton getRemoveLocale();

        PushButton getAddLocale();

        PushButton getSave();

        AddLocaleInterface getAddLocaleDialog();

        RESTImageV1Editor getEditor();
        
        ImagePresenterDriver getDriver();
    }    

    public interface LogicComponent extends Component<Display> {
        void getEntity(final Integer imageId);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
       
        component.bind(display, display);
        component.getEntity(imageId);
        component.setFeedbackLink(HISTORY_TOKEN);
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
}