package kineticrevolution.duster;

import kineticrevolution.blocks.BlockBase;
import kineticrevolution.util.Location;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by AEnterprise
 */
public class BlockDusterFake extends BlockBase {


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
}
