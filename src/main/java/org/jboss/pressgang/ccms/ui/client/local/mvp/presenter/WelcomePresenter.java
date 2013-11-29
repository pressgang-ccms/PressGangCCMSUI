package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

@Dependent
public class WelcomePresenter extends BaseRenderedPresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "WelcomeView";

    public interface Display extends BaseTemplateViewInterface {
        void displayTopicRendered(final Integer topicXMLHoldID);
        PushButton getEdit();
    }

    @Inject
    private Display display;

    @Override
    public void go(@NotNull final HasWidgets container) {
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {
        super.bind(display);

        display.getEdit().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent clickEvent) {
                getEventBus().fireEvent(new TopicSearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX +
                        CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId(), false));
            }
        });

        getFailOverRESTCall().performRESTCall(
                FailOverRESTCallDatabase.getTopic(ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 value) {
                        final String xml = cleanXMLAndAddAdditionalContent(value.getXml(), true, true, true);
                        getFailOverRESTCall().performRESTCall(
                                FailOverRESTCallDatabase.holdXML(xml),
                                new RESTCallBack<IntegerWrapper>() {
                                    public void success(@NotNull final IntegerWrapper value) {
                                        display.displayTopicRendered(value.value);
                                    }
                                },
                                display,
                                true
                        );
                    }
                },
                display
        );
    }


    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
