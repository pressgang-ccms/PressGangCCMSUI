package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;

import org.jboss.pressgang.ccms.ui.client.local.utilities.XMLUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

public class TopicXMLPresenter extends BaseTemplatePresenter {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicXMLPresenter.class.getName());

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {
    }

    public interface Display extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<RESTBaseTopicV1<?, ?, ?>, RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {

        interface PlainTextXMLDialog {
            PushButton getOK();

            PushButton getCancel();

            void setText(final String text);

            String getText();

            DialogBox getDialogBox();
        }

        /**
         * The interface that defines the tag selection dialog box
         *
         * @author Matthew Casperson
         */
        interface XmlTagsDialog {
            PushButton getOK();

            PushButton getCancel();

            PushButton getMoreInfo();

            ListBox getOptions();

            DialogBox getDialogBox();

            void setSuggestions(final List<String> suggestions);
        }

        interface XmlTemplatesDialog {
            PushButton getOK();

            PushButton getCancel();

            ListBox getOptions();

            DialogBox getDialogBox();

            void setSuggestions(final Map<String, String> suggestions);
        }

        interface CSPTopicDetailsDialog {
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

        TextArea getXmlErrors();

        HandlerSplitLayoutPanel getVerticalPanel();

        /**
         * Build the split panel with the supplied height
         * @param splitHeight The height of the split panel holding the xml errors
         */
        void initialize(final int splitHeight);
    }

    public static final String HISTORY_TOKEN = "TopicXMLView";

    @Nullable
    private Integer topicId;

    @Inject
    private Display display;

    /**
     * true while there is a thread checking the XML
     */
    private boolean checkingXML = true;

    private native void checkXML(@NotNull final String dtd) /*-{
		if (this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter::checkingXML) {
            var display = this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter::display;
            var editor = display.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display::getEditor()();
		    var xml = editor.@edu.ycp.cs.dh.acegwt.client.ace.AceEditor::getText()();
            var errors = display.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.Display::getXmlErrors()();

            var worker = new Worker('/javascript/xmllint/xmllint.js');
            worker.addEventListener('message', function(e) {
                var theseErrors = e.data;
                var oldErrors = errors.@com.google.gwt.user.client.ui.TextArea::getText()();
                if (oldErrors != theseErrors) {
                    errors.@com.google.gwt.user.client.ui.TextArea::setText(Ljava/lang/String;)(theseErrors);
					this.@org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter::checkXML(Ljava/lang/String;)(dtd);
                }
            }, false);
            worker.postMessage({xml: xml, schema: dtd});
        }
    }-*/;

    public TopicXMLPresenter() {

    }

    /**
     * Load the DTD file and start the XML checking cycle.
     */
    @PostConstruct
    private void postConstruct() {
        try {
            new RequestBuilder(RequestBuilder.GET, "/javascript/xmllint/docbook.dtd").sendRequest("", new RequestCallback() {
                @Override
                public void onResponseReceived(@NotNull final Request req, @NotNull final Response resp) {
                    checkXML(resp.getText());
                }

                @Override
                public void onError(@NotNull final Request res, @NotNull final Throwable throwable) {
                }
            });
        } catch (@NotNull final RequestException e) {
            // do nothing
        }

    }

    @PreDestroy
    private void preDestroy() {
        checkingXML = false;
    }

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String searchToken) {
        try {
            topicId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (@NotNull final NumberFormatException ex) {
            topicId = null;
        }

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.TOPIC_XML_EDIT_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public void close() {

    }

    public void bindExtended(final int helpTopicId, @NotNull final String pageId) {
        super.bind(helpTopicId, pageId, display);
        //bindAceEditorButtons();

        final int splitHeight = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH, Constants.XML_ERRORS_SPLIT_PANEL_SIZE);
        display.initialize(splitHeight);

        bindSplitPanelResize();
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER TopicXMLPresenter.bindSplitPanelResize()");

            display.getVerticalPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(@NotNull final ResizeEvent event) {
                    if (display.getEditor() != null) {
                        display.getEditor().redisplay();
                    }

                    Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH, getDisplay()
                                .getVerticalPanel().getSplitPosition(display.getXmlErrors()) + "");

                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicXMLPresenter.bindSplitPanelResize()");
        }
    }

    /**
     * Bind the button clicks for the ACE editor buttons
     */
    private void bindAceEditorButtons() {
        display.getLineWrap().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getEditor().setUseWrapMode(!display.getEditor().getUserWrapMode());
                display.getLineWrap().setDown(display.getEditor().getUserWrapMode());
            }
        });

        display.getShowInvisibles().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                display.getEditor().setShowInvisibles(!display.getEditor().getShowInvisibles());
                display.getShowInvisibles().setDown(display.getEditor().getShowInvisibles());
            }
        });
    }

}
