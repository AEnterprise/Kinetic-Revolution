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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * Created by AEnterprise
 */
public class TileDuster extends TileSyncBase {
	private static final AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
	private double targetHeight = 1.0;
	private double chanceModifier;
	private double height = 1.0;
	//MC resets the client TE when the meta changes we're not using meta
	private int progress;
	private int maxProgress = 10;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (height > targetHeight) {
			height -= 0.05;
			spawnParticles(10);
		}
	}

	protected void spawnParticles(int amount) {
		if (!worldObj.isRemote && worldObj instanceof WorldServer) {
			((WorldServer) worldObj).func_147487_a("blockcrack_" + Block.getIdFromBlock(worldObj.getBlock(xCoord, yCoord - 1, zCoord)) + "_" + worldObj.getBlock(xCoord, yCoord - 1, zCoord).damageDropped(worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord)),
					xCoord + 0.5, yCoord + height, zCoord + 0.5, amount, 0, 0, 0, 0.075);
		}
	}

	public void onFallenUpon(Entity entity, float distance) {
		if (distance < 0.8 || height > targetHeight)
			return;
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
		if (recipe == null)
			return;
		progress++;
		if (progress >= maxProgress) {
			progress = 0;
			worldObj.destroyBlockInWorldPartially(100, xCoord, yCoord - 1, zCoord, progress);
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
			targetHeight -= 0.8 / maxProgress;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			worldObj.destroyBlockInWorldPartially(100, xCoord, yCoord - 1, zCoord, progress);
		}
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

	public double getHeight() {
		return height;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		chanceModifier = tag.getDouble("chanceModifier");
		height = tag.getDouble("height");
		targetHeight = tag.getDouble("targetHeight");
		progress = tag.getInteger("meta");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight);
		tag.setInteger("meta", progress);
	}


	@Override
	public NBTTagCompound writeToSyncNBT(NBTTagCompound tag) {
		tag.setDouble("targetHeight", targetHeight);
		tag.setInteger("progress", progress);
		return tag;
	}

	@Override
	public void readFromSyncNBT(NBTTagCompound tag) {
		targetHeight = tag.getDouble("targetHeight");
		progress = tag.getInteger("progress");
	}
}