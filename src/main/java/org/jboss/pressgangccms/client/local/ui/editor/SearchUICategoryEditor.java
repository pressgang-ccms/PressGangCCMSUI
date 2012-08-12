package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.TextAndImageButton;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;


public class SearchUICategoryEditor extends FlexTable implements Editor<SearchUICategory>
{
	private static final int COLUMNS = 2;
	TextAndImageButton name = new TextAndImageButton();
	ListEditor<SearchUITag, SearchUITagEditor> myTags = ListEditor.of(new SearchUITagEditorSource());
	
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
			final int column = COLUMNS / index;
			final int fixedIndex = index % COLUMNS;
			
			final SearchUITagEditor subEditor = new SearchUITagEditor();
			SearchUICategoryEditor.this.setWidget(fixedIndex, column, subEditor.name);
			SearchUICategoryEditor.this.setWidget(fixedIndex, column + 1, subEditor.state);
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
			final int column = COLUMNS / index;
			final int fixedIndex = index % COLUMNS;
			
			SearchUICategoryEditor.this.setWidget(fixedIndex, column, subEditor.name);
			SearchUICategoryEditor.this.setWidget(fixedIndex, column + 1, subEditor.state);
		}
	}

	
	public SearchUICategoryEditor()
	{
		this.name.addStyleName("CustomButton");
		this.addStyleName("CategoryTagLayout");
		
		name.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				name.removeStyleName("CustomButton");
				name.addStyleName("CustomButtonDown");
			}
		});
	}
}
