package org.jboss.pressgangccms.client.local.ui.editor;
import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FourTextAndImageButtonSearchUIProjectEditor;
import com.google.gwt.user.client.ui.Grid;

public class SearchUIProjectEditor extends Grid implements ValueAwareEditor<SearchUIProject>
{
	private final SearchPresenterDriver driver;
	private final SearchUIProjects searchUIProjects;
	private SearchUIProject value;
	
	final FourTextAndImageButtonSearchUIProjectEditor summary = new FourTextAndImageButtonSearchUIProjectEditor();
	final ListEditor<SearchUICategory, SearchUICategoryEditor> categories = ListEditor.of(new SearchUICategoryEditorSource());
	private final FlexTable categoriesButtonPanel = new FlexTable();
	
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
			final SearchUICategoryEditor subEditor = new SearchUICategoryEditor(driver, searchUIProjects);
			
			SearchUIProjectEditor.this.categoriesButtonPanel.setWidget(index, 0, subEditor.summary);
			
			subEditor.summary.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(final ClickEvent event)
				{
					SearchUIProjectEditor.this.setWidget(0, 1, subEditor);
					
					/* Untoggle the other buttons */
					for (final SearchUICategoryEditor editor : categories.getEditors())
					{
						if (editor.summary != subEditor.summary)
						{
							editor.summary.removeStyleName(CSSConstants.CUSTOMBUTTONDOWN);
							editor.summary.removeStyleName(CSSConstants.CUSTOMBUTTON);
							
							editor.summary.addStyleName(CSSConstants.CUSTOMBUTTON);
						}
					}
				}
			});
			
			return subEditor;
		}

		@Override
		public void dispose(final SearchUICategoryEditor subEditor)
		{
			subEditor.summary.removeFromParent();
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(final SearchUICategoryEditor subEditor, final int index)
		{
			SearchUIProjectEditor.this.categoriesButtonPanel.setWidget(index, 0, subEditor);
		}
	}

	public SearchUIProjectEditor(final SearchPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		super(1, 2);
		
		this.driver = driver;
		this.searchUIProjects = searchUIProjects;
		
		this.addStyleName(CSSConstants.PROJECTSLAYOUT);
		summary.addStyleName(CSSConstants.CUSTOMBUTTON);
		
		categoriesButtonPanel.addStyleName(CSSConstants.CATEGORIESBUTTONSLAYOUT);
		this.setWidget(0,  0, categoriesButtonPanel);

		summary.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				summary.removeStyleName(CSSConstants.CUSTOMBUTTON);
				summary.addStyleName(CSSConstants.CUSTOMBUTTONDOWN);
			}
		});
	}

	@Override
	public void setDelegate(EditorDelegate<SearchUIProject> delegate)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush()
	{
		this.summary.asEditor().setValue(value.getSummary());		
	}

	@Override
	public void onPropertyChange(String... paths)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(final SearchUIProject value)
	{
		this.value = value;	
	}
}
