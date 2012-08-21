package org.jboss.pressgangccms.client.local.ui.editor.topicview.tags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.TopicTagsPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Label;

public class TopicTagViewTagEditor implements Editor<SearchUITag>
{
	final Label name = new Label();
	private final SearchUIProjects searchUIProjects;
	
	public TopicTagViewTagEditor(final TopicTagsPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		this.searchUIProjects = searchUIProjects;
		
		name.addStyleName(CSSConstants.TOPICVIEWTAGLABEL);
	}
}
