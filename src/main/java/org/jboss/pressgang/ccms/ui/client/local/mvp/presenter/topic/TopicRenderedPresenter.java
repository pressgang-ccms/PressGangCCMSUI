package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.xml.client.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class TopicRenderedPresenter extends BaseTemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicRenderedView";

    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTBaseTopicV1<?, ?, ?>> {
        boolean displayTopicRendered(final Integer topicXMLHoldID, final boolean readOnly, final boolean showImages);

        void clear();

        void removeListener();

        FlexTable getiFrameParent();

        ListBox getConditions();
    }

    @Inject private FailOverRESTCall failOverRESTCall;

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
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {
        display.removeListener();
    }

    public void bindExtended() {
        super.bind(display);
    }

    public void displayTopicRendered(@Nullable final String topicXML, final boolean readOnly, final boolean showImages) {

        String xml = (showImages ? Constants.DOCBOOK_XSL_REFERENCE : Constants.DOCBOOK_PLACEHOLDER_XSL_REFERENCE) + "\n" + DocbookDTD.getDtdDoctype() + "\n" + GWTUtilities.removeAllPreabmle(topicXML);

        if (display.getConditions().getSelectedIndex() != -1) {
            final String condition = display.getConditions().getValue(display.getConditions().getSelectedIndex()).trim();
            if (!condition.isEmpty()) {
                xml = removeConditions(xml, RegExp.compile(condition));
            }
        }

        failOverRESTCall.performRESTCall(
                FailOverRESTCallDatabase.holdXML(xml),
                new RESTCallBack<IntegerWrapper>() {
                    public void success(@NotNull final IntegerWrapper value) {
                        display.displayTopicRendered(value.value, readOnly, showImages);
                    }
                },
                display,
                true
        );
    }

    private String removeConditions(@NotNull final String xml, @NotNull final RegExp condition) {
        try {
            // parse the XML document into a DOM
            final Document messageDom = XMLParser.parse(xml);

            removeConditions(messageDom.getDocumentElement(), condition);

            return messageDom.toString();

        } catch (@NotNull final DOMException e) {
            // fall through to return statement below
        }

        return xml;
    }

    private void removeConditions(@NotNull final Element element, @NotNull final RegExp condition) {
        if (element.hasAttribute(Constants.CONDITION_ATTRIBUTE)) {
            final String elementCondition = element.getAttribute(Constants.CONDITION_ATTRIBUTE);
            if (!condition.test(elementCondition)) {
                element.getParentNode().removeChild(element);
                return;
            }
        }

        for (int i = 0, length = element.getChildNodes().getLength(); i < length; ++i) {
            final Node childNode = element.getChildNodes().item(i);
            if (childNode instanceof Element) {
                removeConditions((Element)childNode, condition);
            }
        }
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        final String fixedToken = removeHistoryToken(historyToken, HISTORY_TOKEN);
        try {
            final Integer topicId = Integer.parseInt(fixedToken);

            final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                @Override
                public void success(@NotNull final RESTTopicV1 retValue) {
                    displayTopicRendered(retValue.getXml(), true, true);
                }
            };

            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback, display);

        } catch (@NotNull final NumberFormatException ex) {

        }
    }
}
