package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.propertyview.BasePropertyViewComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.editor.BaseEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.project.ProjectViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.projectview.RESTProjectV1BasicDetailsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class ProjectPresenter implements TemplatePresenter {
    
    public static final String HISTORY_TOKEN = "ProjectView";

    // Empty interface declaration, similar to UiBinder
    public interface ProjectPresenterDriver extends SimpleBeanEditorDriver<RESTProjectV1, RESTProjectV1BasicDetailsEditor> {
    }

    public interface Display extends ProjectViewInterface,
            BaseEditorViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

    }

    public interface LogicComponent extends
            BasePropertyViewComponentInterface<Display> {
        @Override
        void getEntity(final Integer categoryId);
    }

    private Integer entityId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

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
        component.bind(display, display);
        component.getEntity(entityId);
    }
}
