package example.jpreferences;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.jpreferences.DefaultPreferenceManager;
import org.jpreferences.IPreferenceManager;
import org.jpreferences.PreferencePageFactory;
import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.ui.IPreferencePage;
import org.jpreferences.ui.PreferenceDialog;

public class PreferenceFactoryExample implements UserPreferences {

	/**
	 * Constructs a default <code>PreferenceFactoryExample</code> which
	 * initializes a {@link PreferenceDialog} for this example. Preferences are
	 * loaded from a previous instance of this example and saved upon closing
	 * the example.
	 */
	public PreferenceFactoryExample() {
		final IPreferenceManager mgr = new DefaultPreferenceManager(
				getPreferences());
		// add the Preference pages
		try {
			mgr.add(new DefaultPreferenceNode("window",
					createWindowPreferencePage(), "Window"));
			mgr.add(new DefaultPreferenceNode("mixed",
					new ExampleMixedComponentsPage(mgr, "Mixed",
							"Mixed components"), "Mixed"));
			mgr.add(new DefaultPreferenceNode("text", new ExampleTextFieldPage(
					mgr, "Text", "Text components"), "Text"));
		} catch (ConflictingIdentifierException e) {
			e.printStackTrace();
		}
		PreferenceDialog dialog = new PreferenceDialog(null, mgr);
		// save preferences when the dialog closes
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				savePreferences(e);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				savePreferences(e);
			}

			public void savePreferences(WindowEvent e) {
				getPreferences().putInt(PREFERENCE_WINDOW_SIZE_WIDTH,
						e.getWindow().getSize().width);
				getPreferences().putInt(PREFERENCE_WINDOW_SIZE_HEIGHT,
						e.getWindow().getSize().height);
				getPreferences().putInt(PREFERENCE_WINDOW_LOCATION_X,
						e.getWindow().getLocation().x);
				getPreferences().putInt(PREFERENCE_WINDOW_LOCATION_Y,
						e.getWindow().getLocation().y);
			}
		});
		// install the preferences saved from last instance
		dialog.setLocation(new Point(getPreferences().getInt(
				PREFERENCE_WINDOW_LOCATION_X, DEFAULT_WINDOW_LOCATION.x),
				getPreferences().getInt(PREFERENCE_WINDOW_LOCATION_Y,
						DEFAULT_WINDOW_LOCATION.y)));
		dialog.setSize(new Dimension(getPreferences().getInt(
				PREFERENCE_WINDOW_SIZE_WIDTH, DEFAULT_WINDOW_SIZE.width),
				getPreferences().getInt(PREFERENCE_WINDOW_SIZE_HEIGHT,
						DEFAULT_WINDOW_SIZE.height)));
		// TODO figure out how to maximize a dialog so we can use the preference
		dialog.setDefaultCloseOperation(PreferenceDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Returns the preferences for this example.
	 * 
	 * @return the example preferences
	 */
	public static Preferences getPreferences() {
		return Preferences.userNodeForPackage(PreferenceFactoryExample.class);
	}

	/**
	 * Creates a preference page for window preferences.
	 * 
	 * @return the Windows preference page
	 */
	private IPreferencePage createWindowPreferencePage() {
		PreferencePageFactory factory = new PreferencePageFactory();
		factory.addCheckboxEditorComponent("Maximized",
				"Maximize the window in startup", PREFERENCE_WINDOW_MAXIMIZED,
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						getPreferences().putBoolean(
								PREFERENCE_WINDOW_MAXIMIZED,
								((JCheckBox) e.getSource()).isSelected());
					}
				});
		factory.addTextEditorComponent("Window Width",
				"Customize the window width on startup",
				PREFERENCE_WINDOW_SIZE_WIDTH, new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						try {
							getPreferences().put(PREFERENCE_WINDOW_SIZE_WIDTH,
									e.getDocument().getText(0, e.getLength()));
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
					}
				});
		factory.addTextEditorComponent("Window Height",
				"Customize the window height on startup",
				PREFERENCE_WINDOW_SIZE_HEIGHT, new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						try {
							getPreferences().put(PREFERENCE_WINDOW_SIZE_HEIGHT,
									e.getDocument().getText(0, e.getLength()));
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
					}
				});
		factory.addTextEditorComponent("Window Location X",
				"Customize the window X location on startup",
				PREFERENCE_WINDOW_LOCATION_X, new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						try {
							getPreferences().put(PREFERENCE_WINDOW_LOCATION_X,
									e.getDocument().getText(0, e.getLength()));
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
					}
				});
		factory.addTextEditorComponent("Window Location Y",
				"Customize the window Y location on startup",
				PREFERENCE_WINDOW_LOCATION_Y, new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						try {
							getPreferences().put(PREFERENCE_WINDOW_LOCATION_Y,
									e.getDocument().getText(0, e.getLength()));
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}

					}

					@Override
					public void changedUpdate(DocumentEvent e) {
					}
				});
		factory.addSection("Window");
		return factory.getPage();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				new PreferenceFactoryExample();
			}
		});
	}
}
