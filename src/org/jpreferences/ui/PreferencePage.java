package org.jpreferences.ui;

import java.awt.Component;

import javax.swing.JPanel;

import org.jpreferences.IPreferenceManager;

/**
 * A <code>PreferencePage</code> implements the basic mundane methods provided
 * by the {@link IPreferencePage} interface. The only method(s) required to be
 * implemented by classes extending <code>PreferencePage</code> are
 * {@link IPreferencePage#createContents()}.
 * <p>
 * The default implementation of {@link PreferencePage#performOk()} saves the
 * preference store if not <code>null</code>.
 * </p>
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public abstract class PreferencePage extends JPanel implements IPreferencePage {

	/**
	 * A short title to represent the preference page.
	 * 
	 * This value can be used as the label in a {@link IPreferenceNode} if none
	 * is specified in the node.
	 */
	protected String title;
	/**
	 * Text describing what is on the preference page.
	 */
	protected String description;
	/**
	 * The <code>IPreferenceManager</code> which manages the preferences for
	 * which this page displays.
	 */
	protected IPreferenceManager manager;

	/**
	 * Creates a <code>PreferencePage</code> object specifying the <i>title</i>,
	 * and <i>description</i>.
	 * <p>
	 * It is recommended that {@link #createContents()} is called after calling
	 * the super constructor.
	 * </p>
	 * 
	 * @param manager
	 * @param title
	 *            The title. If value is <code>null</code> the <i>title</i>
	 *            attribute is set to empty string ( <code>""</code>)
	 * @param description
	 *            A description of the page. If value is <code>null</code> the
	 *            <i>description</i> attribute is set to empty string (
	 *            <code>""</code>)
	 */
	public PreferencePage(IPreferenceManager manager, String title,
			String description) {
		setManager(manager);
		setTitle(title);
		setDescription(description);
	}

	//
	// IPreferencePage members
	//

	@Override
	public void setManager(IPreferenceManager manager) {
		this.manager = manager;
	}

	@Override
	public IPreferenceManager getManager() {
		return manager;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setDescription(String desc) {
		this.description = desc;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean okToLeave() {
		return true;
	}

	@Override
	public boolean performOk() {
		boolean saved = false;
		if (manager != null) {
			saved = manager.getStore().save();
		}
		return saved;
	}

	@Override
	public boolean performCancel() {
		return true;
	}

	@Override
	public boolean performDefault() {
		return true;
	}

	@Override
	public Component getContents() {
		return this;
	}

}
