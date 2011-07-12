package org.jpreferences.storage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The default preference store stores preferences using the Java
 * {@link Preferences} object.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class DefaultPreferenceStore implements IPreferenceStore {

	/** The preference mapping storage. */
	private Preferences prefs;
	/** The preference mapping storage for default values. */
	private Map<String, String> defaults;
	/**
	 * Indicates whether a value has been changed by <code>setToDefault()</code>
	 * , <code>setDefault(String)</code>, or <code>setValue(String,T)</code>.
	 * Initially <code>false</code>
	 */
	private boolean dirty = false;

	/**
	 * Constructs a default <code>DefaultPreferenceStore</code> using the user
	 * root preferences.
	 * <p>
	 * This is equivalent to
	 * <code>DefaultPreferenceStore(Preferences.userRoot())</code>.
	 * 
	 * @see Preferences#userRoot()
	 * @see #DefaultPreferenceStore(Preferences)
	 */
	public DefaultPreferenceStore() {
		this(Preferences.userRoot());
	}

	/**
	 * Constructs a <code>DefaultPreferenceStore</code> specifying the
	 * preferences to manage.
	 * <p>
	 * This is equivalent to
	 * <code>DefaultPreferenceStore(preferences, new HashMap<String, String>())</code>.
	 * 
	 * @see #DefaultPreferenceStore(Preferences, Map)
	 * @param preferences
	 *            the preferences to manage
	 */
	public DefaultPreferenceStore(Preferences preferences) {
		this(preferences, new HashMap<String, String>());
	}

	/**
	 * Constructs a <code>DefaultPreferenceStore</code> specifying the
	 * preferences to manage.
	 * 
	 * @param preferences
	 *            the preferences to manage
	 * @param defaults
	 *            the default preferences
	 */
	public DefaultPreferenceStore(Preferences preferences,
			Map<String, String> defaults) {
		setDefaults(defaults);
		setPreferences(preferences);
	}

	/**
	 * Returns the default preferences this store manages.
	 * 
	 * @return the default preferences
	 */
	public Map<String, String> getDefaults() {
		return defaults;
	}

	/**
	 * Sets the default preferences for this store.
	 * 
	 * @param defaults
	 *            the default preferences to set
	 */
	public void setDefaults(Map<String, String> defaults) {
		this.defaults = defaults;
	}

	/**
	 * Returns the reference to the preference container this store manages.
	 * 
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return prefs;
	}

	/**
	 * Sets the preferences for this store to manage.
	 * 
	 * @param prefs
	 *            the preferences to set
	 */
	public void setPreferences(Preferences prefs) {
		this.prefs = prefs;
	}

	/**
	 * Simply tags the store as dirty, thus informing {@link #save()} and
	 * {@link #save(OutputStream)} to save the data.
	 * 
	 * @see #save()
	 * @see #save(OutputStream)
	 */
	protected void storeHasChanged() {
		dirty = true;
	}

	/**
	 * Returns whether or not the store has changed and needs to be saved. This
	 * is used to prevent over using the {@link #save()} method.
	 * 
	 * @return <code>True</code> if the store needs to be saved,
	 *         <code>False</code> if not
	 */
	protected boolean needsSaving() {
		return dirty;
	}

	/**
	 * Simply tags the store as not dirty anymore, thus preventing resource
	 * inefficiency by not over saving.
	 * 
	 * @see #save(OutputStream)
	 */
	protected void storeSaved() {
		dirty = false;
	}

	//
	// IPreferenceStore members
	//

	/**
	 * Creates the preference in the store with the associated identifier.
	 * <p>
	 * A property change event is reported if the current value of the
	 * preference actually changes from its previous value. In the event object,
	 * the property name is the name of the preference.
	 * </p>
	 * <p>
	 * Note that the preferred way of re-initializing a preference to its
	 * default value is to call {@link #reset(String, String)}.
	 * </p>
	 * 
	 * @see Preferences#put(String, String)
	 * @param id
	 *            the identifier
	 * @param preference
	 *            the preference to set
	 * @return <code>true</code> if successful, else <code>false</code>
	 */
	public boolean createPreference(String id, String preference) {
		boolean success = false;
		try {
			prefs.put(id, preference);
			success = true;
		} catch (NullPointerException e) {
			success = false;
		} catch (IllegalArgumentException e) {
			success = false;
		} catch (IllegalStateException e) {
			success = false;
		}
		storeHasChanged();
		return success;
	}

	/**
	 * Reads and returns the preference associated with the given identifier in
	 * the store.
	 * 
	 * @see Preferences#get(String, String)
	 * @param id
	 *            the identifier
	 * @return the preference value
	 */
	public String readPreference(String id) {
		return prefs.get(id, null);
	}

	/**
	 * Updates the preference associated with the specified identifier to the
	 * given preference.
	 * 
	 * @see Preferences#put(String, String)
	 * @param id
	 *            the identifier
	 * @param preference
	 *            the preference value
	 * @return <code>true</code> if successful, else <code>false</code>
	 */
	public boolean updatePreference(String id, String preference) {
		prefs.put(id, preference);
		storeHasChanged();
		return true;
	}

	/**
	 * Deletes the preference associated with the specified identifier.
	 * 
	 * @see Preferences#remove(String)
	 * @param id
	 *            the identifier
	 * @return <code>true</code> if successful, else <code>false</code>. If the
	 *         <code>id</code> did not exist, <code>false</code> is returned.
	 */
	public boolean deletePreference(String id) {
		boolean success = false;
		try {
			prefs.remove(id);
			success = true;
		} catch (NullPointerException e) {
			success = false;
		} catch (IllegalStateException e) {
			success = false;
		}
		storeHasChanged();
		return success;
	}

	/**
	 * Returns the default value for the preference associated with the
	 * specified identifier.
	 * 
	 * @param id
	 *            the identifier
	 * @return the default value
	 */
	public String getDefault(String id) {
		return defaults.get(id);
	}

	/**
	 * Sets the preference associated with the given identifier back to its
	 * default value. If the preference identified by <code>id</code> does not
	 * have a default value registered with the store, nothing occurs.
	 * 
	 * @see #setDefault(String, String)
	 * @param id
	 *            the identifier
	 */
	public void reset(String id) {
		if (defaults.containsKey(id)) {
			prefs.put(id, defaults.get(id));
			storeHasChanged();
		}
	}

	/**
	 * Sets the default value for the preference identified by <code>id</code>.
	 * <p>
	 * When {@link #reset(String)} is called, this is the value the preference
	 * will be set to.
	 * </p>
	 * 
	 * @param id
	 *            the identifier
	 * @param preference
	 *            the new default value for the preference
	 */
	public void setDefault(String id, String preference) {
		defaults.put(id, preference);
	}

	/**
	 * Returns whether the preference associated with the given identifier is
	 * the default value or not.
	 * 
	 * @param name
	 *            the name of the preference
	 * @return <code>true</code> if the preference is the same as the default,
	 *         else <code>false</code>
	 */
	public boolean isDefault(String name) {
		boolean isDefault = false;
		if (name != null) {
			if ((prefs.get(name, null) != null) && defaults.containsKey(name)) {
				isDefault = prefs.get(name, null).equals(defaults.get(name));
			} else if ((prefs.get(name, null) != null)
					|| defaults.containsKey(name)) {
				isDefault = true;
			}
		}
		return isDefault;
	}

	/**
	 * Returns whether the named preference is stored in this preference store.
	 * 
	 * @param name
	 *            the name of the preference
	 * @return <code>true</code> if it exists, else <code>false</code>
	 */
	public boolean contains(String name) {
		return (prefs.get(name, null) != null) || defaults.containsKey(name);
	}

	@Override
	public boolean create(String id, String data) {
		return createPreference(id, data);
	}

	@Override
	public String read(String id) {
		return readPreference(id);
	}

	@Override
	public boolean update(String id, String data) {
		return updatePreference(id, data);
	}

	@Override
	public boolean delete(String id) {
		return deletePreference(id);
	}

	@Override
	public Collection<String> getCollection() {
		return toArrayList();
	}

	@Override
	public String[] toArray() {
		return getInventory();
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = null;
		try {
			list = new ArrayList<String>(prefs.keys().length);
			for (String id : prefs.keys()) {
				list.add(prefs.get(id, null));
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public HashMap<Object, String> toHashMap() {
		HashMap<Object, String> map = null;
		try {
			map = new HashMap<Object, String>(prefs.keys().length);
			for (String id : prefs.keys()) {
				map.put(id, prefs.get(id, null));
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Vector<String> toVector() {
		return new Vector<String>(getCollection());
	}

	/**
	 * Attempts to return <code>Preferences.keys()</code>, but if an error
	 * occurs <code>null</code> is returned.
	 * 
	 * @return the preference keys, or <code>null</code> if an error occurs
	 */
	@Override
	public String[] getInventory() {
		try {
			return prefs.keys();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Attempts to return <code>Preferences.keys().length</code> but if an error
	 * occurs -1 is returned.
	 * 
	 * @return the number of preferences in this store
	 */
	@Override
	public int quantity() {
		try {
			return prefs.keys().length;
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Returns <code>false</code>, because this store does not support loading
	 * from a file.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean load() {
		return false;
	}

	/**
	 * Returns <code>false</code>, because this store does not support loading
	 * from a file.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean load(InputStream is) {
		return false;
	}

	/**
	 * Returns <code>false</code>, because this store does not support saving to
	 * a file. All preferences that are changed are saved automatically via the
	 * {@link Preferences} object.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean save() {
		return false;
	}

	/**
	 * Returns <code>false</code>, because this store does not support saving to
	 * a file. All preferences that are changed are saved automatically via the
	 * {@link Preferences} object.
	 * 
	 * @return <code>false</code>
	 */
	@Override
	public boolean save(OutputStream os) {
		return false;
	}

}