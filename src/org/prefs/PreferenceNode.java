package org.prefs;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.tree.TreeNode;

/**
 * A <code>PreferenceNode</code> maintains a label and icon used to display the
 * node in a {@link PreferenceDialog} (inside a <code>JTree</code>). A
 * <code>PreferenceNode</code> also keeps a reference to the
 * {@link PreferencePage} it represents in a tree.
 * 
 * @author Erich Schroeter
 */
public abstract class PreferenceNode implements IPreferenceNode {

	/**
	 * A <code>String</code> unique among its siblings used to identify this
	 * <code>PreferenceNode</code>
	 * <p>
	 * This is used by {@link PreferenceDialog} to open a {@link PreferencePage}
	 * specified by the user.
	 * </p>
	 */
	private String identifier;

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
	private Icon icon;

	/**
	 * Creates a preference node with the specified arguments.
	 * 
	 * <p>
	 * If <code>PreferencePage</code> == <code>null</code> a
	 * <code>DefaultPreferencePage</code> is created.
	 * </p>
	 * 
	 * @see {@link #setPage(PreferencePage)}
	 * @param page
	 *            The PreferencePage this node represents
	 * @param identifier
	 *            a unique identifier among this node's siblings
	 * @param parent
	 *            the parent node
	 * @param label
	 *            The label representing this node
	 * @param icon
	 *            The icon representing this node
	 * @throws NullPointerException
	 *             if the <code>PreferencePage</code> is <code>null</code>
	 * @throws ConflictingIdentifierException
	 *             if any of the sibling nodes have the same identifier
	 */
	public PreferenceNode(PreferencePage page, String identifier,
			PreferenceNode parent, String label, Icon icon)
			throws NullPointerException, ConflictingIdentifierException {
		this.parent = parent;
		children = new Vector<IPreferenceNode>();
		setIdentifier(identifier);
		setPage(page);
		setLabel(label);
		setIcon(icon);
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
	 * Returns this node's unique identifier among it's sibling nodes.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the unique identifier among this node's siblings.
	 * <p>
	 * This must be unique among this node's siblings. Performs the following
	 * check:
	 * 
	 * <pre>
	 * {@code
	 * for (PreferenceNode sibling : (PreferenceNode[]) parent.getChildrenNodes()) {
	 *     if (sibling.getIdentifier().equalsIgnoreCase(identifier)) {
	 *         throw new ConflictingIdentifierException(sibling.label
	 *             + " has this identifier");
	 *     }
	 * }
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * If the given <i>identifier</i> is <code>null</code> generation of a
	 * unique value is attempted. This is not guaranteed to prevent a
	 * <code>ConflictingIdentifierException</code> from being thrown.
	 * </p>
	 * 
	 * @param identifier
	 *            the identifier to set
	 * @throws ConflictingIdentifierException
	 *             if a sibling node has the same <i>identifier</i>
	 */
	protected void setIdentifier(String identifier)
			throws ConflictingIdentifierException {
		if (identifier != null) {
			if (parent != null) {
				for (PreferenceNode sibling : (PreferenceNode[]) parent
						.getChildrenNodes()) {
					if (sibling.getIdentifier().equalsIgnoreCase(identifier)) {
						throw new ConflictingIdentifierException(sibling.label
								+ " has this identifier");
					}
				}
			}
			this.identifier = identifier;
		} else {
			this.identifier = generateIdentifier();
		}

	}

	/**
	 * Returns a Universal Unique Identifier (UUID) represented as a
	 * <code>String</code>.
	 * 
	 * @return UUID string
	 */
	private String generateIdentifier() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Sets the preference page for this node.
	 * 
	 * @param page
	 *            The page
	 * @throws NullPointerException
	 *             if the <code>PreferencePage</code> is <code>null</code>
	 */
	public void setPage(PreferencePage page) throws NullPointerException {
		if (page == null) {
			throw new NullPointerException("PreferencePage must not be null");
		}

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

	/**
	 * Returns the image used to present this node in a preference dialog.
	 * 
	 * @return the image for this node, or <code>null</code> if there is no
	 *         image for this node
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * Sets the image used to present this node in a preference dialog.
	 * 
	 * @param icon
	 *            the icon to be displayed by the <code>JTree</code>
	 *            <code>TreeCellRenderer</code>
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * Returns the text label used to represent this node in a preference
	 * dialog.
	 * 
	 * @return the text label for this node, or <code>null</code> if there is no
	 *         label for this node
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the text label used to represent this node in a preference dialog.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets this node's parent.
	 * 
	 * @param parent
	 *            the parent node
	 */
	public void setParent(IPreferenceNode parent) {

	}

	//
	// IPreferenceNode members
	//

	@Override
	public IPreferenceNode[] getChildrenNodes() {
		return (IPreferenceNode[]) children.toArray();
	}

	@Override
	public void addChild(IPreferenceNode node) {
		if (children == null) {
			children = new Vector<IPreferenceNode>();
		}
		// TODO set the child's parent to this
		children.add(node);
	}

	@Override
	public boolean removeChild(IPreferenceNode node) {
		return children.remove(node);
	}

	//
	// TreeNode members
	//

	@SuppressWarnings("unchecked")
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
