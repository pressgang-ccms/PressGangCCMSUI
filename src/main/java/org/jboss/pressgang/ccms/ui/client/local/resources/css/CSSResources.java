package org.jboss.pressgang.ccms.ui.client.local.resources.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import org.jetbrains.annotations.NotNull;

/**
 * A GWT resource class to hold some CSS Styles.
 *
 * @author Matthew Casperson
 */
public interface CSSResources extends ClientBundle {
    /**
     * An instance of the CSSResources class created by GWT.
     */
    CSSResources INSTANCE = GWT.create(CSSResources.class);

    /**
     * @return The CSS resource.
     */
    @NotNull
    @Source("App.css")
    MyCssResource appCss();

    /**
     * @return The image to be displayed in the background of the header.
     */
    @NotNull
    @Source("headingBackground.png")
    ImageResource headingBackground();
}
