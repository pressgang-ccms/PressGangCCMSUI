package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class WelcomePresenter implements EditableView, TemplatePresenter {
    
    public static final String HISTORY_TOKEN = "WelcomeView";
    
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
        display.setViewShown(true);        
        clearContainerAndAddTopLevelPanel(container, display);
        component.bind(display, display);
        component.setFeedbackLink(HISTORY_TOKEN);
    }

    @Override
    public void parseToken(final String historyToken) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
