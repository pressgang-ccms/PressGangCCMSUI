package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImagesFilteredResultsAndImagePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class ImagesFilteredResultsAndImageView extends
        BaseSearchAndEditView<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1> implements
        ImagesFilteredResultsAndImagePresenter.Display {

    public ImagesFilteredResultsAndImageView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults());
    }

}
