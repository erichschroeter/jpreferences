package org.prefs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * IPreferencePersistentStore is a preference store that can be saved.
 */
public interface IPreferencePersistentStorage extends IPreferenceStore {

	/**
	 * Saves the preferences known to this preference store to the file from
	 * which they were originally loaded.
	 * 
	 * <p>
	 * Calls <code>save(OutputStream)</code>.
	 * </p>
	 * 
	 * @see IPreferencePersistentStorage#save(OutputStream)
	 * @exception java.io.IOException
	 *                if there is a problem saving this store
	 */
	public void save() throws IOException;
	
	/**
	 * Saves the preferences known to this preference store to the file from
	 * which they were originally loaded.
	 * 
	 * @exception java.io.IOException
	 *                if there is a problem saving this store
	 */
	public void save(OutputStream out) throws IOException;
	
	/**
	 * Loads this preference store from the file established by <code>setFile</code>
	 * 
	 * @see IPreferencePersistentStorage#load(InputStream)
	 * @throws IOException
	 */
	public void load() throws IOException;
	
	/**
	 * Loads this preference store from the given input stream.
	 *  
	 * @param in The input stream
	 * @throws IOException
	 */
	public void load(InputStream in) throws IOException;
	
	/**
	 * Sets the file that stores the preferences.
	 * 
	 * @param file The file
	 */
	public void setStoreFile(File file);
	
	/**
	 * Returns the file that stores the preferences.
	 * 
	 * @return The file which stores the preferences
	 */
	public File getStoreFile();
}
