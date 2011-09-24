package org.jpreferences.page;

import java.util.prefs.Preferences;

import javax.swing.JTable;

import org.jpreferences.PreferenceTableModel;

/**
 * A <code>PreferencePage</code> is a page that displays {@link Preferences} and
 * allows users to view, edit, and delete preferences.
 * 
 * @author Erich Schroeter
 */
public class PreferencePage extends CustomPage<JTable> {

	/**
	 * Constructs a <code>PreferencePage</code> specifying the preference node
	 * to display preferences for.
	 * 
	 * @param preference
	 *            the preferences node
	 */
	public PreferencePage(Preferences preference) {
		super(preference.name(), new JTable());
	}

	@Override
	protected void initializePage(JTable page) {
	}

	/**
	 * Sets the table model.
	 * 
	 * @param model
	 *            the model to set
	 */
	public void setModel(PreferenceTableModel model) {
		page.setModel(model);
	}

}
