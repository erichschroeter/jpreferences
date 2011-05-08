package org.jpreferences;

import java.util.Vector;

import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.ui.IPreferencePage;
import org.jpreferences.model.IPreferenceNode;

/**
 * A preference manager manages the ordering structure of the
 * {@link PreferenceNode}'s which in turn provide access to
 * {@link PreferencePage}'s. As an application gets larger the
 * <code>PreferenceManager</code> makes it easier to manage several pages which
 * are frontend graphical representations to the underlying preferences.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class DefaultPreferenceManager implements IPreferenceManager {

	/**
	 * A default root node. All root nodes are actually not technically roots,
	 * instead they are added as children to this node.
	 * <p>
	 * This is used in the cases of adding the nodes without a parent. If the
	 * parent argument in <code>addTo(IPreferenceNode, IPreferenceNode)</code>
	 * is <code>null</code> then the child argument is added to this node.
	 * </p>
	 */
	private IPreferenceNode default_root;
	/** The preference store in which this manager manages. */
	private IPreferenceStore store;
	/** The preference page currently being displayed. */
	private IPreferencePage current;
	/** The listeners listening for the current page to change */
	private Vector<ICurrentPageListener> currentListeners;

	/**
	 * Creates a preference manager without specifying the root node or
	 * preference store.
	 * <p>
	 * Creates a default root node with an identifier of "root".
	 * </p>
	 */
	public DefaultPreferenceManager() {
		IPreferenceNode root;
		try {
			root = new DefaultPreferenceNode("root", null, "");
		} catch (ConflictingIdentifierException e) {
			throw new IllegalStateException(e);
		}
		init(root, null);
	}

	/**
	 * Creates a preference manager without specifying the preference store.
	 * 
	 * @see #DefaultPreferenceManager(IPreferenceNode, IPreferenceStore)
	 * @param root
	 *            the root node
	 */
	public DefaultPreferenceManager(IPreferenceNode root) {
		this(root, null);
	}

	/**
	 * Creates a preference manager without specifying the root node.
	 * <p>
	 * Creates a default root node with an identifier of "root".
	 * </p>
	 * 
	 * @param store
	 *            the preference store
	 */
	public DefaultPreferenceManager(IPreferenceStore store) {
		IPreferenceNode root;
		try {
			root = new DefaultPreferenceNode("root", null, "");
		} catch (ConflictingIdentifierException e) {
			throw new IllegalStateException(e);
		}
		init(root, store);
	}

	/**
	 * Creates a preference manager specifying the root node and preference
	 * store.
	 * 
	 * @param root
	 *            the root node
	 * @param store
	 *            the preference store
	 * @exception NullPointerException
	 *                if the root node is <code>null</code>
	 */
	public DefaultPreferenceManager(IPreferenceNode root, IPreferenceStore store) {
		if (root == null) {
			throw new NullPointerException("The root node cannot be null");
		}
		init(root, store);
	}

	/**
	 * Handles setting the class properties.
	 * 
	 * @param root
	 *            the default root node
	 * @param store
	 *            the preference store
	 */
	private void init(IPreferenceNode root, IPreferenceStore store) {
		currentListeners = new Vector<ICurrentPageListener>();
		setRoot(root);
		setStore(store);
		setCurrentPage(getRoot().getPage());
	}

	/**
	 * Sets the default root node of all nodes to be managed.
	 * <p>
	 * If <i>root</i> is <code>null</code>, the <i>default_root</code> attribute
	 * is set to a new <code>DefaultPreferenceNode</code>
	 * </p>
	 * 
	 * @param root
	 *            the master root node
	 */
	private void setRoot(IPreferenceNode root) {
		default_root = root;
	}

	/**
	 * Handles notifying all the {@link ICurrentPageListener}'s when
	 * {@link #current} changes.
	 * 
	 * @param current
	 *            the new current page
	 */
	private void notifyCurrentPageListeners(IPreferencePage current) {
		for (ICurrentPageListener l : currentListeners) {
			l.handleCurrentPageChanged(current);
		}
	}

	//
	// IPreferenceManager members
	//

	/**
	 * Sets the store for which this <code>PreferenceManager</code> manages.
	 * <p>
	 * If <i>store</i> is <code>null</code> a new <code>PreferenceStore</code>
	 * is created. The location is set to <i>preferences.properties</i>.
	 * </p>
	 * 
	 * @param store
	 *            the preference store
	 */
	public void setStore(IPreferenceStore store) {
		this.store = store;
	}

	/**
	 * Returns the preference store this manager manages.
	 * 
	 * @return the preference store
	 */
	public IPreferenceStore getStore() {
		return store;
	}

	/**
	 * Returns the root node of all root nodes.
	 * 
	 * @return The master root node
	 */
	@Override
	public IPreferenceNode getRoot() {
		return default_root;
	}

	/**
	 * Adds the given node to the default root node.
	 * <p>
	 * This function is simply a convenience method for
	 * {@link #add(IPreferenceNode, IPreferenceNode)}. Calling this function is
	 * equivalent to calling <code>addTo(null, node)</code>.
	 * </p>
	 * 
	 * @param node
	 *            The new node
	 */
	@Override
	public void add(IPreferenceNode node) {
		add(default_root, node);
	}

	/**
	 * Adds the given child <code>IPreferenceNode</code> to the specified parent
	 * <code>IPreferenceNode</code>.
	 * 
	 * @param parent
	 *            The parent to add the child to. If parent is <code>null</code>
	 *            then the child is added to a default root node.
	 * @param child
	 *            The child being added
	 */
	@Override
	public void add(IPreferenceNode parent, IPreferenceNode child) {
		parent.add(child);
	}

	@Override
	public void addPreference(Preference preference) {
		// TODO Auto-generated method stub

	}

	@Override
	public IPreferencePage getCurrentPage() {
		return current;
	}

	@Override
	public Preference getPreference(String id) {
		// String[] path = id.split("/");
		return null;
	}

	@Override
	public void removePreference(Preference preference) {

	}

	@Override
	public void setCurrentPage(IPreferencePage page) {
		current = page;
		notifyCurrentPageListeners(page);
	}

	/**
	 * Attempts to set the current page to the page specified via a path. The
	 * path is delimited by the character '/'.
	 * <p>
	 * If the specified page at the end of the path does not exist, the current
	 * page is not changed.
	 * </p>
	 * 
	 * @see #setCurrentPage(IPreferencePage)
	 */
	@Override
	public void setCurrentPage(String path) {
		String[] ids = path.split("/");
		IPreferenceNode node = getRoot();
		for (String id : ids) {
			for (int i = 0; i < node.getChildCount(); i++) {
				IPreferenceNode child = (IPreferenceNode) node.getChildAt(i);
				if (child.getIdentifier().equals(id)) {
					node = child;
					break;
				}
			}
		}
		if (node.getIdentifier().equals(ids[ids.length - 1])) {
			setCurrentPage(node.getPage());
		}
	}

	@Override
	public void addCurrentPageListener(ICurrentPageListener listener) {
		currentListeners.add(listener);
	}

	@Override
	public void removeCurrentPageListener(ICurrentPageListener listener) {
		currentListeners.remove(listener);
	}

}