package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FourTextAndImageButtonSearchUICategoryEditor;
import com.google.gwt.user.client.ui.ScrollPanel;

public class SearchUICategoryEditor extends ScrollPanel implements ValueAwareEditor<SearchUICategory> {
    private static final int COLUMNS = 2;
    private final SearchPresenterDriver driver;
    private final SearchUIProjects searchUIProjects;
    private SearchUICategory value;
    private final FlexTable tagsTable = new FlexTable();

    final FourTextAndImageButtonSearchUICategoryEditor summary = new FourTextAndImageButtonSearchUICategoryEditor();
    final ListEditor<SearchUITag, SearchUITagEditor> myTags = ListEditor.of(new SearchUITagEditorSource());

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     * 
     * @author Matthew Casperson
     */
    private class SearchUITagEditorSource extends EditorSource<SearchUITagEditor> {
        @Override
        public SearchUITagEditor create(final int index) {
            final int fixedIndex = index / COLUMNS;
            final int column = index % COLUMNS;

            final SearchUITagEditor subEditor = new SearchUITagEditor(driver, searchUIProjects);
            tagsTable.setWidget(fixedIndex, column * 2, subEditor.name);
            tagsTable.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
            return subEditor;
        }

        @Override
        public void dispose(final SearchUITagEditor subEditor) {
            subEditor.name.removeFromParent();
            subEditor.state.removeFromParent();
        }

        @Override
        public void setIndex(final SearchUITagEditor subEditor, final int index) {
            final int fixedIndex = index / COLUMNS;
            final int column = index % COLUMNS;

            tagsTable.setWidget(fixedIndex, column * 2, subEditor.name);
            tagsTable.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
        }
    }

    public SearchUICategoryEditor(final SearchPresenterDriver driver, final SearchUIProjects searchUIProjects) {
        this.driver = driver;
        this.searchUIProjects = searchUIProjects;

        this.summary.addStyleName(CSSConstants.CUSTOM_BUTTON);
        tagsTable.addStyleName(CSSConstants.CATEGORY_TAG_LAYOUT);
        this.addStyleName(CSSConstants.CATEGORY_TAG_SCROLL);

        this.setWidget(tagsTable);

        summary.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                summary.removeStyleName(CSSConstants.CUSTOM_BUTTON);
                summary.addStyleName(CSSConstants.CUSTOM_BUTTON_DOWN);
            }
        });
    }

    @Override
    public void setDelegate(final EditorDelegate<SearchUICategory> delegate) {

    }

    @Override
    public void flush() {
        this.summary.asEditor().setValue(value.getSummary());
    }

    @Override
    public void onPropertyChange(final String... paths) {

    }

    @Override
    public void setValue(final SearchUICategory value) {
        this.value = value;
    }
}
