package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic;

import javax.enterprise.context.Dependent;
import java.util.logging.Logger;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.DisableableButtonCell;
import com.google.gwt.user.client.ui.Image;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.components.ComponentContentSpecV1;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTopicV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTCSNodeV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.TopicContentSpecsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.children.BaseChildrenView;
import org.jboss.pressgang.ccms.ui.client.local.resources.images.ImageResources;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view that displays the source urls.
 */
@Dependent
public class TopicContentSpecsView extends BaseChildrenView<RESTTopicV1, RESTContentSpecCollectionItemV1, RESTContentSpecV1,
        RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> implements TopicContentSpecsPresenter.Display {

    private static final Logger LOGGER = Logger.getLogger(TopicContentSpecsView.class.getName());

    /**
     * The image to display in the waiting dialog.
     */
    private final Image spinner = new Image(ImageResources.INSTANCE.spinner());

    private final Column<RESTContentSpecCollectionItemV1, String> docbuilderColumn = new Column<RESTContentSpecCollectionItemV1, String>(new ButtonCell()) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.DocBuilder();
        }
    };

    /**
     * The column to display the content spec id.
     */
    private final TextCell idTextCell = new TextCell();

    private final Column<RESTContentSpecCollectionItemV1, String> idColumn = new Column<RESTContentSpecCollectionItemV1, String>(
            idTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object != null && object.getItem() != null && object.getItem().getId() != null) {
                return object.getItem().getId().toString();
            }
            return "";
        }
    };

    /**
     * The column to display the content spec title.
     */
    private final TextCell titleTextCell = new TextCell();

    private final Column<RESTContentSpecCollectionItemV1, String> titleColumn = new Column<RESTContentSpecCollectionItemV1, String>(
            titleTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                final RESTCSNodeV1 titleNode = ComponentContentSpecV1.returnMetaData(object.getItem(), "Title");
                if (titleNode != null && titleNode.getAdditionalText() != null) {
                    return titleNode.getAdditionalText();
                }
            }
            return "";
        }
    };

    /**
     * The column to display the content spec product.
     */
    private final TextCell productTextCell = new TextCell();

    private final Column<RESTContentSpecCollectionItemV1, String> productColumn = new Column<RESTContentSpecCollectionItemV1, String>(
            productTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                final RESTCSNodeV1 productNode = ComponentContentSpecV1.returnMetaData(object.getItem(), "Product");
                if (productNode != null && productNode.getAdditionalText() != null) {
                    return productNode.getAdditionalText();
                }
            }
            return "";
        }
    };

    /**
     * The column to display the content spec version.
     */
    private final TextCell versionTextCell = new TextCell();

    private final Column<RESTContentSpecCollectionItemV1, String> versionColumn = new Column<RESTContentSpecCollectionItemV1, String>(
            versionTextCell) {
        @Override
        @NotNull
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            if (object != null && object.getItem() != null) {
                final RESTCSNodeV1 versionNode = ComponentContentSpecV1.returnMetaData(object.getItem(), "Version");
                if (versionNode != null && versionNode.getAdditionalText() != null) {
                    return versionNode.getAdditionalText();
                }
            }
            return "";
        }
    };

    /**
     * The column that provides the view button.
     */
    private final DisableableButtonCell viewButtonCell = new DisableableButtonCell();
    private final Column<RESTContentSpecCollectionItemV1, String> viewButtonColumn = new Column<RESTContentSpecCollectionItemV1, String>(viewButtonCell) {
        @NotNull
        @Override
        public String getValue(@Nullable final RESTContentSpecCollectionItemV1 object) {
            return PressGangCCMSUI.INSTANCE.View();
        }
    };

    /**
     * Constructor. Sets the page and title name.
     */
    public TopicContentSpecsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.TopicContentSpecs());

        LOGGER.info("ENTER TopicContentSpecsView()");

        getPossibleChildrenResults().addColumn(idColumn, PressGangCCMSUI.INSTANCE.ContentSpecID());
        getPossibleChildrenResults().addColumn(titleColumn, PressGangCCMSUI.INSTANCE.ContentSpecTitle());
        getPossibleChildrenResults().addColumn(productColumn, PressGangCCMSUI.INSTANCE.ContentSpecProduct());
        getPossibleChildrenResults().addColumn(versionColumn, PressGangCCMSUI.INSTANCE.ContentSpecProductVersion());
        getPossibleChildrenResults().addColumn(viewButtonColumn, "");
        getPossibleChildrenResults().addColumn(docbuilderColumn, PressGangCCMSUI.INSTANCE.DocBuilder());

        idColumn.setSortable(true);
        titleColumn.setSortable(true);
        productColumn.setSortable(true);
        versionColumn.setSortable(true);

        spinner.addStyleName(CSSConstants.TopicView.TOPIC_CONTENT_SPEC_VIEW_SPINNER);

        /*
            Allow the table to be sorted.
         */
        final ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(getPossibleChildrenResults());
        getPossibleChildrenResults().addColumnSortHandler(columnSortHandler);

        getPossibleChildrenResultsPanel().addStyleName(CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_PANEL);
        getPossibleChildrenResults().addStyleName(CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_RESULTS);

        getPossibleChildrenResults().addColumnStyleName(0, CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_ID_COLUMN);
        getPossibleChildrenResults().addColumnStyleName(1, CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_TITLE_COLUMN);
        getPossibleChildrenResults().addColumnStyleName(2, CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_PRODUCT_COLUMN);
        getPossibleChildrenResults().addColumnStyleName(3, CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_VERSION_COLUMN);
        getPossibleChildrenResults().addColumnStyleName(4, CSSConstants.TopicContentSpecView.TOPIC_CONTENT_SPEC_VIEW_BUTTON_COLUMN);
    }

    @Override
    public void display(@NotNull final RESTTopicV1 topic, final boolean readOnly) {
        super.displayChildren(topic, readOnly);
    }

    @NotNull
    @Override
    public Column<RESTContentSpecCollectionItemV1, String> getIdColumn() {
        return idColumn;
    }

    @NotNull
    @Override
    public Column<RESTContentSpecCollectionItemV1, String> getTitleColumn() {
        return titleColumn;
    }

    @NotNull
    @Override
    public Column<RESTContentSpecCollectionItemV1, String> getProductColumn() {
        return productColumn;
    }


    @NotNull
    @Override
    public Column<RESTContentSpecCollectionItemV1, String> getVersionColumn() {
        return versionColumn;
    }

    @NotNull
    @Override
    public Column<RESTContentSpecCollectionItemV1, String> getPossibleChildrenButtonColumn() {
        return viewButtonColumn;
    }

    @Override
    protected void showWaiting() {
        this.getPanel().setWidget(spinner);
    }

    @Override
    protected void hideWaiting() {
        this.getPanel().setWidget(getPossibleChildrenResultsPanel());
    }

    public Column<RESTContentSpecCollectionItemV1, String> getDocbuilderColumn() {
        return docbuilderColumn;
    }
}
