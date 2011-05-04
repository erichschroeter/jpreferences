package org.jpreferences;

/**
 * Contains methods for accessing properties required by a preference.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreference {

	/**
	 * The identifier for the preference.
	 */
	public String id();

	/**
	 * The value of the preference.
	 */
	public String value();

}