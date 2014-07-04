package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import static com.google.common.base.Preconditions.checkState;

import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.SearchTagPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jetbrains.annotations.NotNull;

public class SearchUIProjectsEditor extends DockLayoutPanel implements Editor<SearchUIProjects> {

    private static final int BUTTON_COLUMN_WIDTH = 16;

    private final FailOverRESTCall failOverRESTCall;
    private final BaseTemplateView display;
    private final SearchPresenterDriver driver;
    protected final SearchUIProjects searchUIProjects;
    private boolean showBulkTags;
    protected final ListEditor<SearchUIProject, SearchUIProjectEditor> projects = ListEditor.of(new SearchUIProjectEditorSource());
    private final FlexTable projectButtonPanel = new FlexTable();
    private final ScrollPanel scrollPanel = new ScrollPanel();
    private SearchUIProjectEditor selectedProject;

    public boolean isShowBulkTags() {
        return showBulkTags;
    }

    public void setShowBulkTags(boolean showBulkTags) {
        this.showBulkTags = showBulkTags;
    }

    @Ignore
    public FlexTable getProjectButtonPanel() {
        return projectButtonPanel;
    }

    @Ignore
    public SearchUIProjectEditor getSelectedProject() {
        return selectedProject;
    }

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class SearchUIProjectEditorSource extends EditorSource<SearchUIProjectEditor> {
        @NotNull
        @Override
        public SearchUIProjectEditor create(final int index) {
            final SearchUIProjectEditor subEditor = new SearchUIProjectEditor(driver, searchUIProjects, showBulkTags);
            projectButtonPanel.setWidget(index, 0, subEditor.summary);

            subEditor.summary.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    selectedProject = subEditor;

                    final SearchUIProject uiProject = subEditor.getValue();
                    if (!uiProject.isInitialised()) {
                        if (uiProject.getId() > 0) {
                            // Normal project so just load the project and expand the tags
                            final RESTCallBack<RESTProjectV1> callBack = new RESTCallBack<RESTProjectV1>() {
                                @Override
                                public void success(final RESTProjectV1 project) {
                                    checkState(project.getTags() != null, "project.getTags() should not be null");

                                    final RESTProjectCollectionV1 tempProjects = new RESTProjectCollectionV1();
                                    tempProjects.addItem(project);

                                    // Add the project to each tag
                                    for (final RESTTagCollectionItemV1 item : project.getTags().getItems()) {
                                        item.getItem().setProjects(tempProjects);
                                    }

                                    uiProject.populateCategories(project, project.getTags(), uiProject.getFilter());
                                    subEditor.categories.setValue(uiProject.getCategories());
                                }
                            };

                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getProjectTagsWithCategories(uiProject.getId()),
                                    callBack, display);
                        } else {
                            // Common Project so get all tags that have no project
                            final RESTCallBack<RESTTagCollectionV1> callBack = new RESTCallBack<RESTTagCollectionV1>() {
                                @Override
                                public void success(final RESTTagCollectionV1 tags) {
                                    uiProject.populateCategoriesWithoutProject(tags, uiProject.getFilter());
                                    subEditor.categories.setValue(uiProject.getCategories());
                                }
                            };

                            final StringBuilder query = new StringBuilder(Constants.QUERY_PATH_SEGMENT_PREFIX);
                            query.append(CommonFilterConstants.NOT_PROJECT_IDS_FILTER_VAR).append("=");
                            final int baseQueryLength = query.length();
                            for (final SearchUIProject project : searchUIProjects.getProjects()) {
                                if (project.getId() > 0) {
                                    if (query.length() != baseQueryLength) {
                                        query.append(",");
                                    }
                                    query.append(project.getId());
                                }
                            }

                            failOverRESTCall.performRESTCall(FailOverRESTCallDatabase.getTagsFromQuery(query.toString()), callBack, display);
                        }
                    }

                    if (getCenter() != null) {
                        remove(getCenter());
                    }
                    add(subEditor);

                    /* Untoggle the other buttons */
                    for (@NotNull final SearchUIProjectEditor projectEditor : projects.getEditors()) {
                        if (projectEditor.summary != subEditor.summary) {
                            projectEditor.summary.removeStyleName(CSSConstants.Common.CUSTOM_BUTTON_DOWN);
                            projectEditor.summary.removeStyleName(CSSConstants.Common.CUSTOM_BUTTON);

                            projectEditor.summary.addStyleName(CSSConstants.Common.CUSTOM_BUTTON);
                        }
                    }
                }
            });

            return subEditor;
        }

        @Override
        public void dispose(@NotNull final SearchUIProjectEditor subEditor) {
            subEditor.summary.removeFromParent();
            subEditor.removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final SearchUIProjectEditor subEditor, final int index) {
            projectButtonPanel.setWidget(index, 0, subEditor);
        }
    }

    public SearchUIProjectsEditor(@NotNull final SearchPresenterDriver driver, @NotNull final SearchUIProjects searchUIProjects,
            @NotNull final BaseTemplateView display, @NotNull final FailOverRESTCall restClient) {
        super(Style.Unit.EM);

        this.driver = driver;
        this.searchUIProjects = searchUIProjects;
        this.display = display;
        failOverRESTCall = restClient;

        addStyleName(CSSConstants.TagListProjectsView.PROJECTS_LAYOUT);
        projectButtonPanel.addStyleName(CSSConstants.TagListProjectsView.PROJECTS_BUTTONS_LAYOUT);
        scrollPanel.addStyleName(CSSConstants.TagListProjectsView.PROJECTS_SCROLL_PANEL);

        scrollPanel.setWidget(projectButtonPanel);

        addWest(scrollPanel, BUTTON_COLUMN_WIDTH);
    }

}
