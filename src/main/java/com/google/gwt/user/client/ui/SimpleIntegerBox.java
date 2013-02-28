package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.text.client.IntegerParser;
import com.google.gwt.text.shared.AbstractRenderer;
import org.jetbrains.annotations.Nullable;

/**
 * A ValueBox that displays interegers as plain strings
 */
public class SimpleIntegerBox extends ValueBox<Integer> {

    static class IntegerRenderer extends AbstractRenderer<Integer> {
        public static final IntegerRenderer INSTANCE = new IntegerRenderer();

        protected IntegerRenderer() {
        }

        @Override
        public String render(@Nullable final Integer object) {
            if (null == object) {
                return "";
            }

            return object.toString();
        }
    }

    public SimpleIntegerBox() {
        super(Document.get().createTextInputElement(), IntegerRenderer.INSTANCE, IntegerParser.instance());
    }
}
