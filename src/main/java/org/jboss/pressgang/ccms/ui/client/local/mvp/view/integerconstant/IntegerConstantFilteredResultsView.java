package org.jboss.pressgang.ccms.ui.client.local.mvp.view.integerconstant;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTIntegerConstantCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.integerconstants.IntegerConstantFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view used to show the list of integer constants.
 */
public class IntegerConstantFilteredResultsView extends
        BaseFilteredResultsView<RESTIntegerConstantCollectionItemV1> implements
        IntegerConstantFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox valueFilter = new TextBox();

    @NotNull
    private TextColumn<RESTIntegerConstantCollectionItemV1> idColumn = new TextColumn<RESTIntegerConstantCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTIntegerConstantCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private TextColumn<RESTIntegerConstantCollectionItemV1> nameColumn = new TextColumn<RESTIntegerConstantCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTIntegerConstantCollectionItemV1 object) {
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

    public IntegerConstantFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.IntegerConstants(), PressGangCCMSUI.INSTANCE.CreateIntegerConstant());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.IntegerConstantId());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.IntegerConstantName());

        addFilterField(PressGangCCMSUI.INSTANCE.IntegerConstantId(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.IntegerConstantName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.IntegerConstantValue(), valueFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}
