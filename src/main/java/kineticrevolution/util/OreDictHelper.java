package kineticrevolution.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AEnterprise
 */
public class OreDictHelper {
	private static HashMap<String, List<ItemStack>> lookups = new HashMap<String, List<ItemStack>>();

	public static List<ItemStack> lookup(String key) {
		if (!lookups.containsKey(key)) {
			lookups.put(key, OreDictionary.getOres(key));
		}
		return lookups.get(key);
	}

	public static ItemStack getFirstStack(String key) {
		ItemStack stack = lookup(key).get(0);
		return stack != null ? stack.copy() : null;
	}
}
