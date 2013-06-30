package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.filteredresults.BaseFilteredResultsView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;

@Dependent
public class ContentSpecFilteredResultsView extends BaseFilteredResultsView<RESTContentSpecCollectionItemV1>
        implements ContentSpecFilteredResultsPresenter.Display {

    private final PushButton bulkImport = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.BulkTopicImport());
    private final PushButton bulkOverwrite = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.BulkTopicOverwrite());

    @NotNull
    private final TextColumn<RESTContentSpecCollectionItemV1> idColumn = new TextColumn<RESTContentSpecCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }
            return object.getItem().getId().toString();
        }
    };

    @NotNull
    private final TextColumn<RESTContentSpecCollectionItemV1> titleColumn = new TextColumn<RESTContentSpecCollectionItemV1>() {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object == null) {
                return null + "";
            }

            final RESTCSNodeV1 retValue = ComponentContentSpecV1.returnMetaData(object.getItem(), "Title");
            return retValue == null ? null + "" : retValue.getAdditionalText();
        }
    };

    public ContentSpecFilteredResultsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.SearchResults(), PressGangCCMSUI.INSTANCE.CreateContentSpec());

        getResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ContentSpecID());
        getResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.ContentSpecTitle());
        
        /* Unlike every other results view, the topic results don't have a search button */
        this.getEntitySearch().removeFromParent();

        //addActionButton(bulkImport);
        //addActionButton(bulkOverwrite);
    }

    @NotNull
    public PushButton getBulkImport() {
        return bulkImport;
    }

    @NotNull
    public PushButton getBulkOverwrite() {
        return bulkOverwrite;
    }
}
