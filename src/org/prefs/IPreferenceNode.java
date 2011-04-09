package org.prefs;

import java.awt.Image;

import javax.swing.tree.TreeNode;

/**
 * Interface to a node in a preference dialog. A preference node maintains a
 * label and image used to display the node in a preference dialog (usually in
 * the form of a tree), as well as the preference page this node stands for.
 * 
 * The node may use lazy creation for its page
 * 
 * Note that all preference nodes must dispose their resources. The node must
 * dispose the page managed by this node, and any resources allocated by this
 * node (Images, Fonts, etc). However the node itself may be reused.
 */
public interface IPreferenceNode extends TreeNode {

	/**
	 * Returns the image used to present this node in a preference dialog.
	 * 
	 * @return the image for this node, or <code>null</code> if there is no
	 *         image for this node
	 */
	public Image getLabelImage();

	/**
	 * Returns the text label used to present this node in a preference dialog.
	 * 
	 * @return the text label for this node, or <code>null</code> if there is no
	 *         label for this node
	 */
	public String getLabelText();

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
	public void add(IPreferenceNode node);

	/**
	 * Removes the given preference node from the list of subnodes (immediate
	 * children) of this node.
	 * 
	 * @param node
	 *            the node to remove
	 * @return <code>true</code> if the node was removed, and <code>false</code>
	 *         otherwise
	 */
	public boolean remove(IPreferenceNode node);
}
