package kineticrevolution.multiblocks.blocks;

import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.multiblocks.tileEntities.TileMultiBlockTest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockMultiBlockTest extends BlockMultiBlockBase {


	public BlockMultiBlockTest() {
		super(Patterns.TEST_PATTERN, "TestBlock", "noTextureYet");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMultiBlockTest();
	}
}
