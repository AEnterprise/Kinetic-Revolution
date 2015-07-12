package kineticrevolution.multiblocks.interfaces;

import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public interface IBlockDefinition {

	boolean matchesUnformed(World world, int x, int y, int z);

	boolean matchesFormed(World world, int x, int y, int z);
}
