package org.jboss.pressgang.ccms.ui.client.local.mvp.view.stringconstant;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTStringConstantCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTStringConstantV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;

/**
 * The view used to display the integer constants results and their details.
 */
public class StringConstantFilteredResultsAndDetailsView extends
        BaseSearchAndEditView<RESTStringConstantV1, RESTStringConstantCollectionV1, RESTStringConstantCollectionItemV1> implements
        StringConstantFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());


    @Override
    public PushButton getSave() {
        return save;
    }


    public StringConstantFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Categories());
        populateTopActionBar();
    }


    private void populateTopActionBar() {
        this.addActionButton(this.getSave());
    }
}
