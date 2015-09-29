package kineticrevolution.recipes.duster;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;

import kineticrevolution.api.recipe.duster.IDusterRecipe;
import kineticrevolution.api.recipe.duster.IDusterRecipeManager;

public class DusterRecipeManager implements IDusterRecipeManager {

	private final List<IDusterRecipe> recipes = Lists.newArrayList();

	@Override
	public void addRecipe(IDusterRecipe recipe) {
		if (recipe != null && !recipes.contains(recipe)) {
			recipes.add(recipe);
		}
	}

	@Override
	public void removeRecipe(ItemStack input) {
		if (input != null) {
			IDusterRecipe recipe = null;
			for (Iterator<IDusterRecipe> iterator = recipes.iterator(); iterator.hasNext(); recipe = iterator.next()) {
				if (recipe != null && recipe.isValidInput(input)) {
					iterator.remove();
					return;
				}
			}
		}
	}

	@Override
	public IDusterRecipe getRecipe(ItemStack input) {
		if (input != null) {
			for (IDusterRecipe recipe : recipes) {
				if (recipe != null && recipe.isValidInput(input)) {
					return recipe;
				}
			}
		}
		return null;
	}

	@Override
	public List<? extends IDusterRecipe> getRecipeList() {
		return ImmutableList.copyOf(recipes);
	}
}
