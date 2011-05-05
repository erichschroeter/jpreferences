package test.jpreferences;

import javax.swing.Icon;

import junit.framework.TestCase;

public class PreferencePageTest extends TestCase {

	/**
	 * A developer should be able to create use the jpreference library by
	 * instantiating the Default implementations where available. This test
	 * makes sure that the Default implementations do not throw any
	 * <code>NullPointerExceptions</code>.
	 * <p>
	 * This test creates instances of <code>DefaultPreferenceNode</code> using
	 * the following constructors:
	 * <ul>
	 * <li>{@link DefaultPreferenceNode#DefaultPreferenceNode()}</li>
	 * <li>{@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage)}</li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, org.jpreferences.PreferenceNode)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, String)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, String, java.awt.Image)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, org.jpreferences.PreferenceNode, String, java.awt.Image)}
	 * </li>
	 * </ul>
	 * </p>
	 */
	public void testNullDefaultNodeImpls() {
		
	}

	/**
	 * A developer should be able to create use the jpreference library by
	 * instantiating the Default implementations where available. This test
	 * makes sure that the Default implementations do not throw any
	 * <code>NullPointerExceptions</code>.
	 * <p>
	 * This test creates instances of <code>DefaultPreferencePage</code> using
	 * the following constructors:
	 * <ul>
	 * <li>{@link DefaultPreferencePage#DefaultPreferencePage()}</li>
	 * <li>
	 * {@link DefaultPreferencePage#DefaultPreferencePage(PreferenceManager)}</li>
	 * <li>
	 * {@link DefaultPreferencePage#DefaultPreferencePage(PreferenceManager, String, String)}
	 * </li>
	 * </ul>
	 * </p>
	 */
	public void testNullDefaultPageImpls() {
		
	}

}
