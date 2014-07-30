/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextAndImageButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;

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
        super(CSSConstants.Common.CUSTOM_BUTTON_TEXT_BOLD);

        int row = 1;
        div2.setAttribute("class", CSSConstants.Common.CUSTOM_BUTTON_TEXT);
        DOM.insertChild(getElement(), div2, row);

        div3.setAttribute("class", CSSConstants.Common.CUSTOM_BUTTON_TEXT);
        DOM.insertChild(getElement(), div3, ++row);

        div4.setAttribute("class", CSSConstants.Common.CUSTOM_BUTTON_TEXT);
        DOM.insertChild(getElement(), div4, ++row);
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
