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

package org.jboss.pressgang.ccms.ui.client.local.mvp.presenter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.pressgang.ccms.rest.v1.elements.RESTSystemStatsV1;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenterInterface;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.FailOverRESTCallDatabase;
import org.jboss.pressgang.ccms.ui.client.local.restcalls.RESTCallBack;
import org.jetbrains.annotations.NotNull;

/**
 * Presenter to add logic to the system information view
 */
@Dependent
public class SysInfoPresenter extends BaseTemplatePresenter implements BaseTemplatePresenterInterface {

    public static final String HISTORY_TOKEN = "SysInfoView";

    public interface Display extends BaseTemplateViewInterface {
        void display(@NotNull final RESTSystemStatsV1 stats);
    }

    @Inject
    private Display display;

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }

    @Override
    public void bindExtended() {
        getFailOverRESTCall().performRESTCall(
                FailOverRESTCallDatabase.getSysInfo(),
                new RESTCallBack<RESTSystemStatsV1>() {
                    public void success(@NotNull final RESTSystemStatsV1 value) {
                        display.display(value);
                    }
                }, display);
    }

    @Override
    public void go() {
        display.setViewShown(true);
        bindExtended();
    }

    @Override
    public void close() {

    }
}
