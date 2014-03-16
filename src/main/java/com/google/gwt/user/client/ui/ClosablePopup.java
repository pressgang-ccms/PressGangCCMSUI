package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.Element;

/**
 * @author Andrey Talnikov
 *
 * @see http://stackoverflow.com/a/6520046/1330640
 */
public class ClosablePopup extends DialogBox {

    private final Label closeAnchor = new Label("X");;

    /**
     * Instantiates new closable popup.
     *
     * @param title        the title
     * @param defaultClose it {@code true}, hide popup on 'x' click
     */
    public ClosablePopup(String title, boolean defaultClose) {
        super(false);

        FlexTable captionLayoutTable = new FlexTable();
        captionLayoutTable.setWidth("100%");
        captionLayoutTable.setText(0, 0, title);
        captionLayoutTable.setWidget(0, 1, closeAnchor);
        captionLayoutTable.getCellFormatter().setHorizontalAlignment(0, 1,
                HasHorizontalAlignment.HorizontalAlignmentConstant.endOf(HasDirection.Direction.LTR));

        HTML caption = (HTML) getCaption();
        caption.getElement().appendChild(captionLayoutTable.getElement());

        caption.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EventTarget target = event.getNativeEvent().getEventTarget();
                Element targetElement = (Element) target.cast();

                if (targetElement == closeAnchor.getElement()) {
                    closeAnchor.fireEvent(event);
                }
            }
        });

        if (defaultClose) {
            closeAnchor.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    hide();
                }
            });
        }
    }
}
