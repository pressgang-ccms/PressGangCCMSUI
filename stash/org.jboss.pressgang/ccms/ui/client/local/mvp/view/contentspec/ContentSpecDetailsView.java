package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTContentSpecV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContentSpecDetailsView extends BaseTemplateView implements ContentSpecDetailsPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final ContentSpecDetailsPresenter.ContentSpecPresenterDriver driver = GWT.create(ContentSpecDetailsPresenter.ContentSpecPresenterDriver.class);

    @Override
    public ContentSpecDetailsPresenter.ContentSpecPresenterDriver getDriver() {
        return driver;
    }

    public ContentSpecDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Properties());

    }

    @Override
    public void display(final RESTContentSpecV1 topic, final boolean readOnly) {
        throw new UnsupportedOperationException("TopicView.display() is not supported. Use TopicView.displayTopicDetails() instead.");
    }

    @Override
    public void displayContentSpecDetails(@NotNull final RESTContentSpecV1 topic, final boolean readOnly, @NotNull final List<String> locales) {
        /* SearchUIProjectsEditor is a grid */
        @NotNull final RESTContentSpecV1BasicDetailsEditor editor = new RESTContentSpecV1BasicDetailsEditor(readOnly, locales);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

}
