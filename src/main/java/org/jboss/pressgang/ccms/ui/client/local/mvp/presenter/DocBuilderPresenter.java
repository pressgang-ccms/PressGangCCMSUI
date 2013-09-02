package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import static com.google.common.base.Preconditions.checkArgument;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.removeHistoryToken;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.HasWidgets;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTCSNodeCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTCSNodeCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.constants.CommonFilterConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCall;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Dependent
public class DocBuilderPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "DocBuilderView";

    public interface Display extends BaseTemplateViewInterface {
        void display(@Nullable final Integer id);
    }

    @Inject private FailOverRESTCall failOverRESTCall;

    @Inject
    private Display display;

    private String queryString;
    private Integer id;

    @Override
    public void go(@NotNull final HasWidgets container) {
        display.setViewShown(true);
        clearContainerAndAddTopLevelPanel(container, display);
        bindExtended();
    }

    @Override
    public void close() {
        GWTUtilities.setBrowserWindowTitle(PressGangCCMSUI.INSTANCE.PressGangCCMS());
    }

    @Override
    public void bindExtended() {
        super.bind(display);
        display.display(id);

        loadAdditionalDisplayedItemData(id);
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {
        this.queryString = removeHistoryToken(historyToken, HISTORY_TOKEN);

        try {
            id = Integer.parseInt(this.queryString);
        } catch (@NotNull final NumberFormatException ex) {
            id = null;
        }
    }

    protected void loadAdditionalDisplayedItemData(final Integer id) {
        // Make the window title display the id of the content spec
        if (id != null) {
                /*
                    Run an additional query to get the title of the spec
                 */
            failOverRESTCall.performRESTCall(
                    FailOverRESTCallDatabase.getCSNodesWithFromQuery("query;" +
                            CommonFilterConstants.CONTENT_SPEC_NODE_TYPE_FILTER_VAR + "=" + CommonConstants.CS_NODE_META_DATA + ";" +
                            CommonFilterConstants.CONTENT_SPEC_NODE_TITLE_FILTER_VAR + "=" + CommonConstants.CS_TITLE_TITLE
                            + ";" +
                            CommonFilterConstants.CONTENT_SPEC_IDS_FILTER_VAR + "=" + id),
                    new RESTCallBack<RESTCSNodeCollectionV1>() {
                        @Override
                        public void success(@NotNull final RESTCSNodeCollectionV1 retValue) {
                            checkArgument(retValue.getItems() != null, "The returned collection should have expanded items");

                            /*
                                The query may return title and subtitle
                             */
                            for (final RESTCSNodeCollectionItemV1 node : retValue.getItems())  {
                                if (node.getItem().getTitle().equalsIgnoreCase(CommonConstants.CS_TITLE_TITLE)) {
                                    GWTUtilities.setBrowserWindowTitle(node.getItem().getAdditionalText() + " - " +
                                                    PressGangCCMSUI.INSTANCE.PressGangCCMS());
                                    break;
                                }
                            }
                        }
                    }
            );

        }
    }
}
