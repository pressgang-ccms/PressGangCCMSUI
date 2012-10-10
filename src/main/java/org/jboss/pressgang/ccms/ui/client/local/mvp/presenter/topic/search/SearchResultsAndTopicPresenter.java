package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTBugzillaBugCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.events.SearchResultsAndTopicViewEvent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicTagsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLErrorsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicXMLPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.TopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewCategoryEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewProjectEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview.assignedtags.TopicTagViewTagEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProjects;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;

/**
 * This presenter is used to display and wire selection of views, including the topic search results view, and the topic XML,
 * details, tags and XML Errors views.
 * 
 * @author Matthew Casperson
 */
@Dependent
public class SearchResultsAndTopicPresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "SearchResultsAndTopicView";

    public interface Display extends BaseTemplateViewInterface {
        SplitType getSplitType();

        DockLayoutPanel getResultsViewLayoutPanel();

        SimpleLayoutPanel getTopicResultsPanel();

        SimpleLayoutPanel getTopicViewPanel();

        SimpleLayoutPanel getTopicViewActionButtonsPanel();

        SimpleLayoutPanel getTopicResultsActionButtonsPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getTopicViewLayoutPanel();

        void initialize(final SplitType splitType, final Panel panel);
    }

    public interface LogicComponent extends Component<Display> {
        SplitType getSplit();
        void setSplit(final SplitType split);
        String parseToken(final String historyToken);
    }





    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Inject
    private TopicPresenter.Display topicViewDisplay;

    @Inject
    private TopicXMLPresenter.Display topicXMLDisplay;

    /**
     * The rendered topic view display
     */
    @Inject
    private TopicRenderedPresenter.Display topicRenderedDisplay;

    /**
     * The rendered topic view display in a split panel
     */
    @Inject
    private TopicRenderedPresenter.Display topicSplitPanelRenderedDisplay;

    @Inject
    private SearchResultsPresenter.Display searchResultsDisplay;

    @Inject
    private SearchResultsPresenter.LogicComponent searchResultsComponent;

    @Inject
    private TopicXMLErrorsPresenter.Display topicXMLErrorsDisplay;

    @Inject
    private TopicTagsPresenter.Display topicTagsDisplay;

    @Inject
    private TopicBugsPresenter.Display topicBugsDisplay;

    @Inject
    private TopicRevisionsPresenter.Display topicRevisionsDisplay;

    /**
     * The query string to be sent to the REST interface, as extracted from the History Token
     */
    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        
        searchResultsDisplay.setViewShown(true);
        display.setViewShown(true);

        clearContainerAndAddTopLevelPanel(container, display);

        display.getTopicResultsActionButtonsPanel().setWidget(searchResultsDisplay.getTopActionPanel());
        display.getTopicResultsPanel().setWidget(searchResultsDisplay.getPanel());
        
        component.bind(display, display);
        component.setFeedbackLink(HISTORY_TOKEN);
        searchResultsComponent.bind(queryString, searchResultsDisplay, display);

    }


    

   

    

    @Override
    public void parseToken(final String historyToken) {
        queryString = component.parseToken(historyToken);
    }

    
}
