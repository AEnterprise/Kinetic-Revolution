package kineticrevolution.tileEntities;

import com.google.common.collect.Lists;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by AEnterprise
 */
public class TileDuster extends TileSyncBase {
	private static final AxisAlignedBB box = AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
	private double targetHeight = 1.0;
	private double chanceModifier;
	private double height = 1.0;
	//MC resets the client TE when the meta changes so we do meta in the TE
	private int meta;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (height > targetHeight) {
			height -= 0.05;
		}
	}

	public void onFallenUpon(Entity entity, float distance) {
		if (distance < 0.8 || height > targetHeight)
			return;
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
		if (recipe == null)
			return;
		meta++;
		if (meta >= 4) {
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
			targetHeight -= 0.2;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	public AxisAlignedBB getBox() {
		if (meta >= 4)
			meta = 0;
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
		meta = tag.getInteger("meta");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight);
		tag.setInteger("meta", meta);
	}


	@Override
	public NBTTagCompound writeToSyncNBT(NBTTagCompound tag) {
		tag.setDouble("targetHeight", targetHeight);
		tag.setInteger("meta", meta);
		return tag;
	}

	@Override
	public void readFromSyncNBT(NBTTagCompound tag) {
		targetHeight = tag.getDouble("targetHeight");
		meta = tag.getInteger("meta");
	}
}