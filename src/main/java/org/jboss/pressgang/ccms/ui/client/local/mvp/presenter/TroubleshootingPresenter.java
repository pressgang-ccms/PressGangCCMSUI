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

import org.jboss.pressgang.ccms.ui.client.local.mvp.presenter.base.BaseTemplatePresenter;
import org.jboss.pressgang.ccms.ui.client.local.mvp.view.base.BaseTemplateViewInterface;
import org.jetbrains.annotations.NotNull;

@Dependent
public class TroubleshootingPresenter extends BaseTemplatePresenter {

    public static final String HISTORY_TOKEN = "TroubleshootingView";

    public interface Display extends BaseTemplateViewInterface {

    }

    @Inject
    private Display display;

    @Override
    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        bindExtended();
    }

    @Override
    public void close() {

    }

    public void bindExtended() {

    }

    @Override
    public void parseToken(@NotNull final String historyToken) {

    }
}