package org.jboss.pressgang.ccms.ui.client.local.ui.editor.search;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TriStatePushButton;
import org.jboss.pressgang.ccms.ui.client.local.constants.CSSConstants;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.field.SearchUIFields;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocale;
import org.jboss.pressgang.ccms.ui.client.local.ui.search.locale.SearchUILocales;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The editor that binds a list of locales and their states to buttons and labels.
 */
public class SearchUILocaleEditor extends FlexTable implements LeafValueEditor<SearchUILocales> {
    private static final int COLUMNS = 2;

    private final Map<TriStatePushButton, SearchUILocale> buttonsMap = new HashMap<TriStatePushButton, SearchUILocale>();

    @Override
    public void setValue(@Nullable final SearchUILocales value) {
        this.removeAllRows();
        buttonsMap.clear();

        if (value != null) {

            int index = 0;
            for (final SearchUILocale locale : value.getLocales()) {

                final int fixedIndex = index / COLUMNS;
                final int column = index % COLUMNS;

                final Label label = new Label(locale.getName());
                label.addStyleName(CSSConstants.TAG_LABEL);
                final TriStatePushButton button = new TriStatePushButton();

                buttonsMap.put(button, locale);

                final int row = this.getRowCount();

                this.setWidget(fixedIndex, column * 2, label);
                this.setWidget(fixedIndex, (column * 2) + 1, button);

                ++index;
            }
        }
    }

    @Override
    public SearchUILocales getValue() {
        final SearchUILocales retValue = new SearchUILocales();

        for (final TriStatePushButton button : buttonsMap.keySet()) {
            final SearchUILocale locale = buttonsMap.get(button);
            locale.setLocale(button.getState());
        }

        return retValue;
    }
}
