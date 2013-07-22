package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * A callout used by the help overlay
 */
public class HelpCallout extends FlexTable {

    private final Frame iFrame = new Frame();
    private final HTML arrow = new HTML();
    private final PushButton edit = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Edit());
    private final PushButton close = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Close());


    /**
     * @param helpData The help data that will be used to build this callout.
     */
    public HelpCallout(@NotNull final HelpData helpData) {

        this.addStyleName(CSSConstants.HelpOverlay.HELP_CALLOUT);

        iFrame.addStyleName(CSSConstants.HelpOverlay.HELP_CALLOUT_IFRAME);

        final HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.addStyleName(CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT);
        horizontalPanel.setSpacing(2);
        horizontalPanel.add(edit);
        horizontalPanel.add(close);

        if (helpData.getDirection() == 0) {
            this.setWidget(1, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
            this.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

            this.setWidget(1, 1, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);
        } else if (helpData.getDirection() == 1) {
            this.setWidget(1, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.TOP_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);

            this.setWidget(2, 0, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(2, 0, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);
        } else if (helpData.getDirection() == 2) {
            this.setWidget(1, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.ARROW_CELL);
            this.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);

            this.setWidget(1, 0, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);
        }  else if (helpData.getDirection() == 3) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(0, 1, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.ARROW_CELL);

            this.setWidget(1, 0, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);

            this.getFlexCellFormatter().setRowSpan(0, 1, 2);
        }  else if (helpData.getDirection() == 4) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(1, 1, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.ARROW_CELL);
            this.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_BOTTOM);

            this.setWidget(1, 0, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);

            this.getFlexCellFormatter().setRowSpan(0, 1, 2);
        } else if (helpData.getDirection() == 5) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.BOTTOM_ARROW);
            this.setWidget(1, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(2, 0, CSSConstants.HelpOverlay.ARROW_CELL);

            this.setWidget(1, 0, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);
        }  else if (helpData.getDirection() == 6) {
            this.setWidget(0, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(1, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
            this.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_BOTTOM);

            this.setWidget(1, 1, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);

            this.getFlexCellFormatter().setRowSpan(0, 0, 2);
        }  else if (helpData.getDirection() == 7) {
            this.setWidget(0, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);

            this.setWidget(1, 1, horizontalPanel);
            this.getFlexCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
            this.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.HelpOverlay.CLOSE_AND_EDIT_BUTTONS_PARENT_CELL);

            this.getFlexCellFormatter().setRowSpan(0, 0, 2);
        }
    }

    public Frame getiFrame() {
        return iFrame;
    }

    public HTML getArrow() {
        return arrow;
    }

    public PushButton getEdit() {
        return edit;
    }

    public PushButton getClose() {
        return close;
    }
}
