package kineticrevolution.loaders;

import kineticrevolution.items.ItemMachineConfigurator;
import kineticrevolution.items.dusts.ItemDust;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemLoader {
	public static Item machineConfigurator;
	public static Item dust;

	public static void init() {
		machineConfigurator = new ItemMachineConfigurator();
		dust = new ItemDust();
	}

	public static void addRecipes() {
		//add the recipes
	}
}
