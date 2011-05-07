package org.jpreferences.storage;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * A storage is an object that stores other objects. Objects saved in a storage
 * can later be retrieved, modified, and saved again.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IStorage {

	/**
	 * Saves the objects in the storage to a default output stream.
	 * 
	 * This is intended to be a convenience method which calls {@link
	 * #save(OutputStream)}.
	 * 
	 * @see #save(OutputStream)
	 */
	public boolean save();

	/**
	 * Saves the objects in the storage to the specified output stream.
	 * 
	 * @see #save()
	 * 
	 * @param os
	 */
	public boolean save(OutputStream os);

	/**
	 * Loads objects into the storage from a default input stream.
	 * 
	 * @see #load(InputStream)
	 */
	public boolean load();

	/**
	 * Loads the objects from the given input stream into the storage.
	 * 
	 * @see #load()
	 * 
	 * @param is    The input stream
	 */
	public boolean load(InputStream is);

}