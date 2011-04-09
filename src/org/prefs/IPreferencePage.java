package org.prefs;

public interface IPreferencePage {

	public void initialize(IPreferenceStore store);
	
	/**
	 * Checks whether it is alright to leave this page.
	 * 
	 * @return false to abort page flipping and the have the current page remain
	 *         visible, and true to allow the page flip
	 */
	public boolean okToLeave();

	/**
	 * Notifies that the container of this preference page has been canceled.
	 * 
	 * @return false to abort the container's cancel procedure and true to allow
	 *         the cancel to happen
	 */
	public boolean performCancel();

	/**
	 * Notifies that the OK button of this page's container has been pressed.
	 * 
	 * @return false to abort the container's OK processing and true to allow
	 *         the OK to happen
	 */
	public boolean performOk();

	/**
	 * Notifies that the Default button of this page's container has been
	 * pressed.
	 * 
	 * @return false to abort the page's Default processing and true to allow
	 *         the Default to happen
	 */
	public boolean performDefault();

	/**
	 * Sets or clears the container of this page.
	 * 
	 * @param container
	 *            the preference page container, or null
	 */
	public void setPageContainer(IPreferencePageContainer container) throws NullPointerException;

	/**
	 * Returns the <code>IPreferencePage</code>'s
	 * <code>IPreferencePageContainer</code>.
	 * 
	 * @return the preference page's container
	 */
	public IPreferencePageContainer getPageContainer();

	/**
	 * Sets the preference page's title.
	 * 
	 * @param title
	 *            The title
	 */
	public void setTitle(String title);

	/**
	 * Returns the preference page's title.
	 * 
	 * @return The title, else empty string if the title was not set
	 */
	public String getTitle();

	/**
	 * Sets the preference page's description.
	 * 
	 * @param desc
	 *            The description
	 */
	public void setDescription(String desc);

	/**
	 * Returns the preference page's description.
	 * 
	 * @return The description, else empty string if the description was not set
	 */
	public String getDescription();

	/**
	 * Returns the name of the class which implements IPreferencePage. This is
	 * used for creating instances via the Class.newInstance() function.
	 * 
	 * @return The class name
	 */
	public String getClassName();
}
