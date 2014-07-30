/*
  Copyright 2011-2014 Red Hat, Inc

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.google.gwt.user.client.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jetbrains.annotations.NotNull;

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
        initialize(CSSConstants.Common.CUSTOM_BUTTON_TEXT);
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
        @NotNull final Image img = new Image(imageResource);
        final String definedStyles = img.getElement().getAttribute("style");
        img.getElement().setAttribute("style", definedStyles + "; vertical-align:middle;");
        DOM.insertBefore(getElement(), img.getElement(), DOM.getFirstChild(getElement()));
    }

    @Override
    public final void setText(final String text) {
        this.text = text;
        div.setInnerText(text);
    }

    @Override
    public final String getText() {
        return this.text;
    }
}
