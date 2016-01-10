package info.aenterprise.kineticrevolution.items;

import info.aenterprise.kineticrevolution.core.KineticRevolution;
import net.minecraft.item.Item;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class ItemBase extends Item {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(KineticRevolution.creativeTab);
	}
}
