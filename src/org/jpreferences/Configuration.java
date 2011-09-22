package org.jpreferences;

/**
 * The <code>Configuration</code> class provides methods which control the
 * features to be enabled in the {@link PreferenceDialog}. This class provides a
 * default configuration, but the user may override the methods in this class to
 * customize the features.
 * <p>
 * The <code>Configuration</code> implements the <a
 * href="http://en.wikipedia.org/wiki/Builder_pattern">Builder Pattern</a> to
 * allow the user to build a configuration on the fly.
 * <p>
 * <h1>Features</h1>
 * <table>
 * <thead>
 * <tr>
 * <th>Feature</th>
 * <th>Description</th>
 * </tr>
 * </thead><tbody>
 * <tr>
 * <td></td>
 * <td></td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author Erich Schroeter
 */
public class Configuration {

	/** Whether the search feature is enabled or disabled. */
	private boolean searchEnabled;

	/**
	 * Constructs a default <code>Configuration</code>.
	 */
	public Configuration() {

	}

	/**
	 * Enables or disables the search feature.
	 * 
	 * @param enable
	 *            <code>true</code> to enable the feature, <code>false</code> to
	 *            disable
	 * @return the configuration
	 */
	public Configuration enableSearch(boolean enable) {
		searchEnabled = enable;
		return this;
	}

	/**
	 * Returns whether the search feature is enabled or disabled.
	 * 
	 * @return <code>true</code> if the feature is enabled, else
	 *         <code>false</code>
	 */
	public boolean isSearchEnabled() {
		return searchEnabled;
	}
	
	

}
