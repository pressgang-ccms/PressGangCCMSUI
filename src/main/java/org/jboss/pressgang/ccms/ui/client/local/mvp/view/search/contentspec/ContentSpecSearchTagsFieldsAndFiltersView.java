package org.jboss.pressgang.ccms.ui.client.local.mvp.view.search.contentspec;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search.contentspec.ContentSpecSearchTagsFieldsAndFiltersPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.WaitingDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**
 * This is the view that combines all the other search views.
 */
public class ContentSpecSearchTagsFieldsAndFiltersView extends
        BaseTemplateView implements
        ContentSpecSearchTagsFieldsAndFiltersPresenter.Display {

    /**
     * The dialog that is presented when the view is unavailable.
     */
    private final WaitingDialog waiting = new WaitingDialog();

    private final PushButton searchContentSpecs = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.SearchContentSpecs());
    private final PushButton downloadZip = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.DownloadContentSpecZip());
    private final PushButton tags = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton fields = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Fields());
    private final PushButton locales = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Locales());

    private final Label tagsSearchDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Tags());
    private final Label fieldsDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Fields());
    private final Label localesDownLabel = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Locales());

    public ContentSpecSearchTagsFieldsAndFiltersView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.Search());

        addActionButton(searchContentSpecs);
        addActionButton(downloadZip);
        addActionButton(tags);
        addActionButton(fields);
        addActionButton(locales);
    }

    @Override
    protected void initialiseShortcuts() {
        super.initialiseShortcuts();
        getShortcuts().getSearchSubMenu().getSearchContentSpecsButton().setDown(true);
        getShortcuts().getSearchSubMenu().setOpen(true);
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
        return searchContentSpecs;
    }

    @Override
    @NotNull
    public PushButton getDownloadZip() {
        return downloadZip;
    }

    @Override
    @NotNull
    public PushButton getTagsButton() {
        return tags;
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
    public Label getFieldsDownLabel() {
        return fieldsDownLabel;
    }

    @Override
    @NotNull
    public Label getLocalesDownLabel() {
        return localesDownLabel;
    }
}
