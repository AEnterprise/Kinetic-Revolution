package kineticrevolution.loaders;

import kineticrevolution.multiblocks.blocks.BlockMultiBlockTest;
import net.minecraft.block.Block;

/**
 * Created by AEnterprise
 */
public class BlockLoader {
	public static Block test;

	public static void init() {
		test = new BlockMultiBlockTest();
	}

	public static void addRecipes() {
		//add recipes
	}
}
