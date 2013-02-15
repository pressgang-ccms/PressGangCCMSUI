package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;


import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTranslatedTopicV1BasicDetailsEditor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The view to display translated topics details. Unlike the other views, which are generalized
 * around the RESTBaseTopic class, translated topics and topics display slightly different details
 * (although this should probably be standardized).
 */
public class TranslatedTopicView extends BaseTemplateView implements TranslatedTopicPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final TranslatedTopicPresenter.TranslatedTopicPresenterDriver driver = GWT.create(TranslatedTopicPresenter.TranslatedTopicPresenterDriver.class);

    public TranslatedTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Properties());

    }

    @Override
    public TranslatedTopicPresenter.TranslatedTopicPresenterDriver getDriver() {
        return driver;
    }

    @Override
    public final void display(final RESTTranslatedTopicV1 topic, final boolean readOnly) {
                /* SearchUIProjectsEditor is a grid */
        final RESTTranslatedTopicV1BasicDetailsEditor editor = new RESTTranslatedTopicV1BasicDetailsEditor(readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

}
