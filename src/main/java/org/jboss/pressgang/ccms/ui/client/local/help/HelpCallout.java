package org.jboss.pressgang.ccms.ui.client.local.help;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jetbrains.annotations.NotNull;

/**
 * A callout used by the help overlay
 */
public class HelpCallout extends FlexTable {

    private final Frame iFrame = new Frame();
    private final HTML arrow = new HTML();


    /**
     * @param helpData The help data that will be used to build this callout.
     */
    public HelpCallout(@NotNull final HelpData helpData) {

        this.addStyleName(CSSConstants.HelpOverlay.HELP_CALLOUT);

        iFrame.addStyleName(CSSConstants.HelpOverlay.HELP_CALLOUT_IFRAME);

        if (helpData.getDirection() == 0) {
            this.setWidget(1, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        } else if (helpData.getDirection() == 1) {
            this.setWidget(1, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.TOP_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        } else if (helpData.getDirection() == 2) {
            this.setWidget(1, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        }  else if (helpData.getDirection() == 3) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(0, 1, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.ARROW_CELL);
        }  else if (helpData.getDirection() == 4) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.RIGHT_ARROW);
            this.setWidget(1, 1, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.HelpOverlay.ARROW_CELL);
        } else if (helpData.getDirection() == 5) {
            this.setWidget(0, 0, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.BOTTOM_ARROW);
            this.setWidget(1, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        }  else if (helpData.getDirection() == 6) {
            this.setWidget(0, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(1, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        }  else if (helpData.getDirection() == 7) {
            this.setWidget(0, 1, iFrame);
            arrow.addStyleName(CSSConstants.HelpOverlay.LEFT_ARROW);
            this.setWidget(0, 0, arrow);

            this.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.HelpOverlay.CONTENT_CELL);
            this.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.HelpOverlay.ARROW_CELL);
        }
    }

    public Frame getiFrame() {
        return iFrame;
    }

    public HTML getArrow() {
        return arrow;
    }
}
