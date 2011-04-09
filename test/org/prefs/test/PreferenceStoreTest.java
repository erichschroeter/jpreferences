package org.prefs.test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.prefs.PreferenceStore;


public class PreferenceStoreTest extends AbstractFileSystemTestCase {

	// Keys
	private final String NAME = "name";
	private final String PLANET = "planet";
	private final String KEY_DEFAULT_A = "default_a";
	private final String KEY_DEFAULT_B = "default_b";
	private final String KEY_DEFAULT_C = "default_c";
	// Values
	private final String VAL_DEFAULT_A = "tesla";
	private final String VAL_DEFAULT_B = "bearden";
	private final String VAL_DEFAULT_C = "fogal";

	/**
	 * Tests to make sure <code>PreferenceStore</code> does not write the
	 * default values when saving.
	 * <p>
	 * The test is performed in the following steps:
	 * <ol>
	 * <li>create <code>PreferenceStore</code> with defaults</li>
	 * <li>save <code>PreferenceStore</code> (save to
	 * <i>test_name</i>.properties)</li>
	 * <li>create new <code>PreferenceStore</code> with
	 * <i>test_name</i>.properties and no defaults</li>
	 * <li>verify defaults don't exist in new <code>PreferenceStore</code></li>
	 * </ol>
	 * </p>
	 * 
	 * @throws IOException
	 *             if the test file cannot be created
	 */
	public void testDefaultsNotWritten() throws IOException {

		File file = createFile("testDefaultsNotWritten.properties");
		Properties defaults = new Properties();
		defaults.setProperty(KEY_DEFAULT_A, VAL_DEFAULT_A);
		defaults.setProperty(KEY_DEFAULT_B, VAL_DEFAULT_B);
		defaults.setProperty(KEY_DEFAULT_C, VAL_DEFAULT_C);
		PreferenceStore store1 = new PreferenceStore(file, defaults);
		store1.save();

		PreferenceStore store2 = new PreferenceStore(file);
		assertEquals(null, store2.getValue(KEY_DEFAULT_A));
		assertEquals(null, store2.getValue(VAL_DEFAULT_B));
		assertEquals(null, store2.getValue(VAL_DEFAULT_C));
	}

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
	public void testPropertiesWrittenWithDefaultsNotWritten()
			throws IOException {
		
		File file = createFile("testPropertiesWrittenDefaultsNotWritten.properties");
		Properties defaults = new Properties();
		defaults.setProperty(KEY_DEFAULT_A, VAL_DEFAULT_A);
		defaults.setProperty(KEY_DEFAULT_B, VAL_DEFAULT_B);
		defaults.setProperty(KEY_DEFAULT_C, VAL_DEFAULT_C);
		PreferenceStore store1 = new PreferenceStore(file, defaults);
		store1.setValue(NAME, "erich");
		store1.setValue(PLANET, "Earth");
		store1.save();

		PreferenceStore store2 = new PreferenceStore(file);
		assertEquals(null, store2.getValue(KEY_DEFAULT_A));
		assertEquals(null, store2.getValue(VAL_DEFAULT_B));
		assertEquals(null, store2.getValue(VAL_DEFAULT_C));
		assertEquals(store1.getValue(NAME), store2.getValue(NAME));
		assertEquals(store1.getValue(PLANET), store2.getValue(PLANET));
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

		File file = createFile("testPropertiesWritten.properties");
		PreferenceStore store = new PreferenceStore(file);
		store.setValue(NAME, "erich");
		store.setValue(PLANET, "Earth");
		store.save();

		store = new PreferenceStore(file);
		assertEquals("erich", store.getValue(NAME));
		assertEquals("Earth", store.getValue(PLANET));
	}
}
