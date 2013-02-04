package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicViewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TopicXMLErrorsPresenter extends BaseTopicViewPresenter implements BaseTemplatePresenterInterface {
    public static final String HISTORY_TOKEN = "TopicXMLErrorsView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLErrorsPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLErrorsEditor> {
    }

    public interface Display extends BaseTopicViewInterface {
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
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int helpTopicId, final String pageId)
    {
        super.bind(helpTopicId, pageId, display);
    }
}
