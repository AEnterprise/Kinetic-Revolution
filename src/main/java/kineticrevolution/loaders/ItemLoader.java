package kineticrevolution.loaders;

import kineticrevolution.items.ItemMachineConfigurator;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemLoader {
	public static Item machineConfigurator;

	public static void init() {
		machineConfigurator = new ItemMachineConfigurator();
	}

	public static void addRecipes() {
		//add the recipes
	}
}
