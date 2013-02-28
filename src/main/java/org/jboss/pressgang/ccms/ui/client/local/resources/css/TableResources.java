package org.jboss.pressgang.ccms.ui.client.local.resources.css;

import com.google.gwt.user.cellview.client.CellTable;
import org.jetbrains.annotations.NotNull;

public interface TableResources extends CellTable.Resources {
    /**
     * The styles applied to the table.
     */
    interface TableStyle extends CellTable.Style {

    }

    @NotNull
    @Override
    @Source({CellTable.Style.DEFAULT_CSS, "CustomCellTable.css"})
    TableStyle cellTableStyle();
}