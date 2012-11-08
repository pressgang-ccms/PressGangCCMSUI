package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.LogMessageInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
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
 * The presneter that manages the topic search screen, combining the tags, fields and filters views
 * 
 * @author Matthew Casperson
 */
@Dependent
public class CreateTopicPresenter implements TemplatePresenter {

    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "CreateTopicView";

    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getTopicViewActionButtonsPanel();

        DockLayoutPanel getTopicViewLayoutPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        SimpleLayoutPanel getTopicViewPanel();

        void initialize(final SplitType splitType, final Panel panel);
        
        LogMessageInterface getMessageLogDialog();
    }

    public interface LogicComponent extends Component<Display> {
        void bind(final TopicPresenter.Display topicViewDisplay, final TopicPresenter.LogicComponent topicViewComponent,
                final TopicXMLPresenter.Display topicXMLDisplay, final TopicXMLPresenter.LogicComponent topicXMLComponent,
                final TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay,
                final TopicXMLErrorsPresenter.LogicComponent topicXMLErrorsComponent,
                final TopicTagsPresenter.Display topicTagsDisplay, final TopicTagsPresenter.LogicComponent topicTagsComponent,
                final TopicRenderedPresenter.Display topicRenderedDisplay,
                final TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay,
                final CreateTopicPresenter.Display display, final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;
    @Inject
    private LogicComponent component;

    /** The view that displays the tag options */
    @Inject
    private TopicPresenter.Display topic;

    /** The view that displays the tag options */
    @Inject
    private TopicTagsPresenter.Display topicTags;

    /** The view that displays the xml editor */
    @Inject
    private TopicXMLPresenter.Display topicXML;

    /** The view that displays the xml editor */
    @Inject
    private TopicXMLErrorsPresenter.Display topicXMLErrors;

    /** The component that provides logic for the topic view */
    @Inject
    private TopicPresenter.LogicComponent topicComponent;

    /** The component that provides logic for the tags view */
    @Inject
    private TopicTagsPresenter.LogicComponent topicTagsComponent;

    /** The component that provides logic for the topic xml view */
    @Inject
    private TopicXMLPresenter.LogicComponent topicXMLComponent;

    /** The component that provides logic for the topic xml errors view */
    @Inject
    private TopicXMLErrorsPresenter.LogicComponent topicXMLErrorsComponent;

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

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        topicComponent.bind(topic, display);
        topicTagsComponent.bind(topicTags, display);
        topicXMLComponent.bind(topicXML, display);
        topicXMLErrorsComponent.bind(topicXMLErrors, display);

        component.bind(topic, topicComponent, topicXML, topicXMLComponent, topicXMLErrors, topicXMLErrorsComponent, topicTags,
                topicTagsComponent, topicRenderedDisplay, topicSplitPanelRenderedDisplay, display, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }
}
