package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseCustomViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

@Dependent
public class TopicRevisionsPresenter extends BaseTemplatePresenter {

    public interface Display extends BaseTemplateViewInterface, BaseCustomViewInterface<RESTTopicV1> {

        EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTTopicCollectionItemV1> provider);

        CellTable<RESTTopicCollectionItemV1> getResults();

        SimplePager getPager();

        Column<RESTTopicCollectionItemV1, String> getViewButton();

        Column<RESTTopicCollectionItemV1, String> getDiffButton();

        /**
         * @return The currently selected revision topic.
         */
        RESTTopicCollectionItemV1 getRevisionTopic();

        /**
         * @param revisionTopic The currently selected revision topic.
         */
        void setRevisionTopic(RESTTopicCollectionItemV1 revisionTopic);
    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicHistoryView";

    private String topicId;

    @Inject
    private Display display;

    public Display getDisplay() {
        return display;
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    public void bindExtended(final int topicId, final String pageId) {
        super.bind(topicId, pageId, display);
    }

    @Override
    public void parseToken(final String historyToken) {

    }

    /**
     * Open a new window with the results of a prettydiff comparison
     *
     * @param source      The source XML
     * @param sourceLabel The source XML label
     * @param diff        The diff XML
     * @param diffLabel   The diff XML label
     */
    native public void displayDiff(final String source, final String sourceLabel, final String diff, final String diffLabel, final boolean isXML)
    /*-{
        var diffTable = $wnd.prettydiff({
            source: source,
            sourcelabel: sourceLabel,
            diff: diff,
            difflabel: diffLabel,
            lang: isXML ? "markup" : "text",
            mode: "diff",
            diffview: "sidebyside"
        })[0];

        var win = $wnd.open("", "_blank", "width=" + (screen.width - 200) + ", height=" + (screen.height - 200) + ",scrollbars=yes"); // a window object
        if (win != null) {
            win.document.open("text/html", "replace");
            win.document
                .write("<HTML><HEAD><TITLE>PressGangCCMS XML Diff</TITLE><link rel=\"stylesheet\" type=\"text/css\" href=\"../prettydiff.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"prettydiff.css\"></HEAD><BODY>"
                    + diffTable + "</BODY></HTML>");
            win.document.close();
        }
    }-*/;
}
