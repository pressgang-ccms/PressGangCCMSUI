package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TroubleshootingPresenter implements TemplatePresenter {
    
    public static final String HISTORY_TOKEN = "TroubleshootingView";
    
    public interface Display extends BaseTemplateViewInterface {

    }
    
    public interface LogicComponent extends Component<Display>
    {
        
    }

    @Inject
    private Display display;
    
    @Inject private LogicComponent component;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, display, display);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub

    }
}