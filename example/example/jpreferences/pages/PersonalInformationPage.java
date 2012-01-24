package example.jpreferences.pages;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import usr.erichschroeter.jpreferences.page.CustomPage;

/**
 * An example preference page to display some basic fields for entering personal
 * information. This is intended to show users how to create a custom preference
 * page that fits their simple needs.
 * 
 * @author Erich Schroeter
 */
public class PersonalInformationPage extends CustomPage<JPanel> {

	public PersonalInformationPage(String title, String description) {
		super(title, description);
	}

	@Override
	protected void initializePage(JPanel page) {
		GroupLayout layout = new GroupLayout(page);
		page.setLayout(layout);

		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField(10);

		JLabel genderLabel = new JLabel("Gender:");
		JRadioButton maleButton = new JRadioButton("male");
		JRadioButton femaleButton = new JRadioButton("female");
		JRadioButton noneButton = new JRadioButton("none");

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		genderGroup.add(noneButton);

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(nameLabel)
								.addComponent(genderLabel))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(nameField))
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(maleButton)
												.addComponent(femaleButton)
												.addComponent(noneButton))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(nameLabel)
								.addComponent(nameField))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(genderLabel)
								.addComponent(maleButton)
								.addComponent(femaleButton)
								.addComponent(noneButton)));
	}

}
