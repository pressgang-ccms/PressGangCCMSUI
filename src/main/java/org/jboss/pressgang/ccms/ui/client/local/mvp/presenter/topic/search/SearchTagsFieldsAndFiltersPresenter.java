package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchFieldPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchTagsFieldsAndFiltersPresenter extends ComponentBase<BaseTemplateViewInterface> implements TemplatePresenter
{
    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;
    @Inject
    private SearchPresenter.Display tagsDisplay;
    @Inject
    private SearchPresenter.LogicComponent tagsComponent;
    @Inject
    private SearchFieldPresenter.Display fieldsDisplay;
    @Inject
    private SearchFieldPresenter.LogicComponent fieldsComponent;

    @Inject
    private HandlerManager eventBus;

    @Override
    public void go(final HasWidgets container) {

        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        super.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, display, waitDisplay);

        tagsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, tagsDisplay, display);
        fieldsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, fieldsDisplay, display);

        bindSearchButtons();

        displayTags();
    }

    @Override
    public void parseToken(final String historyToken) {

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
