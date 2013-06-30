package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Logger;

public class TopicView extends BaseTemplateView implements TopicPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicView.class.getName());

    /**
     * The GWT Editor Driver
     */
    private final TopicPresenterDriver driver = GWT.create(TopicPresenterDriver.class);

    @Override
    public TopicPresenterDriver getDriver() {
        return driver;
    }

    public TopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Properties());
        LOGGER.info("ENTER TopicView()");
    }

    @Override
    public void display(final RESTTopicV1 topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicView.display() is not supported. Use TopicView.displayTopicDetails() instead.");
    }

    @Override
    public void displayTopicDetails(@NotNull final RESTTopicV1 topic, final boolean readOnly, @NotNull final List<String> locales) {
        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTTopicV1BasicDetailsEditor editor = new RESTTopicV1BasicDetailsEditor(readOnly, locales);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

}
