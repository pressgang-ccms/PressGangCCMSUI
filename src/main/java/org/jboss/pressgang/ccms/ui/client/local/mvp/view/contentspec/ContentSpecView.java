package org.jboss.pressgang.ccms.ui.client.local.mvp.view.contentspec;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import org.jboss.pressgang.ccms.rest.v1.entities.contentspec.RESTTextContentSpecV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.contentspec.ContentSpecPresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateView;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.contentspec.RESTTextContentSpecV1TextEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.google.common.base.Preconditions.checkArgument;

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

    private final ContentSpecPresenter.ContentSpecTextPresenterDriver driver = GWT.create(ContentSpecPresenter.ContentSpecTextPresenterDriver.class);

    @Override
    @NotNull
    public AceEditor getEditor() {
        return editor.text;
    }

    public ContentSpecView() {
        super(PressGangCCMSUI.INSTANCE.PressGangCCMS(), PressGangCCMSUI.INSTANCE.ContentSpecText());
    }

    public void display(@Nullable final RESTTextContentSpecV1 contentSpec, final boolean readOnly) {
        checkArgument(contentSpec.getText() != null, "Content spec is expected to have some text");

        this.editor = new RESTTextContentSpecV1TextEditor(readOnly);
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
