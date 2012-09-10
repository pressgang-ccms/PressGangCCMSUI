package com.google.gwt.user.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;

/**
 * An Image that can be bound to a Base64 representation of an image.
 * 
 * @author Matthew Casperson
 * 
 */
public class ImageStringEditor extends Image implements IsEditor<LeafValueEditor<String>> {
    @Override
    public LeafValueEditor<String> asEditor() {
        return new LeafValueEditor<String>() {

            @Override
            public void setValue(final String value) {
                ImageStringEditor.this.setUrl(value);

            }

            @Override
            public String getValue() {
                return ImageStringEditor.this.getUrl();
            }

        };
    }

}
