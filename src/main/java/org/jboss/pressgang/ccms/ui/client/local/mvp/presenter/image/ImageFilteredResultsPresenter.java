package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.image;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.collections.RESTImageCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTImageCollectionItemV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.HasData;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

@Dependent
public class ImageFilteredResultsPresenter extends TemplatePresenter implements EditableView {
    public static final String HISTORY_TOKEN = "ImageFilteredResultsView";

    public interface Display extends BaseTemplateViewInterface {
        EnhancedAsyncDataProvider<RESTImageCollectionItemV1> getProvider();

        void setProvider(final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider);

        CellTable<RESTImageCollectionItemV1> getResults();

        SimplePager getPager();

        TextBox getImageIdFilter();

        TextBox getImageDescriptionFilter();

        @Override
        PushButton getSearch();

        TextBox getImageOriginalFileNameFilter();
    }

    @Inject
    private Display display;

    private String queryString;

    /**
     * Keeps a reference to the start row.
     */
    private Integer tableStartRow;

    /**
     * Keeps a reference to the list of topics being displayed.
     */
    private List<RESTImageCollectionItemV1> currentList;

    @Override
    public void parseToken(final String searchToken) {
        queryString = removeHistoryToken(searchToken, HISTORY_TOKEN);
    }

    @Override
    public void go(final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bind();
    }

    private void bind() {
        super.bind(display, this);

        final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTImageCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTImageCollectionItemV1> item) {
                final int start = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = start + length;

                final RESTCalls.RESTCallback<RESTImageCollectionV1> callback =
                        new BaseRestCallback<RESTImageCollectionV1, Display>(display,
                                new BaseRestCallback.SuccessAction<RESTImageCollectionV1, Display>() {
                            @Override
                            public void doSuccessAction(RESTImageCollectionV1 retValue, Display display) {
                                displayNewFixedList(retValue.getItems());
                            }
                        }) {
                        };

                RESTCalls.getImagesFromQuery(callback, queryString, start, end);
            }
        };

        display.setProvider(provider);
    }

    /**
     * @return A provider to be used for the image display list.
     */
    private EnhancedAsyncDataProvider<RESTImageCollectionItemV1> generateListProvider() {
        final EnhancedAsyncDataProvider<RESTImageCollectionItemV1> provider = new EnhancedAsyncDataProvider<RESTImageCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTImageCollectionItemV1> item) {
                tableStartRow = item.getVisibleRange().getStart();
                final int length = item.getVisibleRange().getLength();
                final int end = tableStartRow + length;

                final RESTCalls.RESTCallback<RESTImageCollectionV1> callback = new RESTCalls.RESTCallback<RESTImageCollectionV1>() {
                    @Override
                    public void begin() {
                        display.addWaitOperation();
                    }

                    @Override
                    public void generalException(final Exception e) {
                        Window.alert(PressGangCCMSUI.INSTANCE.ErrorGettingTopics());
                        display.removeWaitOperation();
                    }

                    @Override
                    public void success(final RESTImageCollectionV1 retValue) {
                        try {
                            currentList = retValue.getItems();
                            displayAsynchronousList(currentList, tableStartRow, retValue.getSize());
                        } finally {
                            display.removeWaitOperation();
                        }
                    }

                    @Override
                    public void failed() {
                        display.removeWaitOperation();
                        Window.alert(PressGangCCMSUI.INSTANCE.ConnectionError());
                    }
                };

                RESTCalls.getImagesFromQuery(callback, queryString, tableStartRow, end);
            }
        };
        return provider;
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
