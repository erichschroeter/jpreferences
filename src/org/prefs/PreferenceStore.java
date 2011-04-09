package org.prefs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

public class PreferenceStore implements IPreferencePersistentStorage {

	/**
	 * The preference mapping storage.
	 */
	private Properties properties;

	/**
	 * The preference mapping storage for default values.
	 */
	private Properties defaultProperties;

	/**
	 * Indicates whether a value has been changed by <code>setToDefault()</code>
	 * , <code>setDefault(String)</code>, or <code>setValue(String,T)</code>.
	 * Initially <code>false</code>
	 */
	private boolean dirty = false;

	/**
	 * The file in which all the preferences are saved.
	 */
	private File store;

	/**
	 * A description of the preferences stored in this store.
	 * 
	 * <p>
	 * This value is written to the file when the store is saved.
	 * </p>
	 */
	private String description;

	/**
	 * Creates an empty store that loads and saves to the file.
	 * <p>
	 * Calls <code>PreferenceStore(new File(filename))</code>
	 * </p>
	 * 
	 * @see {@link PreferenceStore#PreferenceStore(File)}
	 * @param filename
	 *            The file name of the store
	 */
	public PreferenceStore(String filename) {
		this(new File(filename));
	}

	/**
	 * Creates an empty store that loads and saves to the file.
	 * <p>
	 * Calls <code>PreferenceStore(file, "")</code>
	 * </p>
	 * 
	 * @see {@link PreferenceStore#PreferenceStore(File, String)}
	 * @param file
	 *            The file of the store
	 */
	public PreferenceStore(File file) {
		this(file, "");
	}

	/**
	 * Creates an empty store that loads and saves to the file.
	 * <p>
	 * Calls <code>PreferenceStore(file, description, null)</code>
	 * </p>
	 * 
	 * @see {@link PreferenceStore#PreferenceStore(File, String, Properties)}
	 * @param file
	 *            The file of the store
	 * @param description
	 *            A description of the preferences in this store. This
	 *            description is written to the file as a header.
	 */
	public PreferenceStore(File file, String description) {
		this(file, description, null);
	}

	/**
	 * Creates an empty store that loads and saves to the file.
	 * <p>
	 * Calls <code>PreferenceStore(file, "", defaults)</code>
	 * </p>
	 * 
	 * @see {@link PreferenceStore#PreferenceStore(File, String, Properties)}
	 * @param file
	 *            The file of the store
	 * @param defaults
	 *            The default property values
	 */
	public PreferenceStore(File file, Properties defaults) {
		this(file, "", defaults);
	}

	/**
	 * Creates an empty store that loads and saves to the file.
	 * <p>
	 * Calls <code>load()</code> and prints a message to standard error if an
	 * error occurs in the process.
	 * </p>
	 * <p>
	 * If the defaults value is <code>null</code> a new <code>Properties</code>
	 * instance is created.
	 * </p>
	 * 
	 * @param file
	 *            The file of the store
	 * @param description
	 *            A description of the preferences in this store. This
	 *            description is written to the file as a header.
	 * @param defaults
	 *            The default property values
	 */
	public PreferenceStore(File file, String description, Properties defaults) {
		defaultProperties = defaults != null ? defaults : new Properties();
		properties = new Properties(defaultProperties);
		setStoreFile(file);
		setDescription(description);
		try {
			load();
		} catch (IOException e) {
			System.err.println("Failed to load preferences from <"
					+ file.getAbsolutePath() + ">");
		}
	}

	/**
	 * @see PreferenceStore#description
	 * @return the store's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @see PreferenceStore#description
	 * @param description
	 *            a description of the preferences in this store
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This is a wrapper method around the <code>Properties.putAll(Map)</code>.
	 * 
	 * @see Properties#putAll(Map)
	 * @param <K>
	 * @param <V>
	 * @param values
	 */
	public <K, V> void setValues(Map<K, V> values) {
		properties.putAll(values);
	}

	//
	// IPreferenceStore members
	//

	@Override
	public boolean contains(String name) {
		return properties.contains(name);
	}

	@Override
	public boolean isDefault(String name) {
		return properties.getProperty(name) == defaultProperties
				.getProperty(name);
	}

	@Override
	public boolean needsSaving() {
		return dirty;
	}

	@Override
	public <T> void setDefault(String name, T value) {
		dirty = true;
		defaultProperties.setProperty(name, value.toString());
	}

	@Override
	public String getDefault(String name) {
		return defaultProperties.getProperty(name);
	}

	@Override
	public void setToDefault(String name) {
		dirty = true;
		properties.setProperty(name, defaultProperties.getProperty(name));
	}

	@Override
	public <T> void setValue(String name, T value) {
		dirty = true;
		properties.setProperty(name, value.toString());
	}

	@Override
	public String getValue(String name) {
		if (!properties.containsKey(name)) {
			return defaultProperties.getProperty(name);
		}
		return properties.getProperty(name);
	}

	//
	// IPreferencePersistentStorage members
	//

	/**
	 * Convenience method for <code>save(OutputStream)</code>.
	 * 
	 * @see PreferenceStore#save(OutputStream)
	 */
	@Override
	public void save() throws IOException {
		save(new FileOutputStream(store));
	}

	@Override
	public void save(OutputStream out) throws IOException {
		dirty = false;
		properties.store(out, description);
	}

	/**
	 * Convenience method for <code>load(InputStream)</code>.
	 * 
	 * @see PreferenceStore#load(InputStream)
	 */
	@Override
	public void load() throws IOException {
		load(new FileInputStream(store));
	}

	@Override
	public void load(InputStream in) throws IOException {
		properties.load(in);
	}

	@Override
	public void setStoreFile(File file) {
		this.store = file;
	}

	@Override
	public File getStoreFile() {
		return store;
	}

}
