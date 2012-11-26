package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import java.util.List;
import java.util.Map;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter.TopicXMLPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public class TopicXMLView extends TopicViewBase implements TopicXMLPresenter.Display {

    /** The GWT Editor Driver */
    private final TopicXMLPresenterDriver driver = GWT.create(TopicXMLPresenterDriver.class);

    private RESTTopicV1XMLEditor editor;

    private final ToggleButton lineWrap = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.LineWrap());
    private final ToggleButton showInvisibles = UIUtilities.createToggleButton(PressGangCCMSUI.INSTANCE.ShowHiddenCharacters());
    
    public class PlainTextXMLDialog extends DialogBox implements TopicXMLPresenter.Display.PlainTextXMLDialog
    {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextArea textArea = new TextArea();
        private final FlexTable layout = new FlexTable();
        
        @Override
        public PushButton getOK() {
            return ok;
        }

        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public TextArea getTextArea() {
            return textArea;
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
            
            textArea.addStyleName(CSSConstants.PlainTextXMLDialog.PLAIN_TEXT_XML_DIALOG_TEXTAREA);
            
            layout.setWidget(0, 0, textArea);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            this.add(layout);
        }
        
    }
    
    /** The dialog box that displays a list of docbook tags */
    public class XmlTagsDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTagsDialog
    {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final PushButton moreInfo = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.MoreInfo());
        private final ListBox options = new ListBox(false);
        private final FlexTable layout = new FlexTable();
       
        @Override
        public PushButton getOK() {
            return ok;
        }
        
        @Override
        public PushButton getCancel() {
            return cancel;
        }
        
        @Override
        public PushButton getMoreInfo() {
            return moreInfo;
        }

        @Override
        public ListBox getOptions() {
            return options;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }
        
        @Override 
        public void setSuggestions(final List<String> suggestions)
        {
            options.clear();
            
            if (suggestions != null)
            {
                for (final String suggestion : suggestions)
                {
                    options.addItem(suggestion);
                }
            }
        }
        
        public XmlTagsDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertXMLElement());
            
            options.setVisibleItemCount(10);
            layout.setWidget(0, 0, options);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(moreInfo);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            this.add(layout);
        }
        
        @Override
        public void show()
        {
            super.show();
            options.setFocus(true);
        }     
        
        /**
         * Select the first item when the box is closed
         */
        @Override
        public void hide()
        {
            super.hide();
            options.setSelectedIndex(0);
        }
    }
    
    /** The dialog box that displays a list of docbook templates */
    public class XmlTemplatesDialog extends DialogBox implements TopicXMLPresenter.Display.XmlTemplatesDialog
    {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final ListBox options = new ListBox(false);
        private final FlexTable layout = new FlexTable();
       
        @Override
        public PushButton getOK() {
            return ok;
        }
        
        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public ListBox getOptions() {
            return options;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }
        
        @Override 
        public void setSuggestions(final Map<String, String> suggestions)
        {
            options.clear();
            
            if (suggestions != null)
            {
                for (final String suggestion : suggestions.keySet())
                {
                    options.addItem(suggestion, suggestions.get(suggestion));
                }
            }
        }
        
        public XmlTemplatesDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertXMLElement());
            
            options.setVisibleItemCount(10);
            layout.setWidget(0, 0, options);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            this.add(layout);
        }
        
        @Override
        public void show()
        {
            super.show();
            options.setFocus(true);
        }     
        
        /**
         * Select the first item when the box is closed
         */
        @Override
        public void hide()
        {
            super.hide();
            options.setSelectedIndex(0);
        }
    }
    
    public class CSPTopicDetailsDialog extends DialogBox implements TopicXMLPresenter.Display.CSPTopicDetailsDialog
    {
        private final PushButton ok = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.OK());
        private final PushButton cancel = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Cancel());
        private final TextBox ids = new TextBox();
        private final FlexTable layout = new FlexTable();
       
        @Override
        public PushButton getOK() {
            return ok;
        }
        
        @Override
        public PushButton getCancel() {
            return cancel;
        }

        @Override
        public TextBox getIds() {
            return ids;
        }

        @Override
        public DialogBox getDialogBox() {
            return this;
        }

        
        public CSPTopicDetailsDialog() {
            this.setGlassEnabled(true);
            this.setText(PressGangCCMSUI.INSTANCE.InsertCSPTopicDetails());

            layout.setWidget(0, 0, ids);
            new NumbersAndCommaValidator(ids);

            final HorizontalPanel buttonPanel = new HorizontalPanel();
            buttonPanel.addStyleName(CSSConstants.DIALOG_BOX_OK_CANCEL_PANEL);
            buttonPanel.add(cancel);
            buttonPanel.add(ok);

            layout.setWidget(1, 0, buttonPanel);

            this.add(layout);
        }
        
        @Override
        public void show()
        {
            super.show();
            ids.setFocus(true);
        }        
    }
    
    private final XmlTagsDialog xmlTagsDialog = new XmlTagsDialog();
    private final XmlTemplatesDialog xmlTemplatesDialog = new XmlTemplatesDialog();
    private final CSPTopicDetailsDialog cspTopicDetailsDialog = new CSPTopicDetailsDialog();
    private final PlainTextXMLDialog plainTextXMLDialog = new PlainTextXMLDialog();

    public PlainTextXMLDialog getPlainTextXMLDialog() {
        return plainTextXMLDialog;
    }

    @Override
    public XmlTemplatesDialog getXmlTemplatesDialog() {
        return xmlTemplatesDialog;
    }

    @Override
    public XmlTagsDialog getXmlTagsDialog() {
        return xmlTagsDialog;
    }

    @Override
    public CSPTopicDetailsDialog getCSPTopicDetailsDialog() {
        return cspTopicDetailsDialog;
    }

    @Override
    public ToggleButton getShowInvisibles() {
        return showInvisibles;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    @Override
    public ToggleButton getLineWrap() {
        return lineWrap;
    }

    @Override
    public AceEditor getEditor() {
        if (editor != null) {
            return editor.xml;
        }
        return null;
    }

    public TopicXMLView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.XMLEditing());
        this.getPanel().addStyleName(CSSConstants.TOPIC_XML_VIEW_PANEL);
    }

    @Override
    protected void populateTopActionBar(final boolean newTopic, final boolean hasErrors) {
        super.populateTopActionBar(newTopic, hasErrors);

        addActionButton(this.getRenderedSplit());
        addActionButton(this.getRendered());
        addActionButton(this.getXmlDown());
        addActionButton(this.getXmlErrors());
        addActionButton(this.getFields());
        addActionButton(this.getTopicTags());
        if (!newTopic) {
            addActionButton(this.getBugs());
            addActionButton(this.getHistory());
        }
        addActionButton(this.getCsps());
        addActionButton(this.getSave());

        fixReadOnlyButtons();

        addRightAlignedActionButtonPaddingPanel();
        addActionButton(lineWrap);
        addActionButton(showInvisibles);
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType, final List<String> locales, final Boolean showImages) {
        this.readOnly = readOnly;
        populateTopActionBar(newTopic, topic.getXmlErrors() != null && !topic.getXmlErrors().trim().isEmpty());
        buildSplitViewButtons(splitType);

        /* SearchUIProjectsEditor is a grid */
        editor = new RESTTopicV1XMLEditor(readOnly);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topic);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }
}
