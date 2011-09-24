package org.jpreferences;

import javax.swing.JTree;

import org.jpreferences.page.CustomPage;

/**
 * A <code>CustomPageTreeNode</code> wraps a {@link CustomPage} object allowing
 * it to be used in a {@link JTree}.
 * 
 * @author Erich Schroeter, http://www.roseindia.net/javatutorials/javaapi.shtml
 */
@SuppressWarnings("serial")
public class CustomPageTreeNode extends PageTreeNode {

	/** The custom page being wrapped. */
	private CustomPage<?> page;

	/**
	 * Constructs a <code>CustomPageTreeNode</code> specifying the
	 * <code>CustomPage</code> object to be wrapped.
	 * 
	 * @param page
	 *            the custom page object
	 */
	public CustomPageTreeNode(CustomPage<?> page) {
		super(page);
		this.page = page;
	}

	/**
	 * Returns the <code>CustomPage</code> object being wrapped.
	 * 
	 * @return the page object
	 */
	public CustomPage<?> getPageObject() {
		return page;
	}

}
