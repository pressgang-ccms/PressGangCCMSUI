package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.TriStatePushButton;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Label;

/**
 * A GWT Editor to provide a visual representation of SearchUITag 
 * @author Matthew Casperson
 */
public class SearchUITagEditor implements Editor<SearchUITag>
{
	Label name = new Label();
	TriStatePushButton state = new TriStatePushButton();
}
