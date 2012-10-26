package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagsFilteredResultsAndTagPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class TagsFilteredResultsAndTagView extends BaseSearchAndEditView implements TagsFilteredResultsAndTagPresenter.Display {
    public TagsFilteredResultsAndTagView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags());  
    }
}
