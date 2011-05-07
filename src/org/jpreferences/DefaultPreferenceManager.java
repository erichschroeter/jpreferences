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
	/** Classes containing {@link Preference} annotations */
	@SuppressWarnings("rawtypes")
	private Vector<Class> preferenceContainers;

	public DefaultPreferenceManager() throws NullPointerException,
			ConflictingIdentifierException {
		this(null, null);
	}

	/**
	 * 
	 * @param root
	 * @throws ConflictingIdentifierException
	 * @throws NullPointerException
	 */
	public DefaultPreferenceManager(IPreferenceNode root)
			throws NullPointerException, ConflictingIdentifierException {
		this(root, null);
	}

	/**
	 * 
	 * @param store
	 * @throws ConflictingIdentifierException
	 * @throws NullPointerException
	 */
	public DefaultPreferenceManager(IPreferenceStore store)
			throws NullPointerException, ConflictingIdentifierException {
		this(null, store);
	}

	/**
	 * 
	 * @param root
	 * @param store
	 * @throws ConflictingIdentifierException
	 * @throws NullPointerException
	 */
	public DefaultPreferenceManager(IPreferenceNode root, IPreferenceStore store)
			throws NullPointerException, ConflictingIdentifierException {
		currentListeners = new Vector<ICurrentPageListener>();
		preferenceContainers = new Vector<Class>();
		setRoot(root != null ? root : new DefaultPreferenceNode("root", null,
				""));
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
	 * <code>addTo(IPreferenceNode, IPreferenceNode)</code>. Calling this
	 * function is equivalent to calling <code>addTo(null, node)</code>.
	 * </p>
	 * 
	 * @param node
	 *            The new node
	 */
	@Override
	public void add(IPreferenceNode node) {
//		default_root.insert(node, default_root.getChildCount() - 1);
		addTo(default_root, node);
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
	public void addTo(IPreferenceNode parent, IPreferenceNode child) {
//		parent.insert(child, parent.getChildCount() - 1);
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

	@SuppressWarnings("rawtypes")
	@Override
	public void registerPreferenceContainer(Class container) {
		preferenceContainers.add(container);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void gatherAnnotations() {
		for (Class c : preferenceContainers) {
			c.getAnnotation(c);
		}
	}

}