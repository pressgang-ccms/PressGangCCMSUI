package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.ServiceConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenComponent;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.EnhancedAsyncDataProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1.REMOVE_STATE;
import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseCollectionItemV1.UNCHANGED_STATE;
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

        /**
         * @return The column that holds opens the URL
         */
        Column<RESTTopicSourceUrlCollectionItemV1, String> getOpenUrlColumn();

        /**
         * @return The button used to add a source URL
         */
        PushButton getAdd();
    }

    /**
     * History token.
     */
    public static final String HISTORY_TOKEN = "TopicSourceURLView";

    /**
     * A Logger
     */
    private static final Logger LOGGER = Logger.getLogger(TopicSourceURLsPresenter.class.getName());

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
                getPossibleChildrenProviderData().setItems(new ArrayList<RESTTopicSourceUrlCollectionItemV1>());

                if (parent != null && parent.getSourceUrls_OTM() != null) {
                    getPossibleChildrenProviderData().getItems().addAll(parent.getSourceUrls_OTM().returnExistingAddedAndUpdatedCollectionItems());
                }

                displayNewFixedList(getPossibleChildrenProviderData().getItems());
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
        super.bindChildren(helpTopicId, pageId, display);
    }

    @Override
    public final void displayChildrenExtended(@NotNull final RESTTopicV1 parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        bindPropertyTagButtons(parent);
    }

    /**
     * Add behaviour to the property tag add and remove buttons, and the value text edit field.
     */
    private void bindPropertyTagButtons(@NotNull final RESTTopicV1 parent)
    {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicSourceURLsPresenter.bindPropertyTagButtons()");

            display.getNameValueColumn().setFieldUpdater(
                    new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                            if (object.getState() == UNCHANGED_STATE) {
                                object.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                            }

                            object.getItem().explicitSetTitle(value);
                        }
                    }
            );

            display.getURLValueColumn().setFieldUpdater(new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                @Override
                public void update(final int index, final RESTTopicSourceUrlCollectionItemV1 object, final String value) {

                    if (object.getState() == UNCHANGED_STATE) {
                        object.setState(RESTBaseUpdateCollectionItemV1.UPDATE_STATE);
                    }

                    object.getItem().explicitSetUrl(value);
                }
            });

            display.getOpenUrlColumn().setFieldUpdater(
                    new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                            Window.open(object.getItem().getUrl(), "_blank", "");
                        }
                    }
            );

            display.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                @Override
                public void update(final int index, final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER Remove Column FieldUpdater.update()");

                        if (object.returnIsAddItem()) {
                            LOGGER.log(Level.INFO, "Item is new, so removing from collection");
                            parent.getSourceUrls_OTM().getItems().remove(index);
                        } else {
                            LOGGER.log(Level.INFO, "Item is existing, so marking as removed from collection");
                            object.setState(REMOVE_STATE);
                        }

                        redisplayPossibleChildList(parent);
                    } finally {
                        LOGGER.log(Level.INFO, "EXIT Remove Column FieldUpdater.update()");
                    }
                }
            });

            display.getAdd().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    final RESTTopicSourceUrlV1 newUrl = new RESTTopicSourceUrlV1();
                    newUrl.explicitSetUrl(PressGangCCMSUI.INSTANCE.NewURLLink());
                    newUrl.explicitSetTitle(PressGangCCMSUI.INSTANCE.NewURLTitle());
                    parent.getSourceUrls_OTM().addNewItem(newUrl);
                    redisplayPossibleChildList(parent);
                }
            });
        } finally {
            LOGGER.log(Level.INFO, "EXIT TopicSourceURLsPresenter.bindPropertyTagButtons()");
        }
    }
}
