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
 * @author Matthew Casperson
 */
public interface ImageResources extends ClientBundle
{
	public static final ImageResources INSTANCE = GWT.create(ImageResources.class);
	
	@Source("headingBanner.png")
	ImageResource headingBanner();
	
	@Source("transparent48.png")
	ImageResource transparent48();
	
	@Source("fields48.png")
	ImageResource fields48();
	
	@Source("fieldsHover48.png")
	ImageResource fieldsHover48();
	
	@Source("fieldsDown48.png")
	ImageResource fieldsDown48();
	
	@Source("xml48.png")
	ImageResource xml48();
	
	@Source("xmlHover48.png")
	ImageResource xmlHover48();
	
	@Source("xmlDown48.png")
	ImageResource xmlDown48();
	
	@Source("rendered48.png")
	ImageResource rendered48();
	
	@Source("renderedHover48.png")
	ImageResource renderedHover48();
	
	@Source("renderedDown48.png")
	ImageResource renderedDown48();
	
	@Source("home48.png")
	ImageResource home48();
	
	@Source("homeHover48.png")
	ImageResource homeHover48();
	
	@Source("homeDown48.png")
	ImageResource homeDown48();
	
	@Source("searchTranslations48.png")
	ImageResource searchTranslations48();
	
	@Source("searchTranslationsDown48.png")
	ImageResource searchTranslationsDown48();
	
	@Source("searchTranslationsHover48.png")
	ImageResource searchTranslationsHover48();
	
	@Source("reports48.png")
	ImageResource reports48();
	
	@Source("reportsDown48.png")
	ImageResource reportsDown48();
	
	@Source("reportsHover48.png")
	ImageResource reportsHover48();
	
	@Source("reportsDisabled48.png")
	ImageResource reportsDisabled48();
	
	@Source("spinner.gif")
	ImageResource spinner();
	
	@Source("users48.png")
	ImageResource users48();

	@Source("search48.png")
	ImageResource search48();

	@Source("searchHover48.png")
	ImageResource searchHover48();

	@Source("searchDown48.png")
	ImageResource searchDown48();

	@Source("bug48.png")
	ImageResource bug48();

	@Source("bugHover48.png")
	ImageResource bugHover48();

	@Source("bugDown48.png")
	ImageResource bugDown48();

	@Source("check16.png")
	ImageResource check16();

	@Source("plus16.png")
	ImageResource plus16();
	
	@Source("plus32.png")
	ImageResource plus32();

	@Source("minus16.png")
	ImageResource minus16();
	
	@Source("minus32.png")
	ImageResource minus32();

	@Source("round16.png")
	ImageResource round16();
	
	@Source("round32.png")
	ImageResource round32();
}
