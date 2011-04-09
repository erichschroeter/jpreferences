package org.prefs;

public class PreferenceManager {

	/**
	 * A default root node. All root nodes are actually not technically roots,
	 * instead they are added as children to this node.
	 * 
	 * <p>
	 * This is used in the cases of adding the nodes without a parent. If the
	 * parent argument in <code>addTo(IPreferenceNode, IPreferenceNode)</code>
	 * is <code>null</code> then the child argument is added to this node.
	 * </p>
	 */
	private PreferenceNode default_root;

	/**
	 * The preference store in which this manager manages.
	 */
	private PreferenceStore store;

	public PreferenceManager() {
		this(new DefaultPreferenceNode(new DefaultPreferencePage()), new PreferenceStore("preferences.properties"));
	}

	public PreferenceManager(PreferenceNode root) {
		this(root, new PreferenceStore("preferences.properties"));
	}
	
	public PreferenceManager(PreferenceStore store) {
		this(new DefaultPreferenceNode(new DefaultPreferencePage()), store);
	}
	
	public PreferenceManager(PreferenceNode root, PreferenceStore store) {
		default_root = root;
		this.setStore(store);
	}

	public void setStore(PreferenceStore store) {
		this.store = store;
	}

	public PreferenceStore getStore() {
		return store;
	}

	/**
	 * Returns the root node of all root nodes.
	 * 
	 * @return The master root node
	 */
	public PreferenceNode getRoot() {
		return default_root;
	}

	/**
	 * Adds the given node to the default root node.
	 * 
	 * <p>
	 * This function is simply a convenience method for
	 * <code>addTo(IPreferenceNode, IPreferenceNode)</code>. Calling this
	 * function is equivalent to calling <code>addTo(null, node)</code>.
	 * </p>
	 * 
	 * @param node
	 *            The new node
	 */
	public void add(IPreferenceNode node) {
		addTo(null, node);
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
	public void addTo(IPreferenceNode parent, IPreferenceNode child) {
		if (parent != null) {
			parent.add(child);
		} else {
			default_root.add(child);
		}
	}

}
