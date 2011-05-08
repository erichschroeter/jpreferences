package org.jpreferences.ui;

import javax.swing.JLabel;

import org.jpreferences.IPreferenceManager;

/**
 * A preference page which simply displays the
 * {@link DefaultPreferencePage#getDescription()} wrapped inside a
 * {@link JPanel}.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class DefaultPreferencePage extends PreferencePage {

	public DefaultPreferencePage() {
		this(null, null, null);
	}

	/**
	 * Creates a <code>PreferencePage</code> object specifying the <i>title</i>,
	 * and <i>description</i>.
	 * 
	 * @param manager
	 * @param title
	 *            The title. If value is <code>null</code> the <i>title</i>
	 *            attribute is set to empty string ( <code>""</code>)
	 * @param description
	 *            A description of the page. If value is <code>null</code> the
	 *            <i>description</i> attribute is set to empty string (
	 *            <code>""</code>)
	 */
	public DefaultPreferencePage(IPreferenceManager manager, String title,
			String description) {
		super(manager, title, description);
	}

	@Override
	public void createContents() {
		add(new JLabel(getDescription()));
	}

}