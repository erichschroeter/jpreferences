package org.prefs;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.TreeNode;

public abstract class PreferenceNode implements IPreferenceNode {

	/**
	 * Parent node, or <code>null</code> if root.
	 */
	private PreferenceNode parent;

	/**
	 * Preference page, or <code>null</code> if not yet loaded.
	 */
	private PreferencePage page;

	/**
	 * The list of immediate children of this node (of type
	 * <code>IPreferenceNode</code>).
	 */
	private Vector<IPreferenceNode> children;

	/**
	 * Text label for this node.
	 * 
	 * <p>
	 * This value is what is returned by <code>toString()</code>. Thus, when
	 * adding an <code>PreferenceNode</code> to a <code>JTree</code> the tree
	 * node will display this text.
	 * </p>
	 */
	private String label;

	/**
	 * Cached image, or <code>null</code> if none.
	 */
	private Image image;

	/**
	 * Creates a preference node with the specified arguments.
	 * 
	 * <p>
	 * If <code>PreferencePage</code> == <code>null</code> a
	 * <code>DefaultPreferencePage</code> is created.
	 * </p>
	 * 
	 * @param page
	 *            The PreferencePage this node represents
	 * @param label
	 *            The label representing this node
	 * @param image
	 *            The image representing this node
	 */
	public PreferenceNode(PreferenceNode parent, PreferencePage page,
			String label, Image image) throws NullPointerException {
		this.parent = parent;
		children = new Vector<IPreferenceNode>();

		if (page == null) {
			throw new NullPointerException("PreferencePage must not be null");
		}
		this.page = page;
		this.label = label;
		this.image = image;
	}

	/**
	 * Returns whether the PreferenceNode is a root node.
	 * <p>
	 * Compares <i>parent</i> attribute to <code>null</code>. If the
	 * <i>parent</i> attribute is <code>null</code>, the node is a root node.
	 * </p>
	 * 
	 * @return True if the node is a root node, else false
	 */
	public boolean isRoot() {
		return parent != null ? false : true;
	}

	/**
	 * Returns the root node of the tree structure this node is a part of.
	 * 
	 * <p>
	 * Recursively climbs the tree structure to find the
	 * <code>PreferenceNode</code> whose parent is <code>null</code>.
	 * <p>
	 * 
	 * @return The root node
	 */
	public PreferenceNode getRoot() {
		if (isRoot()) {
			return this;
		} else {
			return parent.getRoot();
		}
	}

	@Override
	public String toString() {
		return label;
	}

	/**
	 * Sets the preference page for this node.
	 * 
	 * @param page
	 *            The page
	 */
	public void setPage(PreferencePage page) {
		this.page = page;
	}

	/**
	 * Returns the preference page for this node.
	 * 
	 * @return the preference page
	 */
	public PreferencePage getPage() {
		return page;
	}

	//
	// IPreferenceNode members
	//

	@Override
	public Image getLabelImage() {
		return image;
	}

	@Override
	public String getLabelText() {
		return label;
	}

	@Override
	public IPreferenceNode[] getChildrenNodes() {
		return (IPreferenceNode[]) children.toArray();
	}

	@Override
	public void add(IPreferenceNode node) {
		if (children == null) {
			children = new Vector<IPreferenceNode>();
		}
		children.add(node);
	}

	@Override
	public boolean remove(IPreferenceNode node) {
		return children.remove(node);
	}

	//
	// TreeNode members
	//

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return children.elements();
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return children.get(index);
	}

	@Override
	public int getChildCount() {
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return children.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return getChildCount() > 0 ? false : true;
	}
}
