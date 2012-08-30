package org.jboss.pressgangccms.client.local.ui.editor.image;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public class RESTImageV1Editor extends DockPanel implements Editor<RESTImageV1>
{
	/**
	 * A text area to represent the description field
	 */
	final TextArea description = new TextArea();
	
	private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription()) ;
	
	private final FlexTable imageDetails = new FlexTable();
	
	/**
	 * The editor representing a collection of language image editors
	 */
	final RESTLanguageImageCollectionV1Editor languageImages_OTM = new RESTLanguageImageCollectionV1Editor();
	
	public RESTImageV1Editor()
	{
		this.addStyleName(CSSConstants.IMAGEVIEWPARENTDOCKPANEL);
		
		imageDetails.setWidget(0, 0, descriptionLabel);
		imageDetails.setWidget(0, 1, description);
		
		this.add(imageDetails, DockPanel.NORTH);
		this.add(languageImages_OTM, DockPanel.CENTER);
	}
}
