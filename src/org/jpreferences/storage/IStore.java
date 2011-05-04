package org.jpreferences.storage;

/**
 * A store is an object which handles saving data in order to persist it.  The
 * interface is intended to be open enough to allow implementors provide several
 * different ways to save data (e.g. XML, Java properties, etc).
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IStore<T> extends IStorage {

	/**
	 * Returns whether the specified store object is already stored in the
	 * <code>IStore</code>.
	 * @return <code>True</code> if already stored, else <code>False</code>
	 * 
	 * @param obj    the profile
	 */
	public boolean contains(T obj);

	/**
	 * Returns an array of store objects currently stored in the <code>IStore</code>.
	 */
	public T[] getInventory();

	/**
	 * Returns the number of objects stored in the store.
	 * @return number of items
	 */
	public int quantity();

}