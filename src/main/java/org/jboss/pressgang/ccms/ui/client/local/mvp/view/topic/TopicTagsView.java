/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class TopicTagsView extends BaseTemplateView implements TopicTagsPresenter.Display {

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicTagsView.class.getName());

    final FlexTable layout = new FlexTable();

    /**
     * The GWT Editor Driver
     */
    private final TopicTagsPresenterDriver driver = GWT.create(TopicTagsPresenterDriver.class);
    private TopicTagViewProjectsEditor editor = new TopicTagViewProjectsEditor();
    private final PushButton add = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Add());

    private final HorizontalPanel newTagUIElementsPanel = new HorizontalPanel();
    private final ValueListBox<SearchUIProject> projects;
    private final ValueListBox<SearchUICategory> categories;
    private final ValueListBox<SearchUITag> myTags;

    public interface TopicTagsPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, TopicTagViewProjectsEditor> {
    }


    @Override
    public PushButton getAdd() {
        return add;
    }

    @NotNull
    @Override
    public ValueListBox<SearchUITag> getMyTags() {
        return myTags;
    }

    @NotNull
    @Override
    public ValueListBox<SearchUICategory> getCategoriesList() {
        return categories;
    }

    @NotNull
    @Override
    public ValueListBox<SearchUIProject> getProjectsList() {
        return projects;
    }

    @Override
    public TopicTagViewProjectsEditor getEditor() {
        return editor;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return driver;
    }

    public TopicTagsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TopicTags());

        try {
            LOGGER.log(Level.INFO, "ENTER TopicTagsView()");

            /* Add the layout to the panel */
            layout.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_NEW_TAG_TABLE);

            projects = new ValueListBox<SearchUIProject>(
                    new ProxyRenderer<SearchUIProject>(null) {
                        @Nullable
                        @Override
                        public String render(@Nullable final SearchUIProject object) {
                            return object == null ? null : object.getName();
                        }
                    }, new ProvidesKey<SearchUIProject>() {
                @Override
                public Object getKey(@NotNull final SearchUIProject item) {
                    return item.getId();
                }
            }
            );
            projects.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_NEW_TAG_PROJECTS_LIST);

            categories = new ValueListBox<SearchUICategory>(new ProxyRenderer<SearchUICategory>(null) {
                @Nullable
                @Override
                public String render(@Nullable final SearchUICategory object) {
                    return object == null ? null : object.getName();
                }
            }, new ProvidesKey<SearchUICategory>() {
                @Override
                public Object getKey(@NotNull final SearchUICategory item) {
                    return item.getId();
                }
            }
            );
            categories.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_NEW_TAG_CATEGORIES_LIST);

            myTags = new ValueListBox<SearchUITag>(new ProxyRenderer<SearchUITag>(null) {
                @Nullable
                @Override
                public String render(@Nullable final SearchUITag object) {
                    return object == null ? null : object.getName();
                }
            }, new ProvidesKey<SearchUITag>() {
                @Override
                public Object getKey(@NotNull final SearchUITag item) {
                    return item.getId();
                }
            }
            );
            myTags.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_NEW_TAG_TAGS_LIST);

            newTagUIElementsPanel.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_NEW_TAG_PARENT_PANEL);
            newTagUIElementsPanel.add(projects);
            newTagUIElementsPanel.add(categories);
            newTagUIElementsPanel.add(myTags);
            newTagUIElementsPanel.add(add);

            this.getPanel().addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_CONTENT_PANEL);
            this.getPanel().setWidget(layout);


        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicTagsView()");
        }
    }

    @Override
    public void updateNewTagCategoriesDisplay() {
        final List<SearchUICategory> listCategories = projects.getValue().getCategories();

        if (!listCategories.isEmpty()) {
            final SearchUICategory category = listCategories.get(0);

            categories.setValue(category);

            /*
             * The final list of acceptable values will be the object that was set with setValue() combined with the list we
             * supply to the setAcceptableValues() method. We need to use setValue() to set a valid value before we call
             * setAcceptableValues(), or otherwise an old value will appear in the list. We can't pass null to setValue()
             * either.
             * 
             * However, sometimes having the same object set via setValue() included in the list sent to setAcceptableValues()
             * can results in the item showing up twice in the drop down box. So we need to clone the original array and remove
             * the item that was set via setValue().
             */
            final List<SearchUICategory> clonedCategories = new ArrayList<SearchUICategory>(listCategories);

            categories.setAcceptableValues(clonedCategories);

            updateNewTagTagDisplay();
        }
    }

    @Override
    public void updateNewTagTagDisplay() {
        final List<SearchUITag> listTags = categories.getValue().getMyTags();

        if (!listTags.isEmpty()) {
            final SearchUITag tag = listTags.get(0);

            myTags.setValue(tag);

            @NotNull final List<SearchUITag> cloneTags = new ArrayList<SearchUITag>(listTags);

            myTags.setAcceptableValues(cloneTags);
        }
    }

    @Override
    public void initializeNewTags(@NotNull final RESTTagCollectionV1 retValue) {
        final SearchUIProjects tags = new SearchUIProjects(retValue);

        if (!tags.getProjects().isEmpty()) {
            projects.setValue(tags.getProjects().get(0));

            projects.setAcceptableValues(tags.getProjects());

            if (!projects.getValue().getCategories().isEmpty()) {
                categories.setValue(projects.getValue().getCategories().get(0));

                categories.setAcceptableValues(projects.getValue().getCategories());

                if (!categories.getValue().getMyTags().isEmpty()) {
                    myTags.setValue(categories.getValue().getMyTags().get(0));

                    myTags.setAcceptableValues(categories.getValue().getMyTags());
                }
            }
        }
    }

    @Override
    public void display(@NotNull final RESTBaseTopicV1<?, ?, ?> topic, final boolean readOnly) {

        /* reset the layout */
        layout.clear();

        if (!readOnly) {
            layout.setWidget(0, 0, newTagUIElementsPanel);
        }

        /* We can't assume the tags have been loaded by the time we initialize the view */
        if (topic.getTags() != null && topic.getTags().getItems() != null) {
            /* Build up a hierarchy of tags assigned to the topic */
            final SearchUIProjects projects = new SearchUIProjects(topic.getTags());
            /* SearchUIProjectsEditor is a simple panel */
            editor.setReadOnly(readOnly);
            /* Initialize the driver with the top-level editor */
            driver.initialize(editor);
            /* Copy the data in the object into the UI */
            driver.edit(projects);
            /* Add the projects */
            layout.setWidget(layout.getRowCount(), 0, editor);
        }
    }

    @Override
    protected void showWaiting() {
        final Image spinner = new Image(ImageResources.INSTANCE.spinner());
        spinner.addStyleName(CSSConstants.TopicView.TOPIC_TAG_VIEW_SPINNER);
        this.getPanel().setWidget(spinner);
    }

    @Override
    protected void hideWaiting() {
        this.getPanel().setWidget(layout);
    }
}
