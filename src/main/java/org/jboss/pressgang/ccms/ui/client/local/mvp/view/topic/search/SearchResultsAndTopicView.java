package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchResultsAndTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * The view that combines the topic search results with the individual topic views 
 * @author Matthew Casperson
 */
public class SearchResultsAndTopicView extends
        BaseSearchAndEditView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchResultsAndTopicPresenter.Display {

    /**
     * The dialog box that presents the list of locales for the user to select from.
     * 
     * @author Matthew Casperson
     */
    public class MessageLogDialog extends DialogBox implements SearchResultsAndTopicPresenter.Display.LogMessageDialog {
        
        /** Used to group the radio buttons */
        private static final String CHANGE_TYPE_GROUP = "ChangeType";
        
        private final FlexTable layout = new FlexTable();
        private final RadioButton minorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
        private final RadioButton majorChange = new RadioButton(CHANGE_TYPE_GROUP, "");
        private final TextArea message = new TextArea(); 
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());

        public TextArea getMessage() {
            return message;
        }

        @Override
        public RadioButton getMajorChange() {
            return majorChange;
        }

        @Override
        public RadioButton getMinorChange() {
            return minorChange;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public PushButton getOk() {
            return ok;
        }

        public MessageLogDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.SaveLog());

            layout.setWidget(0, 0, new Label(PressGangCCMSUI.INSTANCE.Message()));
            layout.setWidget(0, 1, message);
            layout.setWidget(1, 0, new Label(PressGangCCMSUI.INSTANCE.MinorChange()));
            layout.setWidget(1, 1, minorChange);
            layout.setWidget(2, 0, new Label(PressGangCCMSUI.INSTANCE.MajorChange()));
            layout.setWidget(2, 1, majorChange);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(3, 0, buttonPanel);

            layout.getFlexCellFormatter().setColSpan(3, 0, 2);

            this.add(layout);
        }

        @Override
        public void reset() {
            this.message.setText("");
            this.minorChange.setValue(true);            
        }

    }
    
    /** An instance of the message log dialog box */
    private final MessageLogDialog messageLogDialog = new MessageLogDialog();
    
    /** The type of split used to display the rendered XML */
    private SplitType splitType = SplitType.NONE;

    public MessageLogDialog getMessageLogDialog() {
        return messageLogDialog;
    }

    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    public SearchResultsAndTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
    }

    /**
     * The split panel needs to have the center widget added last, which we need to do after optionally added a east or south
     * widget for the rendered view.
     * 
     * @param splitType How the parent panel should be split
     * @param panel The rendered view panel itself
     */
    @Override
    public void initialize(final SplitType splitType, final Panel panel) {
        this.splitType = splitType;

        getSplitPanel().clear();

        getSplitPanel().addWest(this.getResultsViewLayoutPanel(), Constants.SPLIT_PANEL_SIZE);

        final SimplePanel renderedPanelParent = new SimplePanel();
        renderedPanelParent.addStyleName(CSSConstants.TOPIC_VIEW_LAYOUT_PANEL);
        renderedPanelParent.add(panel);

        if (splitType == SplitType.HORIZONTAL) {
            getSplitPanel().addSouth(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        } else if (splitType == SplitType.VERTICAL) {
            getSplitPanel().addEast(renderedPanelParent, Constants.SPLIT_PANEL_SIZE);
        }

        getSplitPanel().add(this.getViewLayoutPanel());
    }
}
