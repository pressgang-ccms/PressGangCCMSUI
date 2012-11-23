package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTProjectCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTProjectCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTProjectV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.children.BaseChildrenComponentInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TagProjectsPresenter implements TemplatePresenter {
    public static final String HISTORY_TOKEN = "TagProjectsView";

    public interface Display extends TagViewInterface, BaseChildrenViewInterface<
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {
        
    }

    public interface LogicComponent extends BaseChildrenComponentInterface<Display, 
        RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1, 
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1,
        RESTProjectV1, RESTProjectCollectionV1, RESTProjectCollectionItemV1> {

    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void parseToken(final String searchToken) {

    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display,  display);
    }
}
