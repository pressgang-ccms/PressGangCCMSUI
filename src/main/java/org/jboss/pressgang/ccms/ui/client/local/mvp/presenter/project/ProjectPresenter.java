package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project.ProjectPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.project.ProjectViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.projectview.RESTProjectV1BasicDetailsEditor;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ProjectPresenter extends ComponentBase<ProjectPresenter.Display> implements TemplatePresenter {

    // Empty interface declaration, similar to UiBinder
    public interface ProjectPresenterDriver extends SimpleBeanEditorDriver<RESTProjectV1, RESTProjectV1BasicDetailsEditor> {
    }

    public interface Display extends ProjectViewInterface,
            BaseEditorViewInterface<RESTProjectV1, RESTProjectV1BasicDetailsEditor> {

    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "ProjectView";

    private Integer entityId;

    @Inject
    private Display display;

    public Display getDisplay()
    {
        return display;
    }

    @Override
    public void parseToken(final String searchToken) {
        try {
            entityId = Integer.parseInt(removeHistoryToken(searchToken, HISTORY_TOKEN));
        } catch (final NumberFormatException ex) {
            entityId = null;
        }
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display);
    }

    public void process(final int topicId, final String pageId, final BaseTemplateViewInterface waitDisplay) {
        getEntity(entityId);
    }

    /**
     * Get the category from the database and use it to populate the editor in the view
     */
    public void getEntity(final Integer entityId) {
        final RESTCalls.RESTCallback<RESTProjectV1> callback = new BaseRestCallback<RESTProjectV1, ProjectPresenter.Display>(display,
                new BaseRestCallback.SuccessAction<RESTProjectV1, ProjectPresenter.Display>() {
                    @Override
                    public void doSuccessAction(final RESTProjectV1 retValue, final ProjectPresenter.Display display) {
                        display.initialize(retValue, false);
                    }
                }) {
        };
        RESTCalls.getUnexpandedProject(callback, entityId);
    }
}
