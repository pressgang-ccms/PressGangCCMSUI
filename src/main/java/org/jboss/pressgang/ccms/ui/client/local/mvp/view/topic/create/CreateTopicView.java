package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.create;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.create.CreateTopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class CreateTopicView extends BaseTemplateView implements CreateTopicPresenter.Display {

    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();
    
    public CreateTopicView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.CreateTopic());
    }
    
    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();        
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();        
    }

}

