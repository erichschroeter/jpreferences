package example.jpreferences;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.PreferenceDialog;

import example.jpreferences.pages.PersonalInformationPage;

/**
 * An example showing the simple usage of {@link PreferenceDialog}. This example
 * includes using custom preference pages.
 * 
 * @author Erich Schroeter
 */
public class SimpleDialogWithCustomPageExample {

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

				Preferences prefs = Preferences.userRoot().node(
						"preference-dialog");
				prefs.putInt("number of states", 50);
				prefs.putDouble("pi", 3.14159);
				prefs.put("name", "<Enter your name>");

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

				dlg.add(new PersonalInformationPage("Personal",
						"Enter personal information").getPage());

				dlg.setVisible(true);
			}
		});
	}

}
