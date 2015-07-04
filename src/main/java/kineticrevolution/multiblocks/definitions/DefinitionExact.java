package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionExact implements IBlockDefinition {

    private Block match;
    private int meta;

    public DefinitionExact(Block match, int meta) {
        this.match = match;
        this.meta = meta;
    }

    public DefinitionExact(Block match) {
        this(match, 0);
    }

    @Override
    public boolean matches(World world, int x, int y, int z) {
        boolean test = world.getBlock(x, y, z) == this.match && world.getBlockMetadata(x, y, z) == this.meta;
		System.out.println(test + " " + world.getBlock(x, y, z) + " " + world.getBlockMetadata(x, y, z) + " " + this.match);
		return world.getBlock(x, y, z) == match && world.getBlockMetadata(x, y, z) == meta;
    }
}
