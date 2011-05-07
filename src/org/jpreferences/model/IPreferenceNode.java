package org.jpreferences.model;

import javax.swing.Icon;
import javax.swing.tree.MutableTreeNode;

import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.ui.IPreferencePage;

/**
 * An <code>IPreferenceNode</code> is a node in a tree managed by a an
 * {@link IPreferenceManager}. It keeps a reference to a single
 * {@link IPreferencePage} and is displayed when the node is selected in a list.
 * 
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public interface IPreferenceNode extends MutableTreeNode {

	/**
	 * Returns the preference page associated with this preference nodes.
	 * 
	 * @return the preference page
	 */
	public IPreferencePage getPage();

	/**
	 * Sets the preference page for this preference node.
	 * 
	 * @param page
	 *            the preference page
	 */
	public void setPage(IPreferencePage page);

	/**
	 * Returns the icon representing the preference page.
	 * 
	 * @return the icon
	 */
	public Icon getIcon();

	/**
	 * Sets the icon representing the preference page.
	 * 
	 * @param icon
	 *            the icon
	 */
	public void setIcon(Icon icon);

	/**
	 * Sets the identifier for this preference node.
	 * 
	 * The identifier is meant to be used by a manager in order to search a tree
	 * for a specific preference page.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier();

	/**
	 * Sets the identifier for this preference node.
	 * 
	 * @param id
	 *            the identifier
	 */
	public void setIdentifier(String id) throws ConflictingIdentifierException;

	/**
	 * Returns the label representing this preference node.
	 * 
	 * @return the label
	 */
	public String getLabel();

	/**
	 * Sets the label representing the preference node.
	 * 
	 * @param label
	 *            the label
	 */
	public void setLabel(String label);

	/**
	 * Returns the node's parent.
	 * 
	 * @return the parent
	 */
	public IPreferenceNode getParentNode();

	/**
	 * Sets the node's parent.
	 * <p>
	 * This method should be used instead of
	 * {@link MutableTreeNode#setParent(MutableTreeNode)} when working with
	 * <code>IPreferenceNode</code>'s.
	 * </p>
	 * 
	 * @param node
	 */
	public void setParent(IPreferenceNode node);

	/**
	 * Adds the specified child node to this node's children.
	 * 
	 * @param child
	 *            the child node
	 * @param child
	 */
	public void add(IPreferenceNode child);
}