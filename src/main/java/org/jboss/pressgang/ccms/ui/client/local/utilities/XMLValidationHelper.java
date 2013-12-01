package org.jboss.pressgang.ccms.ui.client.local.utilities;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;

public interface XMLValidationHelper {
    AceEditor getEditor();
    String getError();
    void setError(final String errorMsg);
}
