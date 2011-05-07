package org.jpreferences.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

/**
 * The default preference store stores preferences in a Java properties file.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class DefaultPreferenceStore implements IPreferenceStore {

	/** The preference mapping storage. */
	private Properties properties;
	/** The preference mapping storage for default values. */
	private Properties defaults;
	/**
	 * Indicates whether a value has been changed by <code>setToDefault()</code>
	 * , <code>setDefault(String)</code>, or <code>setValue(String,T)</code>.
	 * Initially <code>false</code>
	 */
	private boolean dirty = false;
	/** The file in which all the preferences are saved. */
	private File store;
	/**
	 * A description of the preferences stored in this store.
	 * <p>
	 * This value is written to the file when the store is saved.
	 * </p>
	 */
	private String description;

	/**
	 * Creates an empty store that without specifying a file.
	 * 
	 * Passes the <code>null</code> to {@link #DefaultPreferenceStore(File)}
	 * constructor.
	 * 
	 * @see #DefaultPreferenceStore(File)
	 */
	public DefaultPreferenceStore() {
		this((File) null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Passes the <i>filename</i> to a new <code>File</code> instance calling
	 * {@link #DefaultPreferenceStore(File)} constructor.
	 * 
	 * @see #DefaultPreferenceStore(File)
	 * 
	 * @param filename
	 *            The file name of the store
	 */
	public DefaultPreferenceStore(String filename) {
		this(new File(filename));
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String)} passing an empty
	 * string as the <i>description</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String)
	 * 
	 * @param file
	 *            The file of the store
	 */
	public DefaultPreferenceStore(File file) {
		this(file, (String) null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String, Properties)} passing
	 * an empty {@link Properties} instance as the <i>defaults</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String, Properties)
	 * 
	 * @param file
	 *            The file of the store
	 * @param description
	 *            A description of the preferences in this store. This
	 *            description is written to the file as a header.
	 */
	public DefaultPreferenceStore(File file, String description) {
		this(file, description, null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String, Properties)} passing
	 * an empty string as <i>description</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String, Properties)
	 * 
	 * @param file
	 *            The file of the store
	 * @param defaults
	 *            The default property values
	 */
	public DefaultPreferenceStore(File file, Properties defaults) {
		this(file, null, defaults);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * @param file
	 *            The file of the store
	 * @param description
	 *            A description of the preferences in this store. This
	 *            description is written to the file as a header.
	 * @param defaults
	 *            The default property values
	 */
	public DefaultPreferenceStore(File file, String description,
			Properties defaults) {
		setFile(file);
		setDescription(description);
		properties = new Properties();
		this.defaults = defaults != null ? defaults : new Properties();
		load();
	}

	/**
	 * Returns the description of this preference store.
	 * 
	 * @see #description
	 * @return the store's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets this store's description.
	 * 
	 * @see #description
	 * 
	 * @param description
	 *            a description of the preferences in this store
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the file where preferences are stored.
	 * 
	 * @see #save()
	 * 
	 * @param file
	 *            the file
	 */
	public void setFile(File file) {
		store = file;
		storeHasChanged();
	}

	/**
	 * Returns the file which stores the preferences.
	 * 
	 * @return the file
	 */
	public File getFile() {
		return store;
	}

	/**
	 * Simply tags the store as dirty, thus informing {@link #save()} and
	 * {@link #save(OutputStream)} to save the data.
	 * 
	 * @see #save()
	 * @see #save(OutputStream)
	 */
	private void storeHasChanged() {
		dirty = true;
	}

	/**
	 * Returns whether or not the store has changed and needs to be saved. This
	 * is used to prevent over using the {@link #save()} method.
	 * 
	 * @return <code>True</code> if the store needs to be saved,
	 *         <code>False</code> if not
	 */
	private boolean needsSaving() {
		return dirty;
	}

	/**
	 * Simply tags the store as not dirty anymore, thus preventing resource
	 * inefficiency by not over saving.
	 * 
	 * @see #save(OutputStream)
	 */
	private void storeSaved() {
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
	 * @see Properties#setProperty(String, String)
	 * @param id
	 *            the identifier
	 * @param preference
	 *            the preference to set
	 * @return <code>True</code> if successful, else <code>False</code>
	 */
	public boolean createPreference(String id, String preference) {
		properties.setProperty(id, preference);
		storeHasChanged();
		return true;
	}

	/**
	 * Reads and returns the preference associated with the given identifier in
	 * the store.
	 * 
	 * @see Properties#getProperty(String)
	 * @param id
	 *            the identifier
	 * @return the preference
	 */
	public String readPreference(String id) {
		String preference = null;
		if ((preference = properties.getProperty(id)) == null) {
			preference = defaults.getProperty(id);
		}
		return preference;
	}

	/**
	 * Updates the preference associated with the specified identifier to the
	 * given preference.
	 * 
	 * @see Properties#setProperty(String, String)
	 * @param id
	 *            the identifier
	 * @param preference
	 *            the preference
	 * @return <code>True</code> if successful, else <code>False</code>
	 */
	public boolean updatePreference(String id, String preference) {
		properties.setProperty(id, preference);
		storeHasChanged();
		return true;
	}

	/**
	 * Deletes the preference associated with the specified identifier.
	 * 
	 * @see Properties#remove(Object)
	 * @param id
	 *            the identifier
	 * @return <code>True</code> if successful, else <code>False</code>. If the
	 *         <code>id</code> did not exist, <code>False</code> is returned.
	 */
	public boolean deletePreference(String id) {
		boolean success = properties.remove(id) != null;
		storeHasChanged();
		return success;
	}

	/**
	 * Returns the default value for the preference associated with the
	 * specified identifier.
	 * 
	 * @see Properties#getProperty(String)
	 * @param id
	 *            the identifier
	 * @return the default value
	 */
	public String getDefault(String id) {
		return defaults.getProperty(id);
	}

	/**
	 * Sets the preference associated with the given identifier back to its
	 * default value.
	 * 
	 * @see #setDefault(String, String)
	 * @param id
	 *            the identifier
	 */
	public void reset(String id) {
		if (defaults.containsKey(id)) {
			properties.remove(id);
			storeHasChanged();
		}
	}

	/**
	 * Sets the default value for the preference associated with the given
	 * identifier.
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
		defaults.setProperty(id, preference);
	}

	/**
	 * Returns whether the preference associated with the given identifier is
	 * the default value or not.
	 * 
	 * @param name
	 *            the name of the preference
	 * @return <code>True</code> if the preference is the same as the default,
	 *         else <code>False</code>
	 */
	public boolean isDefault(String name) {
		boolean isDefault = false;
		if (name != null) {
			if (properties.containsKey(name) && defaults.containsKey(name)) {
				isDefault = properties.getProperty(name).equals(
						defaults.getProperty(name));
			} else if (properties.containsKey(name)
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
	 * @return <code>True</code> if it exists, else <code>False</code>
	 */
	public boolean contains(String name) {
		return properties.containsKey(name) || defaults.containsKey(name);
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
		ArrayList<String> list = new ArrayList<String>(properties.size());
		Enumeration<Object> vals = properties.elements();
		while (vals.hasMoreElements()) {
			String value = (String) vals.nextElement();
			list.add(value);
		}
		return list;
	}

	@Override
	public HashMap<Object, String> toHashMap() {
		HashMap<Object, String> map = new HashMap<Object, String>(
				properties.size());
		@SuppressWarnings("rawtypes")
		Enumeration keys = properties.propertyNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			map.put(key, properties.getProperty(key));
		}
		return map;
	}

	@Override
	public Vector<String> toVector() {
		return new Vector<String>(getCollection());
	}

	@Override
	public String[] getInventory() {
		return properties.values().toArray(new String[properties.size()]);
	}

	@Override
	public int quantity() {
		return properties.size();
	}

	@Override
	public boolean load() {
		boolean success = false;
		if (store != null && store.exists()) {
			try {
				FileInputStream is = new FileInputStream(store);
				success = load(is);
				is.close();
			} catch (FileNotFoundException e) {
				success = false;
			} catch (IOException e) {
				success = false;
			}
		}
		return success;
	}

	@Override
	public boolean load(InputStream is) {
		boolean success = false;
		try {
			properties.load(is);
			storeHasChanged();
			success = true;
		} catch (IOException e) {
			success = false;
		}
		return success;
	}

	@Override
	public boolean save() {
		boolean success = true;
		if (store != null && needsSaving()) {
			try {
				store.createNewFile();
				FileOutputStream fos = new FileOutputStream(store);
				success = save(fos);
				fos.close();
			} catch (FileNotFoundException e) {
				success = false;
			} catch (IOException e) {
				success = false;
			}
		}
		return success;
	}

	@Override
	public boolean save(OutputStream os) {
		boolean success = true;
		if (needsSaving()) {
			try {
				properties.store(os, getDescription());
				storeSaved();
				success = true;
			} catch (IOException e) {
				success = false;
			}
		}
		return success;
	}

}