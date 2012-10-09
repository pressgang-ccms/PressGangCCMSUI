package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;

public class SearchUIProjectsEditor extends Grid implements Editor<SearchUIProjects> {
    private final SearchPresenterDriver driver;
    final SearchUIProjects searchUIProjects;
    final ListEditor<SearchUIProject, SearchUIProjectEditor> projects = ListEditor.of(new SearchUIProjectEditorSource());
    private final FlexTable projectButtonPanel = new FlexTable();
    private final ScrollPanel scrollPanel = new ScrollPanel();

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     * 
     * @author Matthew Casperson
     */
    private class SearchUIProjectEditorSource extends EditorSource<SearchUIProjectEditor> {
        @Override
        public SearchUIProjectEditor create(final int index) {
            final SearchUIProjectEditor subEditor = new SearchUIProjectEditor(driver, searchUIProjects);
            projectButtonPanel.setWidget(index, 0, subEditor.summary);

            subEditor.summary.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    SearchUIProjectsEditor.this.setWidget(0, 1, subEditor);

                    /* Untoggle the other buttons */
                    for (final SearchUIProjectEditor projectEditor : projects.getEditors()) {
                        if (projectEditor.summary != subEditor.summary) {
                            projectEditor.summary.removeStyleName(CSSConstants.CUSTOM_BUTTON_DOWN);
                            projectEditor.summary.removeStyleName(CSSConstants.CUSTOM_BUTTON);

                            projectEditor.summary.addStyleName(CSSConstants.CUSTOM_BUTTON);
                        }
                    }
                }
            });

            return subEditor;
        }

        @Override
        public void dispose(final SearchUIProjectEditor subEditor) {
            subEditor.summary.removeFromParent();
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(final SearchUIProjectEditor subEditor, final int index) {
            projectButtonPanel.setWidget(index, 0, subEditor);
        }
    }

    public SearchUIProjectsEditor(final SearchPresenterDriver driver, final SearchUIProjects searchUIProjects) {
        super(1, 2);

        this.driver = driver;
        this.searchUIProjects = searchUIProjects;

        this.addStyleName(CSSConstants.PROJECTS_LAYOUT);
        projectButtonPanel.addStyleName(CSSConstants.PROJECTS_BUTTONS_LAYOUT);
        scrollPanel.addStyleName(CSSConstants.PROJECTS_SCROLL_PANEL);

        scrollPanel.setWidget(projectButtonPanel);

        this.setWidget(0, 0, scrollPanel);
    }

}
