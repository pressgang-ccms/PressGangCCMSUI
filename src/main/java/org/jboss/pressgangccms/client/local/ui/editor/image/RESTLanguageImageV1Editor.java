package org.jboss.pressgangccms.client.local.ui.editor.image;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.utilities.GWTStringUtilities;
import org.jboss.pressgangccms.rest.v1.entities.RESTLanguageImageV1;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Provides a UI for viewing and editing a RESTLanguageImageV1 object.
 * 
 * We can't bind to a byte array directly
 * (http://code.google.com/p/google-web-toolkit/issues/detail?id=6600), so we
 * need to use the ValueAwareEditor to do some manual binding.
 * 
 * @author Matthew Casperson
 * 
 */
public class RESTLanguageImageV1Editor extends FlexTable implements ValueAwareEditor<RESTLanguageImageV1>
{
	private static final String JPG_BASE64_PREFIX = "data:image/jpg;base64,";
	
	/** Keep a reference to the object this editor gets its values from */
	public RESTLanguageImageV1 self;

	/**
	 * To set the name of a tab based on the object that initializes this editor
	 * we need to keep a track of the tab layout and the index that this editor
	 * is assigned to. See
	 * http://stackoverflow.com/questions/10463782/adding-list
	 * -sub-editors-to-tab-panel for an explanation. This is the reference to the 
	 * tab panel
	 */
	private final TabLayoutPanel parentPanel;
	
	/**
	 * This is a reference to the tab index
	 */
	private final int parentIndex;

	private final TextBox filename = new TextBox();
	private final ListBox locale = new ListBox();
	private final Image imageDataBase64 = new Image();

	private final Label filenameLabel = new Label(PressGangCCMSUI.INSTANCE.ImageFilename());
	private final Label localeLabel = new Label(PressGangCCMSUI.INSTANCE.ImageLocale());
	private final Label imageLabel = new Label(PressGangCCMSUI.INSTANCE.ImageSample());

	public ListBox getLocale()
	{
		return locale;
	}

	public RESTLanguageImageV1Editor(final TabLayoutPanel parentPanel, final int parentIndex)
	{
		this.addStyleName(CSSConstants.IMAGEVIEWLANGUAGEIMAGETAB);
		
		this.parentPanel = parentPanel;
		this.parentIndex = parentIndex;

		this.setWidget(0, 0, filenameLabel);
		this.setWidget(0, 1, filename);
		this.setWidget(1, 0, localeLabel);
		this.setWidget(1, 1, locale);
		this.setWidget(2, 0, imageLabel);
		this.setWidget(2, 1, imageDataBase64);
	}

	@Override
	public void setDelegate(final EditorDelegate<RESTLanguageImageV1> delegate)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void flush()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPropertyChange(final String... paths)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(final RESTLanguageImageV1 value)
	{
		this.self = value;
		this.filename.setText(value.getFilename());
		final String base64 = GWTStringUtilities.getStringUTF8(value.getImageDataBase64());
		this.imageDataBase64.setUrl(JPG_BASE64_PREFIX + base64);

		parentPanel.setTabText(parentIndex, this.self.getLocale());
	}
}
