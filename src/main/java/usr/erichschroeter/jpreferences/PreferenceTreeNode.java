package usr.erichschroeter.jpreferences;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * A <code>PreferenceTreeNode</code> wraps a {@link Preferences} object allowing
 * it to be used in a {@link JTree}.
 * 
 * @author Erich Schroeter, http://www.roseindia.net/javatutorials/javaapi.shtml
 */
@SuppressWarnings("serial")
public class PreferenceTreeNode extends DefaultMutableTreeNode {

	/** The preferences being wrapped. */
	private Preferences pref;
	/** The children node names. */
	private String[] children;

	/**
	 * Constructs a <code>PreferenceTreeNode</code> specifying the
	 * <code>Preferences</code> node to be wrapped.
	 * 
	 * @param pref
	 *            the preference node
	 * @throws BackingStoreException
	 *             if a failure in the backing store occurs, or inability to
	 *             communicate with it
	 */
	public PreferenceTreeNode(Preferences pref) throws BackingStoreException {
		this.pref = pref;
		this.children = pref.childrenNames();
	}

	/**
	 * Returns the <code>Preferences</code> object being wrapped.
	 * 
	 * @return the preference object
	 */
	public Preferences getPrefObject() {
		return pref;
	}

	@Override
	public boolean isLeaf() {
		return ((children == null) || (children.length == 0));
	}

	@Override
	public int getChildCount() {
		return children.length;
	}

	@Override
	public TreeNode getChildAt(int index) {
		TreeNode child = null;
		if (index < children.length) {
			try {
				child = new PreferenceTreeNode(pref.node(children[index]));
			} catch (BackingStoreException e) {
				e.printStackTrace();
				child = new DefaultMutableTreeNode("Problem Child!");
			}
		}
		return child;
	}

	@Override
	public String toString() {
		String name = pref.name();
		if ((name == null) || ("".equals(name))) {
			name = "System Preferences";
			if (pref.isUserNode()) {
				name = "User Preferences";
			}
		}
		return name;
	}

}
