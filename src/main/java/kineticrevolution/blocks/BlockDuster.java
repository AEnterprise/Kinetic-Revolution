package kineticrevolution.blocks;

import kineticrevolution.core.CTabs;
import kineticrevolution.duster.Components;
import kineticrevolution.duster.ItemBlockDuster;
import kineticrevolution.duster.TileDuster;
import kineticrevolution.lib.Names;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.util.Location;
import kineticrevolution.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockDuster extends BlockBase {
	public static final Location[] LOCATIONS = {
			new Location(-1, 0, -1),
			new Location(-1, 0, 0),
			new Location(-1, 0, 1),
			new Location(0, 0, -1),
			new Location(0, 0, 1),
			new Location(1, 0, -1),
			new Location(1, 0, 0),
			new Location(1, 0, 1),
			new Location(-1, -1, -1),
			new Location(-1, -1, 0),
			new Location(-1, -1, 1),
			new Location(0, -1, -1),
			new Location(0, -1, 1),
			new Location(1, -1, -1),
			new Location(1, -1, 0),
			new Location(1, -1, 1),
	};

	private final Location[] SUPPORT_LOCATIONS = {
			new Location(-1, -2, -1),
			new Location(-1, -2, 1),
			new Location(1, -2, -1),
			new Location(1, -2, -1)
	};

	public BlockDuster() {
		super(Names.Blocks.DUSTER, Names.Blocks.DUSTER, Names.Blocks.DUSTER, CTabs.MAIN_TAB, ItemBlockDuster.class, Material.grass);
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
		for (Location l : LOCATIONS) {
			Location location = l.copy().offset(x, y, z);
			if (!world.isAirBlock(location.x, location.y, location.z)) {
				return false;
			}
		}

		return isSupported(world, x, y, z);
	}

	public boolean isIntact(World world, int x, int y, int z) {
		for (Location l : LOCATIONS) {
			Location location = l.copy().offset(x, y, z);
			if (!(world.getBlock(location.x, location.y, location.z) == BlockLoader.dusterFake)) {
				return false;
			}
		}
		return isSupported(world, x, y, z);
	}

	public boolean isSupported(World world, int x, int y, int z) {
		for (Location l : SUPPORT_LOCATIONS) {
			Location location = l.copy().offset(x, y, z);
			if (world.isAirBlock(location.x, location.y, location.z)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!isIntact(world, x, y, z) && world.getBlockMetadata(x, y, z) == 0) {
			Utils.dropItemstacks(world, x, y, z, getDrops(world, x, y, z, 0, 0));
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		if (meta == 1) {
			//we're already removing the structure, return to prevent overflow
			return;
		}
		world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		world.destroyBlockInWorldPartially(0, x, y - 1, z, -1);
		for (Location l : LOCATIONS) {
			Location location = l.copy().offset(x, y, z);
			if (world.getBlock(location.x, location.y, location.z) == BlockLoader.dusterFake)
				world.setBlockToAir(location.x, location.y, location.z);
		}
	}



	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		AxisAlignedBB aabb = TileDuster.DEFAULT_BOX;
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

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack stack = new ItemStack(BlockLoader.duster);
		if (entity != null) {
			NBTTagCompound tag = new NBTTagCompound();
			entity.writeToNBT(tag);
			stack.stackTagCompound = tag;
			tag.removeTag("height");
			tag.removeTag("progress");
			tag.removeTag("breakProgress");
			tag.removeTag("targetHeight");
		}
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(stack);
		return list;
	}
}
