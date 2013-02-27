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
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkState;

public final class SearchUICategoryEditor extends ScrollPanel implements ValueAwareEditor<SearchUICategory> {
    private static final int COLUMNS = 2;
    private static final String INTERNAL_LOGIC_RADIOBUTTON_GROUP = "InternalLogic";
    private static final String EXTERNAL_LOGIC_RADIOBUTTON_GROUP = "ExternalLogic";

    private final SearchPresenterDriver driver;
    private final SearchUIProjectEditor searchUIProject;
    private SearchUICategory value;
    private final FlexTable tagsTable = new FlexTable();
    private RadioButton internalLogicAnd;
    private RadioButton internalLogicOr;
    private RadioButton externalLogicAnd;
    private RadioButton externalLogicOr;

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
            final int fixedIndex = (index / COLUMNS) + 2; // add two because the first rows are taken up by the category logic controls
            final int column = index % COLUMNS;

            final SearchUITagEditor subEditor = new SearchUITagEditor(driver, SearchUICategoryEditor.this);
            tagsTable.setWidget(fixedIndex, column * 2, subEditor.name);
            tagsTable.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
            return subEditor;
        }

        @Override
        public void dispose(@NotNull final SearchUITagEditor subEditor) {
            subEditor.name.removeFromParent();
            subEditor.state.removeFromParent();
        }

        @Override
        public void setIndex(@NotNull final SearchUITagEditor subEditor, final int index) {
            final int fixedIndex = (index / COLUMNS) + 2; // add two because the first rows are taken up by the category logic controls
            final int column = index % COLUMNS;

            tagsTable.setWidget(fixedIndex, column * 2, subEditor.name);
            tagsTable.setWidget(fixedIndex, (column * 2) + 1, subEditor.state);
        }
    }

    public SearchUICategoryEditor(@NotNull final SearchPresenterDriver driver, @NotNull final SearchUIProjectEditor searchUIProject) {
        this.driver = driver;
        this.searchUIProject = searchUIProject;

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
    public void setDelegate(@NotNull final EditorDelegate<SearchUICategory> delegate) {

    }

    @Override
    public void flush() {
        this.summary.asEditor().setValue(value.getSummary());
    }

    @Override
    public void onPropertyChange(@NotNull final String... paths) {

    }

    @Override
    public void setValue(@NotNull final SearchUICategory value) {
        this.value = value;

        /*
            Build up the internal and external category logic ui elements. We do this here because
            we need to know what category this editor is working with in order to group the
            radio buttons.
         */

        checkState(COLUMNS >= 2, "The layout of the category logic ui elements assumes that there are at least 2 columns.");

        // group the radio buttons by project and category id to make them unique
        internalLogicAnd = new RadioButton(INTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId(), PressGangCCMSUI.INSTANCE.And());
        internalLogicOr = new RadioButton(INTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId(), PressGangCCMSUI.INSTANCE.Or());
        externalLogicAnd = new RadioButton(EXTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId(), PressGangCCMSUI.INSTANCE.And());
        externalLogicOr = new RadioButton(EXTERNAL_LOGIC_RADIOBUTTON_GROUP + searchUIProject.getValue().getId() + value.getId(), PressGangCCMSUI.INSTANCE.Or());

        // set the default values
        internalLogicOr.setValue(true);
        externalLogicAnd.setValue(true);

        // build the ui
        final Label internalLogicLabel = new Label(PressGangCCMSUI.INSTANCE.InternalLogic());
        tagsTable.setWidget(0, 0, internalLogicLabel);
        tagsTable.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.SearchView.LOGIC_HEADER_CELL);

        final HorizontalPanel internalLogicPanel = new HorizontalPanel();
        internalLogicPanel.add(internalLogicAnd);
        internalLogicPanel.add(internalLogicOr);

        tagsTable.setWidget(1, 0, internalLogicPanel);

        final Label externalLogicLabel = new Label(PressGangCCMSUI.INSTANCE.ExternalLogic());
        tagsTable.setWidget(0, 1, externalLogicLabel);
        tagsTable.getFlexCellFormatter().addStyleName(0, 1, CSSConstants.SearchView.LOGIC_HEADER_CELL);

        final HorizontalPanel externalLogicPanel = new HorizontalPanel();
        externalLogicPanel.add(externalLogicAnd);
        externalLogicPanel.add(externalLogicOr);

        tagsTable.setWidget(1, 1, externalLogicPanel);
    }
}
