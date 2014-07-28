/*
  Copyright 2011-2014 Red Hat

  This file is part of PresGang CCMS.

  PresGang CCMS is free software: you can redistribute it and/or modify
  it under the terms of the GNU Lesser General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  PresGang CCMS is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with PresGang CCMS.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.jboss.pressgang.ccms.ui.client.local.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;
import org.jetbrains.annotations.NotNull;

/**
 * This is a custom button that can be databound to a SearchUIProject.CategorySummary object. This button is used to display a
 * project selection button.
 *
 * @author Matthew Casperson
 */
public final class ThreeTextAndImageButtonSearchUIProjectEditor extends ThreeTextAndImageButton implements
        IsEditor<LeafValueEditor<SearchUIProject.CategorySummary>> {
    private SearchUIProject.CategorySummary details;

    public ThreeTextAndImageButtonSearchUIProjectEditor() {

    }

    @NotNull
    @Override
    public LeafValueEditor<SearchUIProject.CategorySummary> asEditor() {
        return new LeafValueEditor<SearchUIProject.CategorySummary>() {
            @Override
            public void setValue(@NotNull final SearchUIProject.CategorySummary value) {
                details = value;

                setText(value.getName());
                setText2(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags());
                setText3(PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());

                div2.setAttribute("class", value.getIncludedTags() != 0 ? CSSConstants.Common.CUSTOM_BUTTON_TAGS_INCLUDED + ""
                        : CSSConstants.Common.CUSTOM_BUTTON_TEXT);
                div3.setAttribute("class", value.getExcludedTags() != 0 ? CSSConstants.Common.CUSTOM_BUTTON_TAGS_EXCLUDED + ""
                        : CSSConstants.Common.CUSTOM_BUTTON_TEXT);
            }

            @Override
            public SearchUIProject.CategorySummary getValue() {
                return details;
            }
        };
    }

}
