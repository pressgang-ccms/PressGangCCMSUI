package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Frame;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicBIRTBugsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.SplitType;

import java.util.List;

/**
 * A MVP view for displaying a topic's Bugzilla Bugs. This view simply displays an iFrame
 * to a BIRT report.
 * 
 * @author Matthew Casperson
 */
public final class TopicBIRTBugsView extends BaseTemplateView implements TopicBIRTBugsPresenter.Display {

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
        if (topic.getId() != null) {
            iFrame.setUrl(Constants.BIRT_URL + Constants.BIRT_RUN_REPORT + Constants.BIRT_TOPIC_BUGZILLA_REPORT + topic.getId());
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public SimpleBeanEditorDriver getDriver() {
        return null;
    }
}
