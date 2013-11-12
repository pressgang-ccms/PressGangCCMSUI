package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocbookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.utilities.InjectionResolver;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseTopicRenderedPresenter<T extends RESTBaseTopicV1<T, ?, ?>> extends BaseTemplatePresenter {
    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTBaseTopicV1<? ,?, ?>> {
        boolean displayTopicRendered(final Integer topicXMLHoldID, final boolean readOnly, final boolean showImages);

        void clear();

        void removeListener();

        FlexTable getLayoutPanel();

        ListBox getConditions();

        CheckBox getRemarks();
    }

    @Inject
    protected FailOverRESTCall failOverRESTCall;

    /**
     * The condition set via the URL history token. Used when the view is used stand alone.
     */
    protected String conditionOverride;

    @NotNull
    public abstract Display getDisplay();

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, getDisplay());
        bindExtended();

        /*
            Don't display the conditions when this view is used stand alone.
         */
        getDisplay().getLayoutPanel().getFlexCellFormatter().setVisible(0, 0, false);
    }

    @Override
    public void close() {
        getDisplay().removeListener();
    }

    public void bindExtended() {
        super.bind(getDisplay());
    }

    protected String processXML(final String xml) {
        final Document doc = XMLUtilities.convertStringToDocument(xml);
        if (doc != null) {
            processXML(doc);

            return doc.toString();
        }

        return xml;
    }

    protected void processXML(@NotNull final Document doc) {
        // Apply the conditions
        if (getDisplay().getConditions().getSelectedIndex() != -1 || conditionOverride != null) {

            /*
                conditionOverride overrides any selection in the combo box
             */
            final String condition = conditionOverride != null ? conditionOverride : getDisplay().getConditions().getValue(
                    getDisplay().getConditions().getSelectedIndex()).trim();

            /*
                Apply some special rules when no condition is specified
             */
            final String actualCondition = condition.isEmpty() ? ServiceConstants.DEFAULT_CONDITION : condition;

            removeConditions(doc, actualCondition);
        }

        // Resolve any injections
        InjectionResolver.resolveInjections(doc);
    }

    /**
     * Remove any XML elements that don't match the supplied condition
     *
     * @param doc       The XML to be parsed
     * @param condition The condition to be met
     * @return The XML with the elements removed
     */
    protected void removeConditions(@NotNull final Document doc, @NotNull final String condition) {
        try {

            // test the the regex is valid
            RegExp.compile(condition);

            removeConditions(doc.getDocumentElement(), condition);

        } catch (@NotNull final DOMException e) {
            // fall through to return statement below
        } catch (@NotNull final RuntimeException ex) {
            // regex did not compile. fall through to return statement below
        }
    }

    protected boolean removeConditions(@NotNull final Element element, @NotNull final String condition) {
        if (element.hasAttribute(Constants.CONDITION_ATTRIBUTE)) {
            final String elementCondition = element.getAttribute(Constants.CONDITION_ATTRIBUTE);

            /*
                The condition attribute value is split using one of three characters. See
                 DocBookUtilities.getConditionNodes() for the code that does this with
                 the content spec processor.
             */
            final String[] individualConditions = elementCondition.split("\\s*(;|,)\\s*");

            boolean match = false;
            for (@NotNull final String individualCondition : individualConditions) {
                if (individualCondition.matches(condition)) {
                    match = true;
                    break;
                }
            }

            if (!match) {
                return false;
            }
        }

        final List<Element> removedElements = new ArrayList<Element>();
        final NodeList children = element.getChildNodes();
        for (int i = 0, length = children.getLength(); i < length; ++i) {
            final Node childNode = children.item(i);
            if (childNode instanceof Element) {
                if (!removeConditions((Element) childNode, condition)) {
                    removedElements.add((Element) childNode);
                }
            }
        }

        for (@NotNull final Element removedElement : removedElements) {
            element.removeChild(removedElement);
        }

        return true;
    }

    public abstract void displayTopicRendered(final T topic, final boolean readOnly, final boolean showImages);

    protected String cleanXMLAndAddAdditionalContent(final String xml, final boolean showImages) {
        String retValue = XMLUtilities.addLineNumberAttributesToXML(XMLUtilities.removeAllPreamble(xml));

        // If the root node is <authorgroup> or <legalnotice> then we need to wrap it in
        // <book><bookinfo>...</bookinfo></book> for it to render.
        if (retValue.matches("^\\s*<(authorgroup|legalnotice)(\\s|.)*")) {
            retValue = "<book><bookinfo>" + retValue + "</bookinfo></book>";
        }

        String xsl = null;
        if (showImages)  {
            if (getDisplay().getRemarks().getValue()) {
                xsl = Constants.DOCBOOK_REMARKS_XSL_REFERENCE;
            } else {
                xsl = Constants.DOCBOOK_XSL_REFERENCE;
            }
        } else {
            if (getDisplay().getRemarks().getValue()) {
                xsl = Constants.DOCBOOK_REMARKS_PLACEHOLDER_XSL_REFERENCE;
            } else {
                xsl = Constants.DOCBOOK_PLACEHOLDER_XSL_REFERENCE;
            }
        }

        return xsl + "\n" + DocbookDTD.getDtdDoctype() + "\n" + retValue;
    }
}
