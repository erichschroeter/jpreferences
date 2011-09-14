package example.jpreferences;

import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.DefaultPreferencePage;
import org.jpreferences.PreferenceDialog;

class PreferenceMain {

	PreferenceDialog dlg;

	public PreferenceMain() {

		DefaultPreferencePage examplePage = new DefaultPreferencePage(
				"Child title", "This is an example description");
		ExampleTextFieldPage textFieldPage = new ExampleTextFieldPage(
				"Text Fields", "Show a page populated with text fields");
		ExampleMixedComponentsPage mixedFieldPage = new ExampleMixedComponentsPage(
				"Mixed Fields",
				"Show a page populated with a mix of field types");

		dlg = new PreferenceDialog((Dialog) null);
		dlg.addWindowListener(dialogWindowListener());
		dlg.setVisible(true);
	}

	/**
	 * @param args
	 * @throws ConflictingIdentifierException
	 * @throws NullPointerException
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				try {
					new PreferenceMain();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private WindowListener dialogWindowListener() {
		return new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {

			}

			@Override
			public void windowIconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {

			}

			@Override
			public void windowActivated(WindowEvent arg0) {

			}
		};
	}
}
