package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class TopicXMLView extends TopicViewBase implements TopicXMLPresenter.Display {

    /** The GWT Editor Driver */
    private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

    private RESTTopicV1XMLEditor editor;

    private final ToggleButton lineWrap = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.LineWrap());
    private final ToggleButton showInvisibles = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.ShowHiddenCharacters());

    @Override
    public ToggleButton getShowInvisibles() {
        return showInvisibles;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    @Override
    public ToggleButton getLineWrap() {
        return lineWrap;
    }

    @Override
    public AceEditor getEditor() {
        if (editor != null) {
            return editor.xml;
        }
        return null;
    }

    public TopicXMLView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.XMLEditing());
        this.getPanel().addStyleName(CSSConstants.TOPIC_XML_VIEW_PANEL);
    }

    @Override
    protected void populateTopActionBar(final boolean newTopic) {
        super.populateTopActionBar(newTopic);

        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXmlDown());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getTopicTags());
        if (!newTopic) {
            addActionButton(this.getBugs());
            addActionButton(this.getHistory());
        }
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
        addActionButton(lineWrap);
        addActionButton(showInvisibles);
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType, final String[] locales) {
        this.readOnly = readOnly;
        populateTopActionBar(newTopic);
        buildSplitViewButtons(splitType);

        /* SearchUIProjectsEditor is a grid */
        editor = new RESTTopicV1XMLEditor(readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }
}
