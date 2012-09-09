package org.jboss.pressgangccms.client.local.mvp.view.topic;

import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgangccms.client.local.mvp.presenter.topic.TopicPresenter.TopicPresenterDriver;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

public class TopicView extends TopicViewBase implements TopicPresenter.Display {
    public static final String HISTORY_TOKEN = "TopicView";

    /** The GWT Editor Driver */
    private final TopicPresenterDriver driver = GWT.create(TopicPresenterDriver.class);

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    public TopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Properties());
    }

    @Override
    protected void populateTopActionBar() {
        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXml());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFieldsDown());
        addActionButton(this.getTags());
        addActionButton(this.getBugs());
        addActionButton(this.getHistory());
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType) {
        this.readOnly = readOnly;
        fixReadOnlyButtons();
        buildSplitViewButtons(splitType);

        /* SearchUIProjectsEditor is a grid */
        final RESTTopicV1BasicDetailsEditor editor = new RESTTopicV1BasicDetailsEditor(readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

}
