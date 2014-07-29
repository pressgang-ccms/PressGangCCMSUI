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

package org.jboss.pressgang.ccms.ui.client.local.ui.editor.settings;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleIntegerLabel;
import org.jboss.pressgang.ccms.rest.v1.elements.RESTServerEntitiesV1;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jetbrains.annotations.NotNull;

public final class RESTServerEntitiesV1DetailsEditor extends FlexTable implements Editor<RESTServerEntitiesV1> {
    private final DisclosurePanel tagsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.Tags());
    private final FlexTable tagsTable = new FlexTable();
    private final DisclosurePanel categoriesPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.Categories());
    private final FlexTable categoriesTable = new FlexTable();
    private final DisclosurePanel propertyTagsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.PropertyTags());
    private final FlexTable propertyTagsTable = new FlexTable();
    private final DisclosurePanel blobConstantsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.BlobConstants());
    private final FlexTable blobConstantsTable = new FlexTable();
    private final DisclosurePanel stringConstantsPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.StringConstants());
    private final FlexTable stringConstantsTable = new FlexTable();
    private final DisclosurePanel stringConstantUITemplatesPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.StringConstantUITemplates());
    private final FlexTable stringConstantUITemplatesTable = new FlexTable();
    private final DisclosurePanel stringConstantBuildTemplatesPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE
            .StringConstantBuildTemplates());
    private final FlexTable stringConstantBuildTemplatesTable = new FlexTable();
    private final DisclosurePanel customEntitiesPanel = new DisclosurePanel(PressGangCCMSUI.INSTANCE.SettingsCustomEntities());
    private final FlexTable customEntitiesTable = new FlexTable();

    private SimpleIntegerLabel categoryTypeId = new SimpleIntegerLabel();
    private SimpleIntegerLabel categoryWriterId = new SimpleIntegerLabel();

    private SimpleIntegerLabel tagAbstractId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagAuthorGroupId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagContentSpecId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagFrozenId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagInfoId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagInternalOnlyId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagLegalNoticeId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagObsoleteId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagReviewId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagRevisionHistoryId = new SimpleIntegerLabel();
    private SimpleIntegerLabel tagTaskId = new SimpleIntegerLabel();

    private SimpleIntegerLabel propertyTagAddedById = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagBugLinksLastValidatedId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagCspIdId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagEmailId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagFirstNameId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagFixedUrlId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagOrgId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagOrgDivisionId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagOriginalFileNameId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagPressGangWebsiteId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagReadOnlyId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagSurnameId = new SimpleIntegerLabel();
    private SimpleIntegerLabel propertyTagTagStyleId = new SimpleIntegerLabel();

    private SimpleIntegerLabel blobConstantDB45DTDId = new SimpleIntegerLabel();
    private SimpleIntegerLabel blobConstantDB50RNGId = new SimpleIntegerLabel();
    private SimpleIntegerLabel blobConstantFailPenguinId = new SimpleIntegerLabel();

    private SimpleIntegerLabel stringConstantXmlFormattingId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantDocBookElementsId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantArticleId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantArticleInfoId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantAuthorGroupId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantBookId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantBookInfoId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantPomId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantPrefaceId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantPublicanCfgId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantRevisionHistoryId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantEmptyTopicId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantInvalidInjectionId = new SimpleIntegerLabel();
    private SimpleIntegerLabel stringConstantInvalidTopicId = new SimpleIntegerLabel();
    
    private SimpleIntegerLabel topicTemplateId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook45AbstractId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook45AuthorGroupId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook45InfoId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook45LegalNoticeId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook45RevisionHistoryId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook50AbstractId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook50AuthorGroupId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook50InfoId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook50LegalNoticeId = new SimpleIntegerLabel();
    private SimpleIntegerLabel topicTemplateDocBook50RevisionHistoryId = new SimpleIntegerLabel();
    private SimpleIntegerLabel contentSpecTemplateId = new SimpleIntegerLabel();

    private final RESTServerUndefinedEntitiesCollectionV1Editor customEntitiesEditor;

    @NotNull
    public SimpleIntegerLabel typeCategoryIdEditor() {
        return categoryTypeId;
    }

    @NotNull
    public SimpleIntegerLabel writerCategoryIdEditor() {
        return categoryWriterId;
    }

    @NotNull
    public SimpleIntegerLabel abstractTagIdEditor() {
        return tagAbstractId;
    }

    @NotNull
    public SimpleIntegerLabel authorGroupTagIdEditor() {
        return tagAuthorGroupId;
    }

    @NotNull
    public SimpleIntegerLabel contentSpecTagIdEditor() {
        return tagContentSpecId;
    }

    @NotNull
    public SimpleIntegerLabel frozenTagIdEditor() {
        return tagFrozenId;
    }

    @NotNull
    public SimpleIntegerLabel infoTagIdEditor() {
        return tagInfoId;
    }

    @NotNull
    public SimpleIntegerLabel internalOnlyTagIdEditor() {
        return tagInternalOnlyId;
    }

    @NotNull
    public SimpleIntegerLabel legalNoticeTagIdEditor() {
        return tagLegalNoticeId;
    }

    @NotNull
    public SimpleIntegerLabel obsoleteTagIdEditor() {
        return tagObsoleteId;
    }

    @NotNull
    public SimpleIntegerLabel reviewTagIdEditor() {
        return tagReviewId;
    }

    @NotNull
    public SimpleIntegerLabel revisionHistoryTagIdEditor() {
        return tagRevisionHistoryId;
    }

    @NotNull
    public SimpleIntegerLabel taskTagIdEditor() {
        return tagTaskId;
    }

    @NotNull
    public SimpleIntegerLabel addedByPropertyTagIdEditor() {
        return propertyTagAddedById;
    }

    @NotNull
    public SimpleIntegerLabel bugLinksLastValidatedPropertyTagIdEditor() {
        return propertyTagBugLinksLastValidatedId;
    }

    @NotNull
    public SimpleIntegerLabel cspIdPropertyTagIdEditor() {
        return propertyTagCspIdId;
    }

    @NotNull
    public SimpleIntegerLabel emailPropertyTagIdEditor() {
        return propertyTagEmailId;
    }

    @NotNull
    public SimpleIntegerLabel firstNamePropertyTagIdEditor() {
        return propertyTagFirstNameId;
    }

    @NotNull
    public SimpleIntegerLabel fixedUrlPropertyTagIdEditor() {
        return propertyTagFixedUrlId;
    }

    @NotNull
    public SimpleIntegerLabel orgPropertyTagIdEditor() {
        return propertyTagOrgId;
    }

    @NotNull
    public SimpleIntegerLabel orgDivisionPropertyTagIdEditor() {
        return propertyTagOrgDivisionId;
    }

    @NotNull
    public SimpleIntegerLabel originalFileNamePropertyTagIdEditor() {
        return propertyTagOriginalFileNameId;
    }

    @NotNull
    public SimpleIntegerLabel pressGangWebsitePropertyTagIdEditor() {
        return propertyTagPressGangWebsiteId;
    }

    @NotNull
    public SimpleIntegerLabel readOnlyPropertyTagIdEditor() {
        return propertyTagReadOnlyId;
    }

    @NotNull
    public SimpleIntegerLabel surnamePropertyTagIdEditor() {
        return propertyTagSurnameId;
    }

    @NotNull
    public SimpleIntegerLabel tagStylePropertyTagIdEditor() {
        return propertyTagTagStyleId;
    }

    @NotNull
    public SimpleIntegerLabel failPenguinBlobConstantIdEditor() {
        return blobConstantFailPenguinId;
    }

    @NotNull
    public SimpleIntegerLabel rocBook45DTDBlobConstantIdEditor() {
        return blobConstantDB45DTDId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50RNGBlobConstantIdEditor() {
        return blobConstantDB50RNGId;
    }

    @NotNull
    public SimpleIntegerLabel xmlFormattingStringConstantIdEditor() {
        return stringConstantXmlFormattingId;
    }

    @NotNull
    public SimpleIntegerLabel docBookElementsStringConstantIdEditor() {
        return stringConstantDocBookElementsId;
    }

    @NotNull
    public SimpleIntegerLabel articleStringConstantIdEditor() {
        return stringConstantArticleId;
    }

    @NotNull
    public SimpleIntegerLabel articleInfoStringConstantIdEditor() {
        return stringConstantArticleInfoId;
    }

    @NotNull
    public SimpleIntegerLabel authorGroupStringConstantIdEditor() {
        return stringConstantAuthorGroupId;
    }

    @NotNull
    public SimpleIntegerLabel bookStringConstantIdEditor() {
        return stringConstantBookId;
    }

    @NotNull
    public SimpleIntegerLabel bookInfoStringConstantIdEditor() {
        return stringConstantBookInfoId;
    }

    @NotNull
    public SimpleIntegerLabel pomStringConstantIdEditor() {
        return stringConstantPomId;
    }

    @NotNull
    public SimpleIntegerLabel prefaceStringConstantIdEditor() {
        return stringConstantPrefaceId;
    }

    @NotNull
    public SimpleIntegerLabel publicanCfgStringConstantIdEditor() {
        return stringConstantPublicanCfgId;
    }

    @NotNull
    public SimpleIntegerLabel revisionHistoryStringConstantIdEditor() {
        return stringConstantRevisionHistoryId;
    }

    @NotNull
    public SimpleIntegerLabel emptyTopicStringConstantIdEditor() {
        return stringConstantEmptyTopicId;
    }

    @NotNull
    public SimpleIntegerLabel invalidInjectionStringConstantIdEditor() {
        return stringConstantInvalidInjectionId;
    }

    @NotNull
    public SimpleIntegerLabel invalidTopicStringConstantIdEditor() {
        return stringConstantInvalidTopicId;
    }

    @NotNull
    public SimpleIntegerLabel topicTemplateIdEditor() {
        return topicTemplateId;
    }

    @NotNull
    public SimpleIntegerLabel contentSpecTemplateIdEditor() {
        return contentSpecTemplateId;
    }

    @NotNull
    public SimpleIntegerLabel docBook45AbstractTopicTemplateIdEditor() {
        return topicTemplateDocBook45AbstractId;
    }

    @NotNull
    public SimpleIntegerLabel docBook45AuthorGroupTopicTemplateIdEditor() {
        return topicTemplateDocBook45AuthorGroupId;
    }

    @NotNull
    public SimpleIntegerLabel docBook45InfoTopicTemplateIdEditor() {
        return topicTemplateDocBook45InfoId;
    }

    @NotNull
    public SimpleIntegerLabel docBook45LegalNoticeTopicTemplateIdEditor() {
        return topicTemplateDocBook45LegalNoticeId;
    }

    @NotNull
    public SimpleIntegerLabel docBook45RevisionHistoryTopicTemplateIdEditor() {
        return topicTemplateDocBook45RevisionHistoryId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50AbstractTopicTemplateIdEditor() {
        return topicTemplateDocBook50AbstractId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50AuthorGroupTopicTemplateIdEditor() {
        return topicTemplateDocBook50AuthorGroupId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50InfoTopicTemplateIdEditor() {
        return topicTemplateDocBook50InfoId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50LegalNoticeTopicTemplateIdEditor() {
        return topicTemplateDocBook50LegalNoticeId;
    }

    @NotNull
    public SimpleIntegerLabel docBook50RevisionHistoryTopicTemplateIdEditor() {
        return topicTemplateDocBook50RevisionHistoryId;
    }

    @NotNull
    public RESTServerUndefinedEntitiesCollectionV1Editor undefinedEntitiesEditor() {
        return customEntitiesEditor;
    }

    public RESTServerEntitiesV1DetailsEditor(final boolean readOnly) {
        customEntitiesEditor = new RESTServerUndefinedEntitiesCollectionV1Editor(readOnly);


        int row = 0;
        setWidget(row, 0, categoriesPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, tagsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, propertyTagsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, blobConstantsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, stringConstantsPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, stringConstantUITemplatesPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, stringConstantBuildTemplatesPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        row++;
        setWidget(row, 0, customEntitiesPanel);
        getFlexCellFormatter().setColSpan(row, 0, 2);

        setupCategories();
        setupTags();
        setupPropertyTags();
        setupBlobConstants();
        setupStringConstants();
        setupStringConstantIUITemplates();
        setupStringConstantIBuildTemplates();

        customEntitiesPanel.setAnimationEnabled(true);
        customEntitiesPanel.setOpen(false);
        customEntitiesPanel.add(customEntitiesTable);
        customEntitiesTable.setWidget(0, 0, customEntitiesEditor);

        // Add the styling
        customEntitiesPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        customEntitiesTable.setWidth("100%");

        addStyleName(CSSConstants.SettingsView.SETTINGS_TABLE);
    }

    protected void setupCategories() {
        categoriesPanel.setAnimationEnabled(true);
        categoriesPanel.setOpen(true);
        categoriesPanel.add(categoriesTable);

        categoriesPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        categoriesTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        categoriesTable.setWidget(row, 0, createCellPanel("Type", categoryTypeId));
        categoriesTable.setWidget(row, 1, createCellPanel("Writer", categoryWriterId));
    }

    protected void setupTags() {
        tagsPanel.setAnimationEnabled(true);
        tagsPanel.setOpen(true);
        tagsPanel.add(tagsTable);

        tagsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        tagsTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        tagsTable.setWidget(row, 0, createCellPanel("Abstract", tagAbstractId));
        tagsTable.setWidget(row, 1, createCellPanel("Author Group", tagAuthorGroupId));

        row++;
        tagsTable.setWidget(row, 0, createCellPanel("Content Spec", tagContentSpecId));
        tagsTable.setWidget(row, 1, createCellPanel("Frozen", tagFrozenId));

        row++;
        tagsTable.setWidget(row, 0, createCellPanel("Info", tagInfoId));
        tagsTable.setWidget(row, 1, createCellPanel("Internal Only", tagInternalOnlyId));

        row++;
        tagsTable.setWidget(row, 0, createCellPanel("Legal Notice", tagLegalNoticeId));
        tagsTable.setWidget(row, 1, createCellPanel("Obsolete", tagObsoleteId));

        row++;
        tagsTable.setWidget(row, 0, createCellPanel("Review", tagReviewId));
        tagsTable.setWidget(row, 1, createCellPanel("Revision History", tagRevisionHistoryId));

        row++;
        tagsTable.setWidget(row, 0, createCellPanel("Task", tagTaskId));
    }

    protected void setupPropertyTags() {
        propertyTagsPanel.setAnimationEnabled(true);
        propertyTagsPanel.setOpen(true);
        propertyTagsPanel.add(propertyTagsTable);

        propertyTagsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        propertyTagsTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        propertyTagsTable.setWidget(row, 0, createCellPanel("Added By", propertyTagAddedById));
        propertyTagsTable.setWidget(row, 1, createCellPanel("Bug Links Last Validated", propertyTagBugLinksLastValidatedId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("CSP ID", propertyTagCspIdId));
        propertyTagsTable.setWidget(row, 1, createCellPanel("Email", propertyTagEmailId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("First Name", propertyTagFirstNameId));
        propertyTagsTable.setWidget(row, 1, createCellPanel("Fixed URL", propertyTagFixedUrlId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("Organization", propertyTagOrgId));
        propertyTagsTable.setWidget(row, 1, createCellPanel("Organization Division", propertyTagOrgDivisionId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("Original File Name", propertyTagOriginalFileNameId));
        propertyTagsTable.setWidget(row, 1, createCellPanel("PressGang Website", propertyTagPressGangWebsiteId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("Read Only", propertyTagReadOnlyId));
        propertyTagsTable.setWidget(row, 1, createCellPanel("Surname", propertyTagSurnameId));

        row++;
        propertyTagsTable.setWidget(row, 0, createCellPanel("Tag Style", propertyTagTagStyleId));
    }

    protected void setupBlobConstants() {
        blobConstantsPanel.setAnimationEnabled(true);
        blobConstantsPanel.setOpen(true);
        blobConstantsPanel.add(blobConstantsTable);

        blobConstantsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        blobConstantsTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        blobConstantsTable.setWidget(row, 0, createCellPanel("DocBook 4.5 DTD", blobConstantDB45DTDId));
        blobConstantsTable.setWidget(row, 1, createCellPanel("Fail Penguin Image", blobConstantFailPenguinId));

        row++;
        blobConstantsTable.setWidget(row, 0, createCellPanel("DocBook 5.0 RNG", blobConstantDB50RNGId));
    }

    protected void setupStringConstants() {
        stringConstantsPanel.setAnimationEnabled(true);
        stringConstantsPanel.setOpen(true);
        stringConstantsPanel.add(stringConstantsTable);

        stringConstantsPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        stringConstantsTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        stringConstantsTable.setWidget(row, 0, createCellPanel("XML Formatting Rules", stringConstantXmlFormattingId));
        stringConstantsTable.setWidget(row, 1, createCellPanel("DocBook Elements", stringConstantDocBookElementsId));
    }

    protected void setupStringConstantIUITemplates() {
        stringConstantUITemplatesPanel.setAnimationEnabled(true);
        stringConstantUITemplatesPanel.setOpen(true);
        stringConstantUITemplatesPanel.add(stringConstantUITemplatesTable);

        stringConstantUITemplatesPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        stringConstantUITemplatesTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        stringConstantUITemplatesTable.setWidget(row, 0, createCellPanel("Topic Template", topicTemplateId));
        stringConstantUITemplatesTable.setWidget(row, 1, createCellPanel("Content Spec Template", contentSpecTemplateId));

        row++;
        stringConstantUITemplatesTable.setWidget(row, 0, createCellPanel("Abstract DocBook 4.5 Template", topicTemplateDocBook45AbstractId));
        stringConstantUITemplatesTable.setWidget(row, 1, createCellPanel("Abstract DocBook 5.0 Template",
                topicTemplateDocBook50AbstractId));

        row++;
        stringConstantUITemplatesTable.setWidget(row, 0,
                createCellPanel("Author Group DocBook 4.5 Template", topicTemplateDocBook45AuthorGroupId));
        stringConstantUITemplatesTable.setWidget(row, 1,
                createCellPanel("Author Group DocBook 5.0 Template", topicTemplateDocBook50AuthorGroupId));

        row++;
        stringConstantUITemplatesTable.setWidget(row, 0, createCellPanel("Info DocBook 4.5 Template", topicTemplateDocBook45InfoId));
        stringConstantUITemplatesTable.setWidget(row, 1, createCellPanel("Info DocBook 5.0 Template", topicTemplateDocBook50InfoId));

        row++;
        stringConstantUITemplatesTable.setWidget(row, 0,
                createCellPanel("Legal Notice DocBook 4.5 Template", topicTemplateDocBook45LegalNoticeId));
        stringConstantUITemplatesTable.setWidget(row, 1,
                createCellPanel("Legal Notice DocBook 5.0 Template", topicTemplateDocBook50LegalNoticeId));

        row++;
        stringConstantUITemplatesTable.setWidget(row, 0,
                createCellPanel("Revision History DocBook 4.5 Template", topicTemplateDocBook45RevisionHistoryId));
        stringConstantUITemplatesTable.setWidget(row, 1,
                createCellPanel("Revision History DocBook 5.0 Template", topicTemplateDocBook50RevisionHistoryId));
    }

    protected void setupStringConstantIBuildTemplates() {
        stringConstantBuildTemplatesPanel.setAnimationEnabled(true);
        stringConstantBuildTemplatesPanel.setOpen(true);
        stringConstantBuildTemplatesPanel.add(stringConstantBuildTemplatesTable);

        stringConstantBuildTemplatesPanel.addStyleName(CSSConstants.SettingsView.DISCLOSURE_PANEL);
        stringConstantBuildTemplatesTable.addStyleName(CSSConstants.SettingsView.ENTITIES_TABLE);

        int row = 0;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Article Template", stringConstantArticleId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("Article Info Template", stringConstantArticleInfoId));

        row++;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Author Group Template", stringConstantAuthorGroupId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("Book Template", stringConstantBookId));

        row++;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Book Info Template", stringConstantBookInfoId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("POM Template", stringConstantPomId));

        row++;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Preface Template", stringConstantPrefaceId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("publican.cfg Template", stringConstantPublicanCfgId));

        row++;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Revision History Template", stringConstantRevisionHistoryId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("Empty Topic Template", stringConstantEmptyTopicId));

        row++;
        stringConstantBuildTemplatesTable.setWidget(row, 0, createCellPanel("Invalid Injection Template",
                stringConstantInvalidInjectionId));
        stringConstantBuildTemplatesTable.setWidget(row, 1, createCellPanel("Invalid Topic Template", stringConstantInvalidTopicId));
    }
    
    private Grid createCellPanel(final String title, final SimpleIntegerLabel value) {
        final Grid panel = new Grid(1, 2);
        panel.setWidget(0, 0, new Label(title));
        panel.setWidget(0, 1, value);
        panel.getCellFormatter().addStyleName(0, 0, CSSConstants.SettingsView.ENTITIES_LABEL);
        panel.getCellFormatter().addStyleName(0, 1, CSSConstants.SettingsView.ENTITIES_FIELD);
        return panel;
    }
}