package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicViewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TopicRenderedPresenter extends BaseTopicViewPresenter implements BaseTemplatePresenterInterface {
    public static final String HISTORY_TOKEN = "TopicRenderedView";
    
    private String topicId;

    public interface Display extends BaseTopicViewInterface {

    }

    /**
     * The rendered topic view display
     */
    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int topicId, final String pageId)
    {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
    }
}
