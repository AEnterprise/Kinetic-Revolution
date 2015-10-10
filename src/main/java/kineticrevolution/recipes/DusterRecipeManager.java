package kineticrevolution.recipes;

import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by AEnterprise
 */
public class DusterRecipeManager {
	private static ArrayList<IDusterRecipe> recipes = new ArrayList<IDusterRecipe>();

	public static void registerRecipe(IDusterRecipe recipe) {
		recipes.add(recipe);
	}

	public static IDusterRecipe getRecipe(World world, int x, int y, int z) {
		for (IDusterRecipe recipe : recipes) {
			if (recipe.validInput(world, x, y, z))
				return recipe;
		}
		return null;
	}


}
