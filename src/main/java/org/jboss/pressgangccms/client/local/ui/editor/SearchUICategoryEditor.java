package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class SearchUICategoryEditor extends DockPanel implements Editor<SearchUICategory>
{
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into
	 * a ListEditor
	 * 
	 * @author Matthew Casperson
	 */
	private class SearchUITagEditorSource extends EditorSource<SearchUITagEditor>
	{
		@Override
		public SearchUITagEditor create(final int index)
		{
			final SearchUITagEditor subEditor = new SearchUITagEditor();
			SearchUICategoryEditor.this.table.setWidget(index, 0, subEditor.name);
			SearchUICategoryEditor.this.table.setWidget(index, 1, subEditor.state);
			return subEditor;
		}

		@Override
		public void dispose(final SearchUITagEditor subEditor)
		{
			subEditor.name.removeFromParent();
			subEditor.state.removeFromParent();
		}

		@Override
		public void setIndex(final SearchUITagEditor subEditor, final int index)
		{
			SearchUICategoryEditor.this.table.setWidget(index, 0, subEditor.name);
			SearchUICategoryEditor.this.table.setWidget(index, 1, subEditor.state);
		}
	}
	
	Label name = new Label();
	ListEditor<SearchUITag, SearchUITagEditor> myTags = ListEditor.of(new SearchUITagEditorSource());
	final FlexTable table = new FlexTable();
	
	public SearchUICategoryEditor()
	{
		this.add(name, DockPanel.NORTH);
		this.add(table, DockPanel.CENTER);
		
		this.addStyleName("CategoryLayout");
		this.name.addStyleName("CategoryTitle");
		table.addStyleName("CategoryTagLayout");
	}
}
