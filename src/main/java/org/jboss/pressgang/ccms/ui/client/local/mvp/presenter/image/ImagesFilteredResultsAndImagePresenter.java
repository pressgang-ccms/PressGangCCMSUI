package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditViewInterface;

import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class ImagesFilteredResultsAndImagePresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";

    public interface Display extends
            BaseSearchAndEditViewInterface<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1> {

    }

    public interface LogicComponent extends Component<Display> {
        void bind(final ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay,
                final ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent,
                final ImagePresenter.Display imageDisplay, final ImagePresenter.LogicComponent imageComponent,
                final ImagesFilteredResultsAndImagePresenter.Display display,
                final BaseTemplateViewInterface waitDisplay);
    }

    @Inject
    private Display display;

    @Inject
    private LogicComponent component;

    @Inject
    private ImageFilteredResultsPresenter.Display imageFilteredResultsDisplay;

    @Inject
    private ImageFilteredResultsPresenter.LogicComponent imageFilteredResultsComponent;

    @Inject
    private ImagePresenter.Display imageDisplay;

    @Inject
    private ImagePresenter.LogicComponent imageComponent;

    private String queryString;

    @Override
    public void go(final HasWidgets container) {
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        clearContainerAndAddTopLevelPanel(container, display);

        imageComponent.bind(imageDisplay, display);
        imageFilteredResultsComponent.bind(queryString, imageFilteredResultsDisplay, display);

        component.bind(imageFilteredResultsDisplay, imageFilteredResultsComponent, imageDisplay, imageComponent, display,
                display);
    }

    @Override
    public void parseToken(final String historyToken) {
        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }
    }
}
