package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The presneter that manages the topic search screen, combining the tags, fields and filters views
 * 
 * @author Matthew Casperson
 */
@Dependent
public class SearchTagsFieldsAndFiltersPresenter implements TemplatePresenter {

    /** The history token used to access this page */
    public static final String HISTORY_TOKEN = "SearchTagsFieldsAndFiltersView";

    public interface Display extends BaseTemplateViewInterface {

    }

    public interface LogicComponent extends Component<Display> {
        public void bind(final int topicId, final String pageId, final SearchPresenter.Display tagsDisplay, final SearchPresenter.LogicComponent tagsComponent,
                final SearchFieldPresenter.Display fieldsDisplay, final SearchFieldPresenter.LogicComponent fieldsComponent,
                final SearchTagsFieldsAndFiltersPresenter.Display display,
                final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;
    @Inject
    private LogicComponent component;

    /** The view that displays the tag options */
    @Inject
    private SearchPresenter.Display tagsDisplay;

    @Inject
    private SearchPresenter.LogicComponent tagsComponent;

    /** The view that displays the field options */
    @Inject
    private SearchFieldPresenter.Display fieldsDisplay;

    @Inject
    private SearchFieldPresenter.LogicComponent fieldsComponent;

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);

        display.setViewShown(true);

        tagsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, tagsDisplay, display);
        fieldsComponent.bind(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, fieldsDisplay, display);

        component.bind(ServiceConstants.SEARCH_VIEW_HELP_TOPIC, HISTORY_TOKEN, tagsDisplay, tagsComponent, fieldsDisplay, fieldsComponent, display, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }
}
