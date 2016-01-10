package info.aenterprise.kineticrevolution.core;

import info.aenterprise.kineticrevolution.blocks.BlockFluidicCompressor;
import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class BlockLoader {
	public static Block blockFluidicCompressor;

	public static void addBlocks() {
		blockFluidicCompressor = new BlockFluidicCompressor();
		GameRegistry.registerBlock(blockFluidicCompressor);

		KineticRevolution.proxy.registerInventoryModel(blockFluidicCompressor);
		GameRegistry.registerTileEntity(TileEntityFluidicCompressor.class, "tileFluidicCompressor");
	}

	public static void addRecipes() {

	}
}
