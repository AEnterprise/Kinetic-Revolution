package info.aenterprise.kineticrevolution.networking;

import info.aenterprise.kineticrevolution.tileEntities.TileEntityFluidicCompressor;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class MessageFluidicCompressorMode implements IMessage, IMessageHandler<MessageFluidicCompressorMode, IMessage> {
	public BlockPos pos;
	public TileEntityFluidicCompressor.Mode mode;

	public MessageFluidicCompressorMode() {
	}

	public MessageFluidicCompressorMode(BlockPos pos, TileEntityFluidicCompressor.Mode mode) {
		this.pos = pos;
		this.mode = mode;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		mode = TileEntityFluidicCompressor.Mode.values()[buf.readInt()];
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeInt(mode.ordinal());
	}

	@Override
	public IMessage onMessage(MessageFluidicCompressorMode message, MessageContext ctx) {
		TileEntity entity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.pos);
		if (entity instanceof TileEntityFluidicCompressor) {
			((TileEntityFluidicCompressor) entity).setMode(message.mode);
		}
		return null;
	}
}
