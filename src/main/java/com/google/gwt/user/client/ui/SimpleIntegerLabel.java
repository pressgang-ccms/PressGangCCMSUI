package com.google.gwt.user.client.ui;

import com.google.gwt.text.shared.AbstractRenderer;
import org.jetbrains.annotations.Nullable;

/**
 * A ValueLabel that displays Integers as plain strings
 */
public class SimpleIntegerLabel extends ValueLabel<Integer> {

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

    public SimpleIntegerLabel() {
        super(IntegerRenderer.INSTANCE);
    }
}
