package org.jpreferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Erich Schroeter, http://www.roseindia.net/javatutorials/javaapi.shtml
 */
@SuppressWarnings("serial")
class PrefTableModel extends AbstractTableModel {

	private Preferences pref;
	private String[] keys;

	public PrefTableModel(Preferences pref) {
		this.pref = pref;
		try {
			keys = pref.keys();
		} catch (BackingStoreException e) {
			System.out.println("Could not get keys for Preference node: "
					+ pref.name());
			e.printStackTrace();
			keys = new String[0];
		}
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
		try {
			// make sure the backing store is synchronized with latest update
			pref.sync();
		} catch (BackingStoreException e) {
			System.out
					.println("Error synchronizing backStore with updated value");
			e.printStackTrace();
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		String key = keys[row];
		if (column == 0)
			return key;
		Object value = pref.get(key, "(Unknown)");
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
