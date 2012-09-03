package org.jboss.pressgangccms.client.local.resources.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Hover Hue: 200
 * Hover Saturation: 100
 * Hover Lightness: 25
 * 
 * Down Hue: 200
 * Down Saturation: 100
 * Down Lightness: 35
 * 
 * Disabled Hue: 0
 * Disabled Saturation: 0
 * Disabled Lightness: 35
 * 
 * @author Matthew Casperson
 */
public interface ImageResources extends ClientBundle
{
	public static final ImageResources INSTANCE = GWT.create(ImageResources.class);
	
	@Source("spinner.gif")
	ImageResource spinner();
	
	@Source("headingBanner.gif")
	ImageResource headingBanner();
	
	@Source("round32.png")
	ImageResource round32();
	
	@Source("plus32.png")
	ImageResource plus32();
	
	@Source("minus32.png")
	ImageResource minus32();

}
