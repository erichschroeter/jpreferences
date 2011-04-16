package org.prefs;

import java.awt.Image;

public class DefaultPreferenceNode extends PreferenceNode {

	public DefaultPreferenceNode() {
		this(null);
	}
	
	public DefaultPreferenceNode(PreferencePage page) {
		this(page, null, null);
	}

	public DefaultPreferenceNode(PreferencePage page, String label) {
		this(page, label, null);
	}

	public DefaultPreferenceNode(PreferencePage page, PreferenceNode parent) {
		this(page, parent, null, null);
	}
	
	public DefaultPreferenceNode(PreferencePage page, String label, Image image) {
		this(page, null, label, image);
	}

	public DefaultPreferenceNode(PreferencePage page, PreferenceNode parent, String label, Image image) {
		super(page != null ? page : new DefaultPreferencePage(), parent, label, image);
	}
}
