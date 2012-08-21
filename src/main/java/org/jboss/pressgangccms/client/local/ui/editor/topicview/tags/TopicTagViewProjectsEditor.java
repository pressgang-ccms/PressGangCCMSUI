package org.jboss.pressgangccms.client.local.ui.editor.topicview.tags;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.TopicTagsPresenter.TopicTagsPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;

public class TopicTagViewProjectsEditor extends SimplePanel implements Editor<SearchUIProjects>
{
	private final SearchUIProjects searchUIProjects;
	private final TopicTagsPresenterDriver driver;
	private final FlexTable projectLabelPanel = new FlexTable();
	final ListEditor<SearchUIProject, TopicTagViewProjectEditor> projects = ListEditor.of(new TopicTagViewProjectEditorSource());
	
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into
	 * a ListEditor
	 * 
	 * @author Matthew Casperson
	 */
	private class TopicTagViewProjectEditorSource extends EditorSource<TopicTagViewProjectEditor>
	{
		@Override
		public TopicTagViewProjectEditor create(final int index)
		{
			final TopicTagViewProjectEditor subEditor = new TopicTagViewProjectEditor(driver, searchUIProjects);
			projectLabelPanel.setWidget(index, 0, subEditor);
			
			return subEditor;
		}

		@Override
		public void dispose(final TopicTagViewProjectEditor subEditor)
		{
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(final TopicTagViewProjectEditor subEditor, final int index)
		{
			projectLabelPanel.setWidget(index, 0, subEditor);
		}
	}
	
	public TopicTagViewProjectsEditor(final TopicTagsPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		projectLabelPanel.addStyleName(CSSConstants.TOPICTAGVIEWPROJECTSTABLE);
		this.setWidget(projectLabelPanel);
		
		this.searchUIProjects = searchUIProjects;
		this.driver = driver;
	}
}
