package org.jboss.pressgang.ccms.ui.client.local.ui.search.field;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseTopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public class TopicSearchUIFields extends BaseTopicSearchUIFields {
    public TopicSearchUIFields() {

    }

    public TopicSearchUIFields(@NotNull final RESTFilterV1 filter) {
        super(filter);
    }
}
