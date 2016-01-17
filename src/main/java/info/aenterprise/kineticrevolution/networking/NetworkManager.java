package info.aenterprise.kineticrevolution.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */
public class NetworkManager {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("kineticrevolution");

	public static void init() {
		INSTANCE.registerMessage(MessageByteBuff.class, MessageByteBuff.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageFluidicCompressorMode.class, MessageFluidicCompressorMode.class, 1, Side.SERVER);
	}
}
