package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.search;

import com.google.gwt.user.client.ui.PushButton;
import org.jboss.pressgang.ccms.rest.v1.entities.RESTFilterV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BasePopulatedEditorViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.ui.editor.search.base.BaseSearchFieldUIEditor;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.base.BaseSearchUIFields;
import org.jetbrains.annotations.NotNull;

public abstract class BaseSearchFieldPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public interface Display<T extends BaseSearchUIFields, U extends BaseSearchFieldUIEditor<T>> extends BaseTemplateViewInterface,
            BasePopulatedEditorViewInterface<RESTFilterV1, T, U> {
        PushButton getSearchButton();

        PushButton getTagsButton();

        T getFields();

        PushButton getLocales();

        PushButton getFiltersButton();
    }

    public abstract Display getDisplay();

    @Override
    public void bindExtended() {
        bind(getDisplay());
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}
