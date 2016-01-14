package info.aenterprise.kineticrevolution.tileEntities;

import info.aenterprise.kineticrevolution.networking.ISyncronizedTile;
import info.aenterprise.kineticrevolution.networking.MessageByteBuff;
import info.aenterprise.kineticrevolution.networking.NetworkManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public abstract class TileSyncBase extends TileEntity implements ISyncronizedTile {

	@Override
	public BlockPos getBlockPos() {
		return getPos();
	}

	public void syncToPlayer(EntityPlayerMP player) {
		NetworkManager.INSTANCE.sendTo(new MessageByteBuff(this), player);
	}
}
