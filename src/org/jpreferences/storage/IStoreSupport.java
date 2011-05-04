package org.jpreferences.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

/**
 * Contains several convenience methods for retrieving the objects stored inside
 * an {@link IStore}.  Note that these methods may not be the most efficient modes
 * for retrieving data within a store.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IStoreSupport<T> {

	/**
	 * Returns the data within a store in a <code>Collection</code>.
	 * @return a <code>Collection</code>
	 */
	public Collection<T> getCollection();

	/**
	 * Returns the data within a store in a <code>Vector</code>.
	 * @return a <code>Vector</code>
	 */
	public Vector<T> toVector();

	/**
	 * Returns the data within a store in an <code>ArrayList</code>.
	 * @return an <code>ArrayList</code>
	 */
	public ArrayList<T> toArrayList();

	/**
	 * Returns the data within a store in a <code>HashMap</code>. The key is decided
	 * by the implementor (typically a string), and the value is object type stored in
	 * the store.
	 * @return a <code>HashMap</code>
	 */
	public HashMap<Object, T> toHashMap();

	/**
	 * Returns the data within a store in an <code>Array</code>.
	 * @return an <code>Array</code>
	 */
	public T[] toArray();

}