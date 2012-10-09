package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PushButton;

public class SearchView extends BaseTemplateView implements SearchPresenter.Display {    

    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());

    /** The GWT Editor Driver */
    private final SearchPresenterDriver driver = GWT.create(SearchPresenterDriver.class);
    /** The UI hierarchy */
    private final SearchUIProjects searchUIProjects = new SearchUIProjects();
    
    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

    @Override
    public SearchUIProjects getSearchUIProjects() {
        return searchUIProjects;
    }

    @Override
    public SearchPresenterDriver getDriver() {
        return driver;
    }

    @Override
    public PushButton getSearch() {
        return search;
    }

    public SearchView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());
    }

    @Override
    public void initialise(final RESTTagCollectionV1 tags) {
       
        /* Build the action bar icons */
        addActionButton(search);

        addRightAlignedActionButtonPaddingPanel();

        /* Construct a hierarchy of tags from the tag collection */
        getSearchUIProjects().initialize(tags);

        /* SearchUIProjectsEditor is a grid */
        final SearchUIProjectsEditor editor = new SearchUIProjectsEditor(driver, searchUIProjects);
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
}
