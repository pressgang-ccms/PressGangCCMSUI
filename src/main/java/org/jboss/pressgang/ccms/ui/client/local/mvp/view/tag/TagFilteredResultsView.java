package org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag.TagFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;

public class TagFilteredResultsView extends BaseFilteredResultsView<RESTTagV1, RESTTagCollectionV1, RESTTagCollectionItemV1> implements
        TagFilteredResultsPresenter.Display {

    private final TextBox idFilter = new TextBox();
    private final TextBox nameFilter = new TextBox();
    private final TextBox descriptionFilter = new TextBox();

    private final TextColumn<RESTTagCollectionItemV1> idColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                /* don't display the null ID for new tags */
                if (object.getItem().getId() != null && object.getItem().getId().equals(Constants.NULL_ID)) {
                    return "";
                } else {
                    return object.getItem().getId() + "";
                }
            }
            return null + "";
        }
    };

    private final TextColumn<RESTTagCollectionItemV1> nameColumn = new TextColumn<RESTTagCollectionItemV1>() {
        @Override
        public String getValue(final RESTTagCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                return object.getItem().getName();
            }
            return null + "";
        }
    };

    @Override
    public TextBox getNameFilter() {
        return nameFilter;
    }

    @Override
    public TextBox getIdFilter() {
        return idFilter;
    }

    @Override
    public TextBox getDescriptionFilter() {
        return descriptionFilter;
    }

    public TagFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Tags(), PressGangCCMSUI.INSTANCE.CreateTag());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.TagID());
        getResults().addColumn(nameColumn, PressGangCCMSUI.INSTANCE.TagName());

        this.addFilterField(PressGangCCMSUI.INSTANCE.TagIDs(), idFilter);
        this.addFilterField(PressGangCCMSUI.INSTANCE.TagName(), nameFilter);
        this.addFilterField(PressGangCCMSUI.INSTANCE.TagDescription(), descriptionFilter);
        
        new NumbersAndCommaValidator(idFilter);

    }
}
