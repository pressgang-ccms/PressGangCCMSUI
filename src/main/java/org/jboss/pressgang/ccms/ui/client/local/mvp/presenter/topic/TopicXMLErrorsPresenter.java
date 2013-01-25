package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.TopicViewComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicXMLErrorsPresenter extends TopicViewComponent<TopicXMLErrorsPresenter.Display> implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicXMLErrorsView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLErrorsPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLErrorsEditor> {
    }

    public interface Display extends TopicViewInterface {
    }

    private Integer topicId;

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void parseToken(final String searchToken) {

        try
        {
            topicId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        }
        catch (final NumberFormatException ex)
        {
            topicId = null;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(topicId, ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    public void process(final Integer topicId, final int helpTopicId, final String pageId, final BaseTemplateViewInterface waitDisplay) {
        getTopic(topicId);
    }

    private void getTopic(final Integer topicId) {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, Display>() {
                    @Override
                    public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                        display.initialize(retValue, false, false, SplitType.DISABLED, null, false);
                    }
                }) ;

        if (topicId != null)
        {
            RESTCalls.getTopic(callback,topicId);
        }
    }
}
