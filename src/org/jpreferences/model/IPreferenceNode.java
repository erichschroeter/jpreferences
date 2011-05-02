package org.jpreferences;

import javax.swing.tree.TreeNode;

/**
 * Interface to a node in a {@link PreferenceManager}.
 * 
 * @author Erich Schroeter
 */
public interface IPreferenceNode extends TreeNode {

	/**
	 * Returns an iterator over the subnodes (immediate children) of this
	 * contribution node.
	 * 
	 * @return an IPreferenceNode array containing the child nodes
	 */
	public IPreferenceNode[] getChildrenNodes();

	/**
	 * Adds the given preference node as a subnode of this preference node.
	 * 
	 * @param node
	 *            the node to add
	 */
	public void addChild(IPreferenceNode node);

	/**
	 * Removes the given preference node from the list of subnodes (immediate
	 * children) of this node.
	 * 
	 * @param node
	 *            the node to remove
	 * @return <code>true</code> if the node was removed, and <code>false</code>
	 *         otherwise
	 */
	public boolean removeChild(IPreferenceNode node);
}
