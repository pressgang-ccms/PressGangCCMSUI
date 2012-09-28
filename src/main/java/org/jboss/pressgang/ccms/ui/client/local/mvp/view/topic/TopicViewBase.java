package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

/**
 * The base class for all views that display some details of a topic. This class creates and exposes all the common UI elements.
 * 
 * @author Matthew Casperson
 */
abstract public class TopicViewBase extends BaseTemplateView implements TopicViewInterface {
    private final PushButton fields;
    private final PushButton xml;
    private final PushButton xmlErrors;
    private final PushButton rendered;
    private final PushButton tags;
    private final PushButton save;
    private final PushButton bugs;
    private final PushButton history;

    private final Label fieldsDown;
    private final Label xmlDown;
    private final Label xmlErrorsDown;
    private final Label renderedDown;
    private final Label tagsDown;
    private final Label bugsDown;
    private final Label historyDown;

    private final FlexTable renderedSplitViewMenu = new FlexTable();
    private final PushButton renderedSplit;
    private final PushButton renderedNoSplit;
    private final PushButton renderedVerticalSplit;
    private final PushButton renderedHorizontalSplit;
    private final Label renderedNoSplitDown;
    private final Label renderedVerticalSplitDown;
    private final Label renderedHorizontalSplitDown;
    private final PushButton renderedSplitClose;
    private final PushButton renderedSplitOpen;

    protected boolean readOnly = false;

    public Label getFieldsDown() {
        return fieldsDown;
    }

    public Label getXmlDown() {
        return xmlDown;
    }

    public Label getXmlErrorsDown() {
        return xmlErrorsDown;
    }

    public Label getRenderedDown() {
        return renderedDown;
    }

    public Label getTagsDown() {
        return tagsDown;
    }

    public Label getBugsDown() {
        return bugsDown;
    }

    public Label getHistoryDown() {
        return historyDown;
    }

    @Override
    public PushButton getRenderedSplitOpen() {
        return renderedSplitOpen;
    }

    @Override
    public PushButton getRenderedHorizontalSplit() {
        return renderedHorizontalSplit;
    }

    @Override
    public FlexTable getRenderedSplitViewMenu() {
        return renderedSplitViewMenu;
    }

    @Override
    public PushButton getRenderedSplitClose() {
        return renderedSplitClose;
    }

    @Override
    public PushButton getRenderedVerticalSplit() {
        return renderedVerticalSplit;
    }

    @Override
    public PushButton getRenderedNoSplit() {
        return renderedNoSplit;
    }

    @Override
    public PushButton getRenderedSplit() {
        return renderedSplit;
    }

    @Override
    public PushButton getHistory() {
        return history;
    }

    @Override
    public PushButton getBugs() {
        return bugs;
    }

    @Override
    public PushButton getTags() {
        return tags;
    }

    @Override
    public PushButton getXmlErrors() {
        return xmlErrors;
    }

    @Override
    public PushButton getSave() {
        return save;
    }

    @Override
    public PushButton getRendered() {
        return rendered;
    }

    @Override
    public PushButton getXml() {
        return xml;
    }

    @Override
    public PushButton getFields() {
        return fields;
    }

    public TopicViewBase(final String applicationName, final String pageName) {
        super(applicationName, pageName);

        renderedSplitViewMenu.addStyleName(CSSConstants.RENDERED_SPLIT_VIEW_MENU_TABLE);

        /* Build the action bar icons */
        renderedSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true);
        rendered = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedView());
        xml = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.XMLEditing());
        xmlErrors = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.XMLValidationErrors());
        fields = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Properties());
        save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
        tags = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.TopicTags());
        bugs = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Bugs());
        history = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Revisions());

        fieldsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Properties());
        xmlDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.XMLEditing());
        xmlErrorsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.XMLValidationErrors());
        renderedDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.RenderedView());
        tagsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.TopicTags());
        bugsDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Bugs());
        historyDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Revisions());

        renderedSplitOpen = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.RenderedPane(), true);
        renderedNoSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedNoSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.NoSplit());
        renderedVerticalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedVerticalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.VerticalSplit());
        renderedHorizontalSplit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedHorizontalSplitDown = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.HorizontalSplit());
        renderedSplitClose = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.CloseSubMenu());

        populateTopActionBar();
    }

    /**
     * This method enables or disables the save button based on the read only state, and also highlights the history button if
     * needed.
     */
    protected void fixReadOnlyButtons() {
        this.getSave().setEnabled(!readOnly);

        if (readOnly) {
            this.getHistory().addStyleName(CSSConstants.ALERT_BUTTON);
        } else {
            this.getHistory().removeStyleName(CSSConstants.ALERT_BUTTON);
        }
    }

    protected void buildSplitViewButtons(final SplitType splitType) {
        renderedSplitViewMenu.clear();

        if (splitType != SplitType.DISABLED) {
            renderedSplitViewMenu.setWidget(0, 0, renderedSplitOpen);

            if (splitType == SplitType.NONE) {
                renderedSplitViewMenu.setWidget(0, 1, renderedNoSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, 1, renderedNoSplit);
            }

            if (splitType == SplitType.VERTICAL) {
                renderedSplitViewMenu.setWidget(0, 2, renderedVerticalSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, 2, renderedVerticalSplit);
            }

            if (splitType == SplitType.HORIZONTAL) {
                renderedSplitViewMenu.setWidget(0, 3, renderedHorizontalSplitDown);
            } else {
                renderedSplitViewMenu.setWidget(0, 3, renderedHorizontalSplit);
            }

            renderedSplitViewMenu.setWidget(0, 4, renderedSplitClose);
        }

        addRightAlignedActionButtonPaddingPanel(this.renderedSplitViewMenu);
    }

    /** Show the rendered split view menu */
    @Override
    public void showSplitViewButtons() {
        getTopActionParentPanel().clear();
        getTopActionParentPanel().add(renderedSplitViewMenu);
    }

    /**
     * This method is called to initialize the buttons that should appear in the top action bar
     */
    abstract protected void populateTopActionBar();
}
