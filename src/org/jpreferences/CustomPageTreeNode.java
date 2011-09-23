package org.jpreferences;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jpreferences.page.Page;

/**
 * A <code>CustomPageTreeNode</code> wraps a {@link Page} object allowing it to
 * be used in a {@link JTree}.
 * 
 * @author Erich Schroeter, http://www.roseindia.net/javatutorials/javaapi.shtml
 */
@SuppressWarnings("serial")
public class CustomPageTreeNode extends DefaultMutableTreeNode {

	/** The page being wrapped. */
	private Page page;

	/**
	 * Constructs a <code>CustomPageTreeNode</code> specifying the
	 * <code>Page</code> object to be wrapped.
	 * 
	 * @param page
	 *            the page object
	 */
	public CustomPageTreeNode(Page page) {
		super(page.getPageTitle());
		this.page = page;
	}

	/**
	 * Returns the <code>Page</code> object being wrapped.
	 * 
	 * @return the page object
	 */
	public Page getPageObject() {
		return page;
	}

}
