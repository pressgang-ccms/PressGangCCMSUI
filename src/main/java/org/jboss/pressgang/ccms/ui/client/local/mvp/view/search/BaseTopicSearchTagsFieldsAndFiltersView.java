package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.BaseTopicSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This is the view that combines all the other search views.
 */
public class BaseTopicSearchTagsFieldsAndFiltersView extends
        BaseTemplateView implements
        BaseTopicSearchTagsFieldsAndFiltersPresenter.Display {

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    private final PushButton searchTopics = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.SearchTopics());
    private final PushButton downloadZip = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.DownloadTopicZip());
    private final PushButton downloadCSV = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.DownloadTopicCSV());
    private final PushButton applyBulkTags = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.ApplyBulkTags());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());

    private final Label tagsSearchDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Tags());
    private final Label filtersDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Filters());
    private final Label fieldsDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());
    private final Label localesDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Locales());

    public BaseTopicSearchTagsFieldsAndFiltersView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());

        addActionButton(searchTopics);
        addActionButton(downloadZip);
        addActionButton(downloadCSV);
        addActionButton(applyBulkTags);
        addActionButton(tagsSearch, false, true);
        addActionButton(fields);
        addActionButton(locales);
        addActionButton(filters);
    }

    @Override
    protected void showWaiting() {
        waiting.center();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

    @Override
    @NotNull
    public PushButton getSearchButton() {
        return searchTopics;
    }

    @Override
    @NotNull
    public PushButton getDownloadZip() {
        return downloadZip;
    }

    @Override
    @NotNull
    public PushButton getDownloadCSV() {
        return downloadCSV;
    }

    @Override
    @NotNull
    public PushButton getApplyBulkTags() {
        return applyBulkTags;
    }

    @Override
    @NotNull
    public PushButton getTagsButton() {
        return tagsSearch;
    }

    @Override
    @NotNull
    public PushButton getFilters() {
        return filters;
    }

    @Override
    @NotNull
    public PushButton getFields() {
        return fields;
    }

    @Override
    @NotNull
    public PushButton getLocales() {
        return locales;
    }

    @Override
    @NotNull
    public Label getTagsButtonDownLabel() {
        return tagsSearchDownLabel;
    }

    @Override
    @NotNull
    public Label getFiltersDownLabel() {
        return filtersDownLabel;
    }

    @Override
    @NotNull
    public Label getFieldsDownLabel() {
        return fieldsDownLabel;
    }

    @Override
    @NotNull
    public Label getLocalesDownLabel() {
        return localesDownLabel;
    }
}
