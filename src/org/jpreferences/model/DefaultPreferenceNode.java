package org.jpreferences.model;

import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.IPreferencePage;

/**
 * A <code>DefaultPreferenceNode</code> maintains a label and icon used to
 * represent a node in a graphical tree such as {@link JTree}. A
 * <code>DefaultPreferenceNode</code> also keeps a reference to the
 * {@link IPreferencePage} it represents.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:05 PM
 */
public class DefaultPreferenceNode implements IPreferenceNode {

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
	private IPreferenceNode parent;
	/**
	 * Preference page, or <code>null</code> if not yet loaded.
	 */
	private IPreferencePage page;
	/**
	 * The list of immediate children of this node (of type
	 * <code>IPreferenceNode</code>).
	 */
	private Vector<IPreferenceNode> children;
	/**
	 * Text label for this node.
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
	 * Creates a preference node without specifying any arguments.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * @exception ConflictingIdentifierException
	 *                if any of the sibling nodes have the same identifier
	 * @exception NullPointerException
	 *                if the <code>PreferencePage</code> is <code>null</code>
	 */
	public DefaultPreferenceNode() throws NullPointerException,
			ConflictingIdentifierException {
		this(null, null, null);
	}

	/**
	 * Creates a preference node specifying the identifier, preference page, and
	 * label.
	 * 
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param identifier
	 *            a unique identifier among this node's siblings
	 * @param page
	 *            the preference page
	 * @param label
	 *            the label
	 * @exception ConflictingIdentifierException
	 *                if any of the sibling nodes have the same identifier
	 * @exception NullPointerException
	 *                if the <code>PreferencePage</code> is <code>null</code>
	 */
	public DefaultPreferenceNode(String identifier, IPreferencePage page,
			String label) throws NullPointerException,
			ConflictingIdentifierException {
		this(null, identifier, page, label, null);
	}

	/**
	 * Creates a preference node specifying the identifier, preference page,
	 * icon, and label.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param identifier
	 *            a unique identifier among this node's siblings
	 * @param page
	 *            the preference page
	 * @param label
	 *            the label
	 * @param icon
	 *            the icon
	 * @exception ConflictingIdentifierException
	 *                if any of the sibling nodes have the same identifier
	 * @exception NullPointerException
	 *                if the <code>PreferencePage</code> is <code>null</code>
	 */
	public DefaultPreferenceNode(String identifier, IPreferencePage page,
			String label, Icon icon) throws NullPointerException,
			ConflictingIdentifierException {
		this(null, identifier, page, label, icon);
	}

	/**
	 * Creates a preference node specifying the identifier, preference page,
	 * label, and parent node.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param parent
	 *            the parent node
	 * @param identifier
	 *            a unique identifier among this node's siblings
	 * @param page
	 *            the preference page
	 * @param label
	 *            the label
	 * @exception ConflictingIdentifierException
	 *                if any of the sibling nodes have the same identifier
	 * @exception NullPointerException
	 *                if the <code>PreferencePage</code> is <code>null</code>
	 */
	public DefaultPreferenceNode(IPreferenceNode parent, String identifier,
			IPreferencePage page, String label) throws NullPointerException,
			ConflictingIdentifierException {
		this(parent, identifier, page, label, null);
	}

	/**
	 * Creates a preference node specifying the identifier, preference page,
	 * icon, label, and parent node.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param parent
	 *            the parent node
	 * @param identifier
	 *            a unique identifier among this node's siblings
	 * @param page
	 *            the preference page
	 * @param label
	 *            the label
	 * @param icon
	 *            the icon
	 * @exception ConflictingIdentifierException
	 *                if any of the sibling nodes have the same identifier
	 * @exception NullPointerException
	 *                if the <code>PreferencePage</code> is <code>null</code>
	 */
	public DefaultPreferenceNode(IPreferenceNode parent, String identifier,
			IPreferencePage page, String label, Icon icon)
			throws NullPointerException, ConflictingIdentifierException {
		children = new Vector<IPreferenceNode>();
		setParent(parent);
		setIdentifier(identifier != null ? identifier : "");
		setPage(page != null ? page : new DefaultPreferencePage());
		setLabel(label);
		setIcon(icon);
	}

	/**
	 * Returns the value returned by {@link #getLabel()}.
	 * 
	 * @return the label
	 */
	@Override
	public String toString() {
		return label;
	}

	/**
	 * Returns the preference page associated with this preference nodes.
	 * 
	 * @return the preference page
	 */
	public IPreferencePage getPage() {
		return page;
	}

	/**
	 * Sets the preference page for this preference node.
	 * 
	 * @param page
	 *            the preference page
	 */
	public void setPage(IPreferencePage page) {
		this.page = page;
	}

	/**
	 * Returns the icon representing the preference page.
	 * 
	 * @return the icon
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * Sets the icon representing the preference page.
	 * 
	 * @param icon
	 *            the icon
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * Sets the identifier for this preference node.
	 * 
	 * The identifier is meant to be used by a manager in order to search a tree
	 * for a specific preference page.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for this preference node.
	 * <p>
	 * If the <code>id</code> does not match the regex
	 * <code>[a-zA-Z0-9_-]</code> an <code>IllegalArgumentException</code> is
	 * thrown.
	 * </p>
	 * 
	 * @param id
	 *            the identifier
	 * @throws ConflictingIdentifierException
	 */
	public void setIdentifier(String id) throws ConflictingIdentifierException {
		Pattern regex = Pattern.compile("(^$|[a-zA-Z0-9_-]*)");
		Matcher verifier = regex.matcher(id);
		if (!verifier.matches()) {
			throw new IllegalArgumentException("id contains illegal characters");
		}

		for (IPreferenceNode child : children) {
			if (child.getIdentifier().equals(id)) {
				throw new ConflictingIdentifierException("id already in use");
			}
		}
		identifier = id;
	}

	/**
	 * Returns the label representing this preference node.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label representing the preference node.
	 * 
	 * @param label
	 *            the label
	 */
	public void setLabel(String label) {
		if (label == null) {
			this.label = getPage().getTitle();
		} else {
			this.label = label;
		}
	}

	@Override
	public void insert(MutableTreeNode newChild, int index) {
		if (newChild == null) {
			throw new IllegalArgumentException("new child is null");
		} else if (!(newChild instanceof IPreferenceNode)) {
			throw new IllegalArgumentException(
					"new child must be of type IPreferenceNode");
		}
		IPreferenceNode node = (IPreferenceNode) newChild;
		IPreferenceNode oldParent = (IPreferenceNode) node.getParent();
		if (oldParent != null) {
			oldParent.remove(node);
		}
		node.setParent(this);
		children.insertElementAt(node, index);
	}

	@Override
	public void remove(int index) {
		IPreferenceNode child = (IPreferenceNode) getChildAt(index);
		children.removeElementAt(index);
		child.setParent((IPreferenceNode) null);
	}

	@Override
	public void remove(MutableTreeNode node) {
		remove(getIndex(node));
	}

	@Override
	public void removeFromParent() {
		MutableTreeNode parent = (MutableTreeNode) getParent();
		if (parent != null) {
			parent.remove(this);
		}
	}

	/**
	 * Sets the node's parent.
	 * <p>
	 * A type check is performed to verify the <code>node</code> is an instance
	 * of {@link IPreferenceNode}. If <code>node</code> is not an
	 * <code>IPreferenceNode</code> nothing is done.
	 * </p>
	 * 
	 * @param node
	 *            the parent preference node
	 */
	@Override
	public void setParent(MutableTreeNode node) {
		if (node instanceof IPreferenceNode) {
			setParent((IPreferenceNode) node);
		}
	}

	/**
	 * Sets the node's page.
	 * <p>
	 * A type check is performed to verify the <code>obj</code> is an instance
	 * of {@link IPreferencePage}. If <code>obj</code> is not an
	 * <code>IPreferencePage</code> nothing is done.
	 * </p>
	 * 
	 * @param obj
	 *            the preference page
	 */
	@Override
	public void setUserObject(Object obj) {
		if (obj instanceof IPreferencePage) {
			setPage((IPreferencePage) obj);
		}
	}

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
		if (node == null) {
			throw new IllegalArgumentException("argument is null");
		}
		return children.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return (getChildCount() == 0);
	}

	@Override
	public void setParent(IPreferenceNode node) {
		parent = node;
	}

	@Override
	public IPreferenceNode getParentNode() {
		return parent;
	}

	@Override
	public void add(IPreferenceNode child) {
		if (child != null && child.getParent() == this) {
			insert(child, getChildCount() - 1);
		} else {
			insert(child, getChildCount());
		}
	}

}