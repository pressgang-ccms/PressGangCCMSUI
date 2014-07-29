/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.contentspec;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleIntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import org.jboss.pressgang.ccms.rest.v1.entities.enums.RESTXMLFormat;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler.NumbersAndCommaValidator;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.ContentSpecSearchUIFields;
import org.jboss.pressgang.ccms.utils.constants.CommonConstants;
import org.jetbrains.annotations.NotNull;

public final class ContentSpecSearchFieldUIEditor extends BaseSearchFieldUIEditor<ContentSpecSearchUIFields> {

    /**
     * The default format for the DateBoxes
     */
    private static final DateTimeFormat DATE_FORMAT = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_LONG);
    /**
     * The name of the group that the matching option checkboxes belong to
     */
    private static final String MATCH_GROUP = "match";

    private final DateBox editedAfter = new DateBox();
    private final DateBox editedBefore = new DateBox();
    private final SimpleIntegerBox editedInLastXDays = new SimpleIntegerBox();
    private final SimpleIntegerBox notEditedInLastXDays = new SimpleIntegerBox();
    private final TextBox createdBy = new TextBox();
    private final TextBox notCreatedBy = new TextBox();
    private final TextBox editedBy = new TextBox();
    private final TextBox notEditedBy = new TextBox();
    private final ListBox type = new ListBox();
    private final ListBox format = new ListBox();
    private final TextBox ids = new TextBox();
    private final TextBox title = new TextBox();
    private final TextBox subtitle = new TextBox();
    private final TextBox product = new TextBox();
    private final TextBox version = new TextBox();
    private final TextBox edition = new TextBox();
    private final TextBox pubsnumber = new TextBox();
    private final TextBox abstractDesc = new TextBox();
    private final TextBox brand = new TextBox();
    private final TextBox copyrightHolder = new TextBox();
    private final TextBox copyrightYear = new TextBox();
    private final TextBox publicanCfg = new TextBox();
    private final RadioButton matchAll = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAll());
    private final RadioButton matchAny = new RadioButton(MATCH_GROUP, PressGangCCMSUI.INSTANCE.MatchAny());

    private ContentSpecSearchUIFields value;

    public ContentSpecSearchFieldUIEditor() {

//        @NotNull final Label contentSpecCreatedByLabel = new Label(PressGangCCMSUI.INSTANCE.CreatedBy());
//        setWidget(getRowCount(), 0, contentSpecCreatedByLabel);
//        setWidget(getRowCount() - 1, 1, createdBy);
//
//        @NotNull final Label contentSpecNotCreatedByLabel = new Label(PressGangCCMSUI.INSTANCE.NotCreatedBy());
//        setWidget(getRowCount(), 0, contentSpecNotCreatedByLabel);
//        setWidget(getRowCount() - 1, 1, notCreatedBy);
//
//        @NotNull final Label contentSpecEditedByLabel = new Label(PressGangCCMSUI.INSTANCE.EditedBy());
//        setWidget(getRowCount(), 0, contentSpecEditedByLabel);
//        setWidget(getRowCount() - 1, 1, editedBy);
//
//        @NotNull final Label contentSpecNotEditedByLabel = new Label(PressGangCCMSUI.INSTANCE.NotEditedBy());
//        setWidget(getRowCount(), 0, contentSpecNotEditedByLabel);
//        setWidget(getRowCount() - 1, 1, notEditedBy);

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

        @NotNull final Label contentSpecTypeLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecType());
        setWidget(getRowCount(), 0, contentSpecTypeLabel);
        setWidget(getRowCount() - 1, 1, type);

        @NotNull final Label contentSpecsIDLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecIds());
        setWidget(getRowCount(), 0, contentSpecsIDLabel);
        setWidget(getRowCount() - 1, 1, ids);

        @NotNull final Label contentSpecTitleLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecTitle());
        setWidget(getRowCount(), 0, contentSpecTitleLabel);
        setWidget(getRowCount() - 1, 1, title);

        @NotNull final Label contentSpecSubtitleLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecSubtitle());
        setWidget(getRowCount(), 0, contentSpecSubtitleLabel);
        setWidget(getRowCount() - 1, 1, subtitle);

        @NotNull final Label contentSpecProductLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecProduct());
        setWidget(getRowCount(), 0, contentSpecProductLabel);
        setWidget(getRowCount() - 1, 1, product);

        @NotNull final Label contentSpecVersionLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecProductVersion());
        setWidget(getRowCount(), 0, contentSpecVersionLabel);
        setWidget(getRowCount() - 1, 1, version);

        @NotNull final Label contentSpecEditionLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecEdition());
        setWidget(getRowCount(), 0, contentSpecEditionLabel);
        setWidget(getRowCount() - 1, 1, edition);

        @NotNull final Label contentSpecPubsnumberLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecPubsnumber());
        setWidget(getRowCount(), 0, contentSpecPubsnumberLabel);
        setWidget(getRowCount() - 1, 1, pubsnumber);

        @NotNull final Label contentSpecAbstractLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecAbstract());
        setWidget(getRowCount(), 0, contentSpecAbstractLabel);
        setWidget(getRowCount() - 1, 1, abstractDesc);

        @NotNull final Label contentSpecBrandLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecBrand());
        setWidget(getRowCount(), 0, contentSpecBrandLabel);
        setWidget(getRowCount() - 1, 1, brand);

        @NotNull final Label contentSpecFormatLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecFormat());
        setWidget(getRowCount(), 0, contentSpecFormatLabel);
        setWidget(getRowCount() - 1, 1, format);

        @NotNull final Label contentSpecCopyrightHolderLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecCopyrightHolder());
        setWidget(getRowCount(), 0, contentSpecCopyrightHolderLabel);
        setWidget(getRowCount() - 1, 1, copyrightHolder);

        @NotNull final Label contentSpecCopyrightYearLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecCopyrightYear());
        setWidget(getRowCount(), 0, contentSpecCopyrightYearLabel);
        setWidget(getRowCount() - 1, 1, copyrightYear);

        @NotNull final Label contentSpecPublicanCfgLabel = new Label(PressGangCCMSUI.INSTANCE.ContentSpecPublicanCfg());
        setWidget(getRowCount(), 0, contentSpecPublicanCfgLabel);
        setWidget(getRowCount() - 1, 1, publicanCfg);

        for (int i = 0; i < getRowCount(); ++i) {
            getCellFormatter().addStyleName(i, 0, CSSConstants.FieldEditor.FIELD_VIEW_LABEL_CELL);
        }

        for (int i = 0; i < getRowCount() - 1; ++i) {
            getCellFormatter().addStyleName(i, 1, CSSConstants.FieldEditor.FIELD_VIEW_VALUE_CELL);
        }

        setWidget(getRowCount(), 0, matchAll);
        setWidget(getRowCount() - 1, 1, matchAny);

        new NumbersAndCommaValidator(ids);
        new NumbersAndCommaValidator(pubsnumber);

        type.addItem("", Integer.toString(-1));
        type.addItem("Book", Integer.toString(CommonConstants.CS_BOOK));
        type.addItem("Book Draft", Integer.toString(CommonConstants.CS_BOOK_DRAFT));
        type.addItem("Article", Integer.toString(CommonConstants.CS_ARTICLE));
        type.addItem("Article Draft", Integer.toString(CommonConstants.CS_ARTICLE_DRAFT));

        format.clear();
        format.addItem("", Integer.toString(-1));
        for (final RESTXMLFormat docType : RESTXMLFormat.values()) {
            format.addItem(docType.getCommonName(), RESTXMLFormat.getXMLFormatId(docType).toString());
        }
    }

    @Override
    public void setValue(@NotNull final ContentSpecSearchUIFields value) {
        this.value = value;

//        createdBy.setValue(value.getCreatedBy());
//        notCreatedBy.setValue(value.getNotCreatedBy());
//        editedBy.setValue(value.getEditedBy());
//        notEditedBy.setValue(value.getNotEditedBy());
        editedAfter.setValue(value.getEditedAfter());
        editedBefore.setValue(value.getEditedBefore());
        editedInLastXDays.setValue(value.getEditedInLastXDays());
        notEditedInLastXDays.setValue(value.getNotEditedInLastXDays());
        if (value.getType() == null) {
            type.setSelectedIndex(0);
        } else {
            type.setSelectedIndex(value.getType());
        }
        ids.setValue(value.getIds());
        title.setValue(value.getTitle());
        subtitle.setValue(value.getSubtitle());
        product.setValue(value.getProduct());
        version.setValue(value.getVersion());
        edition.setValue(value.getEdition());
        pubsnumber.setValue(value.getPubsnumber());
        abstractDesc.setValue(value.getAbstractDesc());
        brand.setValue(value.getBrand());
        copyrightHolder.setValue(value.getCopyrightHolder());
        copyrightYear.setValue(value.getCopyrightYear());
        publicanCfg.setValue(value.getPublicanCfg());
        matchAll.setValue(value.isMatchAll());
        matchAny.setValue(!value.isMatchAll());

        if (value.getFormat() == null) {
            format.setSelectedIndex(0);
        } else {
            final RESTXMLFormat format = RESTXMLFormat.getXMLFormat(value.getFormat());
            this.format.setSelectedIndex(format.ordinal() + 1);
        }
    }

    @Override
    public ContentSpecSearchUIFields getValue() {

//        value.setCreatedBy(createdBy.getValue());
//        value.setNotCreatedBy(notCreatedBy.getValue());
//        value.setEditedBy(editedBy.getValue());
//        value.setNotEditedBy(notEditedBy.getValue());
        value.setEditedAfter(editedAfter.getValue());
        value.setEditedBefore(editedBefore.getValue());
        value.setEditedInLastXDays(editedInLastXDays.getValue());
        value.setNotEditedInLastXDays(notEditedInLastXDays.getValue());
        final Integer typeValue = Integer.valueOf(type.getValue(type.getSelectedIndex()));
        value.setType(typeValue == -1 ? null : typeValue);
        value.setIds(ids.getValue());
        value.setTitle(title.getValue());
        value.setSubtitle(subtitle.getValue());
        value.setProduct(product.getValue());
        value.setVersion(version.getValue());
        value.setEdition(edition.getValue());
        value.setPubsnumber(pubsnumber.getValue());
        value.setAbstractDesc(abstractDesc.getValue());
        value.setBrand(brand.getValue());
        value.setCopyrightHolder(copyrightHolder.getValue());
        value.setCopyrightYear(copyrightYear.getValue());
        value.setPublicanCfg(publicanCfg.getValue());
        value.setMatchAll(matchAll.getValue());
        value.setMatchAll(!matchAny.getValue());
        final Integer formatValue = Integer.valueOf(format.getValue(format.getSelectedIndex()));
        value.setFormat(formatValue == -1 ? null : formatValue);

        return value;
    }
}
