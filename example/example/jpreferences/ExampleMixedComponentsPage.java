package example.jpreferences;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jpreferences.PreferencePage;

@SuppressWarnings("serial")
public class ExampleMixedComponentsPage extends PreferencePage {

	public ExampleMixedComponentsPage(String title,
			String description) {
		super(title, description);
		createContents();
	}

	@Override
	public void createContents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createCheckboxFieldPanel("Modify", "Bold", "Italics", "Underline", "Strike-through"), c);

		c = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createRadioFieldPanel("Size", "Small", "Medium", "Large"), c);

		c = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createRadioFieldPanel("Color", "Red", "Green", "Blue", "Yellow"), c);

		c = new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Time", "Year", "Month", "Day", "Hour", "Minute", "Second"), c);

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
				field.setText(Integer.toString(random.nextInt(255)));
			} else if (c instanceof JRadioButton) {
				JRadioButton radio = (JRadioButton) c;
				radio.setSelected(random.nextBoolean());
			} else if (c instanceof JCheckBox) {
				JCheckBox check = (JCheckBox) c;
				check.setSelected(random.nextBoolean());
			}
		}
	}

}
