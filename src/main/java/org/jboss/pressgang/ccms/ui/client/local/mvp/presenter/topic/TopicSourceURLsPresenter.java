package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.view.client.HasData;
import org.jboss.pressgang.ccms.rest.v1.collections.RESTTopicSourceUrlCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityUpdateCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.collections.items.RESTTopicSourceUrlCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicSourceUrlV1;
import org.jboss.pressgang.ccms.rest.v1.entities.base.RESTBaseTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.children.BaseChildrenPresenter;
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

import static com.google.common.base.Preconditions.checkState;
import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1.REMOVE_STATE;
import static org.jboss.pressgang.ccms.rest.v1.collections.base.RESTBaseEntityCollectionItemV1.UNCHANGED_STATE;
import static org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities.clearContainerAndAddTopLevelPanel;

/**
 * Displays the topic's source urls.
 */
@Dependent
public class TopicSourceURLsPresenter extends BaseChildrenPresenter<
        RESTBaseTopicV1<?, ?, ?>,
        RESTTopicSourceUrlCollectionItemV1,
        RESTTopicSourceUrlV1, RESTTopicSourceUrlCollectionV1, RESTTopicSourceUrlCollectionItemV1> {

    /**
     * The interface that defines the display used by this presenter.
     */
    public interface Display extends BaseChildrenViewInterface<
            RESTBaseTopicV1<?, ?, ?>,
            RESTTopicSourceUrlCollectionItemV1,
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
        @NotNull
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
    @Inject
    private Display display;

    /**
     * @return The view that displays the source urls
     */
    @NotNull
    public Display getDisplay() {
        return display;
    }

    @Override
    @NotNull
    public EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1> generatePossibleChildrenProvider(@Nullable final RESTBaseTopicV1<?, ?, ?> parent) {
        return new EnhancedAsyncDataProvider<RESTTopicSourceUrlCollectionItemV1>() {
            @Override
            protected void onRangeChanged(@NotNull final HasData<RESTTopicSourceUrlCollectionItemV1> data) {

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
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void go(@NotNull final HasWidgets container) {
        clearContainerAndAddTopLevelPanel(container, display);
        bindChildrenExtended();
    }

    @Override
    public void close() {

    }

    @Override
    public void bindChildrenExtended() {
        super.bindChildren(display);
    }

    @Override
    public void displayChildrenExtended(@NotNull final RESTBaseTopicV1<?, ?, ?> parent, final boolean readOnly) {
        super.displayChildren(parent, readOnly);
        bindPropertyTagButtons(parent);
    }

    /**
     * Add behaviour to the property tag add and remove buttons, and the value text edit field.
     */
    private void bindPropertyTagButtons(@NotNull final RESTBaseTopicV1<?, ?, ?> parent) {
        try {
            LOGGER.log(Level.INFO, "ENTER TopicSourceURLsPresenter.bindPropertyTagButtons()");

            display.getNameValueColumn().setFieldUpdater(
                    new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                            if (UNCHANGED_STATE.equals(object.getState())) {
                                object.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                            }

                            object.getItem().explicitSetTitle(value);
                        }
                    }
            );

            display.getURLValueColumn().setFieldUpdater(new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicSourceUrlCollectionItemV1 object, final String value) {

                    if (UNCHANGED_STATE.equals(object.getState())) {
                        object.setState(RESTBaseEntityUpdateCollectionItemV1.UPDATE_STATE);
                    }

                    object.getItem().explicitSetUrl(value);
                }
            });

            display.getOpenUrlColumn().setFieldUpdater(
                    new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                        @Override
                        public void update(final int index, @NotNull final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                            Window.open(object.getItem().getUrl(), "_blank", "");
                        }
                    }
            );

            display.getPossibleChildrenButtonColumn().setFieldUpdater(new FieldUpdater<RESTTopicSourceUrlCollectionItemV1, String>() {
                @Override
                public void update(final int index, @NotNull final RESTTopicSourceUrlCollectionItemV1 object, final String value) {
                    try {
                        LOGGER.log(Level.INFO, "ENTER Remove Column FieldUpdater.update()");

                        if (object.returnIsAddItem()) {
                            LOGGER.log(Level.INFO, "Item is new, so removing from collection");

                            checkState(parent.getSourceUrls_OTM() != null, "The parent should have a collection of source urls");
                            checkState(parent.getSourceUrls_OTM().getItems() != null, "The parent's collection of source urls should have valid items");

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
                public void onClick(@NotNull final ClickEvent event) {
                    @NotNull final RESTTopicSourceUrlV1 newUrl = new RESTTopicSourceUrlV1();
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
