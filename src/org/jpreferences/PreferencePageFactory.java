package org.jpreferences;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.IPreferencePage;

/**
 * The <code>PreferencePageFactory</code> is a factory for building preference
 * pages.
 * 
 * @author Erich Schroeter
 */
public class PreferencePageFactory {

	/** The queue of sections to be added to the preference page. */
	private List<Component> sections;
	/** The queue of components to be added to the next section. */
	private List<Component> sectionComponents;

	/**
	 * Constructs a default <code>PreferencePageFactory</code>.
	 */
	public PreferencePageFactory() {
		sectionComponents = new Vector<Component>();
		sections = new Vector<Component>();
	}

	public PreferencePageFactory generatePageFor(String... preferences) {
		return this;
	}

	/**
	 * Creates a check box editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * The <code>actionListener</code> should be used for updating the
	 * preference value.
	 * <p>
	 * This is equivalent to
	 * <code>addCheckboxEditorComponent(createCheckboxEditorComponent(label, "", actionListener), preferenceIdentifier)</code>.
	 * 
	 * @param label
	 *            the check box label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @param actionListener
	 *            the action to perform when the check box is clicked
	 * @return the preference page factory
	 */
	public PreferencePageFactory addCheckboxEditorComponent(String label,
			String preferenceIdentifier, ActionListener actionListener) {
		return addCheckboxEditorComponent(
				createCheckboxEditorComponent(label, "", actionListener),
				preferenceIdentifier);
	}

	/**
	 * Creates a check box editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * The <code>actionListener</code> should be used for updating the
	 * preference value.
	 * <p>
	 * This is equivalent to
	 * <code>addCheckboxEditorComponent( createCheckboxEditorComponent(label, tooltip, actionListener), preferenceIdentifier)</code>.
	 * 
	 * @param label
	 *            the check box label
	 * @param tooltip
	 *            the label tooltip
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @param actionListener
	 *            the action to perform when the check box is clicked
	 * @return the preference page factory
	 */
	public PreferencePageFactory addCheckboxEditorComponent(String label,
			String tooltip, String preferenceIdentifier,
			ActionListener actionListener) {
		return addCheckboxEditorComponent(
				createCheckboxEditorComponent(label, tooltip, actionListener),
				preferenceIdentifier);
	}

	/**
	 * Creates a check box editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * 
	 * @param checkbox
	 *            the check box field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addCheckboxEditorComponent(JCheckBox checkbox,
			String preferenceIdentifier) {
		JPanel component = new JPanel();
		component.add(checkbox);
		return addSectionComponent(component, preferenceIdentifier);
	}

	/**
	 * Creates and returns a check box component for editing a preference value.
	 * The component is initialized with the <code>label</code> and the
	 * <code>tooltip</code>.
	 * 
	 * @param label
	 *            the label describing what the check box modifies
	 * @param tooltip
	 *            the label tooltip
	 * @param actionListener
	 *            the action to perform when the check box is clicked
	 * @return the customized <code>JCheckBox</code>
	 */
	public static JCheckBox createCheckboxEditorComponent(String label,
			String tooltip, ActionListener actionListener) {
		JCheckBox checkbox = new JCheckBox(label);
		checkbox.setToolTipText(tooltip);
		checkbox.addActionListener(actionListener);
		return checkbox;
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addTextEditorComponent(new JLabel(label), preferenceIdentifier, actionListener)</code>.
	 * 
	 * @see #addTextEditorComponent(JLabel, String, ActionListener)
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(String label,
			String preferenceIdentifier, DocumentListener actionListener) {
		return addTextEditorComponent(new JLabel(label), preferenceIdentifier,
				actionListener);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * 
	 * @see #addTextEditorComponent(JLabel, String, ActionListener)
	 * @param label
	 *            the text field label
	 * @param tooltip
	 *            the label tooltip
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @param actionListener
	 *            the action to perform when the text field changes
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(String label,
			String tooltip, String preferenceIdentifier,
			DocumentListener actionListener) {
		JLabel jlabel = new JLabel(label);
		jlabel.setToolTipText(tooltip);
		return addTextEditorComponent(jlabel, preferenceIdentifier,
				actionListener);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addTextEditorComponent(label, new JTextField(), preferenceIdentifier, actionListener)</code>.
	 * 
	 * @see #addTextEditorComponent(JLabel, JTextField, String, ActionListener)
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @param actionListener
	 *            the action to perform when the text field changes
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(JLabel label,
			String preferenceIdentifier, DocumentListener actionListener) {
		return addTextEditorComponent(label, new JTextField(),
				preferenceIdentifier, actionListener);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addSectionComponent( createTextEditorComponent(label, textField, actionListener), preferenceIdentifier)</code>.
	 * 
	 * @see #addSectionComponent(Component, String)
	 * @see #createTextEditorComponent(JLabel, JTextField, ActionListener)
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @param actionListener
	 *            the action to perform when the text field changes
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(JLabel label,
			JTextField textField, String preferenceIdentifier,
			DocumentListener actionListener) {
		return addSectionComponent(
				createTextEditorComponent(label, textField, actionListener),
				preferenceIdentifier);
	}

	/**
	 * Creates and returns a text component for editing a preference value. A
	 * text component is comprised of a <code>JPanel</code> containing a
	 * <code>JLabel</code> followed by a <code>JTextField</code>. The component
	 * is initialized with the <code>label</code> and the <code>tooltip</code>.
	 * 
	 * @see #createTextEditorComponent(JLabel, JTextField, ActionListener)
	 * @param label
	 *            the text field label
	 * @param tooltip
	 *            the label tooltip
	 * @param actionListener
	 *            the action to perform when the text field changes
	 * @return the customized <code>JPanel</code>
	 */
	public static JPanel createTextEditorComponent(String label,
			String tooltip, DocumentListener actionListener) {
		JLabel jlabel = new JLabel(label);
		jlabel.setToolTipText(tooltip);
		JTextField textfield = new JTextField();
		textfield.setToolTipText(tooltip);
		return createTextEditorComponent(jlabel, textfield, actionListener);
	}

	/**
	 * Creates and returns a text component for editing a preference value. A
	 * text component is comprised of a <code>JPanel</code> containing a
	 * <code>JLabel</code> followed by a <code>JTextField</code>.
	 * 
	 * @param label
	 *            the text field label
	 * @param textfield
	 *            the text field
	 * @param actionListener
	 *            the action to perform when the text field changes
	 * @return the customized <code>JPanel</code>
	 */
	public static JPanel createTextEditorComponent(JLabel label,
			JTextField textfield, DocumentListener actionListener) {
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints c;
		// label constraints
		c = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0);
		panel.add(label, c);
		// textfield constraints
		c = new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0);
		textfield.getDocument().addDocumentListener(actionListener);
		panel.add(textfield, c);
		return panel;
	}

	/**
	 * Adds the UI component and returns the <code>PreferencePageFactory</code>
	 * for additional modification or creation.
	 * 
	 * @param component
	 *            the UI component
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addSectionComponent(Component component,
			String preferenceIdentifier) {
		sectionComponents.add(component);
		return this;
	}

	/**
	 * Creates a new section to contain UI components for preferences and
	 * returns the <code>PreferencePageFactory</code> for additional
	 * modification or creation.
	 * <p>
	 * This will clear the section component queue after adding all components
	 * in the queue.
	 * 
	 * @param title
	 *            the title of the section
	 * @return the preference page factory
	 */
	public PreferencePageFactory addSection(String title) {
		JPanel section = new JPanel();
		section.setLayout(new GridBagLayout());
		GridBagConstraints c;
		section.setBorder(BorderFactory.createTitledBorder(title));
		for (int i = 0; i < sectionComponents.size(); i++) {
			// section component constraints
			c = new GridBagConstraints(0, i, 1, 1, 1.0, 0.0,
					GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
					new Insets(2, 2, 2, 2), 0, 0);
			section.add(sectionComponents.get(i), c);
		}
		sections.add(section);
		sectionComponents.clear();
		return this;
	}

	/**
	 * Returns the preference page that has been customized via this factory.
	 * 
	 * @return the customized preference page
	 */
	@SuppressWarnings("serial")
	public IPreferencePage getPage() {
		return new DefaultPreferencePage() {
			@Override
			public void createContents() {
				setLayout(new GridBagLayout());
				GridBagConstraints c;
				for (int i = 0; i < sections.size(); i++) {
					// section constraints
					c = new GridBagConstraints(0, i, 1, 1, 1.0, 1.0,
							GridBagConstraints.NORTHWEST,
							GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0,
							0);
					add(sections.get(i), c);
				}
				sections.clear();
			}
		};
	}
}
