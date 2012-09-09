package org.jboss.pressgangccms.client.local.ui.editor.image;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.rest.v1.components.ComponentImageV1;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class RESTImageV1Editor extends DockPanel implements ValueAwareEditor<RESTImageV1>
{
	private RESTImageV1 value;
	
	/**
	 * A text area to represent the description field
	 */
	private final TextArea description = new TextArea();
	
	private final TextBox docbookFileName = new TextBox();
	
	private final Label descriptionLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());
	
	private final Label docbookFileNameLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookFilename());
	
	private final FlexTable imageDetails = new FlexTable();
	
	private final TextArea xmlTemplate = new TextArea();
	
	private final Label xmlTemplateLabel = new Label(PressGangCCMSUI.INSTANCE.DocbookImageTemplate());
	
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
		
		docbookFileName.setReadOnly(true);
		xmlTemplate.setReadOnly(true);
		
		imageDetails.addStyleName(CSSConstants.IMAGEVIEWDETAILSTABLE);
		descriptionLabel.addStyleName(CSSConstants.IMAGEVIEWDESCRIPTIONLABEL);
		description.addStyleName(CSSConstants.IMAGEVIEWDESCRIPTIONTEXT);
		xmlTemplate.addStyleName(CSSConstants.IMAGEVIEWTEMPLATETEXT);
		
		imageDetails.setWidget(0, 0, descriptionLabel);
		imageDetails.setWidget(0, 1, description);
		imageDetails.setWidget(1, 0, xmlTemplateLabel);
		imageDetails.setWidget(1, 1, xmlTemplate);
		imageDetails.setWidget(2, 0, docbookFileNameLabel);
		imageDetails.setWidget(2, 1, docbookFileName);
		
		imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGEVIEWDESCRIPTIONLABELCELL);
		imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGEVIEWDESCRIPTIONTEXTCELL);
		imageDetails.getCellFormatter().addStyleName(0, 0, CSSConstants.IMAGEVIEWTEMPLATELABELCELL);
		imageDetails.getCellFormatter().addStyleName(0, 1, CSSConstants.IMAGEVIEWTEMPLATETEXTCELL);
		imageDetails.getCellFormatter().addStyleName(2, 0, CSSConstants.IMAGEVIEWDOCBOOKFILENAMELABELCELL);
		imageDetails.getCellFormatter().addStyleName(2, 1, CSSConstants.IMAGEVIEWDOCBOOKFILENAMETEXTCELL);
		
		this.add(imageDetails, DockPanel.NORTH);
		this.add(languageImages_OTM, DockPanel.CENTER);
	}

	@Override
	public void setDelegate(final EditorDelegate<RESTImageV1> delegate)
	{
		
	}

	@Override
	public void flush()
	{
		value.setDescription(this.description.getText());
	}

	@Override
	public void onPropertyChange(final String... paths)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(final RESTImageV1 value)
	{
		this.value = value;
		this.docbookFileName.setText(ComponentImageV1.getDocbookFileName(value));	
		this.xmlTemplate.setText(ComponentImageV1.getXMLTemplate(value));
	}

}
