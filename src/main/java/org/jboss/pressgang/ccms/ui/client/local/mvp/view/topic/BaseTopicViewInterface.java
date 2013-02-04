package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import java.util.List;

/**

 */
public interface BaseTopicViewInterface extends BaseTemplateViewInterface {
    void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType, final List<String> locales, final Boolean showImages);
    /**
     * @return The GWT Editor Framework driver that is used to sync the data between the underlying POJOs and the UI Editor
     * elements, or null if the view does not use the Editor framework.
     */
    @SuppressWarnings("rawtypes")
    SimpleBeanEditorDriver getDriver();
}
