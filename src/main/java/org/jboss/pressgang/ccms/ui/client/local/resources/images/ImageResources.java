package org.jboss.pressgang.ccms.ui.client.local.resources.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import org.jetbrains.annotations.NotNull;

/**
 * Embeds image resources into the GWT app.
 *
 * @author Matthew Casperson
 */
public interface ImageResources extends ClientBundle {
    /**
     * A singleton instance of this class.
     */
    ImageResources INSTANCE = GWT.create(ImageResources.class);

    /**
     * @return An animated GIF used to indicate that the page is loading
     */
    @NotNull
    @Source("spinner.gif")
    ImageResource spinner();

    /**
     * @return The application banner
     */
    @NotNull
    @Source("headingBanner.png")
    ImageResource headingBanner();

    /**
     * @return Used to indicate tristate checkbox in a non-selected state
     */
    @NotNull
    @Source("round32.png")
    ImageResource round32();

    /**
     * @return Used to indicate a selected tristate checkbox
     */
    @NotNull
    @Source("plus32.png")
    ImageResource plus32();

    /**
     * @return Used to indicate an unselected tristate checkbox
     */
    @NotNull
    @Source("minus32.png")
    ImageResource minus32();

}
