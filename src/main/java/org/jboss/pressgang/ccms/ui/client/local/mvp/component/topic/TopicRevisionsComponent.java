package org.jboss.pressgang.ccms.ui.client.local.mvp.component.topic;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicRevisionsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;

public class TopicRevisionsComponent extends TopicViewComponent<TopicRevisionsPresenter.Display> implements TopicRevisionsPresenter.LogicComponent {
    
    @Override
    public void bind(final TopicRevisionsPresenter.Display display, final BaseTemplateViewInterface waitDisplay)
    {
        super.bind(display, waitDisplay);
    }
    
    /**
     * Open a new window with the results of a prettydiff comparison
     * 
     * @param source The source XML
     * @param sourceLabel The source XML label
     * @param diff The diff XML
     * @param diffLabel The diff XML label
     */
    @Override
    native public void displayDiff(final String source, final String sourceLabel, final String diff, final String diffLabel)
    /*-{
        var diffTable = $wnd.prettydiff({
            source : source,
            sourcelabel : sourceLabel,
            diff : diff,
            difflabel : diffLabel,
            lang : "markup",
            mode : "diff",
            diffview : "sidebyside"
        })[0];

        var win = $wnd.open("", "_blank", "width=" + (screen.width - 200)
                + ", height=" + (screen.height - 200)); // a window object
        if (win != null) {
            win.document.open("text/html", "replace");
            win.document
                    .write("<HTML><HEAD><TITLE>PressGangCCMS XML Diff</TITLE><link rel=\"stylesheet\" type=\"text/css\" href=\"../prettydiff.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"prettydiff.css\"></HEAD><BODY>"
                            + diffTable + "</BODY></HTML>");
            win.document.close();
        }
    }-*/;
    
    
}
