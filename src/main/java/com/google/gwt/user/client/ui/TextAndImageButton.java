package com.google.gwt.user.client.ui;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * http://blog.js-development.com/2010/03/gwt-button-with-image-and-text.html
 * 
 * @author Matthew Casperson
 */
public class TextAndImageButton extends Button {
    private String text;
    private final Element div = DOM.createElement("div");

    public TextAndImageButton() {
        super();
        initialize(CSSConstants.CUSTOMBUTTONTEXT);
    }

    public TextAndImageButton(final String divClass) {
        super();
        initialize(divClass);
    }

    public TextAndImageButton(final String text, final ImageResource imageResource) {
        super();
        this.text = text;
        setResource(imageResource);
    }
    
    private void initialize(final String divClass) {
        div.setAttribute("class", divClass);
        DOM.insertChild(getElement(), div, 0);
    }

    final public void setResource(final ImageResource imageResource) {
        final Image img = new Image(imageResource);
        final String definedStyles = img.getElement().getAttribute("style");
        img.getElement().setAttribute("style", definedStyles + "; vertical-align:middle;");
        DOM.insertBefore(getElement(), img.getElement(), DOM.getFirstChild(getElement()));
    }

    @Override
    public void setText(final String text) {
        this.text = text;
        div.setInnerText(text);
    }

    @Override
    public String getText() {
        return this.text;
    }
}
