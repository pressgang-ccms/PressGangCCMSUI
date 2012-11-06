package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.RESTTopicV1BasicDetailsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TopicPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "TopicView";

    // Empty interface declaration, similar to UiBinder
    public interface TopicPresenterDriver extends SimpleBeanEditorDriver<RESTTopicV1, RESTTopicV1BasicDetailsEditor> {
    }

    public interface Display extends TopicViewInterface, BaseEditorViewInterface {

    }

    public interface LogicComponent extends BasePropertyViewComponentInterface<Display> {

    }

    private Integer topicId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

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
        component.bind(display,  display);
        component.getEntity(topicId);
    }

}
