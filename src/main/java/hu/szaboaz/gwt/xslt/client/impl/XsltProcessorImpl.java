/*
 * Copyright 2010. Szabó Árpád Zoltán
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package hu.szaboaz.gwt.xslt.client.impl;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

import hu.szaboaz.gwt.xslt.client.XsltProcessingException;

/**
 * Abstraction for native implementations associated with {@link hu.szaboaz.gwt.xslt.client.XSLTProcessor}.
 * 
 * @author Szabó Árpád Zoltán, szabo.arpad.zoltan at gmail.com
 */
public abstract class XsltProcessorImpl {

    protected JavaScriptObject processor;
    protected JavaScriptObject sourceJsObject;

    public void importSource(String source) throws XsltProcessingException {
        try {
            sourceJsObject = parseImpl(source);
        } catch (JavaScriptException e) {
            throw new XsltProcessingException(e.getMessage(), e);
        }
    }

    public void importStyleSheet(String styleSheet) throws XsltProcessingException {
        try {
            importStyleSheetImpl(styleSheet);
        } catch (JavaScriptException e) {
            throw new XsltProcessingException(e.getMessage(), e);
        }
    }

    public void setParameter(String name, String value) throws XsltProcessingException {
        try {
            setParameterImpl(name, value);
        } catch (JavaScriptException e) {
            throw new XsltProcessingException(e.getMessage(), e.getCause());
        }
    }

    public String transform() throws XsltProcessingException {
        try {
            return transformImpl();
        } catch (JavaScriptException e) {
            throw new XsltProcessingException(e.getMessage(), e.getCause());
        }
    }

    protected abstract void importStyleSheetImpl(String styleSheet);

    protected abstract JavaScriptObject parseImpl(String source);

    protected abstract void setParameterImpl(String name, String value);

    protected abstract String transformImpl();
}
