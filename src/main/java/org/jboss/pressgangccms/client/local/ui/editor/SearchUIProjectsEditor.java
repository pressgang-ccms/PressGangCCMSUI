package org.jboss.pressgangccms.client.local.ui.editor;

import org.jboss.pressgangccms.client.local.presenter.SearchPresenter.Display.Driver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProject;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;

public class SearchUIProjectsEditor extends Grid implements Editor<SearchUIProjects>
{
	private final Driver driver;
	final SearchUIProjects searchUIProjects;
	
	/**
	 * The EditorSource is used to create and orgainse the Editors that go into
	 * a ListEditor
	 * 
	 * @author Matthew Casperson
	 */
	private class SearchUIProjectEditorSource extends EditorSource<SearchUIProjectEditor>
	{
		@Override
		public SearchUIProjectEditor create(final int index)
		{
			final SearchUIProjectEditor subEditor = new SearchUIProjectEditor(driver, searchUIProjects);
			projectButtonPanel.setWidget(index, 0, subEditor.summary);

			subEditor.summary.addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(final ClickEvent event)
				{
					SearchUIProjectsEditor.this.setWidget(0, 1, subEditor);
					
					/* Untoggle the other buttons */
					for (final SearchUIProjectEditor projectEditor : projects.getEditors())
					{
						if (projectEditor.summary != subEditor.summary)
						{
							projectEditor.summary.removeStyleName("CustomButtonDown");
							projectEditor.summary.removeStyleName("CustomButton");
							
							projectEditor.summary.addStyleName("CustomButton");
						}
					}
				}
			});

			return subEditor;
		}

		@Override
		public void dispose(final SearchUIProjectEditor subEditor)
		{
			subEditor.summary.removeFromParent();
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(final SearchUIProjectEditor subEditor, final int index)
		{
			projectButtonPanel.setWidget(index, 0, subEditor);
		}
	}

	final ListEditor<SearchUIProject, SearchUIProjectEditor> projects = ListEditor.of(new SearchUIProjectEditorSource());
	private final FlexTable projectButtonPanel = new FlexTable();

	public SearchUIProjectsEditor(final Driver driver, final SearchUIProjects searchUIProjects)
	{
		super(1, 2);
		
		this.driver = driver;
		this.searchUIProjects = searchUIProjects;
		
		this.addStyleName("ProjectsLayout");
		projectButtonPanel.addStyleName("ProjectsButtonsLayout");
		
		this.setWidget(0, 0, projectButtonPanel);
	}

}
