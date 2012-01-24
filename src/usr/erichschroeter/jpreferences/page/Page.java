package usr.erichschroeter.jpreferences.page;

/**
 * The <code>Page</code> interface provides methods for accessing and modifying
 * data about a page.
 * 
 * @author Erich Schroeter
 */
public interface Page {

	/**
	 * Returns the page title. This is a short string that should describe what
	 * is on the page.
	 * 
	 * @return the page title
	 */
	public String getPageTitle();

	/**
	 * Sets the page title. This is a short string that should describe what is
	 * on the page.
	 * 
	 * @param title
	 *            the page title
	 */
	public void setPageTitle(String title);

	/**
	 * Returns the page description. This should be a short description about
	 * what is on the page.
	 * 
	 * @return the page description
	 */
	public String getPageDescription();

	/**
	 * Sets the page description. This should be a short description about what
	 * is on the page.
	 * 
	 * @param description
	 *            the page description
	 */
	public void setPageDescription(String description);
}
