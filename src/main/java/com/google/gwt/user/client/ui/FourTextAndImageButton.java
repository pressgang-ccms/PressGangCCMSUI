package com.google.gwt.user.client.ui;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class FourTextAndImageButton extends TextAndImageButton {
    private String text2;
    private String text3;
    private String text4;
    protected final Element div2 = DOM.createElement("div");
    protected final Element div3 = DOM.createElement("div");
    protected final Element div4 = DOM.createElement("div");

    /**
     * Create the UI element and sets the CSS styles.
     */
    public FourTextAndImageButton() {
        super(CSSConstants.CUSTOMBUTTONTEXTBOLD);

        div2.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
        DOM.insertChild(getElement(), div2, 1);

        div3.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
        DOM.insertChild(getElement(), div3, 2);

        div4.setAttribute("class", CSSConstants.CUSTOMBUTTONTEXT);
        DOM.insertChild(getElement(), div4, 3);
    }

    /**
     * @param text The text to appear on the second line of the button
     */
    public void setText2(final String text) {
        this.text2 = text;
        div2.setInnerText(text);
    }

    /**
     * @return The text that appears on the second line of the button
     */
    public String getText2() {
        return this.text2;
    }

    /**
     * @param text The text to appear on the third line of the button
     */
    public void setText3(final String text) {
        this.text3 = text;
        div3.setInnerText(text);
    }

    /**
     * @return The text that appears on the third line of the button
     */
    public String getText3() {
        return this.text3;
    }

    /**
     * @return The text that appears on the fourth line of the button
     */
    public String getText4() {
        return text4;
    }

    /**
     * @param text The text to appear on the fourth line of the button
     */
    public void setText4(final String text) {
        this.text4 = text;
        div4.setInnerText(text4);
    }
}
