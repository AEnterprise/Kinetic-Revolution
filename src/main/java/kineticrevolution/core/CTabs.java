package kineticrevolution.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import kineticrevolution.lib.Names;
import kineticrevolution.lib.Textures;
import kineticrevolution.loaders.ItemLoader;

public class CTabs {

	public static final CreativeTabs MAIN_TAB = new KRTab(Names.CTabs.MAIN_TAB) {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ItemLoader.machineConfigurator;
		}
	};
	public static final CreativeTabs DUST_TAB = new KRTab(Names.CTabs.DUST_TAB) {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ItemLoader.dust;
		}
	}.enableSearchBar();

	public static abstract class KRTab extends CreativeTabs {

		private boolean hasSearchBar = false;

		public KRTab(String label) {
			super(label);
		}

		@Override
		public boolean hasSearchBar() {
			return hasSearchBar;
		}

		public CreativeTabs enableSearchBar() {
			hasSearchBar = true;
			setBackgroundImageName(Textures.CTabs.SEARCH_BAR_BACKGROUND);
			return this;
		}
	}
}
