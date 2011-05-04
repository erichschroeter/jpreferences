package org.jpreferences;
import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.storage.DefaultPreferenceStore;
import org.jpreferences.model.IPreferenceNode;

/**
 * A preference manager manages the ordering structure of the {@link
 * PreferenceNode}'s which in turn provide access to {@link PreferencePage}'s. As
 * an application gets larger the <code>PreferenceManager</code> makes it easier
 * to manage several pages which are frontend graphical representations to the
 * underlying preferences.
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class PreferenceManager {

	/**
	 * A default root node. All root nodes are actually not technically roots, instead
	 * they are added as children to this node.
	 * <p> This is used in the cases of adding the nodes without a parent. If the
	 * parent argument in <code>addTo(IPreferenceNode, IPreferenceNode)</code> is
	 * <code>null</code> then the child argument is added to this node.
	 * </p>
	 */
	private DefaultPreferenceNode default_root;
	/**
	 * The preference store in which this manager manages.
	 */
	private DefaultPreferenceStore store;



	public void finalize() throws Throwable {

	}

	public PreferenceManager(){

	}

	/**
	 * 
	 * @param root
	 */
	public PreferenceManager(DefaultPreferenceNode root){

	}

	/**
	 * 
	 * @param store
	 */
	public PreferenceManager(DefaultPreferenceStore store){

	}

	/**
	 * 
	 * @param root
	 * @param store
	 */
	public PreferenceManager(DefaultPreferenceNode root, DefaultPreferenceStore store){

	}

	/**
	 * Sets the store for which this <code>PreferenceManager</code> manages.
	 * <p> If <i>store</i> is <code>null</code> a new <code>PreferenceStore</code> is
	 * created. The location is set to <i>preferences.properties</i>.
	 * </p>
	 * 
	 * @param store    the preference store
	 */
	public void setStore(DefaultPreferenceStore store){

	}

	public DefaultPreferenceStore getStore(){
		return null;
	}

	/**
	 * Sets the default root node of all nodes to be managed.
	 * <p> If <i>root</i> is <code>null</code>, the <i>default_root</code> attribute
	 * is set to a new <code>DefaultPreferenceNode</code>
	 * </p>
	 * 
	 * @param root    the master root node
	 */
	private void setRoot(DefaultPreferenceNode root){

	}

	/**
	 * Returns the root node of all root nodes.
	 * @return The master root node
	 */
	public DefaultPreferenceNode getRoot(){
		return null;
	}

	/**
	 * Adds the given node to the default root node.
	 * <p> This function is simply a convenience method for
	 * <code>addTo(IPreferenceNode, IPreferenceNode)</code>. Calling this function is
	 * equivalent to calling <code>addTo(null, node)</code>.
	 * </p>
	 * 
	 * @param node    The new node
	 */
	public void add(IPreferenceNode node){

	}

	/**
	 * Adds the given child <code>IPreferenceNode</code> to the specified parent
	 * <code>IPreferenceNode</code>.
	 * 
	 * @param parent    The parent to add the child to. If parent is <code>null</code>
	 * then the child is added to a default root node.
	 * @param child    The child being added
	 */
	public void addTo(IPreferenceNode parent, IPreferenceNode child){

	}

}