package kineticrevolution.blocks;

import kineticrevolution.core.CTabs;
import kineticrevolution.duster.Components;
import kineticrevolution.duster.ItemBlockDuster;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.lib.Names;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.util.Location;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuster extends BlockBase {

	public BlockDuster() {
		super(Names.Blocks.DUSTER, Names.Blocks.DUSTER, Names.Blocks.DUSTER, CTabs.MAIN_TAB, ItemBlockDuster.class);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getCurrentEquippedItem();
		if (!world.isRemote && stack != null && stack.getItem() == ItemLoader.resourceItem) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileDuster) {
				TileDuster duster = (TileDuster) tile;
				switch (stack.getItemDamage()) {
					//tin spring
					case 1:
						if (duster.installComponent(Components.PLAYER_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully installed " + Components.PLAYER_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.PLAYER_PRESSURE_PLATE + " is already installed"));
						}
						break;
					//silver spring
					case 2:
						if (duster.installComponent(Components.SLIME_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully installed " + Components.SLIME_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.SLIME_PRESSURE_PLATE + " is already installed"));
						}
						break;
					//lead spring
					case 3:
						if (duster.installComponent(Components.ANVIL_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully installed " + Components.ANVIL_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.ANVIL_PRESSURE_PLATE + " is already installed"));
						}
						break;
					//iron spring
					case 4:
						if (duster.removeComponent(Components.PLAYER_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully removed " + Components.PLAYER_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.PLAYER_PRESSURE_PLATE + " is not installed"));
						}
						break;
					//gold spring
					case 5:
						if (duster.removeComponent(Components.SLIME_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully removed " + Components.SLIME_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.SLIME_PRESSURE_PLATE + " is not installed"));
						}
						break;
					//enderium spring
					case 6:
						if (duster.removeComponent(Components.ANVIL_PRESSURE_PLATE)) {
							player.addChatMessage(new ChatComponentText("Successfully removed " + Components.ANVIL_PRESSURE_PLATE));
							return true;
						} else {
							player.addChatMessage(new ChatComponentText("ERROR: " + Components.ANVIL_PRESSURE_PLATE + " is not installed"));
						}
						break;
				}
			}
		}
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
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		for (Location location : TileDuster.LOCATIONS) {
			Location l = location.copy().offset(x, y, z);
			if (!world.isAirBlock(l.x, l.y, l.z)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		for (Location l : TileDuster.LOCATIONS) {
			Location location = l.copy().offset(x, y, z);
			world.setBlockToAir(location.x, location.y, location.z);
		}
		world.setBlockToAir(x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		AxisAlignedBB aabb = TileDuster.DEFAULT_BOX;
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileDuster) {
			aabb = ((TileDuster) tile).getBox();
		}
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
