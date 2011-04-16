package org.prefs.example;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.prefs.DefaultPreferenceNode;
import org.prefs.DefaultPreferencePage;
import org.prefs.PreferenceDialog;
import org.prefs.PreferenceManager;
import org.prefs.PreferenceNode;
import org.prefs.PreferenceStore;

class PreferenceMain {
	PreferenceManager mgr;
	PreferenceNode general;
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

		mgr = new PreferenceManager(store);

		DefaultPreferencePage backgroundPage = new DefaultPreferencePage(mgr,
				"Background", "A description");
		PreferenceNode background = new DefaultPreferenceNode(backgroundPage,
				"Background");

		DefaultPreferencePage colorPage = new DefaultPreferencePage(mgr,
				"Color", "A colorful description");
		PreferenceNode color = new DefaultPreferenceNode(colorPage, "Color");

		general = new DefaultPreferenceNode(backgroundPage, "General");
		PreferenceNode thread = new DefaultPreferenceNode(colorPage, "Threads");

		mgr.add(general);
		mgr.addTo(general, background);
		mgr.addTo(general, color);
		mgr.add(thread);

		// Create UI

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2, dim.height / 2);

		JButton button = new JButton("Settings");

		window.add(button);
		window.pack();
		window.setVisible(true);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDialog();
			}
		});

		dlg = new PreferenceDialog(window, mgr);

	}

	public void showDialog() {
		dlg.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PreferenceMain();
	}

}
