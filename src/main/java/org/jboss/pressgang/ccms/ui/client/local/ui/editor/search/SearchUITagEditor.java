package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TriStatePushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter.Display.SearchPresenterDriver;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUITag;
import org.jetbrains.annotations.NotNull;

/**
 * A GWT Editor to provide a visual representation of SearchUITag
 *
 * @author Matthew Casperson
 */
public final class SearchUITagEditor implements Editor<SearchUITag> {
    private final SearchUICategoryEditor searchUICategory;

    final Label name = new Label();
    final TriStatePushButton state = new TriStatePushButton();
    final TriStatePushButton bulkTagState = new TriStatePushButton();

    public SearchUITagEditor(@NotNull final SearchPresenterDriver driver, @NotNull final SearchUICategoryEditor searchUICategory) {
        this.searchUICategory = searchUICategory;

        name.addStyleName(CSSConstants.TagListTagView.TAG_LABEL);

        state.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                driver.flush();
            }
        });

        bulkTagState.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(@NotNull final ClickEvent event) {
                driver.flush();
            }
        });
    }
}
