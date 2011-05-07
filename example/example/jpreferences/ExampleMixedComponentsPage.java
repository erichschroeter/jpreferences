package example.jpreferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import org.jpreferences.IPreferenceManager;

@SuppressWarnings("serial")
public class ExampleMixedComponentsPage extends ExamplePreferencePage {

	public ExampleMixedComponentsPage(IPreferenceManager manager, String title,
			String description) {
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

}
