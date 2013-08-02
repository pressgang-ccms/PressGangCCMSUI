package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic;

import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseTopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;

public final class TopicSearchFieldUIEditor extends BaseTopicSearchFieldUIEditor<TopicSearchUIFields> {

    public TopicSearchFieldUIEditor() {
        addBasicFields();
        addMatchFields();
    }
}
