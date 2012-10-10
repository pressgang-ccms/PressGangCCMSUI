package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.component.base.Component;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HandlerSplitLayoutPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

@Dependent
public class ImagesFilteredResultsAndImagePresenter implements TemplatePresenter {

    public static final String HISTORY_TOKEN = "ImageFilteredResultsAndImageView";

    public interface Display extends BaseTemplateViewInterface {
        SimpleLayoutPanel getResultsPanel();

        SimpleLayoutPanel getViewPanel();

        SimpleLayoutPanel getViewActionButtonsPanel();

        SimpleLayoutPanel getResultsActionButtonsPanel();

        HandlerSplitLayoutPanel getSplitPanel();

        DockLayoutPanel getViewLayoutPanel();
    }

    public interface LogicComponent extends Component<Display> {

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
        display.setViewShown(true);
        display.setFeedbackLink(Constants.KEY_SURVEY_LINK + HISTORY_TOKEN);

        clearContainerAndAddTopLevelPanel(container, display);

        display.getResultsActionButtonsPanel().setWidget(imageFilteredResultsDisplay.getTopActionPanel());
        display.getResultsPanel().setWidget(imageFilteredResultsDisplay.getPanel());

        component.bind(display, display);
        component.setFeedbackLink(HISTORY_TOKEN);
        imageComponent.bind(imageDisplay, display);
        imageFilteredResultsComponent.bind(imageFilteredResultsDisplay, display);
    }

    @Override
    public void parseToken(final String historyToken) {

        queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);
        if (!queryString.startsWith(Constants.QUERY_PATH_SEGMENT_PREFIX)) {
            queryString = Constants.QUERY_PATH_SEGMENT_PREFIX;
        }

        final String[] queryStringElements = queryString.replace(Constants.QUERY_PATH_SEGMENT_PREFIX, "").split(";");
        for (final String queryStringElement : queryStringElements) {
            final String[] queryElements = queryStringElement.split("=");

            if (queryElements.length == 2) {
                if (queryElements[0].equals("imageIds")) {
                    this.imageFilteredResultsDisplay.getImageIdFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageDesc")) {
                    this.imageFilteredResultsDisplay.getImageDescriptionFilter().setText(queryElements[1]);
                } else if (queryElements[0].equals("imageOrigName")) {
                    this.imageFilteredResultsDisplay.getImageOriginalFileNameFilter().setText(queryElements[1]);
                }
            }
        }
    }
}
