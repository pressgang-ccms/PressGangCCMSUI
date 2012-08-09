package org.jboss.pressgangccms.client.local.resources.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


public interface CSSResources extends ClientBundle
{
	public static CSSResources INSTANCE = GWT.create(CSSResources.class);

	@Source("App.css")
	public MyCssResource App();

	@Source("headingBackground.png")
	public ImageResource headingBackground();
}
