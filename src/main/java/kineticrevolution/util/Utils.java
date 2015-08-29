package kineticrevolution.util;

import com.google.common.base.Strings;
import net.minecraft.util.StatCollector;

/**
 * Created by AEnterprise
 */
public class Utils {

	public static String localize(String key) {
		return ("" + StatCollector.translateToLocal(key)).trim();
	}

	public static String localizeFormatted(String key, Object... objects) {
		return ("" + StatCollector.translateToLocalFormatted(key, objects)).trim();
	}

	public static String localizeAllFormatted(String key, String... strings) {
		Object[] objects = new Object[strings != null ? strings.length : 0];
		if (strings != null) {
			for (int i = 0; i < objects.length; i++) {
				String string = strings[i];
				if (string != null)
					objects[i] = localize(string);
			}
		}
		return localizeFormatted(key, objects);
	}

	public static String decapitalizeFirstChar(String string) {
		return !Strings.isNullOrEmpty(string) ? Character.toLowerCase(string.charAt(0)) + string.substring(1) : null;
	}


}
