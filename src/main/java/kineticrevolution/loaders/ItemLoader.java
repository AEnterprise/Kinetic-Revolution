package kineticrevolution.loaders;

import kineticrevolution.items.ItemBase;
import kineticrevolution.items.ItemMachineConfigurator;
import kineticrevolution.items.ItemResource;
import kineticrevolution.items.dusts.ItemDust;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemLoader {
	public static Item machineConfigurator;
	public static Item resourceItem;
	public static Item dust;
	public static Item springTin;
	public static Item springSilver;
	public static Item springLead;
	public static Item springIron;
	public static Item springGold;
	public static Item springEnderium;
	public static Item springElectrum;
	public static Item springDiamond;
	public static Item springCopper;
	public static Item springBronze;

	public static void init() {
		machineConfigurator = new ItemMachineConfigurator();
		resourceItem = new ItemResource();
		dust = new ItemDust();

		springTin = new ItemBase("springTin");
		springSilver = new ItemBase("springSilver");
		springLead = new ItemBase("springLead");
		springIron = new ItemBase("springIron");
		springGold = new ItemBase("springGold");
		springEnderium = new ItemBase("springEnderium");
		springElectrum = new ItemBase("springElectrum");
		springDiamond = new ItemBase("springDiamond");
		springCopper = new ItemBase("springCopper");
		springBronze = new ItemBase("springBronze");
	}

	public static void addRecipes() {
		//add the recipes
	}
}
