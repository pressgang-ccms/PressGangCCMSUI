package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search.contentspec;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.contentspec.ContentSpecSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.ContentSpecSearchUIFields;
import org.jetbrains.annotations.NotNull;

public class ContentSpecSearchFieldView extends BaseTemplateView implements ContentSpecSearchFieldPresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /**
     * The UI hierarchy
     */
    private final ContentSpecSearchUIFields contentSpecSearchUIFields = new ContentSpecSearchUIFields();

    private final PushButton searchContentSpecs = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final Label fields = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());

    @Override
    public SearchFieldPresenterDriver getDriver() {
        return driver;
    }

    @NotNull
    @Override
    public ContentSpecSearchUIFields getFields() {
        return contentSpecSearchUIFields;
    }

    @Override
    public PushButton getSearchButton() {
        return searchContentSpecs;
    }

    @Override
    public PushButton getTagsButton() {
        return tagsSearch;
    }

    @Override
    public PushButton getLocales() {
        return locales;
    }

    @Override
    public PushButton getFiltersButton() {
        return filters;
    }

    public ContentSpecSearchFieldView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());

        /* Build the action bar icons */
        addActionButton(searchContentSpecs);
        addActionButton(tagsSearch, true);
        addActionButton(fields);
        addActionButton(locales);
        addActionButton(filters);
    }

    @Override
    public void display(final RESTFilterV1 filter, final boolean readOnly) {
        contentSpecSearchUIFields.initialize(filter);
        /* SearchUIProjectsEditor is a grid */
        @NotNull final ContentSpecSearchFieldUIEditor editor = new ContentSpecSearchFieldUIEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(contentSpecSearchUIFields);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }


}
