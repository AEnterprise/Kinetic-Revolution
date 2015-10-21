package kineticrevolution.tileEntities;

import cpw.mods.fml.common.network.NetworkRegistry;
import kineticrevolution.networking.ISynchronizedTile;
import kineticrevolution.networking.MessageByteBuff;
import kineticrevolution.networking.PacketHandler;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by AEnterprise
 */
public abstract class TileSyncBase extends TileEntity implements ISynchronizedTile {
	private int syncCounter = 100;

	@Override
	public void updateEntity() {
		//sync every 100 ticks to fix any possible desyncs or for players who where not in the area during the last sync
		syncCounter--;
		if (syncCounter <= 0) {
			PacketHandler.instance.sendToAllAround(new MessageByteBuff(this), new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 20));
			syncCounter = 100;
		}
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
}
