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
package hu.szaboaz.gwt.xslt.client;

import com.google.gwt.core.client.GWT;
import hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl;

/**
 * This class represents the client interface to XSLT processing.
 * 
 * Usage: <code>
 * //Any number of processors can be created, they will behave
 * //independently. Every stylesheet have to have its own processor.
 * XsltProcessor processor = new XsltProcessor();
 * 
 * //Setting the stylesheet to transform with
 * processor.importStyleSheet(styleSheetText);
 * 
 * //Setting the document to be transformed
 * processor.importSource(sourceText);
 * 
 * //Order of setting the stylesheet and document is indifferent.
 * 
 * //Optional, must be called after importStyleSheet
 * processor.setParameter(paramNameString, paramValueString);
 * 
 * //Getting the result
 * String resultString = processor.transform();
 * DOM.getElementById("resultContainer").setInnerHTML(resultString);
 * </code>
 * 
 * The importSource-setParameter-transform cycle can be repeated multiple times.
 * 
 * @author Szabó Árpád Zoltán, szabo.arpad.zoltan at gmail.com
 */
public final class XsltProcessor {

    private XsltProcessorImpl impl;

    public XsltProcessor() {
        impl = GWT.create(XsltProcessorImpl.class);
    }

    public void importSource(final String source) throws XsltProcessingException {
        impl.importSource(source);
    }

    public void importStyleSheet(final String styleSheet) throws XsltProcessingException {
        impl.importStyleSheet(styleSheet);
    }

    public void setParameter(final String name, final String value) throws XsltProcessingException {
        impl.setParameter(name, value);
    }

    public String transform() throws XsltProcessingException {
        return impl.transform();
    }
}
