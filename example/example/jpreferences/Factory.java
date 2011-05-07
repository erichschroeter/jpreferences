package example.jpreferences;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Factory {

	/**
	 * Factory method for generating a <code>JPanel</code> with a titled border
	 * with the specified title. The <code>JPanel</code> contains as many fields
	 * as there are <code>labels</code> specified.
	 * 
	 * @param title
	 *            the titled border text
	 * @param labels
	 *            the labels used for each field
	 * @return <code>JPanel</code> of fields
	 */
	public static JPanel createTextFieldPanel(String title, String... labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 2));
		panel.setBorder(BorderFactory.createTitledBorder(title));

		for (String label : labels) {
			panel.add(new JLabel(label));
			panel.add(new JTextField());
		}

		return panel;
	}

	/**
	 * Factory method for generating a <code>JPanel</code> with a titled border
	 * with the specified title. The <code>JPanel</code> contains as many fields
	 * as there are <code>labels</code> specified.
	 * 
	 * @param title
	 *            the titled border text
	 * @param labels
	 *            the labels used for each field
	 * @return <code>JPanel</code> of fields
	 */
	public static JPanel createRadioFieldPanel(String title, String... labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 1));
		panel.setBorder(BorderFactory.createTitledBorder(title));

		ButtonGroup group = new ButtonGroup();
		for (String label : labels) {
			JRadioButton button = new JRadioButton(label);
			group.add(button);
			panel.add(button);
		}

		return panel;
	}

	/**
	 * Factory method for generating a <code>JPanel</code> with a titled border
	 * with the specified title. The <code>JPanel</code> contains as many fields
	 * as there are <code>labels</code> specified.
	 * 
	 * @param title
	 *            the titled border text
	 * @param labels
	 *            the labels used for each field
	 * @return <code>JPanel</code> of fields
	 */
	public static JPanel createCheckboxFieldPanel(String title,
			String... labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 1));
		panel.setBorder(BorderFactory.createTitledBorder(title));

		for (String label : labels) {
			JCheckBox button = new JCheckBox(label);
			panel.add(button);
		}

		return panel;
	}

}
