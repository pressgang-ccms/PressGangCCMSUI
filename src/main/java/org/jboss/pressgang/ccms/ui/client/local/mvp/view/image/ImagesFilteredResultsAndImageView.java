package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class ImagesFilteredResultsAndImageView extends BaseSearchAndEditView implements
        ImagesFilteredResultsAndImagePresenter.Display {
   

    public ImagesFilteredResultsAndImageView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
    }

}
