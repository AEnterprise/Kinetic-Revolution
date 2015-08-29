package kineticrevolution.util;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by AEnterprise
 */
public class OreDictHelper {

	private static final Map<String, List<ItemStack>> lookups = Maps.newHashMap();

	public static List<ItemStack> lookup(String key) {
		if (!lookups.containsKey(key)) {
			lookups.put(key, OreDictionary.getOres(key));
		}
		return lookups.get(key);
	}

	public static ItemStack getFirstStack(String key) {
		List<ItemStack> stacks = lookup(key);
		return stacks == null || stacks.isEmpty() ? null : ItemStack.copyItemStack(stacks.get(0));
	}

}
