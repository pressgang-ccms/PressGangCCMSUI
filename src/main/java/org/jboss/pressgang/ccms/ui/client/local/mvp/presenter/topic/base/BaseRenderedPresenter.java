/*
  Copyright 2011-2014 Red Hat, Inc

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

        // If the root node is <authorgroup>, <legalnotice> or an info element then we need to wrap it in
        // <book><bookinfo>...</bookinfo></book> for it to render.
        if (retValue.matches("^\\s*<(authorgroup|legalnotice)(\\s|.)*")) {
            retValue = "<book><bookinfo>" + retValue + "</bookinfo></book>";
        } else if (retValue.matches("^\\s*<(sectioninfo|info)(\\s|.)*")) {
            // Change sectioninfo to bookinfo as it doesn't require a title
            retValue = retValue.replaceAll("<sectioninfo( .*?)?(?=>)", "<bookinfo")
                    .replaceAll("</sectioninfo( .*?)?(?=>)", "</bookinfo");
            retValue = "<book>" + retValue + "</book>";
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
