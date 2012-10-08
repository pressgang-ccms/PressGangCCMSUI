package com.google.gwt.user.client.ui;

import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * A PushButton to perform the functionality of a tristate checkbox.
 * 
 * @author Matthew Casperson
 * 
 */
public class TriStatePushButton extends PushButton implements IsEditor<LeafValueEditor<TriStateSelectionState>> {
    private TriStateSelectionState state = TriStateSelectionState.NONE;

    public TriStatePushButton() {
        super(new Image(ImageResources.INSTANCE.round32()));

        this.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                if (state == TriStateSelectionState.NONE || state == null) {
                    state = TriStateSelectionState.SELECTED;
                } else if (state == TriStateSelectionState.SELECTED) {
                    state = TriStateSelectionState.UNSELECTED;
                } else if (state == TriStateSelectionState.UNSELECTED) {
                    state = TriStateSelectionState.NONE;
                }

                updateState();
            }
        });
    }

    public TriStateSelectionState getState() {
        return state;
    }

    public void setState(final TriStateSelectionState state) {
        this.state = state;
        updateState();
    }

    private void updateState() {
        if (state == TriStateSelectionState.SELECTED) {
            TriStatePushButton.this.getUpFace().setImage(new Image(ImageResources.INSTANCE.plus32()));
        } else if (state == TriStateSelectionState.UNSELECTED) {
            TriStatePushButton.this.getUpFace().setImage(new Image(ImageResources.INSTANCE.minus32()));
        } else if (state == TriStateSelectionState.NONE) {
            TriStatePushButton.this.getUpFace().setImage(new Image(ImageResources.INSTANCE.round32()));
        }
    }

    @Override
    public LeafValueEditor<TriStateSelectionState> asEditor() {
        return new LeafValueEditor<TriStateSelectionState>() {
            @Override
            public void setValue(final TriStateSelectionState value) {
                setState(value);
            }

            @Override
            public TriStateSelectionState getValue() {
                return state;
            }
        };
    }
}
