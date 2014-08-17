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

package com.google.gwt.user.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.HasDirection;

public class CheckBoxList extends Composite {
    private final ScrollPanel scroller = new ScrollPanel();
    private final VerticalPanel panel = new VerticalPanel();
    private final List<Widget> checkBoxes = new ArrayList<Widget>();
    private boolean enabled = true;

    public CheckBoxList() {
        initWidget(scroller);
        scroller.setWidget(panel);
        addStyleName("CheckBoxList");
    }

    /**
     * Removes all items from the list box.
     */
    public void clear() {
        for (final Widget widget : checkBoxes) {
            if (widget instanceof CheckBoxListGroup) {
                ((CheckBoxListGroup) widget).clear();
            }
        }

        checkBoxes.clear();
        panel.clear();
    }

    public void addItem(final String label, final String value) {
        addItem(label, value, false);
    }

    public void addItem(final String label, final String value, final boolean checked) {
        final CheckBox checkBox = new CheckBox(label);
        checkBox.setFormValue(value);
        checkBox.setValue(checked);
        addItem(checkBox);
    }

    public void addItem(final CheckBox checkBox) {
        checkBox.setEnabled(enabled);
        panel.add(checkBox);
        checkBoxes.add(checkBox);
    }

    public void addGroupItem(final CheckBoxListGroup group) {
        panel.add(group);
        checkBoxes.add(group);
    }

    /**
     * Removes the item at the specified index.
     *
     * @param index the index of the item to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void removeItem(int index) {
        checkIndex(index);
        final Widget checkBox = checkBoxes.get(index);
        checkBox.removeFromParent();
        checkBoxes.remove(index);
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
        final Widget widget = checkBoxes.get(index);
        if (widget instanceof CheckBox) {
            return ((CheckBox) widget).getText();
        } else {
            return null;
        }
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
        final Widget widget = checkBoxes.get(index);
        if (widget instanceof CheckBox) {
            ((CheckBox) widget).setText(text, dir);
        } else {
            throw new IllegalArgumentException("Cannot set the text for a CheckBoxList group");
        }
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
        final Widget widget = checkBoxes.get(index);
        if (widget instanceof CheckBox) {
            return ((CheckBox) widget).getValue();
        } else {
            throw new IllegalArgumentException("Cannot get the value for a CheckBoxList group");
        }
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
        final Widget widget = checkBoxes.get(index);
        if (widget instanceof CheckBox) {
            ((CheckBox) widget).setValue(selected);
        } else {
            throw new IllegalArgumentException("Cannot set the value for a CheckBoxList group");
        }
    }

    public List<Integer> getSelectedIndexes() {
        final List<Integer> retValue = new ArrayList<Integer>();
        for (int i = 0; i < checkBoxes.size(); i++) {
            final Widget widget = checkBoxes.get(i);
            if (widget instanceof CheckBox) {
                if (((CheckBox) widget).getValue()) {
                    retValue.add(i);
                }
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
            final Widget widget = checkBoxes.get(i);
            if (widget instanceof CheckBox) {
                final CheckBox checkBox = (CheckBox) widget;
                if (checkBox.getValue()) {
                    retValue.add(checkBox.getFormValue());
                }
            } else {
                retValue.addAll(((CheckBoxListGroup) widget).getSelectedItemValues());
            }
        }
        return retValue;
    }

    /**
     * Gets the value associated with the item at a given index.
     *
     * @param index the index of the item to be retrieved
     * @return the item's associated value
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public String getValue(int index) {
        checkIndex(index);
        final Widget widget = checkBoxes.get(index);
        if (widget instanceof CheckBox) {
            return ((CheckBox) widget).getFormValue();
        } else {
            throw new IllegalArgumentException("Cannot get the value for a CheckBoxList group");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getItemCount()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        for (final Widget checkBox : checkBoxes) {
            if (checkBox instanceof CheckBox) {
                ((CheckBox) checkBox).setEnabled(enabled);
            } else if (checkBox instanceof CheckBoxListGroup) {
                ((CheckBoxListGroup) checkBox).setEnabled(enabled);
            }
        }
    }
}
