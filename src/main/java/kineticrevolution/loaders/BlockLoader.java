package kineticrevolution.loaders;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.multiblocks.blocks.BlockGeneratorCasing;
import kineticrevolution.multiblocks.blocks.BlockGeneratorInductor;
import kineticrevolution.multiblocks.tileEntities.TileKineticGenerator;
import kineticrevolution.multiblocks.tileEntities.TileMultiBlockBase;
import net.minecraft.block.Block;

/**
 * Created by AEnterprise
 */
public class BlockLoader {
	public static Block generatorCasing;
	public static Block generatorInductor;

	public static void init() {
		//generator
		generatorCasing = new BlockGeneratorCasing();
		generatorInductor = new BlockGeneratorInductor();

		GameRegistry.registerTileEntity(TileMultiBlockBase.class, "tileMultiBlockTest");
		GameRegistry.registerTileEntity(TileKineticGenerator.class, "tileKineticGenerator");
	}

	public static void addRecipes() {
		//add recipes
	}
}
