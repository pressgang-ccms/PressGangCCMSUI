package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.TopicSearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class WelcomePresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "WelcomeView";

    /**
     * The GWT event bus.
     */
    @Inject
    private EventBus eventBus;

    public interface Display extends BaseTemplateViewInterface {
        void displayTopicRendered(final Integer topicXMLHoldID);
        PushButton getEdit();
    }

    @Inject private FailOverRESTCall failOverRESTCall;

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
                eventBus.fireEvent(new TopicSearchResultsAndTopicViewEvent(Constants.QUERY_PATH_SEGMENT_PREFIX +
                        CommonFilterConstants.TOPIC_IDS_FILTER_VAR + "=" + ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId(), false));
            }
        });

        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopic(ServiceConstants.HELP_TOPICS.WELCOME_VIEW_CONTENT_TOPIC.getId()),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 value) {
                        final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + DocbookDTD.getDtdDoctype() + "\n" + XMLUtilities.removeAllPreamble(
                                value.getXml());
                        failOverRESTCall.performRESTCall(
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
