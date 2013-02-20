package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;

public class SearchFieldView extends BaseTemplateView implements SearchFieldPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /**
     * The UI hierarchy
     */
    private final SearchUIFields searchUIFields = new SearchUIFields();

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());
    private final Label fields = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());

    @Override
    public SearchFieldPresenterDriver getDriver() {
        return driver;
    }

    @Override
    public SearchUIFields getSearchUIFields() {
        return searchUIFields;
    }

    @Override
    public PushButton getSearchTopics() {
        return searchTopics;
    }

    @Override
    public PushButton getTagsSearch() {
        return tagsSearch;
    }

    @Override
    public PushButton getFilters() {
        return filters;
    }

    @Override
    public PushButton getLocales() {
        return locales;
    }

    public SearchFieldView() {
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
        searchUIFields.initialize(filter);
        /* SearchUIProjectsEditor is a grid */
        final SearchFieldEditor editor = new SearchFieldEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(searchUIFields);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}
