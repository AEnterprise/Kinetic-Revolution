package kineticrevolution;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import kineticrevolution.core.CTabs;
import kineticrevolution.loaders.BlockLoader;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by AEnterprise
 */

@Mod(modid = "kineticrevolution", name = "Kinetic Revolution", version = "@MODVERSION@", acceptableRemoteVersions = "1.7.10")
public class KineticRevolution {

	public static final CreativeTabs MAIN_TAB = new CTabs.MainTab();
	@Mod.Instance
	public static KineticRevolution instance;
	@SidedProxy(serverSide = "kineticrevolution.proxy.CommonProxy", clientSide = "kineticrevolution.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItemLoader.init();
		BlockLoader.init();
	}
}
