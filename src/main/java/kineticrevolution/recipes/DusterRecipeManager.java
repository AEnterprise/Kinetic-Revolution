package kineticrevolution.recipes;

import java.util.ArrayList;

/**
 * Created by AEnterprise
 */
public class DusterRecipeManager {
	private static ArrayList<IDusterRecipe> recipes = new ArrayList<IDusterRecipe>();

	public static void registerRecipe(IDusterRecipe recipe) {
		recipes.add(recipe);
	}
}
