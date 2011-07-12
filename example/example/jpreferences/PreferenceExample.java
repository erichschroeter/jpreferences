package example.jpreferences;

import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jpreferences.IPreferenceManager;
import org.jpreferences.DefaultPreferenceManager;
import org.jpreferences.model.DefaultPreferenceNode;
import org.jpreferences.model.IPreferenceNode;
import org.jpreferences.storage.ConflictingIdentifierException;
import org.jpreferences.storage.IPreferenceStore;
import org.jpreferences.storage.PropertiesPreferenceStore;
import org.jpreferences.ui.DefaultPreferencePage;
import org.jpreferences.ui.PreferenceDialog;

class PreferenceMain {

	IPreferenceStore store;
	PreferenceDialog dlg;

	public PreferenceMain() throws NullPointerException,
			ConflictingIdentifierException {

		store = new PropertiesPreferenceStore(new File("prefs.properties"),
				"This file contains preference settings");
		store.load();

		IPreferenceManager mgr = new DefaultPreferenceManager(store);

		DefaultPreferencePage examplePage = new DefaultPreferencePage(mgr,
				"Child title", "This is an example description");
		ExampleTextFieldPage textFieldPage = new ExampleTextFieldPage(
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
			mgr.add(parent, new DefaultPreferenceNode("child",
					new DefaultPreferencePage(mgr, "Child Node",
							"This is a child preference page."), null));
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
				} catch (ConflictingIdentifierException e) {
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