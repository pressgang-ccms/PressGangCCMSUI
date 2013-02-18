package com.google.gwt.user.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.tag.SearchUIProject;

/**
 * This is a custom button that can be databound to a SearchUIProject.CategorySummary object. This button is used to display a
 * project selection button.
 *
 * @author Matthew Casperson
 */
public final class FourTextAndImageButtonSearchUIProjectEditor extends FourTextAndImageButton implements
        IsEditor<LeafValueEditor<SearchUIProject.CategorySummary>> {
    private SearchUIProject.CategorySummary details;

    public FourTextAndImageButtonSearchUIProjectEditor() {

    }

    @Override
    public LeafValueEditor<SearchUIProject.CategorySummary> asEditor() {
        return new LeafValueEditor<SearchUIProject.CategorySummary>() {
            @Override
            public void setValue(final SearchUIProject.CategorySummary value) {
                details = value;

                setText(value.getName());
                setText2(PressGangCCMSUI.INSTANCE.CategoryCount() + ": " + value.getCategoryCount());
                setText3(PressGangCCMSUI.INSTANCE.Included() + ": " + value.getIncludedTags());
                setText4(PressGangCCMSUI.INSTANCE.Excluded() + ": " + value.getExcludedTags());

                div3.setAttribute("class", value.getIncludedTags() != 0 ? CSSConstants.CUSTOM_BUTTON_TAGS_INCLUDED + ""
                        : CSSConstants.CUSTOM_BUTTON_TEXT);
                div4.setAttribute("class", value.getExcludedTags() != 0 ? CSSConstants.CUSTOM_BUTTON_TAGS_EXCLUDED + ""
                        : CSSConstants.CUSTOM_BUTTON_TEXT);
            }

            @Override
            public SearchUIProject.CategorySummary getValue() {
                return details;
            }
        };
    }

}
