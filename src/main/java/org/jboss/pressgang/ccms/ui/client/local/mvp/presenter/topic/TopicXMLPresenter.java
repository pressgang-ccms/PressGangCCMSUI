package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLView.PlainTextXMLDialog;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1XMLEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

@Dependent
public class TopicXMLPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicXMLView";
    
    private Integer topicId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    // Empty interface declaration, similar to UiBinder
    public interface TopicXMLPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1XMLEditor> {
    }
    
    

    public interface Display extends TopicViewInterface {
        
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
    
    public interface LogicComponent extends BasePropertyViewComponentInterface<Display>
    {
        
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
        component.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display,  display);
        component.getEntity(topicId);
    }
}
