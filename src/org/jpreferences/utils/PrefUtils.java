package org.jpreferences.utils;

import java.util.prefs.Preferences;

/**
 * The <code>PrefUtils</code> consists of public static utility methods whose
 * functionality may not be unique for any particular class.
 * 
 * @author Erich Schroeter
 */
public class PrefUtils {

	/**
	 * Returns the class for the given <code>Object</code>. This handles
	 * catching the <code>ClassNotFoundException</code> thrown by
	 * {@link Class#forName(String)}. If the exception is caught the
	 * {@link Preferences} class is returned.
	 * 
	 * @param obj
	 *            the object whose class to retrieve
	 * @return <code>obj</code>'s class, or the {@link Preferences} class
	 */
	public static Class<?> classFor(Object obj) {
		Class<?> c = null;
		try {
			c = Class.forName(obj.getClass().getCanonicalName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			c = Preferences.class;
		}
		return c;
	}

}
