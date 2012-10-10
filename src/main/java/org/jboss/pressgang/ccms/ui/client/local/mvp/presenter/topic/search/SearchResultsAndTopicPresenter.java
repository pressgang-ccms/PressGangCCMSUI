package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

/**
 * This presenter is used to display and wire selection of views, including the topic search results view, and the topic XML,
 * details, tags and XML Errors views.
 * 
 * @author Matthew Casperson
 */
@Dependent
public class SearchResultsAndTopicPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    public interface Display extends BaseTemplateViewInterface {
        SplitType getSplitType();

        DockLayoutPanel getResultsViewLayoutPanel();

        SimpleLayoutPanel getTopicResultsPanel();

        SimpleLayoutPanel getTopicViewPanel();

        SimpleLayoutPanel getTopicViewActionButtonsPanel();

        SimpleLayoutPanel getTopicResultsActionButtonsPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getTopicViewLayoutPanel();

        void initialize(final SplitType splitType, final Panel panel);
    }

    public interface LogicComponent extends Component<Display> {
        SplitType getSplit();
        void setSplit(final SplitType split);
        void parseToken(final String historyToken);
        String getQueryString();
        void setQueryString(final String queryString);
    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Inject
    private TopicPresenter.Display topicViewDisplay;

    @Inject
    private TopicXMLPresenter.Display topicXMLDisplay;

    /**
     * The rendered topic view display
     */
    @Inject
    private TopicRenderedPresenter.Display topicRenderedDisplay;

    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;

    @Inject
    private SearchResultsPresenter.Display searchResultsDisplay;

    @Inject
    private SearchResultsPresenter.LogicComponent searchResultsComponent;

    @Inject
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;

    @Inject
    private TopicTagsPresenter.Display topicTagsDisplay;

    @Inject
    private TopicBugsPresenter.Display topicBugsDisplay;

    @Inject
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;

    @Override
    public void go(final HasWidgets container) {
        
        searchResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        clearContainerAndAddTopLevelPanel(container, display);

        display.getTopicResultsActionButtonsPanel().setWidget(searchResultsDisplay.getTopActionPanel());
        display.getTopicResultsPanel().setWidget(searchResultsDisplay.getPanel());
        
        component.bind(display, display);
        component.setFeedbackLink(HISTORY_TOKEN);
        searchResultsComponent.bind(component.getQueryString(), searchResultsDisplay, display);

    }

    @Override
    public void parseToken(final String historyToken) {
        component.parseToken(historyToken);
    }

    
}
