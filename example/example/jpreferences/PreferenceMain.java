package example.jpreferences;

import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.DefaultPreferenceManager;
import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.model.IPreferenceNode;
import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.storage.DefaultPreferenceStore;
import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.PreferenceDialog;

class PreferenceMain {
	IPreferenceStore store;
	PreferenceDialog dlg;

	public PreferenceMain() throws NullPointerException,
			ConflictingIdentifierException {

		store = new DefaultPreferenceStore(new File("prefs.properties"),
				"This file contains preference settings");
		try {
			store.load();
		} catch (IOException e) {
			// do nothing
		}

		IPreferenceManager mgr = new DefaultPreferenceManager(store);

		DefaultPreferencePage defaultPage = new DefaultPreferencePage(mgr,
				"DefaultPage", "A default preference page");
		ExampleTextFieldPreferencePage textFieldPage = new ExampleTextFieldPreferencePage(
				mgr, "Text Fields", "Show a page populated with text fields");

		try {
			IPreferenceNode parent = new DefaultPreferenceNode();
			mgr.add(new DefaultPreferenceNode("default", defaultPage,
					"Default Page"));
			mgr.add(parent);
			mgr.addTo(parent, new DefaultPreferenceNode("child",
					new DefaultPreferencePage(), "Test Child"));
			mgr.add(new DefaultPreferenceNode("text", textFieldPage,
					"Text Fields"));
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ConflictingIdentifierException e) {
			e.printStackTrace();
		}

		dlg = new PreferenceDialog((Dialog) null, mgr);
		dlg.addWindowListener(dialogWindowListener());
		dlg.setVisible(true);
	}

	/**
	 * @param args
	 * @throws ConflictingIdentifierException
	 * @throws NullPointerException
	 */
	public static void main(String[] args) throws NullPointerException,
			ConflictingIdentifierException {
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
