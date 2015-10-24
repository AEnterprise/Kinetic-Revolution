package kineticrevolution.loaders;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.blocks.BlockDuster;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.lib.Names;
import kineticrevolution.multiblocks.blocks.BlockGeneratorCasing;
import kineticrevolution.multiblocks.blocks.BlockGeneratorInductor;
import kineticrevolution.tileEntities.TileKineticGenerator;
import kineticrevolution.tileEntities.TileMultiBlockBase;
import net.minecraft.block.Block;

/**
 * Created by AEnterprise
 */
public class BlockLoader {
	public static Block generatorCasing;
	public static Block generatorInductor;
	public static Block duster;

	public static void init() {
		//generator
		generatorCasing = new BlockGeneratorCasing();
		generatorInductor = new BlockGeneratorInductor();

		//dusters
		duster = new BlockDuster();

		GameRegistry.registerTileEntity(TileMultiBlockBase.class, Names.TileEntities.MULTIBLOCK_BASE);
		GameRegistry.registerTileEntity(TileKineticGenerator.class, Names.TileEntities.MULTIBLOCK_GENERATOR);
		GameRegistry.registerTileEntity(TileDuster.class, Names.TileEntities.DUSTER);
	}

	public static void addRecipes() {
		//add recipes
	}
}
