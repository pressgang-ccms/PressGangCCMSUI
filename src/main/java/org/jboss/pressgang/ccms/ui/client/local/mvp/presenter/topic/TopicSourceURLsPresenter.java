package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.AddPossibleChildCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.GetExistingCollectionCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.UpdateAfterChildModfiedCallback;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * Displays the topic's source urls.
 */
@Dependent
public class TopicSourceURLsPresenter extends BaseChildrenComponent<
        RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1> {

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseChildrenViewInterface<
                RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
                RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1,
                RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1> {
        /**
         * @return The column that holds the url.
         */
        Column<RESTTopicSourceUrlCollectionItemV1, String> getURLValueColumn();

        /**
         * @return The column that holds the URL title.
         */
        Column<RESTTopicSourceUrlCollectionItemV1, String> getNameValueColumn();
    }

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "TopicSourceURLView";

    /**
     * The view that displays the source urls.
     */
    @Inject private Display display;

    /**
     * @return The view that displays the source urls
     */
    @NotNull public final Display getDisplay() {
        return display;
    }

    @Override
    @NotNull
    public final EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1> generatePossibleChildrenProvider(@Nullable final RESTTopicV1 parent) {
        return new EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicSourceUrlCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (parent != null && parent.getSourceUrls_OTM() != null && getPossibleChildrenProviderData().getItems() != null) {
                    displayNewFixedList(parent.getSourceUrls_OTM().getItems());
                } else {
                    resetProvider();
                }
            }
        };
    }

    @Override
    public final void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public final void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN);
    }

    @Override
    public final void bindChildrenExtended(final int helpTopicId, @NotNull final String pageId) {
        bindChildren(helpTopicId, pageId, display);
    }

    @Override
    public final void displayChildrenExtended(@NotNull final RESTTopicV1 parent) {
        bindPossibleChildrenListButtonClicks(
                new GetExistingCollectionCallback<RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1>() {
                    @Override
                    public RESTTopicSourceUrlCollectionV1 getExistingCollection() {
                        return parent.getSourceUrls_OTM();
                    }
                },
                new AddPossibleChildCallback<RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1>() {
                    @Override
                    public void createAndAddChild(final RESTTopicSourceUrlCollectionItemV1 copy) {
                        // Do nothing here, because the button always removed a URL
                    }
                },
                new UpdateAfterChildModfiedCallback() {
                    @Override
                    public void updateAfterChidModfied() {

                    }
                }
        );
    }
}
