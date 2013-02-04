package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base.BaseTopicViewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

public final class TopicXMLPresenter extends BaseTopicViewPresenter implements
        BaseTemplatePresenterInterface {

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLEditor> {
    }

    public interface Display extends BaseTopicViewInterface {

        public interface PlainTextXMLDialog
        {
            PushButton getOK();
            PushButton getCancel();
            void setText(final String text);
            String getText();
            DialogBox getDialogBox();
        }

        /**
         * The interface that defines the tag selection dialog box
         * @author Matthew Casperson
         */
        public interface XmlTagsDialog
        {
            PushButton getOK();
            PushButton getCancel();
            PushButton getMoreInfo();
            ListBox getOptions();
            DialogBox getDialogBox();
            void setSuggestions(final List<String> suggestions);
        }

        public interface XmlTemplatesDialog
        {
            PushButton getOK();
            PushButton getCancel();
            ListBox getOptions();
            DialogBox getDialogBox();
            void setSuggestions(final Map<String, String> suggestions);
        }

        public interface CSPTopicDetailsDialog
        {
            PushButton getOK();
            PushButton getCancel();
            TextBox getIds();
            DialogBox getDialogBox();
        }

        XmlTagsDialog getXmlTagsDialog();
        CSPTopicDetailsDialog getCSPTopicDetailsDialog();
        XmlTemplatesDialog getXmlTemplatesDialog();
        PlainTextXMLDialog getPlainTextXMLDialog();

        ToggleButton getLineWrap();

        ToggleButton getShowInvisibles();

        AceEditor getEditor();
    }

    public static final String HISTORY_TOKEN = "TopicXMLView";

    private Integer topicId;

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void parseToken(final String searchToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (final NumberFormatException ex) {
            topicId = null;
        }

    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int helpTopicId, final String pageId) {
        super.bind(helpTopicId, pageId, display);
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
