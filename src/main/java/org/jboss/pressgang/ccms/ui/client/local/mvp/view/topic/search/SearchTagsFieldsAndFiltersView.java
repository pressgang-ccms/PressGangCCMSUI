package org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.search;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.topic.search.SearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This is the view that combines all the other search views.
 */
public class SearchTagsFieldsAndFiltersView extends
        BaseTemplateView implements
        SearchTagsFieldsAndFiltersPresenter.Display {

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    private final PushButton searchTopics = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Search());
    private final PushButton downloadZip = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.DownloadZip());
    private final PushButton downloadCSV = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.DownloadCSV());
    private final PushButton applyBulkTags = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.ApplyBulkTags());
    private final PushButton tagsSearch = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton filters = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Filters());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());

    private final Label tagsSearchDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Tags());
    private final Label filtersDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Filters());
    private final Label fieldsDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());
    private final Label localesDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Locales());

    public SearchTagsFieldsAndFiltersView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());

        addActionButton(searchTopics);
        addActionButton(downloadZip);
        addActionButton(downloadCSV);
        addActionButton(applyBulkTags);
        addActionButton(tagsSearch);
        addActionButton(fields);
        addActionButton(locales);
        addActionButton(filters);
    }

    @Override
    protected void showWaiting() {
        waiting.center();
        waiting.show();
    }

    @Override
    protected void hideWaiting() {
        waiting.hide();
    }

    @Override
    @NotNull
    public PushButton getSearchTopics() {
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
    public PushButton getTagsSearch() {
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
    public Label getTagsSearchDownLabel() {
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
