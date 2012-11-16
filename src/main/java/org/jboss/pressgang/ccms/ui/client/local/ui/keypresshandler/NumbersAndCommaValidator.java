package org.jboss.pressgang.ccms.ui.client.local.ui.keypresshandler;

import org.jboss.pressgang.ccms.ui.client.local.utilities.GWTUtilities;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.ValueBoxBase;

/**
 * Cancels and key press that is not a digit or comma, and removes any non digit or comma character on
 * value change.
 * @author Matthew Casperson
 */
public class NumbersAndCommaValidator implements KeyPressHandler, ValueChangeHandler<String> {

    private final ValueBoxBase<String> source;
    
    
    public NumbersAndCommaValidator(final ValueBoxBase<String> source)
    {
        this.source = source;
        source.addKeyPressHandler(this);
        source.addValueChangeHandler(this);
    }
    
    @Override
    public void onKeyPress(final KeyPressEvent event) {
        final char keyCode = event.getCharCode();

        /* Allow navigation keys */
        if (keyCode == KeyCodes.KEY_DELETE ||
                keyCode == KeyCodes.KEY_BACKSPACE ||
                keyCode == KeyCodes.KEY_LEFT ||
                keyCode == KeyCodes.KEY_RIGHT ||
                keyCode == KeyCodes.KEY_UP ||
                keyCode == KeyCodes.KEY_DOWN ||
                keyCode == KeyCodes.KEY_HOME ||
                keyCode == KeyCodes.KEY_END )
        {
            return;
        }
        
        /* Allow numeric keys */
        if (keyCode >= '0' && keyCode <= '9')
            return;
        
        /* Allow the comma */
        if (keyCode == ',')
            return;
        
        /* Block all others */
        source.cancelKey();
    }

    @Override
    public void onValueChange(final ValueChangeEvent<String> event) {
        source.setText(GWTUtilities.fixUpIdSearchString(source.getText()));
    }

}
