package kineticrevolution.items.dusts;

import net.minecraft.item.ItemStack;

import kineticrevolution.loaders.ItemLoader;


public class Dust {

	private final String name;
	private final int color;

	public Dust(String name, int color) {
		this.name = name;
		this.color = color;
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
		return new ItemStack(ItemLoader.dust, count, DustManager.getMeta(name));
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}

}