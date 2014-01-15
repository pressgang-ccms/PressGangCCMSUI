package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.base;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.editor.client.Editor;
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
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.EditorSettingsDialog;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jetbrains.annotations.NotNull;

public abstract class BaseTopicXMLPresenter extends BaseTemplatePresenter {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(BaseTopicXMLPresenter.class.getName());

    public interface Display<T, U extends Editor<T>> extends BaseTemplateViewInterface, BasePopulatedEditorViewInterface<T, T, U> {

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

        XmlTagsDialog getXmlTagsDialog();

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


    public BaseTopicXMLPresenter() {
        LOGGER.log(Level.INFO, "ENTER BaseTopicXMLPresenter()");
    }

    @NotNull
    public abstract Display getDisplay();

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, getDisplay());
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {
        super.bind(getDisplay());

        final int splitHeight = Preferences.INSTANCE.getInt(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH,
                Constants.XML_ERRORS_SPLIT_PANEL_SIZE);
        getDisplay().initialize(splitHeight);

        bindSplitPanelResize();
        bindEditorSettingsButtons();
    }

    public void loadEditorSettings() {
        getDisplay().getEditorSettingsDialog().getLineWrap().setValue(getDisplay().getEditor().getUserWrapMode());
        getDisplay().getEditorSettingsDialog().getShowInvisibles().setValue(getDisplay().getEditor().getShowInvisibles());

        final String theme = Preferences.INSTANCE.getString(Preferences.EDITOR_THEME, Constants.DEFAULT_THEME);
        getDisplay().getEditor().setThemeByName(theme);
        for (int i = 0; i < getDisplay().getEditorSettingsDialog().getThemes().getItemCount(); ++i) {
            if (getDisplay().getEditorSettingsDialog().getThemes().getValue(i).equals(theme)) {
                getDisplay().getEditorSettingsDialog().getThemes().setSelectedIndex(i);
                break;
            }
        }

        final String fontSize = Preferences.INSTANCE.getString(Preferences.EDITOR_FONT_SIZE, Constants.DEFAULT_FONT_SIZE);
        getDisplay().getEditor().setFontSize(fontSize);
        for (int i = 0; i < getDisplay().getEditorSettingsDialog().getFontSizes().getItemCount(); ++i) {
            if (getDisplay().getEditorSettingsDialog().getFontSizes().getValue(i).equals(fontSize)) {
                getDisplay().getEditorSettingsDialog().getFontSizes().setSelectedIndex(i);
                break;
            }
        }

        final String font = Preferences.INSTANCE.getString(Preferences.EDITOR_FONT, Constants.DEFAULT_MONOSPACED_FONT);
        getDisplay().getEditor().setFontFamily(font);
        for (int i = 0; i < getDisplay().getEditorSettingsDialog().getFonts().getItemCount(); ++i) {
            if (getDisplay().getEditorSettingsDialog().getFonts().getValue(i).equals(font)) {
                getDisplay().getEditorSettingsDialog().getFonts().setSelectedIndex(i);
                break;
            }
        }

        final boolean wrap = Preferences.INSTANCE.getBoolean(Preferences.LINE_WRAPPING, true);
        getDisplay().getEditor().setUseWrapMode(wrap);
        getDisplay().getEditorSettingsDialog().getLineWrap().setValue(wrap);

        final boolean hidden = Preferences.INSTANCE.getBoolean(Preferences.SHOW_HIDDEN_CHARACTERS, false);
        getDisplay().getEditor().setShowInvisibles(hidden);
        getDisplay().getEditorSettingsDialog().getShowInvisibles().setValue(hidden);

        final boolean behaviours = Preferences.INSTANCE.getBoolean(Preferences.BEHAVIOURS, true);
        getDisplay().getEditor().setBehavioursEnabled(behaviours);
        getDisplay().getEditorSettingsDialog().getBehaviours().setValue(behaviours);
    }

    /**
     * Respond to the split panel resizing by redisplaying the ACE editor component
     */
    private void bindSplitPanelResize() {

        try {
            LOGGER.log(Level.INFO, "ENTER BaseTopicXMLPresenter.bindSplitPanelResize()");

            getDisplay().getVerticalPanel().addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(@NotNull final ResizeEvent event) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER BaseTopicXMLPresenter.bindSplitPanelResize() ResizeHandler.onResize()");


                        if (getDisplay().getEditor() != null) {
                            getDisplay().getEditor().redisplay();
                        }

                        Preferences.INSTANCE.saveSetting(Preferences.TOPIC_VIEW_XML_ERRORS_SPLIT_WIDTH,
                                getDisplay().getVerticalPanel().getSplitPosition(getDisplay().getXmlErrors()) + "");
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT BaseTopicXMLPresenter.bindSplitPanelResize() ResizeHandler.onResize()");
                    }

                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT BaseTopicXMLPresenter.bindSplitPanelResize()");
        }
    }

    /**
     * Bind the button clicks for the ACE editor buttons
     */
    protected void bindEditorSettingsButtons() {
        getDisplay().getEditorSettingsDialog().getBehaviours().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> event) {
                getDisplay().getEditor().setBehavioursEnabled(event.getValue());
                Preferences.INSTANCE.saveSetting(Preferences.BEHAVIOURS, event.getValue());
            }
        });

        getDisplay().getEditorSettingsDialog().getLineWrap().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> event) {
                getDisplay().getEditor().setUseWrapMode(event.getValue());
                Preferences.INSTANCE.saveSetting(Preferences.LINE_WRAPPING, event.getValue());
            }
        });

        getDisplay().getEditorSettingsDialog().getShowInvisibles().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(@NotNull final ValueChangeEvent<Boolean> event) {
                getDisplay().getEditor().setShowInvisibles(event.getValue());
                Preferences.INSTANCE.saveSetting(Preferences.SHOW_HIDDEN_CHARACTERS, event.getValue());
            }
        });

        getDisplay().getEditorSettingsDialog().getThemes().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(@NotNull final ChangeEvent event) {
                if (getDisplay().getEditor() != null && getDisplay().getEditorSettingsDialog().getThemes().getSelectedIndex() != -1) {
                    final String theme = getDisplay().getEditorSettingsDialog().getThemes().getValue(
                            getDisplay().getEditorSettingsDialog().getThemes().getSelectedIndex());
                    getDisplay().getEditor().setThemeByName(theme);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_THEME, theme);
                }
            }
        });

        getDisplay().getEditorSettingsDialog().getFontSizes().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (getDisplay().getEditor() != null && getDisplay().getEditorSettingsDialog().getFontSizes().getSelectedIndex() != -1) {
                    final String fontSize = getDisplay().getEditorSettingsDialog().getFontSizes().getValue(
                            getDisplay().getEditorSettingsDialog().getFontSizes().getSelectedIndex());
                    getDisplay().getEditor().setFontSize(fontSize);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_FONT_SIZE, fontSize);
                }
            }
        });

        getDisplay().getEditorSettingsDialog().getFonts().addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if (getDisplay().getEditor() != null && getDisplay().getEditorSettingsDialog().getFonts().getSelectedIndex() != -1) {
                    final String font = getDisplay().getEditorSettingsDialog().getFonts().getValue(
                            getDisplay().getEditorSettingsDialog().getFonts().getSelectedIndex());
                    getDisplay().getEditor().setFontFamily(font);
                    Preferences.INSTANCE.saveSetting(Preferences.EDITOR_FONT, font);
                }
            }
        });
    }

}
