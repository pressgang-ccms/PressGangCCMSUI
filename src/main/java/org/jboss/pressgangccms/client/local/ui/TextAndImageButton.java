package org.jboss.pressgangccms.client.local.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

/**
 * http://blog.js-development.com/2010/03/gwt-button-with-image-and-text.html
 * 
 * @author Matthew Casperson
 */
public class TextAndImageButton extends Button
{
	private String text;

	public TextAndImageButton()
	{
		super();
	}
	
	public TextAndImageButton(final String text, final ImageResource imageResource)
	{
		setText(text);
		setResource(imageResource);
	}

	public void setResource(final ImageResource imageResource)
	{
		Image img = new Image(imageResource);
		String definedStyles = img.getElement().getAttribute("style");
		img.getElement().setAttribute("style", definedStyles + "; vertical-align:middle;");
		DOM.insertBefore(getElement(), img.getElement(), DOM.getFirstChild(getElement()));
	}

	@Override
	public void setText(final String text)
	{
		this.text = text;
		Element span = DOM.createElement("span");
		span.setInnerText(text);
		span.setAttribute("style", "padding-left:3px; vertical-align:middle;");

		DOM.insertChild(getElement(), span, 0);
	}

	@Override
	public String getText()
	{
		return this.text;
	}
}
