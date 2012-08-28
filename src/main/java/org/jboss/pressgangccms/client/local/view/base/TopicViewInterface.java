package org.jboss.pressgangccms.client.local.view.base;

import org.jboss.pressgangccms.client.local.ui.SplitType;
import org.jboss.pressgangccms.rest.v1.entities.RESTTopicV1;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;

/**
 * The base interface for all views that display topic details.
 * 
 * @author Matthew Casperson
 * 
 */
public interface TopicViewInterface extends BaseTemplateViewInterface
{
	FlexTable getRenderedSplitViewMenu();
	
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
	PushButton getTags();

	/**
	 * 
	 * @return The button that is used to switch to the bugs view
	 */
	PushButton getBugs();

	/**
	 * 
	 * @return The GWT Editor Framework driver that is used to sync the data
	 *         between the underlying POJOs and the UI Editor elements
	 */
	@SuppressWarnings("rawtypes")
	SimpleBeanEditorDriver getDriver();

	/**
	 * Initialize the view with the details in the supplied topic
	 * 
	 * @param topic
	 *            The topic that is used to initialize the view
	 * @param readOnly
	 * 				true if the display is to be read only, false otherwise
	 */
	void initialize(final RESTTopicV1 topic, final boolean readOnly, final SplitType splitType);
	
	/** Show the rendered split view menu */
	void showSplitViewButtons();
}
