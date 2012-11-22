package org.jboss.pressgang.ccms.ui.client.local.mvp.component;

import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.WelcomePresenter.Display;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

public class WelcomeCompnent extends ComponentBase<WelcomePresenter.Display> implements WelcomePresenter.LogicComponent {
    
    @Override
    public void bind(final WelcomePresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
        
        final RESTCalls.RESTCallback<RESTTopicV1> callback = new BaseRestCallback<RESTTopicV1, WelcomePresenter.Display>(
                display,
                new BaseRestCallback.SuccessAction<RESTTopicV1, WelcomePresenter.Display>() {

                    @Override
                    public void doSuccessAction(final RESTTopicV1 retValue, final Display display) {
                        display.initialize(retValue);
                    }
                    
                }) {

        };
        RESTCalls.getTopic(callback, ServiceConstants.WELCOME_VIEW_CONTENT_TOPIC);
    }
}
