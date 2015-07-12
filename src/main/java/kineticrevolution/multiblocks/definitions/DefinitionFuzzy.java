package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionFuzzy implements IBlockDefinition {

	private Block match;

	public DefinitionFuzzy(Block match) {
		this.match = match;
	}

	@Override
	public boolean matchesUnformed(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == match;
	}

	@Override
	public boolean matchesFormed(World world, int x, int y, int z) {
		return matchesUnformed(world, x, y, z);
	}
}
