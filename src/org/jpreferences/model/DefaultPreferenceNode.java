package org.jpreferences.model;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.IPreferencePage;

/**
 * A <code>DefaultPreferenceNode</code> maintains a label and icon used to
 * represent a node in a graphical tree such as {@link JTree}. A
 * <code>DefaultPreferenceNode</code> also keeps a reference to the {@link
 * IPreferencePage} it represents.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:05 PM
 */
public class DefaultPreferenceNode implements IPreferenceNode {

	/**
	 * A <code>String</code> unique among its siblings used to identify this
	 * <code>PreferenceNode</code>
	 * <p> This is used by {@link PreferenceDialog} to open a {@link PreferencePage}
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
	private DefaultPreferencePage page;
	/**
	 * The list of immediate children of this node (of type
	 * <code>IPreferenceNode</code>).
	 */
	private Vector<IPreferenceNode> children;
	/**
	 * Text label for this node.
	 * <p> This value is what is returned by <code>toString()</code>. Thus, when
	 * adding an <code>PreferenceNode</code> to a <code>JTree</code> the tree node
	 * will display this text.
	 * </p>
	 */
	private String label;
	/**
	 * Cached image, or <code>null</code> if none.
	 */
	private Icon icon;



	public void finalize() throws Throwable {

	}

	/**
	 * Creates a preference node without specifying any arguments.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * @exception ConflictingIdentifierException if any of the sibling nodes have the
	 * same identifier
	 * @exception NullPointerException if the <code>PreferencePage</code> is
	 * <code>null</code>
	 */
	public DefaultPreferenceNode()
	  throws NullPointerException,ConflictingIdentifierException{

	}

	/**
	 * Creates a preference node specifying the identifier, preference page, and label.
	 * 
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param identifier    a unique identifier among this node's siblings
	 * @param page    the preference page
	 * @param label    the label
	 * @exception ConflictingIdentifierException if any of the sibling nodes have the
	 * same identifier
	 * @exception NullPointerException if the <code>PreferencePage</code> is
	 * <code>null</code>
	 */
	public DefaultPreferenceNode(String identifier, IPreferencePage page, String label)
	  throws NullPointerException,ConflictingIdentifierException{

	}

	/**
	 * Creates a preference node specifying the identifier, preference page, icon, and
	 * label.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param identifier    a unique identifier among this node's siblings
	 * @param page    the preference page
	 * @param label    the label
	 * @param icon    the icon
	 * @exception ConflictingIdentifierException if any of the sibling nodes have the
	 * same identifier
	 * @exception NullPointerException if the <code>PreferencePage</code> is
	 * <code>null</code>
	 */
	public DefaultPreferenceNode(String identifier, IPreferencePage page, String label, Icon icon)
	  throws NullPointerException,ConflictingIdentifierException{

	}

	/**
	 * Creates a preference node specifying the identifier, preference page, label,
	 * and parent node.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param parent    the parent node
	 * @param identifier    a unique identifier among this node's siblings
	 * @param page    the preference page
	 * @param label    the label
	 * @exception ConflictingIdentifierException if any of the sibling nodes have the
	 * same identifier
	 * @exception NullPointerException if the <code>PreferencePage</code> is
	 * <code>null</code>
	 */
	public DefaultPreferenceNode(IPreferenceNode parent, String identifier, IPreferencePage page, String label)
	  throws NullPointerException,ConflictingIdentifierException{

	}

	/**
	 * Creates a preference node specifying the identifier, preference page, icon,
	 * label, and parent node.
	 * 
	 * @see {@link #DefaultPreferenceNode()}
	 * 
	 * @param parent    the parent node
	 * @param identifier    a unique identifier among this node's siblings
	 * @param page    the preference page
	 * @param label    the label
	 * @param icon    the icon
	 * @exception ConflictingIdentifierException if any of the sibling nodes have the
	 * same identifier
	 * @exception NullPointerException if the <code>PreferencePage</code> is
	 * <code>null</code>
	 */
	public DefaultPreferenceNode(IPreferenceNode parent, String identifier, IPreferencePage page, String label, Icon icon)
	  throws NullPointerException,ConflictingIdentifierException{

	}

	/**
	 * Returns the value returned by {@link #getLabel()}.
	 * 
	 * @return the label
	 */
	@Override
	public String toString(){
		return "";
	}

	/**
	 * Returns the preference page associated with this preference nodes.
	 * 
	 * @return the preference page
	 */
	public IPreferencePage getPage(){
		return null;
	}

	/**
	 * Sets the preference page for this preference node.
	 * 
	 * @param page    the preference page
	 */
	public void setPage(IPreferencePage page){

	}

	/**
	 * Returns the icon representing the preference page.
	 * 
	 * @return the icon
	 */
	public Icon getIcon(){
		return null;
	}

	/**
	 * Sets the icon representing the preference page.
	 * 
	 * @param icon    the icon
	 */
	public void setIcon(Icon icon){

	}

	/**
	 * Sets the identifier for this preference node.
	 * 
	 * The identifier is meant to be used by a manager in order to search a tree for a
	 * specific preference page.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier(){
		return "";
	}

	/**
	 * Sets the identifier for this preference node.
	 * 
	 * @param id    the identifier
	 */
	public void setIdentifier(String id){

	}

	/**
	 * Returns the label representing this preference node.
	 * 
	 * @return the label
	 */
	public String getLabel(){
		return "";
	}

	/**
	 * Sets the label representing the preference node.
	 * 
	 * @param label    the label
	 */
	public void setLabel(String label){

	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MutableTreeNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(MutableTreeNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserObject(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

}