package kineticrevolution.api.recipe.duster;

import java.util.List;

import net.minecraft.item.ItemStack;

import kineticrevolution.api.recipe.IChancedOutput;

public interface IDusterRecipe {

	boolean isValidInput(ItemStack input);

	List<IChancedOutput> getOutputs();

	List<ItemStack> getExampleInputs();

}
