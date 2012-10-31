package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.project;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.orderedchildren.BaseOrderedChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseOrderedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class ProjectTagPresenter implements TemplatePresenter {
    
    public static final String HISTORY_TOKEN = "ProjectTagView";
    
    public interface Display
            extends
            BaseOrderedChildrenViewInterface<RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1, RESTProjectV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
    }

    public interface LogicComponent
            extends
            BaseOrderedChildrenComponentInterface<Display, RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1, RESTProjectV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> {
    }
    
    private Integer entityId;

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        display.initialize(null, false);
        component.bind(display, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        try {
            entityId = Integer.parseInt(GWTUtilities.removeHistoryToken(HISTORY_TOKEN, historyToken));
        } catch (final NumberFormatException ex) {
            entityId = null;
        }
    }
}
