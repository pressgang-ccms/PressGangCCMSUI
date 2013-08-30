package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.EditorSettingsDialog;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TopicXMLPresenter extends BaseTemplatePresenter {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicXMLPresenter.class.getName());

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {
    }

    public interface Display extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<RESTBaseTopicV1<?, ?, ?>,
            RESTBaseTopicV1<?, ?, ?>, RESTTopicV1XMLEditor> {

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

        EditorSettingsDialog getEditorSettingsDialog();

        PushButton getEditorSettings();

        AceEditor getEditor();

        TextArea getXmlErrors();

        HandlerSplitLayoutPanel getVerticalPanel();

        DockLayoutPanel getEditorParent();

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

    @Override
    public void bindExtended() {
        super.bind(display);

        final int splitHeight = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH,
                Constants.XML_ERRORS_SPLIT_PANEL_SIZE);
        display.initialize(splitHeight);

        bindSplitPanelResize();
        bindEditorSettingsButtons();
    }

    public void loadEditorSettings() {
        display.getEditorSettingsDialog().getLineWrap().setValue(display.getEditor().getUserWrapMode());
        display.getEditorSettingsDialog().getShowInvisibles().setValue(display.getEditor().getShowInvisibles());

        final String theme = Preferences.INSTANCE.getString(Preferences.EDITOR_THEME, Constants.DEFAULT_THEME);
        display.getEditor().setThemeByName(theme);
        for (int i = 0; i < display.getEditorSettingsDialog().getThemes().getItemCount(); ++i) {
            if (display.getEditorSettingsDialog().getThemes().getValue(i).equals(theme)) {
                display.getEditorSettingsDialog().getThemes().setSelectedIndex(i);
                break;
            }
        }

        final String fontSize = Preferences.INSTANCE.getString(Preferences.EDITOR_FONT_SIZE, Constants.DEFAULT_FONT_SIZE);
        display.getEditor().setFontSize(fontSize);
        for (int i = 0; i < display.getEditorSettingsDialog().getFontSizes().getItemCount(); ++i) {
            if (display.getEditorSettingsDialog().getFontSizes().getValue(i).equals(fontSize)) {
                display.getEditorSettingsDialog().getFontSizes().setSelectedIndex(i);
                break;
            }
        }

        final String font = Preferences.INSTANCE.getString(Preferences.EDITOR_FONT, Constants.DEFAULT_MONOSPACED_FONT);
        display.getEditor().setFontFamily(font);
        for (int i = 0; i < display.getEditorSettingsDialog().getFonts().getItemCount(); ++i) {
            if (display.getEditorSettingsDialog().getFonts().getValue(i).equals(font)) {
                display.getEditorSettingsDialog().getFonts().setSelectedIndex(i);
                break;
            }
        }

        final boolean wrap = Preferences.INSTANCE.getBoolean(Preferences.LINE_WRAPPING, true);
        display.getEditor().setUseWrapMode(wrap);
        display.getEditorSettingsDialog().getLineWrap().setValue(wrap);

        final boolean hidden = Preferences.INSTANCE.getBoolean(Preferences.SHOW_HIDDEN_CHARACTERS, false);
        display.getEditor().setShowInvisibles(hidden);
        display.getEditorSettingsDialog().getShowInvisibles().setValue(hidden);
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

                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH,
                                getDisplay().getVerticalPanel().getSplitPosition(display.getXmlErrors()) + "");
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
    protected void bindEditorSettingsButtons() {
        display.getEditorSettingsDialog().getLineWrap().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> event) {
                display.getEditor().setUseWrapMode(event.getValue());
                Preferences.INSTANCE.saveSetting(Preferences.LINE_WRAPPING, event.getValue());
            }
        });

        display.getEditorSettingsDialog().getShowInvisibles().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> event) {
                display.getEditor().setShowInvisibles(event.getValue());
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_HIDDEN_CHARACTERS, event.getValue());
            }
        });

        display.getEditorSettingsDialog().getThemes().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                if (display.getEditor() != null && display.getEditorSettingsDialog().getThemes().getSelectedIndex() != -1) {
                    final String theme = display.getEditorSettingsDialog().getThemes().getValue(
                            display.getEditorSettingsDialog().getThemes().getSelectedIndex());
                    display.getEditor().setThemeByName(theme);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_THEME, theme);
                }
            }
        });

        display.getEditorSettingsDialog().getFontSizes().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (display.getEditor() != null && display.getEditorSettingsDialog().getFontSizes().getSelectedIndex() != -1) {
                    final String fontSize = display.getEditorSettingsDialog().getFontSizes().getValue(
                            display.getEditorSettingsDialog().getFontSizes().getSelectedIndex());
                    display.getEditor().setFontSize(fontSize);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_FONT_SIZE, fontSize);
                }
            }
        });

        display.getEditorSettingsDialog().getFonts().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (display.getEditor() != null && display.getEditorSettingsDialog().getFonts().getSelectedIndex() != -1) {
                    final String font = display.getEditorSettingsDialog().getFonts().getValue(
                            display.getEditorSettingsDialog().getFonts().getSelectedIndex());
                    display.getEditor().setFontFamily(font);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_FONT, font);
                }
            }
        });
    }

}
