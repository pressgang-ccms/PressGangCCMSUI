package org.jboss.pressgang.ccms.ui.client.local.ui.editor;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.view.client.ListDataProvider;

/**
 * Adapts the HasData interface to the Editor framework.
 *
 * @param <T> the type of data to be edited
 */
public class ListDataProviderEditor<T> extends ListEditor<T, LeafValueEditor<T>> {
    static class ListDataProviderEditorSource<T> extends EditorSource<LeafValueEditor<T>> {
        private final ListDataProvider<T> dataProvider;

        public ListDataProviderEditorSource(ListDataProvider<T> dataProvider) {
            this.dataProvider = dataProvider;
        }

        @Override
        public IndexedEditor<T> create(int index) {
            assert index >= 0;
            return new IndexedEditor<T>(index, dataProvider);
        }

        @Override
        public LeafValueEditor<T> createEditorForTraversal() {
            return new IndexedEditor<T>(-1, null);
        }

        @Override
        public void dispose(LeafValueEditor<T> subEditor) {
            // We use a negative index as flag in createEditorForTraversal
            assert ((IndexedEditor<T>) subEditor).index >= 0;
        }

        @Override
        public void setIndex(LeafValueEditor<T> editor, int index) {
            assert index >= 0;
            ((IndexedEditor<T>) editor).setIndex(index);
        }
    }

    private static class IndexedEditor<Q> implements LeafValueEditor<Q> {
        private int index;
        private Q value;
        private final ListDataProvider<Q> dataProvider;

        IndexedEditor(int index, ListDataProvider<Q> dataProvider) {
            this.index = index;
            this.dataProvider = dataProvider;
        }

        @Override
        public Q getValue() {
            return value;
        }

        @Override
        public void setValue(Q value) {
            this.value = value;
            push();
        }

        void setIndex(int index) {
            assert index >= 0;
            this.index = index;
            push();
        }

        private void push() {
            if (dataProvider != null && !dataProvider.getList().contains(value)) {
                dataProvider.getList().add(value);
            }
        }
    }

    private final ListDataProvider<T> dataProvider;

    /**
     * Create a ListDataProviderEditor backed by a ListDataProvider.
     *
     * @param <T> the type of data to be edited
     * @param data the ListDataProvider that is displaying the data
     * @return a instance of a ListDataProviderEditor
     */
    public static <T> ListDataProviderEditor<T> of(ListDataProvider<T> data) {
        return new ListDataProviderEditor<T>(data);
    }

    /**
     * Prevent subclassing.
     */
    private ListDataProviderEditor(ListDataProvider<T> data) {
        super(new ListDataProviderEditorSource<T>(data));

        dataProvider = data;
    }

    public void addValue(final T value) {
        getList().add(value);
        dataProvider.setList(getList());
    }

    public void removeValue(final T value) {
        getList().remove(value);
        dataProvider.setList(getList());
    }
}