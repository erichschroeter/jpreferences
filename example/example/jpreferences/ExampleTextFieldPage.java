package example.jpreferences;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.ui.PreferencePage;

@SuppressWarnings("serial")
public class ExampleTextFieldPage extends PreferencePage {

	public ExampleTextFieldPage(IPreferenceManager manager,
			String title, String description) {
		super(manager, title, description);
	}

	@Override
	public void createContents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Time", "Year", "Month", "Day",
				"Hour", "Minute", "Second"), c);

		c = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Size", "Width", "Height"), c);

		c = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Position", "X", "Y", "Z"), c);

		c = new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Color", "Red", "Green", "Blue",
				"Opacity"), c);
	}

	@Override
	public boolean performDefault() {
		for (Component c : getComponents()) {
			if (c instanceof JPanel) {
				JPanel panel = (JPanel) c;
				fillJPanelFieldsWithRandomData(panel);
			}
		}
		return super.performDefault();
	}

	private void fillJPanelFieldsWithRandomData(JPanel panel) {
		Random random = new Random();
		for (Component c : panel.getComponents()) {
			if (c instanceof JTextField) {
				JTextField field = (JTextField) c;
				field.setText(Integer.toString(random.nextInt(1000)));
			}
		}
	}

}
