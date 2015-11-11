package kineticrevolution.loaders;

import cpw.mods.fml.common.registry.GameRegistry;
import kineticrevolution.blocks.BlockAssembler;
import kineticrevolution.blocks.BlockDuster;
import kineticrevolution.duster.BlockDusterFake;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.lib.Names;
import kineticrevolution.multiblocks.blocks.BlockGeneratorCasing;
import kineticrevolution.multiblocks.blocks.BlockGeneratorInductor;
import kineticrevolution.tileEntities.TileAssembler;
import kineticrevolution.tileEntities.TileKineticGenerator;
import kineticrevolution.tileEntities.TileMultiBlockBase;
import net.minecraft.block.Block;

/**
 * Created by AEnterprise
 */
public class BlockLoader {
	public static Block generatorCasing;
	public static Block generatorInductor;
	public static BlockDuster duster;
	public static Block dusterFake;
	public static Block assembler;

	public static void init() {
		//generator
		generatorCasing = new BlockGeneratorCasing();
		generatorInductor = new BlockGeneratorInductor();

		//dusters
		duster = new BlockDuster();
		dusterFake = new BlockDusterFake();

		assembler = new BlockAssembler();

		GameRegistry.registerTileEntity(TileMultiBlockBase.class, Names.TileEntities.MULTIBLOCK_BASE);
		GameRegistry.registerTileEntity(TileKineticGenerator.class, Names.TileEntities.MULTIBLOCK_GENERATOR);
		GameRegistry.registerTileEntity(TileDuster.class, Names.TileEntities.DUSTER);
		GameRegistry.registerTileEntity(TileAssembler.class, Names.TileEntities.ASSEMBLER);
	}

	public static void addRecipes() {
		//add recipes
	}
}
