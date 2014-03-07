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
     * @return A big red cross
     */
    @NotNull
    @Source("big_error.png")
    ImageResource bigError();

    /**
     * @return A smaller red cross
     */
    @NotNull
    @Source("error16.png")
    ImageResource error16();

    /**
     * @return A smaller black and white cross
     */
    @NotNull
    @Source("error16-BW.png")
    ImageResource error16BW();

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

    /**
     * @return Used to indicate tristate checkbox in a non-selected state
     */
    @NotNull
    @Source("round16.png")
    ImageResource round16();

    /**
     * @return Used to indicate a selected tristate checkbox
     */
    @NotNull
    @Source("plus16.png")
    ImageResource plus16();

    /**
     * @return Used to indicate an unselected tristate checkbox
     */
    @NotNull
    @Source("minus16.png")
    ImageResource minus16();

    @NotNull
    @Source("info.png")
    ImageResource info();

    @NotNull
    @Source("loading.gif")
    ImageResource loading();

    @NotNull
    @Source("refresh.png")
    ImageResource refresh16();
}
