package org.jboss.pressgang.ccms.ui.client.local.mvp.view.stringconstant;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTStringConstantCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.stringconstants.StringConstantFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view used to show the list of integer constants.
 */
public class StringConstantFilteredResultsView extends
        BaseFilteredResultsView<RESTStringConstantCollectionItemV1> implements
        StringConstantFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox valueFilter = new TextBox();

    @NotNull
    private TextColumn<RESTStringConstantCollectionItemV1> idColumn = new TextColumn<RESTStringConstantCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTStringConstantCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private TextColumn<RESTStringConstantCollectionItemV1> nameColumn = new TextColumn<RESTStringConstantCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTStringConstantCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getName() != null) {
                return object.getItem().getName();
            }

            return null + "";
        }
    };

    @NotNull
    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @NotNull
    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @NotNull
    @Override
    public TextBox getValueFilter() {
        return valueFilter;
    }

    public StringConstantFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.StringConstants(), PressGangCCMSUI.INSTANCE.CreateStringConstant());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.StringConstantId());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.StringConstantName());

        addFilterField(PressGangCCMSUI.INSTANCE.StringConstantId(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.StringConstantName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.StringConstantValue(), valueFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}
