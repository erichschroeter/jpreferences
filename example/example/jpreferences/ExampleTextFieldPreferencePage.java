package example.jpreferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jpreferences.PreferenceManager;
import org.jpreferences.PreferencePage;

public class ExampleTextFieldPreferencePage extends PreferencePage {

	private static final long serialVersionUID = -1625333354602046336L;

	public ExampleTextFieldPreferencePage(PreferenceManager manager,
			String title, String description) {
		super(manager, title, description);
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
		return true;
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
	protected void createContents() {
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

}
