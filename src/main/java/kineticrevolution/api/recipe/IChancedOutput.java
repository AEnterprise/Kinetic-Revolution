package kineticrevolution.api.recipe;

import java.util.Random;

import net.minecraft.item.ItemStack;

public interface IChancedOutput {

	double getChance();

	ItemStack getOutput();

	int getPercentOutput();

	ItemStack getOutput(Random random);

}
