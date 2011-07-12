package example.jpreferences;

/**
 * The <code>UserPreferences</code> interface is a container for all user
 * preference keys, which are used to look up the preference values. Default
 * values may also be stored here.
 * 
 * @author Erich Schroeter
 */
public interface UserPreferences {

	/** The preference key for whether the window is maximized. */
	public static final String PREFERENCE_MAXIMIZED = "preference.example.isMaximized";
	/** The preference key for the window's x location. */
	public static final String PREFERENCE_WINDOW_LOCATION_X = "preference.example.window.x";
	/** The preference key for the window's y location. */
	public static final String PREFERENCE_WINDOW_LOCATION_Y = "preference.example.window.y";
	/** The preference key for the window's width. */
	public static final String PREFERENCE_WINDOW_SIZE_WIDTH = "preference.example.window.width";
	/** The preference key for the window's height. */
	public static final String PREFERENCE_WINDOW_SIZE_HEIGHT = "preference.example.window.height";
	/** The preference key for the . */
	public static final String PREFERENCE_EXAMPLE_TEXTFIELD = "preference.example.textfield";

}
