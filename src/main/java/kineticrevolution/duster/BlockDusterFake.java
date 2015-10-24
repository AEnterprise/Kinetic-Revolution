package kineticrevolution.duster;

import kineticrevolution.blocks.BlockBase;
import kineticrevolution.util.Location;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AEnterprise
 */
public class BlockDusterFake extends BlockBase {
	private static AxisAlignedBB[] BOXES = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1)
	};


	public BlockDusterFake() {
		super("dusterFake");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		Location location = TileDuster.LOCATIONS[meta].copy();
		location.x *= -1;
		location.y *= -1;
		location.z *= -1;
		location.offset(x, y, z);
		world.getBlock(location.x, location.y, location.z).onBlockDestroyedByPlayer(world, location.x, location.y, location.z, meta);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		AxisAlignedBB aabb = BOXES[world.getBlockMetadata(x, y, z)];
		setBlockBounds((float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return null;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance) {
		BOXES = new AxisAlignedBB[]{
				AxisAlignedBB.getBoundingBox(0.65, 0, 0.65, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 0.45),
				AxisAlignedBB.getBoundingBox(0, 0, 0.65, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 0.45),
				AxisAlignedBB.getBoundingBox(0, 0, 0.65, 0.45, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 0.45),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
				AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1)
		};
	}
}
