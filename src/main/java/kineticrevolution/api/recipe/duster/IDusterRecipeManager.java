package kineticrevolution.api.recipe.duster;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IDusterRecipeManager {

	void addRecipe(IDusterRecipe recipe);

	void removeRecipe(ItemStack input);

	IDusterRecipe getRecipe(ItemStack input);

	List<? extends IDusterRecipe> getRecipeList();

}
