package test.jpreferences;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.jpreferences.storage.DefaultPreferenceStore;
import org.jpreferences.storage.IPreferenceStore;

public class PreferenceStoreTest extends AbstractFileSystemTestCase {

	/**
	 * Tests to make sure <code>PreferenceStore</code> does <b>not</b> write the
	 * default values and <b>does</b> write the non-default values when saving.
	 * <p>
	 * The test is performed in the following steps:
	 * <ol>
	 * <li>create <code>PreferenceStore</code> with defaults</li>
	 * <li>store some non-default preferences</li>
	 * <li>save <code>PreferenceStore</code> (save to
	 * <i>test_name</i>.properties)</li>
	 * <li>create new <code>PreferenceStore</code> with
	 * <i>test_name</i>.properties and no defaults</li>
	 * <li>verify defaults don't exist in new <code>PreferenceStore</code></li>
	 * <li>verify non-default preferences exist and are the same as the values
	 * in the first <code>PreferenceStore</code>
	 * </ol>
	 * </p>
	 * 
	 * @throws IOException
	 *             if the test file cannot be created
	 */
	public void testhDefaultsNotWritten()
			throws IOException {
		File file = createFile("DefaultsNotWritten.test");
		
		Properties defaults = new Properties();
		defaults.setProperty("nikola", "tesla");
		defaults.setProperty("albert", "einstein");
		defaults.setProperty("paul", "dirac");

		IPreferenceStore store1 = new DefaultPreferenceStore(file, defaults);
		store1.create("erich", "schroeter");
		store1.save();

		IPreferenceStore store2 = new DefaultPreferenceStore(file);
		assertEquals(null, store2.read("nikola"));
		assertEquals(null, store2.read("albert"));
		assertEquals(null, store2.read("paul"));
		assertEquals(store1.read("erich"), store2.read("erich"));
	}

	/**
	 * Tests to make sure <code>PreferenceStore</code> writes
	 * non-default values when saving.
	 * <p>
	 * The test is performed in the following steps:
	 * <ol>
	 * <li>create <code>PreferenceStore</code> without defaults</li>
	 * <li>store some preferences</li>
	 * <li>save <code>PreferenceStore</code> (save to
	 * <i>test_name</i>.properties)</li>
	 * <li>create new <code>PreferenceStore</code> with
	 * <i>test_name</i>.properties</li>
	 * <li>verify values exist in new <code>PreferenceStore</code></li>
	 * </ol>
	 * </p>
	 * 
	 * @throws IOException
	 *             if the test file cannot be created
	 */
	public void testPropertiesWritten() throws IOException {

		File file = createFile("PropertiesWritten.test");
		IPreferenceStore store = new DefaultPreferenceStore(file);
		store.create("erich", "schroeter");
		store.create("nikola", "tesla");
		store.save();

		store = new DefaultPreferenceStore(file);
		assertEquals("schroeter", store.read("erich"));
		assertEquals("tesla", store.read("nikola"));
	}
}
