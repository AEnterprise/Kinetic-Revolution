package kineticrevolution.blocks;

import kineticrevolution.duster.Components;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.lib.Names;
import kineticrevolution.loaders.ItemLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuster extends BlockBase {

	public BlockDuster() {
		super(Names.Blocks.DUSTER);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack.getItem() == ItemLoader.resourceItem) {
			TileEntity e = world.getTileEntity(x, y, z);
			TileDuster duster = null;
			if (e instanceof TileDuster) {
				duster = (TileDuster) e;
			} else {
				return false;
			}
			switch (stack.getItemDamage()) {
				//tin spring
				case 1:
					duster.installComponent(Components.PLAYER_PRESSURE_PLATE);
					break;
				//silver spring
				case 2:
					duster.installComponent(Components.SLIME_PRESSURE_PLATE);
					break;
				//lead spring
				case 3:
					duster.installComponent(Components.ANVIL_PRESSURE_PLATE);
					break;
				//iron spring
				case 4:
					duster.removeComponent(Components.PLAYER_PRESSURE_PLATE);
					break;
				//gold spring
				case 5:
					duster.removeComponent(Components.SLIME_PRESSURE_PLATE);
					break;
				//enderium spring
				case 6:
					duster.removeComponent(Components.ANVIL_PRESSURE_PLATE);
					break;
			}
		}
		return true;
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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileDuster();
	}

	@Override
	public int getRenderType() {
		return -1;
	}
}
