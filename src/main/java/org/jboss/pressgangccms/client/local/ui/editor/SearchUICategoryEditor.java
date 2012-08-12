package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.Driver;
import org.jboss.pressgangccms.client.local.ui.TextAndImageButton;
import org.jboss.pressgangccms.client.local.ui.TextAndImageButtonStringEditor;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
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
	private final Driver driver;
	private final SearchUIProjects searchUIProjects;
	
	final TextAndImageButtonStringEditor name = new TextAndImageButtonStringEditor();
	final ListEditor<SearchUITag, SearchUITagEditor> myTags = ListEditor.of(new SearchUITagEditorSource());
	
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
			final int fixedIndex = index / COLUMNS;
			final int column = index % COLUMNS;
			
			final SearchUITagEditor subEditor = new SearchUITagEditor(driver, searchUIProjects);
			SearchUICategoryEditor.this.setWidget(fixedIndex, column * 2, subEditor.name);
			SearchUICategoryEditor.this.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
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
			final int fixedIndex = index / COLUMNS;
			final int column = index % COLUMNS;
			
			SearchUICategoryEditor.this.setWidget(fixedIndex, column * 2, subEditor.name);
			SearchUICategoryEditor.this.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
		}
	}

	public SearchUICategoryEditor(final Driver driver, final SearchUIProjects searchUIProjects)
	{
		this.driver = driver;
		this.searchUIProjects = searchUIProjects;
		
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
