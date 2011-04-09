package org.prefs;

public interface IPreferenceStore {

	/**
	 * The default value for boolean preferences (<code>false</code>).
	 */
	public static final boolean DEFAULT_BOOLEAN = false;

	/**
	 * The default value for double preferences (<code>0.0</code>).
	 */
	public static final double DEFAULT_DOUBLE = 0.0;

	/**
	 * The default value for float preferences (<code>0.0f</code>).
	 */
	public static final float DEFAULT_FLOAT = 0.0f;

	/**
	 * The default value for int preferences (<code>0</code>).
	 */
	public static final int DEFAULT_INT = 0;

	/**
	 * The default value for long preferences (<code>0L</code>).
	 */
	public static final long DEFAULT_LONG = 0L;

	/**
	 * The default value for String preferences (<code>""</code>).
	 */
	public static final String DEFAULT_STRING = "";

	/**
	 * The string representation used for <code>true</code> (<code>"true"</code>
	 * ).
	 */
	public static final String TRUE = "true";

	/**
	 * The string representation used for <code>false</code> (
	 * <code>"false"</code>).
	 */
	public static final String FALSE = "false";

	/**
	 * Returns whether the named preference is known to this preference store.
	 * 
	 * @param name
	 *            the name of the preference
	 * @return <code>true</code> if either a current value or a default value is
	 *         known for the named preference, and <code>false</code> otherwise
	 */
	public boolean contains(String name);

	/**
	 * Returns whether the current value of the preference with the given name
	 * has the default value.
	 * 
	 * @param name
	 *            the name of the preference
	 * @return <code>true</code> if the preference has a known default value and
	 *         its current value is the same, and <code>false</code> otherwise
	 *         (including the case where the preference is unknown to this
	 *         store)
	 */
	public boolean isDefault(String name);

	/**
	 * Returns whether the current values in this property store require saving.
	 * 
	 * @return <code>true</code> if at least one of values of the preferences
	 *         known to this store has changed and requires saving, and
	 *         <code>false</code> otherwise.
	 */
	public boolean needsSaving();

	/**
	 * Sets the default value for the T-valued preference with the given name.
	 * <p>
	 * Note that the current value of the preference is affected if the
	 * preference's current value was its old default value, in which case it
	 * changes to the new default value. If the preference's current is
	 * different from its old default value, its current value is unaffected. No
	 * property change events are reported by changing default values.
	 * </p>
	 * 
	 * @param name
	 *            the name of the preference
	 * @param value
	 *            the new default value for the preference
	 */
	public <T> void setDefault(String name, T value);

	/**
	 * Sets the current value of the preference with the given name back to its
	 * default value.
	 * <p>
	 * Note that the preferred way of re-initializing a preference to the
	 * appropriate default value is to call <code>setToDefault</code>. This is
	 * implemented by removing the named value from the store, thereby exposing
	 * the default value.
	 * </p>
	 * 
	 * @param name
	 *            the name of the preference
	 */
	public void setToDefault(String name);

	/**
	 * Returns the default value for the given property.
	 * 
	 * @param name
	 *            The name of the property
	 * @return The default value of the property
	 */
	public String getDefault(String name);

	/**
	 * Sets the current value of the T-valued preference with the given name.
	 * <p>
	 * A property change event is reported if the current value of the
	 * preference actually changes from its previous value. In the event object,
	 * the property name is the name of the preference, and the old and new
	 * values are wrapped as objects.
	 * </p>
	 * <p>
	 * Note that the preferred way of re-initializing a preference to its
	 * default value is to call <code>setToDefault</code>.
	 * </p>
	 * 
	 * @param name
	 *            the name of the preference
	 * @param value
	 *            the new current value of the preference
	 */
	public <T> void setValue(String name, T value);

	/**
	 * Returns the value of the specified property.
	 * 
	 * @param name
	 *            The name of the property
	 * @return The stored value of the property
	 */
	public String getValue(String name);
	
}
