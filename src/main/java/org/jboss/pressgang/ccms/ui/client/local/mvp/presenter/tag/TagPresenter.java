package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.tag;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.entities.RESTTagV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.EditableView;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.TemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.tag.TagViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.BaseRestCallback;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCalls;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.tagview.RESTTagV1BasicDetailsEditor;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HasWidgets;

@Dependent
public class TagPresenter extends TemplatePresenter implements EditableView {

    public static final String HISTORY_TOKEN = "TagView";

    // Empty interface declaration, similar to UiBinder
    public interface TagPresenterDriver extends SimpleBeanEditorDriver<RESTTagV1, RESTTagV1BasicDetailsEditor> {
    }

    public interface Display extends TagViewInterface {
        @SuppressWarnings("rawtypes")
        SimpleBeanEditorDriver getDriver();
    }

    private String tagId;

    @Inject
    private Display display;

    @Override
    public void parseToken(final String searchToken) {
        tagId = searchToken.replace(HISTORY_TOKEN + ";", "");
    }

    @Override
    public void go(final HasWidgets container) {
        container.clear();
        container.add(display.getTopLevelPanel());

        getTag();

        bind();
    }

    private void getTag() {
        final RESTCalls.RESTCallback<RESTTagV1> callback = new BaseRestCallback<RESTTagV1, Display>(display,
                new BaseRestCallback.SuccessAction<RESTTagV1, Display>() {
                    @Override
                    public void doSuccessAction(RESTTagV1 retValue, Display display) {
                        display.initialize(retValue, false);
                    }
                }) {
        };
        RESTCalls.getUnexpandedTag(callback, Integer.parseInt(tagId));
    }

    private void bind() {
        super.bind(display, this);
    }

    @Override
    public boolean checkForUnsavedChanges() {
        return true;
    }
}
