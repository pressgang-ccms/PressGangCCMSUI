package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class TopicXMLComponent extends TopicViewComponent<TopicXMLPresenter.Display> implements
        TopicXMLPresenter.LogicComponent {

    @Override
    public void bind(final int topicId, final String pageId, final TopicXMLPresenter.Display display,
            final BaseTemplateViewInterface waitDisplay) {
         super.bind(topicId, pageId, display, waitDisplay);
        bindAceEditorButtons();
    }

    /**
     * Bind the button clicks for the ACE editor buttons
     */
    private void bindAceEditorButtons() {
        display.getLineWrap().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getEditor().setUseWrapMode(!display.getEditor().getUserWrapMode());
                display.getLineWrap().setDown(display.getEditor().getUserWrapMode());
            }
        });

        display.getShowInvisibles().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                display.getEditor().setShowInvisibles(!display.getEditor().getShowInvisibles());
                display.getShowInvisibles().setDown(display.getEditor().getShowInvisibles());
            }
        });
    }

}
