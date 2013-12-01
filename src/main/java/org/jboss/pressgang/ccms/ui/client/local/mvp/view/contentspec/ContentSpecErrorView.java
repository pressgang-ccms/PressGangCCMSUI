package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import com.google.gwt.user.client.ui.TextArea;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecErrorPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.Nullable;

/**
 * The view that displays the text content of a content spec
 */
public class ContentSpecErrorView extends BaseTemplateView implements ContentSpecErrorPresenter.Display {

    private final TextArea errors = new TextArea();

    public ContentSpecErrorView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ContentSpecErrors());
        errors.setReadOnly(true);
        errors.addStyleName(CSSConstants.ContentSpecView.CONTENT_SPEC_ERRORS);
        this.getPanel().setWidget(errors);
    }

    public void display(@Nullable final RESTTextContentSpecV1 contentSpec) {
        errors.setText("");

        if (contentSpec != null && contentSpec.getErrors() != null) {
            errors.setText(contentSpec.getErrors());
        }
    }
}
