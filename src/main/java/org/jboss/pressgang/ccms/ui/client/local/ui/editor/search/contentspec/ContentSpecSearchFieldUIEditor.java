package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.contentspec;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.ContentSpecSearchUIFields;
import org.jetbrains.annotations.NotNull;

public final class ContentSpecSearchFieldUIEditor extends BaseSearchFieldUIEditor<ContentSpecSearchUIFields> {

    /**
     * The default format for the DateBoxes
     */
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getLongDateFormat();
    /**
     * The name of the group that the matching option checkboxes belong to
     */
    private static final String MATCH_GROUP = "match";

    private final DateBox editedAfter = new DateBox();
    private final DateBox editedBefore = new DateBox();
    private final SimpleIntegerBox editedInLastXDays = new SimpleIntegerBox();
    private final SimpleIntegerBox notEditedInLastXDays = new SimpleIntegerBox();
    private final TextBox ids = new TextBox();
    private final TextBox title = new TextBox();
    private final TextBox product = new TextBox();
    private final TextBox version = new TextBox();
    private final RadioButton matchAll = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAll());
    private final RadioButton matchAny = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAny());

    private ContentSpecSearchUIFields value;

    public ContentSpecSearchFieldUIEditor() {

        @NotNull final Label contentSpecEditedAfterLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecEditedAfter());
        editedAfter.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedAfter.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, contentSpecEditedAfterLabel);
        setWidget(getRowCount() - 1, 1, editedAfter);

        @NotNull final Label contentSpecEditedBeforeLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecEditedBefore());
        editedBefore.setFormat(new DateBox.DefaultFormat(DATE_FORMAT));
        editedBefore.getTextBox().setReadOnly(true);
        setWidget(getRowCount(), 0, contentSpecEditedBeforeLabel);
        setWidget(getRowCount() - 1, 1, editedBefore);

        @NotNull final Label contentSpecsEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.ContentSpecsEditedInLastXDays());
        setWidget(getRowCount(), 0, contentSpecsEditedInLastXDaysLabels);
        setWidget(getRowCount() - 1, 1, editedInLastXDays);

        @NotNull final Label contentSpecsNotEditedInLastXDaysLabels = new Label(PressGangCCMSUI.INSTANCE.ContentSpecsNotEditedInLastXDays());
        setWidget(getRowCount(), 0, contentSpecsNotEditedInLastXDaysLabels);
        setWidget(getRowCount() - 1, 1, notEditedInLastXDays);

        @NotNull final Label contentSpecsIDLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecIds());
        setWidget(getRowCount(), 0, contentSpecsIDLabel);
        setWidget(getRowCount() - 1, 1, ids);

        @NotNull final Label contentSpecTitleLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecTitle());
        setWidget(getRowCount(), 0, contentSpecTitleLabel);
        setWidget(getRowCount() - 1, 1, title);

        @NotNull final Label contentSpecProductLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecProduct());
        setWidget(getRowCount(), 0, contentSpecProductLabel);
        setWidget(getRowCount() - 1, 1, product);

        @NotNull final Label contentSpecVersionLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecVersion());
        setWidget(getRowCount(), 0, contentSpecVersionLabel);
        setWidget(getRowCount() - 1, 1, version);

        setWidget(getRowCount(), 0, matchAll);
        setWidget(getRowCount() - 1, 1, matchAny);

        new NumbersAndCommaValidator(ids);
    }

    @Override
    public void setValue(@NotNull final ContentSpecSearchUIFields value) {
        this.value = value;

        editedAfter.setValue(value.getEditedAfter());
        editedBefore.setValue(value.getEditedBefore());
        editedInLastXDays.setValue(value.getEditedInLastXDays());
        notEditedInLastXDays.setValue(value.getNotEditedInLastXDays());
        ids.setValue(value.getIds());
        title.setValue(value.getTitle());
        product.setValue(value.getProduct());
        version.setValue(value.getVersion());
        matchAll.setValue(value.isMatchAll());
        matchAny.setValue(!value.isMatchAll());
    }

    @Override
    public ContentSpecSearchUIFields getValue() {

        value.setEditedAfter(editedAfter.getValue());
        value.setEditedBefore(editedBefore.getValue());
        value.setEditedInLastXDays(editedInLastXDays.getValue());
        value.setNotEditedInLastXDays(notEditedInLastXDays.getValue());
        value.setIds(ids.getValue());
        value.setTitle(title.getValue());
        value.setProduct(product.getValue());
        value.setVersion(version.getValue());
        value.setMatchAll(matchAll.getValue());
        value.setMatchAll(!matchAny.getValue());

        return value;
    }
}
