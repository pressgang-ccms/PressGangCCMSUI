/*
  Copyright 2011-2014 Red Hat

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

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.user.client.ui.CheckBox;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

/**
 * This view maintains a kind of double buffer. IFrames are loaded into hidden table cells, and then
 * "flipped" out after a period of time to give a seamless appearance of a panel being updated.
 */
public class TranslatedTopicRenderedView extends BaseTopicRenderedView implements TranslatedTopicRenderedPresenter.Display {
    private final CheckBox mergeAdditionalXML = new CheckBox(PressGangCCMSUI.INSTANCE.MergeAdditionalXml());

    public TranslatedTopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.RenderedView());

        getRenderingOptions().setWidget(1, 1, mergeAdditionalXML);
        mergeAdditionalXML.setValue(true);
    }

    @Override
    public CheckBox getMergeAdditionalXML() {
        return mergeAdditionalXML;
    }
}