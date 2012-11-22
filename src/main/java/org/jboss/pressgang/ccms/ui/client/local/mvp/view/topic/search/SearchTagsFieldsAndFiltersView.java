package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class SearchTagsFieldsAndFiltersView extends
        BaseTemplateView implements
        SearchTagsFieldsAndFiltersPresenter.Display {

    /** The dialog that is presented when the view is unavailable. */
    private final WaitingDialog waiting = new WaitingDialog();

    public SearchTagsFieldsAndFiltersView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());
    }

    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

}
