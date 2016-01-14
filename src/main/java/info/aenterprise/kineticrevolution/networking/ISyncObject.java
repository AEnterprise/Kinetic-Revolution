package info.aenterprise.kineticrevolution.networking;

import io.netty.buffer.ByteBuf;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public interface ISyncObject {

	void writeToByteBuff(ByteBuf buf);

	void readFromByteBuff(ByteBuf buf);
}
