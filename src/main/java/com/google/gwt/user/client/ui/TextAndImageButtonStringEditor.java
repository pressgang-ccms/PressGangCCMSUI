package com.google.gwt.user.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import org.jetbrains.annotations.NotNull;

public final class TextAndImageButtonStringEditor extends TextAndImageButton implements IsEditor<LeafValueEditor<String>> {
    @NotNull
    @Override
    public LeafValueEditor<String> asEditor() {
        return new LeafValueEditor<String>() {
            @Override
            public void setValue(final String value) {
                setText(value);
            }

            @Override
            public String getValue() {
                return getText();
            }
        };
    }
}
