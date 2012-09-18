package org.jboss.pressgangccms.client.local.mvp.view.image;

import org.jboss.pressgangccms.client.local.constants.CSSConstants;
import org.jboss.pressgangccms.client.local.constants.Constants;
import org.jboss.pressgangccms.client.local.mvp.presenter.image.ImageFilteredResultsPresenter;
import org.jboss.pressgangccms.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgangccms.client.local.resources.css.TableResources;
import org.jboss.pressgangccms.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgangccms.client.local.ui.UIUtilities;
import org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider;
import org.jboss.pressgangccms.rest.v1.entities.RESTImageV1;

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

public class ImageFilteredResultsView extends BaseTemplateView implements ImageFilteredResultsPresenter.Display {
    

    private final VerticalPanel searchResultsPanel = new VerticalPanel();
    private final FlexTable filterTable = new FlexTable();
    private final Label imageIdFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageID());
    private final TextBox imageIdFilter = new TextBox();
    private final Label imageDescriptionFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageDescription());
    private final TextBox imageDescriptionFilter = new TextBox();
    private final Label imageOriginalFileNameFilterLabel = new Label(PressGangCCMSUI.INSTANCE.ImageOriginalFileName());
    private final TextBox imageOriginalFileNameFilter = new TextBox();
    private final PushButton search = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());

    private final SimplePager pager = new SimplePager();
    private final CellTable<RESTImageV1> results = new CellTable<RESTImageV1>(Constants.MAX_SEARCH_RESULTS,
            (Resources) GWT.create(TableResources.class));
    private org.jboss.pressgangccms.client.local.utilities.EnhancedAsyncDataProvider<RESTImageV1> provider;

    private final TextColumn<RESTImageV1> idColumn = new TextColumn<RESTImageV1>() {
        @Override
        public String getValue(final RESTImageV1 object) {
            return object.getId().toString();

        }
    };

    private final TextColumn<RESTImageV1> descriptionColumn = new TextColumn<RESTImageV1>() {
        @Override
        public String getValue(final RESTImageV1 object) {
            return object.getDescription();
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

    @Override
    public SimplePager getPager() {
        return pager;
    }

    @Override
    public CellTable<RESTImageV1> getResults() {
        return results;
    }

    @Override
    public EnhancedAsyncDataProvider<RESTImageV1> getProvider() {
        return provider;
    }

    @Override
    public void setProvider(EnhancedAsyncDataProvider<RESTImageV1> provider) {
        this.provider = provider;
        provider.addDataDisplay(results);
    }

    public ImageFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Images());

        this.addActionButton(search);
        this.addRightAlignedActionButtonPaddingPanel();

        results.addColumn(idColumn, PressGangCCMSUI.INSTANCE.ImageID());
        results.addColumn(descriptionColumn, PressGangCCMSUI.INSTANCE.ImageDescription());

        searchResultsPanel.addStyleName(CSSConstants.IMAGEFILTEREDRESULTSPANEL);
        filterTable.addStyleName(CSSConstants.IMAGEFILTEREDOPTIONSPANEL);

        filterTable.setWidget(0, 0, imageIdFilterLabel);
        filterTable.setWidget(0, 1, imageIdFilter);
        filterTable.setWidget(1, 0, imageDescriptionFilterLabel);
        filterTable.setWidget(1, 1, imageDescriptionFilter);
        filterTable.setWidget(2, 0, imageOriginalFileNameFilterLabel);
        filterTable.setWidget(2, 1, imageOriginalFileNameFilter);

        searchResultsPanel.add(filterTable);
        searchResultsPanel.add(results);
        searchResultsPanel.add(pager);

        pager.setDisplay(results);

        this.getPanel().add(searchResultsPanel);
    }

    @Override
    public PushButton getSearch() {
        return search;
    }

    @Override
    protected void showWaiting() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void hideWaiting() {
        // TODO Auto-generated method stub
        
    }
}
