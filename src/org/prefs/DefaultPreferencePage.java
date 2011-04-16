package org.prefs;

public class DefaultPreferencePage extends PreferencePage {

	private static final long serialVersionUID = 4271734721354591553L;

	/**
	 * Represents whether any setting on this page has changed.
	 * <p>
	 * This value will be <code>True</code> if a value has changed, otherwise
	 * <code>False</code>.
	 * <p>
	 */
	protected boolean dirty = false;

	public DefaultPreferencePage() {
		this(null);
	}
	
	/**
	 * Calls <code>DefaultPreferencePage(manager, "", "")</code>
	 * 
	 * @see {@link DefaultPreferencePage#DefaultPreferencePage(PreferenceManager, String, String)}
	 * @param manager
	 *            the manager managing the preferences
	 */
	public DefaultPreferencePage(PreferenceManager manager) {
		this(manager, "", "");
	}

	/**
	 * Creates a <code>DefaultPreferencePage</code> object specifying the
	 * <i>container</i>, <i>title</i>, and <i>description</i>.
	 * <p>
	 * Calls the super's constructor passing the given <i>manager</i>,
	 * <i>title</i>, and <i>description</i> arguments to it.
	 * </p>
	 * <p>
	 * The <i>manager</i> argument may not be <code>null</code>. This is to
	 * prevent the <code>NullPointerException</code>'s from happening during
	 * runtime. Often in the <code>createContent()</code> function the manager
	 * is called upon to initialize the values on this
	 * <code>PreferencePage</code>, and if the manager is <code>null</code> the
	 * application will fail.
	 * </p>
	 * 
	 * @see {@link PreferencePage#PreferencePage(PreferenceManager, String, String)}
	 * @param manager
	 *            the manager managing the preferences
	 * @param title
	 *            The title. If value is <code>null</code> the <i>title</i>
	 *            attribute is set to empty string ( <code>""</code>)
	 * @param description
	 *            A description of the page. If value is <code>null</code> the
	 *            <i>description</i> attribute is set to empty string (
	 *            <code>""</code>)
	 */
	public DefaultPreferencePage(PreferenceManager manager, String title,
			String description) {
		super(manager, title, description);
		createContents();
	}

	/**
	 * Returns the <i>dirty</i> attribute.
	 * 
	 * @see DefaultPreferencePage#dirty
	 * @return <code>True</code> if a value on the page has changed, else
	 *         <code>False</code>
	 */
	public boolean valuesChanged() {
		return dirty;
	}

	//
	// PreferencePage
	//

	@Override
	protected void createContents() {

	}

	//
	// IPreferencePage members
	//

	@Override
	public boolean okToLeave() {
		return !dirty;
	}

	@Override
	public boolean performCancel() {
		return true;
	}

	@Override
	public boolean performOk() {
		return true;
	}

	@Override
	public boolean performDefault() {
		return true;
	}

}
