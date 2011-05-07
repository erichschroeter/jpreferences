package example.jpreferences;

import javax.swing.JLabel;

import org.jpreferences.IPreferenceManager;

@SuppressWarnings("serial")
public class ExampleDefaultPage extends ExamplePreferencePage {

	public ExampleDefaultPage(IPreferenceManager manager, String title,
			String description) {
		super(manager, title, description);
	}

	@Override
	public void createContents() {
		add(new JLabel(getDescription()));
	}

}
