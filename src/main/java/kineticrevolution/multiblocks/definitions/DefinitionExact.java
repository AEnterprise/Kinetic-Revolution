package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionExact implements IBlockDefinition {

    private Block match;
	private int metaUnformed, metaFormed;

	public DefinitionExact(Block match, int metaUnformed, int metaFormed) {
		this.match = match;
		this.metaUnformed = metaUnformed;
		this.metaFormed = metaFormed;
	}

    public DefinitionExact(Block match) {
		this(match, 0, 1);
	}

    @Override
	public boolean matchesUnformed(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == match && world.getBlockMetadata(x, y, z) == metaUnformed;
	}

	@Override
	public boolean matchesFormed(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == match && world.getBlockMetadata(x, y, z) == metaFormed;
	}
}
