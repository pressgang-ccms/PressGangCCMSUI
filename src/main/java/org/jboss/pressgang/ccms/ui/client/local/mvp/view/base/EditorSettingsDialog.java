package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import org.jboss.pressgang.ccms.ui.client.local.constants.Constants;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;
import org.jboss.pressgang.ccms.ui.client.local.utilities.FontDetector;

public class EditorSettingsDialog extends ClosablePopup {

    private final CheckBox lineWrap = new CheckBox(PressGangCCMSUI.INSTANCE.LineWrap());
    private final CheckBox showInvisibles = new CheckBox(PressGangCCMSUI.INSTANCE.ShowHiddenCharacters());
    private final ListBox fontSizes = new ListBox();
    private final ListBox themes = new ListBox();
    private final ListBox fonts = new ListBox();
    private final CheckBox behaviours = new CheckBox(PressGangCCMSUI.INSTANCE.EnableBehaviours());

    private final FlexTable layout = new FlexTable();
    private final Label fontSizesLabel = new Label(PressGangCCMSUI.INSTANCE.EditorFontSize());
    private final Label themesLabel = new Label(PressGangCCMSUI.INSTANCE.EditorTheme());
    private final Label fontsLabel = new Label(PressGangCCMSUI.INSTANCE.EditorFont());

    public EditorSettingsDialog() {
        super(PressGangCCMSUI.INSTANCE.EditorSettings(), true);
        setGlassEnabled(true);

        final FontDetector fontDetector = new FontDetector();
        for (final String font : Constants.MONOSPACED_FONTS) {
            if (font.equals("monospace")) {
                fonts.addItem(font, font);
            } else {
                String fixedFont = font.contains(" ") ? ("\"" + font + "\"") : font;
                if (fontDetector.detect(fixedFont)) {
                    fonts.addItem(font, fixedFont + ", monospace");
                }
            }
        }

        for (final String fontSize : Constants.FONT_SIZES) {
            fontSizes.addItem(fontSize);
        }

        themes.addItem("Chrome", "chrome");
        themes.addItem("Clouds", "clouds");
        themes.addItem("Crimson Editor", "crimson_editor");
        themes.addItem("Dawn", "dawn");
        themes.addItem("Dreamweaver", "dreamweaver");
        themes.addItem("Eclipse", "eclipse");
        themes.addItem("GitHub", "github");
        themes.addItem("Solarized Light", "solarized_light");
        themes.addItem("TextMate", "textmate");
        themes.addItem("Tomorrow", "tomorrow");
        themes.addItem("XCode", "xcode");
        themes.addItem("Ambiance", "ambiance");
        themes.addItem("Chaos", "chaos");
        themes.addItem("Clouds Midnight", "clouds_midnight");
        themes.addItem("Cobalt", "cobalt");
        themes.addItem("idleFingers", "idle_fingers");
        themes.addItem("krTheme", "kr_theme");
        themes.addItem("Merbivore", "merbivore");
        themes.addItem("Merbivore Soft", "merbivore_soft");
        themes.addItem("Mono Industrial", "mono_industrial");
        themes.addItem("Monokai", "monokai");
        themes.addItem("Pastel on dark", "pastel_on_dark");
        themes.addItem("Solarized Dark", "solarized_dark");
        themes.addItem("Terminal", "terminal");
        themes.addItem("Tomorrow Night", "tomorrow_night");
        themes.addItem("Tomorrow Night Blue", "tomorrow_night_blue");
        themes.addItem("Tomorrow Night Bright", "tomorrow_night_bright");
        themes.addItem("Tomorrow Night 80s", "tomorrow_night_eighties");
        themes.addItem("Twilight", "twilight");
        themes.addItem("Vibrant Ink", "vibrant_ink");

        layout.setWidget(0, 0, themesLabel);
        layout.setWidget(1, 0, themes);
        layout.setWidget(2, 0, fontsLabel);
        layout.setWidget(3, 0, fonts);
        layout.setWidget(4, 0, fontSizesLabel);
        layout.setWidget(5, 0, fontSizes);
        layout.setWidget(6, 0, lineWrap);
        layout.setWidget(7, 0, showInvisibles);
        layout.setWidget(8, 0, getBehaviours());

        add(this.layout);
    }

    public CheckBox getLineWrap() {
        return lineWrap;
    }

    public CheckBox getShowInvisibles() {
        return showInvisibles;
    }

    public ListBox getThemes() {
        return themes;
    }

    public ListBox getFontSizes() {
        return fontSizes;
    }

    public DialogBox getDialogBox() {
        return this;
    }

    public ListBox getFonts() {
        return fonts;
    }

    public CheckBox getBehaviours() {
        return behaviours;
    }
}
