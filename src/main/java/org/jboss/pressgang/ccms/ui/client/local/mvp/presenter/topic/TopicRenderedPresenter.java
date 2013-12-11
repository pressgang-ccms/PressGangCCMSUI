package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TopicRenderedPresenter extends BaseTopicRenderedPresenter<RESTTopicV1> {
    public static final String HISTORY_TOKEN = "TopicRenderedView";

    public interface Display extends BaseTopicRenderedPresenter.Display {

    }

    /**
     * The rendered topic view display
     */
    @Inject
    private Display display;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);

        /*
         * The history token is expected to be the topic id, optionally with a semicolon
         * and then an encoded condition.
         */
        try {
            final String[] tokens = fixedToken.split(";");

            if (tokens.length > 0) {

                // Set the condition override if it is present.
                if (tokens.length > 1) {
                    conditionOverride = URL.decode(tokens[1]);
                }

                final Integer topicId = Integer.parseInt(tokens[0]);

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        displayTopicRendered(retValue, true, true);
                    }
                };

                getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback, display);
            }

        } catch (@NotNull final NumberFormatException ex) {

        }
    }

    @Override
    public void displayTopicRendered(final RESTTopicV1 topic, final boolean readOnly, final boolean showImages) {
        try {
            String xml = cleanXMLAndAddAdditionalContent(topic.getXml(), showImages);

            xml = processXML(xml);

            getFailOverRESTCall().performRESTCall(FailOverRESTCallDatabase.holdXML(xml),
                    new RESTCallBack<IntegerWrapper>() {
                        @Override
                        public void success(@NotNull final IntegerWrapper value) {
                            getDisplay().displayTopicRendered(value.value, readOnly, showImages);
                        }
                    }, getDisplay(), true);
        } catch (DOMParseException e) {
            handleXMLError(e);
        }
    }
}