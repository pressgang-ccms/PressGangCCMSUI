package org.jboss.pressgangccms.client.local.ui;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.preferences.Preferences;
import org.jboss.pressgangccms.client.local.preferences.Preferences.ButtonView;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * This class contains helper methods for creating and managing UI elements
 * 
 * @author Matthew Casperson
 * 
 */
final public class UIUtilities
{
	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String text)
	{
		PushButton retvalue;

		if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
		{
			retvalue = new PushButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));
			retvalue.addStyleName(CSSConstants.IMAGEBUTTON);
		}
		else
		{
			retvalue = new PushButton(text);
			retvalue.addStyleName(CSSConstants.TEXTBUTTON);
		}

		return retvalue;
	}

	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled, final String text)
	{
		PushButton retvalue;

		if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
		{
			retvalue = new PushButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));
			retvalue.getUpDisabledFace().setImage(new Image(disabled));
			retvalue.addStyleName(CSSConstants.IMAGEBUTTON);
		}
		else
		{
			retvalue = new PushButton(text);
			retvalue.addStyleName(CSSConstants.TEXTBUTTON);
		}

		return retvalue;
	}

	public static ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final String text, final ImageResource hover)
	{
		ToggleButton retvalue;

		if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
		{
			retvalue = new ToggleButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));
			retvalue.addStyleName(CSSConstants.IMAGEBUTTON);
		}
		else
		{
			retvalue = new ToggleButton(text);
			retvalue.addStyleName(CSSConstants.TEXTBUTTON);
		}

		return retvalue;
	}

	public static ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String text)
	{
		ToggleButton retvalue;

		if (Preferences.INSTANCE.getButtonView() == ButtonView.IMAGE)
		{
			retvalue = new ToggleButton(new Image(up), new Image(down));
			retvalue.getUpHoveringFace().setImage(new Image(hover));
			retvalue.addStyleName(CSSConstants.TEXTBUTTON);
		}
		else
		{
			retvalue = new ToggleButton(text);
			retvalue.addStyleName(CSSConstants.IMAGEBUTTON);
		}
		
		return retvalue;
	}
	
	public static Label createDownLabel(final String text)
	{
		final Label retvalue = new Label(text);
		retvalue.addStyleName(CSSConstants.DOWNLABEL);
		return retvalue;
	}
}
