package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.ComponentBase;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.viewevents.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class SearchTagsFieldsAndFiltersPresenter extends ComponentBase implements TemplatePresenter
{
    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;
    @Inject
    private SearchPresenter tagsComponent;
    @Inject
    private SearchFieldPresenter fieldsComponent;

    @Inject
    private HandlerManager eventBus;

    @Override
    public void go(final HasWidgets container) {

        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        super.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, display);

        tagsComponent.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
        fieldsComponent.process(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);

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
                fieldsComponent.getDisplay().getDriver().flush();
                final String query = tagsComponent.getDisplay().getSearchUIProjects().getSearchQuery(true)
                        + fieldsComponent.getDisplay().getSearchUIFields().getSearchQuery(false);
                eventBus.fireEvent(new SearchResultsAndTopicViewEvent(query, GWTUtilities.isEventToOpenNewWindow(event)));
            }
        };

        fieldsComponent.getDisplay().getTagsSearch().addClickHandler(tagsHandler);
        tagsComponent.getDisplay().getFields().addClickHandler(fieldsHandler);

        tagsComponent.getDisplay().getSearch().addClickHandler(searchHandler);
        fieldsComponent.getDisplay().getSearch().addClickHandler(searchHandler);
    }

    private void displayTags()
    {
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(tagsComponent.getDisplay().getTopActionParentPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(tagsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(false);
        tagsComponent.getDisplay().setViewShown(true);
    }

    private void displayFields()
    {
        display.getTopActionGrandParentPanel().clear();
        display.getTopActionGrandParentPanel().setWidget(fieldsComponent.getDisplay().getTopActionParentPanel());

        display.getPanel().clear();
        display.getPanel().setWidget(fieldsComponent.getDisplay().getPanel());

        fieldsComponent.getDisplay().setViewShown(true);
        tagsComponent.getDisplay().setViewShown(false);
    }
}
