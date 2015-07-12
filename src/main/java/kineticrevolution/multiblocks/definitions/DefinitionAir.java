package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionAir implements IBlockDefinition {

	@Override
	public boolean matchesUnformed(World world, int x, int y, int z) {
		return world.isAirBlock(x, y, z);
	}

	@Override
	public boolean matchesFormed(World world, int x, int y, int z) {
		return matchesUnformed(world, x, y, z);
	}
}
