package com.google.gwt.user.client.ui;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.Header;

public class TextInputHeader extends Header<String> {
    private ValueUpdater<String> updater;
    private String value;

    public TextInputHeader() {
        this(new TextInputCell());
    }

    public TextInputHeader(final TextInputCell inputCell) {
        super(inputCell);

        super.setUpdater(new ValueUpdater<String>() {
            @Override
            public void update(final String value) {
                if (updater != null) {
                    updater.update(value);
                }
                TextInputHeader.this.value = value;
            }
        });
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public void setUpdater(final ValueUpdater<String> updater) {
        this.updater = updater;
    }
}
