package org.prefs.test;

import java.awt.Image;

import junit.framework.TestCase;

import org.prefs.DefaultPreferenceNode;
import org.prefs.DefaultPreferencePage;
import org.prefs.PreferenceManager;
import org.prefs.PreferenceNode;
import org.prefs.PreferencePage;

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
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, org.prefs.PreferenceNode)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, String)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, String, java.awt.Image)}
	 * </li>
	 * <li>
	 * {@link DefaultPreferenceNode#DefaultPreferenceNode(PreferencePage, org.prefs.PreferenceNode, String, java.awt.Image)}
	 * </li>
	 * </ul>
	 * </p>
	 */
	public void testNullDefaultNodeImpls() {
		PreferencePage page = new DefaultPreferencePage();

		PreferenceNode node;
		node = new DefaultPreferenceNode();
		node = new DefaultPreferenceNode((PreferencePage) null);
		node = new DefaultPreferenceNode((PreferencePage) null,
				(PreferenceNode) null);
		node = new DefaultPreferenceNode((PreferencePage) null, (String) null);
		node = new DefaultPreferenceNode((PreferencePage) null, (String) null,
				(Image) null);
		node = new DefaultPreferenceNode((PreferencePage) null,
				(PreferenceNode) null, (String) null, (Image) null);

		node = new DefaultPreferenceNode(page);
		node = new DefaultPreferenceNode(page, new DefaultPreferenceNode());
		node = new DefaultPreferenceNode(page, "");
		node = new DefaultPreferenceNode(page, "", (Image) null);
		node = new DefaultPreferenceNode(page, new DefaultPreferenceNode(), "",
				(Image) null);

		node.getLabelText();
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
		PreferenceManager manager = new PreferenceManager();

		PreferencePage page;

		page = new DefaultPreferencePage();
		page = new DefaultPreferencePage((PreferenceManager) null);
		page = new DefaultPreferencePage((PreferenceManager) null,
				(String) null, (String) null);
		page = new DefaultPreferencePage(manager);
		page = new DefaultPreferencePage(manager, "", "");
		
		page.getTitle();
	}

}
