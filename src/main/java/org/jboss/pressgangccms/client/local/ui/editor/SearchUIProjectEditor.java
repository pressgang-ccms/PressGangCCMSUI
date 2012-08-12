package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.ui.TextAndImageButton;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;

public class SearchUIProjectEditor extends Grid implements Editor<SearchUIProject>
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
			final SearchUICategoryEditor subEditor = new SearchUICategoryEditor();
			
			SearchUIProjectEditor.this.categoriesButtonPanel.setWidget(index, 0, subEditor.name);
			
			subEditor.name.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(final ClickEvent event)
				{
					SearchUIProjectEditor.this.setWidget(0, 1, subEditor);
					
					/* Untoggle the other buttons */
					for (final SearchUICategoryEditor editor : categories.getEditors())
					{
						if (editor.name != subEditor.name)
						{
							editor.name.removeStyleName("CustomButtonDown");
							editor.name.removeStyleName("CustomButton");
							
							editor.name.addStyleName("CustomButton");
						}
					}
				}
			});
			
			return subEditor;
		}

		@Override
		public void dispose(final SearchUICategoryEditor subEditor)
		{
			subEditor.name.removeFromParent();
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(final SearchUICategoryEditor subEditor, final int index)
		{
			SearchUIProjectEditor.this.categoriesButtonPanel.setWidget(index, 0, subEditor);
		}
	}

	TextAndImageButton name = new TextAndImageButton();
	ListEditor<SearchUICategory, SearchUICategoryEditor> categories = ListEditor.of(new SearchUICategoryEditorSource());
	private final FlexTable categoriesButtonPanel = new FlexTable();

	public SearchUIProjectEditor()
	{
		super(1, 2);
		
		this.addStyleName("ProjectLayout");
		name.addStyleName("CustomButton");
		
		categoriesButtonPanel.addStyleName("CategoriesButtonsLayout");
		this.setWidget(0,  0, categoriesButtonPanel);

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
