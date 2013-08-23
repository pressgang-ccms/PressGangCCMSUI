package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.*;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
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

        DockLayoutPanel getEditorParent();

        ListBox getThemes();

        /**
         * Build the split panel with the supplied height
         *
         * @param splitHeight The height of the split panel holding the xml errors
         */
        void initialize(final int splitHeight);
    }

    public static final String HISTORY_TOKEN = "TopicXMLView";

    @Nullable
    private Integer topicId;

    @Inject
    private Display display;


    public TopicXMLPresenter() {
        LOGGER.log(Level.INFO, "ENTER TopicXMLPresenter()");
    }

    /**
     * Load the DTD file and start the XML checking cycle.
     */
    @PostConstruct
    private void postConstruct() {


    }

    @PreDestroy
    private void preDestroy() {

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
        bindExtended();
    }

    @Override
    public void close() {

    }

    public void bindExtended() {
        super.bind(display);
        //bindAceEditorButtons();

        final int splitHeight = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH, Constants.XML_ERRORS_SPLIT_PANEL_SIZE);
        display.initialize(splitHeight);

        bindSplitPanelResize();
        bindThemesChange();
        loadTheme();
    }

    public void loadTheme() {
        final String theme = Preferences.INSTANCE.getString(Preferences.EDITOR_THEME, Constants.DEFAULT_THEME);
        for (int i = 0; i < display.getThemes().getItemCount(); ++i) {
            if (display.getThemes().getValue(i) == theme) {
                display.getThemes().setSelectedIndex(i);
                break;
            }
        }
    }

    private void bindThemesChange() {
        display.getThemes().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                if (display.getEditor() != null && display.getThemes().getSelectedIndex() != -1) {
                    final String theme = display.getThemes().getValue(display.getThemes().getSelectedIndex());
                    display.getEditor().setThemeByName(theme);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_THEME, theme);
                }
            }
        });
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
                    try {
                        LOGGER.log(Level.INFO, "ENTER TopicXMLPresenter.bindSplitPanelResize() ResizeHandler.onResize()");


                        if (display.getEditor() != null) {
                            display.getEditor().redisplay();
                        }

                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH, getDisplay()
                                .getVerticalPanel().getSplitPosition(display.getXmlErrors()) + "");
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT TopicXMLPresenter.bindSplitPanelResize() ResizeHandler.onResize()");
                    }

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
