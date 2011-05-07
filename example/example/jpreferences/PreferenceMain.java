package example.jpreferences;

import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

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
		store.load();

		IPreferenceManager mgr = new DefaultPreferenceManager(store);

		ExampleDefaultPage examplePage = new ExampleDefaultPage(mgr,
				"Child title", "This is an example description");
		ExampleTextFieldPreferencePage textFieldPage = new ExampleTextFieldPreferencePage(
				mgr, "Text Fields", "Show a page populated with text fields");
		ExampleMixedComponentsPage mixedFieldPage = new ExampleMixedComponentsPage(
				mgr, "Mixed Fields",
				"Show a page populated with a mix of field types");
		
		IPreferenceNode parent = new DefaultPreferenceNode("parent",
				new DefaultPreferencePage(mgr, null,
						"This is a parent node page"), "Parent");

		try {
			mgr.add(new DefaultPreferenceNode("default", examplePage,
					"Default Page"));
			mgr.add(parent);
			mgr.addTo(parent, new DefaultPreferenceNode("child",
					new DefaultPreferencePage(), "Empty child"));
			mgr.add(new DefaultPreferenceNode("text", textFieldPage,
					"Text Fields"));
			mgr.add(new DefaultPreferenceNode("mixed", mixedFieldPage,
					"Mixed Components"));
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
