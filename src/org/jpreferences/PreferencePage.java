package org.jpreferences;

import javax.swing.JPanel;

public abstract class PreferencePage extends JPanel implements IPreferencePage {

	private static final long serialVersionUID = -2034703565523548097L;

	/**
	 * A short title to represent the <code>PreferencePage</code>.
	 * <p>
	 * This value is used as the <code>Label</code> in a <code>JList</code>.
	 * </p>
	 */
	protected String title;

	/**
	 * Text describing the purpose of the <code>PreferencePage</code>.
	 */
	protected String description;

	/**
	 * The <code>PreferenceManager</code> which manages the preferences for this
	 * page displays.
	 */
	protected PreferenceManager manager;

	/**
	 * Creates a <code>PreferencePage</code> object specifying the <i>title</i>,
	 * and <i>description</i>.
	 * 
	 * @param title
	 *            The title. If value is <code>null</code> the <i>title</i>
	 *            attribute is set to empty string ( <code>""</code>)
	 * @param description
	 *            A description of the page. If value is <code>null</code> the
	 *            <i>description</i> attribute is set to empty string (
	 *            <code>""</code>)
	 */
	public PreferencePage(PreferenceManager manager, String title,
			String description) {
		setManager(manager);
		setTitle(title);
		setDescription(description);
	}

	/**
	 * Returns the <code>PreferenceManager</code> this
	 * <code>PreferencePage</code> is using.
	 * 
	 * @return the manager
	 */
	public PreferenceManager getManager() {
		return manager;
	}

	/**
	 * Sets the <code>PreferenceManager</code> which manages the preferences
	 * this <code>PreferencePage</code> is displaying.
	 * 
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(PreferenceManager manager) {
		this.manager = manager;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the <i>title</i> attribute.
	 * <p>
	 * If the argument is <code>null</code>, the <i>title</i> attribute is set
	 * to the DEFAULT_TITLE.
	 * <p>
	 * 
	 * @param title
	 *            The new title
	 */
	public void setTitle(String title) {
		this.title = title != null ? title : "";
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the <i>description</i> attribute.
	 * <p>
	 * If the argument is <code>null</code>, the <i>description</i> attribute is
	 * set to an empty string (<code>""</code>).
	 * <p>
	 * 
	 * @param description
	 *            The new description
	 */
	public void setDescription(String description) {
		this.description = description != null ? description : "";
	}

	/**
	 * Handles creating the components to be displayed on the
	 * <code>PreferencePage</code>.
	 */
	protected abstract void createContents();
}
