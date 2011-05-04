package org.jpreferences;
import org.jpreferences.storage.IStorage;

/**
 * @author erisch
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferencePageContainer {

	/**
	 * Returns the preference store.
	 * @return the preference store, or <code>null</code> if none
	 */
	public IStorage getPreferenceStore();

	/**
	 * Sets the preference store.
	 * 
	 * @param store
	 */
	public void setPreferenceStore(IStorage store);

	/**
	 * Adjusts the enable state of the OK button to reflect the state of the currently
	 * active page in this container.  This method is called by the container itself
	 * when its preference page changes and may be called by the page at other times
	 * to force a button state update.
	 */
	public void updateButtons();

	/**
	 * Updates the message (or error message) shown in the message line to reflect the
	 * state of the currently active page in this container.  This method is called by
	 * the container itself when its preference page changes and may be called by the
	 * page at other times to force a message update.
	 */
	public void updateMessage();

	/**
	 * Updates the title to reflect the state of the currently active page in this
	 * container.  This method is called by the container itself when its page changes
	 * and may be called by the page at other times to force a title update.
	 */
	public void updateTitle();

	/**
	 * Returns the <code>Window</code> reference in which the
	 * <code>IPreferencePageContainer</code> has access to.
	 * <p>
	 * <code>IPreferencePage</code>'s are graphical objects needing a
	 * <code>Window</code> to be displayed in. Often objects contained in a
	 * <code>IPreferencePage</code> will need access to the
	 * <code>IPreferencePageContainer</code>'s <code>Window</code>, such is the case
	 * in additional dialog windows (e.g. <code>ColorPicker</code>,
	 * <code>JDialog</code>).
	 * </p>
	 * @return the <code>IPreferencePageContainer</code>'s <code>Window</code>
	 * reference
	 */
	public Window getWindow();

}