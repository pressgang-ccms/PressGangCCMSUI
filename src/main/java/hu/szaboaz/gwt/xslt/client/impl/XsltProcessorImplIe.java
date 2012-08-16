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
 * This class is the IE implementation of the XsltProcessor interface.
 * 
 * XML parsing code was copied from 
 * com.google.gwt.xml.client.impl.XMLParserImplIE6
 * @author Szabó Árpád Zoltán, szabo.arpad.zoltan at gmail.com
 */
public class XsltProcessorImplIe extends XsltProcessorImpl {
  
  protected native JavaScriptObject createDocumentImpl() /*-{
    var doc = @hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImplIe::selectDOMDocumentVersion()();
    // preserveWhiteSpace is set to true here to prevent IE from throwing away
    // text nodes that consist of only whitespace characters. This makes it
    // act more like other browsers.
    doc.preserveWhiteSpace = true;
    doc.setProperty("SelectionNamespaces", "xmlns:xsl='http://www.w3.org/1999/XSL/Transform'");
    doc.setProperty("SelectionLanguage", "XPath");
    return doc;
  }-*/;
  
  @Override
  protected native void importStyleSheetImpl(String styleSheet) /*-{
    var styleSheetDocument = new ActiveXObject("MSXML2.FreeThreadedDOMDocument");
    styleSheetDocument.async = "false";
    styleSheetDocument.loadXML(styleSheet);
  
    var styleSheetTemplate = new ActiveXObject("MSXML2.XSLTemplate");
    styleSheetTemplate.stylesheet = styleSheetDocument;
    // processor must be created *after* stylesheet is loaded into styleSheetTemplate
    // (so it cannot be created in initialize())
    // and *before* parameters added (of course, since parameters are added to the processor itself)
    // (so it cannot be created in transform() which wouldn't be good anyways 
    // since the repeated calls would make new objects all the time)
    this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor = 
        styleSheetTemplate.createProcessor();
  }-*/;
  
  @Override
  protected native JavaScriptObject parseImpl(String contents) /*-{
    var doc = this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImplIe::createDocumentImpl()();
    if(!doc.loadXML(contents)) {
      var err = doc.parseError;
      throw new Error("line " + err.line + ", char " + err.linepos + ":" + err.reason);
    } else {
      return doc;
    }
  }-*/;
  
  protected static native JavaScriptObject selectDOMDocumentVersion() /*-{
    try { return new ActiveXObject("Msxml2.DOMDocument"); } catch (e) { }
    try { return new ActiveXObject("MSXML.DOMDocument"); } catch (e) { }
    try { return new ActiveXObject("MSXML3.DOMDocument"); } catch (e) { }
    try { return new ActiveXObject("Microsoft.XmlDom"); } catch (e) { }
    try { return new ActiveXObject("Microsoft.DOMDocument"); } catch (e) { }
  
    throw new Error("Could not find appropriate version of DOMDocument.");
  }-*/;
  
  @Override
  protected native void setParameterImpl(String name, String value) /*-{
    if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor != null) {
      this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.addParameter(name, value, "");
    }
    else {
      throw new Error("XsltProcessor hasn't been initialized yet. Stylesheet has to be imported before setting parameters.");
    }
  }-*/;
  
  @Override
  protected native String transformImpl() /*-{
    if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor != null) {
      if (this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::sourceJsObject != null) {
        this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.input = 
          this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::sourceJsObject;
      }
      else {
        throw new Error("Source document has to be imported before initiating transformation.");
      }
      
      this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.transform();
      return this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImpl::processor.output;
    }
    else {
      throw new Error("XsltProcessor hasn't been initialized yet. Stylesheet has to be imported before initiating transformation.");
    }
  }-*/;
}