package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.*;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicReviewPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.server.ServerDetails;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * The view to display a topic review
 */
public class TopicReviewView extends BaseTemplateView implements TopicReviewPresenter.Display {

    private final PushButton startReview = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.StartReview());
    private final PushButton endAndAcceptReview = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.EndAndAcceptReview());
    private final PushButton endAndRejectReview = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.EndAndRejectReview());
    private final FlexTable verticalPanel = new FlexTable();
    private final HorizontalPanel buttonPanel = new HorizontalPanel();
    private final Frame content = new Frame();
    private final Label info = new Label();
    private String htmlDiff;


    /**
     * The constructor is used to piece the template together.
     */
    public TopicReviewView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults() + " - "
            + PressGangCCMSUI.INSTANCE.RenderedView());

        verticalPanel.addStyleName(CSSConstants.TopicReviewView.TOPIC_REVIEW_LAYOUT_PANEL);
        buttonPanel.addStyleName(CSSConstants.TopicReviewView.TOPIC_REVIEW_BUTTON_LAYOUT_PANEL);

        content.addStyleName(CSSConstants.TopicReviewView.TOPIC_REVIEW_HELP_IFRAME);

        verticalPanel.setWidget(0, 0, content);
        verticalPanel.setWidget(1, 0, buttonPanel);

        verticalPanel.getFlexCellFormatter().addStyleName(0, 0, CSSConstants.TopicReviewView.TOPIC_REVIEW_HELP_IFRAME_PARENT);

        this.getPanel().setWidget(verticalPanel);
    }

    @NotNull
    @Override
    public PushButton getStartReview() {
        return startReview;
    }

    @NotNull
    @Override
    public PushButton getEndAndAcceptReview() {
        return endAndAcceptReview;
    }

    @NotNull
    @Override
    public PushButton getEndAndRejectReview() {
        return endAndRejectReview;
    }

    @Override
    public void showWaitingFromRenderedDiff() {
        this.addWaitOperation();
    }

    @Override
    public void displayHtmlDiff(@NotNull final String htmlDiff) {
        this.removeWaitOperation();

        buttonPanel.clear();
        buttonPanel.add(endAndAcceptReview);
        buttonPanel.add(endAndRejectReview);
        buttonPanel.add(info);

        this.htmlDiff = htmlDiff;
        reDisplayHtmlDiff();
    }

    /**
     * The iFrame will lose its contents if it is detached, so here we redisplay the
     * html diff that was generated and supplied to us through the displayHtmlDiff() method.
     */
    @Override
    public void reDisplayHtmlDiff() {
        if (htmlDiff != null) {
            final IFrameElement iFrameElement = content.getElement().cast();
            if (iFrameElement.getContentDocument() != null) {
                GWTUtilities.writeHTMLToIFrame(iFrameElement.getContentDocument(), htmlDiff);
            }
        }
    }

    @Override
    public void showHelpTopic(@NotNull final Integer helpTopic) {
        buttonPanel.clear();
        buttonPanel.add(startReview);

        content.setUrl(ServerDetails.getSavedServer().getRestEndpoint() +
                Constants.ECHO_ENDPOINT + "?id=" + helpTopic + "&" +
                Constants.ECHO_ENDPOINT_PARENT_DOMAIN_QUERY_PARAM + "=" + GWTUtilities.getLocalUrlEncoded());

    }

    @Override
    protected void showWaiting() {
        final Image spinner = new Image(ImageResources.INSTANCE.spinner());
        spinner.addStyleName(CSSConstants.TopicReviewView.TOPIC_REVIEW_VIEW_SPINNER);
        this.getPanel().setWidget(spinner);
    }

    @Override
    protected void hideWaiting() {
        this.getPanel().setWidget(verticalPanel);
        reDisplayHtmlDiff();
    }

    @Override
    @NotNull
    public Label getInfo() {
        return info;
    }
}
