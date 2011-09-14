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

import org.jpreferences.IPreferencePage;
import org.jpreferences.PreferenceDialog;


public class PreferenceFactoryExample implements UserPreferences {

	/**
	 * Constructs a default <code>PreferenceFactoryExample</code> which
	 * initializes a {@link PreferenceDialog} for this example. Preferences are
	 * loaded from a previous instance of this example and saved upon closing
	 * the example.
	 */
	public PreferenceFactoryExample() {
		
		PreferenceDialog dialog = new PreferenceDialog(null, null);
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
