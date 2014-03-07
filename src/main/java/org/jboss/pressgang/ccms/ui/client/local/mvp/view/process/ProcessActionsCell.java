package org.jboss.pressgang.ccms.ui.client.local.mvp.view.process;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.jboss.pressgang.ccms.rest.v1.elements.enums.RESTProcessStatusV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;

/**
 * This is a modified version of <a href="https://gwt2go.googlecode.com/svn/trunk/gwt2go/src/com/gwt2go/dev/client/ui/table/ImagesCell.java">
 * https://gwt2go.googlecode.com/svn/trunk/gwt2go/src/com/gwt2go/dev/client/ui/table/ImagesCell.java</a>
 */
public class ProcessActionsCell extends AbstractSafeHtmlCell<String> {
    public static final String STOP_NAME = "Stop";
    public static final String REFRESH_NAME = "Refresh";
    private static final List<String> STOP_STATUS = Arrays.asList(RESTProcessStatusV1.PENDING.name(), RESTProcessStatusV1.QUEUED.name());
    private static final List<String> REFRESH_STATUS = Arrays.asList(RESTProcessStatusV1.PENDING.name(), RESTProcessStatusV1.QUEUED.name(),
            RESTProcessStatusV1.EXECUTING.name());

    /**
     * The HTML templates used to render the cell.
     */
    interface Templates extends SafeHtmlTemplates {
        /**
         * The template for this Cell, which includes styles and a value.
         *
         * @param styles the styles to include in the style attribute of the div
         * @param value  the safe value. Since the value type is {@link SafeHtml},
         *               it will not be escaped before including it in the
         *               template. Alternatively, you could make the value type
         *               String, in which case the value would be escaped.
         * @return a {@link SafeHtml} instance
         */
        @SafeHtmlTemplates.Template("<div title=\"{0}\" class=\"{1}\">{2}</div>")
        SafeHtml cell(String name, String classes, SafeHtml value);
    }

    public ProcessActionsCell() {
        super(SimpleSafeHtmlRenderer.getInstance(), "click", "keydown");
    }

    public ProcessActionsCell(SafeHtmlRenderer<String> renderer) {
        super(renderer, "click", "keydown");
    }

    /**
     * Create a singleton instance of the templates used to render the cell.
     */
    private static final Templates templates = GWT.create(Templates.class);

    private static final SafeHtml ICON_STOP = makeImage(ImageResources.INSTANCE.error16());
    private static final SafeHtml ICON_REFRESH = makeImage(ImageResources.INSTANCE.refresh16());

    /**
     * Called when an event occurs in a rendered instance of this Cell. The
     * parent element refers to the element that contains the rendered cell, NOT
     * to the outermost element that the Cell rendered.
     */
    @Override
    public void onBrowserEvent(final Context context, final Element parent, final String value, final NativeEvent event,
            final ValueUpdater<String> valueUpdater) {
        // Handle the click event.
        if ("click".equals(event.getType())) {

            // Ignore clicks that occur outside of the outermost element.
            EventTarget eventTarget = event.getEventTarget();

            if (parent.isOrHasChild(Element.as(eventTarget))) {
                // if (parent.getFirstChildElement().isOrHasChild(
                // Element.as(eventTarget))) {

                // use this to get the selected element!!
                Element el = Element.as(eventTarget);

                // check if we really click on the image
                if (el.getNodeName().equalsIgnoreCase("IMG")) {
                    doAction(el.getParentElement().getAttribute("title"), valueUpdater);
                }
            }
        } else {
            // Let AbstractCell handle the keydown event.
            super.onBrowserEvent(context, parent, value, event, valueUpdater);
        }
    }

    /**
     * onEnterKeyDown is called when the user presses the ENTER key will the
     * Cell is selected. You are not required to override this method, but its a
     * common convention that allows your cell to respond to key events.
     */
    @Override
    protected void onEnterKeyDown(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
        doAction(value, valueUpdater);
    }

    /**
     * Intern action
     *
     * @param value        selected value
     * @param valueUpdater value updater or the custom value update to be called
     */
    private void doAction(String value, ValueUpdater<String> valueUpdater) {
        // Trigger a value updater. In this case, the value doesn't actually
        // change, but we use a ValueUpdater to let the app know that a value
        // was clicked.
        if (valueUpdater != null) valueUpdater.update(value);
    }

    @Override
    protected void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        /*
         * Always do a null check on the value. Cell widgets can pass null to
         * cells if the underlying data contains a null, or if the data arrives
         * out of order.
         */
        if (data == null) {
            return;
        }

        // Generate the image cells
        if (REFRESH_STATUS.contains(data.asString())) {
            SafeHtml rendered = templates.cell(REFRESH_NAME, CSSConstants.ProcessView.ACTION_IMAGE, ICON_REFRESH);
            sb.append(rendered);
        }
        if (STOP_STATUS.contains(data.asString())) {
            SafeHtml rendered = templates.cell(STOP_NAME, CSSConstants.ProcessView.ACTION_IMAGE, ICON_STOP);
            sb.append(rendered);
        }
    }

    /**
     * Make icons available as SafeHtml
     *
     * @param resource
     * @return
     */
    private static SafeHtml makeImage(final ImageResource resource) {
        return AbstractImagePrototype.create(resource).getSafeHtml();
    }
}