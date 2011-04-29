package example.jpreferences;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import org.jpreferences.ConflictingIdentifierException;
import org.jpreferences.DefaultPreferenceNode;
import org.jpreferences.DefaultPreferencePage;
import org.jpreferences.PreferenceDialog;
import org.jpreferences.PreferenceManager;
import org.jpreferences.PreferenceNode;
import org.jpreferences.PreferenceStore;

class PreferenceMain {
	PreferenceStore store;
	PreferenceDialog dlg;

	public PreferenceMain() {

		store = new PreferenceStore(new File("prefs.properties"),
				"This file contains preference settings");
		try {
			store.load();
		} catch (IOException e) {
			// do nothing
		}

		PreferenceManager mgr = new PreferenceManager(store);

		DefaultPreferencePage defaultPage = new DefaultPreferencePage(mgr,
				"DefaultPage", "A default preference page");
		ExampleTextFieldPreferencePage textFieldPage = new ExampleTextFieldPreferencePage(
				mgr, "Text Fields", "Show a page populated with text fields");

		try {
			PreferenceNode parent = new DefaultPreferenceNode("Parent");
			mgr.add(new DefaultPreferenceNode(defaultPage, "Default Page"));
			mgr.add(parent);
			mgr.addTo(parent, new DefaultPreferenceNode("Child"));
			mgr.add(new DefaultPreferenceNode(textFieldPage, "Text Fields"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ConflictingIdentifierException e) {
			e.printStackTrace();
		}

		dlg = new PreferenceDialog(mgr);
		dlg.addWindowListener(dialogWindowListener());
		dlg.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PreferenceMain();
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
