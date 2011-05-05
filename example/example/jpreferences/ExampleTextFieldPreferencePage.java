package example.jpreferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.ui.IPreferencePage;

public class ExampleTextFieldPreferencePage extends JPanel implements IPreferencePage {

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


	public ExampleTextFieldPreferencePage(IPreferenceManager manager,
			String title, String description) {
		createContents();
	}

	private JPanel createTextFieldPanel(String title, String ...labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 2));
		panel.setBorder(BorderFactory.createTitledBorder(title));
		
		for (String label : labels) {
			panel.add(new JLabel(label));
			panel.add(new JTextField());
		}
		
		return panel;
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
	public void createContents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(createTextFieldPanel("Time", "Year", "Month", "Day", "Hour", "Minute", "Second"), c);

		c = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(createTextFieldPanel("Size", "Width", "Height"), c);

		c = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(createTextFieldPanel("Position", "X", "Y", "Z"), c);

		c = new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(createTextFieldPanel("Color", "Red", "Green", "Blue", "Opacity"), c);
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

}
