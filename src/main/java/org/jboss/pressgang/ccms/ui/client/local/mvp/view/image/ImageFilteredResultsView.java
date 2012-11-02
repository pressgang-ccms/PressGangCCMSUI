package org.jboss.pressgang.ccms.ui.client.local.mvp.view.image;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTImageV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.css.TableResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImageFilteredResultsView extends BaseFilteredResultsView<RESTImageV1, RESTImageCollectionV1, RESTImageCollectionItemV1>
        implements ImageFilteredResultsPresenter.Display {

    private final TextBox imageIdFilter = new TextBox();
    private final TextBox imageDescriptionFilter = new TextBox();
    private final TextBox imageOriginalFileNameFilter = new TextBox();


    private final TextColumn<RESTImageCollectionItemV1> idColumn = new TextColumn<RESTImageCollectionItemV1>() {
        @Override
        public String getValue(final RESTImageCollectionItemV1 object) {
            return object.getItem().getId().toString();

        }
    };

    private final TextColumn<RESTImageCollectionItemV1> descriptionColumn = new TextColumn<RESTImageCollectionItemV1>() {
        @Override
        public String getValue(final RESTImageCollectionItemV1 object) {
            return object.getItem().getDescription();
        }
    };

    @Override
    public TextBox getImageOriginalFileNameFilter() {
        return imageOriginalFileNameFilter;
    }

    @Override
    public TextBox getImageIdFilter() {
        return imageIdFilter;
    }

    @Override
    public TextBox getImageDescriptionFilter() {
        return imageDescriptionFilter;
    }

    public ImageFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());

       
        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
        getResults().addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());

        addFilterField(PressGangCCMSUI.INSTANCE.ImageID(), imageIdFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ImageDescription(), imageDescriptionFilter);
        addFilterField(PressGangCCMSUI.INSTANCE.ImageOriginalFileName(), imageOriginalFileNameFilter);
    }

}
