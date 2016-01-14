package info.aenterprise.kineticrevolution.core;

import info.aenterprise.kineticrevolution.items.ItemCanister;
import info.aenterprise.kineticrevolution.items.MachineConfigurator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class ItemLoader {
	public static Item machineConfigurator;
	public static ItemCanister canisterT1;
	public static ItemCanister canisterT2;
	public static ItemCanister canisterT3;

	public static void addItems() {
		machineConfigurator = new MachineConfigurator();
		GameRegistry.registerItem(machineConfigurator);
		KineticRevolution.proxy.registerInventoryModel(machineConfigurator);

		if (canisterT1 == null) {
			addCanisters();
		}
	}

	public static void addCanisters() {
		canisterT1 = new ItemCanister(1, 2000);
		GameRegistry.registerItem(canisterT1);
		KineticRevolution.proxy.registerInventoryModel(canisterT1);

		canisterT2 = new ItemCanister(2, 8000);
		GameRegistry.registerItem(canisterT2);
		KineticRevolution.proxy.registerInventoryModel(canisterT2);

		canisterT3 = new ItemCanister(3, 32000);
		GameRegistry.registerItem(canisterT3);
		KineticRevolution.proxy.registerInventoryModel(canisterT3);
	}

	public static void addRecipes() {

	}
}
