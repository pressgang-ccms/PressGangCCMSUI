package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.TextAndImageButton;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.FlexTable;

public class SearchUIProjectEditor extends FlexTable implements Editor<SearchUIProject>
{
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into
	 * a ListEditor
	 * 
	 * @author Matthew Casperson
	 */
	private class SearchUICategoryEditorSource extends EditorSource<SearchUICategoryEditor>
	{
		@Override
		public SearchUICategoryEditor create(final int index)
		{
			final int row = index / COLUMNS;
			final int column = index % COLUMNS;
			
			final SearchUICategoryEditor subEditor = new SearchUICategoryEditor();
			SearchUIProjectEditor.this.setWidget(row, column, subEditor);
			return subEditor;
		}

		@Override
		public void dispose(final SearchUICategoryEditor subEditor)
		{
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(final SearchUICategoryEditor subEditor, final int index)
		{
			final int row = index / COLUMNS;
			final int column = index % COLUMNS;
			SearchUIProjectEditor.this.setWidget(row, column, subEditor);
		}
	}

	private static final int COLUMNS = 6;

	TextAndImageButton name = new TextAndImageButton();
	ListEditor<SearchUICategory, SearchUICategoryEditor> categories = ListEditor.of(new SearchUICategoryEditorSource());

	public SearchUIProjectEditor()
	{
		this.addStyleName("ProjectLayout");
	}
}
