package org.jboss.pressgangccms.client.local.ui.editor.topicview.tags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.TopicTagsPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public class TopicTagViewCategoryEditor extends Grid implements Editor<SearchUICategory>
{
	private final SearchUIProjects searchUIProjects;
	private final TopicTagsPresenterDriver driver;
	private final FlexTable tagsTable = new FlexTable();
	final Label name = new Label();
	final ListEditor<SearchUITag, TopicTagViewTagEditor> myTags = ListEditor.of(new TopicTagViewTagEditorSource());
	
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into
	 * a ListEditor
	 * 
	 * @author Matthew Casperson
	 */
	private class TopicTagViewTagEditorSource extends EditorSource<TopicTagViewTagEditor>
	{
		@Override
		public TopicTagViewTagEditor create(final int index)
		{
			final TopicTagViewTagEditor subEditor = new TopicTagViewTagEditor(driver, searchUIProjects);
			tagsTable.setWidget(index, 0, subEditor.name);
			tagsTable.getCellFormatter().addStyleName(index, 0, CSSConstants.TOPICTAGVIEWTAGROW);
			return subEditor;
		}

		@Override
		public void dispose(final TopicTagViewTagEditor subEditor)
		{
			subEditor.name.removeFromParent();
		}

		@Override
		public void setIndex(final TopicTagViewTagEditor subEditor, final int index)
		{
			tagsTable.setWidget(index, 0, subEditor.name);
		}
	}
	
	public TopicTagViewCategoryEditor(final TopicTagsPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		super(1, 2);
		
		this.addStyleName(CSSConstants.TOPICTAGVIEWCATEGORYTABLE);
		
		this.setWidget(0, 0, name);
		this.setWidget(0, 1, tagsTable);
		
		this.getCellFormatter().addStyleName(0, 0, CSSConstants.TOPICTAGVIEWCATEGORYROW);
		tagsTable.addStyleName(CSSConstants.TOPICTAGVIEWTAGSTABLE);
		
		this.driver = driver;
		this.searchUIProjects = searchUIProjects;
	}
}