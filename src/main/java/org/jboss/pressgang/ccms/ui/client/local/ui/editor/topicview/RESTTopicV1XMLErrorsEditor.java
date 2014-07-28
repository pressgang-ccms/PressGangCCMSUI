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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

public class RESTTopicV1XMLErrorsEditor extends SimplePanel implements Editor<RESTBaseTopicV1<?, ?, ?>> {
    public final TextArea xmlErrors = new TextArea();

    public RESTTopicV1XMLErrorsEditor() {
        this.addStyleName(CSSConstants.TopicView.TOPIC_XML_ERRORS_VIEW_PANEL);
        xmlErrors.addStyleName(CSSConstants.TopicView.TOPIC_XML_ERRORS_VIEW_FIELD);
        xmlErrors.setReadOnly(true);

        this.setWidget(xmlErrors);
    }
}
