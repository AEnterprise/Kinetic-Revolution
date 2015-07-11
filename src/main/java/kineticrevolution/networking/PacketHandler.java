package kineticrevolution.networking;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by AEnterprise
 */
public class PacketHandler {
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel("kineticrevolution");

	public static void init() {
		instance.registerMessage(MessageByteBuff.class, MessageByteBuff.class, 0, Side.CLIENT);
	}
}
