package org.jpreferences;

import org.jpreferences.ui.IPreferencePage;

public interface ICurrentPageListener {

	/**
	 * Handles what to do when the current page changes.
	 * 
	 * @param current
	 *            the current page
	 */
	public void currentPageChanged(IPreferencePage current);
}
