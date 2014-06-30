package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;


import com.google.gwt.user.client.ui.CheckBox;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TranslatedTopicRenderedPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.base.BaseTopicRenderedView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

/**
 * This view maintains a kind of double buffer. IFrames are loaded into hidden table cells, and then
 * "flipped" out after a period of time to give a seamless appearance of a panel being updated.
 */
public class TranslatedTopicRenderedView extends BaseTopicRenderedView implements TranslatedTopicRenderedPresenter.Display {
    private final CheckBox mergeAdditionalXML = new CheckBox(PressGangCCMSUI.INSTANCE.MergeAdditionalXml());

    public TranslatedTopicRenderedView() {
        super(PressGangCCMSUI.INSTANCE.RenderedView());

        getRenderingOptions().setWidget(1, 1, mergeAdditionalXML);
        mergeAdditionalXML.setValue(true);
    }

    @Override
    public CheckBox getMergeAdditionalXML() {
        return mergeAdditionalXML;
    }
}