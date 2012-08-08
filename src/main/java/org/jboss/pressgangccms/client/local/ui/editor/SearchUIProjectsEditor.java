package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;

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
			projectButtonPanel.insert(subEditor.name, index + 1);
			return subEditor;
		}

		@Override
		public void dispose(final SearchUIProjectEditor subEditor)
		{
			subEditor.removeFromParent();
			projectButtonPanel.remove(subEditor.name);
		}

		@Override
		public void setIndex(final SearchUIProjectEditor editor, final int index)
		{
			projectButtonPanel.insert(editor.name, index + 1);
		}
	}

	ListEditor<SearchUIProject, SearchUIProjectEditor> projects = ListEditor.of(new SearchUIProjectEditorSource());
	private final VerticalPanel projectButtonPanel = new VerticalPanel();

	public SearchUIProjectsEditor()
	{
		super(1, 2);
		this.setWidget(0, 0, projectButtonPanel);
	}

}
