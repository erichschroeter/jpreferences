package org.jpreferences.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

/**
 * The default preference store stores preferences in a Java properties file.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class DefaultPreferenceStore implements IPreferenceStore {

	/**
	 * The preference mapping storage.
	 */
	private Properties properties;
	/**
	 * The preference mapping storage for default values.
	 */
	private Properties defaultProperties;
	/**
	 * Indicates whether a value has been changed by <code>setToDefault()</code> ,
	 * <code>setDefault(String)</code>, or <code>setValue(String,T)</code>. Initially
	 * <code>false</code>
	 */
	private boolean dirty = false;
	/**
	 * The file in which all the preferences are saved.
	 */
	private File store;
	/**
	 * A description of the preferences stored in this store.
	 * <p> This value is written to the file when the store is saved.
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
	public DefaultPreferenceStore(){

	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Passes the <i>filename</i> to a new <code>File</code> instance calling {@link
	 * #DefaultPreferenceStore(File)} constructor.
	 * 
	 * @see #DefaultPreferenceStore(File)
	 * 
	 * @param filename    The file name of the store
	 */
	public DefaultPreferenceStore(String filename){

	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String)} passing an empty string as
	 * the <i>description</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String)}
	 * 
	 * @param file    The file of the store
	 */
	public DefaultPreferenceStore(File file){

	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String, Properties)} passing an
	 * empty {@link Properties} instance as the <i>defaults</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String, Properties)
	 * 
	 * @param file    The file of the store
	 * @param description    A description of the preferences in this store. This
	 * description is written to the file as a header.
	 */
	public DefaultPreferenceStore(File file, String description){

	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * Calls {@link #DefaultPreferenceStore(File, String, Properties)} passing an
	 * empty string as <i>description</i>.
	 * 
	 * @see #DefaultPreferenceStore(File, String, Properties)
	 * 
	 * @param file    The file of the store
	 * @param defaults    The default property values
	 */
	public DefaultPreferenceStore(File file, Properties defaults){

	}

	/**
	 * Creates an empty store that loads and saves to the specified file.
	 * 
	 * @param file    The file of the store
	 * @param description    A description of the preferences in this store. This
	 * description is written to the file as a header.
	 * @param defaults    The default property values
	 */
	public DefaultPreferenceStore(File file, String description, Properties defaults){

	}

	/**
	 * Returns the description of this preference store.
	 * 
	 * @see #description
	 * @return the store's description
	 */
	public String getDescription(){
		return "";
	}

	/**
	 * Sets this store's description.
	 * 
	 * @see #description
	 * 
	 * @param description    a description of the preferences in this store
	 */
	public void setDescription(String description){

	}

	/**
	 * Sets the file where preferences are stored.
	 * 
	 * @see #save()
	 * 
	 * @param file    the file
	 */
	public void setFile(File file){

	}

	/**
	 * Returns the file which stores the preferences.
	 * 
	 * @return the file
	 */
	public File getFile(){
		return null;
	}

	/**
	 * Reads and returns the preference associated with the given identifier in the
	 * store.
	 * 
	 * @return the preference
	 * 
	 * @param id    the identifier
	 */
	public String readPreference(String id){
		return "";
	}

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
	public boolean createPreference(String id, String preference){
		return false;
	}

	/**
	 * Returns the default value for the preference associated with the specified
	 * identifier.
	 * 
	 * @return the default value
	 * 
	 * @param id    the identifier
	 */
	public String getDefault(String id){
		return "";
	}

	/**
	 * Sets the preference associated with the given identifier back to its default
	 * value.
	 * 
	 * @see #setDefault(String, String)
	 * 
	 * @param id    the identifier
	 */
	public void reset(String id){

	}

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
	public void setDefault(String id, String preference){

	}

	/**
	 * Returns whether the preference associated with the given identifier is the
	 * default value or not.
	 * 
	 * @return <code>True</code> if the preference is the same as the default, else
	 * <code>False</code>
	 * 
	 * @param name    the name of the preference
	 */
	public boolean isDefault(String name){
		return false;
	}

	/**
	 * Returns whether the named preference is stored in this preference store.
	 * 
	 * @return <code>True</code> if it exists, else <code>False</code>
	 * 
	 * @param name    the name of the preference
	 */
	public boolean contains(String name){
		return false;
	}

	/**
	 * Updates the preference associated with the specified identifier to the given
	 * preference.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 * @param preference    the preference
	 */
	public boolean updatePreference(String id, String preference){
		return false;
	}

	/**
	 * Deletes the preference associated with the specified identifier.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 */
	public boolean deletePreference(String id){
		return false;
	}

	@Override
	public boolean create(String id, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String id, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<String> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Object, String> toHashMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<String> toVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int quantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean load() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean load(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}