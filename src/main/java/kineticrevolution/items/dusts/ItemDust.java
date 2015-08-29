package kineticrevolution.items.dusts;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import kineticrevolution.core.CTabs;
import kineticrevolution.items.ItemBase;
import kineticrevolution.lib.Names;
import kineticrevolution.loaders.ItemLoader;
import kineticrevolution.util.Utils;

/**
 * Created by AEnterprise
 */
public class ItemDust extends ItemBase {


	public ItemDust() {
		super(Names.Items.DUST, CTabs.DUST_TAB);
		setHasSubtypes(true);
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int pass) {
		Dust dust = DustManager.getDust(stack.getItemDamage());
		return dust != null ? dust.color : super.getColorFromItemStack(stack, pass);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		Dust dust = DustManager.getDust(stack.getItemDamage());
		return dust != null ? "item.dust" + dust.name : super.getUnlocalizedName(stack);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		Dust dust = DustManager.getDust(stack.getItemDamage());
		if (dust != null) {
			String s = "item.dust" + dust.name;
			String tS = Utils.localize(s);
			if (!s.equalsIgnoreCase(tS))
				return tS;
			return Utils.localizeAllFormatted("item.dust.name", "material." + Utils.decapitalizeFirstChar(dust.name) + ".name");
		}
		return super.getItemStackDisplayName(stack);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int meta : DustManager.registeredDusts.keySet()) {
			list.add(new ItemStack(ItemLoader.dust, 1, meta));
		}
	}
}
