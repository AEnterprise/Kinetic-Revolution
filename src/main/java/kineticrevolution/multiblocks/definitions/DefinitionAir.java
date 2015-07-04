package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionAir implements IBlockDefinition {

	@Override
	public boolean matches(World world, int x, int y, int z) {
		return world.isAirBlock(x, y, z);
	}
}
