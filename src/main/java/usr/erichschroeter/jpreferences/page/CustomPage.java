package usr.erichschroeter.jpreferences.page;

import java.awt.Component;

import javax.swing.JPanel;

import usr.erichschroeter.jpreferences.PreferenceDialog;

/**
 * A <code>CustomPage</code> implements the {@link Page} interface and is
 * intended to be derived from for creating custom preference pages. It provides
 * a means to customizing a UI component to be displayed in a
 * {@link PreferenceDialog} as a custom preference page.
 * 
 * @author Erich Schroeter
 */
public abstract class CustomPage<C extends Component> implements Page {

	/** A short title to represent the custom page in the preferences tree. */
	protected String title;
	/** A short description of what is on the custom page. */
	protected String description;
	/** A reference to the actual UI component. */
	protected C page;

	/**
	 * Creates a <code>CustomPage</code> specifying the title.
	 * <p>
	 * If <code>title</code> is <code>null</code> it is set to an empty string.
	 * <p>
	 * This is equivalent to <code>CustomPage(title, null, null)</code>.
	 * 
	 * @see #CustomPage(String, String, Component)
	 * @param title
	 *            a short string to represent the preference page in a tree
	 */
	public CustomPage(String title) {
		this(title, null, null);
	}

	/**
	 * Creates a <code>CustomPage</code> specifying the title and description.
	 * <p>
	 * If <code>title</code> or <code>description</code> are <code>null</code>
	 * they are set to an empty string.
	 * <p>
	 * This is equivalent to <code>CustomPage(title, description, null)</code>.
	 * 
	 * @see #CustomPage(String, String, Component)
	 * @param title
	 *            a short string to represent the preference page in a tree
	 * @param description
	 *            a short description of what page is useful for
	 */
	public CustomPage(String title, String description) {
		this(title, description, null);
	}

	/**
	 * Creates a <code>CustomPage</code> specifying the title and the UI
	 * component.
	 * <p>
	 * If <code>title</code> is <code>null</code> it is set to an empty string.
	 * If <code>component</code> is <code>null</code> it is set to a
	 * {@link JPanel} instance.
	 * <p>
	 * This is equivalent to <code>CustomPage(title, null, component)</code>.
	 * 
	 * @see #CustomPage(String, String, Component)
	 * @param title
	 *            a short string to represent the preference page in a tree
	 * @param component
	 *            the UI component of the preference page
	 */
	public CustomPage(String title, C component) {
		this(title, null, component);
	}

	/**
	 * Creates a <code>CustomPage</code> specifying the title, description, and
	 * the UI component.
	 * <p>
	 * If <code>title</code> or <code>description</code> are <code>null</code>
	 * they are set to an empty string. If <code>component</code> is
	 * <code>null</code> it is set to a {@link JPanel} instance.
	 * 
	 * @param title
	 *            a short string to represent the custom page in a tree
	 * @param description
	 *            a short description of what page is useful for
	 * @param component
	 *            the UI component of the custom page
	 */
	@SuppressWarnings("unchecked")
	public CustomPage(String title, String description, C component) {
		setPageTitle(title);
		setPageDescription(description);
		if (component == null) {
			component = (C) new JPanel();
		}
		setPage(component);
		initializePage(getPage());
	}

	/**
	 * Initializes the preference page. This is where you customize the page UI
	 * component which determines what is displayed to the user in a
	 * {@link PreferenceDialog}.
	 * 
	 * @param page
	 *            the page UI component to be initialized
	 */
	protected abstract void initializePage(C page);

	/**
	 * Returns the page UI component. This is what contains the information
	 * actually displayed to the user on a custom page.
	 * 
	 * @return the page UI component
	 */
	public C getPage() {
		return page;
	}

	/**
	 * Sets the page UI component. This is what contains the information
	 * actually displayed to the user on a custom page.
	 * 
	 * @param page
	 *            the page UI component
	 */
	public void setPage(C page) {
		this.page = page;
	}

	//
	// Page members
	//

	@Override
	public void setPageTitle(String title) {
		this.title = title;
	}

	@Override
	public String getPageTitle() {
		return title;
	}

	@Override
	public void setPageDescription(String description) {
		this.description = description;
	}

	@Override
	public String getPageDescription() {
		return description;
	}

}
