package kineticrevolution.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import kineticrevolution.lib.Names;
import kineticrevolution.loaders.ItemLoader;

public class CTabs {
	public static final CreativeTabs MAIN_TAB = new MainTab();
	public static final CreativeTabs DUST_TAB = new DustTab();

	public static class MainTab extends CreativeTabs {

		public MainTab() {
			super(Names.CTabs.MAIN_TAB);
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	}

	public static class DustTab extends CreativeTabs {

		public DustTab() {
			super(Names.CTabs.DUST_TAB);
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.dust;
		}

		@Override
		public boolean hasSearchBar() {
			return true;
		}
	}
}