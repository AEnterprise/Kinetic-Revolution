package kineticrevolution.multiblocks.definitions;

import kineticrevolution.multiblocks.interfaces.IBlockDefinition;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by big_Xplosion
 */
public class DefinitionMaterial implements IBlockDefinition {

	private Material material;

	public DefinitionMaterial(Material material) {
		this.material = material;
	}

	@Override
	public boolean matchesUnformed(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).getMaterial() == material;
	}

	@Override
	public boolean matchesFormed(World world, int x, int y, int z) {
		return matchesUnformed(world, x, y, z);
	}
}
