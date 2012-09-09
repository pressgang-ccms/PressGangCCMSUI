package org.jboss.pressgangccms.client.local.ui.editor.topicview.assignedtags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.SimpleEditor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class TopicTagViewTagEditor implements Editor<SearchUITag> {
    private final boolean readOnly;
    /** bound to the SearchUITag itself */
    @Path("")
    final SimpleEditor<SearchUITag> self = SimpleEditor.of();
    /** bound to SearchUITag.getName() */
    final Label name = new Label();
    /** A button used to delete this tag */
    private final PushButton delete = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Remove());

    /** @return a reference to the SearchUITag that was used to bind this Editor */
    public SearchUITag getTag() {
        if (self != null)
            return self.getValue();
        return null;
    }

    public PushButton getDelete() {
        return delete;
    }

    public TopicTagViewTagEditor(final boolean readOnly) {
        this.readOnly = readOnly;
        name.addStyleName(CSSConstants.TOPICVIEWTAGLABEL);
    }
}
