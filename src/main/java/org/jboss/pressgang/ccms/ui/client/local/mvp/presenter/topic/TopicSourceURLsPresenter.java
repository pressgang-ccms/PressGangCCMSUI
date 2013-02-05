package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.join.RESTAssignedPropertyTagCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.join.RESTAssignedPropertyTagCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTPropertyTagV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.join.RESTAssignedPropertyTagV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.orderedchildren.BaseExtendedChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.BaseTopicViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.preferences.Preferences;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * Displays the topic's source urls.
 */
public class TopicSourceURLsPresenter extends BaseChildrenComponent<
        RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1> {

    public interface Display extends BaseExtendedChildrenViewInterface<
            RESTTopicV1, RESTTopicCollectionV1, RESTTopicCollectionItemV1,
            RESTTopicV1,
            RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1,
            RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1>,
            BaseTopicViewInterface {
        Column<RESTTopicSourceUrlCollectionItemV1, String> getURLValueColumn();
        Column<RESTTopicSourceUrlCollectionItemV1, String> getNameValueColumn();
    }

    /**
     * History token
     */
    public static final String HISTORY_TOKEN = "TopicSourceURLView";

    /**
     * The view that displays the source urls.
     */
    @Inject private Display display;

    /**
     * @return The view that displays the source urls
     */
    public Display getDisplay()
    {
        return display;
    }

    @Override
    @NotNull
    public final EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1> generatePossibleChildrenProvider(final RESTTopicV1 parent) {
        return new EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1>() {
            @Override
            protected void onRangeChanged(final HasData<RESTTopicSourceUrlCollectionItemV1> data) {

                getPossibleChildrenProviderData().setStartRow(data.getVisibleRange().getStart());

                if (getPossibleChildrenProviderData().getItems() != null) {
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
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindChildrenExtended(ServiceConstants.DEFAULT_HELP_TOPIC, HISTORY_TOKEN, new RESTTopicV1());
    }

    @Override
    public void bindChildrenExtended(final int helpTopicId, @NotNull final String pageId, @NotNull final RESTTopicV1 parent) {
        this.bindChildren(helpTopicId, pageId, parent, display);
    }
}
