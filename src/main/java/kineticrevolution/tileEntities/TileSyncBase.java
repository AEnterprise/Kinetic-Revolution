package kineticrevolution.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public abstract class TileSyncBase extends TileEntity {

	@Override
	public Packet getDescriptionPacket() {
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, writeToSyncNBT(new NBTTagCompound()));
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromSyncNBT(pkt.func_148857_g());
	}

	public abstract NBTTagCompound writeToSyncNBT(NBTTagCompound tag);

	public abstract void readFromSyncNBT(NBTTagCompound tag);
}
