package example.jpreferences;

import java.awt.Dimension;
import java.awt.Point;

/**
 * The <code>UserPreferences</code> interface is a container for all user
 * preference keys, which are used to look up the preference values. Default
 * values may also be stored here.
 * 
 * @author Erich Schroeter
 */
public interface UserPreferences {

	/** The preference key for whether the window is maximized. */
	public static final String PREFERENCE_WINDOW_MAXIMIZED = "preference.example.isMaximized";
	/** The default preference value for the window being maximized. */
	public static final boolean DEFAULT_WINDOW_MAXIMIZED = false;
	/** The preference key for the window's X location. */
	public static final String PREFERENCE_WINDOW_LOCATION_X = "preference.example.window.x";
	/** The preference key for the window's Y location. */
	public static final String PREFERENCE_WINDOW_LOCATION_Y = "preference.example.window.y";
	/** The default preference value for the window's X and Y location. */
	public static final Point DEFAULT_WINDOW_LOCATION = new Point(100, 100);
	/** The preference key for the window's width. */
	public static final String PREFERENCE_WINDOW_SIZE_WIDTH = "preference.example.window.width";
	/** The preference key for the window's height. */
	public static final String PREFERENCE_WINDOW_SIZE_HEIGHT = "preference.example.window.height";
	/** The default preference value for the window's width and height. */
	public static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(600, 500);

}
