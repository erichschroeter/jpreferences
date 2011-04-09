package org.prefs;

public class DefaultPreferencePage extends PreferencePage {

	private static final long serialVersionUID = 4271734721354591553L;

	/**
	 * The page container holding this <code>PreferencePage</code>.
	 */
	protected IPreferencePageContainer container;

	/**
	 * Represents whether any setting on this page has changed.
	 * <p>
	 * This value will be <code>True</code> if a value has changed, otherwise
	 * <code>False</code>.
	 * <p>
	 */
	protected boolean dirty = false;

	public DefaultPreferencePage() {
		this("", "");
	}

	/**
	 * @see {@link DefaultPreferencePage#DefaultPreferencePage(IPreferencePageContainer, String, String)}
	 * @param container
	 *            The container in which this <code>PreferencePage</code> is
	 *            being held.
	 */
	public DefaultPreferencePage(IPreferencePageContainer container) {
		this(container, "", "");
	}

	public DefaultPreferencePage(String title, String description) {
		super(title, description);
		createContents();
	}

	/**
	 * Creates a <code>DefaultPreferencePage</code> object specifying the
	 * <i>container</i>, <i>title</i>, and <i>description</i>.
	 * <p>
	 * Calls the super's constructor passing the given <i>title</i> and
	 * <i>description</i> arguments to it. It then calls
	 * <code>setPageContainer(IPreferencePageContainer)</code>.
	 * </p>
	 * <p>
	 * The <i>container</i> argument may not be <code>null</code>. This is to
	 * prevent the <code>NullPointerException</code>'s from happening during
	 * runtime. Often in the <code>performOk()</code> function the container is
	 * called upon to save the values on this <code>PreferencePage</code>, and
	 * if the container is <code>null</code> the application will fail.
	 * </p>
	 * 
	 * @see {@link PreferencePage#PreferencePage(String, String)}
	 * @param container
	 *            The container in which this <code>PreferencePage</code> is
	 *            being held.
	 * @param title
	 *            The title. If value is <code>null</code> the <i>title</i>
	 *            attribute is set to empty string ( <code>""</code>)
	 * @param description
	 *            A description of the page. If value is <code>null</code> the
	 *            <i>description</i> attribute is set to empty string (
	 *            <code>""</code>)
	 */
	public DefaultPreferencePage(IPreferencePageContainer container,
			String title, String description) {
		super(title, description);
		setPageContainer(container);
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
	public void initialize(IPreferenceStore store) {
		
	}

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

	/**
	 * Returns the container in which this <code>PreferencePage</code> is being
	 * held.
	 */
	public IPreferencePageContainer getPageContainer() {
		return container;
	}

	/**
	 * Sets the container in which this <code>PreferencePage</code> is being
	 * held.
	 * <p>
	 * A <code>NullPointerException</code> may often occur if the developer
	 * passes an uninitialized reference to an
	 * <code>IPreferencePageContainer</code> in the
	 * {@link DefaultPreferencePage#DefaultPreferencePage(IPreferencePageContainer, String, String)}
	 * constructor. When this is the case, you should use the
	 * {@link DefaultPreferencePage#DefaultPreferencePage(String, String)}
	 * constructor and set the container after the
	 * <code>IPreferencePageContainer</code> has been initialized.
	 * </p>
	 * 
	 * @see {@link DefaultPreferencePage#DefaultPreferencePage(IPreferencePageContainer, String, String)}
	 * @param container
	 *            The container in which this <code>PreferencePage</code> is
	 *            being held.
	 * @throws NullPointerException
	 *             if the container argument is <code>null</code>.
	 */
	public void setPageContainer(IPreferencePageContainer container)
			throws NullPointerException {
		if (container == null) {
			throw new NullPointerException("container cannot be set to null.");
		}
		this.container = container;
	}

	@Override
	public String getClassName() {
		return "DefaultPreferencePage";
	}

}
