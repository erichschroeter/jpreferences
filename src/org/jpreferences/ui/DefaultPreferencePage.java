package org.jpreferences.ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jpreferences.IPreferenceManager;

/**
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:05 PM
 */
@SuppressWarnings("serial")
public class DefaultPreferencePage extends JPanel implements IPreferencePage {

	/**
	 * Represents whether any setting on this page has changed.
	 * 
	 * This value will be <code>True</code> if a value has changed, otherwise
	 * <code>False</code>.
	 */
	private boolean dirty;
	/**
	 * A short title to represent the preference page.
	 * 
	 * This value can be used as the label in a {@link IPreferenceNode} if none
	 * is specified in the node.
	 */
	private String title;
	/**
	 * Text describing what is on the preference page.
	 */
	private String description;
	/**
	 * The <code>IPreferenceManager</code> which manages the preferences for
	 * which this page displays.
	 */
	private IPreferenceManager manager;

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
		setManager(manager);
		setTitle(title);
		setDescription(description);
		createContents();
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
		this.title = title;
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
		this.description = description;
	}

	/**
	 * Handles creating the components to be displayed on the
	 * <code>IPreferencePage</code>.
	 */
	public void createContents() {
		add(new JLabel(getDescription()));
	}

	/**
	 * Handles setting the default values on this page.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performDefault() {
		return true;
	}

	/**
	 * Handles saving the preferences represented on this preference page.
	 * Notifies whether saving was successful or an error occurred attempting
	 * to.
	 * 
	 * @return <code>True</code> if preferences were successfully saved, else
	 *         <code>False</code>
	 */
	public boolean performOk() {
		boolean saved = false;
		if (manager != null) {
			saved = manager.getStore().save();
		}
		return saved;
	}

	/**
	 * Handles canceling any changes made on this preference page. Notifies
	 * whether Canceling was successful or if an error occurred.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performCancel() {
		return true;
	}

	/**
	 * Checks whether it is OK to leave this page.
	 * 
	 * This could be used to prevent the preference page from changing in case
	 * preferences need to be saved. This could happen if a user selects another
	 * page from the preference tree after modifying fields without clicking the
	 * <i>OK</i> or <i>Cancel</i> buttons.
	 * 
	 * @return <code>True</code> if the preferences on this page have been
	 *         saved, else <code>False</code>
	 */
	public boolean okToLeave() {
		return !dirty;
	}

	@Override
	public void setManager(IPreferenceManager manager) {
		this.manager = manager;
	}

	@Override
	public IPreferenceManager getManager() {
		return manager;
	}

	@Override
	public Component getContents() {
		return this;
	}

}