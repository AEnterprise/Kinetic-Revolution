package kineticrevolution.loaders;

import kineticrevolution.items.ItemMachineConfigurator;
import kineticrevolution.items.ItemResource;
import kineticrevolution.items.blueprints.ItemBlueprint;
import kineticrevolution.items.dusts.ItemDust;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemLoader {
	public static Item machineConfigurator;
	public static Item resourceItem;
	public static Item dust;
	public static Item blueprint;

	public static void init() {
		machineConfigurator = new ItemMachineConfigurator();
		resourceItem = new ItemResource();
		dust = new ItemDust();
		blueprint = new ItemBlueprint();
	}

	public static void addRecipes() {
		//add the recipes
	}
}
