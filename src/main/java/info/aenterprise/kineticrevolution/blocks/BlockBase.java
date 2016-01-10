package info.aenterprise.kineticrevolution.blocks;

import info.aenterprise.kineticrevolution.core.KineticRevolution;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class BlockBase extends Block {

	public BlockBase(String name) {
		super(Material.iron);
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(2.5f);
		setResistance(2.5f);
		setCreativeTab(KineticRevolution.creativeTab);
	}
}
