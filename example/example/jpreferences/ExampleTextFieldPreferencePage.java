package example.jpreferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import org.jpreferences.IPreferenceManager;

@SuppressWarnings("serial")
public class ExampleTextFieldPreferencePage extends ExamplePreferencePage {

	public ExampleTextFieldPreferencePage(IPreferenceManager manager,
			String title, String description) {
		super(manager, title, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createContents() {
		setLayout(new GridBagLayout());

		GridBagConstraints c;

		c = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(2, 2, 2, 2), 0, 0);
		add(Factory.createTextFieldPanel("Time", "Year", "Month", "Day", "Hour", "Minute", "Second"), c);

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
		add(Factory.createTextFieldPanel("Color", "Red", "Green", "Blue", "Opacity"), c);
	}

}
