package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

public class RESTTopicV1XMLErrorsEditor extends SimplePanel implements Editor<RESTTopicV1> {
    public final TextArea xmlErrors = new TextArea();

    public RESTTopicV1XMLErrorsEditor() {
        this.addStyleName(CSSConstants.TOPICXMLERRORSVIEWPANEL);
        xmlErrors.addStyleName(CSSConstants.TOPICXMLERRORSVIEWFIELD);
        xmlErrors.setReadOnly(true);

        this.setWidget(xmlErrors);
    }
}
