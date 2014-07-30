/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import static com.google.common.base.Preconditions.checkArgument;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.PushButton;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.data.XMLElementDBLoader;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.EditorSettingsDialog;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.UIUtilities;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTTextContentSpecV1TextEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The view that displays the text content of a content spec
 */
public class ContentSpecView extends BaseTemplateView implements ContentSpecPresenter.Display {

    /**
     * Ace builds from the 17th December 2012 and prior use absolute positioning, and require
     * that the AceEditor being constructed with true. After the 17th December 2012 the ACE
     * editor uses relative positioning, and the AceEditor needs to be constructed with false.
     */
    public RESTTextContentSpecV1TextEditor editor;

    private final EditorSettingsDialog editorSettingsDialog = new EditorSettingsDialog();
    private final PushButton settings = UIUtilities.createTopPushButton(PressGangCCMSUI.INSTANCE.EditorSettings());

    private final ContentSpecPresenter.ContentSpecTextPresenterDriver driver = GWT.create(ContentSpecPresenter.ContentSpecTextPresenterDriver.class);

    @Inject
    private XMLElementDBLoader xmlElementDBLoader;

    @Override
    @Nullable
    public AceEditor getEditor() {
        if (editor != null)
            return editor.text;
        return null;
    }

    @NotNull
    @Override
    public EditorSettingsDialog getEditorSettingsDialog() {
        return editorSettingsDialog;
    }

    @NotNull
    @Override
    public PushButton getEditorSettings() {
        return settings;
    }

    public ContentSpecView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ContentSpecText());

        addLocalActionButton(this.settings);
    }

    public void display(@Nullable final RESTTextContentSpecV1 contentSpec, final boolean readOnly) {
        checkArgument(contentSpec.getText() != null, "Content spec is expected to have some text");

        this.editor = new RESTTextContentSpecV1TextEditor(readOnly, xmlElementDBLoader);
        /* Initialize the driver with the top-level editor */
        this.driver.initialize(this.editor);
        /* Copy the data in the object into the UI */
        this.driver.edit(contentSpec);
        /* Add the editor */
        this.getPanel().setWidget(this.editor);
    }

    @Override
    public SimpleBeanEditorDriver<RESTTextContentSpecV1, RESTTextContentSpecV1TextEditor> getDriver() {
        return driver;
    }
}
