package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TagPresenter extends ComponentBase<TagPresenter.Display> implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "TagView";

    // Empty interface declaration, similar to UiBinder
    public interface TagPresenterDriver extends SimpleBeanEditorDriver<RESTTagV1, RESTTagV1BasicDetailsEditor> {
    }

    public interface Display extends TagViewInterface, BaseEditorViewInterface<RESTTagV1, RESTTagV1BasicDetailsEditor> {

    }

    private Integer tagId;

    @Inject
    private Display display;

    public Display getDisplay() {
        return display;
    }

    public void getEntity(final Integer tagId) {
        final RESTCalls.RESTCallback<RESTTagV1> callback = new BaseRestCallback<RESTTagV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTagV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTTagV1 retValue, final Display display) {
                        display.initialize(retValue, false);
                    }
                });
        RESTCalls.getUnexpandedTag(callback, tagId);
    }

    @Override
    public void parseToken(final String searchToken) {
        try {
            tagId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (final NumberFormatException ex) {
            tagId = null;
        }

    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    public void process(final int topicId, final String pageId, final BaseTemplateViewInterface waitDisplay) {
        super.bind(topicId, pageId, display, waitDisplay);

        if (tagId != null)
        {
            getEntity(tagId);
        }
    }
}
