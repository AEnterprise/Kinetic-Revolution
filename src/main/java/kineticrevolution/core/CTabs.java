package kineticrevolution.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import kineticrevolution.lib.Reference;
import kineticrevolution.loaders.ItemLoader;

/**
 * Created by AEnterprise
 */
public class CTabs {

	public static class MainTab extends CreativeTabs {

		public MainTab() {
			super(Reference.MOD_ID);
		}

		@Override
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	}
}
