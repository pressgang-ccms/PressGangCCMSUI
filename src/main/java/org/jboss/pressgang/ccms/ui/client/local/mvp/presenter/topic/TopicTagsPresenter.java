package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicXMLErrorsView;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.SearchUITag;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;

@Dependent
public class TopicTagsPresenter extends TemplatePresenter {
    public static final String HISTORY_TOKEN = "TopicTagsView";
    
    private String topicId;

    @Inject
    private Display display;

    public interface Display extends TopicViewInterface {
        void initializeNewTags(final SearchUIProjects tags);

        void updateNewTagCategoriesDisplay();

        void updateNewTagTagDisplay();

        ValueListBox<SearchUITag> getMyTags();

        ValueListBox<SearchUICategory> getCategoriesList();

        ValueListBox<SearchUIProject> getProjectsList();

        TopicTagViewProjectsEditor getEditor();

        PushButton getAdd();
    }

    @Override
    public void parseToken(final String searchToken) {
        topicId = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        getTopic();

        bind();
    }

    private void getTopic() {
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new RESTCalls.RESTCallback<RESTTopicV1>() {
            @Override
            public void begin() {
                display.addWaitOperation();
            }

            @Override
            public void generalException(final Exception ex) {
                display.removeWaitOperation();
            }

            @Override
            public void success(final RESTTopicV1 retValue) {
                try {
                    display.initialize(retValue, false, SplitType.DISABLED);
                } finally {
                    display.removeWaitOperation();
                }
            }

            @Override
            public void failed() {
                display.removeWaitOperation();
            }
        };

        try {
            RESTCalls.getTopic(callback, Integer.parseInt(topicId));
        } catch (final NumberFormatException ex) {
            display.removeWaitOperation();
        }
    }

    private void bind() {

    }
}
