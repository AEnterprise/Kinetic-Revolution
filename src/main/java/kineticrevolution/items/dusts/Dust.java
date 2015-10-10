package kineticrevolution.items.dusts;

import kineticrevolution.loaders.ItemLoader;
import net.minecraft.item.ItemStack;

/**
 * Created by AEnterprise
 */
public class Dust {
	public final String name;
	public final int color;
	public final int dustPerOre;

	public Dust(String name, int color) {
		this(name, color, 2);
	}

	public Dust(String name, int color, int dustPerOre) {
		this.name = name;
		this.color = color;
		this.dustPerOre = dustPerOre;
	}

	public boolean shouldRegister() {
		return true;
	}

	public ItemStack getSmeltingOutput() {
		return null;
	}

	public ItemStack getStack() {
		return getStack(1);
	}

	public ItemStack getStack(int count) {
		return new ItemStack(ItemLoader.dust, count, DustManager.dusts.indexOf(name));
	}
}
