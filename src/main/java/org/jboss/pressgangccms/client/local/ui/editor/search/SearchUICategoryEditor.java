package org.jboss.pressgangccms.client.local.ui.editor.search;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.mvp.presenter.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgangccms.client.local.ui.search.SearchUICategory;
import org.jboss.pressgangccms.client.local.ui.search.SearchUIProjects;
import org.jboss.pressgangccms.client.local.ui.search.SearchUITag;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FourTextAndImageButtonSearchUICategoryEditor;


public class SearchUICategoryEditor extends FlexTable implements ValueAwareEditor<SearchUICategory>
{
	private static final int COLUMNS = 2;
	private final SearchPresenterDriver driver;
	private final SearchUIProjects searchUIProjects;
	private SearchUICategory value;
	
	final FourTextAndImageButtonSearchUICategoryEditor summary = new FourTextAndImageButtonSearchUICategoryEditor();
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

	public SearchUICategoryEditor(final SearchPresenterDriver driver, final SearchUIProjects searchUIProjects)
	{
		this.driver = driver;
		this.searchUIProjects = searchUIProjects;
		
		this.summary.addStyleName(CSSConstants.CUSTOMBUTTON);
		this.addStyleName(CSSConstants.CATEGORYTAGLAYOUT);
		
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
	public void setDelegate(final EditorDelegate<SearchUICategory> delegate)
	{

	}

	@Override
	public void flush()
	{
		this.summary.asEditor().setValue(value.getSummary());
	}

	@Override
	public void onPropertyChange(final String... paths)
	{

	}

	@Override
	public void setValue(final SearchUICategory value)
	{
		this.value = value;
	}
}
