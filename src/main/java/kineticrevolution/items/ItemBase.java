package kineticrevolution.items;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.KineticRevolution;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemBase extends Item {

	public ItemBase(String name, String registryName, String texture, CreativeTabs tab) {
		setUnlocalizedName(name);
		setTextureName("kineticrevolution:" + texture);
		setCreativeTab(tab);
		GameRegistry.registerItem(this, registryName);
	}

	public ItemBase(String name, String texture) {
		this(name, name, texture, KineticRevolution.MAIN_TAB);
	}

	public ItemBase(String name) {
		this(name, name);
	}
}
