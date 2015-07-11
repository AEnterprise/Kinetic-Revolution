package kineticrevolution.multiblocks.tileEntities;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import kineticrevolution.networking.ISynchronizedTile;
import kineticrevolution.networking.MessageByteBuff;
import kineticrevolution.networking.PacketHandler;
import kineticrevolution.util.MultiBlockData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public abstract class TileMultiBlockBase extends TileEntity implements IMultiBlock, ISynchronizedTile {
	public final MultiBlockData data;

	public TileMultiBlockBase() {
		data = new MultiBlockData(getPattern());
		data.setValid(false);
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote && isMaster())
			System.out.println("I'M THE MASTER");
	}

	@Override
	public void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation) {
		System.out.println(String.format("Forming multiblock, master location (x:%s, y:%s, z:%s), rotation:%s, own location(x:%s, y:%s,z:%s)", xCoord + masterXoffset, yCoord + masterYoffset, zCoord + masterZoffset, rotation, xCoord, yCoord, zCoord));
		data.setMasterXoffset(masterXoffset);
		data.setMasterYoffset(masterYoffset);
		data.setMasterZoffset(masterZoffset);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
		if (masterXoffset == 0 && masterYoffset == 0 && masterZoffset == 0) {
			data.setMaster(true);
		}
		data.setValid(true);
		sync();
	}

	public void sync() {
		if (!worldObj.isRemote) {
			PacketHandler.instance.sendToAllAround(new MessageByteBuff(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 40));
		}
	}

	@Override
	public void deformMultiBlock() {
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
		worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord), 100);
		sync();
	}

	@Override
	public boolean onBlockClicked(EntityPlayer player) {
		return data != null;
	}

	@Override
	public void blockUpdate() {
		if (data == null) {
			return;
		}
		if (data.getPattern().checkMultiBlock(worldObj,
				xCoord + data.getMasterXoffset() - data.getPattern().getMasterXoffset(),
				yCoord + data.getMasterYoffset() - data.getPattern().getMasterYoffset(),
				zCoord + data.getMasterZoffset() - data.getPattern().getMasterZoffset()) != data.getRotation()) {
			data.getPattern().deformMultiblock(worldObj,
					xCoord + data.getMasterXoffset() - data.getPattern().getMasterXoffset(),
					yCoord + data.getMasterYoffset() - data.getPattern().getMasterYoffset(),
					zCoord + data.getMasterZoffset() - data.getPattern().getMasterZoffset(),
					data.getRotation());
		}
	}

	@Override
	public int getMasterX() {
		return xCoord + data.getMasterXoffset();
	}

	@Override
	public int getMasterY() {
		return yCoord + data.getMasterYoffset();
	}

	@Override
	public int getMasterZ() {
		return zCoord + data.getMasterZoffset();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		data.loadFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (data != null) {
			data.saveToNBT(tag);
		}
	}

	@Override
	public boolean isMaster() {
		return data != null && data.isMaster();
	}

	@Override
	public int getX() {
		return xCoord;
	}

	@Override
	public int getY() {
		return yCoord;
	}

	@Override
	public int getZ() {
		return zCoord;
	}

	@Override
	public void writeToByteBuff(ByteBuf buf) {
		if (data != null) {
			buf.writeBoolean(true);
			data.writeToByteBuff(buf);
		}
	}

	@Override
	public void readFromByteBuff(ByteBuf buf) {
		if (buf.readBoolean()) {
			data.readFromByteBuff(buf);
		}
	}
}
