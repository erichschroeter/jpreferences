package test.jpreferences;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.storage.XmlPreferenceStore;
import org.junit.After;

public class XmlPreferenceStoreTest extends AbstractFileSystemTestCase {

	@After
	protected void tearDown() throws Exception {
		cleanupTestRoot();
	}
	
	/**
	 * Tests to make sure <code>IPreferenceStore</code> does <b>not</b> write the
	 * default values and <b>does</b> write the non-default values when saving.
	 * <p>
	 * The test is performed in the following steps:
	 * <ol>
	 * <li>create <code>IPreferenceStore</code> with defaults</li>
	 * <li>store some non-default preferences</li>
	 * <li>save <code>IPreferenceStore</code> (save to
	 * <i>test_name</i>.test)</li>
	 * <li>create new <code>IPreferenceStore</code> with
	 * <i>test_name</i>.test and no defaults</li>
	 * <li>verify defaults don't exist in new <code>IPreferenceStore</code></li>
	 * <li>verify non-default preferences exist and are the same as the values
	 * in the first <code>IPreferenceStore</code>
	 * </ol>
	 * </p>
	 * 
	 * @throws IOException
	 *             if the test file cannot be created
	 */
	public void testhDefaultsNotWritten()
			throws IOException {
		File file = createFile("DefaultsNotWritten.test");
		
		Map<String, String> defaults = new HashMap<String, String>();
		defaults.put("nikola", "tesla");
		defaults.put("albert", "einstein");
		defaults.put("paul", "dirac");

		IPreferenceStore store1 = new XmlPreferenceStore(file, defaults);
		store1.create("erich", "schroeter");
		store1.createPreference("tom", "bearden");
		store1.save();

		IPreferenceStore store2 = new XmlPreferenceStore(file);
		assertEquals(null, store2.read("nikola"));
		assertEquals(null, store2.read("albert"));
		assertEquals(null, store2.read("paul"));
		assertEquals(store1.read("erich"), store2.read("erich"));
		assertEquals(store1.read("tom"), store2.read("tom"));
	}

	/**
	 * Tests to make sure <code>IPreferenceStore</code> writes
	 * non-default values when saving.
	 * <p>
	 * The test is performed in the following steps:
	 * <ol>
	 * <li>create <code>IPreferenceStore</code> without defaults</li>
	 * <li>store some preferences</li>
	 * <li>save <code>IPreferenceStore</code> (save to
	 * <i>test_name</i>.test)</li>
	 * <li>create new <code>IPreferenceStore</code> with
	 * <i>test_name</i>.test</li>
	 * <li>verify values exist in new <code>IPreferenceStore</code></li>
	 * </ol>
	 * </p>
	 * 
	 * @throws IOException
	 *             if the test file cannot be created
	 */
	public void testPropertiesWritten() throws IOException {

		File file = createFile("PropertiesWritten.test");
		IPreferenceStore store = new XmlPreferenceStore(file);
		store.create("erich", "schroeter");
		store.createPreference("nikola", "tesla");
		store.save();

		store = new XmlPreferenceStore(file);
		assertEquals("schroeter", store.read("erich"));
		assertEquals("tesla", store.read("nikola"));
	}
}
