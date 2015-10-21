package kineticrevolution.blocks;

import kineticrevolution.lib.Names;
import kineticrevolution.tileEntities.TileDuster;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuster extends BlockBase {



	public BlockDuster() {
		super(Names.Blocks.DUSTER);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileDuster) {
			aabb = ((TileDuster) entity).getBox();
		}
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity instanceof TileDuster) {
			aabb = ((TileDuster) entity).getBox();
		}
		return aabb.offset(x, y, z);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
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
		if (world.isRemote)
			return;
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
