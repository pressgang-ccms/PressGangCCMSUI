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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.SimpleEditor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TopicTagViewTagEditor implements Editor<SearchUITag> {
    private final boolean readOnly;
    /**
     * bound to the SearchUITag itself
     */
    @Path("")
    final SimpleEditor<SearchUITag> self = SimpleEditor.of();
    /**
     * bound to SearchUITag.getName()
     */
    final Label name = new Label();
    /**
     * A button used to delete this tag
     */
    private final PushButton delete = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Remove());

    /**
     * @return a reference to the SearchUITag that was used to bind this Editor
     */
    @Nullable
    public SearchUITag getTag() {
        if (self != null) {
            return self.getValue();
        }
        return null;
    }

    @NotNull
    public PushButton getDelete() {
        return delete;
    }

    public TopicTagViewTagEditor(final boolean readOnly) {
        this.readOnly = readOnly;
        name.addStyleName(CSSConstants.TopicView.TOPIC_VIEW_TAG_LABEL);
    }
}
