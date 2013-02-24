package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchLocalePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUILocaleEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocales;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.Dependent;
import java.util.List;

/**
 * The search locales view.
 */
@Dependent
public class SearchLocalesView extends BaseTemplateView implements SearchLocalePresenter.Display {

    /**
     * The GWT Editor Driver
     */
    private final SearchLocalePresenter.LocalesPresenterDriver driver = GWT.create(SearchLocalePresenter.LocalesPresenterDriver.class);

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final Label locales = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Locales());

    private final SearchUILocales searchUILocales = new SearchUILocales();

    @Override
    public Label getLocales() {
        return locales;
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
    public PushButton getFields() {
        return fields;
    }

    public SearchLocalesView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());

        /* Build the action bar icons */
        addActionButton(searchTopics);
        addActionButton(tagsSearch);
        addActionButton(fields);
        addActionButton(locales);
        addActionButton(filters);
    }

    @Override
    public void display(@NotNull final  List<String> entity, final boolean readonly) {
        searchUILocales.initialize(entity);

        /* SearchUIProjectsEditor is a grid */
        final SearchUILocaleEditor editor = new SearchUILocaleEditor();
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(searchUILocales);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    public SimpleBeanEditorDriver<SearchUILocales, SearchUILocaleEditor> getDriver() {
        return driver;
    }

    @Override
    public SearchUILocales getSearchUILocales() {
        return searchUILocales;
    }
}
