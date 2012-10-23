package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;

/**
 * The base interface for all views that display topic details.
 * 
 * @author Matthew Casperson
 * 
 */
public interface TopicViewInterface extends BaseTemplateViewInterface {
    FlexTable getRenderedSplitViewMenu();

    PushButton getRenderedSplitOpen();

    PushButton getRenderedHorizontalSplit();

    PushButton getRenderedSplitClose();

    PushButton getRenderedVerticalSplit();

    PushButton getRenderedNoSplit();

    PushButton getRenderedSplit();

    /**
     * @return The button that is used to switch to the history view
     */
    PushButton getHistory();

    /**
     * @return The button that is used to switch to the rendered view
     */
    PushButton getRendered();

    /**
     * 
     * @return The button that is used to switch to the XML view
     */
    PushButton getXml();

    /**
     * 
     * @return The button that is used to switch to the topic fields view
     */
    PushButton getFields();

    /**
     * 
     * @return The button that is used to save the topic
     */
    PushButton getSave();

    /**
     * 
     * @return The button that is used to switch to the XML errors view
     */
    PushButton getXmlErrors();

    /**
     * 
     * @return The button that is used to switch to the tags view
     */
    @Override
    PushButton getTopicTags();

    /**
     * 
     * @return The button that is used to switch to the bugs view
     */
    PushButton getBugs();

    /**
     * 
     * @return The GWT Editor Framework driver that is used to sync the data between the underlying POJOs and the UI Editor
     *         elements
     */
    @SuppressWarnings("rawtypes")
    SimpleBeanEditorDriver getDriver();

    /**
     * Initialize the view with the details in the supplied topic
     * 
     * @param topic The topic that is used to initialize the view
     * @param readOnly true if the display is to be read only, false otherwise
     * @param splitType How the panel should be split to display the rendered veiw
     */
    void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType);

    /** Show the rendered split view menu */
    void showSplitViewButtons();
    
    /**
     * Rebuild the split view buttons
     * @param splitType The screen split
     */
    void buildSplitViewButtons(final SplitType splitType);
}
