package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search.topic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.topic.TopicSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.topic.TopicSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.TopicSearchUIFields;
import org.jetbrains.annotations.NotNull;

public class TopicSearchFieldView extends BaseTemplateView implements TopicSearchFieldPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /**
     * The UI hierarchy
     */
    private final TopicSearchUIFields topicSearchUIFields = new TopicSearchUIFields();

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());
    private final Label fields = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());

    @Override
    public SearchFieldPresenterDriver getDriver() {
        return driver;
    }

    @NotNull
    @Override
    public TopicSearchUIFields getFields() {
        return topicSearchUIFields;
    }

    @Override
    public PushButton getSearchButton() {
        return searchTopics;
    }

    @Override
    public PushButton getTagsButton() {
        return tagsSearch;
    }

    @Override
    public PushButton getFiltersButton() {
        return filters;
    }

    @Override
    public PushButton getLocales() {
        return locales;
    }

    public TopicSearchFieldView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());

        /* Build the action bar icons */
        addActionButton(searchTopics);
        addActionButton(tagsSearch);
        addActionButton(fields);
        //addActionButton(locales);
        addActionButton(filters);
    }

    @Override
    public void display(final RESTFilterV1 filter, final boolean readOnly) {
        topicSearchUIFields.initialize(filter);
        /* SearchUIProjectsEditor is a grid */
        @NotNull final TopicSearchFieldUIEditor editor = new TopicSearchFieldUIEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(topicSearchUIFields);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}
