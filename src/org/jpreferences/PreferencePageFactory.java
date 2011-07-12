package org.jpreferences;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	}
	
	public PreferencePageFactory generatePageFor(String ...preferences) {
		return this;
	}

	/**
	 * Creates a check box editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addCheckboxEditorComponent(new JCheckBox(label), preferenceIdentifier)</code>.
	 * 
	 * @param label
	 *            the check box label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addCheckboxEditorComponent(String label,
			String preferenceIdentifier) {
		return addCheckboxEditorComponent(new JCheckBox(label),
				preferenceIdentifier);
	}

	/**
	 * Creates a check box editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * 
	 * @param label
	 *            the check box label
	 * @param tooltip
	 *            the label tooltip
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addCheckboxEditorComponent(String label,
			String tooltip, String preferenceIdentifier) {
		JCheckBox checkbox = new JCheckBox(label);
		checkbox.setToolTipText(tooltip);
		return addCheckboxEditorComponent(checkbox, preferenceIdentifier);
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
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addTextEditorComponent(new JLabel(label), preferenceIdentifier)</code>.
	 * 
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(String label,
			String preferenceIdentifier) {
		return addTextEditorComponent(new JLabel(label), preferenceIdentifier);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * 
	 * @param label
	 *            the text field label
	 * @param tooltip
	 *            the label tooltip
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(String label,
			String tooltip, String preferenceIdentifier) {
		JLabel jlabel = new JLabel(label);
		jlabel.setToolTipText(tooltip);
		return addTextEditorComponent(jlabel, preferenceIdentifier);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * <p>
	 * This is equivalent to
	 * <code>addTextEditorComponent(label, new JTextField(), preferenceIdentifier)</code>.
	 * 
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(JLabel label,
			String preferenceIdentifier) {
		return addTextEditorComponent(label, new JTextField(),
				preferenceIdentifier);
	}

	/**
	 * Creates a text editor UI component and returns the
	 * <code>PreferencePageFactory</code> for additional modification or
	 * creation.
	 * 
	 * @param label
	 *            the text field label
	 * @param preferenceIdentifier
	 *            the identifier for the preference this text component is used
	 *            to edit
	 * @return the preference page factory
	 */
	public PreferencePageFactory addTextEditorComponent(JLabel label,
			JTextField textField, String preferenceIdentifier) {
		JPanel component = new JPanel();
		component.add(label);
		component.add(textField);
		return addSectionComponent(component, preferenceIdentifier);
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
		section.setBorder(BorderFactory.createTitledBorder(title));
		for (Component c : sectionComponents) {
			section.add(c);
		}
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
				setLayout(new FlowLayout());
				for (Component section : sections) {
					add(section);
				}
				sections.clear();
			}
		};
	}
}
