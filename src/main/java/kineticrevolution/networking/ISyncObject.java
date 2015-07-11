package kineticrevolution.networking;

import io.netty.buffer.ByteBuf;

/**
 * Created by AEnterprise
 */
public interface ISyncObject {

	void writeToByteBuff(ByteBuf buf);

	void readFromByteBuff(ByteBuf buf);
}
