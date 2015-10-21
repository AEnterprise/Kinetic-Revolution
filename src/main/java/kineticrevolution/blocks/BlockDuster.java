package kineticrevolution.blocks;

import kineticrevolution.lib.Names;
import kineticrevolution.tileEntities.TileDuster;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuster extends BlockBase {

	private static final AxisAlignedBB[] boxes = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.8, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.6, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.4, 1),
	};

	public BlockDuster() {
		super(Names.Blocks.DUSTER);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 0 || meta > 3)
			meta = 0;
		AxisAlignedBB aabb = boxes[meta];
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 0 || meta > 3)
			meta = 0;
		return boxes[meta].copy().offset(x, y, z);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		AxisAlignedBB aabb = boxes[0];
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileDuster) {
			((TileDuster) tileEntity).onFallenUpon(entity, distance);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileDuster();
	}

	@Override
	public int getRenderType() {
		return -1;
	}
}
