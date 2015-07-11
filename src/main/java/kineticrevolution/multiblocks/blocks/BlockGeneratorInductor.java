package kineticrevolution.multiblocks.blocks;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.multiblocks.tileEntities.TileKineticGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockGeneratorInductor extends BlockMultiBlockBase {

	public BlockGeneratorInductor() {
		super("generatorInductor");
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
