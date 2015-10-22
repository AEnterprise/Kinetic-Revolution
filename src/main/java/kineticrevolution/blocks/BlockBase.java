package kineticrevolution.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.common.registry.GameRegistry;

import kineticrevolution.core.CTabs;
import kineticrevolution.lib.Reference;

/**
 * Created by AEnterprise
 */
public abstract class BlockBase extends BlockContainer {

	public BlockBase(String name, String registryName, String texture, CreativeTabs tab) {
		super(Material.iron);
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + texture);
		setCreativeTab(tab);
		GameRegistry.registerBlock(this, registryName);
		setHardness(5);
		setResistance(5);
	}

	public BlockBase(String name, String texture) {
		this(name, name, texture, CTabs.MAIN_TAB);
	}

	public BlockBase(String name) {
		this(name, name);
	}

}
