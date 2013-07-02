package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.RESTContentSpecCollectionV1;
import org.jboss.pressgang.ccms.rest.v1.collections.contentspec.items.RESTContentSpecCollectionItemV1;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecFilteredResultsAndDetailsPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.searchandedit.BaseSearchAndEditView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.topic.LogMessageView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jetbrains.annotations.NotNull;

/**

 */
public class ContentSpecFilteredResultsAndDetailsView extends
        BaseSearchAndEditView<RESTContentSpecV1, RESTContentSpecCollectionV1, RESTContentSpecCollectionItemV1> implements
        ContentSpecFilteredResultsAndDetailsPresenter.Display {

    private final PushButton save = UIUtilities.createPushButton(PressGangCCMSUI.INSTANCE.Save());
    private final PushButton extendedProperties = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final PushButton details = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ContentSpecDetails());
    private final PushButton text = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.ContentSpecText());
    private final PushButton history = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Revisions());
    private final PushButton tags = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.Tags());
    private final PushButton errors = UIUtilities.createTopTabPushButton(PressGangCCMSUI.INSTANCE.SpecValidationErrors());

    private final Label extendedPropertiesDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ExtendedProperties());
    private final Label detailsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ContentSpecDetails());
    private final Label textDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.ContentSpecText());
    private final Label historyDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Revisions());
    private final Label tagsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.Tags());
    private final Label errorsDown = UIUtilities.createTopTabDownLabel(PressGangCCMSUI.INSTANCE.SpecValidationErrors());

    /**
     * An instance of the message log dialog box
     */
    private final LogMessageView messageLogDialog = new LogMessageView();

    @NotNull
    @Override
    public PushButton getText() {
        return text;
    }


    @NotNull
    @Override
    public PushButton getErrors() {
        return errors;
    }

    @NotNull
    @Override
    public PushButton getDetails() {
        return details;
    }

    @NotNull
    @Override
    public PushButton getSave() {
        return save;
    }

    @NotNull
    @Override
    public Label getTextDown() {
        return textDown;
    }

    @NotNull
    @Override
    public Label getErrorsDown() {
        return errorsDown;
    }

    @NotNull
    @Override
    public Label getDetailsDown() {
        return detailsDown;
    }

    @NotNull
    @Override
    public LogMessageView getMessageLogDialog() {
        return messageLogDialog;
    }

    @Override
    @NotNull
    public PushButton getExtendedProperties() {
        return extendedProperties;
    }

    @Override
    @NotNull
    public Label getExtendedPropertiesDown() {
        return extendedPropertiesDown;
    }

    public ContentSpecFilteredResultsAndDetailsView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ContentSpecifications());
        populateTopActionBar();
        addSpacerToShortcutPanels();
        super.initialize(true);
    }


    private void populateTopActionBar() {
        this.addActionButton(this.getText());
        this.addActionButton(this.getErrors());
        this.addActionButton(this.getDetails());
        this.addActionButton(this.getContentSpecTags());
        this.addActionButton(this.getExtendedProperties());
        this.addActionButton(this.getHistory());
        this.addActionButton(this.getSave());
    }

    @Override
    @NotNull
    public PushButton getHistory() {
        return history;
    }

    @Override
    @NotNull
    public PushButton getContentSpecTags() {
        return tags;
    }

    @Override
    @NotNull
    public Label getHistoryDown() {
        return historyDown;
    }

    @Override
    @NotNull
    public Label getContentSpecTagsDown() {
        return tagsDown;
    }
}
