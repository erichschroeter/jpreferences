package example.jpreferences;

import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.DefaultPreferenceManager;
import org.jpreferences.IPreferenceManager;
import org.jpreferences.PreferencePageFactory;
import org.jpreferences.ui.PreferenceDialog;

public class PreferenceFactoryExample implements UserPreferences {

	public PreferenceFactoryExample() {
		PreferencePageFactory factory = new PreferencePageFactory();
		factory.addCheckboxEditorComponent("Example Checkbox",
				"Enable/Disable the Example Preference",
				PREFERENCE_MAXIMIZED);
		factory.addTextEditorComponent("Example Text Field",
				"Customize the example text field",
				PREFERENCE_EXAMPLE_TEXTFIELD);
		factory.addSection("Example");
	}

	public static void main(String[] args) {
		// initialize the preferences
		Preferences prefs = Preferences.userNodeForPackage(PreferenceFactoryExample.class);
		prefs.putBoolean(PREFERENCE_MAXIMIZED, false);
		prefs.putInt(PREFERENCE_WINDOW_LOCATION_X, 100);
		prefs.putInt(PREFERENCE_WINDOW_LOCATION_Y, 100);
		prefs.putInt(PREFERENCE_WINDOW_SIZE_WIDTH, 600);
		prefs.putInt(PREFERENCE_WINDOW_SIZE_HEIGHT, 500);
		
		IPreferenceManager mgr = new DefaultPreferenceManager(prefs);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				PreferenceDialog.showDialog(null, null);
			}
		});
	}
}
