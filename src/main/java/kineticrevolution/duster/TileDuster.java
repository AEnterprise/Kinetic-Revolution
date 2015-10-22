package kineticrevolution.duster;

import com.google.common.collect.Lists;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.tileEntities.TileSyncBase;
import kineticrevolution.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by AEnterprise
 */
public class TileDuster extends TileSyncBase {
	private static final AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
	private double targetHeight = 1.0;
	private double chanceModifier;
	private double height = 1.0;
	//MC resets the client TE when the meta changes we're not using meta
	private double progress;
	private int maxProgress = 20;
	private ArrayList<Components> components = new ArrayList<Components>();

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (height > targetHeight) {
			height -= 0.05;
			spawnParticles(10);
		}
		if (progress >= maxProgress) {
			progress -= maxProgress;
			IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
			if (recipe == null)
				return;
			worldObj.destroyBlockInWorldPartially(100, xCoord, yCoord - 1, zCoord, 0);
			spawnParticles(100);
			targetHeight = 1.0;
			worldObj.setBlock(xCoord, yCoord - 1, zCoord, BlockLoader.duster, 0, 2);
			NBTTagCompound tag = new NBTTagCompound();
			writeToNBT(tag);
			tag.setInteger("y", yCoord - 1);
			TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
			if (tileEntity != null)
				tileEntity.readFromNBT(tag);
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);

			handleOutputs(worldObj, xCoord, yCoord, zCoord, getOutputs(recipe));

		} else {
			double old = targetHeight;
			targetHeight = 1.0 - (progress * 0.8 / maxProgress);
			if (old != targetHeight) {
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				worldObj.destroyBlockInWorldPartially(100, xCoord, yCoord - 1, zCoord, (int) (progress * 10) / maxProgress);
			}
		}
	}

	public void onFallenUpon(Entity entity, float distance) {
		if (distance < 0.8 || height > targetHeight || !canEntityDust(entity))
			return;
		//corjaantje asked for ligning, here it is
		if (entity instanceof EntityPlayer && ((EntityPlayer) entity).getGameProfile().getId().equals(UUID.fromString("209f3364-0042-4d2a-b539-8640e6bbd6c1"))) {
			worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, xCoord, yCoord, zCoord));
		}
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
		if (recipe == null)
			return;
		progress += 2.5 * distance;
	}

	private boolean canEntityDust(Entity entity) {
		for (Components component : components) {
			if (component.canEntityDust(entity))
				return true;
		}
		return false;
	}

	public void installComponent(Components component) {
		if (!components.contains(component))
			components.add(component);
	}

	public void removeComponent(Components component) {
		components.remove(component);
	}

	public AxisAlignedBB getBox() {
		return box.copy().offset(0, height - 1, 0);
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
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		chanceModifier = tag.getDouble("chanceModifier");
		height = tag.getDouble("height");
		targetHeight = tag.getDouble("targetHeight");
		progress = tag.getDouble("progress");
		maxProgress = tag.getInteger("maxProgress");
		int numComponents = tag.getInteger("components");
		for (int i = 0; i < numComponents; i++) {
			components.add(Components.values()[tag.getInteger("component" + i)]);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight);
		tag.setDouble("progress", progress);
		tag.setInteger("maxProgress", maxProgress);
		tag.setInteger("components", components.size());
		for (int i = 0; i < components.size(); i++) {
			tag.setInteger("component" + i, components.get(i).ordinal());
		}
	}


	@Override
	public NBTTagCompound writeToSyncNBT(NBTTagCompound tag) {
		tag.setDouble("targetHeight", targetHeight);
		tag.setDouble("progress", progress);
		return tag;
	}

	@Override
	public void readFromSyncNBT(NBTTagCompound tag) {
		targetHeight = tag.getDouble("targetHeight");
		progress = tag.getInteger("progress");
	}
}