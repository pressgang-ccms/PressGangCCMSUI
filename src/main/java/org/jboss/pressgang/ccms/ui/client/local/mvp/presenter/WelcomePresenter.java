package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
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

        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, Display>(
                display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, WelcomePresenter.Display>() {

                    @Override
                    public void doSuccessAction(@NotNull final RESTTopicV1 retValue, @NotNull final Display display) {
                        final BaseRestCallback<IntegerWrapper, Display> callback = new BaseRestCallback<IntegerWrapper, Display>(display,
                                new BaseRestCallback.SuccessAction<IntegerWrapper, Display>() {
                                    @Override
                                    public void doSuccessAction(@NotNull final IntegerWrapper retValue, @NotNull final Display display) {
                                        display.displayTopicRendered(retValue.value);
                                    }
                                }, true);

                        RESTCalls.holdXml(callback, Constants.DOCBOOK_XSL_REFERENCE + "\n" + retValue.getXml());
                    }

                });
        RESTCalls.getTopic(callback, ServiceConstants.WELCOME_VIEW_CONTENT_TOPIC);
    }


    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
