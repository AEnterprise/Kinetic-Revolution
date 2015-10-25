package kineticrevolution.duster;

import kineticrevolution.blocks.BlockBase;
import kineticrevolution.blocks.BlockDuster;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.util.Location;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by AEnterprise
 */
public class BlockDusterFake extends BlockBase {
	private final AxisAlignedBB[] BOXES = {
			AxisAlignedBB.getBoundingBox(0.65, 0, 0.65, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 0.45),
			AxisAlignedBB.getBoundingBox(0, 0, 0.65, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 0.45),
			AxisAlignedBB.getBoundingBox(0, 0, 0.65, 0.45, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 0.45),
			AxisAlignedBB.getBoundingBox(0.65, 0, 0.65, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0.65, 0, 0, 1, 1, 0.45),
			AxisAlignedBB.getBoundingBox(0, 0, 0.65, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 0.45),
			AxisAlignedBB.getBoundingBox(0, 0, 0.65, 0.45, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 0.45, 1, 0.45)
	};


	public BlockDusterFake() {
		super("dusterFake");
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	private Location getDusterLocation(int x, int y, int z, int meta) {
		Location location = BlockDuster.LOCATIONS[meta].copy();
		location.x *= -1;
		location.y *= -1;
		location.z *= -1;
		location.offset(x, y, z);
		return location;
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
	public String getLocalizedName() {
		return BlockLoader.duster.getLocalizedName();
	}

	@Override
	public String getUnlocalizedName() {
		return BlockLoader.duster.getUnlocalizedName();
	}

	@Override
	public Item getItem(World world, int x, int y, int z) {
		Location location = getDusterLocation(x, y, z, world.getBlockMetadata(x, y, z));
		return BlockLoader.duster.getItem(world, location.x, location.y, location.z);
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
		Location location = getDusterLocation(x, y, z, world.getBlockMetadata(x, y, z));
		BlockLoader.duster.onBlockExploded(world, location.x, location.y, location.z, explosion);
	}


	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		Location location = getDusterLocation(x, y, z, world.getBlockMetadata(x, y, z));
		if (world.getBlock(location.x, location.y, location.z) == BlockLoader.duster) {
			BlockLoader.duster.onNeighborBlockChange(world, location.x, location.y, location.z, block);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
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
}
