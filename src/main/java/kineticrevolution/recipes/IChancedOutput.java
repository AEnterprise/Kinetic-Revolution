package kineticrevolution.recipes;

import net.minecraft.item.ItemStack;

public interface IChancedOutput {

	ItemStack getOutput();

	double getChance();

}
