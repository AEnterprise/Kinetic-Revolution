package kineticrevolution.duster;

import com.google.common.collect.Lists;
import kineticrevolution.lib.UUIDs;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.tileEntities.TileSyncBase;
import kineticrevolution.util.Location;
import kineticrevolution.util.PlayerUtils;
import kineticrevolution.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by AEnterprise
 */
public class TileDuster extends TileSyncBase {

	public static final AxisAlignedBB DEFAULT_BOX = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
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

	private final EnumSet<Components> components = EnumSet.noneOf(Components.class);
	private double chanceModifier = 0;
	private double targetHeight = 1;
	private double height = 1;
	private int maxProgress = 20;
	private int progress = 0;
	private int breakProgress = -1;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (height > targetHeight) {
			height -= 0.05;
			spawnParticles(10);
		}
		if (progress >= maxProgress) {
			progress = 0;
			targetHeight = 1;
			height = 1;
			breakProgress = -1;
			worldObj.destroyBlockInWorldPartially(0, xCoord, yCoord - 1, zCoord, breakProgress);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
			if (recipe == null) {
				return;
			}
			spawnParticles(100);
			worldObj.setBlockToAir(xCoord, yCoord - 1, zCoord);
			handleOutputs(worldObj, xCoord, yCoord, zCoord, getOutputs(recipe));

		} else {
			double old = targetHeight;
			targetHeight = 1.0 - (progress * 0.8 / maxProgress);
			breakProgress = (int) ((progress * 10D) / maxProgress);
			if (old != targetHeight) {
				IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
				if (recipe == null) {
					progress = 0;
					targetHeight = 1;
					height = 1;
					breakProgress = -1;
				}
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				worldObj.destroyBlockInWorldPartially(0, xCoord, yCoord - 1, zCoord, breakProgress);
			}
		}
	}

	public void onFallenUpon(Entity entity, float distance) {
		if (distance < 0.8 || height > targetHeight || !canEntityDust(entity))
			return;
		//Corjaantje asked for lightning, so here it is ;)
		if (entity instanceof EntityPlayer && PlayerUtils.playerMatches(UUIDs.CORJAANTJE, (EntityPlayer) entity)) {
			worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, xCoord, yCoord, zCoord));
		}
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
		if (recipe == null)
			return;
		double addedProgress = 2.5 * distance;
		for (Components component : components) {
			addedProgress *= component.getProgressModifier();
		}
		progress += addedProgress;
	}

	private boolean canEntityDust(Entity entity) {
		for (Components component : components) {
			if (component.canEntityDust(entity))
				return true;
		}
		return false;
	}

	public boolean installComponent(Components component) {
		if (!components.contains(component)) {
			maxProgress *= component.getMaxProgressModifier();
			return components.add(component);
		}
		return false;
	}

	public boolean removeComponent(Components component) {
		return components.remove(component);
	}

	public AxisAlignedBB getBox() {
		return DEFAULT_BOX.copy().offset(0, height - 1, 0);
	}


	public void handleOutputs(World world, int x, int y, int z, List<ItemStack> outputs) {
		Utils.dropItemstacks(world, x + .5, y + .5, z + .5, outputs);
	}

	protected List<ItemStack> getOutputs(IDusterRecipe recipe) {
		List<ItemStack> outputs = Lists.newArrayList();
		if (recipe != null) {
			for (IChancedOutput cOutput : recipe.getOutputs()) {
				if (cOutput != null) {
					ItemStack output = cOutput.getOutput();
					if (output != null && output.getItem() != null && output.stackSize > 0) {
						double chance = cOutput.getChance() + chanceModifier;
						while (chance >= 1) {
							outputs.add(output.copy());
							chance--;
						}
						if (chance > 0 && worldObj.rand.nextDouble() < chance) {
							outputs.add(output.copy());
						}
					}
				}
			}
		}
		return outputs;
	}

	protected void spawnParticles(int amount) {
		if (!worldObj.isRemote && worldObj instanceof WorldServer) {
			((WorldServer) worldObj).func_147487_a("blockcrack_" + Block.getIdFromBlock(worldObj.getBlock(xCoord, yCoord - 1, zCoord)) + "_" + worldObj.getBlock(xCoord, yCoord - 1, zCoord).damageDropped(worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord)),
					xCoord + 0.5, yCoord + height, zCoord + 0.5, amount, 0, 0, 0, 0.075);
		}
	}

	public double getHeight() {
		return height;
	}

	@Override
	public void readFromCustomNBT(NBTTagCompound tag) {
		chanceModifier = tag.getDouble("chanceModifier");
		height = tag.getDouble("height");
		targetHeight = tag.getDouble("targetHeight");
		maxProgress = tag.getInteger("maxProgress");
		progress = tag.getInteger("progress");
		breakProgress = tag.getInteger("breakProgress");
		if (worldObj != null)
			worldObj.destroyBlockInWorldPartially(0, xCoord, yCoord - 1, zCoord, breakProgress);
		NBTTagList tagList = tag.getTagList("components", Constants.NBT.TAG_STRING);
		for (int i = 0; i < tagList.tagCount(); i++) {
			components.add(Components.getComponent(tagList.getStringTagAt(i)));
		}
	}

	@Override
	public void writeToCustomNBT(NBTTagCompound tag) {
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight);
		tag.setInteger("maxProgress", maxProgress);
		tag.setInteger("progress", progress);
		tag.setInteger("breakProgress", breakProgress);
		tag.setInteger("components", components.size());
		NBTTagList tagList = new NBTTagList();
		for (Components component : components) {
			tagList.appendTag(new NBTTagString(component.getName()));
		}
		tag.setTag("components", tagList);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
}
