package kineticrevolution;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import kineticrevolution.core.CTabs;
import kineticrevolution.lib.Reference;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.proxy.CommonProxy;

/**
 * Created by AEnterprise
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES, acceptableRemoteVersions = Reference.MC_VERSION, acceptedMinecraftVersions = Reference.MC_VERSION)
public class KineticRevolution {

	public static final CreativeTabs MAIN_TAB = new CTabs.MainTab();
	@Mod.Instance
	public static KineticRevolution instance;
	@SidedProxy(serverSide = Reference.PROXY_SERVER, clientSide = Reference.PROXY_CLIENT)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItemLoader.init();
		BlockLoader.init();
	}
}
