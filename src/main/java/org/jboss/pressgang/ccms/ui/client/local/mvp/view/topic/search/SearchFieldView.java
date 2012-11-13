package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchFieldEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class SearchFieldView extends BaseTemplateView<RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1> implements
        SearchFieldPresenter.Display {

    /** The GWT Editor Driver */
    private final SearchFieldPresenterDriver driver = GWT.create(SearchFieldPresenterDriver.class);
    /** The UI hierarchy */
    private final SearchUIFields searchUIFields = new SearchUIFields();

    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton tagsSearch = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final Label fields = UIUtilities.createDownLabel(PressGangCCMSUI.INSTANCE.Fields());

    @Override
    public SearchFieldPresenterDriver getDriver() {
        return driver;
    }

    @Override
    public SearchUIFields getSearchUIFields() {
        return searchUIFields;
    }

    @Override
    public PushButton getSearch() {
        return search;
    }

    @Override
    public PushButton getTagsSearch() {
        return tagsSearch;
    }

    public SearchFieldView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchFields());

        /* Build the action bar icons */
        addActionButton(search);
        addActionButton(tagsSearch);
        addActionButton(fields);

        addRightAlignedActionButtonPaddingPanel();

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
