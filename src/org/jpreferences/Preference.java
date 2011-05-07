package org.jpreferences;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Contains methods for accessing properties required by a preference.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD})
public @interface Preference {

	/**
	 * The identifier for the preference.
	 */
	public String id();

	/**
	 * The value of the preference.
	 */
	public String value();

}