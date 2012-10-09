package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;

import com.google.gwt.core.client.GWT;

public class SearchFieldView extends BaseTemplateView implements SearchFieldPresenter.Display {
    
    /** The GWT Editor Driver */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /** The UI hierarchy */
    private final SearchUIFields searchUIFields = new SearchUIFields();
    
    public SearchFieldView()
    {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());
        
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
