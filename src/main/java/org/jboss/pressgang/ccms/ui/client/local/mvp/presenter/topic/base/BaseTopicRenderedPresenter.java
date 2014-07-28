/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.isStringNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.impl.DOMParseException;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicRenderingInfoDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.DocBookUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.InjectionResolver;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;

public abstract class BaseTopicRenderedPresenter<T extends RESTBaseTopicV1<T, ?, ?>> extends BaseRenderedPresenter {
    private static final RegExp ENTITY_ERROR_RE = RegExp.compile(".*Entity '([\\w-\\.]+)' not defined.*");

    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTBaseTopicV1<? ,?, ?>> {
        boolean displayTopicRendered(final Integer topicXMLHoldID, final boolean readOnly, final boolean showImages);

        void displayError(final String errorMessage);

        void clear();

        void removeListener();

        FlexTable getLayoutPanel();

        ListBox getContentSpecs();

        CheckBox getRemarks();

        PushButton getRenderingInfo();

        TopicRenderingInfoDialog getRenderingInfoDialog();
    }

    /**
     * The condition set via the URL history token. Used when the view is used stand alone.
     */
    protected String conditionOverride;
    protected boolean standalone = false;

    @NotNull
    public abstract Display getDisplay();

    @Override
    protected void go() {
        bindExtended();

        /*
            Don't display the conditions when this view is used stand alone.
         */
        getDisplay().getTopActionGrandParentPanel().removeFromParent();
        standalone = true;
    }

    @Override
    public void close() {
        getDisplay().removeListener();
    }

    @Override
    public void bindExtended() {

    }

    protected void handleXMLError(final DOMParseException e) {
        final String errorMessage = e.getContents();

        final MatchResult entityError = ENTITY_ERROR_RE.exec(errorMessage);
        if (entityError != null) {
            getDisplay().displayError(PressGangCCMSUI.INSTANCE.UnableToRenderEntity().replace("#", entityError.getGroup(1)));
        } else {
            getDisplay().displayError(PressGangCCMSUI.INSTANCE.UnableToRenderGeneric());
        }
    }

    protected String processXML(final RESTXMLFormat xmlFormat, final String xml) throws DOMParseException {
        final Document doc = XMLUtilities.convertStringToDocument(xml);
        processXML(xmlFormat, doc);
        return doc.toString();
    }

    protected void processXML(final RESTXMLFormat xmlFormat, @NotNull final Document doc) {
        final JSONObject jsonValue = getSelectedContentSpecValue();

        // Apply the conditions
        String condition = "";
        if (jsonValue.containsKey("condition") || conditionOverride != null) {
            // conditionOverride overrides any selection in the combo box
            condition = conditionOverride != null ? conditionOverride : jsonValue.get("condition").isString().stringValue().trim();
        }

        // Account for no condition
        if (GWTUtilities.isStringNullOrEmpty(condition)) {
            condition = ServiceConstants.DEFAULT_CONDITION;
        }

        removeConditions(doc, condition);

        // Resolve any injections
        InjectionResolver.resolveInjections(xmlFormat, doc);
    }

    protected JSONObject getSelectedContentSpecValue() {
        // Check to see if we have a selected content spec to render for
        if (getDisplay().getContentSpecs().getSelectedIndex() != -1) {
            final String value = getDisplay().getContentSpecs().getValue(getDisplay().getContentSpecs()
                    .getSelectedIndex());
            if (!isStringNullOrEmpty(value)) {
                try {
                    return (JSONObject) JSONParser.parseStrict(value);
                } catch (Exception e) {

                }
            }
        }

        return new JSONObject();
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

    protected String cleanXMLAndAddAdditionalContent(final RESTXMLFormat xmlFormat, final String xml, final boolean showImages) {
        final boolean showRemarks = getDisplay().getRemarks().getValue();

        if (xmlFormat == RESTXMLFormat.DOCBOOK_50) {
            return DocBookUtilities.addDocBook50Namespaces(cleanXMLAndAddAdditionalContent(xml, showImages, showRemarks, standalone));
        } else {
            return cleanXMLAndAddAdditionalContent(xml, showImages, showRemarks, standalone);
        }
    }

    @Override
    protected String getDTD() {
        if (!standalone) {
            final JSONObject jsonValue = getSelectedContentSpecValue();
            if (jsonValue.containsKey("entities") && jsonValue.containsKey("customEntities")) {
                final String entities = jsonValue.get("entities").isString().stringValue();
                final String customEntities = jsonValue.get("customEntities").isString().stringValue();
                return DocBookDTD.getDtdDoctype(entities +  "\n" + customEntities);
            } else if (jsonValue.containsKey("entities")) {
                final String entities = jsonValue.get("entities").isString().stringValue();
                return DocBookDTD.getDtdDoctype(entities);
            } else if (jsonValue.containsKey("customEntities")) {
                final String customEntities = jsonValue.get("customEntities").isString().stringValue();
                return DocBookDTD.getDtdDoctype(customEntities);
            }
        }

        return super.getDTD();
    }

    protected String getXSLTemplate(final boolean showImages, final boolean showRemarks) {
        final String xsl;
        if (showImages)  {
            if (showRemarks) {
                xsl = Constants.DOCBOOK_REMARKS_XSL_REFERENCE;
            } else {
                xsl = Constants.DOCBOOK_XSL_REFERENCE;
            }
        } else {
            if (showRemarks) {
                xsl = Constants.DOCBOOK_REMARKS_PLACEHOLDER_XSL_REFERENCE;
            } else {
                xsl = Constants.DOCBOOK_PLACEHOLDER_XSL_REFERENCE;
            }
        }

        return xsl;
    }
}
