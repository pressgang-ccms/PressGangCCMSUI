package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

public class RESTTopicV1XMLErrorsEditor extends SimplePanel implements Editor<RESTBaseTopicV1<?, ?, ?>> {
    public final TextArea xmlErrors = new TextArea();

    public RESTTopicV1XMLErrorsEditor() {
        this.addStyleName(CSSConstants.TOPIC_XML_ERRORS_VIEW_PANEL);
        xmlErrors.addStyleName(CSSConstants.TOPIC_XML_ERRORS_VIEW_FIELD);
        xmlErrors.setReadOnly(true);

        this.setWidget(xmlErrors);
    }
}
