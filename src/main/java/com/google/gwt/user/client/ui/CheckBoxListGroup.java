package com.google.gwt.user.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.Element;

public class CheckBoxListGroup extends Composite {
    private final HTMLPanel rootPanel;
    private final VerticalPanel panel = new VerticalPanel();
    private final String name;
    private final List<CheckBox> checkBoxes = new ArrayList<CheckBox>();

    public CheckBoxListGroup(final String name) {
        this.name = name;

        // Create the outer element
        rootPanel = new HTMLPanel("");
        initWidget(rootPanel);
        getElement().addClassName("CheckBoxListGroup");

        // Create the header element
        final Element header = Document.get().createDivElement().cast();
        header.setInnerText(name);
        header.getStyle().setFontWeight(Style.FontWeight.BOLD);
        getElement().appendChild(header);

        // Add the panel
        panel.addStyleName("CheckBoxListGroupContent");
        final Element content = panel.getElement();
        content.getStyle().setMarginLeft(10, Style.Unit.PX);
        rootPanel.add(panel);
    }

    public void addItem(final String label, final String value) {
        addItem(label, value, false);
    }

    public void addItem(final String label, final String value, final boolean checked) {
        addItem(label, value, checked, true);
    }

    public void addItem(final String label, final String value, final boolean checked, boolean enabled) {
        final CheckBox checkBox = new CheckBox(label);
        checkBox.setFormValue(value);
        checkBox.setValue(checked);
        checkBox.setEnabled(enabled);
        addItem(checkBox);
    }

    public void addItem(final CheckBox checkBox) {
        panel.add(checkBox);
        checkBoxes.add(checkBox);
    }

    /**
     * Gets the number of items present in the list box.
     *
     * @return the number of items
     */
    public int getItemCount() {
        return checkBoxes.size();
    }

    /**
     * Gets the text associated with the item at the specified index.
     *
     * @param index the index of the item whose text is to be retrieved
     * @return the text associated with the item
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getItemText(int index) {
        checkIndex(index);
        return checkBoxes.get(index).getText();
    }

    /**
     * Sets the text associated with the item at a given index.
     *
     * @param index the index of the item to be set
     * @param text the item's new text
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemText(int index, String text) {
        setItemText(index, text, null);
    }

    /**
     * Sets the text associated with the item at a given index.
     *
     * @param index the index of the item to be set
     * @param text the item's new text
     * @param dir the item's direction.
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemText(int index, String text, HasDirection.Direction dir) {
        checkIndex(index);
        if (text == null) {
            throw new NullPointerException("Cannot set an option to have null text");
        }
        checkBoxes.get(index).setText(text, dir);
    }

    /**
     * Determines whether an individual list item is selected.
     *
     * @param index the index of the item to be tested
     * @return <code>true</code> if the item is selected
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean isItemSelected(int index) {
        checkIndex(index);
        return checkBoxes.get(index).getValue();
    }

    /**
     * Sets whether an individual list item is selected.
     *
     * @param index the index of the item to be selected or unselected
     * @param selected <code>true</code> to select the item
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setItemSelected(int index, boolean selected) {
        checkIndex(index);
        checkBoxes.get(index).setValue(selected);
    }

    public List<Integer> getSelectedIndexes() {
        final List<Integer> retValue = new ArrayList<Integer>();
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).getValue()) {
                retValue.add(i);
            }
        }
        return retValue;
    }

    /**
     * Gets the values associated with all the selected items.
     *
     * @return the lists selected item values
     */
    public List<String> getSelectedItemValues() {
        final List<String> retValue = new ArrayList<String>();
        for (int i = 0; i < checkBoxes.size(); i++) {
            final CheckBox checkBox = checkBoxes.get(i);
            if (checkBox.getValue()) {
                retValue.add(checkBox.getFormValue());
            }
        }
        return retValue;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getItemCount()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getName() {
        return name;
    }

    protected List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    /**
     * Removes all items from the list box.
     */
    public void clear() {
        checkBoxes.clear();
        panel.clear();
    }
}
