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

package org.jboss.pressgang.ccms.ui.client.local.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUICategory;
import org.jetbrains.annotations.NotNull;

/**
 * This is a custom button that can be databound to a SearchUIProject.CategorySummary object. This button is used to display a
 * project selection button.
 *
 * @author Matthew Casperson
 */
public final class FourTextAndImageButtonSearchUICategoryEditor extends FourTextAndImageButton implements
        IsEditor<LeafValueEditor<SearchUICategory.TagSummary>> {
    private SearchUICategory.TagSummary details;

    @NotNull
    @Override
    public LeafValueEditor<SearchUICategory.TagSummary> asEditor() {
        return new LeafValueEditor<SearchUICategory.TagSummary>() {
            @Override
            public void setValue(@NotNull final SearchUICategory.TagSummary value) {
                details = value;

                setText(value.getName());
                setText2(PressGangCCMSUI.INSTANCE.TagCount() + ": " + value.getTagCount());
                setText3(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags());
                setText4(PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());

                div3.setAttribute("class", value.getIncludedTags() != 0 ? CSSConstants.Common.CUSTOM_BUTTON_TAGS_INCLUDED + ""
                        : CSSConstants.Common.CUSTOM_BUTTON_TEXT);
                div4.setAttribute("class", value.getExcludedTags() != 0 ? CSSConstants.Common.CUSTOM_BUTTON_TAGS_EXCLUDED + ""
                        : CSSConstants.Common.CUSTOM_BUTTON_TEXT);

            }

            @Override
            public SearchUICategory.TagSummary getValue() {
                return details;
            }
        };
    }
}
