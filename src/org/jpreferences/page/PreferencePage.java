package org.jpreferences.page;

import java.util.prefs.Preferences;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jpreferences.PreferenceTable;
import org.jpreferences.PreferenceTableModel;

/**
 * A <code>PreferencePage</code> is a page that displays {@link Preferences} and
 * allows users to view, edit, and delete preferences.
 * 
 * @author Erich Schroeter
 */
public class PreferencePage extends CustomPage<JScrollPane> {

	/** The preference table. */
	private JTable prefTable;

	/**
	 * Constructs a <code>PreferencePage</code> specifying the preference node
	 * to display preferences for.
	 * 
	 * @param preference
	 *            the preferences node
	 */
	public PreferencePage(Preferences preference) {
		super(preference.name(), new JScrollPane());
	}

	@Override
	protected void initializePage(JScrollPane page) {
		prefTable = new PreferenceTable();
		page.setViewportView(prefTable);
	}

	/**
	 * Sets the table model.
	 * 
	 * @param model
	 *            the model to set
	 */
	public void setModel(PreferenceTableModel model) {
		prefTable.setModel(model);
	}

}
