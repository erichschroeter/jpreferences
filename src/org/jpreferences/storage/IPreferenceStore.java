package org.jpreferences.storage;

/**
 * A preference store provides storage for preferences.
 * 
 * The method for storing the objects in a preference store is left up to the
 * implementor. Some examples for different methods of storing objects are XML,
 * Java properties, or JSON.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferenceStore extends ICRUD<String>, IStoreSupport<String>, IStore<String> {

	/**
	 * Returns whether the named preference is stored in this preference store.
	 * 
	 * @return <code>True</code> if it exists, else <code>False</code>
	 * 
	 * @param name    the name of the preference
	 */
	public boolean contains(String name);

	/**
	 * Returns whether the preference associated with the given identifier is the
	 * default value or not.
	 * 
	 * @return <code>True</code> if the preference is the same as the default, else
	 * <code>False</code>
	 * 
	 * @param name    the name of the preference
	 */
	public boolean isDefault(String name);

	/**
	 * Returns the default value for the preference associated with the specified
	 * identifier.
	 * 
	 * @return the default value
	 * 
	 * @param id    the identifier
	 */
	public String getDefault(String id);

	/**
	 * Sets the default value for the preference associated with the given identifier.
	 * 
	 * 
	 * When {@link #reset(String)} is called, this is the value the preference will be
	 * set to.
	 * 
	 * @param id    the identifier
	 * @param preference    the new default value for the preference
	 */
	public void setDefault(String id, String preference);

	/**
	 * Sets the preference associated with the given identifier back to its default
	 * value.
	 * 
	 * @see #setDefault(String, String)
	 * 
	 * @param id    the identifier
	 */
	public void reset(String id);

	/**
	 * Creates the preference in the store with the associated identifier.
	 * 
	 * A property change event is reported if the current value of the preference
	 * actually changes from its previous value. In the event object, the property
	 * name is the name of the preference.
	 * 
	 * Note that the preferred way of re-initializing a preference to its default
	 * value is to call {@link #reset(String, String)}.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 * @param preference    the preference to set
	 */
	public boolean createPreference(String id, String preference);

	/**
	 * Reads and returns the preference associated with the given identifier in the
	 * store.
	 * 
	 * @return the preference
	 * 
	 * @param id    the identifier
	 */
	public String readPreference(String id);

	/**
	 * Updates the preference associated with the specified identifier to the given
	 * preference.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 * @param preference    the preference
	 */
	public boolean updatePreference(String id, String preference);

	/**
	 * Deletes the preference associated with the specified identifier.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 */
	public boolean deletePreference(String id);

}