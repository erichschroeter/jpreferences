package org.jpreferences;

import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.ui.IPreferencePage;

/**
 * An interface for managing {@link IPreferenceNode} objects. By managing nodes
 * you are actually managing {@link IPreferencePage}s as well since each node
 * contains a page.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferenceManager {

	/**
	 * Returns the page currently being displayed. Only one page can be shown at a
	 * time.
	 * 
	 * @return the current preference page
	 */
	public IPreferencePage getCurrentPage();

	/**
	 * Sets the current page displayed to the user.
	 * 
	 * @param page    the preference page
	 */
	public void setCurrentPage(IPreferencePage page);

	/**
	 * Sets the current page displayed to the user based on the tree path.
	 * 
	 * @param path    the page tree path
	 */
	public void setCurrentPage(String path);

	/**
	 * Returns the preference store being managed.
	 * 
	 * @return the preference store
	 */
	public IPreferenceStore getStore();

	/**
	 * Sets the preference store to manage.
	 * 
	 * @param store    the preference store
	 */
	public void setStore(IPreferenceStore store);

	/**
	 * Adds the specified preference to the manager.
	 * 
	 * @param preference    the preference
	 */
	public void addPreference(IPreference preference);

	/**
	 * Removes the specified preference from the manager.
	 * 
	 * @param preference    the preference
	 */
	public void removePreference(IPreference preference);

	/**
	 * Returns the specified preference from the manager.
	 * 
	 * @return the preference
	 * 
	 * @param id    the identifier
	 */
	public IPreference getPreference(String id);

}