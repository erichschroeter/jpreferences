package example.jpreferences;

import java.awt.Component;

import javax.swing.JPanel;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.ui.IPreferencePage;

@SuppressWarnings("serial")
public abstract class ExamplePreferencePage extends JPanel implements
		IPreferencePage {

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
	private IPreferenceManager manager;

	public ExamplePreferencePage(IPreferenceManager manager,
			String title, String description) {
		setManager(manager);
		setTitle(title);
		setDescription(description);
		createContents();
	}

	//
	// PreferencePage members
	//

	@Override
	public boolean okToLeave() {
		return !dirty;
	}

	@Override
	public boolean performCancel() {
		return true;
	}

	@Override
	public boolean performOk() {
		return true;
	}

	@Override
	public boolean performDefault() {
		return true;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public IPreferenceManager getManager() {
		return manager;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setDescription(String desc) {
		description = desc;
	}

	@Override
	public void setManager(IPreferenceManager manager) {
		this.manager = manager;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Component getContents() {
		return this;
	}

}
