package kineticrevolution.multiblocks.blocks;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.multiblocks.tileEntities.TileKineticGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockGeneratorCasing extends BlockMultiBlockBase {

	public BlockGeneratorCasing() {
		super("generatorCasing");
	}

	@Override
	public MultiBlockPattern getPattern() {
		return Patterns.KINETIC_GENERATOR;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileKineticGenerator();
	}
}
