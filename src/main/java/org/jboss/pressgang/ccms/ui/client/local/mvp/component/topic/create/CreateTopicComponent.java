package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.create;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create.CreateTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

/**
 * A component used to provide the logic for the create topic view
 * 
 * @author Matthew Casperson
 * 
 */
public class CreateTopicComponent extends ComponentBase<CreateTopicPresenter.Display> implements
        CreateTopicPresenter.LogicComponent {

    private TopicPresenter.Display topic;
    private TopicTagsPresenter.Display topicTags;
    private TopicXMLPresenter.Display topicXML;
    private TopicXMLErrorsPresenter.Display topicXMLErrors;
    private TopicViewInterface[] topicViews;

    /** The last displayed view */
    private TopicViewInterface lastView;

    /** The new topic being created */
    private final RESTTopicV1 newTopic = new RESTTopicV1();
    
    /**
     * A click handler to add a tag to a topic
     * 
     * @author Matthew Casperson
     */
    private class AddTagClickhandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final RESTTagV1 selectedTag = topicTags.getMyTags().getValue().getTag().getItem();

            /* Need to deal with re-adding removed tags */
            for (final RESTTagCollectionItemV1 tag : newTopic.getTags().getItems()) {
                if (tag.getItem().getId().equals(selectedTag.getId())) {
                    if (tag.getState() == RESTBaseCollectionItemV1.REMOVE_STATE) {
                        tag.setState(RESTBaseCollectionItemV1.UNCHANGED_STATE);

                        /* Redisplay the view */
                        updateDisplayedTopicView(lastView);

                        return;
                    } else {
                        /* Don't add tags twice */
                        Window.alert(PressGangCCMSUI.INSTANCE.TagAlreadyExists());
                        return;
                    }
                }
            }

            /* Get the selected tag, and clone it */
            final RESTTagV1 selectedTagClone = selectedTag.clone(true);
            /* Add the tag to the topic */
            newTopic.getTags().addNewItem(selectedTagClone);
            /* Redisplay the view */
            updateDisplayedTopicView(lastView);
        }
    }

    /**
     * A click handler to remove a tag from a topic
     * 
     * @author Matthew Casperson
     */
    private class DeleteTagClickHandler implements ClickHandler {
        private final RESTTagCollectionItemV1 tag;

        public DeleteTagClickHandler(final RESTTagCollectionItemV1 tag) {
            if (tag == null) {
                throw new IllegalArgumentException("tag cannot be null");
            }

            this.tag = tag;
        }

        @Override
        public void onClick(final ClickEvent event) {
            if (tag.getState() == RESTBaseCollectionItemV1.ADD_STATE) {
                /* Tag was added and then removed, so we just delete the tag */
                newTopic.getTags().getItems().remove(tag);
            } else {
                /* Otherwise we set the tag as removed */
                tag.setState(RESTBaseCollectionItemV1.REMOVE_STATE);
            }

            updateDisplayedTopicView(lastView);
        }
    }

    @Override
    public void bind(final TopicPresenter.Display topic, final TopicTagsPresenter.Display topicTags,
            final TopicXMLPresenter.Display topicXML, final TopicXMLErrorsPresenter.Display topicXMLErrors,
            final CreateTopicPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {

        super.bind(display, waitDisplay);
        
        /* Create an intial collection to hold any new tags */
        newTopic.setTags(new RESTTagCollectionV1());

        this.topic = topic;
        this.topicTags = topicTags;
        this.topicXML = topicXML;
        this.topicXMLErrors = topicXMLErrors;
        topicViews = new TopicViewInterface[] { topic, topicTags, topicXML, topicXMLErrors };

        topic.initialize(newTopic, false, true, SplitType.NONE);
        topicTags.initialize(newTopic, false, true, SplitType.NONE);
        topicXML.initialize(newTopic, false, true, SplitType.NONE);
        topicXMLErrors.initialize(newTopic, false, true, SplitType.NONE);

        updateDisplayedTopicView(topic);

        bindTopActionButtons();
        bindTagButtons();
    }
    
    private void bindTagButtons()
    {
        topicTags.getAdd().addClickHandler(new AddTagClickhandler());
    }

    private void bindTopActionButtons() {
        final ClickHandler displayTopicClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topic);

            }
        };

        final ClickHandler displayTagsClickHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicTags);
            }
        };

        final ClickHandler topicXMLClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXML);

            }
        };

        final ClickHandler topicXMLErrorsClickHandler = new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                updateDisplayedTopicView(topicXMLErrors);

            }
        };

        for (final TopicViewInterface view : topicViews) {
            view.getTopicTags().addClickHandler(displayTagsClickHandler);
            view.getFields().addClickHandler(displayTopicClickHandler);
            view.getXml().addClickHandler(topicXMLClickHandler);
            view.getXmlErrors().addClickHandler(topicXMLErrorsClickHandler);
        }
    }

    /**
     * Sets the viewShown property on the views to reflect the currently displayed view
     * 
     * @param shownDisplay The currently displayed view
     */
    private void setShownDisplay(final TopicViewInterface shownDisplay) {
        this.topic.setViewShown(shownDisplay == topic);
        this.topicTags.setViewShown(shownDisplay == topicTags);
        this.topicXML.setViewShown(shownDisplay == topicXML);
        this.topicXMLErrors.setViewShown(shownDisplay == topicXMLErrors);
    }

    /**
     * Updates the current topic view
     */
    private void updateDisplayedTopicView(final TopicViewInterface selectedView) {
        if (lastView != selectedView) {
            setShownDisplay(selectedView);

            display.getTopActionParentPanel().clear();
            display.getTopActionParentPanel().setWidget(selectedView.getTopActionPanel());

            display.getPanel().clear();
            display.getPanel().setWidget(selectedView.getPanel());
        }
        
        /* Reload the tags view */
        if (selectedView == this.topicTags)
        {
            selectedView.initialize(newTopic, false, true, SplitType.NONE);
            bindTagEditingButtons();
        }
        
        lastView = selectedView;
    }
    
    /**
     * Add behaviour to the tag delete buttons
     */
    public void bindTagEditingButtons() {

        /* This will be null if the tags have not been downloaded */
        if (topicTags.getEditor() != null) {
            for (final TopicTagViewProjectEditor topicTagViewProjectEditor : topicTags.getEditor().projects.getEditors()) {
                for (final TopicTagViewCategoryEditor topicTagViewCategoryEditor : topicTagViewProjectEditor.categories
                        .getEditors()) {
                    for (final TopicTagViewTagEditor topicTagViewTagEditor : topicTagViewCategoryEditor.myTags.getEditors()) {
                        topicTagViewTagEditor.getDelete().addClickHandler(
                                new DeleteTagClickHandler(topicTagViewTagEditor.getTag().getTag()));
                    }
                }
            }
        }
    }
}
