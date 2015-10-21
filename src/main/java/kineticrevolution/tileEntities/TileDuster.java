package kineticrevolution.tileEntities;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.networking.ISynchronizedTile;
import kineticrevolution.networking.MessageByteBuff;
import kineticrevolution.networking.PacketHandler;
import kineticrevolution.recipes.DusterRecipeManager;
import kineticrevolution.recipes.IChancedOutput;
import kineticrevolution.recipes.IDusterRecipe;
import kineticrevolution.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by AEnterprise
 */
public class TileDuster extends TileSyncBase implements ISynchronizedTile {
	public double targetHeight = 1.0;
	private double chanceModifier;
	private double height = 1.0;

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
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) + 1;
		if (meta >= 4) {
			targetHeight = 1.0;
			if (!worldObj.isRemote) {
				worldObj.setBlock(xCoord, yCoord - 1, zCoord, BlockLoader.duster, 0, 2);
				NBTTagCompound tag = new NBTTagCompound();
				writeToNBT(tag);
				tag.setInteger("y", yCoord - 1);
				TileEntity tileEntity = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
				if (tileEntity != null)
					tileEntity.readFromNBT(tag);
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				handleOutputs(worldObj, xCoord, yCoord, zCoord, getOutputs(recipe));
			}
		} else {
			targetHeight -= 0.2;
			if (!worldObj.isRemote) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
				PacketHandler.instance.sendToAllAround(new MessageByteBuff(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
			}
		}
		System.out.println("Target heigt: " + targetHeight + ", world.isRemote: " + worldObj.isRemote);
		System.out.println("Height: " + height + ", world.isRemote: " + worldObj.isRemote);
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
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("chanceModifier", chanceModifier);
		tag.setDouble("height", height);
		tag.setDouble("targetHeight", targetHeight);
	}

	@Override
	public int getIdentifier() {
		return 0;
	}

	@Override
	public void writeToByteBuff(ByteBuf buf) {
		buf.writeDouble(targetHeight);
	}

	@Override
	public void readFromByteBuff(ByteBuf buf) {
		targetHeight = buf.readDouble();
	}
}
