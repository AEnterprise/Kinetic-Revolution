package kineticrevolution.multiblocks.interfaces;

import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public interface IBlockDefinition {

	boolean matches(World world, int x, int y, int z);
}
