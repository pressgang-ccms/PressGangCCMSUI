package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TopicXMLView extends BaseTemplateView implements TopicXMLPresenter.Display {

    /** The GWT Editor Driver */
    private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

    private RESTTopicV1XMLEditor editor;

    private final ToggleButton lineWrap = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.LineWrap());
    private final ToggleButton showInvisibles = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.ShowHiddenCharacters());

    public static class PlainTextXMLDialog extends DialogBox implements TopicXMLPresenter.Display.PlainTextXMLDialog {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private TextArea textArea;
        private final FlexTable layout = new FlexTable();

        @Override
        public final PushButton getOK() {
            return this.ok;
        }

        @Override
        public final PushButton getCancel() {
            return this.cancel;
        }

        @Override
        public final void setText(final String text) {
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
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
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

    /** The dialog box that displays a list of docbook tags. */
    public static class XmlTagsDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTagsDialog {
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

        @Override
        public ListBox getOptions() {
            return this.options;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        @Override
        public void setSuggestions(final List<String> suggestions) {
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
            this.layout.setWidget(0, 0, this.options);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
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

    /** The dialog box that displays a list of docbook templates. */
    public static class XmlTemplatesDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTemplatesDialog {
        private static final int NUMBER_OF_VISIBLE_ITEMS = 10;
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final ListBox options = new ListBox(false);
        private final FlexTable layout = new FlexTable();

        @Override
        public final PushButton getOK() {
            return this.ok;
        }

        @Override
        public final PushButton getCancel() {
            return this.cancel;
        }

        @Override
        public final ListBox getOptions() {
            return this.options;
        }

        @Override
        public final DialogBox getDialogBox() {
            return this;
        }

        @Override
        public final void setSuggestions(final Map<String, String> suggestions) {
            this.options.clear();

            if (suggestions != null) {
                for (final Entry<String, String> suggestion : suggestions.entrySet()) {
                    this.options.addItem(suggestion.getKey(), suggestion.getValue());
                }
            }
        }

        public XmlTemplatesDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertXMLElement());

            this.options.setVisibleItemCount(NUMBER_OF_VISIBLE_ITEMS);
            this.layout.setWidget(0, 0, this.options);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public final void show() {
            super.show();
            this.options.setFocus(true);
        }

        /**
         * Select the first item when the box is closed.
         */
        @Override
        public final void hide() {
            super.hide();
            this.options.setSelectedIndex(0);
        }
    }

    public static class CSPTopicDetailsDialog extends DialogBox implements TopicXMLPresenter.Display.CSPTopicDetailsDialog {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextBox ids = new TextBox();
        private final FlexTable layout = new FlexTable();

        @Override
        public final PushButton getOK() {
            return this.ok;
        }

        @Override
        public final PushButton getCancel() {
            return this.cancel;
        }

        @Override
        public final TextBox getIds() {
            return this.ids;
        }

        @Override
        public final DialogBox getDialogBox() {
            return this;
        }

        public CSPTopicDetailsDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertCSPTopicDetails());

            this.layout.setWidget(0, 0, this.ids);
            new NumbersAndCommaValidator(this.ids);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(this.cancel);
            buttonPanel.add(this.ok);

            this.layout.setWidget(1, 0, buttonPanel);

            this.add(this.layout);
        }

        @Override
        public final void show() {
            super.show();
            this.ids.setFocus(true);
        }
    }

    private final XmlTagsDialog xmlTagsDialog = new XmlTagsDialog();
    private final XmlTemplatesDialog xmlTemplatesDialog = new XmlTemplatesDialog();
    private final CSPTopicDetailsDialog cspTopicDetailsDialog = new CSPTopicDetailsDialog();
    private final PlainTextXMLDialog plainTextXMLDialog = new PlainTextXMLDialog();

    @Override
    public PlainTextXMLDialog getPlainTextXMLDialog() {
        return this.plainTextXMLDialog;
    }

    @Override
    public XmlTemplatesDialog getXmlTemplatesDialog() {
        return this.xmlTemplatesDialog;
    }

    @Override
    public XmlTagsDialog getXmlTagsDialog() {
        return this.xmlTagsDialog;
    }

    @Override
    public CSPTopicDetailsDialog getCSPTopicDetailsDialog() {
        return this.cspTopicDetailsDialog;
    }

    @Override
    public ToggleButton getShowInvisibles() {
        return this.showInvisibles;
    }

    @Override
    public TopicXMLPresenterDriver getDriver() {
        return this.driver;
    }

    @Override
    public ToggleButton getLineWrap() {
        return this.lineWrap;
    }

    @Override
    public AceEditor getEditor() {
        if (this.editor != null) {
            return this.editor.xml;
        }
        return null;
    }

    public TopicXMLView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.XMLEditing());
        this.getPanel().addStyleName(CSSConstants.TOPIC_XML_VIEW_PANEL);

        addLocalActionButton(this.lineWrap);
        addLocalActionButton(this.showInvisibles);
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType,
            final List<String> locales, final Boolean showImages) {

        /* SearchUIProjectsEditor is a grid */
        this.editor = new RESTTopicV1XMLEditor(readOnly);
        /* Initialize the driver with the top-level editor */
        this.driver.initialize(this.editor);
        /* Copy the data in the object into the UI */
        this.driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(this.editor);
    }
}
