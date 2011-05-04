package org.jpreferences.storage;

/**
 * Any object wishing to perform the basic CRUD actions (<i>Create</i>,
 * <i>Read</i>, <i>Update</i>, <i>Delete</i>) may implement this interface.  A
 * suggestion for derived classes is to use these methods as wrapper methods
 * around methods which custom argument types to provide ease of use for others.
 * For example, consider {@link DefaultProfileStore}, the {@link #create(String,
 * Object)} wraps {@link DefaultProfileStore#createProfile(String, IProfile)} by
 * casting the <code>Object</code> to a <code>IProfile</code> object.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface ICRUD<T> {

	/**
	 * Creates an entry in an storage object.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 * @param data    the data
	 */
	public boolean create(String id, T data);

	/**
	 * Reads an object stored in a storage object.
	 * 
	 * @return the <code>T</code> object associated with the specified identifier,
	 * else <code>null</code
	 * 
	 * @param id    the identifier
	 */
	public T read(String id);

	/**
	 * Updates an object stored in a storage object.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 * @param data    the data
	 */
	public boolean update(String id, T data);

	/**
	 * Deletes the specified object in a storage object.
	 * 
	 * @return <code>True</code> if successful, else <code>False</code>
	 * 
	 * @param id    the identifier
	 */
	public boolean delete(String id);

}