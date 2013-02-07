package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Element;

/**
 * A version of the EditTextCell element that can be disabled.
 */
public final class DisableEditTextCell extends EditTextCell {
    /** true if the text bos should permit editing, and false otherwise */
    private boolean enabled;

    @Override
    public void onBrowserEvent(final Context context, final Element parent, final String value, final NativeEvent event, final ValueUpdater<String> valueUpdater) {
        if (enabled) {
            super.onBrowserEvent(context, parent, value, event, valueUpdater);
        }
    }

    /**
     * @param enabled true if the text bos should permit editing, and false otherwise
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
