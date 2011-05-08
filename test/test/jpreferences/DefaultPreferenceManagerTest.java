package test.jpreferences;

import static org.junit.Assert.*;

import org.jpreferences.DefaultPreferenceManager;
import org.jpreferences.IPreferenceManager;
import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.model.IPreferenceNode;
import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.IPreferencePage;
import org.junit.Test;

public class DefaultPreferenceManagerTest {

	@Test
	public void testSetCurrentPageString() {
		IPreferenceManager mgr = new DefaultPreferenceManager();
		IPreferenceNode test1 = defaultNode(mgr, "test1", "test1",
				"test1 description");
		IPreferenceNode test2 = defaultNode(mgr, "test2", "test2",
				"test2 description");
		IPreferenceNode test3 = defaultNode(mgr, "test3", "test3",
				"test3 description");
		IPreferenceNode test4 = defaultNode(mgr, "test4", "test4",
				"test4 description");
		/**
		 * <pre>
		 * root/
		 * 		test1/
		 * 		test2/
		 * 			  test3/
		 * 					test4/
		 * </pre>
		 */
		mgr.add(test1);
		mgr.add(test2);
		mgr.add(test2, test3);
		mgr.add(test3, test4);

		IPreferencePage current;
		
		mgr.setCurrentPage("root/test2/test3/test4");
		current = mgr.getCurrentPage();
		assertEquals("test4", current.getTitle());
		
		mgr.setCurrentPage("root/test2/test3");
		current = mgr.getCurrentPage();
		assertEquals("test3", current.getTitle());
		
		mgr.setCurrentPage("root/test1");
		current = mgr.getCurrentPage();
		assertEquals("test1", current.getTitle());
		
		mgr.setCurrentPage("root/test2");
		current = mgr.getCurrentPage();
		assertEquals("test2", current.getTitle());
		
	}

	/**
	 * Creates a {@link DefaultPreferenceNode} with a
	 * {@link DefaultPreferencePage}.
	 * <p>
	 * The <code>label</code> is used as the page title and node label.
	 * </p>
	 * 
	 * @param mgr
	 *            the preference manager
	 * @param id
	 *            the node identifier
	 * @param label
	 *            the page title and node label
	 * @param description
	 *            the page description
	 * @return a new {@link DefaultPreferenceNode}
	 */
	private IPreferenceNode defaultNode(IPreferenceManager mgr, String id,
			String label, String description) {
		IPreferencePage page = new DefaultPreferencePage(mgr, label,
				description);
		IPreferenceNode node = null;
		try {
			node = new DefaultPreferenceNode(id, page, label);
		} catch (NullPointerException e) {
			fail(e.getMessage());
		} catch (ConflictingIdentifierException e) {
			fail(e.getMessage());
		}
		return node;
	}
}
