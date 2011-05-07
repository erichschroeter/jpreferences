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

	public static JPanel createTextFieldPanel(String title, String ...labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 2));
		panel.setBorder(BorderFactory.createTitledBorder(title));
		
		for (String label : labels) {
			panel.add(new JLabel(label));
			panel.add(new JTextField());
		}
		
		return panel;
	}

	public static JPanel createRadioFieldPanel(String title, String ...labels) {
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

	public static JPanel createCheckboxFieldPanel(String title, String ...labels) {
		JPanel panel = new JPanel(new GridLayout(labels.length, 1));
		panel.setBorder(BorderFactory.createTitledBorder(title));
		
		ButtonGroup group = new ButtonGroup();
		for (String label : labels) {
			JCheckBox button = new JCheckBox(label);
			group.add(button);
			panel.add(button);
		}

		return panel;
	}
	
}
