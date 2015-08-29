package kineticrevolution.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.core.CTabs;
import kineticrevolution.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by AEnterprise
 */
public class BlockBase extends Block {

	public BlockBase(String name, String registryName, String texture, CreativeTabs tab) {
		super(Material.iron);
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + texture);
		setCreativeTab(tab);
		GameRegistry.registerBlock(this, registryName);
	}

	public BlockBase(String name, String texture) {
		this(name, name, texture, CTabs.MAIN_TAB);
	}

	public BlockBase(String name) {
		this(name, name);
	}
}
