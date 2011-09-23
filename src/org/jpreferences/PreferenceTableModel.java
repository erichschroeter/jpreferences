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

	/** The preference object to wrap. */
	private Preferences pref;
	/** A reference to the preference keys. */
	private String[] keys;

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
					addRow(evt.getKey(), evt.getNewValue());
				} else {
					remove(evt.getKey());
				}
			}
		});
	}

	/**
	 * Resets the reference of {@link #keys} to {@link #pref}
	 * <code>.keys()</code> and handles catching the
	 * {@link BackingStoreException} it throws.
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

	public void addRow(String key, Object value) {
		String val = pref.get(key, null);
		if (val == null) {
			pref.put(key, value.toString());
			sync();
			updateKeys();
			// fireTableDataChanged();
			fireTableRowsInserted(getRowCount(), getRowCount());
		}
	}

	public void remove(String key) {
		pref.remove(key);
		sync();
		updateKeys();
		fireTableDataChanged();
	}

	public void removeRow(int rowIndex) {
		pref.remove(keys[rowIndex]);
		sync();
		updateKeys();
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public String getColumnName(int column) {
		String name = null;
		switch (column) {
		case 0:
			name = "Key";
			break;
		case 1:
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
		case 0:
			editable = false;
			break;
		case 1:
			editable = true;
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
		if (column == 0) {
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
