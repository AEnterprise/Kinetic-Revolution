package kineticrevolution.tileEntities;

import com.google.common.collect.Lists;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.Utils;
import kineticrevolution.util.debug.DoubleContainer;
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
	private static final AxisAlignedBB[] boxes = {
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.8, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.6, 1),
			AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.4, 1),
	};
	private DoubleContainer targetHeight = new DoubleContainer(1.0);
	private double chanceModifier;
	private double height = 1.0;
	//MC resets the client TE when the meta changes so we do meta in the TE
	private int meta;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (height > targetHeight.getValue()) {
			height -= 0.05;
		}
	}

	public void onFallenUpon(Entity entity, float distance) {
		if (distance < 0.8 || height > targetHeight.getValue())
			return;
		IDusterRecipe recipe = DusterRecipeManager.getRecipe(worldObj, xCoord, yCoord - 1, zCoord);
		if (recipe == null)
			return;
		meta++;
		if (meta >= 4) {
			targetHeight.setValue(1.0, worldObj.isRemote);
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
			targetHeight.setValue(targetHeight.getValue() - 0.2, worldObj.isRemote);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
	}

	public AxisAlignedBB getBox() {
		if (meta >= 4)
			meta = 0;
		return boxes[meta].copy();
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
		targetHeight.setValue(tag.getDouble("targetHeight"), worldObj.isRemote);
		meta = tag.getInteger("meta");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight.getValue());
		tag.setInteger("meta", meta);
	}


	@Override
	public NBTTagCompound writeToSyncNBT(NBTTagCompound tag) {
		tag.setDouble("targetHeight", targetHeight.getValue());
		tag.setInteger("meta", meta);
		return tag;
	}

	@Override
	public void readFromSyncNBT(NBTTagCompound tag) {
		targetHeight.setValue(tag.getDouble("targetHeight"), worldObj.isRemote);
		meta = tag.getInteger("meta");
	}
}