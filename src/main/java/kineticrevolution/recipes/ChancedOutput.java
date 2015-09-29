package kineticrevolution.recipes;

import java.util.Random;

import net.minecraft.item.ItemStack;

import kineticrevolution.api.recipe.IChancedOutput;

public class ChancedOutput implements IChancedOutput {

	private final ItemStack output;
	private final double chance;

	public ChancedOutput(ItemStack output, double chance) {
		if (output == null || output.getItem() == null || output.stackSize <= 0) {
			throw new IllegalArgumentException("Invalid ItemStack");
		}
		if (chance <= 0 || chance > 1) {
			throw new IllegalArgumentException("Invalid chance");
		}
		this.output = output.copy();
		this.chance = chance;
	}

	@Override
	public double getChance() {
		return chance;
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public int getPercentChance() {
		return (int) (chance * 100 + 0.5);
	}

	@Override
	public ItemStack getOutput(Random random) {
		return chance == 1 || random.nextDouble() < chance ? output.copy() : null;
	}
}
