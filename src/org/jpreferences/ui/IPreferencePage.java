package org.jpreferences.ui;

import org.jpreferences.IPreferenceManager;

/**
 * A preference page displays graphical interface components as a frontend to a
 * backend {@link IPreferenceStore}. A preference page allows users to change
 * preferences within a store.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferencePage {

	/**
	 * Sets the preference manager managing the preferences being displayed on this
	 * page.
	 * 
	 * @param manager    the manager
	 */
	public void setManager(IPreferenceManager manager);

	/**
	 * Returns the manager managing the preference pages.
	 * 
	 * @return the manager
	 */
	public IPreferenceManager getManager();

	/**
	 * Sets the preference page title.
	 * 
	 * @param title    The title
	 */
	public void setTitle(String title);

	/**
	 * Returns the preference page title.
	 * 
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Sets the preference page description.
	 * 
	 * @param desc    The description
	 */
	public void setDescription(String desc);

	/**
	 * Returns the preference page description.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Checks whether it is alright to leave this page.
	 * 
	 * This could be used to prevent the preference page from changing in case
	 * preferences need to be saved. This could happen if a user selects another page
	 * from the preference tree after modifying fields without clicking the <i>OK</i>
	 * or <i>Cancel</i> buttons.
	 * 
	 * @return <code>True</code> if the preferences on this page have been saved, else
	 * <code>False</code>
	 */
	public boolean okToLeave();

	/**
	 * Handles saving the preferences represented on this preference page. Notifies
	 * whether saving was successful or an error occurred attempting to.
	 * 
	 * @return <code>True</code> if preferences were successfully saved, else
	 * <code>False</code>
	 */
	public boolean performOk();

	/**
	 * Handles cancelling any changes made on this preference page. Notifies whether
	 * cancelling was successful or if an error occurred.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performCancel();

	/**
	 * Handles setting the default values on this page.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performDefault();

	/**
	 * Creates the graphical components to display on this page. Graphical components
	 * such as text fields to manipulate preference values
	 */
	public void createContents();

}