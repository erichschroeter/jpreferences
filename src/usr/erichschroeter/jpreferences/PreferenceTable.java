package usr.erichschroeter.jpreferences;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

/**
 * A <code>PreferenceTable</code> displays {@link Preferences} in a table. It
 * allows different features to be enabled/disabled which change the way the
 * user can interact with preferences.
 * 
 * @author Erich Schroeter
 */
@SuppressWarnings("serial")
public class PreferenceTable extends JTable {

	/** The action that handles deleting preferences. */
	private AbstractAction deleteAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteRows(getSelectedRows());
		}

	};
	/** The action that handles adding preferences. */
	private AbstractAction addAction = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			newPreference();
		}

	};

	/**
	 * Constructs a default <code>PreferenceTable</code>.
	 * <p>
	 * This is equivalent to <code></code>.
	 */
	public PreferenceTable() {
		super();
		initialize();
	}

	/**
	 * Initialize the features to be allowed by default. Bind keys to actions.
	 */
	protected void initialize() {
		// bind the Ctrl + D and Del key strokes to delete preferences
		bindDeleteKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				KeyStroke
						.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		// bind the Ctrl + N key stroke to add preferences
		bindAddKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));

		// adding a preference can be handled a couple different ways
		// 1. add a row and let the user modify the values
		// 2. show dialog asking user for key and value
		// we are choosing to implement option 2 here
	}

	/**
	 * Returns the table model for this table as a
	 * <code>PreferenceTableModel</code>.
	 * 
	 * @return the table model
	 * @throws ClassCastException
	 *             if the {@link #getModel()} does not return a
	 *             <code>PreferenceTableModel</code>
	 */
	public PreferenceTableModel getPreferenceTableModel() {
		return (PreferenceTableModel) getModel();
	}

	/**
	 * Handles adding a new preference. This displays a dialog for the user to
	 * enter a key and value.
	 */
	public void newPreference() {
		JPanel form = createPreferenceForm();
		int selectedValue = JOptionPane.showConfirmDialog(this, form,
				"New Preference", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (selectedValue == JOptionPane.YES_OPTION) {
			String key = null;
			String value = null;
			// get the entered values
			for (Component c : form.getComponents()) {
				String name = c.getName();
				if (name != null && name.equals("key")) {
					key = ((JTextField) c).getText();
				}
				if (name != null && name.equals("value")) {
					value = ((JTextField) c).getText();
				}
			}
			if (value == null) {
				value = "";
			}
			// the key cannot be empty, but the value can
			if (key != null && !key.equals("")) {
				addRow(key, value);
				System.out.println("added [" + key + ", " + value + "]");
			}
		}
	}

	protected JPanel createPreferenceForm() {
		JPanel form = new JPanel();
		GroupLayout layout = new GroupLayout(form);
		form.setLayout(layout);

		JLabel keyLabel = new JLabel("Key:");
		keyLabel.setToolTipText("cannot be empty");
		JTextField keyField = new JTextField(10);
		keyField.setName("key");

		JLabel valueLabel = new JLabel("Value:");
		JTextField valueField = new JTextField(10);
		valueField.setName("value");

		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(keyLabel)
								.addComponent(valueLabel))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(keyField)
								.addComponent(valueField)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(keyLabel).addComponent(keyField))
				.addGroup(
						layout.createParallelGroup(Alignment.LEADING)
								.addComponent(valueLabel)
								.addComponent(valueField)));
		return form;
	}

	/**
	 * Binds the specified key strokes to delete preferences.
	 * 
	 * @see #unbindKeyStroke(KeyStroke...)
	 * @param keyStrokes
	 *            key strokes for deleting preferences
	 */
	public void bindDeleteKeyStroke(KeyStroke... keyStrokes) {
		for (KeyStroke k : keyStrokes) {
			getInputMap().put(k, k.toString());
			getActionMap().put(k.toString(), deleteAction);
		}
	}

	/**
	 * Binds the specified key strokes to add preferences.
	 * 
	 * @see #unbindKeyStroke(KeyStroke...)
	 * @param keyStrokes
	 *            key strokes for adding preferences
	 */
	public void bindAddKeyStroke(KeyStroke... keyStrokes) {
		for (KeyStroke k : keyStrokes) {
			getInputMap().put(k, k.toString());
			getActionMap().put(k.toString(), addAction);
		}
	}

	/**
	 * Unbinds the specified key strokes from its action. This can be used for
	 * unbinding key strokes bound from {@link #bindAddKeyStroke(KeyStroke...)}
	 * and {@link #bindDeleteKeyStroke(KeyStroke...)}.
	 * 
	 * @see #bindAddKeyStroke(KeyStroke...)
	 * @see #bindDeleteKeyStroke(KeyStroke...)
	 * @param keyStrokes
	 *            key strokes for adding preferences
	 */
	public void unbindKeyStroke(KeyStroke... keyStrokes) {
		for (KeyStroke k : keyStrokes) {
			getInputMap().remove(k);
			getActionMap().remove(k.toString());
		}
	}

	public void deleteRows(int... rows) {
		if (getPreferenceTableModel().isDeleteAllowed()) {
			TableModel model = getModel();
			if (model instanceof PreferenceTableModel) {
				PreferenceTableModel prefModel = (PreferenceTableModel) model;
				for (int i = 0; i < rows.length; i++) {
					prefModel.removeRow(rows[i]);
				}
			}
		}
	}

	public void addRow(String key, Object value) {
		if (getPreferenceTableModel().isAddAllowed()) {
			TableModel model = getModel();
			if (model instanceof PreferenceTableModel) {
				PreferenceTableModel prefModel = (PreferenceTableModel) model;
				prefModel.addPreference(key, value);
			}
		}
	}
}
