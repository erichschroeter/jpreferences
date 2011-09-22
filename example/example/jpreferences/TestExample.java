package example.jpreferences;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class TestExample {

	public static void main(String[] args) throws BackingStoreException {
		
		Preferences prefs = Preferences.userRoot();
		
		System.out.println(Arrays.toString(prefs.keys()));
		String node = "my favorite number";
		System.out.println(prefs.getInt("my favorite number", -1));
		prefs.remove(node);
		System.out.println(prefs.getInt("my favorite number", -2));
		
	}
	
}
