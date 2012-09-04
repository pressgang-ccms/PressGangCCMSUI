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
	private final TextArea description = new TextArea();
	
	private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription()) ;
	
	private final FlexTable imageDetails = new FlexTable();
	
	/**
	 * The editor representing a collection of language image editors
	 */
	private final RESTLanguageImageCollectionV1Editor languageImages_OTM = new RESTLanguageImageCollectionV1Editor();	

	public TextArea descriptionEditor()
	{
		return description;
	}

	public RESTLanguageImageCollectionV1Editor languageImages_OTMEditor()
	{
		return languageImages_OTM;
	}
	
	public RESTImageV1Editor()
	{
		this.addStyleName(CSSConstants.IMAGEVIEWPARENTDOCKPANEL);
		
		imageDetails.addStyleName(CSSConstants.IMAGEVIEWDETAILSTABLE);
		descriptionLabel.addStyleName(CSSConstants.IMAGEVIEWDESCRIPTIONLABEL);
		description.addStyleName(CSSConstants.IMAGEVIEWDESCRIPTIONTEXT);
		
		imageDetails.setWidget(0, 0, descriptionLabel);
		imageDetails.setWidget(0, 1, description);
		
		imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGEVIEWDESCRIPTIONLABELCELL);
		imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGEVIEWDESCRIPTIONTEXTCELL);
		
		this.add(imageDetails, DockPanel.NORTH);
		this.add(languageImages_OTM, DockPanel.CENTER);
	}

}
