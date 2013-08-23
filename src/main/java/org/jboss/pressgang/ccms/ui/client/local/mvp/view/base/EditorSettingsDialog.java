package org.jboss.pressgang.ccms.ui.client.local.mvp.view.base;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClosablePopup;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import org.jboss.pressgang.ccms.ui.client.local.resources.strings.PressGangCCMSUI;

public class EditorSettingsDialog extends ClosablePopup {

    private final CheckBox lineWrap = new CheckBox(PressGangCCMSUI.INSTANCE.LineWrap());
    private final CheckBox showInvisibles = new CheckBox(PressGangCCMSUI.INSTANCE.ShowHiddenCharacters());
    private final ListBox fontSizes = new ListBox();
    private final ListBox themes = new ListBox();

    private final FlexTable layout = new FlexTable();
    private final Label fontSizesLabel = new Label(PressGangCCMSUI.INSTANCE.EditorFontSize());
    private final Label themesLabel = new Label(PressGangCCMSUI.INSTANCE.EditorTheme());

    public EditorSettingsDialog() {
        super(PressGangCCMSUI.INSTANCE.EditorSettings(), true);
        setGlassEnabled(true);

        fontSizes.addItem("10px");
        fontSizes.addItem("11px");
        fontSizes.addItem("12px");
        fontSizes.addItem("13px");
        fontSizes.addItem("14px");
        fontSizes.addItem("16px");
        fontSizes.addItem("18px");
        fontSizes.addItem("20px");

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
        layout.setWidget(2, 0, fontSizesLabel);
        layout.setWidget(3, 0, fontSizes);
        layout.setWidget(4, 0, lineWrap);
        layout.setWidget(5, 0, showInvisibles);

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
}
