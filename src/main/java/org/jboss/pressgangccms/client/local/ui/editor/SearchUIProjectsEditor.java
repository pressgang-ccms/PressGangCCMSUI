package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;

public class SearchUIProjectsEditor extends Grid implements Editor<SearchUIProjects>
{
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
	 * @author Matthew Casperson
	 */
	private class SearchUIProjectEditorSource extends EditorSource<SearchUIProjectEditor>
	{
		@Override
		public SearchUIProjectEditor create(final int index)
		{
			final SearchUIProjectEditor subEditor = new SearchUIProjectEditor();
			projectButtonPanel.setWidget(index, 0, subEditor);
			return subEditor;
		}

		@Override
		public void dispose(final SearchUIProjectEditor subEditor)
		{
			subEditor.removeFromParent();
			projectButtonPanel.remove(subEditor.name);
		}

		@Override
		public void setIndex(final SearchUIProjectEditor subEditor, final int index)
		{
			projectButtonPanel.setWidget(index, 0, subEditor);
		}
	}

	ListEditor<SearchUIProject, SearchUIProjectEditor> projects = ListEditor.of(new SearchUIProjectEditorSource());
	private final FlexTable projectButtonPanel = new FlexTable();

	public SearchUIProjectsEditor()
	{
		super(1, 2);
		this.setWidget(0, 0, projectButtonPanel);
	}

}
