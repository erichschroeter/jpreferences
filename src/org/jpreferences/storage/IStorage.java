package org.jpreferences.storage;

import java.io.IOException;
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
	 * @exception java.io.IOException if there is a problem saving this store
	 */
	public boolean save()
	  throws IOException;

	/**
	 * Saves the objects in the storage to the specified output stream.
	 * 
	 * @see #save()
	 * 
	 * @param os
	 * @exception java.io.IOException if there is a problem saving this store
	 */
	public boolean save(OutputStream os)
	  throws IOException;

	/**
	 * Loads objects into the storage from a default input stream.
	 * 
	 * @see #load(InputStream)
	 * @exception IOException IOException
	 */
	public boolean load()
	  throws IOException;

	/**
	 * Loads the objects from the given input stream into the storage.
	 * 
	 * @see #load()
	 * 
	 * @param is    The input stream
	 * @exception IOException IOException
	 */
	public boolean load(InputStream is)
	  throws IOException;

}