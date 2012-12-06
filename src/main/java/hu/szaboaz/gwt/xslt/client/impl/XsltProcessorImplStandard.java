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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This class is the Standard (other than IE) implementation of the XsltProcessor interface.
 * 
 * XML parsing code was copied from com.google.gwt.xml.client.impl.XMLParserImplStandard
 * 
 * @author Szabó Árpád Zoltán, szabo.arpad.zoltan at gmail.com
 */
public class XsltProcessorImplStandard extends XsltProcessorImpl {

    protected static native JavaScriptObject createDOMParser() /*-{
                                                               return new DOMParser();
                                                               }-*/;

    protected final JavaScriptObject domParser = createDOMParser();

    @Override
    protected void importStyleSheetImpl(final String styleSheet) {
        importStyleSheetImpl(parseImpl(styleSheet));
    }

    protected native void importStyleSheetImpl(final JavaScriptObject styleSheet) /*-{
                                                                            this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor = new $wnd.XSLTProcessor();
                                                                            this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.importStylesheet(styleSheet);
                                                                            }-*/;

    @Override
    protected native JavaScriptObject parseImpl(final String contents) /*-{
                                                                 var domParser = this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImplStandard::domParser;
                                                                 var result = domParser.parseFromString(contents,"text/xml");
                                                                 var roottag = result.documentElement;
                                                                 if ((roottag.tagName == "parsererror") && 
                                                                 (roottag.namespaceURI ==
                                                                 "http://www.mozilla.org/newlayout/xml/parsererror.xml")) {
                                                                 throw new Error(roottag.firstChild.data);
                                                                 }
                                                                 return result;
                                                                 }-*/;

    @Override
    protected native void setParameterImpl(final String name, final String value) /*-{
                                                                      if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor != null) {
                                                                      this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.setParameter(null, name, value);
                                                                      }
                                                                      else {
                                                                      throw new Error("XsltProcessor hasn't been initialized yet. Stylesheet has to be imported before setting parameters.");
                                                                      }
                                                                      }-*/;

    @Override
    protected native String transformImpl() /*-{
                                            if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor != null) {
                                            if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::sourceJsObject != null) {
                                            var newFragment = this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.transformToFragment(
                                            this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::sourceJsObject, document);
                                            var serializer = new $wnd.XMLSerializer();
                                            return serializer.serializeToString(newFragment);
                                            }
                                            else {
                                            throw new Error("Source document has to be imported before initiating transformation.");
                                            }
                                            }
                                            else {
                                            throw new Error("XsltProcessor hasn't been initialized yet. Stylesheet has to be imported before initiating transformation.");
                                            }
                                            }-*/;
}
