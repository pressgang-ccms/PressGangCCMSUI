package org.jboss.pressgang.ccms.ui.client.local.ui.editor.topicview;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTTranslatedTopicV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

/**
 * An editor to bind the details in a RESTTranslatedTopicV1 to UI elements. This is similar but not the same
 * as RESTTopicV1BasicDetailsEditor, which has some slightly different properties being displayed.
 */
public final class RESTTranslatedTopicV1BasicDetailsEditor extends Grid implements Editor<RESTTranslatedTopicV1> {

    /**
     * The number of rows displayed by this editor.
     */
    private static final int ROWS = 4;
    /**
     * The number of columns displayed by this editor.
     */
    private static final int COLS = 2;

    /**
     * The integer text box that displays the translated topic id
     */
    private final SimpleIntegerBox id = new SimpleIntegerBox();
    /**
     * The integer text box that display the topic revision.
     */
    private final SimpleIntegerBox topicRevision = new SimpleIntegerBox();
    /**
     * The integer text box that displays the topic id
     */
    private final SimpleIntegerBox topicId = new SimpleIntegerBox();
    /**
     * The text box that displays the translation locale
     */
    private final TextBox locale = new TextBox();

    /**
     * The property used by the editor framework to bind the locale to the ui element.
     *
     * @return The locale ui element
     */
    @NotNull
    public TextBox localeEditor() {
        return locale;
    }

    /**
     * The property used by the editor framework to bind the topic id to the ui element.
     *
     * @return The topic id ui element
     */
    @NotNull
    public SimpleIntegerBox topicIdEditor() {
        return topicId;
    }

    /**
     * The property used by the editor framework to bind the topic revision to the ui element.
     *
     * @return The revision ui element
     */
    @NotNull
    public SimpleIntegerBox topicRevisionEditor() {
        return topicRevision;
    }

    /**
     * The property used by the editor framework to bind the translated topic id to the ui element.
     *
     * @return The translated topic id ui element
     */
    @NotNull
    public SimpleIntegerBox idEditor() {
        return id;
    }

    /**
     * @param readOnly true if the ui elements presented by this editor should be readonly
     */
    public RESTTranslatedTopicV1BasicDetailsEditor(final boolean readOnly) {
        super(ROWS, COLS);

        this.addStyleName(CSSConstants.TranslatedTopicView.TRANSLATED_TOPIC_VIEW_PANEL);

        topicId.setReadOnly(readOnly);
        topicRevision.setReadOnly(readOnly);
        locale.setReadOnly(readOnly);
        id.setReadOnly(true);


        int row = 0;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TranslatedTopicID()));
        this.setWidget(row, 1, id);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicID()));
        this.setWidget(row, 1, topicId);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicRevision()));
        this.setWidget(row, 1, topicRevision);

        ++row;
        this.setWidget(row, 0, new Label(PressGangCCMSUI.INSTANCE.TopicLocale()));
        this.setWidget(row, 1, locale);

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 0, CSSConstants.TopicView.TOPIC_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < ROWS; ++i) {
            this.getCellFormatter().addStyleName(i, 1, CSSConstants.TopicView.TOPIC_VIEW_DETAIL_CELL);
        }

    }
}
