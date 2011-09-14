package example.jpreferences;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.PreferenceDialog;

class PreferenceExample {

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

				Preferences prefs = Preferences.userRoot();

				PreferenceDialog dlg = new PreferenceDialog(frame, prefs);
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
				dlg.setVisible(true);
			}
		});
	}

}
