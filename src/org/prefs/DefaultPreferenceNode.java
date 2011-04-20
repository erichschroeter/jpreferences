package org.prefs;

import javax.swing.Icon;

public class DefaultPreferenceNode extends PreferenceNode {

	public DefaultPreferenceNode() throws NullPointerException,
			ConflictingIdentifierException {
		this(null, null, null, null, null);
	}

	public DefaultPreferenceNode(PreferencePage page)
			throws NullPointerException, ConflictingIdentifierException {
		this(page, null, null, null, null);
	}

	public DefaultPreferenceNode(String label)
			throws NullPointerException, ConflictingIdentifierException {
		this(null, null, null, label, null);
	}

	public DefaultPreferenceNode(String label, Icon icon)
			throws NullPointerException, ConflictingIdentifierException {
		this(null, null, null, label, icon);
	}

	public DefaultPreferenceNode(String identifier, String label)
			throws NullPointerException, ConflictingIdentifierException {
		this(null, identifier, null, label, null);
	}

	public DefaultPreferenceNode(PreferencePage page, String label)
			throws NullPointerException, ConflictingIdentifierException {
		this(page, label, null);
	}

	public DefaultPreferenceNode(PreferencePage page, PreferenceNode parent)
			throws NullPointerException, ConflictingIdentifierException {
		this(page, null, parent, null, null);
	}

	public DefaultPreferenceNode(PreferencePage page, String label, Icon icon)
			throws NullPointerException, ConflictingIdentifierException {
		this(page, null, null, label, icon);
	}

	public DefaultPreferenceNode(PreferencePage page, String identifier,
			PreferenceNode parent, String label, Icon icon)
			throws NullPointerException, ConflictingIdentifierException {
		super(page != null ? page : new DefaultPreferencePage(), identifier,
				parent, label, icon);
	}
}
