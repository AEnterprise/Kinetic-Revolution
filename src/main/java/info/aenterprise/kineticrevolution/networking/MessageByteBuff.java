package info.aenterprise.kineticrevolution.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class MessageByteBuff implements IMessage, IMessageHandler<MessageByteBuff, IMessage>{
	private ISyncronizedTile tile;

	public MessageByteBuff() {
	}

	public MessageByteBuff(ISyncronizedTile tile) {
		this.tile = tile;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(new BlockPos(buf.readInt(), buf.readInt(), buf.readInt()));
		if (entity instanceof ISyncronizedTile) {
			ISyncronizedTile sync = (ISyncronizedTile) entity;
			if (sync.getIdentifier() == buf.readInt()) {
				sync.readFromByteBuff(buf);
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(tile.getBlockPos().getX());
		buf.writeInt(tile.getBlockPos().getY());
		buf.writeInt(tile.getBlockPos().getZ());
		buf.writeInt(tile.getIdentifier());
		tile.writeToByteBuff(buf);
	}

	@Override
	public IMessage onMessage(MessageByteBuff message, MessageContext ctx) {
		return null;
	}
}
