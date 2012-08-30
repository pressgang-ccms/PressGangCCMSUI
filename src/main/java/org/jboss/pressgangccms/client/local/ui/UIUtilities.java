package org.jboss.pressgangccms.client.local.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;

/**
 * This class contains helper methods for creating and managing UI elements
 * @author Matthew Casperson
 *
 */
final public class UIUtilities
{
	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		return retvalue;
	}

	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String text, final String className)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.addStyleName(className);
		return retvalue;
	}

	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled, final String text)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.getUpDisabledFace().setImage(new Image(disabled));
		return retvalue;
	}

	public static PushButton createPushButton(final ImageResource up, final ImageResource down, final ImageResource hover, final ImageResource disabled, final String text, final String className)
	{
		final PushButton retvalue = new PushButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.getUpDisabledFace().setImage(new Image(disabled));
		retvalue.addStyleName(className);
		return retvalue;
	}

	public static ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover)
	{
		final ToggleButton retvalue = new ToggleButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		return retvalue;
	}

	public static ToggleButton createToggleButton(final ImageResource up, final ImageResource down, final ImageResource hover, final String className)
	{
		final ToggleButton retvalue = new ToggleButton(new Image(up), new Image(down));
		//retvalue.getUpHoveringFace().setImage(new Image(hover));
		retvalue.addStyleName(className);
		return retvalue;
	}	
}
