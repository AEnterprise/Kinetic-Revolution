package kineticrevolution.core;

import kineticrevolution.lib.Reference;
import kineticrevolution.loaders.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class CTabs {
	public static final CreativeTabs MAIN_TAB = new MainTab();
	public static final CreativeTabs DUST_TAB = new DustTab();

	public static class MainTab extends CreativeTabs {

		public MainTab() {
			super(Reference.MOD_ID);
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	}

	public static class DustTab extends CreativeTabs {

		public DustTab() {
			super("KRDusts");
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.dust;
		}
	}
}
