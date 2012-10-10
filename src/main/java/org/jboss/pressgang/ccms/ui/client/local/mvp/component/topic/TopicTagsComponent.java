package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;

public class TopicTagsComponent extends TopicViewComponent<TopicTagsPresenter.Display> implements
        TopicTagsPresenter.LogicComponent {

    /**
     * A copy of all the tags in the system, broken down into project->category->tag. Used when adding new tags to a topic.
     */
    private final SearchUIProjects searchUIProjects = new SearchUIProjects();

    public SearchUIProjects getSearchUIProjects() {
        return searchUIProjects;
    }

    public void bind(final TopicTagsPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);
        getTags();
    }

    /**
     * Gets the tags, so they can be displayed and added to topics
     */
    private void getTags() {
        final RESTCalls.RESTCallback<RESTTagCollectionV1> callback = new BaseRestCallback<RESTTagCollectionV1, TopicTagsPresenter.Display>(
                display, new BaseRestCallback.SuccessAction<RESTTagCollectionV1, TopicTagsPresenter.Display>() {
                    @Override
                    public void doSuccessAction(RESTTagCollectionV1 retValue, TopicTagsPresenter.Display display) {
                        searchUIProjects.initialize(retValue);
                        display.initializeNewTags(searchUIProjects);
                    }
                }) {
            @Override
            public void failed() {
                super.failed();
                Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
            }
        };
        RESTCalls.getTags(callback);
    }

  
}
