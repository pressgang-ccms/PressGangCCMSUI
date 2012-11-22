package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class WelcomePresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "WelcomeView";

    public interface Display extends BaseTemplateViewInterface {
        void initialize(final RESTTopicV1 topic);
    }

    public interface LogicComponent extends Component<Display> {

    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Override
    public void go(final HasWidgets container) {
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(display, display);
        component.setFeedbackLink(HISTORY_TOKEN);
    }

    @Override
    public void parseToken(final String historyToken) {

    }
}
