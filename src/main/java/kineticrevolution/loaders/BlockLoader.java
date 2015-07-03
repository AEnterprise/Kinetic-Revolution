package kineticrevolution.loaders;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.multiblocks.blocks.BlockMultiBlockTest;
import kineticrevolution.multiblocks.tileEntities.TileMultiBlockTest;
import net.minecraft.block.Block;

/**
 * Created by AEnterprise
 */
public class BlockLoader {
	public static Block test;

	public static void init() {
		test = new BlockMultiBlockTest();

		GameRegistry.registerTileEntity(TileMultiBlockTest.class, "tileMultiBlockTest");
	}

	public static void addRecipes() {
		//add recipes
	}
}
