package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.typo.TypoJS;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.data.TagDBLoader;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.EditorSettingsDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TopicXMLView extends BaseTemplateView implements TopicXMLPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicXMLView.class.getName());

    /**
     * The GWT Editor Driver
     */
    private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

    private RESTTopicV1XMLEditor editor;

    /**
     * An instance of the typo.js library, to be passed to the instance of the ACE editor. This object seems to stick
     * around, and consumes a not insignificant amount of memory, so it is initialized once for the duration of the application.
     */
    private static final TypoJS positiveDictionary = new TypoJS();
    private static final TypoJS negativeDictionary = new TypoJS("javascript/typojs/en_US-neg.dic", "javascript/typojs/en_US-neg.aff", "en_US");
    private static final TypoJS negativePhraseDictionary = new TypoJS("javascript/typojs/en_US-negphrase.dic", "javascript/typojs/en_US-neg.aff", "en_US");

    @Inject
    private TagDBLoader tagDBLoader;

    private final HandlerSplitLayoutPanel verticalPanel = new HandlerSplitLayoutPanel(Constants.SPLIT_PANEL_DIVIDER_SIZE);
    private final TextArea xmlErrors = new TextArea();
    private final DockLayoutPanel editorParent = new DockLayoutPanel(Style.Unit.PX);

    @NotNull
    @Override
    public TextArea getXmlErrors() {
        return xmlErrors;
    }

    @NotNull
    @Override
    public HandlerSplitLayoutPanel getVerticalPanel() {
        return verticalPanel;
    }

    @NotNull
    @Override
    public DockLayoutPanel getEditorParent() {
        return editorParent;
    }

    public final static class PlainTextXMLDialog extends DialogBox implements TopicXMLPresenter.Display.PlainTextXMLDialog {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private TextArea textArea;
        private final FlexTable layout = new FlexTable();

        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @Override
        public PushButton getCancel() {
            return this.cancel;
        }

        @Override
        public void setText(final String text) {
            /*
             * Create a new textarea to work around a situation where a After the Deadline check was not cleared before the
             * dialog was closed
             */
            this.textArea = new TextArea();
            this.textArea.addStyleName(CSSConstants.PlainTextXMLDialog.PLAIN_TEXT_XML_DIALOG_TEXTAREA);
            this.textArea.setText(text);
            this.layout.setWidget(0, 0, this.textArea);
        }

        @Override
        public String getText() {
            if (this.textArea != null) {
                return this.textArea.getText();
            }
            return "";
        }

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        public PlainTextXMLDialog() {
            this.setGlassEnabled(true);
            /* If true, this interferes with the "After the Deadline" plugin */
            this.setModal(false);
            this.setText(PressGangCCMSUI.INSTANCE.SpellChecking());

            this.textArea.addStyleName(CSSConstants.PlainTextXMLDialog.PLAIN_TEXT_XML_DIALOG_TEXTAREA);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public void show() {
            super.show();
            this.textArea.setFocus(true);
        }

    }

    /**
     * The dialog box that displays a list of docbook tags.
     */
    public final static class XmlTagsDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTagsDialog {
        private static final int NUMBER_OF_VISIBLE_ITEMS = 10;
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final PushButton moreInfo = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.MoreInfo());
        private final ListBox options = new ListBox(false);
        private final FlexTable layout = new FlexTable();

        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @Override
        public PushButton getCancel() {
            return this.cancel;
        }

        @Override
        public PushButton getMoreInfo() {
            return this.moreInfo;
        }

        @NotNull
        @Override
        public ListBox getOptions() {
            return this.options;
        }

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public void setSuggestions(@Nullable final List<String> suggestions) {
            this.options.clear();

            if (suggestions != null) {
                for (final String suggestion : suggestions) {
                    this.options.addItem(suggestion);
                }
            }
        }

        public XmlTagsDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertXMLElement());

            this.options.setVisibleItemCount(NUMBER_OF_VISIBLE_ITEMS);
            this.options.addStyleName(CSSConstants.TopicView.XML_TAGS_LIST);
            this.layout.setWidget(0, 0, this.options);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.moreInfo);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public void show() {
            super.show();
            this.options.setFocus(true);
        }

        /**
         * Select the first item when the box is closed.
         */
        @Override
        public void hide() {
            super.hide();
            this.options.setSelectedIndex(0);
        }
    }

    /**
     * The dialog box that displays a list of docbook templates.
     */
    public final static class XmlTemplatesDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTemplatesDialog {
        private static final int NUMBER_OF_VISIBLE_ITEMS = 10;
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final ListBox options = new ListBox(false);
        private final FlexTable layout = new FlexTable();

        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @Override
        public PushButton getCancel() {
            return this.cancel;
        }

        @NotNull
        @Override
        public ListBox getOptions() {
            return this.options;
        }

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public void setSuggestions(@Nullable final Map<String, String> suggestions) {
            this.options.clear();

            if (suggestions != null) {
                for (@NotNull final Entry<String, String> suggestion : suggestions.entrySet()) {
                    this.options.addItem(suggestion.getKey(), suggestion.getValue());
                }
            }
        }

        public XmlTemplatesDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertXMLElement());

            this.options.setVisibleItemCount(NUMBER_OF_VISIBLE_ITEMS);
            this.options.addStyleName(CSSConstants.TopicView.XML_TAGS_LIST);
            this.layout.setWidget(0, 0, this.options);

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public void show() {
            super.show();
            this.options.setFocus(true);
        }

        /**
         * Select the first item when the box is closed.
         */
        @Override
        public void hide() {
            super.hide();
            this.options.setSelectedIndex(0);
        }
    }

    public final static class CSPTopicDetailsDialog extends DialogBox implements TopicXMLPresenter.Display.CSPTopicDetailsDialog {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextBox ids = new TextBox();
        private final FlexTable layout = new FlexTable();

        @Override
        public PushButton getOK() {
            return this.ok;
        }

        @Override
        public PushButton getCancel() {
            return this.cancel;
        }

        @NotNull
        @Override
        public TextBox getIds() {
            return this.ids;
        }

        @NotNull
        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        public CSPTopicDetailsDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertCSPTopicDetails());

            this.layout.setWidget(0, 0, this.ids);
            new NumbersAndCommaValidator(this.ids);

            @NotNull final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.Common.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public void show() {
            super.show();
            this.ids.setFocus(true);
        }
    }

    private final XmlTagsDialog xmlTagsDialog = new XmlTagsDialog();
    private final XmlTemplatesDialog xmlTemplatesDialog = new XmlTemplatesDialog();
    private final CSPTopicDetailsDialog cspTopicDetailsDialog = new CSPTopicDetailsDialog();
    private final PlainTextXMLDialog plainTextXMLDialog = new PlainTextXMLDialog();
    private final EditorSettingsDialog editorSettingsDialog = new EditorSettingsDialog();
    private final PushButton settings = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.EditorSettings());

    @NotNull
    @Override
    public PlainTextXMLDialog getPlainTextXMLDialog() {
        return this.plainTextXMLDialog;
    }

    @NotNull
    @Override
    public XmlTemplatesDialog getXmlTemplatesDialog() {
        return this.xmlTemplatesDialog;
    }

    @NotNull
    @Override
    public XmlTagsDialog getXmlTagsDialog() {
        return this.xmlTagsDialog;
    }

    @NotNull
    @Override
    public CSPTopicDetailsDialog getCSPTopicDetailsDialog() {
        return this.cspTopicDetailsDialog;
    }

    @Override
    public EditorSettingsDialog getEditorSettingsDialog() {
        return this.editorSettingsDialog;
    }

    @Override
    public TopicXMLPresenterDriver getDriver() {
        return this.driver;
    }

    @Override
    public PushButton getEditorSettings() {
        return this.settings;
    }

    @Nullable
    @Override
    public AceEditor getEditor() {
        if (this.editor != null) {
            return this.editor.xml;
        }
        return null;
    }

    public TopicXMLView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.XMLEditing());

        LOGGER.info("ENTER TopicXMLView()");

        this.getPanel().addStyleName(CSSConstants.TopicView.TOPIC_XML_VIEW_PANEL);
        this.editorParent.addStyleName(CSSConstants.TopicView.ACE_EDITOR_PARENT);

        this.getXmlErrors().setReadOnly(true);
        this.getXmlErrors().addStyleName(CSSConstants.TopicView.TOPIC_XML_ERRORS);

        addLocalActionButton(this.settings);

        /* Add the projects */
        this.getPanel().setWidget(verticalPanel);

        verticalPanel.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(@NotNull final ResizeEvent event) {

            }
        });
    }

    @Override
    public void display(final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {

        /* SearchUIProjectsEditor is a grid */
        this.editor = new RESTTopicV1XMLEditor(readOnly, positiveDictionary, negativeDictionary, negativePhraseDictionary, tagDBLoader);
        /* Initialize the driver with the top-level editor */
        this.driver.initialize(this.editor);
        /* Copy the data in the object into the UI */
        this.driver.edit(topic);

        editorParent.clear();
        editorParent.add(this.editor);
    }


    @Override
    public void initialize(final int splitHeight) {
        this.getVerticalPanel().addSouth(xmlErrors, splitHeight);
        this.getVerticalPanel().setWidgetMinSize(xmlErrors, Constants.MINIMUM_SPLIT_SIZE);
        /*
        * We don't add the editor here, because it is null. Instead we add
        * a panel to hold the editor. Adding null here causes all sorts
        * of strange things.
        */
        this.getVerticalPanel().add(editorParent);
    }
}
