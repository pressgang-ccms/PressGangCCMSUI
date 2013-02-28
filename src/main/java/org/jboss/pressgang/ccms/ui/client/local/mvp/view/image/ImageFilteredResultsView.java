package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImageFilteredResultsView extends BaseFilteredResultsView<RESTImageCollectionItemV1>
        implements ImageFilteredResultsPresenter.Display {

    private final TextBox imageIdFilter = new TextBox();
    private final TextBox imageDescriptionFilter = new TextBox();
    private final TextBox imageOriginalFileNameFilter = new TextBox();


    @NotNull
    private TextColumn<RESTImageCollectionItemV1> idColumn = new TextColumn<RESTImageCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTImageCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            return object.getItem().getId().toString();

        }
    };

    @NotNull
    private TextColumn<RESTImageCollectionItemV1> descriptionColumn = new TextColumn<RESTImageCollectionItemV1>() {
        @Override
        public String getValue(@Nullable final RESTImageCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            return object.getItem().getDescription();
        }
    };

    @NotNull
    @Override
    public TextBox getImageOriginalFileNameFilter() {
        return imageOriginalFileNameFilter;
    }

    @NotNull
    @Override
    public TextBox getImageIdFilter() {
        return imageIdFilter;
    }

    @NotNull
    @Override
    public TextBox getImageDescriptionFilter() {
        return imageDescriptionFilter;
    }

    public ImageFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images(), PressGangCCMSUI.INSTANCE.CreateImage());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
        getResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());

        addFilterField(PressGangCCMSUI.INSTANCE.ImageID(), imageIdFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ImageDescription(), imageDescriptionFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ImageOriginalFileName(), imageOriginalFileNameFilter);

        new NumbersAndCommaValidator(imageIdFilter);
    }

}
