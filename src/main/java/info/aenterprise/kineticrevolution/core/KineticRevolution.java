package info.aenterprise.kineticrevolution.core;

import info.aenterprise.kineticrevolution.gui.GuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Copyright (c) 2015, AEnterprise
 * http://www.aenterprise.info/
 */

@Mod(modid = "kineticrevolution", version = "@MODVERSION@")
public class KineticRevolution {
	@Instance
	public static KineticRevolution INSTANCE;

	@SidedProxy(clientSide = "info.aenterprise.kineticrevolution.core.ClientProxy", serverSide = "info.aenterprise.kineticrevolution.core.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs creativeTab = new CreativeTabs("kineticRevolutionTab") {
		@Override
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		BlockLoader.addBlocks();
		ItemLoader.addItems();
		BlockLoader.addRecipes();
		ItemLoader.addRecipes();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
	}
}
