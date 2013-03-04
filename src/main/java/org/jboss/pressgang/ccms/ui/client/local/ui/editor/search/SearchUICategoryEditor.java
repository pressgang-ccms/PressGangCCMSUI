package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkState;

public final class SearchUICategoryEditor extends ScrollPanel implements ValueAwareEditor<SearchUICategory> {
    private static final int COLUMNS = 2;
    private static final int TAG_CELLS_PER_COLUMN = 3;
    private static final int LOGIC_AND_HEADING_ROW_COUNT = 3;
    private static final String INTERNAL_LOGIC_RADIOBUTTON_GROUP = "InternalLogic";
    private static final String EXTERNAL_LOGIC_RADIOBUTTON_GROUP = "ExternalLogic";

    @NotNull
    private final SearchPresenterDriver driver;
    @NotNull
    private final SearchUIProjectEditor searchUIProject;
    private SearchUICategory value;
    private final boolean showBulkTags;
    private final FlexTable tagsTable = new FlexTable();
    private final RadioButton internalLogicAnd = new RadioButton("", PressGangCCMSUI.INSTANCE.And());
    private final RadioButton internalLogicOr  = new RadioButton("", PressGangCCMSUI.INSTANCE.Or());
    private final RadioButton externalLogicAnd  = new RadioButton("", PressGangCCMSUI.INSTANCE.And());
    private final RadioButton externalLogicOr = new RadioButton("", PressGangCCMSUI.INSTANCE.Or());

    final FourTextAndImageButtonSearchUICategoryEditor summary = new FourTextAndImageButtonSearchUICategoryEditor();
    private final ListEditor<SearchUITag, SearchUITagEditor> myTags = ListEditor.of(new SearchUITagEditorSource());

    public ListEditor<SearchUITag, SearchUITagEditor> myTagsEditor() {
        return myTags;
    }

    /**
     * The EditorSource is used to create and orgainse the Editors that go into a ListEditor
     *
     * @author Matthew Casperson
     */
    private class SearchUITagEditorSource extends EditorSource<SearchUITagEditor> {
        @NotNull
        @Override
        public SearchUITagEditor create(final int index) {
            final int fixedIndex = (index / COLUMNS) + LOGIC_AND_HEADING_ROW_COUNT; // add 3 because the first rows are taken up by the category logic controls
            final int column = index % COLUMNS;

            @NotNull final SearchUITagEditor subEditor = new SearchUITagEditor(driver, SearchUICategoryEditor.this);
            tagsTable.setWidget(fixedIndex, column * TAG_CELLS_PER_COLUMN, subEditor.nameEditor());
            tagsTable.setWidget(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 1, subEditor.stateEditor());
            tagsTable.setWidget(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 2, subEditor.bulkTagStateEditor());
            subEditor.bulkTagStateEditor().setVisible(showBulkTags);

            /* Style the cells holding the various tag elements */
            tagsTable.getCellFormatter().addStyleName(fixedIndex, column * TAG_CELLS_PER_COLUMN, CSSConstants.SearchView.TAG_NAME);
            tagsTable.getCellFormatter().addStyleName(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 1, CSSConstants.SearchView.SEARCH_STATE);
            tagsTable.getCellFormatter().addStyleName(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 2, CSSConstants.SearchView.BULK_TAG_STATE);

            subEditor.bulkTagStateEditor().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(@NotNull final ClickEvent event) {
                    /*
                        If this tag is in a mutually exclusive category, then unselect any other selected tags.
                    */
                    if (value.getMutuallyExclusiveCategory() && subEditor.bulkTagStateEditor().getState() == TriStateSelectionState.SELECTED) {
                        for (final SearchUITagEditor tag : myTagsEditor().getEditors()) {
                            if (tag != subEditor && tag.bulkTagStateEditor().getState() == TriStateSelectionState.SELECTED) {
                                tag.bulkTagStateEditor().setState(TriStateSelectionState.NONE);
                            }
                        }
                        driver.flush();
                    }
                }
            });

            return subEditor;
        }

        @Override
        public void dispose(@NotNull final SearchUITagEditor subEditor) {
            subEditor.nameEditor().removeFromParent();
            subEditor.stateEditor().removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final SearchUITagEditor subEditor, final int index) {
            final int fixedIndex = (index / COLUMNS) + LOGIC_AND_HEADING_ROW_COUNT; // add 3 because the first rows are taken up by the category logic controls
            final int column = index % COLUMNS;

            tagsTable.setWidget(fixedIndex, column * TAG_CELLS_PER_COLUMN, subEditor.nameEditor());
            tagsTable.setWidget(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 1, subEditor.stateEditor());
            tagsTable.setWidget(fixedIndex, (column * TAG_CELLS_PER_COLUMN) + 2, subEditor.bulkTagStateEditor());
        }
    }

    public SearchUICategoryEditor(@NotNull final SearchPresenterDriver driver, @NotNull final SearchUIProjectEditor searchUIProject, final boolean showBulkTags) {
        this.driver = driver;
        this.searchUIProject = searchUIProject;
        this.showBulkTags = showBulkTags;

        this.summary.addStyleName(CSSConstants.Common.CUSTOM_BUTTON);
        tagsTable.addStyleName(CSSConstants.TagListCategoryView.CATEGORY_TAG_LAYOUT);
        this.addStyleName(CSSConstants.TagListCategoryView.CATEGORY_TAG_SCROLL);

        this.setWidget(tagsTable);

         /*
            Build up the internal and external category logic ui elements. We do this here because
            we need to know what category this editor is working with in order to group the
            radio buttons.
         */

        checkState(COLUMNS >= 2, "The layout of the category logic ui elements assumes that there are at least 2 columns.");

        // setup the column spans
        tagsTable.getFlexCellFormatter().setColSpan(0, 0, TAG_CELLS_PER_COLUMN);
        /*
            Using column 1 here isn't what you might expect, but it is the next column after column 0. The fact
            that column 0 spans multiple columns doesn't affect how we refer to the next column; the next column
            is referenced by an incremented column count without any consideration to the previous column span.
         */
        tagsTable.getFlexCellFormatter().setColSpan(0, 1, TAG_CELLS_PER_COLUMN);
        tagsTable.getFlexCellFormatter().setColSpan(1, 0, TAG_CELLS_PER_COLUMN);
        tagsTable.getFlexCellFormatter().setColSpan(1, 1, TAG_CELLS_PER_COLUMN);

        // Internal logic

        @NotNull final Label internalLogicLabel = new Label(PressGangCCMSUI.INSTANCE.InternalLogic());
        tagsTable.setWidget(0, 0, internalLogicLabel);
        tagsTable.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.SearchView.LOGIC_HEADER_CELL);

        @NotNull final HorizontalPanel internalLogicPanel = new HorizontalPanel();
        internalLogicPanel.addStyleName(CSSConstants.SearchView.LOGIC_DETAILS_TABLE);
        internalLogicPanel.add(internalLogicAnd);
        internalLogicPanel.add(internalLogicOr);

        tagsTable.setWidget(1, 0, internalLogicPanel);
        tagsTable.getFlexCellFormatter().addStyleName(1, 0, CSSConstants.SearchView.LOGIC_DETAILS_CELL);

        // External logic

        @NotNull final Label externalLogicLabel = new Label(PressGangCCMSUI.INSTANCE.ExternalLogic());
        tagsTable.setWidget(0, 1, externalLogicLabel);
        tagsTable.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.SearchView.LOGIC_HEADER_CELL);


        @NotNull final HorizontalPanel externalLogicPanel = new HorizontalPanel();
        externalLogicPanel.addStyleName(CSSConstants.SearchView.LOGIC_DETAILS_TABLE);
        externalLogicPanel.add(externalLogicAnd);
        externalLogicPanel.add(externalLogicOr);

        tagsTable.setWidget(1, 1, externalLogicPanel);
        tagsTable.getFlexCellFormatter().addStyleName(1, 1, CSSConstants.SearchView.LOGIC_DETAILS_CELL);

        // tags heading cell
        for (int i = 0; i < COLUMNS; ++i) {
            @NotNull final Label tags = new Label(PressGangCCMSUI.INSTANCE.TagName());
            @NotNull final Label tagSearchState = new Label(PressGangCCMSUI.INSTANCE.TagSearchState());


            /*
                Because a column span reduces the number of columns in a table, we need to do some funky
                calculations here on the column index.

                If the bulk tag checkboxes are not being displayed, the search state cell spans
                2 columns. This means that for every loop over the columns, we need to subtract 1 from the
                column index, since each span done in the previous loop reduces the number of cells in
                this row by one.
            */
            tagsTable.setWidget(2, i * (TAG_CELLS_PER_COLUMN - (showBulkTags ? 0 : 1)), tags);
            tagsTable.getFlexCellFormatter().addStyleName(2, i * (TAG_CELLS_PER_COLUMN - (showBulkTags ? 0 : 1)), CSSConstants.SearchView.LOGIC_HEADER_CELL);

            tagsTable.setWidget(2, i * (TAG_CELLS_PER_COLUMN - (showBulkTags ? 0 : 1)) + 1, tagSearchState);
            tagsTable.getFlexCellFormatter().addStyleName(2, i * (TAG_CELLS_PER_COLUMN - (showBulkTags ? 0 : 1)) + 1, CSSConstants.SearchView.LOGIC_HEADER_CELL);


            if (showBulkTags) {
                @NotNull final Label tagBulkUpdateState = new Label(PressGangCCMSUI.INSTANCE.TagBulkUpdateState());
                tagsTable.setWidget(2, i * TAG_CELLS_PER_COLUMN + 2, tagBulkUpdateState);
                tagsTable.getFlexCellFormatter().addStyleName(2,  i * TAG_CELLS_PER_COLUMN + 2, CSSConstants.SearchView.LOGIC_HEADER_CELL);
            } else {
                tagsTable.getFlexCellFormatter().setColSpan(2, i * (TAG_CELLS_PER_COLUMN - (showBulkTags ? 0 : 1)) + 1, 2);
            }
        }

        summary.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                summary.removeStyleName(CSSConstants.Common.CUSTOM_BUTTON);
                summary.addStyleName(CSSConstants.Common.CUSTOM_BUTTON_DOWN);
            }
        });
    }

    @Override
    public void setDelegate(@NotNull final EditorDelegate<SearchUICategory> delegate) {

    }

    @Override
    public void flush() {
        /*
            Upon flushing the selected state of the tags, update the value displayed by the tile. It is a little
            backwards to set the value of a UI element from the underlying data object in a flush operation, but
            in this case it allows us to complete the loop from SearchUITagEditor to SearchUITag and then back to
            the FourTextAndImageButtonSearchUICategoryEditor.
         */
        this.summary.asEditor().setValue(value.getSummary());

        /* flush the value of the category logic checkboxes */
        value.setExternalLogicAnd(externalLogicAnd.getValue());
        value.setExternalLogicOr(externalLogicOr.getValue());
        value.setInternalLogicAnd(internalLogicAnd.getValue());
        value.setInternalLogicOr(internalLogicOr.getValue());
    }

    @Override
    public void onPropertyChange(@NotNull final String... paths) {

    }

    @Override
    public void setValue(@NotNull final SearchUICategory value) {
        this.value = value;

        // group the radio buttons by project and category id to make them unique
        internalLogicAnd.setName(INTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId());
        internalLogicOr.setName(INTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId());
        externalLogicAnd.setName(EXTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId());
        externalLogicOr.setName(EXTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId());

        // set the default values
        internalLogicAnd.setValue(value.isInternalLogicAnd());
        internalLogicOr.setValue(value.isInternalLogicOr());
        externalLogicAnd.setValue(value.isExternalLogicAnd());
        externalLogicOr.setValue(value.isExternalLogicOr());
    }
}
