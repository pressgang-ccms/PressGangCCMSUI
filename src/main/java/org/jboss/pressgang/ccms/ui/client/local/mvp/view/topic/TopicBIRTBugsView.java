package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBugzillaBugCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBIRTBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import java.util.List;

/**
 * A MVP view for displaying a topic's Bugzilla Bugs. This view simply displays an iFrame
 * to a BIRT report.
 * 
 * @author Matthew Casperson
 */
public class TopicBIRTBugsView extends BaseTemplateView implements TopicBIRTBugsPresenter.Display {

    private final Frame iFrame;

    public TopicBIRTBugsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
                + PressGangCCMSUI.INSTANCE.Bugs());

        iFrame = new Frame();
        iFrame.setWidth("100%");
        iFrame.setHeight("100%");
        iFrame.getElement().getStyle().setBorderWidth(0, Style.Unit.PX);
        this.getPanel().add(iFrame);
    }

    @Override
    public void initialize(final RESTTopicV1 topic, final boolean readOnly, final boolean newTopic, final SplitType splitType, final List<String> locales, final Boolean showImages) {
        iFrame.setUrl(Constants.BIRT_URL + Constants.BIRT_RUN_REPORT + Constants.BIRT_TOPIC_BUGZILLA_REPORT + topic.getId());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return null;
    }
}
