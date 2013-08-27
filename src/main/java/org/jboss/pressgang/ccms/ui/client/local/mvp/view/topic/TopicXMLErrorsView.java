package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter.TopicXMLErrorsPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLErrorsEditor;
import org.jetbrains.annotations.NotNull;

public class TopicXMLErrorsView extends BaseTemplateView implements TopicXMLErrorsPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicXMLErrorsView.class.getName());

    /**
     * The GWT Editor Driver
     */
    private final TopicXMLErrorsPresenterDriver driver = GWT.create(TopicXMLErrorsPresenterDriver.class);

    @Override
    public TopicXMLErrorsPresenterDriver getDriver() {
        return driver;
    }

    public TopicXMLErrorsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.XMLValidationErrors());
        LOGGER.info("ENTER TopicXMLErrorsView()");
    }


    @Override
    public void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {
        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTTopicV1XMLErrorsEditor editor = new RESTTopicV1XMLErrorsEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

}
