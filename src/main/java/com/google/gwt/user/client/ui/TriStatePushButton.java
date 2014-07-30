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

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jetbrains.annotations.NotNull;

/**
 * A PushButton to perform the functionality of a tristate checkbox.
 *
 * @author Matthew Casperson
 */
public final class TriStatePushButton extends PushButton implements IsEditor<LeafValueEditor<TriStateSelectionState>> {
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

    @NotNull
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
