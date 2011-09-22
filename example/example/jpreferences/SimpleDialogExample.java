package example.jpreferences;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.PreferenceDialog;

/**
 * An example showing the simple usage of {@link PreferenceDialog}.
 * 
 * @author Erich Schroeter
 */
class SimpleDialogExample {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Preferences prefs = Preferences.userRoot().node("preference-dialog");
				prefs.putInt("number of states", 50);
				prefs.putDouble("pi", 3.14159);
				prefs.put("name", "Erich Schroeter");
				prefs.put("test", "test");

				PreferenceDialog dlg = new PreferenceDialog(frame, prefs,
						Preferences.userRoot(), Preferences.systemRoot());
				dlg.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}

					@Override
					public void windowClosed(WindowEvent e) {
						System.exit(0);
					}
				});
				dlg.setLocationRelativeTo(null);
				dlg.setVisible(true);
			}
		});
	}

}
