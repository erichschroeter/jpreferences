package org.jpreferences;

import org.jpreferences.model.IPreferenceNode;
import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.ui.IPreferencePage;

/**
 * An interface for managing {@link IPreferenceNode} objects. By managing nodes
 * you are actually managing {@link IPreferencePage}s as well since each node
 * contains a page.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferenceManager {

	/**
	 * Returns the page currently being displayed. Only one page can be shown at
	 * a time.
	 * 
	 * @return the current preference page
	 */
	public IPreferencePage getCurrentPage();

	/**
	 * Sets the current page displayed to the user.
	 * 
	 * @param page
	 *            the preference page
	 */
	public void setCurrentPage(IPreferencePage page);

	/**
	 * Sets the current page displayed to the user based on the tree path.
	 * 
	 * @param path
	 *            the page tree path
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
	 * @param store
	 *            the preference store
	 */
	public void setStore(IPreferenceStore store);

	/**
	 * Adds the specified preference to the manager. This should be a
	 * convenience method for calling the
	 * {@link IPreferenceStore#createPreference(String)}.
	 * 
	 * 
	 * @param preference
	 *            the preference
	 */
	public void addPreference(Preference preference);

	/**
	 * Removes the specified preference from the manager. This should be a
	 * convenience method for calling the
	 * {@link IPreferenceStore#deletePreference(String)}.
	 * 
	 * @param preference
	 *            the preference
	 */
	public void removePreference(Preference preference);

	/**
	 * Returns the specified preference from the manager.
	 * 
	 * @return the preference
	 * 
	 * @param id
	 *            the identifier
	 */
	public Preference getPreference(String id);

	/**
	 * Adds the given node to the default root node.
	 * <p>
	 * This function is simply a convenience method for
	 * {@link #add(IPreferenceNode, IPreferenceNode)}. Calling this function is
	 * equivalent to calling {@link #add(IPreferenceNode, IPreferenceNode)}.
	 * </p>
	 * 
	 * @param node
	 *            The new node
	 */
	public void add(IPreferenceNode node);

	/**
	 * Adds the given child <code>IPreferenceNode</code> to the specified parent
	 * <code>IPreferenceNode</code>.
	 * 
	 * @param parent
	 *            The parent to add the child to. If parent is <code>null</code>
	 *            then the child is added to a default root node.
	 * @param child
	 *            The child being added
	 */
	public void add(IPreferenceNode parent, IPreferenceNode child);

	/**
	 * Returns the root node of all root nodes.
	 * 
	 * @return The master root node
	 */
	public IPreferenceNode getRoot();

	/**
	 * Adds the specified {@link ICurrentPageListener} to the list of listeners.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addCurrentPageListener(ICurrentPageListener listener);

	/**
	 * Removes the specified {@link ICurrentPageListener} from the list of
	 * listeners.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void removeCurrentPageListener(ICurrentPageListener listener);

}