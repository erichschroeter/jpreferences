package org.jpreferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import javax.swing.table.AbstractTableModel;

/**
 * The <code>PreferenceTableModel</code> wraps a {@link Preferences} object to
 * view and edit its children preferences.
 * 
 * @author Erich Schroeter, http://www.roseindia.net/javatutorials/javaapi.shtml
 */
@SuppressWarnings("serial")
class PreferenceTableModel extends AbstractTableModel {

	/** The column index for the keys. */
	private static final int KEY_COLUMN = 0;
	/** The column index for the values. */
	private static final int VALUE_COLUMN = 1;

	/** The preference object to wrap. */
	private Preferences pref;
	/** A reference to the preference keys. */
	private String[] keys;

	/** Whether to allow preferences to be deleted. */
	private boolean deleteAllowed;
	/** Whether to allow preferences to be added. */
	private boolean addAllowed;
	/** Whether to allow preference keys to be modified. */
	private boolean editKeysAllowed;
	/** Whether to allow preference values to be modified. */
	private boolean editValuesAllowed;

	/**
	 * Constructs a <code>PreferenceTableModel</code> wrapping the specified
	 * <code>pref</code>.
	 * 
	 * @param pref
	 *            the preference object to wrap
	 */
	public PreferenceTableModel(Preferences pref) {
		this.pref = pref;
		updateKeys();
		pref.addPreferenceChangeListener(new PreferenceChangeListener() {

			@Override
			public void preferenceChange(PreferenceChangeEvent evt) {
				String value = evt.getNewValue();
				if (value != null) {
					addPreference(evt.getKey(), evt.getNewValue());
				} else {
					removePreference(evt.getKey());
				}
			}
		});
		initializeDefaults();
	}

	/**
	 * Initializes the default features to be enabled or disabled.
	 * <p>
	 * In derived classes this may be overridden to customize which features are
	 * enabled or disabled.
	 */
	protected void initializeDefaults() {
		setAddAllowed(true);
		setDeleteAllowed(false);
		setEditKeysAllowed(false);
		setEditValuesAllowed(true);
	}

	/**
	 * Resets the reference of {@link #keys} to {@link #pref}
	 * <code>.keys()</code> and handles catching the
	 * {@link BackingStoreException} it throws.
	 * 
	 * @see #sync()
	 */
	protected void updateKeys() {
		try {
			keys = pref.keys();
		} catch (BackingStoreException e) {
			System.out.println("Could not get keys for Preference node: "
					+ pref.name());
			e.printStackTrace();
			keys = new String[0];
		}
	}

	/**
	 * Calls {@link #pref}<code>.sync()</code> and handles catching the
	 * {@link BackingStoreException} it throws.
	 * 
	 * @see #updateKeys()
	 */
	protected void sync() {
		try {
			// make sure the backing store is synchronized with latest update
			pref.sync();
		} catch (BackingStoreException e) {
			System.out
					.println("Error synchronizing backStore with updated value");
			e.printStackTrace();
		}
	}

	/**
	 * Adds a preference to the preferences node. This method fires a inserted
	 * <code>TableModelEvent</code> when successfully added.
	 * <p>
	 * If <code>key</code> has an existing value associated with it, or if
	 * <code>key</code> or <code>value</code> is <code>null</code> this method
	 * changes nothing.
	 * 
	 * @param key
	 *            the preference key
	 * @param value
	 *            the preference value
	 */
	public void addPreference(String key, Object value) {
		if (key == null || value == null) {
			return; // return immediately
		}
		String val = pref.get(key, null);
		// only add the preference if the key has no associated value
		if (val == null) {
			pref.put(key, value.toString());
			sync();
			updateKeys();
			fireTableRowsInserted(getRowCount(), getRowCount());
		}
	}

	/**
	 * Removes the preference associated by <code>key</code>. This method fires
	 * a deleted <code>TableModelEvent</code> when successfully removed.
	 * <p>
	 * If <code>key</code> is <code>null</code> this method changes nothing.
	 * <p>
	 * If possible, try to use {@link #removeRow(int)} for better performance.
	 * This method calls that method after iterating over the keys to find the
	 * index.
	 * 
	 * @see #removeRow(int)
	 * @param key
	 *            the preference to remove
	 */
	public void removePreference(String key) {
		if (key == null) {
			return; // return immediately
		}
		int index = -1;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals(key)) {
				index = i;
				// break out of this loop to increase performance
				// otherwise we will iterate n every time
				break;
			}
		}
		removeRow(index);
	}

	/**
	 * Removes the preference at the specified index.This method fires a deleted
	 * <code>TableModelEvent</code> when successfully removed.
	 * <p>
	 * If <code>rowIndex</code> &lt; 0 or <code>rowIndex</code> &gt;=
	 * <code>{@link #keys}.length</code> this method changes nothing.
	 * 
	 * @see #removePreference(String)
	 * @see #sync()
	 * @see #updateKeys()
	 * @param rowIndex
	 *            the preference key index (between 0 and number of keys}
	 */
	public void removeRow(int rowIndex) {
		if (rowIndex <= 0 && rowIndex < keys.length) {
			pref.remove(keys[rowIndex]);
			sync();
			updateKeys();
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

	/**
	 * Returns whether to allow preferences to be deleted.
	 * 
	 * @return <code>true</code> if allowed, else <code>false</code>
	 */
	public boolean isDeleteAllowed() {
		return deleteAllowed;
	}

	/**
	 * Enables or disables the ability to delete preferences.
	 * 
	 * @param enable
	 *            <code>true</code> to allow, <code>false</code> to disallow
	 * @return the instance for additional configuration
	 */
	public PreferenceTableModel setDeleteAllowed(boolean enable) {
		this.deleteAllowed = enable;
		return this;
	}

	/**
	 * Returns whether to allow preferences to be added.
	 * 
	 * @return <code>true</code> if allowed, else <code>false</code>
	 */
	public boolean isAddAllowed() {
		return addAllowed;
	}

	/**
	 * Enables or disables the ability to add preferences.
	 * 
	 * @param enable
	 *            <code>true</code> to allow, <code>false</code> to disallow
	 * @return the instance for additional configuration
	 */
	public PreferenceTableModel setAddAllowed(boolean enable) {
		this.addAllowed = enable;
		return this;
	}

	/**
	 * Returns whether to allow preference values to be modified.
	 * 
	 * @return <code>true</code> if allowed, else <code>false</code>
	 */
	public boolean isEditValuesAllowed() {
		return editValuesAllowed;
	}

	/**
	 * Enables or disables the ability to modify preference values.
	 * 
	 * @param enable
	 *            <code>true</code> to allow, <code>false</code> to disallow
	 * @return the instance for additional configuration
	 */
	public PreferenceTableModel setEditValuesAllowed(boolean enable) {
		this.editValuesAllowed = enable;
		return this;
	}

	/**
	 * Returns whether to allow preference keys to be modified.
	 * 
	 * @return <code>true</code> if allowed, else <code>false</code>
	 */
	public boolean isEditKeysAllowed() {
		return editKeysAllowed;
	}

	/**
	 * Enables or disables the ability to modify preference keys.
	 * 
	 * @param enable
	 *            <code>true</code> to allow, <code>false</code> to disallow
	 * @return the instance for additional configuration
	 */
	public PreferenceTableModel setEditKeysAllowed(boolean enable) {
		this.editKeysAllowed = enable;
		return this;
	}

	@Override
	public String getColumnName(int column) {
		String name = null;
		switch (column) {
		case KEY_COLUMN:
			name = "Key";
			break;
		case VALUE_COLUMN:
			name = "Value";
			break;
		default:
			name = "-";
		}
		return name;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean editable = false;
		switch (columnIndex) {
		case KEY_COLUMN:
			editable = isEditKeysAllowed();
			break;
		case VALUE_COLUMN:
			editable = isEditValuesAllowed();
			break;
		default:
			editable = false;
		}
		return editable;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		pref.put(keys[rowIndex], aValue.toString());
		sync();
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object value = null;
		String key = keys[row];
		if (column == KEY_COLUMN) {
			value = key;
		} else {
			value = pref.get(key, "(Unknown)");
		}
		return value;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return keys.length;
	}
}
