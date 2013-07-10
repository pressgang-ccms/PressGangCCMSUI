package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class WelcomePresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "WelcomeView";

    public interface Display extends BaseTemplateViewInterface {
        void displayTopicRendered(final Integer topicXMLHoldID);
    }

    @Inject private FailOverRESTCall failOverRESTCall;

    @Inject
    private Display display;

    @Override
    public void go(@NotNull final HasWidgets container) {
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.HOME_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    public void bindExtended(final int topicId, @NotNull final String pageId) {
        super.bind(topicId, pageId, display);

        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.getTopic(ServiceConstants.WELCOME_VIEW_CONTENT_TOPIC),
                new RESTCallBack<RESTTopicV1>() {
                    public void success(@NotNull final RESTTopicV1 value) {
                        final String xml = Constants.DOCBOOK_XSL_REFERENCE + "\n" + value.getXml();
                        failOverRESTCall.performRESTCall(
                                FailOverRESTCallDatabase.holdXML(xml),
                                new RESTCallBack<IntegerWrapper>() {
                                    public void success(@NotNull final IntegerWrapper value) {
                                        display.displayTopicRendered(value.value);
                                    }
                                },
                                display
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
