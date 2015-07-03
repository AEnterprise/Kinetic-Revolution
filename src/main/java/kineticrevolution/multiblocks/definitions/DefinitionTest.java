package kineticrevolution.multiblocks.definitions;

import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class DefinitionTest implements IBlockDefinition {
	@Override
	public boolean matches(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == BlockLoader.test;
	}
}
