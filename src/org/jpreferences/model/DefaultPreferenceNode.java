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
		this(null, null, null);
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
		this(null, identifier, page, label, null);
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
		this(null, identifier, page, label, icon);
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
		this(parent, identifier, page, label, null);
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
		setParent(parent);
		setIdentifier(identifier);
		setPage(page);
		setLabel(label);
		setIcon(icon);
		children = new Vector<IPreferenceNode>();
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
		return label;
	}

	/**
	 * Sets the label representing the preference node.
	 * 
	 * @param label    the label
	 */
	public void setLabel(String label){
		this.label = label;
	}

	@Override
	public void insert(MutableTreeNode parent, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		children.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		children.remove(node);
	}

	@Override
	public void removeFromParent() {
		getParent().getIndex(this);
		// TODO how does DefaultMutatableTreeNode handle this?
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
		return getChildCount() == 0 ? true : false;
	}

}