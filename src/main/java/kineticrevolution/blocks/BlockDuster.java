package kineticrevolution.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Created by AEnterprise
 */
public class BlockDuster extends BlockBase {
	private AxisAlignedBB[] boxes = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.8, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.6, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.4, 1),
	};

	public BlockDuster() {
		super("duster");
	}


	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return boxes[world.getBlockMetadata(x, y, z)].copy().offset(x, y, z);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
		if (distance < 0.8)
			return;
		int meta = world.getBlockMetadata(x, y, z) + 1;
		if (meta == 4)
			meta = 0;
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
}
