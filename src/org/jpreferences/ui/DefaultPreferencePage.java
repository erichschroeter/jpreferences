package org.jpreferences.ui;
import javax.swing.JPanel;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.PreferenceManager;

/**
 * @author Erich Schroeter
 * @version 1.0
 * @created 02-May-2011 6:21:05 PM
 */
public class DefaultPreferencePage extends JPanel implements IPreferencePage {

	/**
	 * Represents whether any setting on this page has changed.
	 * 
	 * This value will be <code>True</code> if a value has changed, otherwise
	 * <code>False</code>.
	 */
	private boolean dirty;
	/**
	 * A short title to represent the preference page.
	 * 
	 * This value can be used as the label in a {@link IPreferenceNode} if none is
	 * specified in the node.
	 */
	private String title;
	/**
	 * Text describing what is on the preference page.
	 */
	private String description;
	/**
	 * The <code>IPreferenceManager</code> which manages the preferences for which
	 * this page displays.
	 */
	private PreferenceManager manager;

	public DefaultPreferencePage(){

	}

	/**
	 * Creates a <code>PreferencePage</code> object specifying the <i>title</i>, and
	 * <i>description</i>.
	 * 
	 * @param manager
	 * @param title    The title. If value is <code>null</code> the <i>title</i>
	 * attribute is set to empty string ( <code>""</code>)
	 * @param description    A description of the page. If value is <code>null</code>
	 * the
	 * <i>description</i> attribute is set to empty string (
	 * <code>""</code>)
	 */
	public DefaultPreferencePage(PreferenceManager manager, String title, String description){

	}

	/**
	 * @return the title
	 */
	public String getTitle(){
		return "";
	}

	/**
	 * Sets the <i>title</i> attribute.
	 * <p> If the argument is <code>null</code>, the <i>title</i> attribute is set to
	 * the DEFAULT_TITLE.
	 * <p>
	 * 
	 * @param title    The new title
	 */
	public void setTitle(String title){

	}

	/**
	 * @return the description
	 */
	public String getDescription(){
		return "";
	}

	/**
	 * Sets the <i>description</i> attribute.
	 * <p> If the argument is <code>null</code>, the <i>description</i> attribute is
	 * set to an empty string (<code>""</code>).
	 * <p>
	 * 
	 * @param description    The new description
	 */
	public void setDescription(String description){

	}

	/**
	 * Handles creating the components to be displayed on the
	 * <code>IPreferencePage</code>.
	 */
	public void createContents(){

	}

	/**
	 * Handles setting the default values on this page.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performDefault(){
		return false;
	}

	/**
	 * Handles saving the preferences represented on this preference page. Notifies
	 * whether saving was successful or an error occurred attempting to.
	 * 
	 * @return <code>True</code> if preferences were successfully saved, else
	 * <code>False</code>
	 */
	public boolean performOk(){
		return false;
	}

	/**
	 * Handles cancelling any changes made on this preference page. Notifies whether
	 * cancelling was successful or if an error occurred.
	 * 
	 * @return <code>True</code> if no errors occurred, else <code>False</code>
	 */
	public boolean performCancel(){
		return false;
	}

	/**
	 * Checks whether it is alright to leave this page.
	 * 
	 * This could be used to prevent the preference page from changing in case
	 * preferences need to be saved. This could happen if a user selects another page
	 * from the preference tree after modifying fields without clicking the <i>OK</i>
	 * or <i>Cancel</i> buttons.
	 * 
	 * @return <code>True</code> if the preferences on this page have been saved, else
	 * <code>False</code>
	 */
	public boolean okToLeave(){
		return false;
	}

	@Override
	public void setManager(IPreferenceManager manager){

	}

	@Override
	public IPreferenceManager getManager() {
		// TODO Auto-generated method stub
		return null;
	}

}