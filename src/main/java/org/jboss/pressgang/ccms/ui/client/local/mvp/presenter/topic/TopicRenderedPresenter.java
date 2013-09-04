package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.http.client.URL;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.xml.client.*;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.wrapper.IntegerWrapper;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
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

        FlexTable getLayoutPanel();

        ListBox getConditions();
    }

    @Inject private FailOverRESTCall failOverRESTCall;

    /**
     * The rendered topic view display
     */
    @Inject
    private Display display;

    /**
     * The condition set via the URL history token. Used when the view is used stand alone.
     */
    private String conditionOverride;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();

        /*
            Don't display the conditions when this view is used stand alone.
         */
        display.getLayoutPanel().getFlexCellFormatter().setVisible(0, 0, false);
    }

    @Override
    public void close() {
        display.removeListener();
    }

    public void bindExtended() {
        super.bind(display);
    }

    public void displayTopicRendered(@Nullable final String topicXML, final boolean readOnly, final boolean showImages) {

        String xml = (showImages ? Constants.DOCBOOK_XSL_REFERENCE : Constants.DOCBOOK_PLACEHOLDER_XSL_REFERENCE) + "\n" + DocbookDTD.getDtdDoctype() + "\n" + XMLUtilities.removeAllPreamble(
                topicXML);

        if (display.getConditions().getSelectedIndex() != -1 || conditionOverride != null) {

            /*
                conditionOverride overrides any selection in the combo box
             */
            final String condition = conditionOverride != null ?
                    conditionOverride :
                    display.getConditions().getValue(display.getConditions().getSelectedIndex()).trim();

            /*
                Apply some special rules when no condition is specified
             */
            final String actualCondition =  condition.isEmpty() ? ServiceConstants.DEFAULT_CONDITION : condition;

            xml = removeConditions(xml, actualCondition);
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

    /**
     * Remove any XML elements that don't match the supplied condition
     * @param xml The XML to be parsed
     * @param condition The condition to be met
     * @return The XML with the elements removed
     */
    private String removeConditions(@NotNull final String xml, @NotNull final String condition) {
        try {
            // parse the XML document into a DOM
            final Document messageDom = XMLParser.parse(xml);

            // test the the regex is valid
            RegExp.compile(condition);

            removeConditions(messageDom.getDocumentElement(), condition);

            return messageDom.toString();

        } catch (@NotNull final DOMException e) {
            // fall through to return statement below
        } catch (@NotNull final RuntimeException ex) {
            // regex did not compile. fall through to return statement below
        }

        return xml;
    }

    private void removeConditions(@NotNull final Element element, @NotNull final String condition) {
        if (element.hasAttribute(Constants.CONDITION_ATTRIBUTE)) {
            final String elementCondition = element.getAttribute(Constants.CONDITION_ATTRIBUTE);

            /*
                The condition attribute value is split using one of three characters. See
                 DocBookUtilities.getConditionNodes() for the code that does this with
                 the content spec processor.
             */
            final String[] individualConditions =  elementCondition.split("\\s*(;|,)\\s*");

            boolean match = false;
            for (@NotNull final String individualCondition : individualConditions)  {
                if (individualCondition.matches(condition)) {
                    match = true;
                    break;
                }
            }

            if (!match) {
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

        /*
            The history token is expected to be the topic id, optionally with a semicolon
            and then an encoded condition.
         */

        try {
            final String[] tokens = fixedToken.split(";");

            if (tokens.length > 0) {

                /*
                    Set the condition override if it is present.
                 */
                if (tokens.length > 1) {
                    conditionOverride = URL.decode(tokens[1]);
                }

                final Integer topicId = Integer.parseInt(tokens[0]);

                final RESTCallBack<RESTTopicV1> callback = new RESTCallBack<RESTTopicV1>() {
                    @Override
                    public void success(@NotNull final RESTTopicV1 retValue) {
                        displayTopicRendered(retValue.getXml(), true, true);
                    }
                };

                failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTopic(topicId), callback, display);
            }

        } catch (@NotNull final NumberFormatException ex) {

        }
    }
}
