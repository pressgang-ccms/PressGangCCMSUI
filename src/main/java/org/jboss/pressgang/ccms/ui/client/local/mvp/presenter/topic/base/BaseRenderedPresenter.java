package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.DocBookDTD;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.utilities.DocBookUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;

public abstract class BaseRenderedPresenter extends BaseTemplatePresenter {

    protected String cleanXMLAndAddAdditionalContent(final String xml, final boolean showImages, boolean showRemarks,
            boolean escapeCustomEntities) {
        String retValue = XMLUtilities.addLineNumberAttributesToXML(XMLUtilities.removeAllPreamble(xml));

        if (escapeCustomEntities) {
            retValue = DocBookUtilities.escapeAllCustomEntities(retValue);
        }

        // If the root node is <authorgroup> or <legalnotice> then we need to wrap it in
        // <book><bookinfo>...</bookinfo></book> for it to render.
        if (retValue.matches("^\\s*<(authorgroup|legalnotice)(\\s|.)*")) {
            retValue = "<book><bookinfo>" + retValue + "</bookinfo></book>";
        }

        final String xslTemplate = getXSLTemplate(showImages, showRemarks);
        return xslTemplate + "\n" + getDTD() + "\n" + retValue;
    }

    protected String getDTD() {
        return DocBookDTD.getDtdDoctype();
    }

    protected String getXSLTemplate(boolean showImages, boolean showRemarks) {
        return Constants.DOCBOOK_XSL_REFERENCE;
    }
}
