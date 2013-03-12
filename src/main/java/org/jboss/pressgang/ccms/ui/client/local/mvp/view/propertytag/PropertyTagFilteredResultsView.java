package org.jboss.pressgang.ccms.ui.client.local.mvp.view.propertytag;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.propertytag.PropertyTagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertyTagFilteredResultsView extends
        BaseFilteredResultsView<RESTPropertyTagCollectionItemV1> implements
        PropertyTagFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    @NotNull
    private final TextColumn<RESTPropertyTagCollectionItemV1> idColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return null + "";
        }
    };

    @NotNull
    private final TextColumn<RESTPropertyTagCollectionItemV1> nameColumn = new TextColumn<RESTPropertyTagCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTPropertyTagCollectionItemV1 object) {
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
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public PropertyTagFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ExtendedProperties(), PressGangCCMSUI.INSTANCE.CreateExtendedProperty());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.ExtendedPropertyName());

        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyID(), idFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyName(), nameFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ExtendedPropertyDescription(), descriptionFilter);

        new NumbersAndCommaValidator(idFilter);
    }
}