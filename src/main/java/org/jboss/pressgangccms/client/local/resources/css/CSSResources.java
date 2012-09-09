package org.jboss.pressgangccms.client.local.resources.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface CSSResources extends ClientBundle {
    CSSResources INSTANCE = GWT.create(CSSResources.class);

    @Source("App.css")
    MyCssResource App();

    @Source("headingBackground.png")
    ImageResource headingBackground();
}
