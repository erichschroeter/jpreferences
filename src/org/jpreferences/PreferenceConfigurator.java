package org.jpreferences;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
public @interface PreferenceConfigurator {
	
	/**
	 * The location to store the default preferences.
	 */
	public String location();
}
