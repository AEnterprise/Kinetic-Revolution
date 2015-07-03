package kineticrevolution.core;

import kineticrevolution.loaders.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by AEnterprise
 */
public class CTabs {

	public static class MainTab extends CreativeTabs {

		public MainTab() {
			super("kineticrevolution");
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	}
}
