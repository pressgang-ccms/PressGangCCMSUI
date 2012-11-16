package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;

@Dependent
public class SearchTagsFieldsAndFiltersComponent extends ComponentBase<SearchTagsFieldsAndFiltersPresenter.Display> implements
        SearchTagsFieldsAndFiltersPresenter.LogicComponent {

    private SearchPresenter.Display tagsDisplay;
    private SearchPresenter.LogicComponent tagsComponent;
    private SearchFieldPresenter.Display fieldsDisplay;
    private SearchFieldPresenter.LogicComponent fieldsComponent;

    @Inject
    private HandlerManager eventBus;

    @Override
    public void bind(final SearchPresenter.Display tagsDisplay, final SearchPresenter.LogicComponent tagsComponent,
            final SearchFieldPresenter.Display fieldsDisplay, final SearchFieldPresenter.LogicComponent fieldsComponent,
            final SearchTagsFieldsAndFiltersPresenter.Display display, final BaseTemplateViewInterface waitDisplay) {
        super.bind(display, waitDisplay);

        this.tagsDisplay = tagsDisplay;
        this.tagsComponent = tagsComponent;
        this.fieldsDisplay = fieldsDisplay;
        this.fieldsComponent = fieldsComponent;

        bindSearchButtons();
        
        displayTags();
    }

    private void bindSearchButtons() {

        final ClickHandler tagsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayTags();
            }
        };

        final ClickHandler fieldsHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                displayFields();
            }
        };

        final ClickHandler searchHandler = new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                fieldsDisplay.getDriver().flush();
                final String query = tagsDisplay.getSearchUIProjects().getSearchQuery(true)
                        + fieldsDisplay.getSearchUIFields().getSearchQuery(false);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        fieldsDisplay.getTagsSearch().addClickHandler(tagsHandler);
        tagsDisplay.getFields().addClickHandler(fieldsHandler);

        tagsDisplay.getSearch().addClickHandler(searchHandler);
        fieldsDisplay.getSearch().addClickHandler(searchHandler);
    }
    
    private void displayTags()
    {
        display.getTopActionParentPanel().clear();
        display.getTopActionParentPanel().setWidget(tagsDisplay.getTopActionPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsDisplay.getPanel());
        
        fieldsDisplay.setViewShown(false);
        tagsDisplay.setViewShown(true);
    }
    
    private void displayFields()
    {
        display.getTopActionParentPanel().clear();
        display.getTopActionParentPanel().setWidget(fieldsDisplay.getTopActionPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsDisplay.getPanel());
        
        fieldsDisplay.setViewShown(true);
        tagsDisplay.setViewShown(false);
    }
}
