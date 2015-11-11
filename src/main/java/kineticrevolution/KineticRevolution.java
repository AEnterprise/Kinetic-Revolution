package kineticrevolution;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import kineticrevolution.core.recipes.AssemblerRecipes;
import kineticrevolution.items.dusts.DustManager;
import kineticrevolution.lib.Reference;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.multiblocks.patterns.Patterns;
import kineticrevolution.networking.PacketHandler;
import kineticrevolution.proxy.CommonProxy;
import kineticrevolution.proxy.GuiHandler;

/**
 * Created by AEnterprise
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES, acceptableRemoteVersions = Reference.MC_VERSION, acceptedMinecraftVersions = Reference.MC_VERSION)
public class KineticRevolution {

	@Mod.Instance
	public static KineticRevolution instance;
	@SidedProxy(serverSide = Reference.PROXY_SERVER, clientSide = Reference.PROXY_CLIENT)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItemLoader.init();
		BlockLoader.init();
		ItemLoader.addRecipes();
		BlockLoader.addRecipes();
		proxy.setupRenderers();
		PacketHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Patterns.compilePatterns();
		DustManager.registerDusts();
		AssemblerRecipes.INSTANCE.addRecipes();
	}
}
