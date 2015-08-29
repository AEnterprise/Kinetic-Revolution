package kineticrevolution.multiblocks.tileEntities;

import cpw.mods.fml.common.network.NetworkRegistry;
import io.netty.buffer.ByteBuf;
import kineticrevolution.multiblocks.interfaces.IMultiBlock;
import kineticrevolution.networking.ISynchronizedTile;
import kineticrevolution.networking.MessageByteBuff;
import kineticrevolution.networking.PacketHandler;
import kineticrevolution.util.MultiBlockData;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.concurrent.Callable;

/**
 * Created by AEnterprise
 */
public abstract class TileMultiBlockBase extends TileEntity implements IMultiBlock, ISynchronizedTile {
	protected final MultiBlockData data;
	private int timer = 0;

	public TileMultiBlockBase() {
		data = new MultiBlockData(getPattern());
		data.setValid(false);
	}

	@Override
	public void updateEntity() {
		timer--;
		if (timer <= 0) {
			sync();
			timer = 100;
		}
	}

	@Override
	public void func_145828_a(CrashReportCategory crash) {
		super.func_145828_a(crash);
		crash.addCrashSectionCallable("Multiblock data", new Callable() {
			@Override
			public Object call() throws Exception {
				return data.toString();
			}
		});
	}

	@Override
	public void formMultiBlock(int masterXoffset, int masterYoffset, int masterZoffset, int rotation) {
		System.out.println(String.format("Forming multiblock, master location (x:%s, y:%s, z:%s), rotation:%s, own location(x:%s, y:%s,z:%s)", xCoord + masterXoffset, yCoord + masterYoffset, zCoord + masterZoffset, rotation, xCoord, yCoord, zCoord));
		data.setMasterXoffset(masterXoffset);
		data.setMasterYoffset(masterYoffset);
		data.setMasterZoffset(masterZoffset);
		data.setRotation(rotation);
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
		if (masterXoffset == 0 && masterYoffset == 0 && masterZoffset == 0) {
			data.setMaster(true);
		}
		data.setValid(true);
		timer = 0;
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
		data.setMaster(false);
		data.setValid(false);
		timer = 0;
	}

	@Override
	public boolean onBlockClicked(EntityPlayer player) {
		return data.isValid();
	}

	@Override
	public void blockUpdate() {
		if (!data.isValid()) {
			return;
		}
		if (!data.getPattern().isStillValid(worldObj,
				xCoord + data.getMasterXoffset() - data.getPattern().getMasterXoffset(),
				yCoord + data.getMasterYoffset() - data.getPattern().getMasterYoffset(),
				zCoord + data.getMasterZoffset() - data.getPattern().getMasterZoffset(), data.getRotation())) {
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
		data.saveToNBT(tag);
	}

	@Override
	public boolean isMaster() {
		return data.isMaster();
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
	public int getRotation() {
		return data.getRotation();
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
