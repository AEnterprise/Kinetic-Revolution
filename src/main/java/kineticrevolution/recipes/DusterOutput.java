package kineticrevolution.recipes;

import net.minecraft.item.ItemStack;

public class DusterOutput implements IChancedOutput {

	private final ItemStack output;
	private final double chance;

	public DusterOutput(ItemStack output, double chance) {
		this.output = output;
		this.chance = chance;
	}

	@Override
	public ItemStack getOutput() {
		return output;
	}

	@Override
	public double getChance() {
		return chance;
	}

}
