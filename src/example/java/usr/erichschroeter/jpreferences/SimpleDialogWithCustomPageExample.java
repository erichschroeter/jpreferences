package usr.erichschroeter.jpreferences;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import usr.erichschroeter.jpreferences.pages.PersonalInformationPage;
import usr.erichschroeter.jpreferences.pages.ProfilesPage;

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
				dlg.setCustomPagesEnabled(true);
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
						"Enter personal information"));
				try {
					dlg.add(new ProfilesPage(createTestProfiles()));
				} catch (FileNotFoundException e) {
				} catch (URISyntaxException e) {
				} catch (IOException e) {
				}

				dlg.setVisible(true);
			}
		});
	}

	public static List<String[]> createTestProfiles()
			throws URISyntaxException, IOException {
		List<String[]> profiles = new Vector<String[]>();
		BufferedReader csv = new BufferedReader(new FileReader(new File(
				SimpleDialogWithCustomPageExample.class.getClassLoader()
						.getResource("test/resources/profiles.csv").toURI())));
		String line;
		while ((line = csv.readLine()) != null) {
			profiles.add(line.split(","));
		}
		return profiles;
	}

}
