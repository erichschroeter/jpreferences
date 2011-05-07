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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jpreferences.xml.ObjectFactory;
import org.jpreferences.xml.Preference;
import org.jpreferences.xml.Preferences;
import org.xml.sax.SAXException;

public class XmlPreferenceStore implements IPreferenceStore {

	/** The preference mapping storage. */
	private Map<String, String> properties;
	/** The preference mapping storage for default values. */
	private Map<String, String> defaults;
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
	/** The schema used to validate the XML file */
	private Schema schema;

	/**
	 * Creates an empty store that without specifying a file.
	 * 
	 * Passes the <code>null</code> to {@link #XmlPreferenceStore(File)}
	 * constructor.
	 * 
	 * @see #XmlPreferenceStore(File)
	 */
	public XmlPreferenceStore() {
		this((File) null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Passes the <i>filename</i> to a new <code>File</code> instance calling
	 * {@link #XmlPreferenceStore(File)} constructor.
	 * 
	 * @see #XmlPreferenceStore(File)
	 * 
	 * @param filename
	 *            The file name of the store
	 */
	public XmlPreferenceStore(String filename) {
		this(new File(filename));
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #XmlPreferenceStore(File, String)} passing an empty string
	 * as the <i>description</i>.
	 * 
	 * @see #XmlPreferenceStore(File, String)
	 * 
	 * @param file
	 *            The file of the store
	 */
	public XmlPreferenceStore(File file) {
		this(file, (String) null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #XmlPreferenceStore(File, String, Properties)} passing an
	 * empty {@link Properties} instance as the <i>defaults</i>.
	 * 
	 * @see #XmlPreferenceStore(File, String, Properties)
	 * 
	 * @param file
	 *            The file of the store
	 * @param description
	 *            A description of the preferences in this store. This
	 *            description is written to the file as a header.
	 */
	public XmlPreferenceStore(File file, String description) {
		this(file, description, null);
	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #XmlPreferenceStore(File, String, Properties)} passing an
	 * empty string as <i>description</i>.
	 * 
	 * @see #XmlPreferenceStore(File, String, Properties)
	 * 
	 * @param file
	 *            The file of the store
	 * @param defaults
	 *            The default property values
	 */
	public XmlPreferenceStore(File file, Map<String, String> defaults) {
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
	public XmlPreferenceStore(File file, String description,
			Map<String, String> defaults) {
		setFile(file);
		setDescription(description);
		properties = new HashMap<String, String>();
		this.defaults = defaults != null ? defaults
				: new HashMap<String, String>();

		SchemaFactory f = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			schema = f.newSchema(new File("res/preference.xsd"));
		} catch (SAXException e) {
			e.printStackTrace();
		}

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
	public Vector<String> toVector() {
		return new Vector<String>(properties.values());
	}

	@Override
	public ArrayList<String> toArrayList() {
		return new ArrayList<String>(properties.values());
	}

	@Override
	public HashMap<Object, String> toHashMap() {
		HashMap<Object, String> map = new HashMap<Object, String>();
		map.putAll(properties);
		return map;
	}

	@Override
	public String[] toArray() {
		return getInventory();
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
	public boolean contains(String name) {
		return properties.containsKey(name) || defaults.containsKey(name);
	}

	@Override
	public boolean isDefault(String name) {
		boolean isDefault = false;
		if (name != null) {
			if (properties.containsKey(name) && defaults.containsKey(name)) {
				isDefault = properties.get(name).equals(defaults.get(name));
			} else if (properties.containsKey(name)
					|| defaults.containsKey(name)) {
				isDefault = true;
			}
		}
		return isDefault;
	}

	@Override
	public String getDefault(String id) {
		return defaults.get(id);
	}

	@Override
	public void setDefault(String id, String preference) {
		defaults.put(id, preference);
	}

	@Override
	public void reset(String id) {
		if (defaults.containsKey(id)) {
			properties.remove(id);
			storeHasChanged();
		}
	}

	@Override
	public boolean createPreference(String id, String preference) {
		properties.put(id, preference);
		storeHasChanged();
		return true;
	}

	@Override
	public String readPreference(String id) {
		String preference = null;
		if ((preference = properties.get(id)) == null) {
			preference = defaults.get(id);
		}
		return preference;
	}

	@Override
	public boolean updatePreference(String id, String preference) {
		properties.put(id, preference);
		storeHasChanged();
		return true;
	}

	@Override
	public boolean deletePreference(String id) {
		boolean success = properties.remove(id) != null;
		storeHasChanged();
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

	@SuppressWarnings("rawtypes")
	@Override
	public boolean save(OutputStream os) {
		boolean success = true;
		try {
			ObjectFactory factory = new ObjectFactory();

			Preferences preferencesElement = factory.createPreferences();
			Preference preference;

			Iterator keys = properties.entrySet().iterator();
			while (keys.hasNext()) {
				Entry set = (Entry) keys.next();
				String id = (String) set.getKey();
				String value = (String) set.getValue();

				preference = factory.createPreference();
				preference.setId(id);
				preference.setValue(value);

				preferencesElement.getPreference().add(preference);
			}

			JAXBContext context = JAXBContext
					.newInstance("org.jpreferences.xml");
			Marshaller m = context.createMarshaller();
			m.setSchema(schema);
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(preferencesElement, os);

			storeSaved();
			success = true;
		} catch (JAXBException e) {
			success = false;
		}
		return success;
	}

	@Override
	public boolean load() {
		boolean success = false;
		try {
			FileInputStream is = new FileInputStream(store);
			success = load(is);
			is.close();
		} catch (FileNotFoundException e) {
			success = false;
		} catch (IOException e) {
			success = false;
		}
		return success;
	}

	@Override
	public boolean load(InputStream is) {
		boolean success = true;
		if (store != null && store.exists()) {
			try {
				JAXBContext context = JAXBContext
						.newInstance("org.jpreferences.xml");
				Unmarshaller m = context.createUnmarshaller();
				m.setSchema(schema);

				Preferences preferencesElement = (Preferences) m.unmarshal(is);

				for (Preference p : preferencesElement.getPreference()) {
					createPreference(p.getId(), p.getValue());
				}
				storeHasChanged();
				success = true;
			} catch (JAXBException e) {
				success = false;
			}
		}
		return success;
	}

}
