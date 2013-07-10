package org.jboss.pressgang.ccms.ui.client.local.mvp.view.blobconstant;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTBlobConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTBlobConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTBlobConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.blobconstants.BlobConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view used to display the blob constants results and their details.
 */
public class BlobConstantFilteredResultsAndDetailsView extends BaseSearchAndEditView<RESTBlobConstantV1, RESTBlobConstantCollectionV1,
        RESTBlobConstantCollectionItemV1> implements BlobConstantFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());


    @Override
    public PushButton getSave() {
        return save;
    }


    public BlobConstantFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.BlobConstants());
        populateTopActionBar();
        super.initialize(true);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
        getShortcuts().getAdvancedSubMenu().getBlobConstantsButton().setDown(true);
        getShortcuts().setSpacerEnabled(true);
        getShortcuts().showAdvancedSubMenu();
    }

    private void populateTopActionBar() {
        this.addActionButton(this.getSave());
    }
}
