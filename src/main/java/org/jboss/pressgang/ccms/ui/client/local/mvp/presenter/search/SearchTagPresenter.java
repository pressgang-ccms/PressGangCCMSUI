package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.SearchUIProjectsEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;

@Dependent
public class SearchTagPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public interface Display extends BasePopulatedEditorViewInterface<RESTTagCollectionV1, SearchUIProjects, SearchUIProjectsEditor> {
        // Empty interface declaration, similar to UiBinder
        interface SearchPresenterDriver extends SimpleBeanEditorDriver<SearchUIProjects, SearchUIProjectsEditor> {
        }

        void displayExtended(@NotNull final RESTTagCollectionV1 tagCollection, final RESTFilterV1 filter, final boolean readOnly, final boolean showBulkTags);

        SearchUIProjects getSearchUIProjects();

        SearchUIProjectsEditor getEditor();
    }

    public static final String HISTORY_TOKEN = "SearchView";

    /**
     * A logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchTagPresenter.class.getName());

    @Inject
    private Display display;

    /**
     * The filter that will be used to set the tag's initial state
     */
    private RESTFilterV1 filter;

    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        display.setViewShown(true);
        bindExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

}
