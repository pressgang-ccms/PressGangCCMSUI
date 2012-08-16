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
 * This class is the Safari implementation of the XsltProcessor interface.
 * 
 * Code was copied from com.google.gwt.xml.client.impl.XMLParserImplSafari.
 * @author Szabó Árpád Zoltán, szabo.arpad.zoltan at gmail.com
 */
public class XsltProcessorImplSafari extends XsltProcessorImplStandard {
  
  @Override
  protected native JavaScriptObject parseImpl(String contents) /*-{
    var domParser = 
      this.@hu.szaboaz.gwt.xslt.client.impl.XsltProcessorImplStandard::domParser;
    var result = domParser.parseFromString(contents,"text/xml");
    var parseerrors = result.getElementsByTagName("parsererror");
    if (parseerrors.length > 0) {
      var err = parseerrors.item(0);
      if (err.parentNode.tagName == 'body') {
        throw new Error(err.childNodes[1].innerHTML);
      }
    } 
    return result;
  }-*/;
  
}
