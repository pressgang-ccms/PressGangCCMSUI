package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls.RESTCallback;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The presenter that brings together all the topic views required to create a new topic
 *
 * @author Matthew Casperson
 */
@Dependent
public class CreateTopicPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "CreateTopicView";

    // region Properties
    /**
     * The container into which all the views will be placed
     */
    private HasWidgets container;
    /**
     * The topic that is being edited
     */
    private final RESTTopicV1 topic = new RESTTopicV1();
    /**
     * The view that was last displayed
     */
    private TopicViewInterface lastView;
    /**
     * How the rendered view is displayed
     */
    private SplitType split = SplitType.NONE;
    /**
     * The initial topic template
     */
    private RESTStringConstantV1 topicTemplate;
    // endregion Properties

    // region Click Handlers
    private final ClickHandler xmlEditingClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicXMLDisplay);

        }
    };

    private final ClickHandler viewClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicViewDisplay);

        }
    };

    private final ClickHandler renderedClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicRenderedDisplay);

        }
    };

    private final ClickHandler xmlErrorsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicXMLErrorsDisplay);

        }
    };

    private final ClickHandler tagsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicTagsDisplay);

        }
    };

    private final ClickHandler bugsClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicBugsDisplay);

        }
    };

    private final ClickHandler revisionClickHandler = new ClickHandler() {
        @Override
        public void onClick(final ClickEvent event) {
            changeDisplayedTopicView(topicRevisionsDisplay);

        }
    };
    // endregion Click Handlers

    // region Displays
    /**
     * The topic fields display
     */
    @Inject
    private TopicPresenter.Display topicViewDisplay;

    /**
     * The topic XML display
     */
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

    /**
     * The XML errors display
     */
    @Inject
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;

    /**
     * The topic tags display
     */
    @Inject
    private TopicTagsPresenter.Display topicTagsDisplay;

    /**
     * The topic bugs display
     */
    @Inject
    private TopicBugsPresenter.Display topicBugsDisplay;

    /**
     * The topic revisions display
     */
    @Inject
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;

    /**
     * A list containing all the displays
     */
    private final List<TopicViewInterface> displays = new ArrayList<TopicViewInterface>();

    // endregion Displays

    // region Presenter Lifecycle
    @Override
    public void parseToken(final String historyToken) {
        final String queryString = historyToken.replace(";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        this.container = container;

        /* Display the rendered view by default */
        changeDisplayedTopicView(topicRenderedDisplay);

        /* Populate the list of all the displays */
        displays.add(topicViewDisplay);
        displays.add(topicXMLDisplay);
        displays.add(topicRenderedDisplay);
        displays.add(topicSplitPanelRenderedDisplay);
        displays.add(topicXMLErrorsDisplay);
        displays.add(topicTagsDisplay);
        displays.add(topicBugsDisplay);
        displays.add(topicRevisionsDisplay);

        /* Add a tags collection to the topic */
        topic.explicitSetTags(new RESTTagCollectionV1());

        bind();
    }

    // endregion Presenter Lifecycle

    // region Data Binding and Event Handling
    private void bind() {
        bindTopicEditButtons();

        getTopicTemplate();
    }

    private void getTopicTemplate() {
        final RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1,
                TopicViewInterface>(lastView, new BaseRestCallback.SuccessAction<RESTStringConstantV1, TopicViewInterface>() {
                    @Override
                    public void doSuccessAction(RESTStringConstantV1 retValue, TopicViewInterface display) {
                        topicTemplate = retValue;
                        topic.setXml(topicTemplate.getValue());
                    }
                }) {
        };
        RESTCalls.getStringConstant(callback, ServiceConstants.CONCEPT_TOPIC_TEMPLATE);
    }

    /**
     * Bind behaviour to the standard action buttons displayed by the views
     */
    private void bindTopicEditButtons() {

        for (final TopicViewInterface display : displays) {
            display.getXml().addClickHandler(xmlEditingClickHandler);
            display.getFields().addClickHandler(viewClickHandler);
            display.getRendered().addClickHandler(renderedClickHandler);
            display.getXmlErrors().addClickHandler(xmlErrorsClickHandler);
            display.getTags().addClickHandler(tagsClickHandler);
            display.getBugs().addClickHandler(bugsClickHandler);
            display.getHistory().addClickHandler(revisionClickHandler);
        }

    }

    // endregion Data Binding

    // region View Management
    private void changeDisplayedTopicView(final TopicViewInterface view) {

        /* If we are moving to a new view, initialize it */
        if (lastView != view) {

            /* Clean up the last view */
            if (lastView != null) {

                if (lastView.getDriver() != null) {
                    lastView.getDriver().flush();
                }

                lastView.setViewShown(false);
            }

            container.clear();
            container.add(view.getTopLevelPanel());

            view.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);
            view.setViewShown(true);
        }

        /* Update the displayed topic */
        view.initialize(this.topic, false, split);

        /* The ACE editor needs to be redisplayed, but it has to be delayed slightly for some reason */
        if (view == this.topicXMLDisplay) {

            final Timer refreshTimer = new Timer() {
                @Override
                public void run() {
                    if (topicXMLDisplay.getEditor() != null)
                        topicXMLDisplay.getEditor().redisplay();
                }
            };
            refreshTimer.schedule(50);
        }

        lastView = view;
    }
    // endregion View Management
}
