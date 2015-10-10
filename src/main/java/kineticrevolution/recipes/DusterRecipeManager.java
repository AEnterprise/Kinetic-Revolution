package kineticrevolution.recipes;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.world.World;

public class DusterRecipeManager {

	private static final List<IDusterRecipe> recipes = Lists.newArrayList();

	public static void registerRecipe(IDusterRecipe recipe) {
		recipes.add(recipe);
	}

	public static IDusterRecipe getRecipe(World world, int x, int y, int z) {
		for (IDusterRecipe recipe : recipes) {
			if (recipe.isValidInput(world, x, y, z))
				return recipe;
		}
		return null;
	}

	public static List<? extends IDusterRecipe> getRecipeList() {
		return ImmutableList.copyOf(recipes);
	}


}
