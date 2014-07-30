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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

/**
 * A dialog box to display when the screen is locked.
 *
 * @author Matthew Casperson
 */
public class WaitingDialog extends DialogBox {

    /**
     * The image to display in the waiting dialog.
     */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());

    /**
     * Configures the dialog.
     */
    public WaitingDialog() {
        this.setGlassEnabled(true);
        this.add(spinner);
        this.setText(PressGangCCMSUI.INSTANCE.PleaseWait());
    }
}
