package kineticrevolution.items;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.core.CTabs;
import kineticrevolution.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class ItemBase extends Item {

	public ItemBase(String name, String registryName, String texture, CreativeTabs tab) {
		setUnlocalizedName(name);
		setTextureName(Reference.MOD_ID + ":" + texture);
		setCreativeTab(tab);
		GameRegistry.registerItem(this, registryName);
	}

	public ItemBase(String name, String texture) {
		this(name, name, texture, CTabs.MAIN_TAB);
	}

	public ItemBase(String name) {
		this(name, name);
	}
}
