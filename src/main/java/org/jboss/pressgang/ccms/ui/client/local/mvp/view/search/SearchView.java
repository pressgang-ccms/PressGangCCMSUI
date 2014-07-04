package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;

public class SearchView extends BaseTemplateView implements SearchTagPresenter.Display {
    @Inject
    private FailOverRESTCall failOverRESTCall;
    /**
     * The GWT Editor Driver
     */
    private final SearchPresenterDriver driver = GWT.create(SearchPresenterDriver.class);
    /**
     * The UI hierarchy
     */
    private final SearchUIProjects searchUIProjects = new SearchUIProjects();

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    private final SearchUIProjectsEditor editor = new SearchUIProjectsEditor(driver, searchUIProjects, this, failOverRESTCall);

    @NotNull
    @Override
    public SearchUIProjects getSearchUIProjects() {
        return searchUIProjects;
    }

    @Override
    public SearchPresenterDriver getDriver() {
        return driver;
    }

    public SearchView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());
    }

    @Override
    public void display(final RESTTagCollectionV1 tagCollection, final boolean readOnly) {

        throw new UnsupportedOperationException("display() is not supported. Use displayExtended() instead.");
    }

    public void displayExtended(@NotNull final RESTProjectCollectionV1 projectCollection, final RESTFilterV1 filter,
            final boolean readOnly, final boolean showBulkTags) {

        /* Construct a hierarchy of tags from the tag collection */
        searchUIProjects.initializeFromProjects(projectCollection, filter);

        displayExtended(showBulkTags);
    }

    public void displayExtended(@NotNull final RESTTagCollectionV1 tagCollection, final RESTFilterV1 filter,
            final boolean readOnly, final boolean showBulkTags) {

        /* Construct a hierarchy of tags from the tag collection */
        searchUIProjects.initializeFromTags(tagCollection, filter);

        displayExtended(showBulkTags);
    }

    private void displayExtended(final boolean showBulkTags) {
        editor.setShowBulkTags(showBulkTags);
        /* Initialize the driver with the top-level editor */
        driver.initialize(editor);
        /* Copy the data in the object into the UI */
        driver.edit(searchUIProjects);
        /* Add the projects */
        this.getPanel().setWidget(editor);
    }

    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

    public SearchUIProjectsEditor getEditor() {
        return editor;
    }
}
