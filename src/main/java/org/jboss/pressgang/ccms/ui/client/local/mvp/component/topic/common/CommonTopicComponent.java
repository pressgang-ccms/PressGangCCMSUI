package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;

public class CommonTopicComponent {
    /**
     * Retrieve a list of locales from the server
     */
    public static void populateLocales(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {  
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {
                        /* Get the list of locales from the StringConstant */
                        final List<String> locales = new LinkedList<String>(Arrays.asList(retValue.getValue().replaceAll("\\r\\n", "")
                                .replaceAll("\\n", "").replaceAll(" ", "").split(",")));

                        /* Clean the list */
                        while (locales.contains("")) {
                            locales.remove("");
                        }

                        Collections.sort(locales);
                        
                        loadedCallback.stringListLoaded(locales);
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.LOCALE_STRING_CONSTANT);
    }
    
    /**
     * Retrieve a list of xml elements from the server
     */
    public static void populateXMLElements(final BaseTemplateViewInterface waitDisplay, final StringListLoaded loadedCallback) {
        final RESTCalls.RESTCallback<RESTStringConstantV1> callback = new BaseRestCallback<RESTStringConstantV1, BaseTemplateViewInterface>(
                waitDisplay, new BaseRestCallback.SuccessAction<RESTStringConstantV1, BaseTemplateViewInterface>() {
                    @Override
                    public void doSuccessAction(final RESTStringConstantV1 retValue, final BaseTemplateViewInterface display) {

                        /* Get the list of locales from the StringConstant */
                        final List<String> xmlElements = new LinkedList<String>(Arrays.asList(retValue.getValue()
                                .replaceAll("\r\n", "\n").replaceAll(" ", "").split("\n")));

                        /* Clean the list */
                        while (xmlElements.contains("")) {
                            xmlElements.remove("");
                        }

                        Collections.sort(xmlElements);

                        loadedCallback.stringListLoaded(xmlElements);

                        
                    }
                }) {
        };

        RESTCalls.getStringConstant(callback, ServiceConstants.DOCBOOK_ELEMENTS_STRING_CONSTANT_ID);
    }
}
