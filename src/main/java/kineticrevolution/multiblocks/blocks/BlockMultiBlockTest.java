package kineticrevolution.multiblocks.blocks;

import kineticrevolution.multiblocks.patterns.MultiBlockPattern;
import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.multiblocks.tileEntities.TileMultiBlockBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockMultiBlockTest extends BlockMultiBlockBase {


	public BlockMultiBlockTest() {
		super("TestBlock", "noTextureYet");
	}

	@Override
	public MultiBlockPattern getPattern() {
		return Patterns.TEST_PATTERN;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMultiBlockBase();
	}
}
