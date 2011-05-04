package org.jpreferences.ui;
import org.jpreferences.PreferenceManager;
import org.jpreferences.model.IPreferenceNode;

/**
 * @author erisch
 * @version 1.0
 * @created 02-May-2011 6:21:06 PM
 */
public class PreferenceDialog extends JDialog {

	/**
	 * Buttons available on the <code>PreferencePage</code>.
	 * <p> TODO implement feature to add new Buttons (ex: addButton(JButton))
	 * </p>
	 * @author erisch
	 * @version 1.0
	 * @created 02-May-2011 6:21:06 PM
	 */
	public enum Buttons {
		/**
		 * save the changed preferences
		 */
		OK,
		/**
		 * do not save the changed preferences
		 */
		CANCEL,
		DEFAULTS
	}

	/**
	 * The object managing the <code>IPreferenceNode</code>'s which in turn represent
	 * a corresponding <code>IPreferencePage</code>.
	 */
	private PreferenceManager manager;
	/**
	 * The interface object the user interacts with to view properties from different
	 * pages.
	 */
	private JTree tree;
	/**
	 * The <code>IPreferencePage</code> represented by the selected
	 * <code>IPreferenceNode</code>. This value changes as the selected
	 * <code>IPreferenceNode</code> changes.
	 */
	private DefaultPreferencePage currentPage;
	/**
	 * The <code>JPanel</code> that displays the current
	 * <code>PreferencePage</code>.
	 */
	private JPanel pagePanel;
	protected Buttons buttons;

	public PreferenceDialog(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Dialog,
	 * boolean)}.
	 * 
	 * @param parent    the <code>Dialog</code> from which the dialog is displayed or
	 * null if this dialog has no owner
	 * @param manager    the <code>PreferenceManager</code> that managing the
	 * preferences
	 */
	public PreferenceDialog(Dialog parent, IPreferenceManager manager){

	}

	/**
	 * Creates a <code>PreferenceDialog</code> calling {@link JDialog(Window,
	 * boolean)}.
	 * 
	 * @param parent    the <code>Window</code> from which the dialog is displayed or
	 * null if this dialog has no owner
	 * @param manager    the <code>PreferenceManager</code> that managing the
	 * preferences
	 */
	public PreferenceDialog(Window parent, IPreferenceManager manager){

	}

	/**
	 * Initializes the User Interface (UI) for this dialog. A left panel contains a
	 * tree hierarchy of the {@link IPreferenceNode}s; when the user selects a
	 * <code>IPreferenceNode</code> the page panel (the right on the right) updates to
	 * show the {@link IPreferencePage} associated with the
	 * <code>IPreferenceNode</code>.
	 */
	private void init(){

	}

	/**
	 * Handles displaying the given page.
	 * <p> This function removes the <i>currentPage</i> <code>Component</code> from
	 * <i>pagePanel</i> and adds the given <code>IPreferencePage</code> to
	 * <i>pagePanel</i>. After adding the newly selected page to the panel, it then
	 * calls <code>JPanel.updateUI()<code>.
	 * </p>
	 * 
	 * @param page    the new page to display
	 */
	private void changePageTo(DefaultPreferencePage page){

	}

	/**
	 * 
	 * @param path
	 */
	public void openPage(String path){

	}

	/**
	 * Returns the <code>IPreferenceNode</code> selected in the
	 * <code>JTree</code>.
	 * <p> Uses <code>TreePath.getSelectionPath().getLastPathComponent()</code> to
	 * retrieve the <code>TreeNode</code> and casts the result to
	 * <code>IPreferenceNode</code>.
	 * </p>
	 * @return The selected node
	 */
	public IPreferenceNode getSelectedNode(){
		return null;
	}

	/**
	 * Generates a <code>JPanel</code> object with the specified buttons. A
	 * <code>FlowLayout</code> is used to layout the buttons. Instances of
	 * <code>JButton</code> are created for each
	 * <code>Buttons<code> specified in the array.
	 * @return <code>JPanel</code> with specified buttons.
	 * 
	 * @param buttons
	 */
	protected JPanel generateButtons(Buttons[] buttons){
		return null;
	}

	/**
	 * @return an <code>TreeSelectionListener</code> which handles casting the
	 * selected path's <code>Component</code> to a
	 * <code>PreferencePage</code> and passing it to
	 * <code>{@link PreferenceDialog#changePageTo(IPreferencePage)</code>
	 */
	private TreeSelectionListener pageTreeSelectionListener(){
		return null;
	}

	/**
	 * @return an <code>WindowListener</code> which handles calling the current page's
	 * <code>performCancel()</code> function when the window is closing.
	 */
	private WindowListener dialogWindowListener(){
		return null;
	}

	private ActionListener okActionListener(){
		return null;
	}

	private ActionListener cancelActionListener(){
		return null;
	}

	private ActionListener defaultActionListener(){
		return null;
	}

}